SRC := src
BIN := bin
JARNAME := tp1.jar
TP1B := tp1b
TP1PD := tp1pd
TP1G := tp1g
SCRIPTS := scripts

all: brute dynamic greedy

brute: jar
	cp $(SCRIPTS)/$(TP1B).sh $(TP1B)
	chmod +x $(TP1B)

dynamic: jar
	cp $(SCRIPTS)/$(TP1PD).sh $(TP1PD)
	chmod +x $(TP1PD)

greedy: jar
	cp $(SCRIPTS)/$(TP1G).sh $(TP1G)
	chmod +x $(TP1G)

jar: compile
	cd $(BIN); jar cfe ../$(JARNAME) org.danilofes.paa.tp1.Main *

compile:
	mkdir -p $(BIN)
	javac -cp $(SRC) -d $(BIN) $(SRC)/org/danilofes/paa/tp1/Main.java

clean:
	rm -rf $(BIN) $(JARNAME) $(TP1B) $(TP1PD) $(TP1G)
