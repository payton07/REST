## Comparateur d'Hôtels - Services Web REST

Ce projet démontre une architecture orientée services (SOA) basée sur
des **services web REST** pour construire un comparateur d'offres
hôtelières. L'application est décomposée en trois modules Java
indépendants communiquant exclusivement via des API REST (HTTP/JSON).

### Architecture

Le système est une architecture distribuée à trois niveaux :

1.  **Service `Hotel`** : Le niveau le plus bas. Chaque instance
    représente un hôtel unique, gérant chambres, tarifs et partenariats.
    Il expose une API REST pour consulter ses offres et faire des
    réservations.

2.  **Service `Agence`** : Le niveau intermédiaire. Il consomme les
    API REST de plusieurs hôtels partenaires pour agréger leurs
    offres, les filtrer, puis expose sa propre API REST.

3.  **Application `Comparator`** : Le niveau supérieur et point d'entrée
    utilisateur. Application web Spring Boot avec interface Thymeleaf,
agissant comme un client REST pour comparer les offres de plusieurs
agences et réserver.

![Architecture globale](../images/rest_global_architecture.png)

------------------------------------------------------------------------

### Pile technologique

-   **Langage** : Java 8+
-   **Framework** : Spring Boot (Web)
-   **Gestion de projet** : Maven
-   **Services Web** : REST API (JSON, RestTemplate)
-   **Base de données** : H2
-   **Interface utilisateur** : Thymeleaf

### Comment lancer le projet

Pour lancer l'écosystème, démarrez les services dans cet ordre :\ 
**Hotel → Agence → Comparator**

#### Prérequis

-   JDK 8 (ou version supérieure)\ 
-   Apache Maven

------------------------------------------------------------------------

### Étape 1 : Lancer les services `Hotel`

Chaque service `Hotel` doit être lancé dans son propre terminal.

#### Terminal 1 : Premier hôtel

-   `cd Hotel`
-   `mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8081"`
-   Choisissez par exemple l'hôtel `Belaroia`.
-   API Base : `http://localhost:8081/api/hotel`

#### Terminal 2 : Deuxième hôtel

-   `cd Hotel`
-   `mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8082"`
-   Choisissez par exemple l'hôtel `Ibis`.
-   API Base : `http://localhost:8082/api/hotel`

#### Terminal 3 : Troisième hôtel (optionnel)

-   `cd Hotel`
-   `mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8083"`
-   API Base : `http://localhost:8083/api/hotel`

> **Note :** Il faut s'assurer d'utiliser un port différent pour chaque
> instance.

------------------------------------------------------------------------

### Étape 2 : Configurer et lancer le service `Agence`

#### 1. Mettre à jour la configuration
Si vous avez ajouter un nouvel hotel : \
Modifier le fichier `application.properties` du projet Agence
- ajouter la nouvelle agence en ajoutant cette ligne et remplaçant par les valeurs correspondantes:

      agences.hotels.<nom_agence>=port

#### 2. Lancer le service

-   `cd Agence`
-   `mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8881"`
-   Choisissez un nom d'agence (ex : `StreetMan`).
-   API Base : `http://localhost:8881/api/agence`

Vous pouvez créer plusieurs agences : utilisez un port différent pour
chacune.
Ex :
`mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8882"`

------------------------------------------------------------------------

### Étape 3 : Configurer et lancer le `Comparator`

#### 1. Mettre à jour la configuration
Si vous avez ajouter une nouvelle agence : \
Modifier le fichier `application.properties` du projet Comparator
- ajouter la nouvelle agence en ajoutant cette ligne et remplaçant par les valeurs correspondantes:

      comparator.agences.<nom_agence>=port


#### 2. Lancer l'application

-   `cd Comparator`
-   `mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=9100"`

------------------------------------------------------------------------

### Étape 4 : Utiliser l'application

Ouvrez :

    http://localhost:9100

Vous pouvez maintenant :

-   rechercher des hôtels (les appels REST sont orchestrés en arrière-plan),
-   consulter les offres agrégées,
-   effectuer une réservation.
