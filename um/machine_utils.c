#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include "machine_utils.h"


array* loadFile(const char* filename){
  struct stat info;
  long size;

  //recuperation de la taille du fichier et le nombre d'instructions
  stat(filename, &info);
  size = info.st_size;
  uint32 nb_inst = size/4;

  array* tmp = initArray(nb_inst);
  uint32 *tab0 = tmp->platter;

  //Recopie du contenu binaire du fichier dans le tableau 0
  FILE *f = fopen(filename,"rb");
  fseek(f,0,SEEK_SET);
  fread(tab0,sizeof(uint32),nb_inst,f);


  //inversement de l'endianess (sandmark)
  uint32 i = 0;
  for(i = 0; i < nb_inst; i++){
    tab0[i] = (tab0[i]>>24) | ((tab0[i]<<8)&0xff0000) | ((tab0[i]>>8)&0xff00) | (tab0[i]<<24);
  }
  fclose(f);
  return tmp;
}


array* initArray(uint32 size){
  array *tmp = (array *)malloc(sizeof(array));
  tmp->size = size;
  tmp->platter = (uint32 *)calloc(size,sizeof(uint32));
  return tmp;
}


void freeArray(array *arr){
  if(arr == NULL) return;
  if(arr->platter != NULL){
    free(arr->platter);
  }
  free(arr);
}



freeindex* initFreeIndex(){
  freeindex *tmp = (freeindex *)malloc(sizeof(freeindex));
  tmp->index = 1;
  tmp->next = NULL;
  return tmp;
}


void addFreeIndex(freeindex** fi, uint32 index){
  freeindex *tmp = (freeindex *)malloc(sizeof(freeindex));
  tmp->index = index;
  tmp->next = *fi;
  *fi = tmp;
}

uint32 getFreeIndex(freeindex** fi){
  if(*fi == NULL) return indexcpt++;
  uint32 index = (*fi)->index;
  freeindex *tmp = *fi;
  *fi = (*fi)->next;
  free(tmp);
  return index;
}

void freeFreeIndex(freeindex** fi){
  freeindex *pt = *fi;
  freeindex *tmp = NULL;
  while(pt != NULL){
    tmp = pt->next;
    free(pt);
    pt = tmp;
  }
}


/*
arrays *initArrays(uint32 *array){
  arrays *pt = (arrays*)malloc(sizeof(arrays));
  pt->size = 0;
  pt->index = 0;
  pt->array = array;
  pt->next = NULL;
  return pt;
}


uint32 addArray(arrays** array, freeindex** fi, uint32 size){
  arrays *new = (arrays *)malloc(sizeof(arrays));
  new->size = size;
  new->index = getFreeIndex(fi);
  new->array = (uint32 *)calloc(size,sizeof(uint32));
  new->next = *array;
  *array = new;
  return new->index;
}


arrays* getArray(arrays** array, uint32 index){
  arrays *pt = *array;
  while(pt != NULL && pt->index != index){
    pt = pt->next;
  }
  return pt;

}

void removeArray(arrays** array, freeindex** fi, uint32 index){
  arrays *pt = *array;
  arrays *tmp = pt;
  addFreeIndex(fi, index);
  while(pt != NULL && pt->index != index){
    tmp = pt;
    pt = pt->next;
  }
  if(pt == tmp){
    free(pt->array);
    free(pt);
    *array = tmp->next;
    return;
  }
  tmp->next = pt->next;
  free(pt->array);
  free(pt);
}

void freeArray(arrays** array){
  arrays *pt = *array;
  arrays *tmp;
  while(pt != NULL){
    tmp = pt->next;
    free(pt->array);
    free(pt);
    pt = tmp;
  }
}*/
