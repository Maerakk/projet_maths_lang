package authorReco;


import java.util.*;

import langModel.*;
import authorEval.*;


/**
 * Class AuthorRecognizer1: a first author recognition system that recognizes 
 * the author of a sentence by using the language models read from a configuration system.
 * (no unknown author can be detected)
 * 
 * @author N. Hernandez and S. Quiniou (2017)
 *
 */
public class AuthorRecognizer1 extends AuthorRecognizerAbstractClass {
	/**
	 * Map of LanguageModels associated with each author (each author can be 
	 * associated with several language models). 
	 * The keys to the first map are the names of the authors (e.g., "zola"), the keys 
	 * to the second map are the names associated with each file containing a language model 
	 * (e.g., "zola-bigrams"), and the values of the second map are the LanguageModel objects 
	 * built from the file path given in the AuthorConfigurationFile attribute.
	 */
	protected Map<String, Map<String, LanguageModelInterface>> authorLangModelsMap;
		
	
	
	/**
	 * Constructor.
	 * 
	 * @param configFile the file path of the configuration file containing the information 
	 * on the language models (author, name and file path).
	 * @param vocabFile the file path of the file containing the common vocabulary
	 * for all the language models used in the recognition system. 
	 * @param authorFile the file path of the file containing 
	 * the names of the authors recognized by the system.
	 */
	public AuthorRecognizer1(String configFile, String vocabFile, String authorFile) {
		super();
		//instantiates the configLangModels attribute from the configuration file 
		loadAuthorConfigurationFile(configFile);
		//instantiates the vocabularyLM attribute from the vocabulary file 
		loadVocabularyFile(vocabFile);
		//instantiates the author names recognized by the system
		loadAuthorFile(authorFile);
		
		//creates the language model for each language id
		authorLangModelsMap = new HashMap<>();
		
		LanguageModelInterface lm;
		NgramCounts ngramCounts;

		for(String author: configLangModels.getAuthors()){
			for(String idLM: configLangModels.getAuthorNgramCountMap().get(author).keySet()){
				System.out.println(idLM + " : LM created");
				
				//loads the language model 

				// -----------------------------------------------------------------------
				// SET: Choose the model class to use by commenting/uncommenting the right line  
				lm = new NaiveLanguageModel();
				//lm = new LaplaceLanguageModel();
				// ------------------------------------------------------------------------

				ngramCounts = new NgramCounts();
				ngramCounts.readNgramCountsFile(configLangModels.getNgramCountPath(author, idLM));
				lm.setNgramCounts(ngramCounts, vocabularyLM);
				
				//adds the language model to authorLangModelsMap
				addTuple2AuthorLangModelsMap(author, idLM, lm);
			}
		}
	}
	 
	
	/**
	 * Method adding a language model file path to the authorLangModelsMap attribute.
	 * 
	 * @param author the author to consider.
	 * @param lmName the name of the language model to add.
	 * @param lm the LanguageModel to add.
	 */
	private void addTuple2AuthorLangModelsMap(String author, String lmName, LanguageModelInterface lm) {
		Map<String, LanguageModelInterface> lmMap;
		if (! authorLangModelsMap.containsKey(author)) 
			 lmMap = new HashMap<>();
		else 
			lmMap = authorLangModelsMap.get(author);
		 lmMap.put(lmName, lm);
		 authorLangModelsMap.put(author, lmMap);
	}
	
	
	
	
	/**
	 * Method recognizing and returning the author of the given sentence 
	 * (the unknown author can also be picked up).
	 * 
	 * @param sentence the sentence whose author is to recognize.
	 * @return the author of the sentence as recognized by the recognition system.
	 */
	public String recognizeAuthorSentence(String sentence) {
		// TODO
/*       // Ceci est une proposition d'algorithme relativement intuitif.
		 //
		 //	Il s'agit de tester chaque modèle de langue 
		 // et de sélectionner l'auteur dont le modèle de langue aura donné la proba la plus élevée.
		 //
		 // Attention: ne traite pas le problème de corpus déséquilibré
		 // (le fait que les corpus d'entraînement n'ont pas le même nombre de mots)
		 // qui conduit à un modèle de langue estimé sur plus de données à avoir une proba plus élevé 
		 // qu'un autre estimé sur moins de données.

auteurMax = ""
scoreMax = -1
pour chaque auteur de authorLangModelsMap:
__pour chaque nom_modèle de authorLangModelsMap[auteur]
____lm = authorLangModelsMap[auteur][nom_modèle]
____score = lm.getSentenceProb(sentence)
____si ce score est supérieur au précédent supérieur
____alors ce score devient le nouveau supérieur
______et je garde la trace de l'auteur
retourner auteurMax
*/

		String authorMax = "";
		Double scoreMax = -1.0;
		for (String author:authorLangModelsMap.keySet()) {
			// ex : balzac
			for (String name_model : authorLangModelsMap.get(author).keySet()){
				// ex : balzac_bi
				LanguageModelInterface lm = authorLangModelsMap.get(author).get(name_model);
				Double score = lm.getSentenceProb(sentence);
				if(score>scoreMax){
					scoreMax = score;
					authorMax = author;
				}

			}
		}
		return authorMax;
	}
	
	
	
