import java.io.IOException;
import java.util.ArrayList;
import service.DictionaryJsonService;
import service.DictionaryService;

public class Languages {

  public static void main(String[] args) throws IOException {
    final DictionaryJsonService dictionaryJsonService = new DictionaryJsonService();
    final DictionaryService dictionaryService = new DictionaryService(
        new ArrayList<>(dictionaryJsonService.getAllDictionaries().values()));

  }
}
