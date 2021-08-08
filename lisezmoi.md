# Nom du projet
Le projet s'appelle A.

# Description du projet

Le projet A est un projet de d�veloppement Java **multi-applications** qui repose enti�rement sur un **framework minimaliste** (le framework a). Son objectif est de permettre le d�veloppement d'applications d'une mani�re �volutive et flexible, en organisant le code source en composants r�utilisables.

# Organisation des packages

Le code source du projet A s'organise en 4 packages racine dont nous allons d�tailler le r�le juste apr�s :
- a.framework
- a.core
- a.entity
- a.config

### a.framework

Ce package contient les 16 classes Java qui composent le framework a.

Il regroupe 14 interfaces :
- les 11 interfaces fonctionnelles B, E, F, G, H, I, P, R, S (appel�es caract�ristiques).
- l'interface Entity, qui d�finit le composant entit�
- l'interface Service, qui h�rite des 11 caract�ristiques
- l'interface Manager qui permet d'interfacer les composants

Ainsi que 2 impl�mentations :
- S1 : l'impl�mentation par d�faut de S
- Outside : Classe statique qui fournit aux entit�s une impl�mentation de l'interface Manager

Le code source du framework est d�finitivement fig�, ce qui garantit une compatibilit� structuelle pour tous les code sources qui en d�coulent (contenus dans les packages *a.core*, *a.entity* et *a.config*).

### a.core

Ce package regroupe les diff�rents noyaux applicatifs (core) se basant sur le framework a.
On notera que chaque core dispose de sa propre classe Main ex�cutable et peut a priori �tre utilis� pour batir une multitude d'applications diff�rentes. Le code source de ce package doit se conformer � diff�rentes r�gles de codage qui seront expos�es par la suite.

### a.entity

Ce package regroupe les nombreuses entit�s (entity), sortes de briques fonctionnelles bas�es sur le framework a.
Son code source doit se conformer � diff�rentes r�gles de codage qui seront expos�es par la suite.

### a.config

Ce package regroupe les diff�rentes configurations des applications se basant sur le framework a. Chaque configuration contient des fichiers de param�trage et �ventuellement des resources internes (images, icons, librairies tierce...) utilis�es par l'application.
L� encore, ces fichiers sont organis�s selon diff�rentes r�gles expos�es par la suite.

#R�gles de nommage

### Un projet multi d�veloppeurs

Chaque d�veloppeur (ou instance de d�veloppement) souhaitant contribuer au projet A en y ajoutant son propre code source devra se conformer � certaines r�gles de nommage garantissant notamment une s�paration nette des diff�rentes contributions. Un identifiant unique <dev-id> sera attribu� � chacun permettant d'obtenir des sous-r�pertoires racines distincts � l'int�rieur des 3 packages : *a.core*, *a.entity*, *a.config*

Les deux premiers identifiants attribu�s sont gus (mon identifiant) et tav (identifiant de test). Nous avons ainsi les packages suivants :

Pour le d�veloppeur gus :
- a.core.gus
- a.entity.gus
- a.config.gus

Pour le d�veloppeur tav :
- a.core.tav
- a.entity.tav
- a.config.tav

En supposant que vous d�cidiez de rejoindre l'aventure avec l'identifiant marcus, vous auriez alors � disposition :
- a.core.marcus
- a.entity.marcus
- a.config.marcus

### Organisation pour les noyaux (core)

Les noyaux doivent suivre la convention de nommage suivante : *<dev-id>.<...>*
En tant que d�veloppeur marcus, tous vos noyaux doivent donc �tre nomm�s comme ceci : *marcus.<...>*

Si votre premier noyau s'appelle *marcus.kernel1*, alors son code source sera regroup� dans le package racine *a.core.marcus.kernel1*.
Il pourra contenir plusieurs classes r�parties dans plusieurs packages (contrairement aux entit�s), mais devra contenir une unique classe Main (avec la m�thode public static void main)

A titre d'exemple, j'ai d�velopp� un noyau nomm� **gus.gyem** dont la classe Main est *a.core.gus.gyem.GyemMain*.
Si vous le souhaitez, vous pourrez utiliser ce noyau pour construire vos propres applications.
Dans cette optique, nous aborderons son fonctionnement par la suite.

### Organisation pour les entit�s (entity)

Mais bien plus qu'un noyau (vous pourrez toujours d�buter en utilisant gus.gyem), je vous encourage � cr�er vos propres entit�s pour d�velopper de nouvelles fonctionnalit�s. Les entit�s sont en effet de petits composants pouvant �tre combin�s les uns aux autres pour obtenir des syst�mes fonctionnels de complexit� variable.

Les entit�s doivent suivre la convention de nommage suivante : *<dev-id>.<...>.<...>.<...>*
En tant que d�veloppeur marcus, toutes vos entit�s doivent �tre nomm�es comme ceci : *marcus.<...>.<...>.<...>* 
ou la partie <...>.<...>.<...> (sous-package) vous permet de ranger toutes vos entit�s selon vos propres r�gles.

De mani�re similaire aux noyaux, le nom du composant d�termine le package accueillant le code source.
Une entit� *marcus.conversion1* sera ainsi stock�e dans le package *a.entity.marcus.conversion1*

