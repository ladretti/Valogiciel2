# Exercice 7 : Gestion des dépendances et cycle de vie

Dans cette étape, j'ai intégré JUnit 5 pour la gestion des tests et exploré les phases fondamentales du cycle de vie Maven.

### Configuration des tests
L'ajout de JUnit 5.10.0 dans la section `<dependencies>` du `pom.xml` a permis à Maven de résoudre et de télécharger automatiquement les bibliothèques nécessaires.

### Observation des phases de build
J'ai analysé l'impact des différentes commandes sur le répertoire de sortie `target/` :

* **mvn clean** : Supprime le répertoire `target/`, garantissant que la prochaine compilation partira d'un état neutre.
* **mvn test** : Exécute les phases de compilation des sources et des tests, puis lance l'exécution de ces derniers. On y retrouve les dossiers `classes/` et `test-classes/`.
* **mvn package** : Génère l'artefact final (un fichier JAR) après avoir validé les tests.
* **mvn verify** : Exécute des vérifications supplémentaires sur le package. Bien qu'elle se comporte ici de manière similaire à `package` par manque de tests d'intégration spécifiques, cette phase est essentielle pour les contrôles de qualité avancés.

L'utilisation systématique de ces commandes permet de garantir la fiabilité du build à chaque étape.