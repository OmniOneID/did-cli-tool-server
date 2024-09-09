package org.omnione.did.cli.command.walletmanager;

import org.omnione.did.cli.OmniCLI;
import org.omnione.did.cli.command.util.CommandUtils;
import org.omnione.did.wallet.key.WalletManagerFactory;
import org.omnione.did.wallet.key.WalletManagerInterface;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.File;
import java.util.concurrent.Callable;

@Command(name = "removeKey", mixinStandardHelpOptions = true, version = OmniCLI.VERSION, description = "Wallet file remove key")
public class RemoveKey implements Callable<Void> {

    @Option(names = {"-m", "--wallet-manager"}, required = true, description = "Wallet file path")
    public String walletManager;

    @Option(names = {"-p", "--wallet-manager-password"}, required = true, interactive = true, description = "Wallet password")
    public char[] password;

    @Option(names = {"-i", "--key-id"}, required = true, description = "Wallet Key Id")
    public String keyId;

    @Override
    public Void call() throws Exception {
        System.out.println("== walletManager remove key call ==");
        CommandUtils.printedCommandMessage(this);

        File file = new File(walletManager);
        if (!file.exists()) {
            System.out.println("Not exists walletManager file...");
            System.out.println("[FAIL] walletManager remove key fail...");
            return null;
        }

        // 1. connect
        WalletManagerInterface manager = WalletManagerFactory.getWalletManager(WalletManagerFactory.WalletManagerType.FILE);
        manager.connect(walletManager, password);

        // 2. walletManager remove key
        if (manager.isConnect()) {
            if (keyId.isEmpty()) {
                System.out.println("No keyId...");
                System.out.println("[FAIL] walletManager remove key fail...");
                return null;
            }
            keyId = keyId.trim();

            if(!manager.isExistKey(keyId)) {
                System.out.println("Not exists keyId in wallet...");
                System.out.println("[FAIL] walletManager remove key fail...");
                return null;
            }
            // remove key
            manager.removeKey(keyId);
            System.out.println("[SUCCESS] walletManager remove key success...");
        } else {
            System.out.println("[FAIL] walletManager remove key fail...");
        }
        return null;
    }

}