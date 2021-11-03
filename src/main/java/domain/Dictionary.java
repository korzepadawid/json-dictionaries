package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Dictionary {

  private String language;
  private List<String> words;

  public Dictionary() {}

  public Dictionary(String language) {
    this(language, new ArrayList<>());
  }

  public Dictionary(String language, List<String> words) {
    this.language = language;
    this.words = words;
  }

  public void addWord(String word) {
    words.add(word);
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public List<String> getWords() {
    return words;
  }

  public void setWords(List<String> words) {
    this.words = words;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Dictionary that = (Dictionary) o;
    return language.equals(that.language);
  }

  @Override
  public int hashCode() {
    return Objects.hash(language);
  }

  @Override
  public String toString() {
    return "Dictionary{" + "language='" + language + '\'' + '}';
  }
}
