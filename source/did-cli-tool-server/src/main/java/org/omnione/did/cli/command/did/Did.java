/* 
 * Copyright 2024 Raonsecure
 */

package org.omnione.did.cli.command.did;

import org.omnione.did.cli.OmniCLI;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(name = "did", mixinStandardHelpOptions = true, version = OmniCLI.VERSION, description = "DID Command",
        subcommands = {
                CreateDid.class,
                AddService.class,
                Version.class,
                UpdateVersion.class,
                AddKey.class,
                RemoveKey.class,
                RemoveService.class
        })
public class Did implements Callable<Void> {

    @Override
    public Void call() throws Exception {
        return null;
    }

}