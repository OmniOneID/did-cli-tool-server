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
import org.omnione.did.data.model.did.VerificationMethod;
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

@Command(name = "addKey", mixinStandardHelpOptions = true, version = OmniCLI.VERSION, description = "DID Document add key")
public class AddKey implements Callable<Void> {

    @Option(names = {"-m", "--wallet-manager"}, required = true, description =  "Wallet file path")
    public String walletManager;

    @Option(names = {"-p", "--wallet-manager-password"}, required = true, interactive = true, description = "Wallet password")
    public char[] password;

    @Option(names = {"-f", "--did-file"}, required = true, description = "DID Document save file name")
    public File didFile;

    @Option(names = {"-i", "--key-id"}, required = true, description = "DID Key id")
    public String keyId;

    @Option(names = {"-dt", "--did-key-type"}, required = true, description = "DID Key type: assert(assertionMethod), auth(authentication), keyagree(keyAgreement), invoke(capabilityInvocation), delegate(capabilityDelegation)")
    public String keyType;

    public DidManager didManager;

    @Override
    public Void call() throws Exception {
        System.out.println("== did add key call ==");
        CommandUtils.printedCommandMessage(this);

        // 1. connect
        WalletManagerInterface manager = WalletManagerFactory.getWalletManager(WalletManagerFactory.WalletManagerType.FILE);
        manager.connect(walletManager, password);

        // 2. did add key
        if (manager.isConnect()) {
            File file = didFile;

            if (!file.exists()) {
                System.out.println("Not exists did file....");
                System.out.println("[FAIL] DID add key fail...");
                return null;
            }

            if (keyId.isEmpty() || keyType.isEmpty()) {
                System.out.println("No keyId id or keyType....");
                System.out.println("[FAIL] DID add key fail...");
                return null;
            }
            keyId = keyId.trim();
            keyType = keyType.trim();

            didManager = new DidManager();
            didManager.load(didFile.getPath());
            ProofPurpose proofPurpose = setProofPurpose(keyType);

            if (proofPurpose == null) {
                System.out.println("Invalid keyType....");
                System.out.println("[FAIL] DID add key fail...");
                return null;
            }
            List<String> walletKeyIds = manager.getKeyIdList();

            // exist key in wallet?
            List<String> keyIds = new ArrayList<String>();
            keyIds.add(keyId);

            if (walletKeyIds.containsAll(keyIds)) {
                KeyElement keyElement = manager.getKeyElement(keyId);
                DidKeyInfo didKeyInfo = new DidKeyInfo();
                didKeyInfo.setKeyId(keyId);
                didKeyInfo.setAlgoType(OmniUtils.setDidKeyType(keyElement.getAlgorithm()));
                didKeyInfo.setPublicKey(manager.getPublicKey(keyId));
                didKeyInfo.setKeyPurpose(Arrays.asList(proofPurpose));
                didKeyInfo.setController(didManager.getDocument().getController());
                // server Auth type is FREE
                didKeyInfo.setAuthType(AuthType.Free);

                boolean check = true;
                for (VerificationMethod verificationMethod : didManager.getDocument().getVerificationMethod()) {
                    if (verificationMethod.getId().equals(keyId)) {
                        check = false;
                    }
                }

                if (check) {
                    didManager.addVerifiCationMethod(didKeyInfo);
                } else {
                    didManager.addKeyPurpose(keyId, Arrays.asList(proofPurpose));
                }

                // 4. req signature param
                List<SignatureParams> signatureParams = didManager.getOriginDataForSign(didManager.getAllSignKeyIdList());

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
                System.out.println("[SUCCESS] DID add key success...");
            } else {
                System.out.println("No keyId id or keyType....");
            }
        } else {
            System.out.println("[FAIL] DID add key fail...");
        }
        return null;
    }

    private ProofPurpose setProofPurpose(String keyType) {
        switch (keyType.trim()) {
            case "assert":
                return ProofPurpose.ASSERTION_METHOD;
            case "auth":
                return ProofPurpose.AUTHENTICATION;
            case "keyagree":
                return ProofPurpose.KEY_AGREEMENT;
            case "invoke":
                return ProofPurpose.CAPABILITY_INVOCATION;
            case "delegate":
                return ProofPurpose.CAPABILITY_DELEGATION;
            default:
                return null;
        }
    }

}