SampleHome
======

This is a sample plug-in for the Bukkit API. It requires a CraftBukkit server.
Bukkit can be found at [http://bukkit.org](http://bukkit.org) 

The goal of this sample plug-in is to provide an example for using the
Configuration API to 

This plug-in provides the ability for players to set a home for themselves,
and teleport themselves to that home.

```yaml
permissions:
  samplehome.*:
    description: gives all permissions of the SamplePlugin
    default: true
    children:
      samplehome.home:
        description: Gives permission to send themselves home.
      samplehome.set:
        description: Gives permission to set their a home.
      samplehome.clear:
        description: Give permission to clear their home.
```

Compilation
-----------

This plugin has a Maven 3 pom.xml and uses Maven to compile. Dependencies are 
therefore managed by Maven. You should be able to build it with Maven by running

    mvn package

a jar will be generated in the target folder. For those unfa1milliar with Maven
it is a build system, see http://maven.apache.org/ for more information.
