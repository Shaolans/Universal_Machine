all: universal_machine

universal_machine: main.o machine.o
	gcc -pg -g -Wall machine.o main.o -o universal_machine

main.o:  machine.o main.c
	gcc -pg -g -Wall -c main.c -o main.o

machine.o: machine.h machine.c
	gcc -pg -g -Wall -c machine.c -o machine.o


clean:
	rm -rdf *.o universal_machine
