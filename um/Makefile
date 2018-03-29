all: universal_machine

universal_machine: machine_main.o machine_utils.o
	gcc -O3 -g -Wall machine_utils.o machine_main.o -o universal_machine

machine_main.o:  machine_utils.o machine_main.c
	gcc -O3 -g -Wall -c machine_main.c -o machine_main.o

machine_utils.o: machine_utils.h machine_utils.c
	gcc -O3 -g -Wall -c machine_utils.c -o machine_utils.o


clean:
	rm -rdf *.o universal_machine
