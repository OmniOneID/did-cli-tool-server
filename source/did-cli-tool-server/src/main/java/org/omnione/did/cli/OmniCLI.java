/* 
 * Copyright 2024 Raonsecure
 */

package org.omnione.did.cli;

import org.omnione.did.cli.command.Omni;
import picocli.CommandLine;

import java.io.File;

public class OmniCLI {

    public static final String VERSION = "OmniOne OpenDID CLI Version 1.0.0";
    public static final String DEFAULT_PROPERTY = "cli.properties";

    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            String[] temp = {"-h"};
            args = temp;
        }

        CommandLine commandLine = new CommandLine(new Omni());
        File file = new File(DEFAULT_PROPERTY);

        if (file.exists()) {
            commandLine.setDefaultValueProvider(new CommandLine.PropertiesDefaultProvider(file));
        } else {
            System.out.println("cli.properties is not exist.");
        }
        commandLine.execute(args);
    }

}