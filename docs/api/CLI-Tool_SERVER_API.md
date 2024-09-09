---
puppeteer:
    pdf:
        format: A4
        displayHeaderFooter: true
        landscape: false
        scale: 0.8
        margin:
            top: 1.2cm
            right: 1cm
            bottom: 1cm
            left: 1cm
    image:
        quality: 100
        fullPage: false
---

CLI Tool
==

- Subject: CLI Tool Command
- Author: Eunjeong Lee
- Date: 2024-08-29
- Version: v1.0.0

| Version | Date       | Changes      |
| ------- | ---------- | ------------ |
| v1.0.0 | 2024-08-29  | Initial version |

<div style="page-break-after: always;"></div>

# Table of Contents

- [1. Create Wallet](#1-create-wallet)
- [2. Adding Wallet Key](#2-adding-wallet-key)
- [3. Wallet Key Deletion](#3-wallet-key-deletion)
- [4. Wallet Key List Retrieval](#4-wallet-key-list-retrieval)
- [5. DID Document Creation](#5-did-document-creation)
- [6. Adding DID Key](#6-adding-did-key)
- [7. DID Key Removal](#7-did-key-removal)
- [8. Adding DID Service](#8-adding-did-service)
- [9. DID Service Removal](#9-did-service-removal)
- [10. DID Version ID Lookup](#10-did-version-id-lookup)
- [11. Changing DID Version ID](#11-changing-did-version-id)
- [12. Auto-generate Wallet, DID Document](#12-auto-generate-wallet-did-document)

# Function List

## 1. Create Wallet

### Command

`walletManager createWallet`

### Command Description

`Creates a file wallet.`

### Command Options

| Option                    | Type    | Description                | **M/O** | **Notes**                                     |
| ------------------------- | ------- | -------------------------- | ------- | --------------------------------------------- |
| -m, --wallet-manager             | string  | Wallet file name           | M       | Include the file extension.<br/>e.g., omni.wallet |
| -p, --wallet-manager-password | string  | Wallet password            | M       | Must enter a user-defined password when creating the wallet file for the first time. |
| -r, --file-remove         | boolean | Wallet file removal option | O       | Set this option to delete the file if it already exists. |

### Command Declaration

```shell
# Command declaration in Shell 
java -jar [module-name] walletManager createWallet -m [wallet-name] -p [password]
```

### Command Usage

```shell
# create omni.wallet in Shell
java -jar did-cli-tool-server-1.0.0.jar walletManager createWallet -m omni.wallet -p
```

### CLI Properties Usage

* CLI option values can be set either through command line input or properties files. They can also be used in combination.

* If the same option value exists, the command line input takes precedence.
  
  ```shell
  # command line
  java -jar did-cli-tool-server-1.0.0.jar walletManager createWallet -p
  ```

  ```shell
  # cli.properties
  wallet-manager=omni.wallet
  ```

### Command Result Log

```shell
Enter value for --wallet-manager-password (Wallet password): (Input password)
== new wallet create call ==
Command:	walletmanager->CreateWallet
[SUCCESS] Wallet generate success...
```

<br>

## 2. Adding Wallet Key

### Command

`walletManager addKey`

### Command Description

`Adds a key to the file wallet.`

### Command Options

| Option                    | Type   | Description              | **M/O** | **Notes**                                      |
| ------------------------- | ------ | ------------------------ | ------- | ---------------------------------------------- |
| -m, --wallet-manager      | string | Wallet file name         | M       | Enter with extension.<br/>e.g., omni.wallet  |
| -p, --wallet-manager-password | string | Wallet password          | M       | You must enter the user-defined password when creating the file wallet initially. |
| -i, --key-id              | string | Key ID                   | M       |                                                |
| -t, --key-type            | int    | Key algorithm type       | M       | 0 : SECP256k1<br/>1 : SECP256r1               |

### Command Declaration

```shell
# Command declaration in Shell
java -jar [moduleName] walletManager addKey -m [walletName] -i [keyId] -t [keyType] -p [password]
```

### Command Usage

```shell
# Add secp256r1 type assert key to omni.wallet in Shell
java -jar did-cli-tool-server-1.0.0.jar walletManager addKey -m omni.wallet -i assert -t 1 -p

# Add secp256r1 type auth key to omni.wallet in Shell
java -jar did-cli-tool-server-1.0.0.jar walletManager addKey -m omni.wallet -i auth -t 1 -p

# Add secp256r1 type keyagree key to omni.wallet in Shell
java -jar did-cli-tool-server-1.0.0.jar walletManager addKey -m omni.wallet -i keyagree -t 1 -p

# Add secp256r1 type invoke key to omni.wallet in Shell
java -jar did-cli-tool-server-1.0.0.jar walletManager addKey -m omni.wallet -i invoke -t 1 -p

# Add secp256r1 type invoke2 key to omni.wallet in Shell
java -jar did-cli-tool-server-1.0.0.jar walletManager addKey -m omni.wallet -i invoke2 -t 1 -p
```
### CLI Properties Usage

* CLI option values can be set via command-line input or properties files. They can also be used together.

* If the same option value exists, command-line input takes precedence.

  ```shell
  # command line
  java -jar did-cli-tool-server-1.0.0.jar walletManager addKey -p
  ```

  ```shell
  # cli.properties
  wallet-manager=omni.wallet
  key-id=assert
  key-type=1
  ```

### Command Result Log

```shell
Enter value for --wallet-manager-password (Wallet password): (Input password)
== wallet add key call ==
Command:	walletmanager->AddKey
KeyId:invoke2, KeyType: SECP256r1...
[SUCCESS] WalletManager add key success...
```

### Data Sample

```json
{
  "head": {
    "encryptionInfo": {
      "aesAlgorithm": "AES",
      "padding": "PKCS5Padding",
      "mode": "CBC",
      "keySize": 32
    },
    "secureKeyInfo": {
      "salt": "z4vxTV7JRYU6qPk9hA1yCrMSSfD16fAoSFr2kXggbvKzJ",
      "iterations": 2048,
      "secretPhrase": "zW5Vg94mVUL9oGR3bUM5HNS"
    },
    "version": 0,
    "encoding": {
      "keyEncodingType": "z"
    }
  },
  "keys": [
    {
      "keyId": "assert",
      "algorithm": "Secp256r1",
      "publicKey": "ztNvWQaAfcq5EX3UfS6EDqUgVGFr4JxuuX5UaEYxFBpEL",
      "privateKey": "z6q9SBmcztcVj5PjV6CUEGFM9GNFmhB1Eh8UG6JguDJ27u4RaGyUoxQYm5TbD2XQr87D2TfWdzxazfZx7W8XgEUS38eaHUMC7c8u2wChLr8CsSHhbvC6pAVzRQctBAk3RzojKtiUsJ7JuBKEjh4K1rHjx4L9HDWBX6qnwzAqcrWRQS1DLXuGJo1RQMS1nPq5qtYuZxYGMQZLtnZQTELLgtVFrm63"
    },
    {
      "keyId": "auth",
      "algorithm": "Secp256r1",
      "publicKey": "zdLAQjay65BtNv8RZprum2mdNFruna2iR5PBsRjncwvVn",
      "privateKey": "z6q9SBmcztcVj5PjV6CUEGFM9GNFmhB1Eh8UG6JguDJ27kFHdZSuFNCYfzbswLYnW86hcPmD96wfZiwzGBSNyLTfnrC9HL5Ey2JMVY5zJgpc5RUMMgke8haLpohM3qGAXEy7qPcoX79hU5tHFncoxrPQ6dBRQAdRGsknLqFvF9TarsGLL4Y2mWv6gB6XNSW7cefixrq2aeoCXHhP7GVNayRqiDpS"
    },
    {
      "keyId": "keyagree",
      "algorithm": "Secp256r1",
      "publicKey": "zfpwJri9rNgkDAHvmemZ6WMMBK8iFp1tQqxtuoZHgrgNu",
      "privateKey": "z6q9SBmcztcVj5PjV6CUEGFM9GNFmhB1Eh8UG6JguDJ27ndmVbiFnYDD2o9VZzFGDHmzz9ZZV9tF6NhgSKXG69rdawRQUmbyqo173XTW8CtrhUHfxUfUroGegdGQ7ZewLpt3UY52NQyAxMum5VBnBLvBr4i8ot39Vh8uHSAG4uyibaGLdb5Sen8bo1H1m2xcTdGx9jrZBWeT1UjQe21Up5G2AmEa"
    },
    {
      "keyId": "invoke",
      "algorithm": "Secp256r1",
      "publicKey": "zndGa2B5YaB8s7u9F25VcBHXyUtqP6BiZ1wevPGPhZbX8",
      "privateKey": "z6q9SBmcztcVj5PjV6CUEGFM9GNFmhB1Eh8UG6JguDJ27qs7f88y7Lw58CNjqj3BZzdLwracsqQHa7csbXm73JtcXtaCRw4sxmRJc6CEVaBwB9fixF2cvanJNhzReqRwyJwHnaJKhW6GSM9bd94fUZ9kFEbE43tmkjhsiDN7bG4PSNCqyusNZFkFyrxcx3uLU49zKU8D9Jq97w16UztsicJCxGg7"
    },
    {
      "keyId": "invoke2",
      "algorithm": "Secp256r1",
      "publicKey": "zcQAnYk35fycCt7uJ6PFPoKR4V2gTkqAfrpBoihoA2oPJ",
      "privateKey": "z6q9SBmcztcVj5PjV6CUEGFM9GNFmhB1Eh8UG6JguDJ27zNJ1Zh6Xg3XXBvw9ZQs4AUrmhXiDv1FeCNbghq1sJBehD3QLeDBREQJcmM9gsEYwcNd7SYbFQ7n7TNB11kZf8e19V5nXLtxHYfzKY5DCszcNuCrFkhkcJNWtbbqEpA1poyDD91x7wxJQiqSKxZu49z9BYvNk24mUkneqZsb4BGB2tZZ"
    }
  ]
}
```

<br>

## 3. Wallet Key Deletion

### Command

`walletManager removeKey`

### Command Description

`Deletes a key from the file wallet.`

### Command Options

| Option                    | Type   | Description             | **M/O** | **Notes**                                |
| ------------------------- | ------ | ----------------------- | ------- | ---------------------------------------- |
| -m, --wallet-manager     | string | Wallet file name        | M       | Include the file extension.<br/>e.g., omni.wallet  |
| -p, --wallet-manager-password| string | Wallet password         | M       | The password must be set when the wallet file is first created. |
| -i, --key-id              | string | Key ID                  | M       |                                          |

### Command Declaration

```shell
# Command declaration in Shell 
java -jar [module_name] walletManager removeKey -m [wallet_name] -i [key_id] -p [password]
```

### Command Usage

```shell
java -jar did-cli-tool-server-1.0.0.jar walletManager removeKey -m omni.wallet -i invoke2 -p
```

### CLI Properties Usage

* CLI option values can be set via command-line input or properties files. They can also be used together.

* If the same option value exists, the command-line input takes precedence.
  
  ```shell
  # command line
  java -jar did-cli-tool-server-1.0.0.jar walletManager removeKey -m omni.wallet -p
  ```

  ```shell
  # cli.properties
  key-id=invoke2
  ```

### Command Result Log

```shell
Enter value for --wallet-manager-password (Wallet password): (Input password)
== walletManager remove key call ==
Command:	walletmanager->RemoveKey
[SUCCESS] walletManager remove key success...
```

### Data Sample

```json
{
  "head": {
    "encryptionInfo": {
      "aesAlgorithm": "AES",
      "padding": "PKCS5Padding",
      "mode": "CBC",
      "keySize": 32
    },
    "secureKeyInfo": {
      "salt": "z4vxTV7JRYU6qPk9hA1yCrMSSfD16fAoSFr2kXggbvKzJ",
      "iterations": 2048,
      "secretPhrase": "zW5Vg94mVUL9oGR3bUM5HNS"
    },
    "version": 0,
    "encoding": {
      "keyEncodingType": "z"
    }
  },
  "keys": [
    {
      "keyId": "assert",
      "algorithm": "Secp256r1",
      "publicKey": "ztNvWQaAfcq5EX3UfS6EDqUgVGFr4JxuuX5UaEYxFBpEL",
      "privateKey": "z6q9SBmcztcVj5PjV6CUEGFM9GNFmhB1Eh8UG6JguDJ27u4RaGyUoxQYm5TbD2XQr87D2TfWdzxazfZx7W8XgEUS38eaHUMC7c8u2wChLr8CsSHhbvC6pAVzRQctBAk3RzojKtiUsJ7JuBKEjh4K1rHjx4L9HDWBX6qnwzAqcrWRQS1DLXuGJo1RQMS1nPq5qtYuZxYGMQZLtnZQTELLgtVFrm63"
    },
    {
      "keyId": "auth",
      "algorithm": "Secp256r1",
      "publicKey": "zdLAQjay65BtNv8RZprum2mdNFruna2iR5PBsRjncwvVn",
      "privateKey": "z6q9SBmcztcVj5PjV6CUEGFM9GNFmhB1Eh8UG6JguDJ27kFHdZSuFNCYfzbswLYnW86hcPmD96wfZiwzGBSNyLTfnrC9HL5Ey2JMVY5zJgpc5RUMMgke8haLpohM3qGAXEy7qPcoX79hU5tHFncoxrPQ6dBRQAdRGsknLqFvF9TarsGLL4Y2mWv6gB6XNSW7cefixrq2aeoCXHhP7GVNayRqiDpS"
    },
    {
      "keyId": "keyagree",
      "algorithm": "Secp256r1",
      "publicKey": "zfpwJri9rNgkDAHvmemZ6WMMBK8iFp1tQqxtuoZHgrgNu",
      "privateKey": "z6q9SBmcztcVj5PjV6CUEGFM9GNFmhB1Eh8UG6JguDJ27ndmVbiFnYDD2o9VZzFGDHmzz9ZZV9tF6NhgSKXG69rdawRQUmbyqo173XTW8CtrhUHfxUfUroGegdGQ7ZewLpt3UY52NQyAxMum5VBnBLvBr4i8ot39Vh8uHSAG4uyibaGLdb5Sen8bo1H1m2xcTdGx9jrZBWeT1UjQe21Up5G2AmEa"
    },
    {
      "keyId": "invoke",
      "algorithm": "Secp256r1",
      "publicKey": "zndGa2B5YaB8s7u9F25VcBHXyUtqP6BiZ1wevPGPhZbX8",
      "privateKey": "z6q9SBmcztcVj5PjV6CUEGFM9GNFmhB1Eh8UG6JguDJ27qs7f88y7Lw58CNjqj3BZzdLwracsqQHa7csbXm73JtcXtaCRw4sxmRJc6CEVaBwB9fixF2cvanJNhzReqRwyJwHnaJKhW6GSM9bd94fUZ9kFEbE43tmkjhsiDN7bG4PSNCqyusNZFkFyrxcx3uLU49zKU8D9Jq97w16UztsicJCxGg7"
    }
  ]
}
```

<br>

## 4. Wallet Key List Retrieval

### Command

`walletManager keyList`

### Command Description

`Retrieves the list of keys from the wallet file.`

### Command Options

| Option                    | Type   | Description         | **M/O** | **Notes**                                   |
| ------------------------- | ------ | ------------------- | ------- | ------------------------------------------- |
| -m, --wallet-manager          | string | Wallet file name    | M       | Enter with the extension.<br/>ex) omni.wallet |
| -p, --wallet-manager-password | string | Wallet password     | M       | Must enter the password set during the initial wallet creation. |

### Command Declaration

```shell
# Command declaration in Shell
java -jar [module name] walletManager keyList -m [wallet name] -p [password]
```

### Command Usage

```shell
java -jar did-cli-tool-server-1.0.0.jar walletManager keyList -m omni.wallet -p
```

### CLI Properties Usage

* CLI option values can be set through command-line input or properties files. They can also be used in combination.

* If the same option value exists, command-line input takes precedence.

  ```shell
  # command line
  java -jar did-cli-tool-server-1.0.0.jar walletManager keyList -p
  ```

  ```shell
  # cli.properties
  wallet-manager=omni.wallet
  ```

### Command Result Log

```shell
Enter value for --wallet-manager-password (Wallet password): (input password)
== get key list call ==
Command:	walletmanager->KeyList
Index 0 - KeyId: assert, Key AlgoType: Secp256r1, PublicKey: zqkfP3JkoAJQMH9fyj6ZrA5J8Ep3s9919DC6NjXskNfJD
Index 1 - KeyId: auth, Key AlgoType: Secp256r1, PublicKey: z28Yz7qDxDqpPQjunHpYDKi55XSbvmmTDN3vfHAgJS6Vyf
Index 2 - KeyId: keyagree, Key AlgoType: Secp256r1, PublicKey: zdVFxKZZ5745VS3tryUfDN4HLDg7TV7iSEC5wGcsd9Rhe
Index 3 - KeyId: invoke, Key AlgoType: Secp256r1, PublicKey: zxcatnwnKcoBoL47iuFWciQqxAjgPK5FHxEmReZJXzSoN
Index 4 - KeyId: invoke2, Key AlgoType: Secp256r1, PublicKey: ztHsJfEgMgsr7oBhdzRhhuwtEdU4PFNk7HcDsPYyc8xFH
Key size: 5
[SUCCESS] Wallet Key List...
```

<br>

## 5. DID Document Creation

### Command

`did createDid`

### Command Description

`Creates a DID document.`

### Command Options

| Option                                  | Type    | Description              | **M/O** | **Note**                                                                           |
| --------------------------------------- | ------- | ------------------------ | ------- | -------------------------------------------------------------------------------- |
| -m, --wallet-manager    | string  | Wallet file name         | M       | Include the file extension.<br/>e.g., omni.wallet      |
| -p, --wallet-manager-password  | string  | Wallet password          | M       | A user-defined password must be entered when creating the wallet file for the first time.  |
| -f, --did-file                          | string  | DID file name            | M       | Include the file extension.<br/>e.g., omni.did                                                  |
| -r, --file-remove                       | boolean | Whether to delete DID file | O       | Sets the option to delete the file if the DID file specified by -f option exists.                                     |
| -id, --did-id                           | string  | DID ID                   | M       | e.g., did:example:abcdefg1234567890                                                |
| -ci, --did-controller-id                | string  | DID Controller           | M       |                                                                                  |
| -mi, --assertionMethod-key-id-list      | string  | DID Signature Key List   | M       | Separate with commas.<br/>e.g., assert, pin                                         |
| -ai, --authentication-key-id-list       | string  | DID Authentication Key List | M       | Separate with commas.<br/>e.g., auth, auth2                                         |
| -ki, --keyAgreement-key-id-list         | string  | DID Key Agreement Key List | M       | Separate with commas.<br/>e.g., keyagree, keyagree2                                 |
| -ii, --capabilityInvocation-key-id-list | string  | DID Capability Invocation Key List | O       | Optionally entered depending on the type of DID.<br/>Separate with commas.<br/>e.g., invoke, invoke2         |
| -di, --capabilityDelegation-key-id-list | string  | DID Capability Delegation Key List | O       | Optionally entered depending on the type of DID.<br/>Separate with commas.<br/>e.g., delegation, delegation2 |

### Command Declaration

```shell
# Command declaration in Shell
java -jar [moduleName] did createDid -m [walletName] -f [didName] -id [didId] -ci [controller] -mi [signatureKeyList] -ai [authenticationKeyList] -ki [keyAgreementKeyList] -ii [capabilityInvocationKeyList] -p
```

### Command Usage

```shell
java -jar did-cli-tool-server-1.0.0.jar did createDid -m omni.wallet -f omni.did -id did:omn:0123456789abcdef -ci controllerDid -mi assert -ai auth -ki keyagree -ii invoke,invoke2 -p
```

### CLI Properties Usage

* CLI options can be set via command-line input or a properties file. They can also be used in combination.

* If the same option value exists, the command-line input takes precedence.
  
  ```shell
  # command line
  java -jar did-cli-tool-server-1.0.0.jar did createDid -m omni.wallet -f omni.did -p
  ```

  ```shell
  # cli.properties
  # DID Id
  did-id=did:omn:0123456789abcdef
  # DID Controller
  did-controller-id=controllerDid
  
  ## Setting the keyId will enable purpose use. comma(,) base.
  # ex) assertionMethod-key-id-list=assert, pin
  # AssertionMethod key id list
  assertionMethod-key-id-list=assert
  # Authentication key id list
  authentication-key-id-list=auth
  # KeyAgreement key id list
  keyAgreement-key-id-list=keyagree
  # CapabilityInvocation key id list
  capabilityInvocation-key-id-list=invoke,invoke2
  # CapabilityDelegation key id list
  capabilityDelegation-key-id-list=
  ```

### Command Result Log

```shell
Enter value for --wallet-manager-password (Wallet password): (Input password)
== new did create call ==
Command:	did->CreateDid
dids : (Refer to Data Sample)
[SUCCESS] DID generate success...
```

### Data Sample

```json
{
  "@context": [
    "https://www.w3.org/ns/did/v1"
  ],
  "assertionMethod": [
    "assert"
  ],
  "authentication": [
    "auth"
  ],
  "capabilityInvocation": [
    "invoke",
    "invoke2"
  ],
  "controller": "controllerDid",
  "created": "2024-07-12T02:38:09Z",
  "deactivated": false,
  "id": "did:omn:0123456789abcdef",
  "keyAgreement": [
    "keyagree"
  ],
  "proofs": [
    {
      "created": "2024-07-12T02:38:09Z",
      "proofPurpose": "assertionMethod",
      "proofValue": "z3mCGjDA7LkgHLow4a1THsB1AgcktKAoLh2XydNbdEVDv11xFJ9WQgnrBUuixvP4GQjU5HSxuxTPaA5UKFycKNXuti",
      "type": "Secp256r1Signature2018",
      "verificationMethod": "did:omn:0123456789abcdef?versionId=1#assert"
    },
    {
      "created": "2024-07-12T02:38:09Z",
      "proofPurpose": "authentication",
      "proofValue": "z3sqeRZgEKJuX34kxbWRzvfoEnSGWWxf839kTsbm7FdMLYDTWMG2YWrWWooJKQZJf7LjkGCN3TZ5k2foKuG4NaYg17",
      "type": "Secp256r1Signature2018",
      "verificationMethod": "did:omn:0123456789abcdef?versionId=1#auth"
    },
    {
      "created": "2024-07-12T02:38:09Z",
      "proofPurpose": "capabilityInvocation",
      "proofValue": "z3uX7iRRHzwHSKH5pb1s5TBQbAMsoaTEceYPBpSC9Cs8rmrjZZ8WRkFhStkFtdJo55mnzhB1chXfMPyj2biXL8sCVU",
      "type": "Secp256r1Signature2018",
      "verificationMethod": "did:omn:0123456789abcdef?versionId=1#invoke"
    },
    {
      "created": "2024-07-12T02:38:09Z",
      "proofPurpose": "capabilityInvocation",
      "proofValue": "z3ueVow636sPwKfDmi2KH11JWUY13WhKsbdeh9cqWWcC2S5hFLHKtqSPgNsoKsEwXFS4y3KLLgv4Fs95vt4KNrSsj9",
      "type": "Secp256r1Signature2018",
      "verificationMethod": "did:omn:0123456789abcdef?versionId=1#invoke2"
    }
  ],
  "updated": "2024-07-12T02:38:09Z",
  "verificationMethod": [
    {
      "authType": 1,
      "controller": "controllerDid",
      "id": "assert",
      "publicKeyMultibase": "ztNvWQaAfcq5EX3UfS6EDqUgVGFr4JxuuX5UaEYxFBpEL",
      "type": "Secp256r1VerificationKey2018"
    },
    {
      "authType": 1,
      "controller": "controllerDid",
      "id": "auth",
      "publicKeyMultibase": "zdLAQjay65BtNv8RZprum2mdNFruna2iR5PBsRjncwvVn",
      "type": "Secp256r1VerificationKey2018"
    },
    {
      "authType": 1,
      "controller": "controllerDid",
      "id": "keyagree",
      "publicKeyMultibase": "zfpwJri9rNgkDAHvmemZ6WMMBK8iFp1tQqxtuoZHgrgNu",
      "type": "Secp256r1VerificationKey2018"
    },
    {
      "authType": 1,
      "controller": "controllerDid",
      "id": "invoke",
      "publicKeyMultibase": "zndGa2B5YaB8s7u9F25VcBHXyUtqP6BiZ1wevPGPhZbX8",
      "type": "Secp256r1VerificationKey2018"
    },
    {
      "authType": 1,
      "controller": "controllerDid",
      "id": "invoke2",
      "publicKeyMultibase": "zcQAnYk35fycCt7uJ6PFPoKR4V2gTkqAfrpBoihoA2oPJ",
      "type": "Secp256r1VerificationKey2018"
    }
  ],
  "versionId": "1"
}
```

<br>

## 6. Adding DID Key

### Command

`did addKey`

### Command Description

`Adds a key to the DID document.`

### Command Options

| Option                    | Type   | Description       | **M/O** | **Note**                                                                                                               |
| ------------------------- | ------ | ----------------- | ------- | ---------------------------------------------------------------------------------------------------------------------- |
| -m, --wallet-manager| string | Wallet file name  | M       | Include the file extension.<br/>e.g., omni.wallet                                                                     |
| -p, --wallet-manager-password | string | Wallet password   | M       | You must enter the user-defined password when the wallet file is first created.                                        |
| -f, --did-file            | string | DID file name     | M       | Include the file extension.<br/>e.g., omni.did                                                                        |
| -i, --key-id              | string | Key ID            | M       |                                                                                                                        |
| -dt, --did-key-type       | string | DID Key Type      | M       | assert : assertionMethod<br/>auth : authentication<br/>keyagree : keyAgreement<br/>invoke : capabilityInvocation<br/>delegate : capabilityDelegation |

### Command Declaration

```shell
# Command declaration in Shell
java -jar [module-name] did addKey -m [wallet-name] -f [did-name] -i [key-id] -dt [key-type] -p
```

### Command Usage

```shell
java -jar did-cli-tool-server-1.0.0.jar did addKey -m omni.wallet -f omni.did -i auth -dt assert -p
```

### CLI Properties Usage

* CLI option values can be set via command-line input or properties files. They can also be used in combination.

* If the same option value exists, command-line input takes precedence.

  ```shell
  # command line
  java -jar did-cli-tool-server-1.0.0.jar did addKey -m omni.wallet -f omni.did -p
  ```

  ```shell
  # cli.properties
  key-id=auth
  did-key-type=assert
  ```

### Command Result Log

```shell
Enter value for --wallet-manager-password (Wallet password): (Input password)
== did add key call ==
Command:    did->AddKey
dids : (Refer to Data Sample)
[SUCCESS] DID add key success...
```

### Data Sample

```json
{
  "@context": [
    "https://www.w3.org/ns/did/v1"
  ],
  "assertionMethod": [
    "assert",
    "auth"
  ],
  "authentication": [
    "auth"
  ],
  "capabilityInvocation": [
    "invoke",
    "invoke2"
  ],
  "controller": "controllerDid",
  "created": "2024-07-12T02:38:09Z",
  "deactivated": false,
  "id": "did:omn:0123456789abcdef",
  "keyAgreement": [
    "keyagree"
  ],
  "proofs": [
    {
      "created": "2024-07-12T02:40:48Z",
      "proofPurpose": "assertionMethod",
      "proofValue": "z3uMUryS5mqGZtV6GNkqXhcss5meFQfjiDbwutedwedH49iwNMipa99QGyxMLZonF4ULsEcpudptruviUtkzSvVjxv",
      "type": "Secp256r1Signature2018",
      "verificationMethod": "did:omn:0123456789abcdef?versionId=1#assert"
    },
    {
      "created": "2024-07-12T02:40:48Z",
      "proofPurpose": "assertionMethod",
      "proofValue": "z3tYF44RTkY8SXxKv4CgqhepgsVFE31tJbFjPJD5Beh2648Sv1fVFQyWiA614TztngGz23xeixsAFDgigWiERD372i",
      "type": "Secp256r1Signature2018",
      "verificationMethod": "did:omn:0123456789abcdef?versionId=1#auth"
    },
    {
      "created": "2024-07-12T02:40:48Z",
      "proofPurpose": "authentication",
      "proofValue": "z3mN1ZAjCWdzDqU2SjjXV2MesNuKv1gMWEBsGsmEoNjZZqiEgNd3bN7w7LSnSunWFHdyJm1LtYun2WnkaZjQHoVXiJ",
      "type": "Secp256r1Signature2018",
      "verificationMethod": "did:omn:0123456789abcdef?versionId=1#auth"
    },
    {
      "created": "2024-07-12T02:40:48Z",
      "proofPurpose": "capabilityInvocation",
      "proofValue": "z3p5QZ5rEVPYSiL78vCRoSx4WovJY2eWpaMNchKG5q6LarZBi18WkqNGeZ4HtdD4B7ChBgrhpDaw2xA1PunLoQoEAD",
      "type": "Secp256r1Signature2018",
      "verificationMethod": "did:omn:0123456789abcdef?versionId=1#invoke"
    },
    {
      "created": "2024-07-12T02:40:48Z",
      "proofPurpose": "capabilityInvocation",
      "proofValue": "z3t3MpDMz17WAS53vkh3G8M9TzhGmPpWjaKxapt7NSdwFZkNYLy5qqStucxHfMd7axBj9CSH4fNustj2Swr3RjkaFR",
      "type": "Secp256r1Signature2018",
      "verificationMethod": "did:omn:0123456789abcdef?versionId=1#invoke2"
    }
  ],
  "updated": "2024-07-12T02:38:09Z",
  "verificationMethod": [
    {
      "authType": 1,
      "controller": "controllerDid",
      "id": "assert",
      "publicKeyMultibase": "ztNvWQaAfcq5EX3UfS6EDqUgVGFr4JxuuX5UaEYxFBpEL",
      "type": "Secp256r1VerificationKey2018"
    },
    {
      "authType": 1,
      "controller": "controllerDid",
      "id": "auth",
      "publicKeyMultibase": "zdLAQjay65BtNv8RZprum2mdNFruna2iR5PBsRjncwvVn",
      "type": "Secp256r1VerificationKey2018"
    },
    {
      "authType": 1,
      "controller": "controllerDid",
      "id": "keyagree",
      "publicKeyMultibase": "zfpwJri9rNgkDAHvmemZ6WMMBK8iFp1tQqxtuoZHgrgNu",
      "type": "Secp256r1VerificationKey2018"
    },
    {
      "authType": 1,
      "controller": "controllerDid",
      "id": "invoke",
      "publicKeyMultibase": "zndGa2B5YaB8s7u9F25VcBHXyUtqP6BiZ1wevPGPhZbX8",
      "type": "Secp256r1VerificationKey2018"
    },
    {
      "authType": 1,
      "controller": "controllerDid",
      "id": "invoke2",
      "publicKeyMultibase": "zcQAnYk35fycCt7uJ6PFPoKR4V2gTkqAfrpBoihoA2oPJ",
      "type": "Secp256r1VerificationKey2018"
    }
  ],
  "versionId": "1"
}
```

<br>

## 7. DID Key Removal

### Command

`did removeKey`

### Command Description

`Removes a key from the DID document.`

### Command Options

| Option                    | Type   | Description         | **M/O** | **Notes**                              |
| ------------------------- | ------ | ------------------- | ------- | ------------------------------------- |
| -m, --wallet-manager    | string | Wallet file name    | M       | Include the extension.<br/>e.g., omni.wallet  |
| -p, --wallet-manager-password| string | Wallet password     | M       | Must enter the password set during the initial wallet creation. |
| -f, --did-file            | string | DID file name       | M       | Include the extension.<br/>e.g., omni.did     |
| -i, --key-id              | string | Key ID              | M       |                                       |

### Command Declaration

```shell
# Command declaration in Shell 
java -jar [module_name] did removeKey -m [wallet_name] -f [did_name] -i [key_id] -p [password]
```

### Command Usage

```shell
java -jar did-cli-tool-server-1.0.0.jar did removeKey -m omni.wallet -f omni.did -i invoke2 -p
```

### CLI Properties Usage

* CLI option values can be set either via command line input or properties files. They can also be used in combination.

* If the same option value exists, the command line input takes precedence.
  
  ```shell
  # command line
  java -jar did-cli-tool-server-1.0.0.jar did removeKey -m omni.wallet -p
  ```

  ```shell
  # cli.properties
  key-id=invoke2
  did-file=omni.did
  ```

### Command Result Log

```shell
Enter value for --wallet-manager-password (Wallet password): (Input password)
== did remove key call ==
Command:    did->RemoveKey
dids : (Refer to Data Sample)
[SUCCESS] DID remove key success...
```

### Data Sample

```json
{
  "@context": [
    "https://www.w3.org/ns/did/v1"
  ],
  "assertionMethod": [
    "assert",
    "auth"
  ],
  "authentication": [
    "auth"
  ],
  "capabilityInvocation": [
    "invoke"
  ],
  "controller": "controllerDid",
  "created": "2024-07-12T02:38:09Z",
  "deactivated": false,
  "id": "did:omn:0123456789abcdef",
  "keyAgreement": [
    "keyagree"
  ],
  "proofs": [
    {
      "created": "2024-07-12T02:43:28Z",
      "proofPurpose": "assertionMethod",
      "proofValue": "z3tjVT7WWmU17gNhHv3GGZhSJ77HzXGgH6ZpjsBwjVf943wHBKFHrfuAV412nUgdzyCj73eGyA2zTcpNeEWgu5ikJC",
      "type": "Secp256r1Signature2018",
      "verificationMethod": "did:omn:0123456789abcdef?versionId=1#assert"
    },
    {
      "created": "2024-07-12T02:43:28Z",
      "proofPurpose": "assertionMethod",
      "proofValue": "z3rU1MobBWir1oP9kE3Eha5B8t3Us52z6UqisFyubTbG8QsBw9EUXVF2hzdaPPMcs2MoU3mgfzk4aPExH1aoUbL9QL",
      "type": "Secp256r1Signature2018",
      "verificationMethod": "did:omn:0123456789abcdef?versionId=1#auth"
    },
    {
      "created": "2024-07-12T02:43:28Z",
      "proofPurpose": "authentication",
      "proofValue": "z3tyfePjL8XKXfnpLP3BfJKXhEWgrNXvEkp57v1NHeDjfYZZDYBDmpQbNDHzzHA4hCyergwXZE2MNBqCndgAuXh54Y",
      "type": "Secp256r1Signature2018",
      "verificationMethod": "did:omn:0123456789abcdef?versionId=1#auth"
    },
    {
      "created": "2024-07-12T02:43:28Z",
      "proofPurpose": "capabilityInvocation",
      "proofValue": "z3tXiqWHo4omNYpwKUabA2ZMaRamk9eerR3jQAnAqG62PWgePs2Ae7H4q6NPVWDbrRw4u3pu1mKhZWePuNncJJ1YCZ",
      "type": "Secp256r1Signature2018",
      "verificationMethod": "did:omn:0123456789abcdef?versionId=1#invoke"
    }
  ],
  "updated": "2024-07-12T02:38:09Z",
  "verificationMethod": [
    {
      "authType": 1,
      "controller": "controllerDid",
      "id": "assert",
      "publicKeyMultibase": "ztNvWQaAfcq5EX3UfS6EDqUgVGFr4JxuuX5UaEYxFBpEL",
      "type": "Secp256r1VerificationKey2018"
    },
    {
      "authType": 1,
      "controller": "controllerDid",
      "id": "auth",
      "publicKeyMultibase": "zdLAQjay65BtNv8RZprum2mdNFruna2iR5PBsRjncwvVn",
      "type": "Secp256r1VerificationKey2018"
    },
    {
      "authType": 1,
      "controller": "controllerDid",
      "id": "keyagree",
      "publicKeyMultibase": "zfpwJri9rNgkDAHvmemZ6WMMBK8iFp1tQqxtuoZHgrgNu",
      "type": "Secp256r1VerificationKey2018"
    },
    {
      "authType": 1,
      "controller": "controllerDid",
      "id": "invoke",
      "publicKeyMultibase": "zndGa2B5YaB8s7u9F25VcBHXyUtqP6BiZ1wevPGPhZbX8",
      "type": "Secp256r1VerificationKey2018"
    }
  ],
  "versionId": "1"
}
```

<br>

## 8. Adding DID Service

### Command

`did addService`

### Command Description

`Adds a service to the DID document.`

### Command Options

| Option                    | Type   | Description                  | **M/O** | **Notes**                                                                          |
| ------------------------- | ------ | ---------------------------- | ------- | ---------------------------------------------------------------------------------- |
| -m, --wallet-manager  | string | Wallet file name             | M       | Include the extension.<br/>e.g., omni.wallet                                        |
| -p, --wallet-manager-password  | string | Wallet password              | M       | Must enter a user-defined password when creating the wallet file initially.         |
| -f, --did-file            | string | DID file name                | M       | Include the extension.<br/>e.g., omni.did                                           |
| -si, --did-service-id     | string | DID service ID               | M       |                                                                                      |
| -st, --did-service-type   | string | DID service type             | M       | LinkedDomains: site URL<br/>CredentialRegistry: URL to query verifiable credentials |
| -sul, --did-service-url-list | string | DID service URL              | M       | Specify as a comma-separated list.<br/>e.g., https://did.omnione.net, https://did.omnione.net2 |

### Command Declaration

```shell
# Command declaration in Shell
java -jar [module-name] did addService -m [wallet-name] -f [did-name] -si [service-id] -st [service-type] -sul [service-url-list] -p
```

### Command Usage

```shell
java -jar did-cli-tool-server-1.0.0.jar did addService -m omni.wallet -f omni.did -si homepage -st LinkedDomains -sul https://did.omnione.net,https://did.omnione.net2 -p
```

### CLI Properties Usage

* CLI option values can be set via command line input or properties files. They can also be used in combination.

* If the same option value exists, command line input takes precedence.
  
  ```shell
  # command line
  java -jar did-cli-tool-server-1.0.0.jar did addService -m omni.wallet -f omni.did -p
  ```

  ```shell
  # cli.properties
  ## DID Service Endpoint
  # did service Type : LinkedDomains(site URL), CredentialRegistry(URL to query verifiable credentials)
  did-service-type=LinkedDomains
  # did service id
  # ex. homepage, certificate-vc
  did-service-id=homepage
  ## DID Service url list (note) comma(,) base
  # (ex. did-service-url=https://did.omnione.net,https://did.omnione.net2)
  did-service-url-list=https://did.omnione.net,https://did.omnione.net2
  ```

### Command Result Log

```shell
Enter value for --wallet-manager-password (Wallet password): (Input password)
== did add service call ==
Command:    did->AddService
dids : (Refer to Data Sample)
[SUCCESS] DID add service success...
```

### Data Sample

```json
{
  "@context": [
    "https://www.w3.org/ns/did/v1"
  ],
  "assertionMethod": [
    "assert",
    "auth"
  ],
  "authentication": [
    "auth"
  ],
  "capabilityInvocation": [
    "invoke"
  ],
  "controller": "controllerDid",
  "created": "2024-07-12T02:38:09Z",
  "deactivated": false,
  "id": "did:omn:0123456789abcdef",
  "keyAgreement": [
    "keyagree"
  ],
  "proofs": [
    {
      "created": "2024-07-12T02:45:24Z",
      "proofPurpose": "assertionMethod",
      "proofValue": "z3kgT8c3WgbGFaEpDStKdqTpMvNQo9zyUJp9PC4ArHWn7D3XbCn5uEnaCZqVftqc2TVj6DPx5wB1zK32PpzRgM2AVu",
      "type": "Secp256r1Signature2018",
      "verificationMethod": "did:omn:0123456789abcdef?versionId=1#assert"
    },
    {
      "created": "2024-07-12T02:45:24Z",
      "proofPurpose": "assertionMethod",
      "proofValue": "z3tMdU7nP6RTsrGm9GMzpAaXe7nYdTLCfVDADkhj5UwkJG9AV96f8xvGpKywqMHN9KDLrQ1Z3QvTksp2bWvY3LERAN",
      "type": "Secp256r1Signature2018",
      "verificationMethod": "did:omn:0123456789abcdef?versionId=1#auth"
    },
    {
      "created": "2024-07-12T02:45:24Z",
      "proofPurpose": "authentication",
      "proofValue": "z3owCBsQMh4KDHTLNjaHiZ7B1mz5uQQuxtyEK1CWEP1nVPNfx5D8EjdpQADoagV59NQFqAWxBYkPzfXJs8UsJmqBcq",
      "type": "Secp256r1Signature2018",
      "verificationMethod": "did:omn:0123456789abcdef?versionId=1#auth"
    },
    {
      "created": "2024-07-12T02:45:24Z",
      "proofPurpose": "capabilityInvocation",
      "proofValue": "z3r9Uqt7zccHhPB2CcJA3a7UgERPMtPmkkjnGhwNXA7v6BVeq8h5DC4L41SbzxsRgxq15uSU6vQGM8TeqfYutQF9Aw",
      "type": "Secp256r1Signature2018",
      "verificationMethod": "did:omn:0123456789abcdef?versionId=1#invoke"
    }
  ],
  "service": [
    {
      "id": "homepage",
      "serviceEndpoint": [
        "https://did.omnione.net",
        "https://did.omnione.net2"
      ],
      "type": "LinkedDomains"
    }
  ],
  "updated": "2024-07-12T02:45:24Z",
  "verificationMethod": [
    {
      "authType": 1,
      "controller": "controllerDid",
      "id": "assert",
      "publicKeyMultibase": "ztNvWQaAfcq5EX3UfS6EDqUgVGFr4JxuuX5UaEYxFBpEL",
      "type": "Secp256r1VerificationKey2018"
    },
    {
      "authType": 1,
      "controller": "controllerDid",
      "id": "auth",
      "publicKeyMultibase": "zdLAQjay65BtNv8RZprum2mdNFruna2iR5PBsRjncwvVn",
      "type": "Secp256r1VerificationKey2018"
    },
    {
      "authType": 1,
      "controller": "controllerDid",
      "id": "keyagree",
      "publicKeyMultibase": "zfpwJri9rNgkDAHvmemZ6WMMBK8iFp1tQqxtuoZHgrgNu",
      "type": "Secp256r1VerificationKey2018"
    },
    {
      "authType": 1,
      "controller": "controllerDid",
      "id": "invoke",
      "publicKeyMultibase": "zndGa2B5YaB8s7u9F25VcBHXyUtqP6BiZ1wevPGPhZbX8",
      "type": "Secp256r1VerificationKey2018"
    }
  ],
  "versionId": "1"
}
```

<br>

## 9. DID Service Removal

### Command

`did removeService`

### Command Description

`Removes a service from the DID document.`

### Command Options

| Option                    | Type   | Description        | **M/O** | **Notes**                                                                           |
| ------------------------- | ------ | ------------------ | ------- | --------------------------------------------------------------------------------- |
| -m, --wallet-manager   | string | Wallet file name   | M       | Include the file extension.<br/>ex) omni.wallet                                     |
| -p, --wallet-manager-password| string | Wallet password    | M       | You must enter a user-defined password when creating the wallet file initially.   |
| -f, --did-file            | string | DID file name      | M       | Include the file extension.<br/>ex) omni.did                                        |
| -si, --did-service-id     | string | DID service ID     | M       |                                                                                   |
| -st, --did-service-type   | string | DID service type   | O       | LinkedDomains: site URL<br/>CredentialRegistry: URL to query verifiable credentials |
| -su, --did-service-url    | string | DID service URL    | O       | Set one URL.<br/>ex) https://did.omnione.net                                        |

### Command Declaration

```shell
# Command declaration in Shell
java -jar [module-name] did removeService -m [wallet-name] -f [did-name] -si [service-id] -st [service-type] -su [service-url] -p
```

### Command Usage

```shell
java -jar did-cli-tool-server-1.0.0.jar did removeService -m omni.wallet -f omni.did -si homepage -st LinkedDomains -su https://did.omnione.net -p
```

### CLI Properties Usage

* CLI option values can be set via command line input or properties file. They can also be used in combination.

* If the same option value exists, command line input takes precedence.

  ```shell
  # command line
  java -jar did-cli-tool-server-1.0.0.jar did removeService -m omni.wallet -f omni.did -p
  ```

  ```shell
  # cli.properties
  ## DID Service Endpoint
  # did service Type : LinkedDomains(site URL), CredentialRegistry(URL to query verifiable credentials)
  did-service-type=LinkedDomains
  # did service id
  # ex. homepage, certificate-vc
  did-service-id=homepage
  ## DID Service url list (note) comma(,) base
  # (ex. did-service-url=https://did.omnione.net,https://did.omnione.net2)
  did-service-url=https://did.omnione.net
  ```

### Command Result Log

```shell
Enter value for --wallet-manager-password (Wallet password): (Input password)
== did remove service call ==
Command:    did->RemoveService
dids : (Refer to Data Sample)
[SUCCESS] DID remove service success...
```

### Data Sample

```json
{
  "@context": [
    "https://www.w3.org/ns/did/v1"
  ],
  "assertionMethod": [
    "assert",
    "auth"
  ],
  "authentication": [
    "auth"
  ],
  "capabilityInvocation": [
    "invoke"
  ],
  "controller": "controllerDid",
  "created": "2024-07-12T02:38:09Z",
  "deactivated": false,
  "id": "did:omn:0123456789abcdef",
  "keyAgreement": [
    "keyagree"
  ],
  "proofs": [
    {
      "created": "2024-07-12T02:46:40Z",
      "proofPurpose": "assertionMethod",
      "proofValue": "z3tj8vNa6yVVYRunDPmNvxjMG6LiP5epTr5umojijqM3UEjRjhiJrUgB7NRMLtfTNSipLLXy7ZTWWbL6FSNtwUZGWy",
      "type": "Secp256r1Signature2018",
      "verificationMethod": "did:omn:0123456789abcdef?versionId=1#assert"
    },
    {
      "created": "2024-07-12T02:46:40Z",
      "proofPurpose": "assertionMethod",
      "proofValue": "z3oV8RbqqDNSH7piN49Whn9yqAEx5WPoP4F6G3aBz2fE9nUaef2481WjhfKAD394U44N8aYA23MikGF8fhbT8EediL",
      "type": "Secp256r1Signature2018",
      "verificationMethod": "did:omn:0123456789abcdef?versionId=1#auth"
    },
    {
      "created": "2024-07-12T02:46:40Z",
      "proofPurpose": "authentication",
      "proofValue": "z3p2HUM77eiuRLfnjGaWd4MN2DKYXxsC8tw5jun87D2EezubhcbtSKB5zkHZuK7BBR3pnETwkSTMaXKAZg5q8DXwry",
      "type": "Secp256r1Signature2018",
      "verificationMethod": "did:omn:0123456789abcdef?versionId=1#auth"
    },
    {
      "created": "2024-07-12T02:46:40Z",
      "proofPurpose": "capabilityInvocation",
      "proofValue": "z3t25Nhgwm2Hu7foHDJZYhD3KhBFQAZtBsH57Ko47vXti1VJXWs17KU9ExnjHhyomZyTVs5xxpympGYhcg75sydpYN",
      "type": "Secp256r1Signature2018",
      "verificationMethod": "did:omn:0123456789abcdef?versionId=1#invoke"
    }
  ],
  "service": [
    {
      "id": "homepage",
      "serviceEndpoint": [
        "https://did.omnione.net2"
      ],
      "type": "LinkedDomains"
    }
  ],
  "updated": "2024-07-12T02:46:40Z",
  "verificationMethod": [
    {
      "authType": 1,
      "controller": "controllerDid",
      "id": "assert",
      "publicKeyMultibase": "ztNvWQaAfcq5EX3UfS6EDqUgVGFr4JxuuX5UaEYxFBpEL",
      "type": "Secp256r1VerificationKey2018"
    },
    {
      "authType": 1,
      "controller": "controllerDid",
      "id": "auth",
      "publicKeyMultibase": "zdLAQjay65BtNv8RZprum2mdNFruna2iR5PBsRjncwvVn",
      "type": "Secp256r1VerificationKey2018"
    },
    {
      "authType": 1,
      "controller": "controllerDid",
      "id": "keyagree",
      "publicKeyMultibase": "zfpwJri9rNgkDAHvmemZ6WMMBK8iFp1tQqxtuoZHgrgNu",
      "type": "Secp256r1VerificationKey2018"
    },
    {
      "authType": 1,
      "controller": "controllerDid",
      "id": "invoke",
      "publicKeyMultibase": "zndGa2B5YaB8s7u9F25VcBHXyUtqP6BiZ1wevPGPhZbX8",
      "type": "Secp256r1VerificationKey2018"
    }
  ],
  "versionId": "1"
}
```

<br>

## 10. DID Version ID Lookup

### Command

`did version`

### Command Description

`Queries the version ID of a DID document.`

### Command Options

| Option         | Type   | Description         | **M/O** | **Notes**                          |
| -------------- | ------ | ------------------- | ------- | --------------------------------- |
| -f, --did-file | string | DID file name       | M       | Include the file extension.<br/>e.g., omni.did |

### Command Declaration

```shell
# Command declaration in Shell
java -jar [module-name] did version -f [did-name]
```

### Command Usage

```shell
java -jar did-cli-tool-server-1.0.0.jar did version -f omni.did
```

### CLI Properties Usage

* CLI option values can be set either through command line input or properties files. They can also be used in combination.

* If the same option value exists, the command line input takes precedence.

  ```shell
  # command line
  java -jar did-cli-tool-server-1.0.0.jar did version
  ```

   ```shell
  # cli.properties
  did-file=omni.did
  ```

### Command Result Log

```shell
== did get version id call ==
Command:    did->Version
DID file: omni.did
DID versionId: 1
[SUCCESS] DIDs get version id success...
```

<br>

## 11. Changing DID Version ID

### Command

`did updateVersion`

### Command Description

Changes the version ID of the DID Document.

### Command Options

| Option                    | Type   | Description                  | **M/O** | **Notes**                            |
| ------------------------- | ------ | ---------------------------- | ------- | ------------------------------------ |
| -m, --wallet-manager    | string | Wallet file name             | M       | Include the extension.<br/>e.g., omni.wallet  |
| -p, --wallet-manager-password| string | Wallet password              | M       | You must enter a user-defined password when creating the wallet file initially. |
| -f, --did-file            | string | DID file name                | M       | Include the extension.<br/>e.g., omni.did     |
| -vi, --did-version-id     | int    | DID version ID               | M       | Enter as a decimal number greater than 1.                     |

### Command Declaration

```shell
# Command declaration in Shell
java -jar [module name] did updateVersion -m [wallet name] -f [did name] -vi [version ID] -p
```

### Command Usage

```shell
java -jar did-cli-tool-server-1.0.0.jar did updateVersion -m omni.wallet -f omni.did -vi 2 -p
```

### CLI Properties Usage

* CLI option values can be set via command line input or a properties file. They can also be used together.

* If the same option value exists, command line input takes precedence.

  ```shell
  # command line
  java -jar did-cli-tool-server-1.0.0.jar did updateVersion -m omni.wallet -f omni.did -p
  ```

  ```shell
  # cli.properties
  did-version-id=2
  ```

### Command Result Log

```shell
Enter value for --wallet-manager-password (Wallet password): (Input password)
== did update version id call ==
Command:    did->UpdateVersion
dids : (Refer to Data Sample)
[SUCCESS] DID update version id success...
```

### Data Sample

```json
{
  "@context": [
    "https://www.w3.org/ns/did/v1"
  ],
  "assertionMethod": [
    "assert",
    "auth"
  ],
  "authentication": [
    "auth"
  ],
  "capabilityInvocation": [
    "invoke"
  ],
  "controller": "controllerDid",
  "created": "2024-07-12T02:38:09Z",
  "deactivated": false,
  "id": "did:omn:0123456789abcdef",
  "keyAgreement": [
    "keyagree"
  ],
  "proofs": [
    {
      "created": "2024-07-12T02:48:29Z",
      "proofPurpose": "assertionMethod",
      "proofValue": "z3pTuV3e8w9g8bPp8siPpRAGksDwi1VTGQHe6Zbz4NEhxiipUxXQroWxBvuG4uFrxwUhBhuRqDqAhVmZStHmWFU64U",
      "type": "Secp256r1Signature2018",
      "verificationMethod": "did:omn:0123456789abcdef?versionId=2#assert"
    },
    {
      "created": "2024-07-12T02:48:29Z",
      "proofPurpose": "assertionMethod",
      "proofValue": "z3sLCNmzPrwwiRCfDoWPwPsz9A9pxwBVa4uiJqYUxqJBHGkpmi6e6vkibsLdjSK2LWscYzwQv3A24TVnCHJnd32Aok",
      "type": "Secp256r1Signature2018",
      "verificationMethod": "did:omn:0123456789abcdef?versionId=2#auth"
    },
    {
      "created": "2024-07-12T02:48:29Z",
      "proofPurpose": "authentication",
      "proofValue": "z3jk3mUSqq6Mmo1vPJQFQpbKp9FWM4LWZ76RXzyKZU9BczTUTF9g9bLaw6HC1Wrs69gaqEqRpJra8ao52yeaBNvkXN",
      "type": "Secp256r1Signature2018",
      "verificationMethod": "did:omn:0123456789abcdef?versionId=2#auth"
    },
    {
      "created": "2024-07-12T02:48:29Z",
      "proofPurpose": "capabilityInvocation",
      "proofValue": "z3qsBawVrp7zgqEnenzuo5gKUtjyBuMzuYWy2fBxhnEFoHsT2rb11En5TX9DqqMa3fcHg4KeNvTKqkZ4H7EzXmFuM4",
      "type": "Secp256r1Signature2018",
      "verificationMethod": "did:omn:0123456789abcdef?versionId=2#invoke"
    }
  ],
  "service": [
    {
      "id": "homepage",
      "serviceEndpoint": [
        "https://did.omnione.net2"
      ],
      "type": "LinkedDomains"
    }
  ],
  "updated": "2024-07-12T02:46:40Z",
  "verificationMethod": [
    {
      "authType": 1,
      "controller": "controllerDid",
      "id": "assert",
      "publicKeyMultibase": "ztNvWQaAfcq5EX3UfS6EDqUgVGFr4JxuuX5UaEYxFBpEL",
      "type": "Secp256r1VerificationKey2018"
    },
    {
      "authType": 1,
      "controller": "controllerDid",
      "id": "auth",
      "publicKeyMultibase": "zdLAQjay65BtNv8RZprum2mdNFruna2iR5PBsRjncwvVn",
      "type": "Secp256r1VerificationKey2018"
    },
    {
      "authType": 1,
      "controller": "controllerDid",
      "id": "keyagree",
      "publicKeyMultibase": "zfpwJri9rNgkDAHvmemZ6WMMBK8iFp1tQqxtuoZHgrgNu",
      "type": "Secp256r1VerificationKey2018"
    },
    {
      "authType": 1,
      "controller": "controllerDid",
      "id": "invoke",
      "publicKeyMultibase": "zndGa2B5YaB8s7u9F25VcBHXyUtqP6BiZ1wevPGPhZbX8",
      "type": "Secp256r1VerificationKey2018"
    }
  ],
  "versionId": "2"
}
```


<br>

## 12. Auto-generate Wallet, DID Document

### Command

`auto generator`

### Command Introduction

`Automatically generate a Wallet, DID Document file with basic key information.`

`Wallet, DID's keys are set to “assert”, “auth”, “keyagree”, and “invoke” by default.`

### Command Options

| Option                                  | Type    | Description   | **M/O** | **Notes**                                                                              |
| --------------------------------------- | ------- | ------------- | ------- | -------------------------------------------------------------------------------- |
| -m, --wallet-manager   | string | Wallet file name             | M       | Include the extension.<br/>e.g., omni.wallet  |
| -p, --wallet-manager-password| string | Wallet password              | M       | Must enter the password set during the initial wallet creation.|
| -f, --did-file            | string | DID file name      | M       | Include the file extension.<br/>ex) omni.did                                        |
| -r, --file-remove                       | boolean | Whether to delete DID file | O       | Set this option to delete the file if it already exists. |
| -id, --did-id                           | string  | DID ID                   | M       | e.g., did:example:abcdefg1234567890                                                |
| -ci, --did-controller-id                | string  | DID Controller           | M       |                                                                                  |M       |                                                                                  |
| -si, --did-service-id     | string | DID service ID     | O        |                                                                                   |
| -st, --did-service-type   | string | DID service type   | O       | LinkedDomains: site URL<br/>CredentialRegistry: URL to query verifiable credentials |
| -su, --did-service-url    | string | DID service URL    | O       | Set one URL.<br/>ex) https://did.omnione.net                                        |

### Command Declaration

```shell
# Command declaration in Shell
java -jar [moduleName] auto generator -m [walletName] -f [didName] -id [didId] -ci [contoller] -p
```

### Command Usage

```shell
java -jar did-cli-tool-server-1.0.0.jar auto generator -m omni.wallet -f omni.did -id did:omn:0123456789abcdef -ci controllerDid -p
```

### CLI Properties Usage

* CLI option values can be set via command-line input or properties files. They can also be used together.

* If the same option value exists, the command-line input takes precedence.
  
  ```shell
  # command line
  java -jar did-cli-tool-server-1.0.0.jar auto generator -m omni.wallet -f omni.did -p
  ```
  
  ```shell
  # cli.properties
  # DID Id
  did-id=did:omn:0123456789abcdef
  # DID Controller
  did-controller-id=controllerDid
  ```

### Command Result Log

```shell
Enter value for --wallet-manager-password (Wallet password): (Enter password)
== new wallet, did file create call ==
Command:	generator->Generator
====== STEP 1 =====
== new wallet create call ==
Command:	walletmanager->CreateWallet
[SUCCESS] Wallet generate success...
====== End 1 =====
====== STEP 2 =====
== wallet add key call ==
Command:	walletmanager->AddKey
KeyId:assert, KeyType: SECP256r1...
[SUCCESS] WalletManager add key success...
Adding key id assert to wallet
== wallet add key call ==
Command:	walletmanager->AddKey
KeyId:auth, KeyType: SECP256r1...
[SUCCESS] WalletManager add key success...
Adding key id auth to wallet
== wallet add key call ==
Command:	walletmanager->AddKey
KeyId:keyagree, KeyType: SECP256r1...
[SUCCESS] WalletManager add key success...
Adding key id keyagree to wallet
== wallet add key call ==
Command:	walletmanager->AddKey
KeyId:invoke, KeyType: SECP256r1...
[SUCCESS] WalletManager add key success...
Adding key id invoke to wallet
====== End 2 =====
====== STEP 3 =====
== new did create call ==
Command:	did->CreateDid
dids : (Refer to Data Sample)
[SUCCESS] DID generate success...
====== End 3 =====
No Service value entered.
```

### Data Sample

```json
{
  "@context": [
    "https://www.w3.org/ns/did/v1"
  ],
  "assertionMethod": [
    "assert"
  ],
  "authentication": [
    "auth"
  ],
  "capabilityInvocation": [
    "invoke"
  ],
  "controller": "controllerDid",
  "created": "2024-07-31T10:29:31Z",
  "deactivated": false,
  "id": "did:omn:0123456789abcdef",
  "keyAgreement": [
    "keyagree"
  ],
  "proofs": [
    {
      "created": "2024-07-31T10:29:31Z",
      "proofPurpose": "assertionMethod",
      "proofValue": "z3kBgGFRNwCybVX7wxKLzoepVkFLwNBEspWEbxWQsAzCJaCkwt9DSfHwRvfTZijnXPi7i19FafHQ5rBfHwT9fFAmTE",
      "type": "Secp256r1Signature2018",
      "verificationMethod": "did:omn:0123456789abcdef?versionId=1#assert"
    },
    {
      "created": "2024-07-31T10:29:31Z",
      "proofPurpose": "authentication",
      "proofValue": "z3oMN6nbAissag9e8fP82NpsuZdTBBK4ikc165ffn7aCzkX8FdzEaQEQhtB7UPfyfxPyBPKwKHMTneWxHeKszgvRLu",
      "type": "Secp256r1Signature2018",
      "verificationMethod": "did:omn:0123456789abcdef?versionId=1#auth"
    },
    {
      "created": "2024-07-31T10:29:31Z",
      "proofPurpose": "capabilityInvocation",
      "proofValue": "z3r39pLzFnUb1ZdL5EMyhogQvPrBPD949sXXyC6yvVW9PxUexGQKx7WfeNYW4Vi69QGa2EgNJ6bU3JucoiTsFEYXrK",
      "type": "Secp256r1Signature2018",
      "verificationMethod": "did:omn:0123456789abcdef?versionId=1#invoke"
    }
  ],
  "updated": "2024-07-31T10:29:31Z",
  "verificationMethod": [
    {
      "authType": 1,
      "controller": "controllerDid",
      "id": "assert",
      "publicKeyMultibase": "zisUdUzb5ASN5jDfLihMkQUA3XPeeD7P23pVQq4DE2BBz",
      "type": "Secp256r1VerificationKey2018"
    },
    {
      "authType": 1,
      "controller": "controllerDid",
      "id": "auth",
      "publicKeyMultibase": "z2569cDDrdLx1YSDkMqcfbEkcrCNdLnzAyv77tUArihjFu",
      "type": "Secp256r1VerificationKey2018"
    },
    {
      "authType": 1,
      "controller": "controllerDid",
      "id": "keyagree",
      "publicKeyMultibase": "z25N9XWFMAfwFYBozN8XUtS2eD1tHF9LcPczjprDhfeCAn",
      "type": "Secp256r1VerificationKey2018"
    },
    {
      "authType": 1,
      "controller": "controllerDid",
      "id": "invoke",
      "publicKeyMultibase": "zwWin4rUv4K8hFNuRy5Tsb3wo7uo5rgBGPNWQSiFxowDH",
      "type": "Secp256r1VerificationKey2018"
    }
  ],
  "versionId": "1"
}
```