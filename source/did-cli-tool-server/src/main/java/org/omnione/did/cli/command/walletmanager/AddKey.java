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

package org.omnione.did.cli.command.walletmanager;

import org.omnione.did.cli.OmniCLI;
import org.omnione.did.cli.command.util.CommandUtils;
import org.omnione.did.wallet.key.WalletManagerFactory;
import org.omnione.did.wallet.key.WalletManagerInterface;
import org.omnione.did.wallet.key.data.CryptoKeyPairInfo;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.File;
import java.util.concurrent.Callable;

@Command(name = "addKey", mixinStandardHelpOptions = true, version = OmniCLI.VERSION, description = "Wallet add Key")
public class AddKey implements Callable<Void> {

    @Option(names = {"-m", "--wallet-manager"}, required = true, description =  "Wallet file path")
    public String walletManager;

    @Option(names = {"-p", "--wallet-manager-password"}, required = true, interactive = true, description = "Wallet password")
    public char[] password;

    @Option(names = {"-i", "--key-id"}, required = true, description = "Wallet key Id")
    public String keyId;

    @Option(names = {"-t", "--key-type"}, required = true, description = "Wallet key algorithm type: 0(SECP256k1), 1(SECP256r1), 2(RSA)")
    public Integer keyType = 1;

    @Override
    public Void call() throws Exception {
        System.out.println("== wallet add key call ==");
        CommandUtils.printedCommandMessage(this);

        File file = new File(walletManager);
        if (!file.exists()) {
            System.out.println("Not exists WalletManager file...");
            System.out.println("[FAIL] WalletManager add key fail...");
            return null;
        }

        // 1. connect
        WalletManagerInterface manager = WalletManagerFactory.getWalletManager(WalletManagerFactory.WalletManagerType.FILE);
        manager.connect(walletManager, password);

        if(manager.isExistKey(keyId)) {
            System.out.println("Already exists keyId in wallet...");
            System.out.println("[FAIL] WalletManager add key fail...");
            return null;
        }

        // 2. add key
        if (manager.isConnect()) {
            // 2-1. case k1
            if (getKeyAlgo(keyType) == CryptoKeyPairInfo.KeyAlgorithmType.SECP256k1) {
                System.out.println("KeyId:"+ keyId+", KeyType: SECP256k1...");
                manager.generateRandomKey(keyId, CryptoKeyPairInfo.KeyAlgorithmType.SECP256k1);

                // 2-2. case r1
            } else if (getKeyAlgo(keyType) == CryptoKeyPairInfo.KeyAlgorithmType.SECP256r1) {
                System.out.println("KeyId:"+ keyId+", KeyType: SECP256r1...");
                manager.generateRandomKey(keyId, CryptoKeyPairInfo.KeyAlgorithmType.SECP256r1);

                // 2-3. case rsa
            } else if (getKeyAlgo(keyType) == CryptoKeyPairInfo.KeyAlgorithmType.RSA2048) {
                System.out.println("KeyId:"+ keyId+", KeyType: RSA...");
                manager.generateRandomKey(keyId, CryptoKeyPairInfo.KeyAlgorithmType.RSA2048);
            }
            System.out.println("[SUCCESS] WalletManager add key success...");
        } else {
            System.out.println("[FAIL] WalletManager add key fail...");
        }
        return null;
    }

    private CryptoKeyPairInfo.KeyAlgorithmType getKeyAlgo(int keyAlgo) {
        switch(keyAlgo) {
            case 0:
                return CryptoKeyPairInfo.KeyAlgorithmType.SECP256k1;
            case 1:
                return CryptoKeyPairInfo.KeyAlgorithmType.SECP256r1;
            case 2:
                return CryptoKeyPairInfo.KeyAlgorithmType.RSA2048;
            default:
                return null;
        }
    }

}