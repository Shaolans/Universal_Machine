#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "machine_utils.h"

#define DEFAULT_SIZE 8388608

uint32 registers[8] = {0};
uint32 indexcpt = 1;

int main(int argc, char **argv){
  if(argc != 2){
    printf("./universal_machine <file>\n");
    return 1;
  }

  uint32 collSize = DEFAULT_SIZE;

  array **collPlatters = (array **)calloc(collSize,sizeof(uint32));
  collPlatters[0] = loadFile(argv[1]);
  uint32 *zero = collPlatters[0]->platter;

  freeindex *initfi = NULL;
  freeindex **fi = &initfi;

  array* tmparr;
  uint32 tmpindex;
  char tmpchar;

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


    //printf("op: %d i: %d a: %d b: %d c: %d \n", op, i,a,b,c);fflush(stdout);
    switch(op){
      case CONDITIONALMOVE:
        if(registers[c]) registers[a] = registers[b];
        break;
      case ARRAYINDEX:
        tmparr = collPlatters[registers[b]];
        registers[a] = (tmparr->platter)[registers[c]];
        break;
      case ARRAYAMENDMENT:
        tmparr = collPlatters[registers[a]];
        (tmparr->platter)[registers[b]] = registers[c];
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
        freeFreeIndex(fi);
        uint k = 0;
        collPlatters[0]->platter = NULL;
        for(k = 0;k < indexcpt;k++){
          //printf("%d %d %d\n",k,collPlatters[k]->size,collPlatters[k]->platter[0]);fflush(stdout);
          if(collPlatters[k] != NULL){
            freeArray(collPlatters[k]);
          }
        }
        free(collPlatters);
        return 0;
      case ALLOCATION:
        tmpindex = getFreeIndex(fi);
        collPlatters[tmpindex] = initArray(registers[c]);
        registers[b] = tmpindex;
        break;
      case ABANDONMENT:
        freeArray(collPlatters[registers[c]]);
        collPlatters[registers[c]] = NULL;
        addFreeIndex(fi,registers[c]);
        break;
      case OUTPUT:
        putchar(registers[c]);
        break;
      case INPUT:
        tmpchar = getchar();
        if(tmpchar == EOF){
          registers[c] = 0xFFFFFFFF;
        }else{
          registers[c] = tmpchar;
        }
        break;
      case LOADPROGRAM:
        if(registers[b] != 0){
          free(zero);
          tmparr = collPlatters[registers[b]];
          zero = (uint32 *)calloc(tmparr->size,sizeof(uint32));
          memcpy(zero, tmparr->platter, tmparr->size*sizeof(uint32));
          collPlatters[0]->size = tmparr->size;
          collPlatters[0]->platter = zero;
        }
        pt = registers[c]-1;
        break;
      case ORTHOGRAPHY:
        registers[(word >> 25) & 0x7] = word & 0x01ffffff;
        break;
      default:
        fprintf(stderr,"UNKNOWN OPERATOR: %d\n",op);
        exit(0);
    }
    pt++;
  }
  return 0;
}
