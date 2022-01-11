package langModel;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class NaiveLanguageModelTest {


    NgramCountsInterface ngram = new NgramCounts();
    VocabularyInterface vocab = new Vocabulary();
    LanguageModelInterface bigramm = new NaiveLanguageModel();

    @Before
    public void initiate() {
        vocab.readVocabularyFile("lm/small_corpus/vocabulary1_in.txt");
        ngram.scanTextFile("data/small_corpus/corpus_fr_train.txt",vocab,2);
        bigramm.setNgramCounts(ngram,vocab);
    }

    @Test
    public void getLMOrder1() {
        assertEquals(2,bigramm.getLMOrder());
    }


    @Test
    public void getNgramProb1() {
//        assertEquals();
    }

    @Test
    public void getSentenceProb1() {
    }
}