/* 
 * Copyright 2024 Raonsecure
 */

package org.omnione.did.cli.command;

import org.omnione.did.cli.OmniCLI;
import org.omnione.did.cli.command.did.Did;
import org.omnione.did.cli.command.generator.Auto;
import org.omnione.did.cli.command.walletmanager.WalletManager;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(name = "omni-cli", mixinStandardHelpOptions = true, version = OmniCLI.VERSION,
        subcommands = {
                WalletManager.class,
                Did.class,
                Auto.class
        })
public class Omni implements Callable<Void> {

    @Option(names = {"-d", "--debug"}, required = false, description = "debug log option")
    public static boolean DEBUG = false;

    @Override
    public Void call() throws Exception {
        return null;
    }

}
