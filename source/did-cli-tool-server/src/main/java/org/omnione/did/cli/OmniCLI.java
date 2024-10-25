/*
 * Copyright 2024 OmniOne.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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