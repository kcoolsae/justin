# Bebras International Workshop Information System

(aka *Justin*)

Java web application based on the Play framework. Managed by SBT. Consists of the following
folders/subprojects

* `database` SQL scripts to build the database
* `db` Database tier of the application (Java)
* `webjar` Shared CSS and Javascript code, bundled in a webjar. Based on bootstrap 5.3
* `webapp` The web application proper (Java, Scala, Play framework)
* (`project` and `target` directories are part of the SBT build system)

### Prerequisites

For running the system
* PostgreSQL database server, version 15.10 or higher
* Java runtime environment, version 21 or higher
* Web server, e.g., Apache

For development
* All of the above, plus
* Java development environment, e.g., as integrated in IntelliJ IDEA
* SBT, version 1.10.1 or higher
* Bootstrap 5.3
* Several external libraries, as specified in the various `build.sbt` files. Most of these will
  be downloaded automatically by SBT, with the following exceptions:
  * `be.ugent.caagt:play-utils:1.1`
  * `be.ugent.caagt:daohelper:1.1.13`
  
  which are not yet publicly available and must be installed in your local repository. See below

### Additional libraries

The application uses two additional libraries (Play Utilities / DAO Helper) which are currently not (yet) directly
available but need to be built from source. The source for these libraries is available on `github.com`

* [Play Utilities](https://github.com/kcoolsae/play-utils)
* [DAO Helper](https://github.com/kcoolsae/daoHelper)

Both libraries can be built using the `sbt` build tool. In both cases, the Linux command

    sbt clean publishLocal
when executed from the top level project directory, builds the library and installs it in the local IVY repository, ready
for use by the present application.

### Deployment

**TODO**
