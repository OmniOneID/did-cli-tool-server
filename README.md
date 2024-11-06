# Server CLI-Tool

Welcome to the Server CLI-Tool Repository.<br>
This repository provides an SDK for generating a server wallet and DID document.

## Folder Structure
```
did-cli-tool-server
├── CHANGELOG.md
├── CLA.md
├── CODE_OF_CONDUCT.md
├── CONTRIBUTING.md
├── LICENSE
├── dependencies-license.md
├── MAINTAINERS.md
├── README.md
├── README_ko.md
├── RELEASE-PROCESS.md
├── SECURITY.md
├── docs
│   └── api
│       ├── CLI-Tool_SERVER_API.md
│       └── CLI-Tool_SERVER_API_ko.md
└── source
    ├── did-cli-tool-server
    │   ├── .gitignore
    │   ├── build.gradle
    │   ├── gradle
    │   │    └── wrapper
    │   ├── gradlew
    │   ├── gradlew.bat
    │   ├── libs
    │   ├── README_ko.md
    │   ├── README.md
    │   ├── settings.gradle
    │   └── src
    └── releases
        └── did-cli-tool-server-1.0.0.jar
```

| Name                    | Description                                     |
|-------------------------|-------------------------------------------------|
| source                  | SDK source code project                         |
| docs                    | Documentation                                   |
| ┖ api                   | API guide documentation                         |
| README.md               | Overview and description of the project         |
| CLA.md                  | Contributor License Agreement                   |
| CHANGELOG.md            | Version-specific changes in the project         |
| CODE_OF_CONDUCT.md      | Code of conduct for contributors                |
| CONTRIBUTING.md         | Contribution guidelines and procedures          |
| LICENSE                 | Apache 2.0                                      |
| dependencies-license.md | Licenses for the project’s dependency libraries |
| MAINTAINERS.md          | General guidelines for maintaining              |
| RELEASE-PROCESS.md      | Release process                                 |
| SECURITY.md             | Security policies and vulnerability reporting   |

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

## Libraries

Libraries can be found in the [Releases](https://github.com/OmniOneID/did-cli-tool-server/releases).

### Cli-Tool
1. Copy each of the files below to the libs in your server project.
   <br> - `did-datamodel-server-1.0.0.jar`
   <br> - `did-crypto-sdk-server-1.0.0.jar`
   <br> - `did-core-sdk-server-1.0.0.jar`
   <br> - `did-wallet-sdk-server-1.0.0.jar`

2. Add the following dependencies to the build.gradle of the server project.

```groovy
   implementation 'org.bouncycastle:bcprov-jdk18on:1.78.1'
   implementation 'com.fasterxml.jackson.core:jackson-databind:2.15.2'
   implementation group: 'info.picocli', name: 'picocli', version: '4.2.0'
   implementation group: 'com.google.code.gson', name: 'gson', version: '2.8.9'
   implementation 'org.hibernate.validator:hibernate-validator:7.0.0.Final'

   implementation files('libs/did-datamodel-sdk-server-1.0.0.jar')
   implementation files('libs/did-crypto-sdk-server-1.0.0.jar')
   implementation files('libs/did-core-sdk-server-1.0.0.jar')
   implementation files('libs/did-wallet-sdk-server-1.0.0.jar')
```
3. Sync `Gradle` to ensure the dependencies are properly added.

## API Reference

API Reference can be found [here](docs/api/CLI-Tool_SERVER_API.md)

## Change Log

ChangeLog can be found :
<br>
- [Change Log](CHANGELOG.md)

## OpenDID Demonstration Videos <br>
To watch our demonstration videos of the OpenDID system in action, please visit our [Demo Repository](https://github.com/OmniOneID/did-demo-server). <br>

These videos showcase key features including user registration, VC issuance, and VP submission processes.

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) and [CODE_OF_CONDUCT.md](CODE_OF_CONDUCT.md) for details on our code of conduct, and the process for submitting pull requests to us.


## License
[Apache 2.0](LICENSE)