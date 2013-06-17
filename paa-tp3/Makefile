SRC := src
BIN := bin
JARNAME := tp3.jar
TP3 := tp3
SCRIPTS := scripts

all: jar
	cp $(SCRIPTS)/$(TP3).sh $(TP3)
	chmod +x $(TP3)

jar: compile
	cd $(BIN); jar cfe ../$(JARNAME) org.danilofes.paa.tp3.Main *

compile:
	mkdir -p $(BIN)
	javac -cp $(SRC) -d $(BIN) $(SRC)/org/danilofes/paa/tp3/Main.java

clean:
	rm -rf $(BIN) $(JARNAME) $(TP3)
