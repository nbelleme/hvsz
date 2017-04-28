Worflow Git
===========

*Un Workflow inspiré du GitFlow standard : https://fr.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow*

#### But du document

Ce document a pour but de décrire le workflow git utilisé dans le projet. Ce workflow peut évoluer en fonction des besoins
et les propositions sont évidemment les bienvenues :)


#### Idées principales

* Une branche master qui représente l'état actuel de l'application en production.
* Une branche de developpement qui représente un état "stable" de l'application et contient un certain nombre de features
    prêtes à passer en production.
* Des branches de features qui permettent aux différents collaborateurs du projet de développer leurs fonctionnalités en
    parallèle sans se gêner les uns les autres.
    
#### Les étapes

##### Développer une feature ou un fix

* Démarrer tout développement à partir de la dernière version de la **branche de dev**.
* Créer une **branche de feature** nommée par le nom de votre feature et effectuer votre développement.
* Lorsque votre feature est codée et **testée**, **rebaser** votre branche sur la branche de dev à jour pour rejouer les commits
    au dessus de dev si celle-ci a été mise à jour.
* **Pousser** votre branche de feature.
* Se rendre sur GitHub pour créer une **Pull Request** de votre branche vers la branche de dev et assigner les personnes
    concernées par le developpement pour les informer que votre feature a été developpée ou si vous souhaitez qu'on
    relise votre code où avez besoin d'un retour.
* Lorsque votre Pull Request a été validée par suffisament de personnes (A nuancer : On a besoin de developper vite donc
    ne restez pas bloqués trop longtemps sur cette étape si vous n'avez pas de retour rapidement), et après s'être assuré
    que votre branche est toujours à jour par rapport à dev, **valider la Pull Request**.
* Enjoy :)


##### Faire une mise en prod

* Se mettre sur dev et faire un commit pour **changer le numéro de version** (On retire -SNAPSHOT de la version dans le POM)
* **Merger dev** dans master (Master est censée faire un fast-forward).
* Faire un **tag** sur le commit avec le numéro de version.
* **Pousser le tag**.
* Demander à un dev-ops de déployer votre tag.
* Se remettre sur dev et faire un nouveau commit pour préparer la nouvelle version (On change le numéro et on ajoute -SNAPSHOT).
* C'est repartit pour un tour :)

#### Les pièges à éviter

* Ne jamais (***JAMAIS !!!***) rebase une branche sur laquelle plusieurs personnes travaillent (typiquement la branche master,
la branche de dev et toute branche de feature qui nécéssite un développement à plusieur).

#### Aperçu du git tree

A venir ;)