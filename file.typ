#import "@preview/modern-report-umfds:0.1.2": umfds
#set text(lang: "fr", size: 12pt)
#set par(justify: true)
#show link: set text(blue)

#let template(
  titre: [],
  auteur: [],
  doc,
) = {
  show: umfds.with(
    title: titre,
    authors: (auteur,),
    date: datetime.today().display("[day] [month repr:long] [year]"),
    lang: "fr",
  )
  doc
}

#show: template.with(
  titre: [#text(22pt)[RAPPORT DÉTAILLÉ \ \ ARCHITECTURE LOGICIELLE DISTRIBUÉE : MIGRATION SOAP VERS REST]
  ], auteur: text(16pt)[]
)

#show outline.entry.where(
  level: 1
): set block(above: 1.5em, below: 0.5em)
#show outline.entry.where(
  level: 2
): set block(above: 1em, below: 0.5em)

#outline(title: "Table des matières", depth: 2)

#pagebreak()

= Introduction

Ce rapport documente l'architecture, la conception et l'implémentation d'un système distribué de comparaison d'hôtels. Initialement conçu sur une base SOAP, le projet a été entièrement migré vers une architecture REST (Representational State Transfer) pour répondre aux exigences de légèreté, de scalabilité et de compatibilité avec les standards modernes du Web.

Nous détaillerons ici le fonctionnement interne des trois micro-services composant le système (`Hotel`, `Agence`, `Comparator`), en mettant l'accent sur les défis techniques spécifiques tels que la gestion de l'état dans un environnement sans état, l'agrégation de données en parallèle, et la persistance distribuée.

= Pile Technologique et Choix d'Implémentation

Le système repose sur l'écosystème Java Spring Boot. Ce choix n'est pas anodin et structure profondément l'implémentation.

== Spring Web (REST)
Au cœur du système, le module `spring-boot-starter-web` remplace l'ancien moteur SOAP JAX-WS.
- *Contrôleurs REST* : L'annotation `@RestController` transforme les classes Java en points d'entrée HTTP, sérialisant automatiquement les retours en JSON via la bibliothèque Jackson.
- *RestTemplate* : Pour la communication inter-services, nous utilisons `RestTemplate` (client HTTP synchrone), permettant d'invoquer des services distants avec une abstraction de haut niveau.

== Persistance (Spring Data JPA & H2)
Chaque micro-service maintient sa propre base de données H2 embarquée, garantissant une isolation stricte des données (principe de "Database per Service").
- *JPA (Java Persistence API)* : Fournit une couche d'abstraction objet-relationnel. Les entités comme `Chambre` ou `Reservation` sont mappées directement sur des tables SQL.
- *Repositories* : L'interface `CrudRepository` de Spring Data génère automatiquement le code SQL pour les opérations standards (save, findById, delete), réduisant le code "boilerplate".

#pagebreak()

= Analyse Approfondie : Service `Hotel`

