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

- 주제: CLI Tool Command
- 작성: 이은정
- 일자: 2024-08-29
- 버전: v1.0.0

| 버전     | 일자        | 변경 내용 |
| ------ |------------| ----- |
| v1.0.0 | 2024-08-29 | 초기 작성 |

<div style="page-break-after: always;"></div>

# 목차

- [1. 월렛 생성](#1-월렛-생성)
- [2. 월렛 키 추가](#2-월렛-키-추가)
- [3. 월렛 키 삭제](#3-월렛-키-삭제)
- [4. 월렛 키 목록 조회](#4-월렛-키-목록-조회)
- [5. DID 문서 생성](#5-did-문서-생성)
- [6. DID 키 추가](#6-did-키-추가)
- [7. DID 키 삭제](#7-did-키-삭제)
- [8. DID 서비스 추가](#8-did-서비스-추가)
- [9. DID 서비스 삭제](#9-did-서비스-삭제)
- [10. DID 버전 아이디 조회](#10-did-버전-아이디-조회)
- [11. DID 버전 아이디 변경](#11-did-버전-아이디-변경)
- [12. Wallet, DID Document 자동 생성](#12-wallet-did-document-자동-생성)


# 기능 목록

## 1. 월렛 생성

### Command

`walletManager createWallet`

### Command Introduction

`파일 월렛을 생성합니다.`

### Command Options

| Option                    | Type    | Description | **M/O** | **비고**                                      |
| ------------------------- | ------- | ----------- | ------- | ------------------------------------------- |
| -m, --wallet-manager         | string  | 월렛 파일 명     | M       | 확장자 포함하여 입력한다.<br/>ex) omni.wallet          |
| -p, --wallet-manager-password | string  | 월렛 비밀번호     | M       | 최초 파일 월렛 생성 시 사용자 지정 비밀번호를 입력해야 한다.         |
| -r, --file-remove         | boolean | 월렛 파일 삭제 여부 | O       | -m 옵션으로 지정한 파일 월렛이 존재하는 경우 파일의 삭제 옵션을 설정한다. |

### Command Declaration

```shell
# Command declaration in Shell 
java -jar [모듈명] walletManager createWallet -m [wallet명] -p [password]
```

### Command Usage

```shell
# create omni.wallet in Shell
java -jar did-cli-tool-server-1.0.0.jar walletManager createWallet -m omni.wallet -p           
```

### CLI Properties Usage

* CLI 옵션 값은 명령줄 입력 또는 properties 파일로 설정 가능합니다. 또 복합 사용 가능합니다.

* 동일한 옵션 값 존재 시, 명령줄 입력을 우선합니다.
  
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
Enter value for --wallet-manager-password (Wallet password): (비밀번호 입력)
== new wallet create call ==
Command:	walletmanager->CreateWallet
[SUCCESS] Wallet generate success...
```

<br>

## 2. 월렛 키 추가

### Command

`walletManager addKey`

### Command Introduction

`파일 월렛에 키를 추가합니다.`

### Command Options

| Option                    | Type   | Description | **M/O** | **비고**                              |
| ------------------------- | ------ | ----------- | ------- | ----------------------------------- |
| -m, --wallet-manager         | string  | 월렛 파일 명     | M       | 확장자 포함하여 입력한다.<br/>ex) omni.wallet          |
| -p, --wallet-manager-password | string  | 월렛 비밀번호     | M       | 최초 파일 월렛 생성 시 사용자 지정 비밀번호를 입력해야 한다.         |
| -i, --key-id              | string | 키 아이디       | M       |                                     |
| -t, --key-type            | int    | 키 알고리즘 타입   | M       | 0 : SECP256k1<br/>1 : SECP256r1     |

### Command Declaration

```shell
# Command declaration in Shell
java -jar [모듈명] walletManager addKey -m [wallet명] -i [keyId] -t [keyType] -p [password]
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

* CLI 옵션 값은 명령줄 입력 또는 properties 파일로 설정 가능합니다. 또 복합 사용 가능합니다.

* 동일한 옵션 값 존재 시, 명령줄 입력을 우선합니다.
  
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
Enter value for --wallet-manager-password (Wallet password): (비밀번호 입력)
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

## 3. 월렛 키 삭제

### Command

`walletManager removeKey`

### Command Introduction

`파일 월렛의 키를 삭제합니다.`

### Command Options

| Option                    | Type   | Description | **M/O** | **비고**                              |
| ------------------------- | ------ | ----------- | ------- | ----------------------------------- |
| -m, --wallet-manager         | string  | 월렛 파일 명     | M       | 확장자 포함하여 입력한다.<br/>ex) omni.wallet          |
| -p, --wallet-manager-password | string  | 월렛 비밀번호     | M       | 최초 파일 월렛 생성 시 사용자 지정 비밀번호를 입력해야 한다.         |
| -i, --key-id              | string | 키 아이디       | M       |                                     |

### Command Declaration

```shell
# Command declaration in Shell 
java -jar [모듈명] walletManager removeKey -m [wallet명] -i [키아이디] -p [password]
```

### Command Usage

```shell
java -jar did-cli-tool-server-1.0.0.jar walletManager removeKey -m omni.wallet -i invoke2 -p
```

### CLI Properties Usage

* CLI 옵션 값은 명령줄 입력 또는 properties 파일로 설정 가능합니다. 또 복합 사용 가능합니다.

* 동일한 옵션 값 존재 시, 명령줄 입력을 우선합니다.
  
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
Enter value for --wallet-manager-password (Wallet password): (비밀번호 입력)
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

## 4. 월렛 키 목록 조회

### Command

`walletManager keyList`

### Command Introduction

`파일 월렛의 키 목록을 조회합니다.`

### Command Options

| Option                    | Type   | Description | **M/O** | **비고**                              |
| ------------------------- | ------ | ----------- | ------- | ----------------------------------- |
| -m, --wallet-manager         | string  | 월렛 파일 명     | M       | 확장자 포함하여 입력한다.<br/>ex) omni.wallet          |
| -p, --wallet-manager-password | string  | 월렛 비밀번호     | M       | 최초 파일 월렛 생성 시 사용자 지정 비밀번호를 입력해야 한다.         |

### Command Declaration

```shell
# Command declaration in Shell
java -jar [모듈명] walletManager keyList -m [wallet명] -p [password]
```

### Command Usage

```shell
java -jar did-cli-tool-server-1.0.0.jar walletManager keyList -m omni.wallet -p
```

### CLI Properties Usage

* CLI 옵션 값은 명령줄 입력 또는 properties 파일로 설정 가능합니다. 또 복합 사용 가능합니다.

* 동일한 옵션 값 존재 시, 명령줄 입력을 우선합니다.
  
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
Enter value for --wallet-manager-password (Wallet password): (비밀번호 입력)
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

## 5. DID 문서 생성

### Command

`did createDid`

### Command Introduction

`DID 문서를 생성합니다.`

### Command Options

| Option                                  | Type    | Description   | **M/O** | **비고**                                                                           |
| --------------------------------------- | ------- | ------------- | ------- | -------------------------------------------------------------------------------- |
| -m, --wallet-manager         | string  | 월렛 파일 명     | M       | 확장자 포함하여 입력한다.<br/>ex) omni.wallet          |
| -p, --wallet-manager-password | string  | 월렛 비밀번호     | M       | 최초 파일 월렛 생성 시 사용자 지정 비밀번호를 입력해야 한다.         |
| -f, --did-file                          | string  | DID 파일 명      | M       | 확장자 포함하여 입력한다.<br/>ex) omni.did                                                  |
| -r, --file-remove                       | boolean | DID 파일 삭제 여부  | O       | -f 옵션으로 지정한 DID 파일이 존재하는 경우 파일의 삭제 옵션을 설정한다.                                     |
| -id, --did-id                           | string  | DID 아이디       | M       | ex) did:example:abcdefg1234567890                                                |
| -ci, --did-controller-id                | string  | DID 컨트롤러      | M       |                                                                                  |
| -mi, --assertionMethod-key-id-list      | string  | DID 서명 키 목록   | M       | comma(,)로 구분하여 설정한다.<br/>ex) assert, pin                                         |
| -ai, --authentication-key-id-list       | string  | DID 인증 키 목록   | M       | comma(,)로 구분하여 설정한다.<br/>ex) auth, auth2                                         |
| -ki, --keyAgreement-key-id-list         | string  | DID 키 교환 키 목록 | M       | comma(,)로 구분하여 설정한다.<br/>ex) keyagree, keyagree2                                 |
| -ii, --capabilityInvocation-key-id-list | string  | DID 수행 키 목록   | O       | DID 종류에 따라 선택적으로 입력됩니다.<br/>comma(,)로 구분하여 설정한다.<br/>ex) invoke, invoke2         |
| -di, --capabilityDelegation-key-id-list | string  | DID 위임 키 목록   | O       | DID 종류에 따라 선택적으로 입력됩니다.<br/>comma(,)로 구분하여 설정한다.<br/>ex) delegation, delegation2 |

### Command Declaration

```shell
# Command declaration in Shell
java -jar [모듈명] did createDid -m [wallet명] -f [did명] -id [didId] -ci [contoller] -mi [서명키목록] -ai [인증키목록] -ki [키교환키목록] -ii [수행키목록] -p
```

### Command Usage

```shell
java -jar did-cli-tool-server-1.0.0.jar did createDid -m omni.wallet -f omni.did -id did:omn:0123456789abcdef -ci controllerDid -mi assert -ai auth -ki keyagree -ii invoke,invoke2 -p
```

### CLI Properties Usage

* CLI 옵션 값은 명령줄 입력 또는 properties 파일로 설정 가능합니다. 또 복합 사용 가능합니다.

* 동일한 옵션 값 존재 시, 명령줄 입력을 우선합니다.
  
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
Enter value for --wallet-manager-password (Wallet password): (비밀번호 입력)
== new did create call ==
Command:	did->CreateDid
dids : (Data Sample 참고)
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

## 6. DID 키 추가

### Command

`did addKey`

### Command Introduction

`DID 문서에 키를 추가합니다.`

### Command Options

| Option                    | Type   | Description | **M/O** | **비고**             |
| ------------------------- | ------ | ----------- | ------- | ------------------------------------ |
| -m, --wallet-manager| string  | 월렛 파일 명     | M    | 확장자 포함하여 입력한다.<br/>ex) omni.wallet |
| -p, --wallet-manager-password | string  | 월렛 비밀번호     | M  | 최초 파일 월렛 생성 시 사용자 지정 비밀번호를 입력해야 한다.|
| -f, --did-file            | string | DID 파일 명    | M       | 확장자 포함하여 입력한다.<br/>ex) omni.did     |
| -i, --key-id              | string | 키 아이디       | M       |                                       |
| -dt, --did-key-type       | string | DID 키 타입    | M       | assert : assertionMethod<br/>auth : authentication<br/>keyagree : keyAgreement<br/>invoke : capabilityInvocation<br/>delegate : capabilityDelegation |

