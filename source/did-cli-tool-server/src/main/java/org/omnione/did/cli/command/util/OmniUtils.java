/* 
 * Copyright 2024 Raonsecure
 */

package org.omnione.did.cli.command.util;

import org.omnione.did.data.model.enums.did.DidKeyType;

public class OmniUtils {

    public static String setDidKeyType(String algo) {
        switch (algo) {
            case "Rsa2048":
                return DidKeyType.RSA_VERIFICATION_KEY_2018.getRawValue();
            case "Secp256r1":
                return DidKeyType.SECP256R1_VERIFICATION_KEY_2018.getRawValue();
            case "Secp256k1":
                return DidKeyType.SECP256K1_VERIFICATION_KEY_2018.getRawValue();
            default:
                return null;
        }
    }

}