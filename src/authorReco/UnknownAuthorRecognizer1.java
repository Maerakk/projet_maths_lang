package authorReco;


import java.util.*;

import authorEval.*;
import langModel.*;


/**
 * Class UnknownAuthorRecognizer1: a first author recognition system that recognizes 
 * the author of a sentence by using the language models read from a configuration system.
 * (unknown authors can be detected)
 * 
 * @author N. Hernandez and S. Quiniou (2017)
 *
 */
public class UnknownAuthorRecognizer1 extends AuthorRecognizer1 {

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
	public UnknownAuthorRecognizer1(String configFile, String vocabFile, String authorFile) {
		super(configFile, vocabFile, authorFile);
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

		if(scoreMax < Math.pow(10,-200)){
			return UNKNOWN_AUTHOR;
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
		//reco_small_author_corpus_100sentences();

		/* Tests sur les 119 phrases de validation, dont 19 phrases d'un auteur inconnu,
		   avec les petits modèles de langage. */
		//reco_small_author_corpus_119sentences();

		/* Tests sur les 5000 phrases de validation, avec les petits modèles de langage */
		//reco_small_author_corpus_5000sentences();

		/* Tests sur les 5019 phrases de validation, dont 19 phrases d'un auteur inconnu,
		   avec les petits modèles de langage. */
		//reco_small_author_corpus_5019sentences();


		/* Tests sur les 100 phrases de validation, avec les plus gros modèles de langage
		   que vous devez d'abord avoir construits. */
		//reco_author_corpus_100sentences();

		/* Tests sur les 119 phrases de validation, dont 19 phrases d'un auteur inconnu,
		   avec les plus gros modèles de langage que vous devez d'abord avoir construits. */
		//reco_author_corpus_119sentences();

		/* Tests sur les 5000 phrases de validation, avec les plus gros modèles de langage
		   que vous devez d'abord avoir construits. */
		reco_author_corpus_5000sentences();

		/* Tests sur les 5019 phrases de validation, dont 19 phrases d'un auteur inconnu,
		   avec les plus gros modèles de langage que vous devez d'abord avoir construits. */
		// reco_author_corpus_5019sentences();


		/* Génération des runs, sur les phrases de test (que vous devez avoir
		   récupérées sur madoc), avec les petits modèles de langage. */
		//reco_small_author_corpus_run();

		/* Génération des runs, sur les phrases de test (que vous devez avoir
		   récupérées sur madoc), avec les plus gros modèles de langage que vous devez
		   d'abord avoir construits. */
		//reco_author_corpus_run();
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
		String hypFilePath = dirPath + "authors_100sentences_small_author_corpus_hyp-recoUnknown1.txt";
		String refFilePath = dirPath + "authors_100sentences_ref.txt";

		create_evaluate_unk_hyp_file(configFilePath, vocabFilePath, authorFilePath,
									 sentenceFilePath, hypFilePath, refFilePath);
	}


	/**
	 * Reconnaissance sur les 119 phrases de validation, dont 19 phrases d'un auteur
	 * inconnu, avec les petits modèles de langage, et affichage des scores de
	 * reconnaissance pour chaque auteur.
	 */
	public static void reco_small_author_corpus_119sentences(){
		String dirPathLM = "lm/small_author_corpus/";
		String configFilePath = dirPathLM + "fichConfig_bigram_1000sentences.txt";
		String vocabFilePath = dirPathLM + "corpus_20000.vocab";
		String dirPath = "data/small_author_corpus/validation/";
		String authorFilePath = dirPath + "authors.txt";
		String sentenceFilePath = dirPath + "sentences_test-unk_100sentences.txt";
		String hypFilePath = dirPath + "authors_119sentences_small_author_corpus_hyp-recoUnknown1.txt";
		String refFilePath = dirPath + "authors_test-unk_100sentences_ref.txt";

		create_evaluate_unk_hyp_file(configFilePath, vocabFilePath, authorFilePath,
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
		String hypFilePath = dirPath + "authors_5000sentences_small_author_corpus_hyp-recoUnknown1.txt";
		String refFilePath = dirPath + "authors_ref.txt";

		create_evaluate_unk_hyp_file(configFilePath, vocabFilePath, authorFilePath,
								 	 sentenceFilePath, hypFilePath, refFilePath);
	}


	/**
	 * Reconnaissance sur les 5019 phrases de validation, dont 19 phrases d'un auteur
	 * inconnu, avec les petits modèles de langage, et affichage des scores de
	 * reconnaissance pour chaque auteur.
	 */
	public static void reco_small_author_corpus_5019sentences(){
		String dirPathLM = "lm/small_author_corpus/";
		String configFilePath = dirPathLM + "fichConfig_bigram_1000sentences.txt";
		String vocabFilePath = dirPathLM + "corpus_20000.vocab";
		String dirPath = "data/author_corpus/validation/";
		String authorFilePath = dirPath + "authors.txt";
		String sentenceFilePath = dirPath + "sentences_test-unk.txt";
		String hypFilePath = dirPath + "authors_5019sentences_small_author_corpus_hyp-recoUnknown1.txt";
		String refFilePath = dirPath + "authors_test-unk_ref.txt";

		create_evaluate_unk_hyp_file(configFilePath, vocabFilePath, authorFilePath,
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
		String hypFilePath = dirPath + "authors_100sentences_hyp-recoUnknown1.txt";
		String refFilePath = dirPath + "authors_100sentences_ref.txt";

		create_evaluate_unk_hyp_file(configFilePath, vocabFilePath, authorFilePath,
								 	 sentenceFilePath, hypFilePath, refFilePath);
	}


	/**
	 * Reconnaissance sur les 119 phrases de validation, dont 19 phrases d'un auteur
	 * inconnu, avec les plus gros modèles de langage, et affichage des scores de
	 * reconnaissance pour chaque auteur.
	 */
	public static void reco_author_corpus_119sentences(){
		String dirPathLM = "lm/author_corpus/";
		String configFilePath = dirPathLM + "fichConfig_bigram.txt";
		String vocabFilePath = dirPathLM + "corpus_20000.vocab";
		String dirPath = "data/small_author_corpus/validation/";
		String authorFilePath = dirPath + "authors.txt";
		String sentenceFilePath = dirPath + "sentences_test-unk_100sentences.txt";
		String hypFilePath = dirPath + "authors_119sentences_hyp-recoUnknown1.txt";
		String refFilePath = dirPath + "authors_test-unk_100sentences_ref.txt";

		create_evaluate_unk_hyp_file(configFilePath, vocabFilePath, authorFilePath,
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
		String hypFilePath = dirPath + "authors_5000sentences_hyp-recoUnknown1.txt";
		String refFilePath = dirPath + "authors_ref.txt";

		create_evaluate_unk_hyp_file(configFilePath, vocabFilePath, authorFilePath,
								 	 sentenceFilePath, hypFilePath, refFilePath);
	}


	/**
	 * Reconnaissance sur les 5019 phrases de validation, dont 19 phrases d'un auteur
	 * inconnu, avec les plus gros modèles de langage, et affichage des scores de
	 * reconnaissance pour chaque auteur.
	 */
	public static void reco_author_corpus_5019sentences(){
		String dirPathLM = "lm/author_corpus/";
		String configFilePath = dirPathLM + "fichConfig_bigram.txt";
		String vocabFilePath = dirPathLM + "corpus_20000.vocab";
		String dirPath = "data/author_corpus/validation/";
		String authorFilePath = dirPath + "authors.txt";
		String sentenceFilePath = dirPath + "sentences_test-unk.txt";
		String hypFilePath = dirPath + "authors_5019sentences_hyp-recoUnknown1.txt";
		String refFilePath = dirPath + "authors_test-unk_ref.txt";

		create_evaluate_unk_hyp_file(configFilePath, vocabFilePath, authorFilePath,
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
		String hypFilePath = dirPath + "authors_small_author_corpus_hyp-recoUnknown1.txt";

		create_unk_hyp_file(configFilePath, vocabFilePath, authorFilePath, sentenceFilePath, hypFilePath);
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
		String hypFilePath = dirPath + "authors_hyp-recoUnknown1.txt";

		create_unk_hyp_file(configFilePath, vocabFilePath, authorFilePath, sentenceFilePath, hypFilePath);
	}



	public static void create_unk_hyp_file(String configFilePath, String vocabFilePath, String authorFilePath,
										   String sentenceFilePath, String hypFilePath){
		//initialization of the recognition system
		UnknownAuthorRecognizer1 reco1 = new UnknownAuthorRecognizer1(configFilePath, vocabFilePath, authorFilePath);

		//computation of the hypothesis author file
		reco1.recognizeFileLanguage(sentenceFilePath, hypFilePath);
	}


	public static void create_evaluate_unk_hyp_file(String configFilePath, String vocabFilePath,
													String authorFilePath, String sentenceFilePath,
													String hypFilePath, String refFilePath) {

		//initialization of the recognition system
		UnknownAuthorRecognizer1 reco1 = new UnknownAuthorRecognizer1(configFilePath, vocabFilePath, authorFilePath);

		//computation of the hypothesis author file
		reco1.recognizeFileLanguage(sentenceFilePath, hypFilePath);

		//computation of the performance of the recognition system
		System.out.println("\nPerformances du système : \n"
				+ RecognizerPerformance.evaluateAuthors(refFilePath, hypFilePath));
	}
}
