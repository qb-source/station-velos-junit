# Station de vélos : tests JUnit

Projet d'apprentissage en automatisation des tests : une station de vélos en libre-service, testée avec JUnit.

## Les règles testées

- Louer un vélo retire un vélo de la station ; impossible si la station est vide.
- Rendre un vélo en ajoute un ; impossible si la station est pleine.
- Prix pour un membre : gratuit jusqu'à 45 minutes, puis 0,15 $ par minute supplémentaire.
- Prix pour un non-membre : 1,25 $ de départ, puis 0,15 $ par minute.
- La durée d'une location doit être positive.

## Ce que vérifient les tests

| Famille | Exemples |
| --- | --- |
| État de départ | Une station de 6 vélos n'est pas vide |
| Cas normaux | Louer, rendre, calculer un prix |
| Cas limites | Louer le dernier vélo, rendre dans une station presque pleine, 45 et 46 minutes |
| Cas d'erreur | Station vide, station pleine, durée nulle ou négative (avec vérification du message) |
| Test désactivé | Future règle : 60 minutes gratuites pour les membres |

## Outils

- Java 25
- JUnit 6 (`@Test`, `@BeforeEach`, `@DisplayName`, `@ParameterizedTest`, `@ValueSource`, `@CsvSource`, `@Disabled`)
- Maven

## Lancer les tests

    mvn clean test

## Ce que j'ai appris

- **Lister les cas avant d'écrire du code** : état de départ, cas normaux, cas limites et cas d'erreur, en cherchant les frontières dans le code (`==`, `<=`, le nombre 45).
- **Distinguer un cas limite d'un cas d'erreur** : louer le dernier vélo est un cas limite (accepté), louer dans une station vide est un cas d'erreur (refusé).
- **Tester les deux côtés d'une frontière** : 45 minutes (encore gratuit pour un membre) et 46 minutes (première minute payante).
- **Regrouper des cas dans un seul test** avec `@CsvSource`, y compris une colonne vrai / faux (`boolean`) pour le statut de membre.
- **Comparer des prix** avec une tolérance (`assertEquals(attendu, obtenu, 0.01)`), car les nombres à virgule ne tombent pas toujours pile.
- **Vérifier le message d'une erreur**, pas seulement qu'une erreur est lancée.
- **Préparer une future règle** avec un test désactivé (`@Disabled`) : 60 minutes gratuites pour les membres, pas encore programmées.
- **Compter les tests exécutés** : un résultat tout vert ne suffit pas, il faut vérifier que le nombre de tests correspond à ce qui est attendu.
- **Mettre un projet en ligne avec Git et GitHub** : `.gitignore`, `git add`, `git commit`, `git push`.
