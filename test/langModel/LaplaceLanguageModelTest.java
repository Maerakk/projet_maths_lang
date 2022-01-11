package langModel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class LaplaceLanguageModelTest {

    NgramCountsInterface ngram = new NgramCounts();
    VocabularyInterface vocab = new Vocabulary();
    LanguageModelInterface bigramm = new LaplaceLanguageModel();


    public void initiate() {
        vocab.readVocabularyFile("lm/small_corpus/vocabulary1_in.txt");
        ngram.scanTextFile("data/small_corpus/corpus_fr_train.txt",vocab,2);
        bigramm.setNgramCounts(ngram,vocab);
    }

    @Test
    public void getNgramProb1() {
        // before initation, getNgramProb should return 0 for any value
        assertEquals(Double.valueOf(-1) , bigramm.getNgramProb("<s>"));

        initiate();
        // aftrer initiation, it should return 1 for <s>
        assertEquals(Double.valueOf(1), bigramm.getNgramProb("<s>"));
        // and the valid probability => "a b" doesn't exist but is smoothed
        assertEquals(Double.valueOf(1.0/11.0), bigramm.getNgramProb("a b"));
        //                          => "<s> Denis" should have a 1/14 probability
        assertEquals(Double.valueOf(1.0/14.0),bigramm.getNgramProb("<s> Denis"));
    }
}