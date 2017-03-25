Kontynuujemy implementowanie namiastki systemu zarz¹dzania zadaniami. 

W ramach zadania domowego zajmiemy siê tworzeniem dodatkowych funkcjonalnoœci zwi¹zanych ze wspó³dzieleniem danych.

Nale¿y zaimplementowaæ: 

**Client**, uruchamiany na maszynie u¿ytkownika. W tej klasie bêdziemy dodawaæ zadania do œledzenia. W czasie testów bêdziemy uruchamiaæ dwie instancje klienta.

**IssueTrackerServer**, reprezentuj¹cy dzia³anie serwera œledzenia zadañ.

Typ **Issue** reprezentuj¹cy zadanie. Klasa **Issue** powinna udostêpniaæ pola: `String title` i `int priority`.

**IssueTrackerServer** powinien oferowaæ 4 operacje:

 - `int putIssue(Issue issue, String projectName)` - umieszcza zadanie `issue` w prywatnym projekcie `projectName` i zwraca nadany mu identyfikator.
 Identyfikator powinien byæ unikalny dla ka¿dego zadania. Zadanie powinno byæ zawsze dostêpne pod danym identyfikatorem (a¿ do usuniêcia), 
 niezale¿nie od innych zmian w systemie œledzenia.
 - `Issue get(String project, int id)` - zwraca zadanie znajduj¹ce siê pod podanym id w zadanym projekcie
 Jeœli zadanie o podanym id nie istnieje, nale¿y zwróciæ `null`
 - `bool delete(String project, int id)` - usuniêcie danego zadania z systemu œledzenia
 Jeœli zadanie zosta³o usuniête nale¿y zwróciæ `true`, w przeciwnym razie - `false`.
 
to oznacza, ¿e w stanie serwera **IssueTrackerServer** nale¿y przechowaæ w jakiœ sposób (proponujê mapê) identyfikatory zadañ, nazwy projektów i zadania.

identyfikatory musz¹ byæ unikalne w ramach projektu, ale nie musz¹ byæ unikalne w ramach ca³ego serwera (tzn. ten sam liczbowy identyfikator mo¿e odpowiadaæ
ró¿nym zadaniom w ramach ró¿nych projektów)

Ka¿da wywo³ana funkcja na serwerze powinna drukowaæ swoje wykonanie i argumenty.

W testowaniu serwera wykorzystamy dwie instancje klienta. Klienty powinny byæ uruchomione **w osobnych procesach**. Przekazywanie id pomiêdzy klientami
mo¿na wykonywaæ rêcznie, tzn. wczytywaæ identyfikator zadania do pobrania z poziomu wiersza poleceñ (nie ma potrzeby implementowania komunikacji miêdzy klientami)

W pliku .puml w tym folderze znajduje siê diagram UML obrazuj¹cy sekwencjê komunikacji pomiêdzy klientami a serwerem. 
Diagram jest w formacie PlantUML, ale dla wygody wrzucam tak¿e png.
