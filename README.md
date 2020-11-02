# CSCI450 Software Engineering Assignment 2

Brady Dean

## StoreServer

StoreServer is the name my online store service.
The system consists of one server backend and many client programs.
Communication between the client programs and backend is done through Remote Method Invocation.

## Directory Structure Reference

```
images               - images used in the report
out                  - contains class files
src/client/admin     - administrator client programs
src/client/customer  - customer client programs
src/common           - interfaces and exceptions shared between client programs and server
src/database         - classes related to backend database
src/server           - classes related to server backend
src/META-INF         - contains StoreServer.jar manifest
src/Makefile         - builds StoreServer.jar
```

These files contain initial values:

```
src/server/adminaccounts.csv   - administrator accounts created when server starts
src/database/itemdatabase.csv  - database items created when server starts
```

## Building

```
make
```

## Running

StoreServer.jar will execute the server backend when ran without any arguments.
It also contains all the client programs.
StoreServer opens a RMI registry on port 33333.

Administator client programs:

- client.admin.RegisterAccount              - Register new administrator account
- client.admin.RegisterFromExistingAccount  - Register new administrator account through existing account
- client.admin.AddItem                      - Add new item to database
- client.admin.RemoveItem                   - Remove item from database
- client.admin.UpdateItemDescription        - Update item description
- client.admin.UpdateItemPrice              - Update item price
- client.admin.UpdateItemQuantity           - Update item quantity

Customer client programs;

- TODO

Server backend program:

- server.StoreServer (default)

To execute a client program, where PROGRAM is one of the above:

```
java -cp StoreServer.jar PROGRAM
```
