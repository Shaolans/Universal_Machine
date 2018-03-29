#ifndef MACHINE_H
#define MACHINE_H
#include <stdint.h>

typedef uint32_t uint32;

enum {CONDITIONALMOVE, ARRAYINDEX, ARRAYAMENDMENT, ADDITION, MULTIPLICATION,
   DIVISION, NOT, HALT, ALLOCATION, ABANDONMENT, OUTPUT, INPUT, LOADPROGRAM,
    ORTHOGRAPHY};

extern uint32 indexcpt;

typedef struct arrays{
  uint32 size;
  uint32 index;
  uint32 *array;
  struct arrays *next;
} arrays;


typedef struct freeindex{
  uint32 index;
  struct freeindex *next;
} freeindex;

uint32* loadFile(const char* filename);

arrays* initArrays(uint32 *array);
uint32 addArray(arrays** array, freeindex** fi, uint32 size);
arrays* getArray(arrays** array, uint32 index);
void removeArray(arrays** array, freeindex** fi, uint32 index);
void freeArray(arrays** array);

freeindex* initFreeIndex();
void addFreeIndex(freeindex** fi, uint32 index);
uint32 getFreeIndex(freeindex** fi);
void freeFreeIndex(freeindex** fi);












#endif
