
<img src="./image/sorbonne.png" alt="sorbonne" style="width:200px;height:100px;">

<br/>

<center>

# Rapport partiel de Recherche Du Projet STL
### Sujet:Implantation d’ILP en ILP
#### Encadrant : Antoine Miné
#### Groupe:   
Zhengdao Yu,  
Mengxiao Li,  
Shiyao Chen


<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>

<br>
<br>
<br>
<br>


22/03/2024
</center>

<div STYLE="page-break-after: always;"></div>

## Table des Matières
  - [1 Introduction](#1-introduction)
  - [2 Travail attendu](#2-travail-attendu)
  - [3 Méthode  de Travail en Collaboration](#3-méthode--de-travail-en-collaboration)
  - [4 Presentation des tâches déjà réalisées](#4-presentation-des-tâches-déjà-réalisées)
      - [4.1 Départ du Projet](#41-départ-du-projet)
        - [4.1.1 AST](#411-ast)
        - [4.1.2 Tâche initiale](#412-tâche-initiale)
        - [4.1.3 Notion NULL](#413-notion-null)
        - [4.1.4 Notion Liste chainée](#414-notion-liste-chainée)
        - [Bibliothèque List Chainée](#bibliothèque-list-chainée)
        - [List Chainée implanté](#list-chainée-implanté)
        - [AST implanté en ILPML](#ast-implanté-en-ilpml)
      - [4.2 Tâche actuellement : un mécanisme capable de compiler plusieurs fichiers](#42-tâche-actuellement--un-mécanisme-capable-de-compiler-plusieurs-fichiers)
        - [4.2.1 Notion "Include"](#421-notion-include)
        - [4.2.2 Notre solution Pour "Include"](#422-notre-solution-pour-include)
        - [4.2.3 Avant de implementation](#423-avant-de-implementation)
        - [4.2.4 L'implementation de "Include"](#424-limplementation-de-include)
        - [4.2.5 À completer](#425-à-completer)
  - [5 Tâches restantes + Re-tro Planning](#5-tâches-restantes--re-tro-planning)
      - [5.1 Appliquer l'implementation de Notion include et mecanisme de compiler multiple fichier en ILPML](#51-appliquer-limplementation-de-notion-include-et-mecanisme-de-compiler-multiple-fichier-en-ilpml)
      - [5.2 Développer une méthode d’analyse syntaxique permettant de convertir un code source texte à la syntaxe ILPML en AST représenté en ILP.](#52-développer-une-méthode-danalyse-syntaxique-permettant-de-convertir-un-code-source-texte-à-la-syntaxe-ilpml-en-ast-représenté-en-ilp)
        - [5.2 Planning](#52-planning)
      - [5.3 Développer, au choix, un compilateur d’ILP vers C ou un interprète d’ILP, en ILP](#53-développer-au-choix-un-compilateur-dilp-vers-c-ou-un-interprète-dilp-en-ilp)
        - [5.3 Planning](#53-planning)
      - [5.4 Développer des tests pour valider la correction de  compilateur ou interprète](#54-développer-des-tests-pour-valider-la-correction-de--compilateur-ou-interprète)
        - [5.4 Planning](#54-planning)
      - [5.5 Rapport final et preparation de la soutenance](#55-rapport-final-et-preparation-de-la-soutenance)
        - [5.5 Planning](#55-planning)




<div STYLE="page-break-after: always;"></div>

## 1 Introduction  
<div style="text-indent: 2em;">
ILP est un petit langage dynamique, appartenant à la même catégorie que Python, avec des types de données de base (tels que les entiers et les chaînes), des opérations intégrées, des fonctions globales et de premier ordre, un système d'exceptions et un système d'objets basé sur les classes. 
Ce langage est stratifié en plusieurs niveaux, allant de ILP1 (sans fonctions, exceptions, ni classes) à ILP4 (implémentation complète).  

ILP possède un interpréteur écrit en Java, et un compilateur également écrit en Java mais lié à la bibliothèque d'exécution C pour génerer les code en C.

Notre projet vise à réimplémenter partiellement le langage ILP en utilisant ILP lui-même pour la réécriture. Par exemple, réécrire l'interpréteur ILP ou le compilateur qui compile ILP en C en utilisant le langage ILP. De plus, bien qu'ILP offre des fonctionnalités avancées, il est imparfait à certains égards, comme l'absence de structures de données complètes, un manque de mécanisme de gestion de fichiers, et des fonctionnalités de sortie plutôt limitées. Ainsi, l'un de nos objectifs est également de compléter autant que possible ces aspects.
</div>

<br/>
<br/>
<br/>

<div STYLE="page-break-after: always;"></div>

## 2 Travail attendu
- Développer en ILP une structure de données pour représenter l’AST du langage ILP.

- Développer en ILP, au fur et à mesure des besoins, une bibliothèque de structures de données générales (listes chaînées, tables d’association, etc.).

- Développer une méthode d’analyse syntaxique permettant de convertir un code source texte à la syntaxe ILPML en AST représenté en ILP.

- Développer, au choix, un compilateur d’ILP vers C ou un interprète d’ILP, en ILP

- Développer un mécanisme de gestion de programmes
multi-fichiers en ILP

- Développer des tests pour valider la correction du compilateur ou d l'interprète

<br/>
<br/>
<br/>

<div STYLE="page-break-after: always;"></div>

## 3 Méthode  de Travail en Collaboration
Pour mieux coordonner notre travail d'équipe et progresser plus efficacement dans notre projet, nous avons pris les mesures suivantes :

1. Nous avons créé un dépôt GitHub pour partager le code.
2. Nous avons un channel de discussion de groupe pour discuter des idées.
3. Avant de commencer une nouvelle tâche, nous attribuons les tâches dans le channel de discussion pour travailler en parallèle.
4. Organiser des réunions régulières (vendredi matin) avec M.Antoine Miné pour discuter de l'avancement du projet et résoudre les problèmes éventuels.

En mettant en place ces mesures, nous pouvons améliorer la coordination et l'efficacité de notre travail d'équipe et avancer plus rapidement dans notre projet.

<div STYLE="page-break-after: always;"></div>

## 4 Presentation des tâches déjà réalisées
#### 4.1 Départ du Projet
<div style="text-indent: 2em;">

##### 4.1.1 AST
Un __abrbre syntaxique abstrait (AST)__ est une structure de données arborescente représentant un programme. Chaque nœud interne représente une opération élémentaire du programme. Tels que if(else),while,class...


##### 4.1.2 Tâche initiale

Donc notre __tâche initiale__ consiste à trouver une structure de données pour représenter l'arbre AST dans le langage ILP, différente de la structure AST.class déjà existante implémentée en Java. Cette fois-ci, nous devons utiliser le langage ILP pour construire ces nœuds. 

##### 4.1.3 Notion NULL
ILP n'a pas non plus de notion de null.Donc tout d'abord, nous aimerions mentionner ici notre tentative concernant la notion de NULL. Nous l'avons donc considéré comme une classe vide pour l'implémenter :
```
class NULL extends Object {}
```
Et ensuite,avec le conseil de M.Antoine Miné,nous pourrons réduire le test de nullité à un test de class:
```
function is_null(x) type_of(x) == "NULL"
```
  
##### 4.1.4 Notion Liste chainée

<div style="text-indent: 2em;">

#####  Bibliothèque List Chainée

Une liste chaînée (Linked List) est une structure de données fondamentale qui organise les données sous forme de séquence de nœuds (Node). Chaque nœud contient deux parties : l'une pour stocker les données (telles que des entiers, des chaînes de caractères, etc.), et l'autre pour stocker une référence (ou pointeur) vers le nœud suivant de la séquence. À la fin de la liste chaînée, cette référence pointe vers une valeur nulle, indiquant qu'il n'y a plus de nœuds.

#####  List Chainée implanté

Lors de l'implémentation de ces structures AST, nous avons rencontré un nouveau problème : ILP ne dispose pas d'une structure de données de type liste chaînée.

Par exemple, pour des nœuds tels que ASTblock, ASTsequence, lorsque les paramètres dont ils ont besoin sont un tableau, en Java, nous pouvons simplement utiliser arguments[] pour les implémenter. Mais cela n'est pas possible dans ILP. Par conséquent, notre prochaine tâche consiste à développer une structure de données similaire à une liste chaînée.

</div>
Ici,dans la class du ASTsequence en Java,il existe une structure de tableau utilisé pour représenter la liste des instructions dans une séquence d'instructions:

```
public class ASTsequence extends ASTexpression implements IASTsequence {
    public ASTsequence (IASTexpression[] expressions) {
        this.expressions = expressions;
    }
    protected IASTexpression[] expressions;
    @Override
	public IASTexpression[] getExpressions() {
        return this.expressions;
    }
```
La structure de liste chaînée que nous avons implémentée dans ILP :
```
class ListNode extends Object {
    var value;  
    var next;   // pointeur type: ListNode

    method getValue() 
     self.value;
    method getNext() 
     self.next;
    method setValue(v) 
     self.value = v;
    method setNext(v) 
     self.next = v;
}
```
```
class List extends Object{
    var head; //pointeur type: ListNode
    // ajouter valeur a la fin du list
    method add(value) 
        if  type_of(self.head) == "NULL"  then 
            self.head = new ListNode(value, null = new NULL())
         else 
            let current = self.head in
            let bloc = 
            while (type_of(current.getNext()) != "NULL") do
                current = current.getNext()
                in
            current.setNext(new ListNode(value, null))
        ;
}
```
Nous avons également écrit quelques tests pour vérifier si la liste chaînée fonctionne correctement pour ces ASTs:
```
class NULL extends Object {}

class ListNode extends Object {
    var value;  
    var next;   // pointeur type: ListNode
    ........
}

class List extends Object{
    var head; //pointeur type: ListNode
    .....
}

let node = new ListNode(10,new NULL()) in
   list = new List(node) ;
   list.add(5);
  //list.head.value
  list.head.next.value
```

##### AST implanté en ILPML

En utilisant le concept de classe déjà implémenté dans ILP4,la notion "Null" et la liste chainée que nous avons implantés, nous pouvons utiliser des classes pour construire des nœuds AST dans ILP

Finalement, nous avons réussi à construire tous les nœuds AST basés du ILP1 en utilisant ILP lui-même

</div>
L'exemple AST et ASTexpression

```
class AST { }
```
```
class ASTexpression extends AST { }
```
Dans cet exemple, "ast" et "ast expression" sont deux classes vides, mais elles sont extrêmement importantes car elles servent de point de départ et de base pour la construction de l'ensemble de l'arbre syntaxique abstrait (AST). De nombreuses autres classes AST suivantes, telles que "ast alternative" et "ast block", par exemple : `(if expression else expression) = expression`, sont toutes basées sur "ast expression".

Voici quelques exemples d'AST que nous avons implantés en ILPML:
Exemple de AST (if_else_)
```
ASTalternative:
class ASTalternative extends ASTexpression {
    var condition;
    var consequence;
    var alternant;

    method isTernary () 
    type_of(self.alternant) != "NULL";
}
```
Exemple de AST (operation d'arité 1)
```
class ASTunaryOperation extends ASTexpression{
    var operator;
    var operand;

	method getOperands() 
     let node = new ListNode(self.getOperand(),new NULL()) in
        let list = new List(node) in
        list;
}
```

#### 4.2 Tâche actuellement : un mécanisme capable de compiler plusieurs fichiers 

Maintenant que nous avons la structure de liste chaînée,la class NULL,des structures AST.  
Mais nous ne voulons pas mettre tout le code dans un unique fichier,c'est lourd.Nous voulons le séparer en plusieurs fichiers.Cela rend le code plus maintenable et plus réutilisable.
Cela introduit __une notion de "bibliothèque"__.
Ce concept est courant dans les langages de programmation modernes populaires, comme les modules en JavaScript, les packages en Java et les "include" en C.

Dans ce projet, notre solution est inspirée par "Include" dans C.

##### 4.2.1 Notion "Include"
Dans le langage C, "include" est une opération statique qui se produit avant la compilation du fichier. Le compilateur remplace le fichier inclus par son contenu textuel interne.

##### 4.2.2 Notre solution Pour "Include"
Dans notre projet, nous nous sommes inspirés de la fonctionnalité "include" et avons utilisé le compilateur de syntaxe ANTLR pour mettre en œuvre une fonctionnalité similaire :

* Nous avons ajouté un mot-clé "Include" dans la grammaire pour inclure un chemin.
* Nous utilisons ANTLR pour analyser le contenu textuel du fichier inclus (code) - En fonction de Open/Read fichier.
* Le fichier traité par ANTLR renvoie un arbre de syntaxe abstraite (AST) représentant le programme.
* Après avoir traité tous les "include" et le texte actuel, nous obtenons une liste d'ASTprogramme.
* Maintenant que nous avons une liste d'ASTprogram, notre tâche n'est pas encore terminée. Nous devons fusionner ces ASTprograms en un seul ASTprogram, de sorte que notre compilateur/interpréteur n'ait à traiter qu'un seul astprogram, ce qui équivaudrait à traiter le code de plusieurs fichiers précédents.

##### 4.2.3 Avant de implementation
Pour introduire le mécanisme de "Include", et conformément à la suggestion de Monsieur Antoine Mine, nous avons trouvé que c'est important et utile de supprimer une limitation existante dans ILP. Dans ILP, il y avait une contrainte sur l'ordre des définitions globales (fonctions globales, classes globales) et des expressions, à savoir que dans un programme, les définitions globales étaient toujours en haut du programme, suivies de diverses expressions.

Cependant, si nous voulons introduire le mécanisme d'inclusion, il est très utile de supprimer cette limitation. Parce que,en effet, après l'utilisation d'inclusion, il se peut que nous ayons des situations où les définitions globales du programme 1, les expressions du programme 1, les définitions globales du programme 2 et les expressions du programme 2 soient mélangées.

Nous avons réussi à supprimer cette limitation en modifiant la grammaire existante et en construisant l'AST correspondant. La clé consistait à ajouter "ou" "|" dans la grammaire existante.

grammaire actuelle：
```
prog returns [com.paracamplus.ilp4.interfaces.IASTprogram node] 
    : (elements_globalDef+=globalDef ';'? | elements_expr+= expr ';'?)* EOF ;
```

##### 4.2.4 L'implementation de "Include"
Un aperçu de grammaire:
```
globalDef returns [com.paracamplus.ilp2.interfaces.IASTdeclaration node]
    : def=includeDef #IncludeDefinition
    | def=globalFunDef # GlobalFunctionDefinition
    | def=classDef # ClassDefinition
    ;
includeDef returns [com.paracamplus.pstl.interfaces.IASTincludeDefinition node]
    :'include' body = STRING
    ;
```
Un aperçu de parser des AST:
```
@Override
public void exitIncludeDef(IncludeDefContext ctx) {
String filepath = ctx.body.getText();
filepath = filepath.substring(1, filepath.length() - 1);
ctx.node = factory.newIncludeDefinition(filepath);
}
```
Un aperçu de implementation en JAVA:

```
public Object visit(IASTincludeDefinition iast, ILexicalEnvironment lexenv)  {
		String filepath = iast.getFilepath();
		IncludeHandler handler = new IncludeHandler(factory);
        ...
		String content = readFichier.readIncludeFileContent(filepath);
		includeProgram = (IASTprogram) handler.parseIncludeContent(content);		
        ...
		return includeProgram;
	}
```
##### 4.2.5 À completer
Cependant, notre travail n'est pas encore terminé. Comme mentionné dans l'introduction, notre tâche consiste à réécrire l'implémentation en utilisant le langage ILPML lui-même, c'est-à-dire que nous devons encore convertir l'implémentation actuelle en Java pour la mettre en ILPML. C'est ce que nous devrons réaliser dans les projets suivants.

<div style="page-break-after: always;"></div>

## 5 Tâches restantes + Re-tro Planning
#### 5.1 Appliquer l'implementation de Notion include et mecanisme de compiler multiple fichier en ILPML
 Comme nous l'avons introduit dans la troisième partie, nous devons appliquer la logique d'inclusion dans ILPML. Nous n'allons pas répéter l'explication ici.
  
#### 5.2 Développer une méthode d’analyse syntaxique permettant de convertir un code source texte à la syntaxe ILPML en AST représenté en ILP.
 Pour mettre en œuvre une méthode d'analyse syntaxique permettant de convertir un code source texte en syntaxe ILPML en AST représenté en ILP, nous avions initialement trois options: 
 - La première consistait à écrire directement en ILP un analyseur syntaxique de type "récursif descendant". 
 - La deuxième solution était de développer un "backend" pour ANTLR. 
 - Enfin, la troisième solution, moins élégante, consistait à écrire en Java un convertisseur d'AST Java déjà construit en AST en ILP.

Après avoir travaillé sur ce projet pendant un certain temps, nous avons décidé d'utiliser la troisième méthode, à savoir écrire une version Java d'un convertisseur, qui convertira la structure AST Java en la structure AST ILPML correspondante.

Pour l'instant, cette solution semble être la plus réalisable et la plus claire pour nous. Notre idée initiale est d'écrire un visiteur qui parcourt chaque AST Java et émet simultanément le code AST ILPML correspondant.

##### 5.2 Planning
Nous prévoyons que le convertisseur d'AST Java sera la tâche la plus difficile. Nous allouerons donc au moins trois semaines pour terminer le convertisseur et les inclusions connexes.
__À completer avant la fin de la Premiere Semaine de Printemps__

#### 5.3 Développer, au choix, un compilateur d’ILP vers C ou un interprète d’ILP, en ILP
De même, après cette période d'apprentissage sur le projet, nous pensons qu'il est plus réalisable de choisir de développer un compilateur d'ILP vers C plutôt qu'un interprète ILP en ILP. Voici nos raisons :

- Dans l'ILP existant, il existe déjà une bibliothèque C pertinente, nous pouvons donc réutiliser directement ce code.
- Comme pour la tâche 4.3, nous avons déjà une idée préliminaire, à savoir un visiteur supplémentaire pour émettre le code C.
- De plus,la cible de compilation est le langage C ici,qui est de relativement plus haut niveau que l'assembleur,ce qui facilite beaucoup la generation de code.C'est donc notre meilleur choix.

##### 5.3 Planning
Nous prévoyons d'utiliser une à deux semaines pour terminer le compilateur.

__À completer avant la fin de la Semaine de Rentreé__

#### 5.4 Développer des tests pour valider la correction de  compilateur ou interprète

##### 5.4 Planning
Nous prévoyons d'utiliser deux semaines pour tester l'ensemble du projet.

__À completer avant la semaine de examen__

#### 5.5 Rapport final et preparation de la soutenance
##### 5.5 Planning
Enfin,nous prévoyons d'utiliser le temps restant pour compléter le rapport final et preparer notre soutenance
__À completer avant le rendu final et soutenance__

Merci de votre attention!