## Comparateur d'Hôtels - Services Web SOAP

Ce projet démontre une architecture orientée services (SOA) basée sur
des services web SOAP pour construire un comparateur d'offres
hôtelières. L'application est décomposée en trois modules Java
indépendants communiquant exclusivement via SOAP.

### Architecture

Le système est une architecture distribuée à trois niveaux :

1.  **Service `Hotel`** : Le niveau le plus bas. Chaque instance
    représente un hôtel unique, gérant chambres, tarifs et partenariats.
    Il expose un service SOAP pour consulter ses offres et faire des
    réservations.

2.  **Service `Agence`** : Le niveau intermédiaire. Il consomme les
    services SOAP de plusieurs hôtels partenaires pour agréger leurs
    offres, puis expose son propre service.

3.  **Application `Comparator`** : Le niveau supérieur et point d'entrée
    utilisateur. Application web Spring Boot avec interface Thymeleaf,
    consommant plusieurs services d'agences pour comparer et réserver.

![Architecture globale](images/global_architecture.png)

------------------------------------------------------------------------

### Pile technologique

-   **Langage** : Java 8+
-   **Framework** : Spring Boot
-   **Gestion de projet** : Maven
-   **Services Web** : JAX-WS (WSDL, wsimport)
-   **Base de données** : H2
-   **Interface utilisateur** : Thymeleaf

### Comment lancer le projet

Pour lancer l'écosystème, démarrez les services dans cet ordre :\
**Hotel → Agence → Comparator**

#### Prérequis

-   JDK 8\
-   Apache Maven

------------------------------------------------------------------------

### Étape 1 : Lancer les services `Hotel`

Chaque service `Hotel` doit être lancé dans son propre terminal.

#### Terminal 1 : Premier hôtel

-   `cd Hotel`
-   `mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8000"`
-   Choisissez par exemple le port `8081` et l'hôtel `Belaroia`.
-   WSDL : `http://localhost:8081/Belaroia?wsdl`

#### Terminal 2 : Deuxième hôtel

-   `cd Hotel`
-   `mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8001"`
-   Ex. port `8082`, hôtel `Ibis`.
-   WSDL : `http://localhost:8082/Ibis?wsdl`

#### Terminal 3 : Troisième hôtel (optionnel)

-   `cd Hotel`
-   `mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8003"`
-   Port différent, ex. `8083`, hôtel `Novotel`
-   WSDL : `http://localhost:8083/Novotel?wsdl`

> **Note :** Il faut s'assurer d'utiliser un port différent pour chaque
> instance.

------------------------------------------------------------------------

### Étape 2 : Configurer et lancer le service `Agence`

#### 1. Mettre à jour la configuration

Dans `Agence/src/main/java/org/example/agence/config/AgenceConfig.java`
:

``` java
public AgenceConfig() {
    this.liens = new ArrayList<>();
    liens.add("http://localhost:8081/Belaroia?wsdl");
    liens.add("http://localhost:8082/Ibis?wsdl");
    // ajouter les autres hôtels si nécessaires
}
```

#### 2. Générer les classes client SOAP

Dans `Agence/` :

``` bash
wsimport -s src/main/java -keep -p org.example.agence.hotel http://localhost:8082/Ibis?wsdl
```

#### 3. Lancer le service

-   `mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8888"`
-   Choisissez un nom d'agence (ex : `StreetMan`) et un port (ex :
    `8881`).
-   WSDL : `http://localhost:8881/StreetMan?wsdl`

Vous pouvez créer plusieurs agences : utilisez un port différent pour
chacune.\
Ex :\
`mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8889"`

------------------------------------------------------------------------

### Étape 3 : Configurer et lancer le `Comparator`

#### 1. Mettre à jour la configuration

`Comparator/src/main/java/org/example/comparator/config/ComparatorConfig.java`
:

``` java
public ComparatorConfig() {
    this.liens = new ArrayList<>();
    liens.add("http://localhost:8881/StreetMan?wsdl");
    liens.add("http://localhost:8882/Expedia?wsdl");
    // ajouter les autres agences ou enlever un parmi les deux si nécessaires
}
```

#### 2. Générer les classes client SOAP

Dans `Comparator/` :

``` bash
wsimport -s src/main/java -keep -p org.example.comparator.agence http://localhost:8881/StreetMan?wsdl
```

#### 3. Lancer l'application

-   `mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=9100"`

------------------------------------------------------------------------

### Étape 4 : Utiliser l'application

Ouvrez :

    http://localhost:9100

Vous pouvez maintenant :

-   rechercher des hôtels,
-   consulter les offres agrégées,
-   effectuer une réservation.
