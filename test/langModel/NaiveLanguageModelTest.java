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
        // before the initiation, getLMOrder should return -1
        assertEquals(-1,bigramm.getLMOrder());

        initiate();
        // after the initiation, it should return 2 since we work with bigramms
        assertEquals(2,bigramm.getLMOrder());
    }


    @Test
    public void getNgramProb1() {
        // before initation, getNgramProb should return 0 for any value
        assertEquals(Double.valueOf(0) , bigramm.getNgramProb("<s>"));

        initiate();
        // aftrer initiation, it should return 1 for <s>
        assertEquals(Double.valueOf(1), bigramm.getNgramProb("<s>"));
        // and the valid probability => "a b" doesn't exists
        assertEquals(Double.valueOf(0), bigramm.getNgramProb("a b"));
        //                          => "<s> Denis" should have a 1/14 probability
        assertEquals(Double.valueOf(1.0/3),bigramm.getNgramProb("<s> denis"));
    }

    @Test
    public void getSentenceProb1() {
        // before initiation, getSentenceProb should return 0 for any sentence
        assertEquals(Double.valueOf(0),bigramm.getSentenceProb("<s> antoine écoute une chanson </s>"));

        initiate();
        // after initiation, it should return the valid probability
        // => "a b c" does not exists so 0
        assertEquals(Double.valueOf(0), bigramm.getSentenceProb("a b c"));
        // => "<s> Antoine écoute une chanson </s>" 2/36
        assertEquals(Double.valueOf(1.0/18),bigramm.getSentenceProb("<s> antoine écoute une chanson </s>"));
    }
}