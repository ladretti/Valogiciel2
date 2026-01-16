# Exercice 9 : Analyse et amélioration de la couverture de code

L'utilisation de l'outil JaCoCo a permis de mesurer précisément quelle proportion du code source est réellement sollicitée par la suite de tests.

### État des lieux initial
Le premier rapport a révélé une couverture de seulement 21% pour la classe principale `BankAccountApp`. Ce faible score s'expliquait par la présence de nombreuses branches logiques dans la méthode `main` liées aux interactions utilisateur.

### Stratégie d'amélioration
Afin d'augmenter cette couverture, j'ai implémenté :
* `ACHServiceImplTest` : Tests dédiés aux services de transfert.
* `BankAccountAppMorePathsTest` : Simulation d'entrées utilisateur via le `Scanner` pour tester les fonctionnalités de calcul de moyennes, de recherche de maximum et de suppression de compte.

### Résultats obtenus
La couverture de la méthode `main` a progressé à 32%. Cette analyse met en évidence que pour atteindre une couverture totale sur une interface console, une refactorisation visant à isoler la logique métier de la gestion des entrées/sorties serait nécessaire.