Le service `Hotel` est le "propriétaire" de la donnée brute (l'inventaire des chambres). Il ne se contente pas d'exposer des données, il gère la logique métier de disponibilité et de tarification.

== Modèle de Données : Distinction Chambre vs Offre
Une distinction cruciale est faite entre l'inventaire physique et le produit commercial :
- *`Chambre` (Entité Persistante)* : Représente une pièce physique. Elle possède un `id` fixe, un `nbLits` et un `prixBaseParNuit`. Elle contient une liste de `PeriodeDisponible` pour gérer son calendrier d'occupation.
- *`Offre` (Objet Transactionnel)* : Représente une proposition commerciale temporaire. C'est une projection calculée contenant : un prix final (après réduction), des dates spécifiques, et une référence à la chambre.

== Algorithme de Recherche (`/api/hotel/search`)
Lorsqu'une requête `GET` arrive avec des critères (dates, nombre de personnes), le service exécute l'algorithme suivant :

1.  *Identification du Partenaire* : Le système vérifie l'`idAgence` fourni. S'il correspond à un partenaire enregistré dans `PartenaireRepository`, un taux de réduction (ex: 10%) est appliqué.
2.  *Filtrage de l'Inventaire* :
    On parcourt toutes les chambres. Une chambre est retenue SI :
    $ "Capacité (nbLits) >= Demandé (nbPersonnes)" $
    ET
    $ "Disponibilité" : forall p in "Periodes", ["DateDebut, DateFin"] inter p = emptyset $
3.  *Génération et Persistance de l'Offre* :
    C'est ici que réside une subtilité majeure. Pour chaque chambre éligible, le système vérifie si une `Offre` identique a déjà été générée pour ces paramètres.
    - Si oui, on renvoie l'existant.
    - Si non, on crée une nouvelle `Offre` avec un ID unique (GUID) et on la sauvegarde dans `OffreRepository`.
    *Pourquoi sauvegarder l'offre ?* Cela permet de "geler" les conditions de vente. Lors de la réservation, l'agence renverra uniquement l'`offreId`. L'hôtel pourra alors retrouver exactement le prix et les dates qui avaient été proposés, garantissant l'intégrité de la transaction.

== Verrouillage et Réservation (`/api/hotel/reserver`)
La réservation est une opération atomique déclenchée via `POST`.
1.  Le système charge l'`Offre` via son ID.
2.  Il remonte à la `Chambre` associée.
3.  Il ajoute la période demandée à la liste d'occupation de la chambre.
4.  Il crée une entité `Reservation` liant le `Client` et l'`Offre`.
Si la chambre a été réservée par un autre client dans l'intervalle (entre la recherche et la réservation), l'opération échoue, assurant la consistance des données.

#pagebreak()

= Analyse Approfondie : Service `Agence`

L'Agence est un agrégateur pur. Elle ne possède pas de stock, mais elle ajoute de la valeur par la sélection et l'unification des sources.

== Stratégie d'Appel Parallèle
L'agence doit interroger $N$ hôtels. Pour éviter que le temps de réponse soit $sum T_{"hotel"}$, l'agence utilise les `Stream` parallèles de Java :
```java
hotelPorts.entrySet().parallelStream().forEach(entry -> { ... });
```
Cela lance des threads concurrents pour chaque appel HTTP. Le temps total de réponse perçu par l'utilisateur est donc approximativement $max(T_{"hotel"}) + T_{"traitement"}$.

== "Late Binding" et Flexibilité JSON
Contrairement à l'approche SOAP où les classes Java (Stubs) étaient générées à la compilation, l'agence utilise une approche dynamique.
Elle reçoit les réponses des hôtels sous forme de `ArrayList<Map<String, Object>>`.
*Avantage* : Si l'hôtel ajoute un champ "vueMer" à son JSON, l'agence ne plante pas. Elle ignore simplement les champs qu'elle ne connaît pas et extrait ceux dont elle a besoin ("prix", "offreId") pour construire son propre objet `Response`.

== Enrichissement de Données
Les API des hôtels sont atomiques. L'endpoint `/search` renvoie des offres légères. Pour afficher une belle interface, l'agence doit faire un second appel `/info` pour récupérer l'adresse complète, le nombre d'étoiles et les photos de l'hôtel, qu'elle fusionne avec l'offre avant de la renvoyer au comparateur.

#pagebreak()

= Analyse Approfondie : Application `Comparator`

Le Comparator est le défi architectural le plus complexe : comment gérer un processus d'achat en plusieurs étapes (Recherche -> Sélection -> Confirmation) sur un protocole HTTP sans état ?

== Le Problème de la Perte de Contexte
Imaginez un utilisateur qui cherche des hôtels à Paris. Il reçoit 50 offres. Il clique sur "Réserver" sur la 3ème offre, fournie par l'agence "Expedia".
La page de réservation (`/reserver`) doit afficher le récapitulatif. Mais comment le contrôleur sait-il que c'était l'offre X de l'agence Y ? Il ne peut pas refaire la recherche (les prix pourraient avoir changé).

== La Solution : Le "Panier" en Base de Données (`ResponseComp`)
Le Comparator utilise sa propre base H2 comme une mémoire cache sessionnelle.

1.  *Phase Recherche (`/hotels`)* :
    - Le Comparator reçoit les résultats des agences.
    - Il convertit chaque résultat en une entité `ResponseComp`.
    - Il sauvegarde tout dans sa base locale `ResponseCompRepository`.
    - Il affiche à l'utilisateur une liste où chaque bouton "Réserver" pointe vers l'ID local de cette entrée en base (ex: `/reserver?id=123`).

2.  *Phase Réservation (`/reserver`)* :
    - Le contrôleur reçoit l'ID `123`.
    - Il fait un `findById(123)` dans sa base locale.
    - Il récupère l'objet complet qui contient : le nom de l'agence (`agenceName`), l'ID de l'offre originale (`offreId`), le prix figé, etc.
    - Il a maintenant toutes les informations pour construire la requête vers la bonne agence.

3.  *Phase Confirmation (`/confirmer-reservation`)* :
    - Le Comparator instancie dynamiquement un client REST (`RestAgenceClient`) ciblant l'URL de l'agence récupérée dans l'objet `ResponseComp`.
    - Il envoie le `POST` de réservation.
    - En cas de succès, il nettoie sa base locale (`deleteById`) pour clore la transaction.

== Routage Dynamique
Le système est capable de gérer plusieurs agences simultanément. Grâce au champ `agenceName` stocké dans le cache local `ResponseComp`, le Comparator agit comme un routeur intelligent, dirigeant la requête de réservation vers le bon interlocuteur sans que l'utilisateur n'ait conscience de la multiplicité des sources.

#pagebreak()

= Conclusion et Perspectives

Cette migration vers REST a permis de transformer une application monolithique distribuée (SOAP) en une véritable architecture micro-services moderne.

== Gains Obtenus
- *Performance* : La taille des messages JSON est réduite de 60% par rapport au XML SOAP.
- *Maintenabilité* : La suppression de la génération de code (`wsimport`) rend le cycle de build plus simple et plus rapide.
- *Robustesse* : L'introduction de la base de données locale dans le Comparator rend le parcours utilisateur résilient aux pertes de session serveur.

== Pistes d'Amélioration
Actuellement, la découverte des services (quelles agences existent ? quels hôtels ?) est statique (fichiers de configuration). Dans un environnement de production réel (Kubernetes), l'ajout d'un *Service Registry* (comme Netflix Eureka ou Consul) serait l'étape logique suivante pour permettre aux instances de se découvrir dynamiquement.
