/* 
 * Copyright 2024 Raonsecure
 */

package org.omnione.did.cli.command.did;

import org.omnione.did.cli.OmniCLI;
import org.omnione.did.cli.command.util.CommandUtils;
import org.omnione.did.core.manager.DidManager;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.File;
import java.util.concurrent.Callable;

@Command(name = "version", mixinStandardHelpOptions = true, version = OmniCLI.VERSION, description = "DID Document get version id")
public class Version implements Callable<Void> {

    @Option(names = {"-f", "--did-file"}, required = true, description = "DID Document save file name")
    public File didFile;

    @Override
    public Void call() throws Exception {
        System.out.println("== did get version id call ==");
        CommandUtils.printedCommandMessage(this);

        // 1. exists did?
        File file = didFile;
        if (!file.exists()) {
            System.out.println("Not exists did file...");
            System.out.println("[FAIL] DID get version id fail...");
        } else {
            DidManager didManager = new DidManager();
            didManager.load(didFile.getPath());

            System.out.println("DID file: " + didFile.getName());
            System.out.println("DID versionId: " + didManager.getDocument().getVersionId());
            System.out.println("[SUCCESS] DIDs get version id success...");
        }
        return null;
    }

}