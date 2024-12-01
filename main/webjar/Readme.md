Rasbeb2 - webjar
===

Constructs the webjar with fonts and css for the `webapp` project. 
**Important** Not everything needed for building the webjar is stored in the repository - see below.

Bootstrap customization is done in the `scss` directory.

### Preliminary setup

To build the webjar you need the bootstrap distribution and additional the `sass` command. Both
can be installed using `npm`, as follows

From the `webjar` folder, do

    mkdir -p bootstrap 
    cd bootstrap
    npm install bootstrap@v5.3.3
    sudo npm install -g sass

To test, from the `webjar` folder, do

    ./customize-bootstrap.sh

This should generate the following files

    public/css/justin-bootstrap.min.css
    public/css/justin-bootstrap.min.css.map
    public/js/bootstrap.bundle.min.js

These are not stored in the repository, but will be put into the 
webjar.

### Build with sbt

The `Compile` task of SBT rebuilds the bootstrap CSS-files whenever
any source file is changed.

