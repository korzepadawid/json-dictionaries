import domain.Dictionary;
import java.io.IOException;
import java.util.Map;
import service.DictionaryJsonService;

public class Languages {

  public static void main(String[] args) throws IOException {
    DictionaryJsonService dictionaryJsonService = new DictionaryJsonService();
    Map<String, Dictionary> allDictionaries = dictionaryJsonService.getAllDictionaries();

    allDictionaries.forEach((s, dictionary) -> System.out.println(s));
  }
}
