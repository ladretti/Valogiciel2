# Exercice 11 : Tests de comportement avec Cucumber (BDD)

Le dernier volet du TP consistait à mettre en œuvre une approche BDD (Behavior-Driven Development) pour définir des exigences fonctionnelles claires.

### Mise en œuvre
* **Définition de la Feature** : Rédaction d'un fichier `.feature` décrivant le comportement attendu d'un nouveau compte bancaire (solde initial nul).
* **Step Definitions** : Implémentation en Java des étapes Gherkin via la classe `BankAccountBasicSteps`.
* **Exécution** : Utilisation d'un runner JUnit spécifique pour lier les spécifications textuelles au code de test.

### Conclusion
Cette méthode permet de faire le pont entre les spécifications fonctionnelles et les tests techniques. L'exécution via `mvn test` confirme que le comportement du compte bancaire est conforme aux attentes métiers définies.