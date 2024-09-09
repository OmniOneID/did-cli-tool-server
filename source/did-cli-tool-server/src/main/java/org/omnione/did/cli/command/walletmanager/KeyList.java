package org.omnione.did.cli.command.walletmanager;

import org.omnione.did.cli.OmniCLI;
import org.omnione.did.cli.command.util.CommandUtils;
import org.omnione.did.wallet.key.WalletManagerFactory;
import org.omnione.did.wallet.key.WalletManagerInterface;
import org.omnione.did.wallet.key.data.KeyElement;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.List;
import java.util.concurrent.Callable;

@Command(name = "keyList", mixinStandardHelpOptions = true, version = OmniCLI.VERSION, description = "Wallet Key list")
public class KeyList implements Callable<Void> {

    @Option(names = {"-m", "--wallet-manager"}, required = true, description = "Wallet file path")
    public String walletManager;

    @Option(names = {"-p", "--wallet-manager-password"}, required = true, interactive = true, description = "Wallet password")
    public char[] password;

    @Override
    public Void call() throws Exception {
        System.out.println("== get key list call ==");
        CommandUtils.printedCommandMessage(this);

        // 1. connect
        WalletManagerInterface manager = WalletManagerFactory.getWalletManager(WalletManagerFactory.WalletManagerType.FILE);
        manager.connect(walletManager, password);

        // 2. get key
        if (manager.isConnect()) {
            List<String> keyIds = manager.getKeyIdList();
            int size = (keyIds == null) ? 0 : keyIds.size();
            if (size > 0) {
                for (int i = 0; i < keyIds.size(); i++) {
                    KeyElement keyElement = manager.getKeyElement(keyIds.get(i));
                    System.out.println("Index " + i + " - KeyId: " + keyElement.getKeyId()+", Key AlgoType: " + keyElement.getAlgorithm()+", PublicKey: " + keyElement.getPublicKey());
                }
            }
            System.out.println("Key size: " + size);
            System.out.println("[SUCCESS] Wallet Key List...");
        } else {
            System.out.println("[FAIL] Wallet Key List...");
        }
        return null;
    }

}