	/**
	 * Main method.
	 * 
	 * @param args arguments of the main method.
	 */
	public static void main(String[] args) {
		// mettre en commentaire les parties non utilisées //


		/* Tests sur les 100 phrases de validation, avec les petits modèles de langage */
		reco_small_author_corpus_100sentences();

		/* Tests sur les 5000 phrases de validation, avec les petits modèles de langage */
		reco_small_author_corpus_5000sentences();


		/* Tests sur les 100 phrases de validation, avec les plus gros modèles de langage
		   que vous devez d'abord avoir construits. */
		reco_author_corpus_100sentences();

		/* Tests sur les 5000 phrases de validation, avec les plus gros modèles de langage
		   que vous devez d'abord avoir construits. */
		reco_author_corpus_5000sentences();


		/* Génération des runs, sur les phrases de test (que vous devez avoir
		   récupérées sur madoc), avec les petits modèles de langage. */
		reco_small_author_corpus_run();

		/* Génération des runs, sur les phrases de test (que vous devez avoir
		   récupérées sur madoc), avec les plus gros modèles de langage que vous devez
		   d'abord avoir construits. */
		reco_author_corpus_run();
	}


	/**
	 * Reconnaissance sur les 100 phrases de validation, avec les petits modèles de langage,
	 * et affichage des scores de reconnaissance pour chaque auteur.
	 */
	public static void reco_small_author_corpus_100sentences(){
		String dirPathLM = "lm/small_author_corpus/";
		String configFilePath = dirPathLM + "fichConfig_bigram_1000sentences.txt";
		String vocabFilePath = dirPathLM + "corpus_20000.vocab";
		String dirPath = "data/small_author_corpus/validation/";
		String authorFilePath = dirPath + "authors.txt";
		String sentenceFilePath = dirPath + "sentences_100sentences.txt";
		String hypFilePath = dirPath + "authors_100sentences_small_author_corpus_hyp-reco1.txt";
		String refFilePath = dirPath + "authors_100sentences_ref.txt";

		create_evaluate_hyp_file(configFilePath, vocabFilePath, authorFilePath,
								 sentenceFilePath, hypFilePath, refFilePath);
	}


	/**
	 * Reconnaissance sur les 5000 phrases de validation, avec les petits modèles de langage,
	 * et affichage des scores de reconnaissance pour chaque auteur.
	 */
	public static void reco_small_author_corpus_5000sentences(){
		String dirPathLM = "lm/small_author_corpus/";
		String configFilePath = dirPathLM + "fichConfig_bigram_1000sentences.txt";
		String vocabFilePath = dirPathLM + "corpus_20000.vocab";
		String dirPath = "data/author_corpus/validation/";
		String authorFilePath = dirPath + "authors.txt";
		String sentenceFilePath = dirPath + "sentences.txt";
		String hypFilePath = dirPath + "authors_5000sentences_small_author_corpus_hyp-reco1.txt";
		String refFilePath = dirPath + "authors_ref.txt";

		create_evaluate_hyp_file(configFilePath, vocabFilePath, authorFilePath,
								 sentenceFilePath, hypFilePath, refFilePath);
	}