Par ailleurs, le code source d'une entit� est toujours compos�e d'une unique classe Java nomm�e *EntityImpl*.
Le code source de l'entit� *marcus.conversion1* correspondra ainsi � la classe *a.entity.marcus.conversion1.EntityImpl*

Voici quelques exemples d'entit�s que j'ai d�j� d�velopp� :
- a.execute.exception
- b.entitysrc1.generator1
- b.menu1.init

### Organisation pour les configurations (config)

Nous avons vu que les noms des composants core et entity d�butent imp�rativement par l'identifiant du d�veloppeur qui en est l'auteur.
Mais cette r�gle s'applique aussi pour les nommages d'applications, lesquels doivent suivre la convention : *<dev-id>.<...>*

Une application est compos�e d'un unique noyau, d'une multitudes d'entit�s (a priori), et d'une unique configuration.
Celle-ci est donc li�e � la fois � l'application et au noyau. Son package racine sera donc le suivant :
*a.config.<core-name>.<appli-name>*

Pour fixer les id�es, imaginons le cas de deux d�veloppeurs john et smith qui ont chacun cr�er leurs propres noyaux, respectivement john.core1 et smith.core1, et souhaitent cr�er deux applications chacun en utilisant les 2 noyaux.

Alors john va devoir cr�er les deux packages suivants :
- a.config.john.core1.john.appli1
- a.config.smith.core1.john.appli2

Et smith les deux packages suivants :
- a.config.smith.core1.smith.appli1
- a.config.john.core1.smith.appli2

Nous verrons par la suite qu'un param�tre pass� au d�marrage du noyau (ou un �ventuel fichier *param* situ� � la racine *a.config.<core-name>*) permet d'orienter ce dernier vers l'une au l'autre des configurations disponibles.

#R�gles de codage

### Restrictions sur les imports

Afin d'assurer une stricte s�paration entre les diff�rents composants core et entity, aucune classe d'un composant ne pourra �tre directement import�e dans le code source d'un autre composant.

Dans le cas d'une entit� notamment, le seul import d�butant par a.<...> qui est autoris� dans EntityImpl est :
*import a.framework.\*;*

### Coder une classe d'entit�

Une classe d'entit� doit toujours respecter les r�gles suivantes :
- Etre l'unique classe au sein du package de l'entit�
- Etre nomm�e *EntityImpl*
- Impl�menter l'interface *a.framework.Entity*
- Avoir une m�thode *creationDate()* qui renvoie en sortie la date de cr�ation de l'entit� au format yyyyMMdd

Pour �tre utilis�e directement par le noyau ou indirectement par d'autres entit�s, elle devra par ailleurs impl�menter une ou plusieurs des 11 interfaces de caract�risation definies par le framework : E, B, F, G, P, T, H, R, V, S, I, ou m�me �tendre l'impl�mentation par d�faut de S : S1.

Nous aurons l'occasion de d�couvrir ce que ces r�gles donnent en pratique lorsque nous �tudierons des exemples.

### Coder un noyau

Il est extr�mement simple de cr�er des entit�s et de les utiliser pour construire une application en partant d'un noyau d�j� existant. Coder un noyau, c'est un peu plus compliqu�. Si cela vous tente n�anmoins, je vous encourage � regarder le code source de gus.gyem. N'h�sitez pas non plus � me contacter pour �changer � ce sujet.

### Et la config ?

Une config ne contient que du param�trage et des resources internes. Cette partie n'est par cons�quent pas concern�e par les r�gles de codage. Elle se conforme n�anmoins � un certain nombre de r�gles qui sont enti�rement dict�e par le noyau dont elle d�pend.

# Utiliser le noyau gus.gyem

### Lancer une application bas�e sur gus.gyem

Lorsqu'on lance la classe a.core.gus.gyem.GyemMain que se passe-t-il ? Vous pouvez essayer et vous verrez appara�tre une fen�tre dont le titre est "Application" et dont le panneau central affichage le message suivant :

- app.maingui not found inside application properties
- core name = gus.gyem
- core build = 20210805

Il s'agit l� du comportement par d�faut du noyau gus.gyem, et si on ne gardait dans le projet que son propre code ainsi que le framework, on aurait le m�me r�sultat.

Lorsqu'il existe une ou plusieurs configurations applicatives dans le package a.config, comment peut on alors indiquer au noyau laquelle choisir ? Il existe deux moyens.

En le pr�cisant sous forme de param�tre externe :
- pour cela, il faut ajouter en argument, la ligne suivante : *config=<config-name>*

En me pr�cisant sous forme de param�tre interne :
- pour cela, il faut ajouter dans le projet le fichier properties suivant *a.config.gus.gyem.param* avec la ligne *config=<config-name>*

Le fichier param ne fait pas officiellement parti du projet A, il s'agit juste d'un fichier qu'on ajoute au code source d'une application pour indiquer quelle est sa configuration.

Vous pouvez ainsi tester les diff�rentes configurations existantes en ajoutant la ligne *config=<config-name>*, soit dans le fichier param, soit en ligne argument pass�e au lancement.

### Les propri�t�s de l'application


