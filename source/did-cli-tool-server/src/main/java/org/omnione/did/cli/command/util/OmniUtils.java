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