### Command Declaration

```shell
# Command declaration in Shell
java -jar [모듈명] did addKey -m [wallet명] -f [did명] -i [키아이디] -dt [키타입] -p
```

### Command Usage

```shell
java -jar did-cli-tool-server-1.0.0.jar did addKey -m omni.wallet -f omni.did -i auth -dt assert -p
```

### CLI Properties Usage

* CLI 옵션 값은 명령줄 입력 또는 properties 파일로 설정 가능합니다. 또 복합 사용 가능합니다.

* 동일한 옵션 값 존재 시, 명령줄 입력을 우선합니다.
  
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
Enter value for --wallet-manager-password (Wallet password): (비밀번호 입력)
== did add key call ==
Command:    did->AddKey
dids : (Data Sample 참고)
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

## 7. DID 키 삭제

### Command

`did removeKey`

### Command Introduction

`DID 문서의 키를 삭제합니다.`

### Command Options

| Option                    | Type   | Description | **M/O** | **비고**                              |
| ------------------------- | ------ | ----------- | ------- | ----------------------------------- |
| -m, --wallet-manager    | string  | 월렛 파일 명     | M       | 확장자 포함하여 입력한다.<br/>ex) omni.wallet    |
| -p, --wallet-manager-password | string  | 월렛 비밀번호  | M    | 최초 파일 월렛 생성 시 사용자 지정 비밀번호를 입력해야 한다.   |
| -f, --did-file            | string | DID 파일 명    | M       | 확장자 포함하여 입력한다.<br/>ex) omni.did     |
| -i, --key-id              | string | 키 아이디       | M       |                                     |

