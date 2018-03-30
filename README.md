# Universal Machine

## But
Ce projet à pour but, l'implantation de la machine universelle dont les spécifications
proviennent du concours de programmation ICFP de 2006.
Ce trouve aussi, une implantation d'un compilateur du langage S-UM vers le langage binaire comprit par la machine universelle.

## Compiler le projet
Le projet contient à la racine la machine virtuelle (universal_machine) et le compilateur S-UM (SUMCompiler.jar).
Cependant si vous souhaitez tout de même compiler les différents programmes,
suivez la description suivante.

### Compiler la machine universelle
Depuis la racine du projet:
1. cd um
2. make clean
3. make
La machine universelle se trouvera dans ./um

### Compiler le compilateur S-UM
1. cd sum/SUM
2. ant clean
3. ant jar
Le compilateur se trouvera sur le chemin ./sum/SUM/jar

## Executer le projet
## Executer la machine universelle
La machine universelle s'éxecute comme ceci:  
./universal_machine <input.file>  
Par exemple:  
Si vous êtes à la racine du projet vous pouvez éffectuer la commande suivante:  
./universal_machine ./um/ressources/sandmarks.umz  
ou  
./universal_machine ./tests/test-print/test-print.um  

## Compiler un fichier SUM vers le langage UM
Pour compiler un fichier, éffectuez cette commande:  
java -jar SUMCompiler.jar <input.sum> <output.um> [--verbose-asm || --verbose]  
Par exemple:  
Si vous êtes à la racine du projet vous pouvez éffectuer la commande suivante:  
java -jar SUMCompiler.jar ./tests/test-print/test-print.sum ./tests/test-print/test-print.um --verbose-asm  
ou  
java -jar SUMCompiler.jar ./tests/test-print/test-print.sum ./tests/test-print/test-print.um  
ou  
java -jar SUMCompiler.jar ./tests/test-print/test-print.sum ./tests/test-print/test-print.um --verbose  
