# Exercice 10 : Automatisation via l'Intégration Continue (CI)

Pour garantir la stabilité du code sur le long terme, j'ai mis en place un pipeline d'intégration continue utilisant GitHub Actions.

### Configuration du workflow
Le fichier `.github/workflows/ci.yml` a été configuré pour se déclencher à chaque push sur la branche principale. Les étapes clés sont :
1.  **Environnement** : Utilisation d'une instance Ubuntu avec le JDK 17 (Temurin).
2.  **Optimisation** : Mise en place d'un cache pour les dépendances Maven afin de réduire le temps d'exécution.
3.  **Build & Test** : Exécution automatique de `mvn clean test` puis `mvn package`.
4.  **Artifacts** : Archivage automatique du dossier `target/` pour permettre la récupération du fichier JAR généré après chaque build réussi.

Cette automatisation assure que chaque modification du code est testée et compilée dans un environnement neutre avant d'être validée.