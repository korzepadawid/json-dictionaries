package service;

import domain.Dictionary;
import java.util.List;
import java.util.stream.Collectors;

public class DictionaryService {

  private final List<Dictionary> dictionaries;

  public DictionaryService(List<Dictionary> dictionaries) {
    this.dictionaries = dictionaries;
  }

  public List<String> findAllOccurrences(final String keyword) {
    return dictionaries.stream()
        .filter(
            dictionary ->
                dictionary.getWords().stream()
                    .map(String::toLowerCase)
                    .collect(Collectors.toList())
                    .contains(keyword.toLowerCase()))
        .map(Dictionary::getLanguage)
        .collect(Collectors.toList());
  }
}
