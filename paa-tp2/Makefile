SRC := src
BIN := bin
JARNAME := tp2.jar
TP2 := tp2
SCRIPTS := scripts

all: jar
	cp $(SCRIPTS)/$(TP2).sh $(TP2)
	chmod +x $(TP2)

jar: compile
	cd $(BIN); jar cfe ../$(JARNAME) org.danilofes.paa.tp2.Main *

compile:
	mkdir -p $(BIN)
	javac -cp $(SRC) -d $(BIN) $(SRC)/org/danilofes/paa/tp2/Main.java
	javac -cp $(SRC) -d $(BIN) $(SRC)/org/danilofes/paa/tp2/StatsMain.java

clean:
	rm -rf $(BIN) $(JARNAME) $(TP2)
