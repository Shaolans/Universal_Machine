# Universal Machine

## But
Ce projet à pour but, l'implantation de la machine universelle dont les spécifications
proviennent du concours de programmation ICFP de 2006.
Ce trouve aussi, une implantation d'un compilateur du langage S-UM vers le langage binaire comprit par la machine universelle.

## Compiler le projet
Le projet contient à la racine la machine virtuelle et le compilateur S-UM.
Cependant si vous souhaitez tout de même compiler les différents programmes,
suivez la description suivante.

### Compiler la machine universelle
Depuis la racine du projet:
1. cd universal_machine
2. make clean
3. make
La machine universelle se trouvera dans ./um

### Compiler le compilateur S-UM
1. cd sum/SUM
2. ant clean
3. ant jar
Le compilateur se trouvera sur le chemin ./sum/SUM/jar
