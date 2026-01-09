# Valogiciel2

## Exercice 6 (Repo 2) : Transformer BankApplication en projet Maven

- Créé un nouveau projet Maven avec les détails suivants :
    - groupId : com.imt.mines
    - artifactId : bank-application
    - version : 1.0-SNAPSHOT
- Ajouté la structure de répertoires standard Maven : src/main/java, src/main/resources, src/test/java, src/test/resources.
- Exécuté `mvn compile` avec succès sans erreurs.

## Exercice 7 (Repo 2) : Ajouter des dépendances de test et explorer le cycle de vie Maven

- Ajouté JUnit 5.10.0 comme dépendance de test dans pom.xml.
- Maven a téléchargé les dépendances automatiquement.

### mvn clean
- **Phases exécutées** : clean
- **Nouveaux fichiers/dossiers dans target/** : Aucun (le répertoire target/ est supprimé)

### mvn test
- **Phases exécutées** : process-resources, compile, process-test-resources, test-compile, test
- **Nouveaux fichiers/dossiers dans target/** : 
    - `classes/` (classes principales compilées)
    - `test-classes/` (vide, car aucune source de test)
    - `generated-sources/`
    - `generated-test-sources/`
    - `maven-status/`

### mvn package
- **Phases exécutées** : process-resources, compile, process-test-resources, test-compile, test, package
- **Nouveaux fichiers/dossiers dans target/** : 
    - `bank-application-1.0-SNAPSHOT.jar` (le fichier JAR empaqueté)
    - `maven-archiver/` (métadonnées pour la création du JAR)

### mvn verify
- **Phases exécutées** : process-resources, compile, process-test-resources, test-compile, test, package, verify
- **Nouveaux fichiers/dossiers dans target/** : Aucun (aucun plugin supplémentaire ou test d'intégration configuré)

### Hypothèse : verify vs. test et package
`mvn verify` s'exécute après `mvn package` et est destiné à exécuter des tests d'intégration et à effectuer des vérifications supplémentaires (comme des contrôles de qualité du code, des analyses de sécurité, etc.) qui vont au-delà des tests unitaires. Contrairement à `test` (qui exécute des tests unitaires) et `package` (qui crée l'artefact), `verify` garantit que le package est valide et prêt pour le déploiement en exécutant des phases post-intégration-test. Dans ce cas, comme aucun test d'intégration ou plugin de vérification supplémentaire n'est configuré, `verify` se comporte de manière similaire à `package` mais inclut la phase verify pour des extensions potentielles futures.

