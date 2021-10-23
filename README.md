# Projekt nr. I

## Uruchomienie

```shell
$ git clone https://github.com/korzepadawid/json-dictionaries.git 
```

```shell
$ cd json-dictionaries 
```

```shell
$ mvn clean package
```

```shell
$ java -jar target/*.jar
```

## Polecenie

Napisz program, który wczyta z plików json (lub xml) listę słów danego języka. Następnie dla każdego
wpisanego przez użytkownika słowa wypisze z jakiego języka ono pochodzi (uwaga! to samo słowo może
występować w wielu językach), lub że nie ma takiego słowa w bazie.

Program powinien też umożliwiać dodanie własnego języka i listy słów i zapisanie go do pliku json (
lub xml). Przy kolejnym uruchomieniu plik taki powinien być brany pod uwagę przez program.

Na wyjściu programu powinniśmy zachować do pliku PDF ile podano słów i z jakich języków pochodziły
podane słowa. Wykorzystaj dowolną bibliotekę Javy do tworzenia plików jako pdf, by zapisać w nim
statystyki. Format pliku jest dowolny, ma zawierać podane wyżej informacje, może zawierać też
dodatkowe elementy.

Napisz testy dla metod wyszukujących słowa (najlepiej żeby klasa przyjmowała na wejściu kolekcję
słowników, a metoda słowo do wyszukania). Wykorzystaj strumienie oraz co najmniej dwie struktury
danych (set, map, list, stack, etc.).

Przykład struktury pliku JSON:

```json
{
  "words": [
    "a",
    "abandon",
    "ability",
    "able",
    "abortion",
    "about",
    "above",
    "abroad",
    "absence",
    "absolute",
    "absolutely",
    "absorb",
    "abuse",
    "academic",
    "accept",
    "access",
    "accident",
    "accompany",
    "accomplish",
    "according",
    "account",
    "accurate",
    "accuse",
    "achieve",
    "achievement",
    "acid",
    "acknowledge",
    "acquire",
    "across",
    "act",
    "action",
    "active",
    "activity",
    "actor",
    "actress",
    "actual",
    "actually",
    "ad",
    "adapt",
    "add",
    "addition",
    "additional"
  ],
  "language": "English"
}
```