	/**
	 * Reconnaissance sur les 100 phrases de validation, avec les plus gros modèles de
	 * langage, et affichage des scores de reconnaissance pour chaque auteur.
	 */
	public static void reco_author_corpus_100sentences(){
		String dirPathLM = "lm/author_corpus/";
		String configFilePath = dirPathLM + "fichConfig_bigram.txt";
		String vocabFilePath = dirPathLM + "corpus_20000.vocab";
		String dirPath = "data/small_author_corpus/validation/";
		String authorFilePath = dirPath + "authors.txt";
		String sentenceFilePath = dirPath + "sentences_100sentences.txt";
		String hypFilePath = dirPath + "authors_100sentences_hyp-reco1.txt";
		String refFilePath = dirPath + "authors_100sentences_ref.txt";

		create_evaluate_hyp_file(configFilePath, vocabFilePath, authorFilePath,
								 sentenceFilePath, hypFilePath, refFilePath);
	}


	/**
	 * Reconnaissance sur les 5000 phrases de validation, avec les plus gros modèles de
	 * langage, et affichage des scores de reconnaissance pour chaque auteur.
	 */
	public static void reco_author_corpus_5000sentences(){
		String dirPathLM = "lm/author_corpus/";
		String configFilePath = dirPathLM + "fichConfig_bigram.txt";
		String vocabFilePath = dirPathLM + "corpus_20000.vocab";
		String dirPath = "data/author_corpus/validation/";
		String authorFilePath = dirPath + "authors.txt";
		String sentenceFilePath = dirPath + "sentences.txt";
		String hypFilePath = dirPath + "authors_5000sentences_hyp-reco1.txt";
		String refFilePath = dirPath + "authors_ref.txt";

		create_evaluate_hyp_file(configFilePath, vocabFilePath, authorFilePath,
								 sentenceFilePath, hypFilePath, refFilePath);
	}


	/**
	 * Reconnaissance sur les phrases de test, avec les petits modèles de
	 * langage, pour créer un fichier run.
	 */
	public static void reco_small_author_corpus_run(){
		String dirPathLM = "lm/small_author_corpus/";
		String configFilePath = dirPathLM + "fichConfig_bigram_1000sentences.txt";
		String vocabFilePath = dirPathLM + "corpus_20000.vocab";
		String dirPath = "data/author_corpus/test/";
		String authorFilePath = dirPath + "authors.txt";
		String sentenceFilePath = dirPath + "sentences.txt";
		String hypFilePath = dirPath + "authors_small_author_corpus_hyp-reco1.txt";

		create_hyp_file(configFilePath, vocabFilePath, authorFilePath,
						sentenceFilePath, hypFilePath);
	}


	/**
	 * Reconnaissance sur les phrases de test, avec les plus gros modèles de
	 * langage, pour créer un fichier run.
	 */
	public static void reco_author_corpus_run(){
		String dirPathLM = "lm/author_corpus/";
		String configFilePath = dirPathLM + "fichConfig_bigram.txt";
		String vocabFilePath = dirPathLM + "corpus_20000.vocab";
		String dirPath = "data/author_corpus/test/";
		String authorFilePath = dirPath + "authors.txt";
		String sentenceFilePath = dirPath + "sentences.txt";
		String hypFilePath = dirPath + "authors_hyp-reco1.txt";

		create_hyp_file(configFilePath, vocabFilePath, authorFilePath,
						sentenceFilePath, hypFilePath);
	}


	public static void create_hyp_file(String configFilePath, String vocabFilePath, String authorFilePath,
									   String sentenceFilePath, String hypFilePath){
		//initialization of the recognition system
		AuthorRecognizer1 reco1 = new AuthorRecognizer1(configFilePath, vocabFilePath, authorFilePath);

		//computation of the hypothesis author file
		reco1.recognizeFileLanguage(sentenceFilePath, hypFilePath);
	}


	public static void create_evaluate_hyp_file(String configFilePath, String vocabFilePath,
												String authorFilePath, String sentenceFilePath,
												String hypFilePath, String refFilePath) {

		//initialization of the recognition system
		AuthorRecognizer1 reco1 = new AuthorRecognizer1(configFilePath, vocabFilePath, authorFilePath);

		//computation of the hypothesis author file
		reco1.recognizeFileLanguage(sentenceFilePath, hypFilePath);

		//computation of the performance of the recognition system
		System.out.println("\nPerformances du système : \n"
				+ RecognizerPerformance.evaluateAuthors(refFilePath, hypFilePath));
	}

}
