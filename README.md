# BanqueApp - Gestion de comptes bancaires

## Description du projet
BanqueApp est une application console développée en **Java 8** permettant d'automatiser la gestion des comptes bancaires.  
L'utilisateur peut créer des comptes (courant ou épargne), effectuer des opérations (versements, retraits, virements) et consulter le solde ainsi que l'historique des opérations.

**Objectifs principaux :**
- Créer des comptes bancaires
- Effectuer des versements et retraits
- Réaliser des virements entre comptes
- Consulter le solde et l'historique des opérations

---

## Technologies utilisées
- Java 8  
- ArrayList et HashMap pour le stockage en mémoire  
- Java Time API pour la gestion des dates  
- UUID pour l'identifiant unique des opérations 

---

## Structure du projet
```

src/
├─ ui/                # Interface utilisateur (menu console)
│  └─ Menu.java
├─ metier/            # Couche métier (logique des comptes et opérations)
│  ├─ banque/
│  │  └─ Banque.java
│  ├─ compte/
│  │  ├─ Compte.java
│  │  ├─ CompteCourant.java
│  │  └─ CompteEpargne.java
│  └─ operation/
│     ├─ Operation.java
│     ├─ Versement.java
│     └─ Retrait.java
└─ utility/       
   └─ Helper.java

````

**Couches principales :**
- **UI/Menu :** interface console pour interagir avec l’utilisateur  
- **Couche métier :** contient la logique des comptes et des opérations  
- **Couche utilitaire :** validation des entrées, formatage et helpers  

---

## Prérequis
- Java JDK 8 installé
- Connaissance de base des commandes `javac` et `java`
- (Optionnel) MySQL pour la persistance des données  

---

## Compilation et exécution

### 1. Compilation
Sur Linux / Mac :
```bash


javac -d out $(find src -name "*.java")
````

Sur Windows (cmd) :

```cmd
javac -d out src\**\*.java
```

### 2. Exécution

```bash


java -cp out Main
```

### 3. Génération du JAR exécutable

```bash


jar cfe BanqueApp.jar MANIFEST.MF -C out .

java -jar BanqueApp.jar
```


---

## Fonctionnalités

* Création de compte courant ou épargne 
* Versement sur un compte
* Retrait depuis un compte
* Virement entre comptes
* Consultation du solde d’un compte
* Consultation de l’historique des opérations
* Consultation des Comptes de banque

---

## Auteur

**Nom :** Ahmed Taoudi 


