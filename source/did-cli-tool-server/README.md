# Server CLI Tool Guide
This document is a guide to using the OpenDID Server CLI-Tool, which provides the ability to create and manage wallets and DID files required for Open DID.


## S/W Specifications
| Component | Requirement |
|------|----------------------------|
| Language  | Java 17|
| Build System  | Gradle 8.8 |

<br>

## Build Method
: Create a JAR file based on the build.gradle file of this SDK project.
1. Open the `build.gradle` file of your project and add a task from the configuration file as shown below.

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
2. Open the `Gradle` tab in IDE and run the project's `Task > Build > Clean and Build` task, or type `./gradlew clean build` in a terminal.
3. Once the execution is complete, the `did-cli-tool-server-1.0.0.jar` file will be generated in the `{projetPath}/build/libs/` folder.


<br>


## SDK Application Method
1. Open a terminal in the location of the module `did-cli-tool-server-1.0.0.jar` file.
2. Refer to CLI-Tool_SERVER_API.md and enter the command you want to use.


<br>

## API Specification
| Classification | API Document Link                                            |
|------|--------------------------------------------------------------|
| WalletManager  | [CLI-Tool_SERVER_API](../../docs/api/CLI-Tool_SERVER_API.md) |
| Did  | [CLI-Tool_SERVER_API](../../docs/api/CLI-Tool_SERVER_API.md) |
| Auto  | [CLI-Tool_SERVER_API](../../docs/api/CLI-Tool_SERVER_API.md) |

### WalletManager
WalletManager command provides the ability to create and store wallet files.
<br>The main features are as follows:

* <b>Create a wallet</b>: Create and save a wallet file.
* <b>Add wallet key</b>: Add a key to the wallet file.
* <b>Delete wallet key</b>: Delete a wallet key: Delete a key from the wallet file.
* <b>Lookup wallet keys</b>: Get a list of keys in the wallet file


### Did
Did command provides the ability to create and manage DID documents.
<br>The main features are as follows:

* <b>Create a DID</b>: Generate a new DID and save it to a file.
* <b>Add DID Document key</b>: Add a key to the DID Document.
* <b>Delete DID Document key</b>: Delete a key in the DID Document.
* <b>Add DID Document Service</b>: Add a service to the DID Document.
* <b>Delete DID Document Service</b>: Delete a service from a DID document.
* <b>Lookup DID Document Version</b>: Get the version of a DID document.
* <b>Update DID Document Version</b>: Update the version of a DID Document.

### Auto
Auto command provides the ability to automatically create and save Wallet and DID Document files with the primary key set.
<br>The main features are as follows:

* <b>Auto-generate Wallet, DID Document</b>: Create and save the wallet and DID Document file at once.