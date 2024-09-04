package org.omnione.did.cli.command.generator;

import org.omnione.did.cli.OmniCLI;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(name = "auto", mixinStandardHelpOptions = true, version = OmniCLI.VERSION, description = "Auto Command",
        subcommands = {
                Generator.class
        })
public class Auto implements Callable<Void> {

    @Override
    public Void call() throws Exception {
        return null;
    }

}