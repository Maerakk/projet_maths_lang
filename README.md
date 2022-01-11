# "Reconnaissance d'auteur à l'aide d'un modèle de langage statistique"
## Au 17/11/2021 
Le support du **15/12/2020** n'est pas à jour sur les noms des projets et quelques noms de classes.</br>
La partie 1 intitulée "prise en main de la bibliothèque de modèles de langage", fait référence à un projet intitulé "Etudiant-mm_useLangModel". 
Ce projet a depuis été fusionné avec d'autres projets java pour donner le projet "M3202_MM_etu" récupérable via le lien madoc/gitlab. </br>
Il s'agira d'écrire des tests et un main qui utilisent la bibliothèque "lib/language-model-enseignant-2017.jar"


## Partie 2
Intitulée "implémentation des modèles de langage", fait référence à un projet intitulé "Etudiant-mm_langModel". </br>
Ce projet, comme le précédent, a été fusionné dans le projet "M3202_MM_etu". </br>
Dans cette partie, vous devrez implémenter des classes présentes dans le package "myLangModel" qui se trouve dans le répertoire src. 
</br>Là encore, **le support n'est pas à jour**. Dans cette partie, quand vous lisez "langModel", il faut comprendre "myLangModel". Réserver l'espace de nom "langModel" pour désigner l'implémentation des enseignants présente dans le jar "lib/language-model-enseignant-2017.jar".

## 25/11/2021
 - Le corpus d'entraînement et les phrases de tests utilisés dans le TD séance 1 sont présentes dans le répertoire data/small_corpus... Un compteur de ngrams de ce corpus et un vocabulaire vous sont donnés dans le répertoire lp/small_corpus. Les tests que vous implémenterez pourront s'appuyer sur ces ressources. Vous pourrez vérifier ce que donne les implémentations avec ce que vous avez calculé en TD séance 1. La version 2 (lm et vocab) sont pour illustrer le fonctionnement avec des mots hors vocabulaire. Ils serviront dans le développement d'un main qui met en oeuvre les méthodes principales de la lib.
 - La main de la classe src/langModel.Application_LangModelEns implémente les calculs de proba réalisé en TD séance 1. Le fait de développer ce main avant les tests permet de comprendre comment s'instancie un Naive/LapaceLanguageModel. C'est à dire : 1) création d'un objet Vocabulary (à partir d'un fichier existant qui liste le vocabulaire),  2) création d'un objet NgramCount (soit en "scannant" un corpus de phrases soit en "lisant" un compteur de ngrams déjà construits (via un scan puis write), 3) création d'un objet LanguageModel et association du vocab et du ngramCount à celui-ci (setNgramCount).
 - Dans intellij, si besoin spécifier que vous utiliser junit 4 et non 5.
 - Pour utiliser assertEquals, faire import static org.junit.Assert.assertEquals;
 - On ne fait pas du reverse ingeneering sur le jar/.class pour connaître l'API, on regarde la javadoc dans le répertoire doc. Accidentellement, il semble qu'il faille l'ouvrir avec chromium et non firefox. Le fichier d'entrée de la javadoc est index.html.
 - Dans test/langModel.NgramUtilsTest, l'import n'est pas mylangModel.NgramUtils mais langModel.NgramUtils. Corrigé sur le dépôt.
 - Jusqu'à ce jour la lib lm enseignant était "sealed". Cela pouvait conduire à des violations de sécurité à l'exécution (constaté avec un test/langModel.NgramUtilsTest). La version déposée sur le git ce jour a été corrigée "à la mano". La propriété sealed dans le META-INFO a été déclaré à false. Par la même occasion des fichiers cachés Mac OS et Eclipse ont été supprimés.
 - Parfois Intellij, ne reconnaît pas certains imports. Quand vous avez vérifié que la lib est dans votre lib path (file> project structure > libraries > '+'), vider les caches d'intellij
 - Dans NgramUtils, generate sert à la phase de construction des modèles tandis que decompose sert à la phase de calcul de la probabilité d'une nouvelle phrase (laquelle utilise les modèles construits)
 - Dans *LanguageModel, getNgramProb et getSentence prennent tous les deux une séquence de mots en paramètre. Le premier calcule la probabilité conditionnelle en prenant le mot le plus à droite comme tête. Le second calcule la probabilité de la séquence qui est égale aux produits des probabilités conditionnelles qui composent la séquence...

# A rendre
##Rapport
Point d'avancement de ce qu'on a fait, pas fait, ce qui marche, ne marche pas
##Code du projet
Pas seulement les classes
## data/authorcorpus/test
On a le droit à faire 2 run pour donner 2 hypothèses sur les phrases de test

#Problèmes 
##A la reco de loc
- Déséquilibre dans le corpus d'entraînement. Plus il y a de données plus grande était la probabilité associée à cet auteur là.
</br> On peut tronquer un nombre de phrase, de mots .
- Somme des log prod
- Interpolation : Si la proba est absente il y a des mécanismes de lissage mais on peut aussi considérer que la proba comb de quelque chose est la somme de proba comb de degré inférieur
- Combinaison de modeles ngram
  - différent n
  - mot / caractère
  - reverse()

#Notes du professeur
Quand on déclare un objet complexe on doit déclarer par l'interface LanguageModelInterface. Ca rendra le code bien plus solide </br>
ex : LanguageModelInterface a = new Naive