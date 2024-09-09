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
import org.omnione.did.wallet.key.WalletManagerFactory;
import org.omnione.did.wallet.key.WalletManagerInterface;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.Callable;

@Command(name = "updateVersion", mixinStandardHelpOptions = true, version = OmniCLI.VERSION, description = "DID Document update version id")
public class UpdateVersion implements Callable<Void> {

    @Option(names = {"-m", "--wallet-manager"}, required = true, description =  "Wallet file path")
    public String walletManager;

    @Option(names = {"-p", "--wallet-manager-password"}, required = true, interactive = true, description = "Wallet password")
    public char[] password;

    @Option(names = {"-f", "--did-file"}, required = true, description = "DID Document save file name")
    public File didFile;

    @Option(names = {"-vi", "--did-version-id"}, required = true, description = "DID version id")
    public String versionId;

    @Override
    public Void call() throws Exception {
        System.out.println("== did update version id call ==");
        CommandUtils.printedCommandMessage(this);

        // 1. connect
        WalletManagerInterface manager = WalletManagerFactory.getWalletManager(WalletManagerFactory.WalletManagerType.FILE);
        manager.connect(walletManager, password);

        // 2. load did
        if (manager.isConnect()) {
            // 3. exists did?
            File file = didFile;
            if (!file.exists()) {
                System.out.println("Not exists did file...");
                System.out.println("[FAIL] DID update version id fail...");

            } else {
                DidManager didManager = new DidManager();
                didManager.load(didFile.getPath());

                if (versionId != null) {
                    didManager.getDocument().setVersionId(versionId.trim());
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
                System.out.println("[SUCCESS] DID update version id success...");
            }
        } else {
            System.out.println("[FAIL] DID update version id fail...");
        }

        return null;
    }

}