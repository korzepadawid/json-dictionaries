import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.DictionaryJsonService;
import service.DictionaryService;

public class Languages {

  public static void main(String[] args) throws IOException {
    final Logger log = LoggerFactory.getLogger(Languages.class);
    final DictionaryJsonService dictionaryJsonService = new DictionaryJsonService();
    final DictionaryService dictionaryService = new DictionaryService(
        new ArrayList<>(dictionaryJsonService.getAllDictionaries().values()));
    final Map<String, List<String>> occurrences = new HashMap<>();

    System.out.println("Options");
    System.out.println("1. Find all occurrences.");
    System.out.println("2. Add a new language.");

    Scanner scanner = new Scanner(System.in);
    System.out.print("Your choice: ");

    try {
      int option = scanner.nextInt();
      scanner.nextLine();

      if (option == 1) {
        System.out.println(1);
      } else if (option == 2) {
        System.out.println(2);
      } else {
        throw new RuntimeException();
      }
    } catch (Exception exception) {
      log.error("Invalid option.");
    }

  }
}
