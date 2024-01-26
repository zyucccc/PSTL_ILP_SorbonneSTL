# PSTL_ILP_SorbonneSTL

##Objectifs
L’objectif de ce projet est une réimplantation (partielle) du langage ILP utilisé comme support dans le
cours DLP de Mater 1 STL, dans le langage ILP lui-même. Il s’agira en particulier de reprogrammer en ILP,
soit un interprète d’ILP, soit un compilateur d’ILP vers C, tous deux actuellement programmés en Java.

##Contexte détaillé
Le langage ILP, développé dans le cours DLP de Master 1 STL, est un petit langage dynamique, de la
classe de Python ou JavaScript. Il comporte quelques types de base (entiers, chaînes), des opérations « builtin » sur ces valeurs, des fonctions globales et de première classe, un système d’exceptions et un système
d’objets basé sur des classes. Le langage a plusieurs niveaux, d’ILP1 (ne supportant pas les fonctions, les
exceptions ni les classes), à ILP4 (implantation complète). ILP possède un interprète, écrit en Java, et
un compilateur vers C écrit également en Java et associé à une bibliothèque d’exécution écrite en C. ILP
comporte également un analyseur syntaxique généré en Java par ANTLR 4 et construisant une structure
d’arbre syntaxique abstrait Java prise en entrée par l’interprète et le compilateur.
Le but du projet est de réécrire en ILP, soit l’interprète ILP, soit le compilateur ILP vers C. Dans le cas
de la compilation, il sera possible de générer du code C qui se lie à la bibliothèque d’exécution actuelle, sans
devoir la changer. Seule la partie écrite en Java doit donc être convertie en ILP.
Malgré ses fonctionnalités de haut niveau, ILP reste un langage pauvre en terme de types de données et,
surtout, de bibliothèques. Par exemple : il n’y a pas de gestion des fichiers, les entrées-sorties sont limitées
à print, la seule opération manipulant les chaînes est la concaténation, il n’y a pas de support pour les
tableaux, listes, ou tables d’association, etc. Par ailleurs, ILP n’a pas de support pour les programmes multifichiers, les modules, les packages, les interfaces. Ceci limite la programmation en ILP et le développement
d’applications réalistes.
Au fur et à mesure, le projet mettra au jour les insuffisances du langage pour la tâche demandée. Il sera
alors nécessaire de les résoudre en étendant le langage (types de données, opérateurs, etc.). On prendra soin
de se limiter aux ajouts strictement nécessaires : toute partie pouvant être raisonnablement écrite en ILP
devra l’être, en recourant le moins possible à la programmation Java ou C.
