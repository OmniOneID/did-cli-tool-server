# Server CLI Tool Guide
본 문서는 OpenDID Server CLI-Tool 사용을 위한 가이드로, Open DID에 필요한 Wallet, DID 파일을 생성 관리하는 기능을 제공한다.

## S/W 사양
| 구분 | 내용                |
|------|----------------------------|
| Language  | Java 17|
| Build System  | Gradle 8.8 |

<br>

## 빌드 방법
: 본 SDK 프로젝트의 build.gradle 파일을 기반으로 JAR 파일을 생성한다.
1. 프로젝트의 `build.gradle` 파일을 열고 아래와 같은 구성파일의 태스크를 추가한다.
```groovy
plugins {
    id 'java'
    id 'com.github.johnrengelman.shadow' version '7.1.2'
}

repositories {
    mavenCentral()
    flatDir {
        dirs 'libs'
    }
}

group = 'org.omnione.did'

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation 'org.bouncycastle:bcprov-jdk18on:1.78.1'
    implementation 'com.fasterxml.jackson.core:jackson-databind:2.15.2'
    implementation group: 'info.picocli', name: 'picocli', version: '4.2.0'
    implementation group: 'com.google.code.gson', name: 'gson', version: '2.8.9'
    implementation 'org.hibernate.validator:hibernate-validator:7.0.0.Final'

    implementation files('libs/did-datamodel-sdk-server-1.0.0.jar')
    implementation files('libs/did-crypto-sdk-server-1.0.0.jar')
    implementation files('libs/did-core-sdk-server-1.0.0.jar')
    implementation files('libs/did-wallet-sdk-server-1.0.0.jar')
}

shadowJar {
    archiveBaseName.set('did-cli-tool-server')
    archiveVersion.set('1.0.0')
    archiveClassifier.set('')

    manifest {
        attributes(
                'Main-Class': 'org.omnione.did.cli.OmniCLI'
        )
    }
}

tasks.named('jar').configure {
    enabled = false
}

tasks.withType(JavaCompile) {
    options.encoding = 'UTF-8'
}

build {
    dependsOn shadowJar
}
```
2. IDE에서 `Gradle` 창을 열고, 프로젝트의 `Task > Build > Clean and Build` 태스크를 실행 또는 `./gradlew clean build` 를 터미널 창에 입력한다.
3. 실행이 완료되면 `{projetPath}/build/libs/` 폴더에 `did-cli-tool-server-1.0.0.jar` 파일이 생성된다.

<br>

## SDK 적용 방법
1. 빌드된 서버 모듈 `did-cli-tool-server-1.0.0.jar` 파일의 위치에서 새로운 터미널 창을 연다.
2. [CLI-Tool_SERVER_API.md](../../docs/api/CLI-Tool_SERVER_API_ko.md)를 참고하여 사용할 명령어를 입력한다.

<br>

## API 규격서
| 구분 | API 문서 Link                                                     |
|------|-----------------------------------------------------------------|
| WalletManager  | [CLI-Tool_SERVER_API](../../docs/api/CLI-Tool_SERVER_API_ko.md) |
| Did  | [CLI-Tool_SERVER_API](../../docs/api/CLI-Tool_SERVER_API_ko.md) |
| Auto  | [CLI-Tool_SERVER_API](../../docs/api/CLI-Tool_SERVER_API_ko.md) |

### WalletManager
WalletManager 명령어는 wallet 파일 생성하고 저장하는 기능을 제공한다.
<br>주요 기능은 다음과 같다:

* <b>월렛 생성</b>: 월렛 파일을 생성하고 저장한다.
* <b>월렛 키 추가</b>: 월렛 파일에 키를 추가한다.
* <b>월렛 키 삭제</b>: 월렛 파일에 키를 삭제한다.
* <b>월렛 키 조회</b>: 월렛 파일의 키 목록을 조회한다.

### Did
Did 명령어는 DID Document를 생성하고 관리하는 기능을 제공한다.<br>
주요 기능은 다음과 같다:

* <b>DID 생성</b>: 새로운 DID를 생성하여 파일로 저장한다.
* <b>DID Document 키 추가</b>: DID Document에 키를 추가한다.
* <b>DID Document 키 삭제</b>: DID Document의 키를 삭제한다.
* <b>DID Document 서비스 추가</b>: DID Document에 서비스를 추가한다.
* <b>DID Document 서비스 삭제</b>: DID Document의 서비스를 삭제한다.
* <b>DID Document 버전 조회</b>: DID Document의 버전을 조회한다.
* <b>DID Document 버전 변경</b>: DID Document의 버전을 변경한다.


### Auto
Auto 명령어는 자동으로 기본 키 설정하여 Wallet, DID Document 파일을 생성하고 저장하는 기능을 제공한다.<br>
주요 기능은 다음과 같다:

* <b>Auto-generate Wallet, DID Document</b>: 월렛과 DID Document 파일을 생성하고 저장한다.