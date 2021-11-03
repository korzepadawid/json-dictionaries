import com.lowagie.text.DocumentException;
import domain.Dictionary;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.DictionaryService;
import service.JsonService;
import service.PdfService;

public class Languages {

  public static void main(String[] args) throws IOException, DocumentException {
    final Logger log = LoggerFactory.getLogger(Languages.class);
    final JsonService jsonService = new JsonService();
    final DictionaryService dictionaryService =
        new DictionaryService(new ArrayList<>(jsonService.getAllDictionaries().values()));
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
        System.out.print("How many words?: ");
        int wordsCount = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < wordsCount; i++) {
          System.out.printf("Word nr %d. : ", i + 1);
          String word = scanner.next();
          scanner.nextLine();

          List<String> result = dictionaryService.findAllOccurrences(word);
          occurrences.put(word, result);

          if (result.size() != 0) {
            System.out.println(result);
          } else {
            System.out.println("Word doesn't exist.");
          }
        }

      } else if (option == 2) {
        System.out.print("Language name: ");
        String languageName = scanner.next();
        scanner.nextLine();

        System.out.print("How many words?: ");
        int wordsCount = scanner.nextInt();
        scanner.nextLine();

        final Dictionary dictionary = new Dictionary(languageName);

        for (int i = 0; i < wordsCount; i++) {
          System.out.printf("Word nr %d. : ", i + 1);
          String word = scanner.next();
          scanner.nextLine();

          dictionary.addWord(word);
        }

        jsonService.saveDictionary(dictionary);

      } else {
        throw new RuntimeException();
      }
    } catch (Exception exception) {
      log.error("Invalid input.");
    }

    if (occurrences.size() != 0) {
      PdfService pdfService = new PdfService();
      String template = pdfService.parseThymeleafTemplate(occurrences);
      pdfService.generatePdfFromHtml(template);
      log.info("Saving stats to summary.pdf");
    }
  }
}
