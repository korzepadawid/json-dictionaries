package service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import domain.Dictionary;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class DictionaryServiceTest {

  private DictionaryService dictionaryService;

  @Test
  void shouldReturnEmptyListWhenNoDictionaries() {
    dictionaryService = new DictionaryService(Collections.emptyList());

    var occurrences = dictionaryService.findAllOccurrences("kw");

    assertThat(occurrences).isNotNull();
    assertThat(occurrences.size()).isEqualTo(0);
  }

  @Test
  void shouldReturnEmptyListWhenNoMatches() {
    dictionaryService = new DictionaryService(getListOfDictionaries());

    var occurrences = dictionaryService.findAllOccurrences("kw");

    assertThat(occurrences).isNotNull();
    assertThat(occurrences.size()).isEqualTo(0);
  }

  @Test
  void shouldReturnValidOccurrencesWhenMoreThanOneLanguage() {
    dictionaryService = new DictionaryService(getListOfDictionaries());

    var occurrences = dictionaryService.findAllOccurrences("ok");

    assertThat(occurrences.contains("English")).isTrue();
    assertThat(occurrences.contains("Polish")).isTrue();
    assertThat(occurrences.size()).isEqualTo(2);
  }

  @Test
  void shouldReturnValidOccurrenceWhenOnlyOneLanguage() {
    dictionaryService = new DictionaryService(getListOfDictionaries());

    var occurrences = dictionaryService.findAllOccurrences("no");

    assertThat(occurrences.contains("English")).isTrue();
    assertThat(occurrences.size()).isEqualTo(1);
  }

  @Test
  void shouldReturnValidOccurrenceWhenIgnoringCase() {
    List<Dictionary> dictionaries =
        List.of(
            new Dictionary("English", Arrays.asList("test", "ok", "no")),
            new Dictionary("Polish", Arrays.asList("test", "Ok", "oK")));
    dictionaryService = new DictionaryService(dictionaries);

    var occurrences = dictionaryService.findAllOccurrences("oK");

    assertThat(occurrences.contains("English")).isTrue();
    assertThat(occurrences.contains("Polish")).isTrue();
  }

  List<Dictionary> getListOfDictionaries() {
    return List.of(
        new Dictionary("English", Arrays.asList("test", "ok", "no")),
        new Dictionary("Polish", Arrays.asList("test", "ok", "ok")));
  }
}
