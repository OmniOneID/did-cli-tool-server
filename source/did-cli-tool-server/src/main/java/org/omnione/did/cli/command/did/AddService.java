/* 
 * Copyright 2024 Raonsecure
 */

package org.omnione.did.cli.command.did;

import org.omnione.did.cli.OmniCLI;
import org.omnione.did.cli.command.util.CommandUtils;

import org.omnione.did.core.data.rest.SignatureParams;
import org.omnione.did.core.manager.DidManager;
import org.omnione.did.crypto.enums.DigestType;
import org.omnione.did.crypto.enums.MultiBaseType;
import org.omnione.did.crypto.util.DigestUtils;
import org.omnione.did.crypto.util.MultiBaseUtils;
import org.omnione.did.data.model.enums.did.*;
import org.omnione.did.wallet.key.WalletManagerFactory;
import org.omnione.did.wallet.key.WalletManagerInterface;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

@Command(name = "addService", mixinStandardHelpOptions = true, version = OmniCLI.VERSION, description = "DID Document add service")
public class AddService implements Callable<Void> {

    @Option(names = {"-m", "--wallet-manager"}, required = true, description =  "Wallet file path")
    public String walletManager;

    @Option(names = {"-p", "--wallet-manager-password"}, required = true, interactive = true, description = "Wallet password")
    public char[] password;

    @Option(names = {"-f", "--did-file"}, required = true, description = "DID Document save file name")
    public File didFile;

    /* service end point */
    @Option(names = {"-si", "--did-service-id"}, required = true, description = "DID service id")
    public String serviceId;

    @Option(names = {"-st", "--did-service-type"}, required = true, description = "DID service type: LinkedDomains or CredentialRegistry")
    public String serviceType;

    @Option(names = {"-sul", "--did-service-url-list"}, required = true, split = ",", description = "DID service url list")
    public List<String> serviceUrls;

    @Override
    public Void call() throws Exception {
        System.out.println("== did add service call ==");
        CommandUtils.printedCommandMessage(this);

        // 1. connect
        WalletManagerInterface manager = WalletManagerFactory.getWalletManager(WalletManagerFactory.WalletManagerType.FILE);
        manager.connect(walletManager, password);

        // 2. load did
        if (manager.isConnect()) {
            File file = didFile;

            if(!file.exists()) {
                System.out.println("Not exists did file...");
                System.out.println("[FAIL] DID add service fail...");
                return null;
            }

            if (serviceId.isEmpty() || serviceType.isEmpty() || serviceUrls.isEmpty()) {
                System.out.println("No service id or serviceType...");
                System.out.println("[FAIL] DID add service fail...");
                return null;
            }

            DidManager didManager = new DidManager();
            didManager.load(didFile.getPath());

            // 2-1. data init
            initData();

            // 3. add service end point
            for (String url : serviceUrls) {
                if(url != null) {
                    didManager.addServiceEndPoint(serviceId, DidServiceType.fromString(serviceType), url.trim());
                }
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
            System.out.println("[SUCCESS] DID add service success...");
        } else {
            System.out.println("[FAIL] DID add service fail...");
        }
        return null;
    }

    // remove gap
    private void initData() {
        if (serviceUrls != null) {
            serviceUrls = serviceUrls.stream().map(String::trim).collect(Collectors.toList());
            if (serviceUrls.size() == 1 && serviceUrls.get(0).trim() == "") {
                serviceUrls = null;
                System.out.println("No service url....");
                System.out.println("[FAIL] DID add service fail...");
                System.exit(1);
            }
        }
    }

}