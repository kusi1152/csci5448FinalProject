import org.example.DifficultWordFactory;
import org.example.EasyWordFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;

class WordleGameTest {

    @Test
    void testEasyWordSelected() {
        List<String> words = List.of("apple", "brave", "crisp", "dream", "eagle", "flame", "ghost", "honey", "ivory", "joker");
        EasyWordFactory factory = new EasyWordFactory(words);
        String selectedWord = factory.getWord();
        assertTrue(selectedWord.equals("apple") || selectedWord.equals("brave"));
    }

    @Test
    void testWordSelected_ShouldBeFromLastFiveWords() {
        List<String> words = List.of("apple", "brave", "crisp", "dream", "eagle", "flame", "ghost", "honey", "ivory", "joker");
        DifficultWordFactory factory = new DifficultWordFactory(words);
        String selectedWord = factory.getWord();
        List<String> lastFiveWords = List.of("flame", "ghost", "honey", "ivory", "joker");
        assertTrue(lastFiveWords.contains(selectedWord));
    }
}
