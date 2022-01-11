package myLangModel;


import java.util.ArrayList;
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
		int counts = ngramCounts.getCounts(ngram);
		int v = ngramCounts.getTotalWordNumber();
		Set<String> ngrams = ngramCounts.getNgrams();
		for (int nGram=0; nGram<v; nGram++){
			if (ngrams.get(nGram) == ngram){

			}
		}
		return 0.0;
	}
}
