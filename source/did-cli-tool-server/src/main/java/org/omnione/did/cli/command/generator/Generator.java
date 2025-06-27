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
import org.omnione.did.cli.command.did.AddService;
import org.omnione.did.cli.command.did.CreateDid;
import org.omnione.did.cli.command.walletmanager.AddKey;
import org.omnione.did.cli.command.walletmanager.CreateWallet;
import org.omnione.did.cli.command.util.CommandUtils;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

@Command(name = "generator", mixinStandardHelpOptions = true, version = OmniCLI.VERSION, description = "Generate Wallet, DID Document Command")
public class Generator implements Callable<Void> {

    @Option(names = {"-m", "--wallet-manager"}, required = true, description =  "Wallet file path")
    public String walletManager;

    @Option(names = {"-p", "--wallet-manager-password"}, required = true, interactive = true, description = "Wallet password")
    public char[] password;

    @Option(names = {"-t", "--key-type"}, description = "Wallet key algorithm type: 0(SECP256k1), 1(SECP256r1), 2(RSA)")
    public Integer keyType = 1;

    @Option(names = {"-f", "--did-file"}, required = true, description = "DID Document save file name")
    public File didFile;

    @Option(names = {"-r", "--file-remove"}, description = "if did file exists, delete it and re-generate")
    public boolean existFileRemove = false;

    @Option(names = {"-s", "--step-num"}, hidden = true, description = "Start Step Number Default Step = 1")
    public int stepNum = 1;

    @Option(names = {"-id", "--did-id"}, required = true, description = "DID id")
    public String didId;

    @Option(names = {"-ci", "--did-controller-id"}, required = true, description = "DID controller id")
    public String controller;

    /* service end point */
    @Option(names = {"-si", "--did-service-id"}, description = "DID service id")
    public String serviceId;

    @Option(names = {"-st", "--did-service-type"}, description = "DID service type: LinkedDomains or CredentialRegistry")
    public String serviceType;

    @Option(names = {"-sul", "--did-service-url-list"}, split = ",", description = "DID service url list")
    public List<String> serviceUrls;

    @Override
    public Void call() throws Exception {
        java.lang.System.out.println("== new wallet, did file create call ==");
        CommandUtils.printedCommandMessage(this);

        CreateWallet createWallet = new CreateWallet();
        String[] walletKeyList = {"assert", "auth", "keyagree", "invoke"};

        // 1. create wallet
        if (stepNum <= 1) {
            java.lang.System.out.println("====== STEP 1 =====");
            if (existFileRemove) {
                createWallet.existFileRemove = true;
            }
            createWallet.walletManager = walletManager;
            createWallet.password = password;
            createWallet.call();
            java.lang.System.out.println("====== End 1 =====");
            sleep();
        }

        // 2. wallet add key
        if (stepNum <= 2) {
            java.lang.System.out.println("====== STEP 2 =====");
            AddKey walletKey = new AddKey();
            walletKey.walletManager = walletManager;
            walletKey.password = password;

            for (String walletKeyId : walletKeyList) {
                walletKey.keyId = walletKeyId;
                walletKey.keyType = keyType;
                walletKey.call();
                java.lang.System.out.println("Adding key id " + walletKeyId + " to wallet");
                sleep();
            }
            java.lang.System.out.println("====== End 2 =====");
        }

        // 3. did create
        if (stepNum <= 3) {
            java.lang.System.out.println("====== STEP 3 =====");
            CreateDid createDid = new CreateDid();
            if (existFileRemove) {
                createDid.existFileRemove = true;
            }
            createDid.walletManager = walletManager;
            createDid.password = password;
            createDid.didFile = didFile;
            createDid.didId = didId;
            createDid.controller = controller;
            createDid.assertionMethods = Arrays.asList(walletKeyList[0]);
            createDid.authentications = Arrays.asList(walletKeyList[1]);
            createDid.keyAgreements = Arrays.asList(walletKeyList[2]);
            createDid.invocations = Arrays.asList(walletKeyList[3]);
            createDid.call();
            java.lang.System.out.println("====== End 3 =====");
            sleep();
        }

        if (serviceId == null || serviceId.isEmpty() || serviceType == null || serviceType.isEmpty() || serviceUrls == null || serviceUrls.isEmpty()) {
            java.lang.System.out.println("No Service value entered.");
            return null;
        }

        // 4. did add service
        if (stepNum <= 4) {
            java.lang.System.out.println("====== STEP 4 =====");
            AddService service = new AddService();
            service.walletManager = walletManager;
            service.password = password;
            service.didFile = didFile;
            service.serviceId = serviceId;
            service.serviceType = serviceType;
            service.serviceUrls = serviceUrls;
            service.call();
            java.lang.System.out.println("====== End 4 =====");
            sleep();
        }

        return null;
    }

    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}