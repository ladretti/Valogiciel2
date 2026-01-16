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


## Exercice 8
Fichiers ajoutés / modifiés

- src/test/java/bankAccountApp/BankAccountEdgeCasesTest.java : nouveaux tests unitaires pour `BankAccount`.
- src/main/java/bankAccountApp/Person.java : suppression d'un import `Scanner` inutile et validation du parsing via `String.split`.

Tests

- Cas heureux (happy path) : `depositMoney` — vérifie l'augmentation du solde après un dépôt valide.
- Cas limites : dépôt négatif (aucun changement de solde), et retraits invalides (montant supérieur au solde ou supérieur à la limite — méthode retourne `false` et solde inchangé).

Comment exécuter

```bash
mvn test
```

Résultat

- Résultat obtenu : 34 tests, 0 failures, 0 errors — build success.

Notes techniques

- Des erreurs initiales provenaient d'une ancienne implémentation utilisant `Scanner` pour parser les données sérialisées de `Person`. J'ai remplacé ce parsing par un implémentation basée sur `String.split(Person.DELIM)` ce qui est plus simple et robuste pour la désérialisation dans les tests.
- Le nouveau fichier de test se concentre sur la logique métier critique (opérations monétaires). Il complète les tests existants définis dans `src/test/java/bankAccountApp/BankAccountTest.java`.

## Exercice 9
Actions réalisées

1) Exécution initiale JaCoCo
   - Commande utilisée  : `mvn test` puis `mvn jacoco:report`.

2) Identification d'une classe à faible couverture
   - Package `bankAccountApp` : couverture globale 61% (instructions) mais branches 42%.
   - Classe à faible couverture : `BankAccountApp` — couverture initiale (avant tests ciblés) ~21% (voir `target/site/jacoco/bankAccountApp/index.html`).
   - Méthode ciblée : `main(String[])` (beaucoup de branches non couvertes — interactions utilisateur).

3) Ajout de tests
   - `src/test/java/bankAccountApp/ACHServiceImplTest.java` — tests simples pour `ACHServiceImpl.registerAccount` et `transferAmount`.
   - `src/test/java/bankAccountApp/BankAccountAppMorePathsTest.java` — tests simulant des entrées utilisateur pour `BankAccountApp.main` :
     - `AVERAGE`, `MAXIMUM`, `minimum` puis `quit`
     - `delete` non-existant puis `quit`
   - (Ces tests complètent le test existant `BankAccountAppMainTest` qui appelle `quit` immédiatement.)

4) Exécution des tests et génération du rapport

Commandes exécutées :

```bash
mvn jacoco:report
```

Résultats

- Tests exécutés : 38, 0 failures, 0 errors.
- Rapport JaCoCo généré : `target/site/jacoco/index.html`.
- Avant : `BankAccountApp` — couverture très faible (~21%).
- Après : `BankAccountApp` — `main(String[])` coverage increased to ~32% (see `target/site/jacoco/bankAccountApp/BankAccountApp.html`).

Analyse

- La méthode `main` contient beaucoup de logique interactive (boucles et branches liées à la saisie utilisateur). Les tests ajoutés simulent plusieurs commandes et couvrent certaines branches (AVERAGE, MAXIMUM, minimum, delete non-existant) mais de nombreuses autres branches (création de compte, opérations `Account` avancées, validations de saisie détaillées) restent non testées.
- Pour augmenter davantage la couverture, on peut :
  - ajouter des tests qui simulent la création d'un compte (`new`) en fournissant toutes les valeurs attendues par `Scanner` ;
  - tester les sous-menus `Account` avec dépôts/retraits réussis et ratés ;
  - refactorer la logique interactive pour extraire la gestion de commandes dans des méthodes testables (bon pour testabilité).

Fichiers ajoutés

- `src/test/java/bankAccountApp/ACHServiceImplTest.java`
- `src/test/java/bankAccountApp/BankAccountAppMorePathsTest.java`

## Exercice 10
Exercice 10 — CI workflow (GitHub Actions)

But: ajouter un workflow GitHub Actions basique qui exécute `mvn clean test` sur chaque push vers `main`, puis étendre pour packager et sauvegarder l'artifact `target/`.

Fichiers ajoutés

- `.github/workflows/ci.yml` : workflow CI qui exécute les étapes suivantes :
  1. checkout du repo
  2. setup JDK (Temurin 17)
  3. cache du dépôt Maven local (`~/.m2/repository`)
  4. `mvn -B clean test`
  5. `mvn -B package`
  6. upload de `target/` comme artifact de build

Contenu (extrait) — fichier complet : `.github/workflows/ci.yml`

```yaml
name: CI

on:
  push:
    branches: [ main ]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-java@v4
        with:
          distribution: 'temurin'
          java-version: '17'
      - uses: actions/cache@v4
        with:
          path: ~/.m2/repository
          key: ${{ runner.os }}-m2-${{ hashFiles('**/pom.xml') }}
      - run: mvn -B clean test
      - run: mvn -B package
      - uses: actions/upload-artifact@v4
        with:
          name: target
          path: target/
```

Commentaire

- Le workflow s'exécute sur chaque push vers `main`.
- Le `package` permet de générer l'artefact dans `target/` (jar), puis `upload-artifact` stocke ce dossier comme résultat du run (accessible depuis l'interface Actions → job → Artifacts).
- L'option `-B` (batch mode) est utilisée pour une sortie non interactive.

Tests locaux

Pour vérifier localement avant push :

```bash
mvn -B clean test
mvn -B package
```

## Exercice 11

Modifications effectuées

1. Dépendances (pom.xml)
   - Ajouté `cucumber-java` et `cucumber-junit` (version 7.15.0, scope test).

2. Feature
   - `src/test/resources/features/bank_account_basic.feature` :

```
Feature: Basic bank account behavior
  Scenario: A new account has zero balance
    Given I have a new bank account
    When I check its balance
    Then the balance should be 0
```

3. Runner
   - `src/test/java/com/imt/mines/bank/bdd/RunBankAccountCucumberTest.java` : JUnit runner utilisant Cucumber.

4. Step definitions
   - `src/test/java/com/imt/mines/bank/bdd/BankAccountBasicSteps.java` : implémentation des étapes en utilisant l'API existante `bankAccountApp.BankAccount` :
     - crée un `new BankAccount()` (balance initiale 0)
     - lit le solde via `getBalance()` et compare avec la valeur attendue

Exécution

- Commande utilisée pour exécuter le test Cucumber :

```bash
mvn -Dtest=com.imt.mines.bank.bdd.RunBankAccountCucumberTest test
```

- Sortie : le scénario Cucumber apparaît et passe :
  - `1 test, 0 failures, 0 errors`.
