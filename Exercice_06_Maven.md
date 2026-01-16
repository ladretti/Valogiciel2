# Exercice 6 : Standardisation du projet avec Maven

L'objectif de cet exercice était de migrer l'application `BankApplication` vers l'outil de gestion de build Maven afin de structurer le cycle de vie du projet.

### Actions réalisées :
* **Initialisation du POM** : Création du fichier `pom.xml` avec les coordonnées suivantes :
    * `groupId` : com.imt.mines
    * `artifactId` : bank-application
    * `version` : 1.0-SNAPSHOT
* **Restructuration du projet** : Mise en place de l'arborescence standard Maven en déplaçant les sources vers `src/main/java` et les ressources vers `src/main/resources`.
* **Validation** : Une première compilation via la commande `mvn compile` a été effectuée avec succès, confirmant la bonne configuration de l'environnement.

Le projet bénéficie désormais d'une gestion de dépendances propre et d'une structure conforme aux standards de l'industrie.