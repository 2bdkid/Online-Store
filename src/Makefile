programs := $(wildcard client/admin/*.java) $(wildcard client/customer/*.java) server/StoreServer.java
classes  := $(programs:.java=.class)
out_classes := $(patsubst %, ../out/%, $(classes))

all: jar

%.class: %.java
	javac -d ../out/ $<

jar: $(out_classes)
	jar cmf META-INF/MANIFEST.MF StoreServer.jar -C ../out/ . server/adminaccounts.csv database/itemdatabase.csv

