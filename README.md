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

(À compléter avec tes mots : par exemple ce qu'est un cas limite, pourquoi on vérifie le message d'une erreur, à quoi sert @Disabled…)
