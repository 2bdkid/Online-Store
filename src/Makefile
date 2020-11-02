clientprograms := $(wildcard client/admin/*.java) $(wildcard client/customer/*.java) 
serverprograms := server/StoreServer.java
programs := $(clientprograms) $(serverprograms)

.PHONY: all
all: StoreServer.jar

.PHONY: classes
classes: $(programs)
	@javac -d ../out/ $^

.PHONY: changeport
changeport:
	@for file in $(programs); do \
		sed -i -E 's_(//.*:)([0-9][0-9]*)(/admincontroller)_\1$(PORT)\3_' $$file ; \
	done

.PHONY: changeserverhost
changeserverhost:
	@for file in $(clientprograms); do \
		sed -i -E 's_(//)(.*)(:[0-9][0-9]*/admincontroller)_\1$(HOST)\3_' $$file ; \
	done

StoreServer.jar: classes
	@jar cmf META-INF/MANIFEST.MF StoreServer.jar -C ../out/ . server/adminaccounts.csv database/itemdatabase.csv

