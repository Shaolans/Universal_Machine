#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "machine.h"


uint32 registers[8] = {0};
uint32 indexcpt = 2;

int main(int argc, char **argv){
  if(argc != 2){
    printf("Il faut donner un fichier\n");
    return 1;
  }
  uint32 *zero = loadFile(argv[1]);
  freeindex *initfi = initFreeIndex();
  freeindex **fi = &initfi;
  arrays *initar = initArrays(zero);
  arrays **array = &initar;
  uint32 *tarray = NULL;
  uint32 a,b,c;
  uint32 op;
  uint32 pt = 0;
  uint32 word;
  while(1){

    word = zero[pt];
    op = word>>28;
    a = ((word>>6) & 0x7);
    b = ((word>>3) & 0x7);
    c = (word & 0x7);

    /*
    arrays *test;uint32 l = 0;
    test = *array;
    l = 0;
    while(test != NULL){
      l++;
      test = test->next;
    }
    printf("%d \n",l);*/

    //printf("op: %d i: %d a: %d b: %d c: %d \n", op, i,a,b,c);fflush(stdout);
    switch(op){
      case CONDITIONALMOVE:
        if(registers[c]) registers[a] = registers[b];
        break;
      case ARRAYINDEX:
        tarray = getArray(array,registers[b])->array;
        registers[a] = tarray[registers[c]];
        break;
      case ARRAYAMENDMENT:
        tarray = getArray(array,registers[a])->array;
        tarray[registers[b]] = registers[c];
        break;
      case ADDITION:
        registers[a] = registers[b] + registers[c];
        break;
      case MULTIPLICATION:
        registers[a] = registers[b] * registers[c];
        break;
      case DIVISION:
        registers[a] = registers[b] / registers[c];
        break;
      case NOT:
        registers[a] = ~(registers[b] & registers[c]);
        break;
      case HALT:
        return 0;
      case ALLOCATION:
        registers[b] = addArray(array,fi,registers[c]);
        break;
      case ABANDONMENT:
        removeArray(array,fi,registers[c]);
        break;
      case OUTPUT:
        putchar(registers[c]);
        break;
      case INPUT:
        registers[c] = getchar();
        break;
      case LOADPROGRAM:
        if(registers[b] != 0){
          free(zero);
          arrays *tmp = getArray(array,registers[b]);
          zero = (uint32 *)calloc(tmp->size,sizeof(uint32));
          memcpy(zero, tmp->array, tmp->size*sizeof(uint32));
          getArray(array,0)->array = zero;
        }
        pt = registers[c]-1;
        break;
      case ORTHOGRAPHY:
        registers[(word >> 25) & 0x7] = word & 0x01ffffff;
        break;
    }
    pt++;
  }
  return 0;
}
