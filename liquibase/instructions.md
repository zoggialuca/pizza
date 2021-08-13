# How to run Liquibase

## MySQL

### Dependencies
Follow the instruction in the *Driver information* section of the following [link](https://docs.liquibase.com/workflows/database-setup-tutorials/mysql.html).

## Changelog file generation
Make sure the database already exists. For example, you can run the application once.  
Run `liquibase --changeLogFile=<changeLogFileName>.<databaseType>.<fileType> generateChangeLog` to generate the file.

Examples of `fileType` are `xml` or `sql`.  
An example of `databaseType` is `mysql`. You don't have to specify it if the `fileType` is `xml`.  
If the `liquibase` command is not in your `PATH` environment variable, prefix it with the absolute path. For example, in Windows it is `C:\Program Files\liquibase\`.

## After changelog file generation
You cannot generate the same changelog file multiple times.  
If you use liquibase, you should document the changes in the changelog file and then apply them to the DB.