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