### Command Declaration

```shell
# Command declaration in Shell 
java -jar [모듈명] did removeKey -m [wallet명] -f [did명] -i [키아이디] -p [password]
```

### Command Usage

```shell
java -jar did-cli-tool-server-1.0.0.jar did removeKey -m omni.wallet -f omni.did -i invoke2 -p
```

### CLI Properties Usage

* CLI 옵션 값은 명령줄 입력 또는 properties 파일로 설정 가능합니다. 또 복합 사용 가능합니다.

* 동일한 옵션 값 존재 시, 명령줄 입력을 우선합니다.
  
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
Enter value for --wallet-manager-password (Wallet password): (비밀번호 입력)
== did remove key call ==
Command:    did->RemoveKey
dids : (Data Sample 참고)
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

## 8. DID 서비스 추가

### Command

`did addService`

### Command Introduction

`DID 문서에 서비스를 추가합니다.`

### Command Options

| Option                    | Type   | Description | **M/O** | **비고**                                                                              |
| ------------------------- | ------ | ----------- | ------- | ----------------------------------------------------------------------------------- |
| -m, --wallet-manager    | string  | 월렛 파일 명     | M       | 확장자 포함하여 입력한다.<br/>ex) omni.wallet    |
| -p, --wallet-manager-password | string  | 월렛 비밀번호  | M    | 최초 파일 월렛 생성 시 사용자 지정 비밀번호를 입력해야 한다.   |
| -f, --did-file            | string | DID 파일 명    | M       | 확장자 포함하여 입력한다.<br/>ex) omni.did                                                     |
| -si, --did-service-id     | string | DID 서비스 아이디 | M       |                                                                                     |
| -st, --did-service-type   | string | DID 서비스 타입  | M       | LinkedDomains: site URL<br/>CredentialRegistry: URL to query verifiable credentials |
| -sul, --did-service-url-list    | string | DID 서비스 url | M       | comma(,)로 구분하여 설정한다.<br/>ex) https://did.omnione.net, https://did.omnione.net2      |

