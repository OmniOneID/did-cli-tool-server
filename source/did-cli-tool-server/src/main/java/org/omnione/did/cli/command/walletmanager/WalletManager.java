package org.omnione.did.cli.command.walletmanager;

import org.omnione.did.cli.OmniCLI;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(name = "walletManager", mixinStandardHelpOptions = true, version = OmniCLI.VERSION, description = "WalletManager Command",
        subcommands = {
                CreateWallet.class,
                AddKey.class,
                KeyList.class,
                RemoveKey.class
        })
public class WalletManager implements Callable<Void> {

    @Override
    public Void call() throws Exception {
        return null;
    }

}