/*
 * Copyright 2024 Raonsecure
 */

package org.omnione.did.cli.command.did;

import org.omnione.did.cli.OmniCLI;
import org.omnione.did.cli.command.util.CommandUtils;
import org.omnione.did.cli.command.util.OmniUtils;
import org.omnione.did.core.data.rest.DidKeyInfo;
import org.omnione.did.core.data.rest.SignatureParams;
import org.omnione.did.core.manager.DidManager;
import org.omnione.did.crypto.enums.DigestType;
import org.omnione.did.crypto.enums.MultiBaseType;
import org.omnione.did.crypto.util.DigestUtils;
import org.omnione.did.crypto.util.MultiBaseUtils;
import org.omnione.did.data.model.enums.did.AuthType;
import org.omnione.did.data.model.enums.did.ProofPurpose;
import org.omnione.did.wallet.key.WalletManagerFactory;
import org.omnione.did.wallet.key.WalletManagerInterface;
import org.omnione.did.wallet.key.data.KeyElement;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

@Command(name = "createDid", mixinStandardHelpOptions = true, version = OmniCLI.VERSION, description = "Create DID Document")
public class CreateDid implements Callable<Void> {

    @Option(names = {"-m", "--wallet-manager"}, required = true, description =  "Wallet file path")
    public String walletManager;

    @Option(names = {"-p", "--wallet-manager-password"}, required = true, interactive = true, description = "Wallet password")
    public char[] password;

    @Option(names = {"-f", "--did-file"}, required = true, description = "DID Document save file name")
    public File didFile;

    @Option(names = {"-r", "--file-remove"}, description = "if did file exists, delete it and re-generate")
    public boolean existFileRemove = false;

    @Option(names = {"-id", "--did-id"}, required = true, description = "DID id")
    public String didId;

    @Option(names = {"-ci", "--did-controller-id"}, required = true, description = "DID controller id")
    public String controller;

    /* proof purpose */
    @Option(names = {"-mi", "--assertionMethod-key-id-list"}, required = true, split = ",", description = "assertionMethod key id list")
    public List<String> assertionMethods;

    @Option(names = {"-ai", "--authentication-key-id-list"}, required = true, split = ",", description = "authentication key id list")
    public List<String> authentications;

    @Option(names = {"-ki", "--keyAgreement-key-id-list"}, required = true, split = ",", description = "keyAgreement key id list")
    public List<String> keyAgreements;

    @Option(names = {"-ii", "--capabilityInvocation-key-id-list"}, split = ",", description = "capabilityInvocation key id list")
    public List<String> invocations;

    @Option(names = {"-di", "--capabilityDelegation-key-id-list"}, split = ",", description = "capabilityDelegation key id list")
    public List<String> delegations;

    public List<String> keys = new ArrayList<>();

    public Map<String, List<ProofPurpose>> purposes = new LinkedHashMap<>();

    @Override
    public Void call() throws Exception {
        System.out.println("== new did create call ==");
        CommandUtils.printdCommadMssage(this);

        // 0. remove file
        if (existFileRemove) {
            File file = didFile;
            if (file.exists()) {
                System.out.println("DID file remove : " + didFile);
                file.delete();
            }
        }

        if (didId.isEmpty() || controller.isEmpty()) {
            System.out.println("No did id or controller...");
            System.out.println("[FAIL] DID generate fail...");
            return null;
        }

        // 1. connect
        WalletManagerInterface manager = WalletManagerFactory.getWalletManager(WalletManagerFactory.WalletManagerType.FILE);
        manager.connect(walletManager, password);

        // 2. create did
        if (manager.isConnect()) {
            DidManager didManager = new DidManager();

            // 2-1. data init
            keys = new ArrayList<>();
            purposes = new LinkedHashMap<>();

            processList(assertionMethods);
            processList(authentications);
            processList(keyAgreements);
            processList(invocations);
            processList(delegations);

            // 2-2. exist key in wallet?
            List<String> keyIds = new ArrayList<String>();
            List<DidKeyInfo> didKeyInfos = new ArrayList<>();

            List<String> walletKeyIds = manager.getKeyIdList();
            if (!compareLists(walletKeyIds, keys)) {
                System.out.println("No keyId in wallet...");
                System.out.println("[FAIL] DID generate fail...");
                return null;
            }

            for (String keyid : purposes.keySet()) {
                List<ProofPurpose> purposeList = purposes.get(keyid);

                KeyElement keyElement = manager.getKeyElement(keyid);
                DidKeyInfo didKeyInfo = new DidKeyInfo();
                didKeyInfo.setKeyId(keyid);
                didKeyInfo.setAlgoType(OmniUtils.setDidKeyType(keyElement.getAlgorithm()));
                didKeyInfo.setPublicKey(manager.getPublicKey(keyid));
                didKeyInfo.setKeyPurpose(purposeList);
                didKeyInfo.setController(controller);
                // server Auth type is FREE
                didKeyInfo.setAuthType(AuthType.Free);
                didKeyInfos.add(didKeyInfo);

                for (ProofPurpose purpose : purposeList) {
                    // set keyId
                    if (ProofPurpose.KEY_AGREEMENT.compareTo(purpose) != 0) {
                        keyIds.add(keyid);
                        break;
                    }
                }
            }

            didManager.createDocument(didId, controller, didKeyInfos);

            // 4. req signature param
            List<SignatureParams> signatureParams = didManager.getOriginDataForSign(keyIds);

            // 5. add proof
            for (SignatureParams params : signatureParams) {
                String keyId = params.getKeyId();
                byte[] hashedSource = DigestUtils.getDigest(params.getOriginData().getBytes(StandardCharsets.UTF_8), DigestType.SHA256);
                byte[] signatureBytes = manager.generateCompactSignatureFromHash(keyId, hashedSource);
                params.setSignatureValue(MultiBaseUtils.encode(signatureBytes, MultiBaseType.base58btc));
            }
            didManager.addProof(signatureParams);
            String dids = didManager.getDocument().toJson();
            CommandUtils.writeFile(didFile, dids);

            System.out.println("dids :\n" + CommandUtils.toPrettyFormat(dids));
            System.out.println("[SUCCESS] DID generate success...");
        } else {
            System.out.println("[FAIL] DID generate fail...");
        }

        return null;
    }

    private boolean compareLists(List<String> a, List<String> b) {
        return b.stream().anyMatch(a::contains);
    }

    private void processList(List<String> list) {
        if (list != null) {
            list = list.stream().map(String::trim).collect(Collectors.toList());
            if (list.size() == 1 && list.get(0).trim().isEmpty()) {
                list = null;
            } else {
                for (String key : list) {
                    List<ProofPurpose> temp = purposes.getOrDefault(key, new ArrayList<>());
                    temp.add(getProofPurpose(list));
                    purposes.put(key, temp);
                    keys.addAll(list);
                }
            }
        }
    }

    private ProofPurpose getProofPurpose(List<String> list) {
        if (list.equals(assertionMethods)) {
            return ProofPurpose.ASSERTION_METHOD;
        } else if (list.equals(authentications)) {
            return ProofPurpose.AUTHENTICATION;
        } else if (list.equals(keyAgreements)) {
            return ProofPurpose.KEY_AGREEMENT;
        } else if (list.equals(invocations)) {
            return ProofPurpose.CAPABILITY_INVOCATION;
        } else if (list.equals(delegations)) {
            return ProofPurpose.CAPABILITY_DELEGATION;
        }
        return null;
    }

}