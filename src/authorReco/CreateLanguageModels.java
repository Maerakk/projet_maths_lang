package authorReco;


import myLangModel.NgramCountsInterface;

/**
 * Class CreateLanguageModels: a class to create the language models used in the recognition systems.
 * 
 * @author N. Hernandez and S. Quiniou (2017)
 *
 */
public class CreateLanguageModels {

  /**
   * Main method.
   * 
   * Ci-dessous un exemple de "programme" visant à créer les "modèles de langue" 
   * utilisés dans une application de reconnaissance d'auteurs. 
   * Ce programme est en théorie utilisée qu'une seule fois pour générer 
   * les modèles dont vous avez besoin.
   * Ici le programme est conçu pour générer un modèle de langue par auteur et 
   * il y a moyen de spécifier "en dure" dans le code l'ordre des modèles et 
   * les corpus à considérer pour construire les modèles (small ou normal). 
   * Vous pouvez modifier ce code (l'indiquer dans votre rapport) comme vous 
   * le souhaitez et pas seulement en modifiant les valeurs des paramètres.
   * 
   * @param args arguments of the main method.
   */
  public static void main(String[] args) {
    
    /* PARAMETRES */

    // Spécifier ici le sous-répertoire de data que vous souhaitez considérer
    // Pour rappel "small_author_corpus" contient un extrait des corpus de chaque auteur 
    // et vise à faire des tests fonctionnels de vos développements. 
    // Une fois votre code opérationnel, utilisez "author_corpus" pour générer des
    // modèles plus riches mais aussi de taille plus conséquente. 
    //String repCorpus = "small_author_corpus";
    String repCorpus = "author_corpus";
    
    // Spécifier ici l'ordre de votre modèle de langue 
    int order = 3;

    // Spécifier ici le nom 
    String prefLM = "trigram";
    

    /* CHARGEMENT DU VOCABULAIRE */
   // ! on utilise le même vocabulaire pour small et le corpus complet

    //création du vocabulaire
    myLangModel.VocabularyInterface vocab = new myLangModel.Vocabulary();
    vocab.readVocabularyFile("lm/"+repCorpus+"/corpus_20000.vocab");
    //vocab.readVocabularyFile("lm/small_author_corpus/corpus_20000.vocab");


    /* CREATION DES MODELES DE LANGUES*/

    //create the language models from the training corpora
    NgramCountsInterface ngramCounts_vocab;
    String auteur;

    //création du modèle de langage sans lissage, avec le vocabulaire, pour le corpus balzac.txt
    auteur = "balzac";
    ngramCounts_vocab = new myLangModel.NgramCounts();
    ngramCounts_vocab.scanTextFile("data/"+repCorpus+"/train/"+auteur+".txt", vocab, order);
    ngramCounts_vocab.writeNgramCountFile("lm/"+repCorpus+"/"+prefLM+"_"+auteur+".lm");
    System.out.println("--> Modèle de langage créé pour "+auteur);

    //création du modèle de langage sans lissage, avec le vocabulaire, pour le corpus hugo.txt
    auteur = "hugo";
    ngramCounts_vocab = new myLangModel.NgramCounts();
    ngramCounts_vocab.scanTextFile("data/"+repCorpus+"/train/"+auteur+".txt", vocab, order);
    ngramCounts_vocab.writeNgramCountFile("lm/"+repCorpus+"/"+prefLM+"_"+auteur+".lm");
    System.out.println("--> Modèle de langage créé pour "+auteur);

    //création du modèle de langage sans lissage, avec le vocabulaire, pour le corpus maupassant.txt
    auteur = "maupassant";
    ngramCounts_vocab = new myLangModel.NgramCounts();
    ngramCounts_vocab.scanTextFile("data/"+repCorpus+"/train/"+auteur+".txt", vocab, order);
    ngramCounts_vocab.writeNgramCountFile("lm/"+repCorpus+"/"+prefLM+"_"+auteur+".lm");
    System.out.println("--> Modèle de langage créé pour "+auteur);

    //création du modèle de langage sans lissage, avec le vocabulaire, pour le corpus moliere.txt
    auteur = "moliere";
    ngramCounts_vocab = new myLangModel.NgramCounts();
    ngramCounts_vocab.scanTextFile("data/"+repCorpus+"/train/"+auteur+".txt", vocab, order);
    ngramCounts_vocab.writeNgramCountFile("lm/"+repCorpus+"/"+prefLM+"_"+auteur+".lm");
    System.out.println("--> Modèle de langage créé pour "+auteur);

    //création du modèle de langage sans lissage, avec le vocabulaire, pour le corpus montaigne.txt
    auteur = "montaigne";
    ngramCounts_vocab = new myLangModel.NgramCounts();
    ngramCounts_vocab.scanTextFile("data/"+repCorpus+"/train/"+auteur+".txt", vocab, order);
    ngramCounts_vocab.writeNgramCountFile("lm/"+repCorpus+"/"+prefLM+"_"+auteur+".lm");
    System.out.println("--> Modèle de langage créé pour "+auteur);

    //création du modèle de langage sans lissage, avec le vocabulaire, pour le corpus sand.txt
    auteur = "sand";
    ngramCounts_vocab = new myLangModel.NgramCounts();
    ngramCounts_vocab.scanTextFile("data/"+repCorpus+"/train/"+auteur+".txt", vocab, order);
    ngramCounts_vocab.writeNgramCountFile("lm/"+repCorpus+"/"+prefLM+"_"+auteur+".lm");
    System.out.println("--> Modèle de langage créé pour "+auteur);

    //création du modèle de langage sans lissage, avec le vocabulaire, pour le corpus tocqueville.txt
    auteur = "tocqueville";
    ngramCounts_vocab = new myLangModel.NgramCounts();
    ngramCounts_vocab.scanTextFile("data/"+repCorpus+"/train/"+auteur+".txt", vocab, order);
    ngramCounts_vocab.writeNgramCountFile("lm/"+repCorpus+"/"+prefLM+"_"+auteur+".lm");
    System.out.println("--> Modèle de langage créé pour "+auteur);

    //création du modèle de langage sans lissage, avec le vocabulaire, pour le corpus tolstoi.txt
    auteur = "tolstoi";
    ngramCounts_vocab = new myLangModel.NgramCounts();
    ngramCounts_vocab.scanTextFile("data/"+repCorpus+"/train/"+auteur+".txt", vocab, order);
    ngramCounts_vocab.writeNgramCountFile("lm/"+repCorpus+"/"+prefLM+"_"+auteur+".lm");
    System.out.println("--> Modèle de langage créé pour "+auteur);

    //création du modèle de langage sans lissage, avec le vocabulaire, pour le corpus verne.txt
    auteur = "verne";
    ngramCounts_vocab = new myLangModel.NgramCounts();
    ngramCounts_vocab.scanTextFile("data/"+repCorpus+"/train/"+auteur+".txt", vocab, order);
    ngramCounts_vocab.writeNgramCountFile("lm/"+repCorpus+"/"+prefLM+"_"+auteur+".lm");
    System.out.println("--> Modèle de langage créé pour "+auteur);

    //création du modèle de langage sans lissage, avec le vocabulaire, pour le corpus zola.txt
    auteur = "zola";
    ngramCounts_vocab = new myLangModel.NgramCounts();
    ngramCounts_vocab.scanTextFile("data/"+repCorpus+"/train/"+auteur+".txt", vocab, order);
    ngramCounts_vocab.writeNgramCountFile("lm/"+repCorpus+"/"+prefLM+"_"+auteur+".lm");
    System.out.println("--> Modèle de langage créé pour "+auteur);
  }

}
