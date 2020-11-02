# CSCI450 Software Engineering Assignment 2

Brady Dean

## StoreServer

StoreServer is the name of my online store service.
The system consists of one server backend, and many client programs.
Client programs and the backend communicate through Remote Method Invocation.

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

The server backend and client programs are hard-coded to send/receive on the same RMI port.
The `changeport` target changes the hard-coded port across all files if needed.
The default port is 54321.

```
cd src
make changeport PORT=55555  # needed if port 54321 is in use on Tesla
make
```

## Running

`StoreServer.jar` contains the server backend and client programs.

The server backend starts when `StoreServer.jar` is ran with no arguments.
The server creates an RMI registry on localhost and binds to port 54321, unless changed above.
Client programs look for an RMI registry on in-csci-rrpc01, unless changed above.
Administrator client programs:

- `client.admin.RegisterAccount`              - Register new administrator account
- `client.admin.RegisterFromExistingAccount`  - Register new administrator account through existing account
- `client.admin.AddItem`                      - Add new item to database
- `client.admin.RemoveItem`                   - Remove item from database
- `client.admin.UpdateItemDescription`        - Update item description
- `client.admin.UpdateItemPrice`              - Update item price
- `client.admin.UpdateItemQuantity`           - Update item quantity

Customer client programs:

- TODO

Server backend program:

- `server.StoreServer` - Start server backend (default)

To execute a client program, where `PROGRAM` is one of the above:

```
java -cp StoreServer.jar PROGRAM
```
