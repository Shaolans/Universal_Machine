#ifndef MACHINE_UTILS_H
#define MACHINE_UTILS_H
#include <stdint.h>

typedef uint32_t uint32;

enum {CONDITIONALMOVE, ARRAYINDEX, ARRAYAMENDMENT, ADDITION, MULTIPLICATION,
   DIVISION, NOT, HALT, ALLOCATION, ABANDONMENT, OUTPUT, INPUT, LOADPROGRAM,
    ORTHOGRAPHY};

extern uint32 indexcpt;

//structure gardant un tableau d'instructions et la taille en mémoire
typedef struct array{
  uint32 size;
  uint32 *platter;
} array;

//liste permettant de garder les index réutilisables
typedef struct freeindex{
  uint32 index;
  struct freeindex *next;
} freeindex;

array* loadFile(const char* filename);

freeindex* initFreeIndex();
void addFreeIndex(freeindex** fi, uint32 index);
uint32 getFreeIndex(freeindex** fi);
void freeFreeIndex(freeindex** fi);


array* initArray(uint32 size);
void freeArray(array *arr);





/*
  Implatation avec des listes mais complexite mediocres
typedef struct arrays{
  uint32 size;
  uint32 index;
  uint32 *array;
  struct arrays *next;
} arrays;

  Fonction sur la structure de liste
arrays* initArrays(uint32 *array);
uint32 addArray(arrays** array, freeindex** fi, uint32 size);
arrays* getArray(arrays** array, uint32 index);
void removeArray(arrays** array, freeindex** fi, uint32 index);
void freeArray(arrays** array);*/










#endif
