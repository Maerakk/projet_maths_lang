package myLangModel;

import java.util.List;

/**
 * Class NaiveLanguageModel: class implementing the interface LanguageModelInterface by creating a naive language model,
 * i.e. a n-gram language model with no smoothing.
 * 
 * @author ... (2017)
 *
 */
public class NaiveLanguageModel implements LanguageModelInterface {
	/**
	 * The NgramCountsInterface corresponding to the language model.
	 */
	protected myLangModel.NgramCountsInterface ngramCounts;
	
	/**
	 * The vocabulary of the language model.
	 */
	protected VocabularyInterface vocabulary;
	
	
	/**
	 * Constructor.
	 */
	public NaiveLanguageModel(){
		//TODO
	}
	

	@Override
	public int getLMOrder() {
		// TODO Auto-generated method stub
		if(this.ngramCounts == null){
			return -1;
		}
		return this.ngramCounts.getMaximalOrder();
	}

	@Override
	public void setNgramCounts(NgramCountsInterface ngramCounts, VocabularyInterface vocab) {
		// TODO Auto-generated method stub
		this.ngramCounts = ngramCounts;
		this.vocabulary = vocab;
	}

	@Override
	public Double getNgramProb(String ngram) {
		// TODO Auto-generated method stub
		// Si le vocabulaire n'est pas instancié on renvoit 0
		if(this.vocabulary == null){
			return 0.0;
		}
		// On traite le ngram pour ne pas avoir de mot inconnu
		String ngramOOV = NgramUtils.getStringOOV(ngram, this.vocabulary);
		// On récupére l'historique correspondant à l'ordre maximal
		String history = NgramUtils.getHistory(ngramOOV, this.getLMOrder());
		// On déclare la probabilité
		double prob;
		// S'il n'y a pas d'historique
		if (history.equals("")) {
			// Soit le mot courant est <s> donc un début de phrase
			if (ngramOOV.equals("<s>")) {
				// Et la probabilité est de 1
				prob = 1.0;
			} else {
				// Soit c'est un autre mot, on est donc en Unigram
				// Et la probabilité est le nombre de caractère courant
				// Divisé par le nombre total de mots dans le corpus
				prob = (double)this.ngramCounts.getCounts(ngramOOV)/(double) this.ngramCounts.getTotalWordNumber();
			}
		} else {
			// S'il y a un historique, la probabilité correspond au nombre de caractère courant
			// Divisé par le nombre de caractère de l'historique
			prob =  (double)this.ngramCounts.getCounts(ngramOOV)/(double)this.ngramCounts.getCounts(history);
		}
		// Dans le cas de mots inconnus, il y a une division par 0 et on obtient NaN alors qu'on devrait renvoyer 0
		if(Double.isNaN(prob)){
			prob = 0.0;
		}
		return prob;
	}

	@Override
	public Double getSentenceProb(String sentence) {
		// TODO Auto-generated method stub
		// Utilisation de la méthode decomposeIntoNgrams pour avoir une liste d'unigrame/bigrame/...
		List<String> ngrams = NgramUtils.decomposeIntoNgrams(sentence,this.getLMOrder());
		// La probabilité d'une phrase correspond au produit de la probabilité de ces Ngrams
		// On doit donc intialiser la probabilité à 1 et non à 0
		Double prob = 1.0;
		for (String ngram:ngrams) {
			prob = prob*this.getNgramProb(ngram);
		}
		return prob;
	}

}
