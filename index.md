---
layout: default
---

Sujet: Calculer le produit de 2 matrices

## Setup d'un serveur:

Executer la commande suivante pour compiler le serveur: `javac StartServer.java`.

Pour lancer le serveur, faites dans un premier temps la commande `rmiregistry` puis, dans un second temps, dans un autre terminal exécuter `java StartServer`.

## Setup d'un Noeud:

Executer la commande suivante pour compiler le noeud: `javac StartNode.java`.

Pour lancer le noeud, faites la commande `java StartNode <host/optional> <port/optional>`.

## Setup d'un Client:

Executer la commande suivante pour compiler le client: `javac StartClient.java`.

Pour lancer le noeud,  faites la commande `java StartClient <matrice 1> <matrice 2> <host/optional> <port/optional>`.
