all: universal_machine

universal_machine: main.o machine.o
	gcc -O2 -g -Wall machine.o main.o -o universal_machine

main.o:  machine.o main.c
	gcc -O2 -g -Wall -c main.c -o main.o

machine.o: machine.h machine.c
	gcc -O2 -g -Wall -c machine.c -o machine.o


clean:
	rm -rdf *.o universal_machine
