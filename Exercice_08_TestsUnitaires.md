# Exercice 8 : Fiabilisation de la logique métier

Cet exercice a été consacré à l'amélioration de la robustesse de la classe `BankAccount` par l'ajout de tests unitaires couvrant les cas limites.

### Modifications techniques
* **Refactorisation** : Nettoyage de la classe `Person.java` par la suppression d'un import `Scanner` superflu et remplacement du parsing par une méthode `String.split`, plus adaptée à la désérialisation des données.
* **Nouveaux tests** : Création de la classe `BankAccountEdgeCasesTest.java`.

### Scénarios de test couverts
* **Validation des dépôts** : Vérification du solde après une transaction classique.
* **Gestion des valeurs négatives** : S'assurer qu'un dépôt négatif n'altère pas le solde.
* **Contrôle des retraits** : Vérification que les tentatives de retrait dépassant le solde ou le plafond autorisé sont systématiquement refusées (retour `false`).

Le projet compte désormais 34 tests unitaires, tous validés avec succès via `mvn test`.