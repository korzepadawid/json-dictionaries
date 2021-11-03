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

public class JsonService {

  private static final String DIR_PATH = "./dictionaries";

  private final ObjectMapper objectMapper = new ObjectMapper();
  private final Logger log = LoggerFactory.getLogger(JsonService.class);

  public Map<String, Dictionary> getAllDictionaries() throws IOException {
    return Files.find(
            Paths.get(DIR_PATH),
            1,
            (path, basicFileAttributes) -> path.getFileName().toString().endsWith(".json"))
        .map(this::read)
        .filter(Objects::nonNull)
        .collect(Collectors.toMap(Dictionary::getLanguage, dictionary -> dictionary));
  }

  public void saveDictionary(final Dictionary dictionary) {
    String filename = getKebabCase(dictionary.getLanguage());
    File file = new File(DIR_PATH + "/" + filename + ".json");
    try {
      objectMapper.writeValue(file, dictionary);
      log.info("New dictionary in {}.json", filename);
    } catch (IOException e) {
      log.error("Can't save to json file.");
    }
  }

  public Dictionary read(final Path path) {
    try {
      return objectMapper.readValue(new File(path.toString()), Dictionary.class);
    } catch (IOException e) {
      log.error("Can't read json file.");
    }
    return null;
  }

  private String getKebabCase(final String text) {
    return text.toLowerCase(Locale.ROOT).replaceAll(" ", "-");
  }
}
