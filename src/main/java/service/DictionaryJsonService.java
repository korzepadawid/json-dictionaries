package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Dictionary;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DictionaryJsonService {

  public static final String DIR_PATH = "src/main/resources/dictionaries";

  private final ObjectMapper objectMapper = new ObjectMapper();
  private final Logger log = LoggerFactory.getLogger(DictionaryJsonService.class);

  public Map<String, Dictionary> getAllDictionaries() throws IOException {
    return Files.find(Paths.get(DIR_PATH), 1,
            (path, basicFileAttributes) -> path.getFileName().toString().endsWith(".json"))
        .map(this::read)
        .filter(Objects::nonNull)
        .collect(Collectors.toMap(Dictionary::getLanguage, dictionary -> dictionary));
  }

  public void saveDictionary(Dictionary dictionary) {
    File file = new File(
        DIR_PATH + "/" + dictionary.getLanguage().toLowerCase(Locale.ROOT) + ".json");
    try {
      objectMapper.writeValue(file, dictionary);
    } catch (IOException e) {
      log.error("Can't save to json file.");
    }
  }

  public Dictionary read(Path path) {
    try {
      return objectMapper.readValue(new File(path.toString()), Dictionary.class);
    } catch (IOException e) {
      log.error("Can't read json file.");
    }
    return null;
  }
}