### Command Declaration

```shell
# Command declaration in Shell
java -jar [모듈명] did addService -m [wallet명] -f [did명] -si [서비스아이디] -st [서비스타입] -sul [서비스url목록] -p
```

### Command Usage

```shell
java -jar did-cli-tool-server-1.0.0.jar did addService -m omni.wallet -f omni.did -si homepage -st LinkedDomains -sul https://did.omnione.net,https://did.omnione.net2 -p
```

### CLI Properties Usage

* CLI 옵션 값은 명령줄 입력 또는 properties 파일로 설정 가능합니다. 또 복합 사용 가능합니다.

* 동일한 옵션 값 존재 시, 명령줄 입력을 우선합니다.
  
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
Enter value for --wallet-manager-password (Wallet password): (비밀번호 입력)
== did add service call ==
Command:    did->AddService
dids : (Data Sample 참고)
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

## 9. DID 서비스 삭제

### Command

`did removeService`

### Command Introduction

`DID 문서의 서비스를 삭제합니다.`

### Command Options

| Option                    | Type   | Description | **M/O** | **비고**                                                                              |
| ------------------------- | ------ | ----------- | ------- | ----------------------------------------------------------------------------------- |
| -m, --wallet-manager    | string  | 월렛 파일 명     | M       | 확장자 포함하여 입력한다.<br/>ex) omni.wallet    |
| -p, --wallet-manager-password | string  | 월렛 비밀번호  | M    | 최초 파일 월렛 생성 시 사용자 지정 비밀번호를 입력해야 한다.   |
| -f, --did-file            | string | DID 파일 명      | M       | 확장자 포함하여 입력한다.<br/>ex) omni.did                                                     |
| -si, --did-service-id     | string | DID 서비스 아이디 | M       |                                                                                     |
| -st, --did-service-type   | string | DID 서비스 타입  | O       | LinkedDomains: site URL<br/>CredentialRegistry: URL to query verifiable credentials |
| -su, --did-service-url    | string | DID 서비스 url | O      | 하나의 url을 설정한다.<br/>ex) https://did.omnione.net                                      |

### Command Declaration

```shell
# Command declaration in Shell
java -jar [모듈명] did removeService -m [wallet명] -f [did명] -si [서비스아이디] -st [서비스타입] -su [서비스url] -p
```

### Command Usage

```shell
java -jar did-cli-tool-server-1.0.0.jar did removeService -m omni.wallet -f omni.did -si homepage -st LinkedDomains -su https://did.omnione.net -p
```

### CLI Properties Usage

* CLI 옵션 값은 명령줄 입력 또는 properties 파일로 설정 가능합니다. 또 복합 사용 가능합니다.

* 동일한 옵션 값 존재 시, 명령줄 입력을 우선합니다.
  
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
Enter value for --wallet-manager-password (Wallet password): (비밀번호 입력)
== did remove service call ==
Command:    did->RemoveService
dids : (Data Sample 참고)
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

## 10. DID 버전 아이디 조회

### Command

`did version`

### Command Introduction

`DID 문서의 버전 아이디를 조회합니다.`

### Command Options

| Option         | Type   | Description | **M/O** | **비고**                          |
| -------------- | ------ | ----------- | ------- | ------------------------------- |
| -f, --did-file | string | DID 파일 명     | M       | 확장자 포함하여 입력한다.<br/>ex) omni.did |

### Command Declaration

```shell
# Command declaration in Shell
java -jar [모듈명] did version -f [did명]
```

### Command Usage

```shell
java -jar did-cli-tool-server-1.0.0.jar did version -f omni.did
```

