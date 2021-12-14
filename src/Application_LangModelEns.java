//Utiliser l'un ou l'autre des imports
import langModel.*; //pour tester la bibliothèque fournie
//import myLangModel.*; //pour tester les classes implémentées


public class Application_LangModelEns {

	public static void main(String[] args) {
		String sentence1 = "<s> antoine écoute une chanson </s>";
		String sentence2 = "<s> lionel écoute une chanson </s>";


		/* création des vocabulaires */

		VocabularyInterface vocab1, vocab2;

		//création du premier vocabulaire

		vocab1 = new Vocabulary();

		// lecture du fichier "lm/small_corpus/vocabulary1_in.txt" pour initialiser le vocabulaire

		vocab1.readVocabularyFile("lm/small_corpus/vocabulary1_in.txt");

		// écriture du vocabulaire dans le fichier "lm/small_corpus/vocabulary1_out.txt"

		vocab1.writeVocabularyFile("lm/small_corpus/vocabulary1_out.txt");

		//création du second vocabulaire

		vocab2 = new Vocabulary();

		// lecture du fichier "lm/small_corpus/vocabulary2_in.txt" pour initialiser le vocabulaire

		vocab2.readVocabularyFile("lm/small_corpus/vocabulary2_in.txt");

		// écriture du vocabulaire dans le fichier "lm/small_corpus/vocabulary2_out.txt"

		vocab2.writeVocabularyFile("lm/small_corpus/vocabulary2_out.txt");



		/* création des modèles de langage */

		NgramCountsInterface ngramCounts_bigram_vocab1, ngramCounts_bigram_vocab2;
		LanguageModelInterface lm_bigram_vocab1, lm_bigram_laplace_vocab1, lm_bigram_vocab2, lm_bigram_laplace_vocab2;

		//création de l'objet ngramCounts_bigram_vocab1,
		// à partir du corpus d'apprentissage "data/small_corpus/corpus_fr_train.txt" et de vocab1
		// puis écriture dans le fichier "lm/small_corpus/ngramCounts_bigram_vocabulary1.txt"

		ngramCounts_bigram_vocab1 = new NgramCounts();
		ngramCounts_bigram_vocab1.scanTextFile("data/small_corpus/corpus_fr_train.txt",vocab1,2);
		ngramCounts_bigram_vocab1.writeNgramCountFile("lm/small_corpus/ngramCounts_bigram_vocabulary1.txt");


		//création du modèle bigramme sans lissage (lm_bigram_vocab1),
		// à partir de ngramCounts_bigram_vocab1 et de vocab1

		lm_bigram_vocab1 = new NaiveLanguageModel();
		lm_bigram_vocab1.setNgramCounts(ngramCounts_bigram_vocab1,vocab1);

		//création du modèle bigramme avec lissage (lm_bigram_laplace_vocab1),
		// à partir de ngramCounts_bigram_vocab1 et de vocab1

		lm_bigram_laplace_vocab1 = new LaplaceLanguageModel();
		lm_bigram_laplace_vocab1.setNgramCounts(ngramCounts_bigram_vocab1,vocab1);

		//création de l'objet ngramCounts_bigram_vocab2,
		// à partir du corpus d'apprentissage "data/small_corpus/corpus_fr_train.txt" et de vocab2
		// puis écriture dans le fichier "lm/small_corpus/ngramCounts_bigram_vocabulary2.txt"

		ngramCounts_bigram_vocab2 = new NgramCounts();
		ngramCounts_bigram_vocab2.scanTextFile("data/small_corpus/corpus_fr_train.txt",vocab2,2);
		ngramCounts_bigram_vocab1.writeNgramCountFile("lm/small_corpus/ngramCounts_bigram_vocabulary2.txt");


		//création du modèle bigramme sans lissage (lm_bigram_vocab2),
		// à partir de ngramCounts_bigram_vocab2 et de vocab2

		lm_bigram_vocab2 = new NaiveLanguageModel();
		lm_bigram_vocab2.setNgramCounts(ngramCounts_bigram_vocab2,vocab2);

		//création du modèle bigramme avec lissage (lm_bigram_laplace_vocab2),
		// à partir de ngramCounts_bigram_vocab2 et de vocab2

		lm_bigram_laplace_vocab2 = new LaplaceLanguageModel();
		lm_bigram_laplace_vocab2.setNgramCounts(ngramCounts_bigram_vocab2,vocab2);


		/* utilisation des modèles de langage */

		//calcul de la proba des 2 phrases avec lm_bigram_vocab1 et affichage des probas
		System.out.println();
		System.out.println("Avec lm_bigram_vocab1 : proba(sentence1)="+lm_bigram_vocab1.getSentenceProb(sentence1));
		System.out.println("Avec lm_bigram_vocab1 : proba(sentence2)="+lm_bigram_vocab1.getSentenceProb(sentence2));

		//calcul de la proba des 2 phrases avec lm_bigram_laplace_vocab1 et affichage des probas
		System.out.println();
		System.out.println("Avec lm_bigram_laplace_vocab1 : proba(sentence1)="+lm_bigram_laplace_vocab1.getSentenceProb(sentence1));
		System.out.println("Avec lm_bigram_laplace_vocab1 : proba(sentence2)="+lm_bigram_laplace_vocab1.getSentenceProb(sentence2));

		//calcul de la proba des 2 phrases avec lm_bigram_vocab2 et affichage des probas
		System.out.println();
		System.out.println("Avec lm_bigram_vocab2 : proba(sentence1)="+lm_bigram_vocab2.getSentenceProb(sentence1));
		System.out.println("Avec lm_bigram_vocab2 : proba(sentence2)="+lm_bigram_vocab2.getSentenceProb(sentence2));

		//calcul de la proba des 2 phrases avec lm_bigram_laplace_vocab2 et affichage des probas
		System.out.println();
		System.out.println("Avec lm_bigram_laplace_vocab2 : proba(sentence1)="+lm_bigram_laplace_vocab2.getSentenceProb(sentence1));
		System.out.println("Avec lm_bigram_laplace_vocab2 : proba(sentence2)="+lm_bigram_laplace_vocab2.getSentenceProb(sentence2));




		/* test des méthodes définies dans NgramUtils */

		/*
		String sentenceOOV1_vocab1 = NgramUtils.getStringOOV(sentence1, vocab1);
		System.out.println("sentence1 after processing words not in vocab1 : " + sentenceOOV1_vocab1);

		String sentenceOOV1_vocab2 = NgramUtils.getStringOOV(sentence1, vocab2);
		System.out.println("sentence1 after processing words not in vocab2 : " + sentenceOOV1_vocab2);

		String sentenceOOV2_vocab1 = NgramUtils.getStringOOV(sentence2, vocab1);
		System.out.println("sentence2 after processing words not in vocab1 : " + sentenceOOV2_vocab1);

		String sentenceOOV2_vocab2 = NgramUtils.getStringOOV(sentence2, vocab2);
		System.out.println("sentence2 after processing words not in vocab2 : " + sentenceOOV2_vocab2);
		*/

	}

}
