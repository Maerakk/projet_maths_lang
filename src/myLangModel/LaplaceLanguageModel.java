package myLangModel;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

/**
 * Class LaplaceLanguageModel: class inheriting the class NaiveLanguageModel by creating 
 * a n-gram language model using a Laplace smoothing.
 * 
 * @author ... (2017)
 *
 */
public class LaplaceLanguageModel extends NaiveLanguageModel {

	@Override
	public Double getNgramProb(String ngram) {
		//TODO

		// Si le vocabulaire n'est pas instancié on renvoit -1
		if(this.vocabulary == null){
			return -1.0;
		}
		// On traite le ngram pour ne pas avoir de mot inconnu
		String ngramOOV = NgramUtils.getStringOOV(ngram, this.vocabulary);
		// On récupère l'historique correspondant à l'ordre maximum
		String history = NgramUtils.getHistory(ngram, this.getLMOrder());
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
				// Et la probabilité est le nombre de caractère courant + 1
				// Divisé par le nombre total de mots dans le corpus + la taille du vocabulaire -1
				prob = (double)this.ngramCounts.getCounts(ngramOOV)+1.0/(double)( this.ngramCounts.getTotalWordNumber() + this.vocabulary.getSize()-1);
			}
		} else {
			// S'il y a un historique, la probabilité correspond au nombre de char courant +1
			// divisé par le nombre de charactère de l'historique + la taille du vocabulaire -1
			prob = (double)this.ngramCounts.getCounts(ngramOOV)+1.0/(double) (this.ngramCounts.getCounts(history) + this.vocabulary.getSize()-1.0);

		}
		// Dans le cas de mots inconnus, il y a une division par 0 et on obtient NaN alors qu'on devrait renvoyer 0
		if(Double.isNaN(prob)){
			prob = 0.0;
		}
		return prob;
	}
}