### CLI Properties Usage

* CLI 옵션 값은 명령줄 입력 또는 properties 파일로 설정 가능합니다. 또 복합 사용 가능합니다.

* 동일한 옵션 값 존재 시, 명령줄 입력을 우선합니다.
  
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

## 11. DID 버전 아이디 변경

### Command

`did updateVersion`

### Command Introduction

`DID 문서의 버전 아이디를 변경합니다`

### Command Options

| Option                    | Type   | Description | **M/O** | **비고**                              |
| ------------------------- | ------ | ----------- | ------- | ----------------------------------- |
| -m, --wallet-manager    | string  | 월렛 파일 명     | M       | 확장자 포함하여 입력한다.<br/>ex) omni.wallet    |
| -p, --wallet-manager-password | string  | 월렛 비밀번호  | M    | 최초 파일 월렛 생성 시 사용자 지정 비밀번호를 입력해야 한다.   |
| -f, --did-file            | string | DID 파일 명  | M       | 확장자 포함하여 입력한다.<br/>ex) omni.did     |
| -vi, --did-version-id     | int    | DID 버전 아이디  | M       | 1이상의 십진수로 입력한다.                     |

### Command Declaration

```shell
# Command declaration in Shell
java -jar [모듈명] did updateVersion -m [wallet명] -f [did명] -vi [버전아이디] -p
```

### Command Usage

```shell
java -jar did-cli-tool-server-1.0.0.jar did updateVersion -m omni.wallet -f omni.did -vi 2 -p
```

### CLI Properties Usage

* CLI 옵션 값은 명령줄 입력 또는 properties 파일로 설정 가능합니다. 또 복합 사용 가능합니다.

* 동일한 옵션 값 존재 시, 명령줄 입력을 우선합니다.
  
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
Enter value for --wallet-manager-password (Wallet password): (비밀번호 입력)
== did update version id call ==
Command:    did->UpdateVersion
dids : (Data Sample 참고)
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

## 12. Wallet, DID Document 자동 생성

### Command

`auto generator`

### Command Introduction

`Wallet, DID 문서 파일을 기본 키 정보로 자동 생성합니다.`<br>
`Wallet, DID 의 키는 기본적으로 "assert", "auth", "keyagree", "invoke" 가 설정됩니다.`

### Command Options

| Option                                  | Type    | Description   | **M/O** | **비고**                                                                           |
| --------------------------------------- | ------- | ------------- | ------- | -------------------------------------------------------------------------------- |
| -m, --wallet-manager    | string  | 월렛 파일 명     | M       | 확장자 포함하여 입력한다.<br/>ex) omni.wallet    |
| -p, --wallet-manager-password | string  | 월렛 비밀번호  | M    | 최초 파일 월렛 생성 시 사용자 지정 비밀번호를 입력해야 한다.   |
| -f, --did-file                          | string  | DID 파일 명      | M       | 확장자 포함하여 입력한다.<br/>ex) omni.did                                                  |
| -r, --file-remove                       | boolean | DID 파일 삭제 여부  | O       | -f 옵션으로 지정한 DID 파일이 존재하는 경우 파일의 삭제 옵션을 설정한다.                                     |
| -id, --did-id                           | string  | DID 아이디       | M       | ex) did:example:abcdefg1234567890                                                |
| -ci, --did-controller-id                | string  | DID 컨트롤러      | M       |                                                                                  |
| -si, --did-service-id     | string | DID 서비스 아이디 |O       |          |
| -st, --did-service-type   | string | DID 서비스 타입  | O      | LinkedDomains: site URL<br/>CredentialRegistry: URL to query verifiable credentials |
| -sul, --did-service-url-list    | string | DID 서비스 url | O      | comma(,)로 구분하여 설정한다.<br/>ex) https://did.omnione.net, https://did.omnione.net2      |


### Command Declaration

```shell
# Command declaration in Shell
java -jar [모듈명] auto generator -m [wallet명] -f [did명] -id [didId] -ci [contoller] -p
```

### Command Usage

```shell
java -jar did-cli-tool-server-1.0.0.jar auto generator -m omni.wallet -f omni.did -id did:omn:0123456789abcdef -ci controllerDid -p
```

### CLI Properties Usage

* CLI 옵션 값은 명령줄 입력 또는 properties 파일로 설정 가능합니다. 또 복합 사용 가능합니다.

* 동일한 옵션 값 존재 시, 명령줄 입력을 우선합니다.
  
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
Enter value for --wallet-manager-password (Wallet password): (비밀번호 입력)
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
dids : (Data Sample 참고)
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