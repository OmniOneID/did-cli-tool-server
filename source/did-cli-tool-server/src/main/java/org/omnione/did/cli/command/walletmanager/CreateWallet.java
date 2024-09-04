package org.omnione.did.cli.command.walletmanager;

import org.omnione.did.cli.OmniCLI;
import org.omnione.did.cli.command.util.CommandUtils;
import org.omnione.did.wallet.enums.WalletEncryptType;
import org.omnione.did.wallet.key.WalletManagerFactory;
import org.omnione.did.wallet.key.WalletManagerInterface;
import picocli.CommandLine.Command;
import picocli.CommandLine.Help.Visibility;
import picocli.CommandLine.Option;

import java.io.File;
import java.util.concurrent.Callable;

@Command(name = "createWallet", mixinStandardHelpOptions = true, version = OmniCLI.VERSION, description = "Create Wallet")
public class CreateWallet implements Callable<Void> {

    @Option(names = {"-m", "--wallet-manager"}, required = true, description = "Wallet file path")
    public String walletManager;

    @Option(names = {"-p", "--wallet-manager-password"}, required = true, interactive = true, description = "Wallet password")
    public char[] password;

//    @Option(names = {"-t", "--wallet-encrypt-type"}, showDefaultValue = Visibility.ALWAYS, description = "Wallet encrypt type")
//    public WalletEncryptType walletEncryptType = WalletEncryptType.AES_256_CBC_PKCS5Padding;

    @Option(names = {"-r", "--file-remove"}, description = "if wallet file exists, delete it and re-generate")
    public boolean existFileRemove = false;

    @Override
    public Void call() throws Exception {
        System.out.println("== new wallet create call ==");
        CommandUtils.printdCommadMssage(this);

        // 0. remove file
        if (existFileRemove) {
            File file = new File(walletManager);
            if (file.exists()) {
                System.out.println("[SUCCESS] Wallet file remove : " + walletManager);
                file.delete();
            }
        }

        // 1. create wallet
        WalletManagerInterface createManager = WalletManagerFactory.getWalletManager(WalletManagerFactory.WalletManagerType.FILE);
        createManager.create(walletManager, password, WalletEncryptType.AES_256_CBC_PKCS5Padding);

        // 2. connect
        WalletManagerInterface manager = WalletManagerFactory.getWalletManager(WalletManagerFactory.WalletManagerType.FILE);
        manager.connect(walletManager, password);

        if (manager.isConnect()) {
            System.out.println("[SUCCESS] Wallet generate success...");
        } else {
            System.out.println("[FAIL] Wallet generate fail...");
        }
        return null;
    }

}