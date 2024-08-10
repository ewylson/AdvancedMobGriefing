## What is AdvancedMobGriefing?

AdvancedMobGriefing is a plugin that was developed for a private Minecraft server for one purpose only - to disable griffing for certain mobs, not all mobs as the gamerule mobGriefing does.

Despite the fact that the plugin was developed for personal use, the decision was made to release it to the public.

The plugin _does not interrupt the entity explosion event, but only clears the list of blocks that the explosion should destroy_. This achieves the same effect as the normal `gamerule mobGriefing false` command, so that particles, sounds and other explosion effects are played correctly.

## Supported Versions and Platforms

AdvancedMobGriefing currently supports Minecraft Java Server v1.20.4 based on [Paper](https://papermc.io/) or [Spigot](https://www.spigotmc.org/wiki/about-spigot/).

> [!NOTE]
> The plugin should also work on newer versions of Minecraft, but it has not been tested on them. The plugin fully supports only version of the game 1.20.4

## How to use?

Players with operator permission can use the command: `/mobgriefing [mob type] [true|false]`

**Allowed mob types:**
- creeper;
- fireball;
- enderman;

> [!NOTE]
> Disable griefing for mob type 'fireball' allows you to disable destruction from ghasts, but also affects the functionality of otherwise obtained fireballs.

Also, after starting the server with the plugin installed, a plugin configuration file will be created, in which you can manually change the values:
```
can-grief:
  creeper: true
  fireball: true
  enderman: true
```

The configuration file can be found at the following path: `/plugins/AdvancedMobGriefing/config.yml`

The plugin uses only one permission - `amg.mobgriefing`. This permission is available by default only to users with operator rights.

## Project Settings

AdvancedMobGriefing uses Maven to handle dependencies and building.

The project requires Java 21 JDK or newer.

In order to access the Spigot API using Maven, developers need to add both of the following to the pom.xml:
1. The Spigot Maven repository.
2. The Spigot API artifact.

These additions are excerpted in the following code snippet:
```
<repositories>
    <!-- This adds the Spigot Maven repository to the build -->
    <repository>
        <id>spigot-repo</id>
        <url>https://hub.spigotmc.org/nexus/content/repositories/snapshots/</url>
    </repository>
</repositories>

<dependencies>
    <!--This adds the Spigot API artifact to the build -->
    <dependency>
           <groupId>org.spigotmc</groupId>
           <artifactId>spigot-api</artifactId>
           <version>1.20.4-R0.1-SNAPSHOT</version>
           <scope>provided</scope>
    </dependency>
</dependencies>
```

## License

AdvancedMobGriefing is licensed under the permissive MIT license. Please see [`LICENSE.txt`](https://github.com/Ev1ls0n/AdvancedMobGriefing/blob/main/LICENSE) for more info.
