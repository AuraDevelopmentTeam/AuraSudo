# [AuraSudo](https://github.com/AuraDevelopmentTeam/AuraSudo)

[![Build Status](https://gitlab.aura-dev.team/AuraDev/AuraSudo/badges/master/pipeline.svg)](https://gitlab.aura-dev.team/AuraDev/AuraSudo/pipelines)
[![Coverage Report](https://gitlab.aura-dev.team/AuraDev/AuraSudo/badges/master/coverage.svg)](https://gitlab.aura-dev.team/AuraDev/AuraSudo/pipelines)

A plugin that lets you switch into sudo mode, so you can still play on your server as normal.

## Downloads

You can download all builds from either:

- Maven:
  - Releases: https://maven.aura-dev.team/repository/auradev-releases/
  - Snapshots: https://maven.aura-dev.team/repository/auradev-snapshots/

## [Issue Reporting](https://github.com/AuraDevelopmentTeam/AuraSudo/issues)

If you found a bug or even are experiencing a crash please report it so we can fix it. Please check first if a bug report for the issue already
[exists](https://github.com/AuraDevelopmentTeam/AuraSudo/issues). If not just create a
[new issue](https://github.com/AuraDevelopmentTeam/AuraSudo/issues/new) and fill out the form.

Please include the following:

* Minecraft version
* AuraSudo version
* BungeeCord/Velocity/Sponge/Spigot version/build
* Versions of any mods/plugins potentially related to the issue
* Any relevant screenshots are greatly appreciated.
* For crashes:
  * Steps to reproduce
  * Latest log

*(When creating a new issue please follow the template)*

## [Feature Requests](https://github.com/AuraDevelopmentTeam/AuraSudo/issues)

If you want a new feature added, go ahead an open a [new issue](https://github.com/AuraDevelopmentTeam/AuraSudo/new), remove the existing form and describe your
feature the best you can. The more details you provide the easier it will be implementing it.  
You can also talk to us on [Discord](https://discord.me/bungeechat).

## Developing with our Plugin

So you want to add support or even develop an addon for our plugin then you can easily add it to your development environment! All releases get uploaded to my
maven repository. (Replace `{version}` with the appropriate version!)

### Maven

    <repositories>
        <repository>
            <id>AuraDevelopmentTeam/id>
            <url>https://maven.aura-dev.team/repository/auradev-releases/</url>
            <!--<url>https://maven.aura-dev.team/repository/auradev-snapshots/</url>-->
        </repository>
    </repositories>

    <dependencies>
        <dependency>
            <groupId>team.aura_dev.aurasudo.api</groupId>
            <artifactId>AuraSudo-api</artifactId>
            <version>{version}</version>
            <scope>shadow</scope>
        </dependency>
    </dependencies>

### Gradle

    repositories {
        maven {
            name "AuraDevelopmentTeam"
            url "https://maven.aura-dev.team/repository/auradev-releases/"
            // url "https://maven.aura-dev.team/repository/auradev-snapshots/"
        }
    }

    dependencies {
        shadow "team.aura_dev.aurasudo.api:AuraSudo-api:{version}"
    }

## Setting up a Workspace/Compiling from Source

* Clone: Clone the repository like this: `git clone --recursive https://github.com/AuraDevelopmentTeam/AuraSudo.git`
* IDE-Setup: Run `gradle` in the repository root: `./gradlew installLombok <eclipse|idea>`
* Build: Run `gradle` in the repository root: `./gradlew build`. The build will be in `build/libs`

## Development builds

Between each official release there are several bleeding edge development builds, which you can also use. But be aware that they might contain unfinished
features and therefore won't work properly.

You can find the builds here: https://gitlab.aura-dev.team/AuraDev/AuraSudo/pipelines

On the right is a download symbol, click that a dropdown will open. Select "build". Then you'll download a zip file containing all artifacts including the
plugin jar.

## Signing

### PGP Signing

All files will be signed with PGP.  
The public key to verify it can be found in `keys/publicKey.asc`. The signatures of the files will also be found in the maven.

### Jar Signing

All jars from all official download sources will be signed. The signature will always have a SHA-1 hash of `2238d4a92d81ab407741a2fdb741cebddfeacba6` and you
are free to verify it.

## License

AuraSudo is licensed under the [GNU General Public License v3.0](https://www.gnu.org/licenses/gpl-3.0.html)

## Random Quote

> If it doesn’t work, it doesn’t matter how fast it doesn’t work.
>
> -- <cite>Mich Ravera</cite>
