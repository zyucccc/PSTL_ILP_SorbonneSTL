
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

- [Rapport partiel de Recherche Du Projet STL](#rapport-partiel-de-recherche-du-projet-stl)
    - [Sujet:Implantation d’ILP en ILP](#sujetimplantation-dilp-en-ilp)
      - [Encadrant : Antoine Miné](#encadrant--antoine-miné)
      - [Groupe:](#groupe)
  - [Table des Matières](#table-des-matières)
  - [Introduction](#introduction)
  - [Travail attendu](#travail-attendu)
  - [Presentation des tâches déjà réalisées](#presentation-des-tâches-déjà-réalisées)
      - [Départ du Projet](#départ-du-projet)
      - [Nouveau problème : Les contraintes de la structure de données-\>Liste Chainée](#nouveau-problème--les-contraintes-de-la-structure-de-données-liste-chainée)
      - [La partie que nous travaillons actuellement : un mécanisme capable de compiler plusieurs fichiers =\> La notion "Include"](#la-partie-que-nous-travaillons-actuellement--un-mécanisme-capable-de-compiler-plusieurs-fichiers--la-notion-include)
  - [Tâches restantes](#tâches-restantes)


<div STYLE="page-break-after: always;"></div>

## Introduction  
<div style="text-indent: 2em;">
ILP est un petit langage dynamique, appartenant à la même catégorie que Python, avec des types de données de base (tels que les entiers et les chaînes), des opérations intégrées, des fonctions globales et de premier ordre, un système d'exceptions et un système d'objets basé sur les classes. 

Ce langage est stratifié en plusieurs niveaux, allant de ILP1 (sans fonctions, exceptions, ni classes) à ILP4 (implémentation complète). ILP possède un interpréteur écrit en Java, et un compilateur également écrit en Java mais lié à la bibliothèque d'exécution C. 

Notre projet vise à réimplémenter partiellement le langage ILP en utilisant ILP lui-même pour la réécriture. Par exemple, réécrire l'interpréteur ILP ou le compilateur qui compile ILP en C en utilisant le langage ILP. De plus, bien qu'ILP offre des fonctionnalités avancées, il est imparfait à certains égards, comme l'absence de structures de données complètes, un manque de mécanisme de gestion de fichiers, et des fonctionnalités de sortie plutôt limitées. Ainsi, l'un de nos objectifs est également de compléter autant que possible ces aspects.
</div>

<br/>
<br/>
<br/>

<div STYLE="page-break-after: always;"></div>

## Travail attendu
- Développer en ILP une structure de données pour représenter l’AST du langage ILP.

- Développer en ILP, au fur et à mesure des besoins, une bibliothèque de structures de données générales (listes chaînées, tables d’association, etc.).

- Développer une méthode d’analyse syntaxique permettant de convertir un code source texte à la syntaxe ILPML en AST représenté en ILP.

- Développer, au choix, un compilateur d’ILP vers C ou un interprète d’ILP, en ILP

- Développer un mécanisme de gestion de programmes
multi-fichiers en ILP

- Développer des tests pour valider la correction de  compilateur ou interprète

<br/>
<br/>
<br/>

<div STYLE="page-break-after: always;"></div>

## Presentation des tâches déjà réalisées
#### Départ du Projet
<div style="text-indent: 2em;">
Notre tâche initiale consiste à trouver une structure de données pour représenter l'AST dans le langage ILP, différente de la structure AST.class déjà existante implémentée en Java. Cette fois-ci, nous devons utiliser le langage ILP pour construire ces nœuds. 

En utilisant le concept de classe déjà implémenté dans ILP4, nous pouvons utiliser des classes pour construire des nœuds AST dans ILP

Finalement, nous avons réussi à construire tous les nœuds AST basés du ILP1 en utilisant ILP lui-même
</div>
Quelques exemples :

```
ASTalternative:
class ASTalternative extends ASTexpression {
    var condition;
    var consequence;
    var alternant;

    method getCondition () 
    self.condition;

    method getConsequence () 
    self.consequence;
    
    method getAlternant () 
    self.alternant;
    
    method isTernary () 
    self.alternant != null;
    
    method eval_condition()
    (
    if type_of(self.condition) == "ASTboolean" 
    then self.getValue()
    );
}
```
```
ASTblock:
class ASTblock extends ASTexpression{
    class ASTbinding extends AST{
        var variable;
        var initialisation;

        method getVariable () 
         self.variable;
        

		method getInitialisation () 
         self.initialisation;
        
    }

    var binding; 
    var body;

    method getBindings() 
        self.binding;
    
	method getBody() 
        self.body;
}
```
```
ASTunaryOperation:
class ASTunaryOperation extends ASTexpression{
    var operator;
    var operand;

    method getOperand() 
     self.operand;
    

    method getOperator() 
      self.operator;
    

	method getOperands() 
     let node = new ListNode(self.getOperand(),new NULL()) in
        let list = new List(node) in
        list;
}
```
<div style="page-break-after: always;"></div>

#### Nouveau problème : Les contraintes de la structure de données->Liste Chainée
<br>
<div style="text-indent: 2em;">
Lors de l'implémentation de ces structures AST, nous avons rencontré un nouveau problème : ILP ne dispose pas d'une structure de données de type liste chaînée.

Par exemple, pour des nœuds tels que ASTblock, ASTsequence, lorsque les paramètres dont ils ont besoin sont un tableau, en Java, nous pouvons simplement utiliser arguments[] pour les implémenter. Mais cela n'est pas possible dans ILP. Par conséquent, notre prochaine tâche consiste à développer une structure de données similaire à une liste chaînée.

</div>

```
ASTblock en Java:
  public ASTblock (IASTbinding[] binding,
                     IASTexpression body ) {
        this.binding = binding;
        this.body = body;
    }
    private final IASTbinding[] binding;
    private final IASTexpression body;
```
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
        if  type_of(self.head) == "NULL"  then //? notion null?
            self.head = new ListNode(value, new NULL())
         else 
            let current = self.head in
            let bloc = 
            while (type_of(current.getNext()) != "NULL") do
                current = current.getNext()
                in
            current.setNext(new ListNode(value, new NULL()))
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
En même temps, nous aimerions mentionner ici notre tentative concernant la notion de NULL. ILP n'a pas non plus de notion de null. Nous l'avons donc considéré comme une classe vide pour l'implémenter :
```
class NULL extends Object {}
```
M. Antoine Mine a suggéré une approche plus élégante : utiliser la fonction type_of() déjà présente dans ILP pour simuler null, par exemple type_of(class) => null

<div style="page-break-after: always;"></div>

#### La partie que nous travaillons actuellement : un mécanisme capable de compiler plusieurs fichiers => La notion "Include"

Maintenant que nous avons la structure de liste chaînée dont nous avons besoin, il n'est pas pratique de copier à chaque fois le code source d'une liste lorsque nous en avons besoin. Par conséquent, nous avons besoin d'étendre la notion "Include" pour introduire le code pertinent.

Nous avons complété la base du Include : l'introduction dans ANTLR:
```
globalDef returns [com.paracamplus.ilp2.interfaces.IASTdeclaration node]
//ajout PSTL
    : def=includeDef #IncludeDefinition
    | def=globalFunDef # GlobalFunctionDefinition
    | def=classDef # ClassDefinition
    ;

//ajout PSTL
includeDef returns [com.paracamplus.pstl.interfaces.IASTincludeDefinition node]
    :'include' body = STRING
    ;
```
```
@Override
public void exitIncludeDefinition(IncludeDefinitionContext ctx) {
ctx.node = ctx.def.node;
}

@Override
public void exitIncludeDef(IncludeDefContext ctx) {
String filepath = ctx.body.getText();
System.out.println("Test parser "+filepath);
   //on supprime des guillemets" "dans text obtenut
   //car include work genre: include "path/array.ilpml"
   filepath = filepath.substring(1, filepath.length() - 1);
   System.out.println("Test parser "+filepath);
   
ctx.node = factory.newIncludeDefinition(filepath);
}
```
Les étapes restantes sont à compléter...

<div style="page-break-after: always;"></div>

## Tâches restantes
- Terminer l'implementation de Notion include et mecanisme de compiler multiple fichier
  
- Développer une méthode d’analyse syntaxique permettant de convertir un code source texte à la syntaxe ILPML en AST représenté en ILP.

- Développer, au choix, un compilateur d’ILP vers C ou un interprète d’ILP, en ILP

- Développer des tests pour valider la correction de  compilateur ou interprète

Merci de votre attention!