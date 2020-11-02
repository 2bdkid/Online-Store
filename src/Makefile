programs := $(wildcard client/admin/*.java) $(wildcard client/customer/*.java) server/StoreServer.java

all: jar

classes: %(programs)
	javac -d ../out/ $^

jar: $(classes)
	jar cmf META-INF/MANIFEST.MF StoreServer.jar -C ../out/ . server/adminaccounts.csv database/itemdatabase.csv

