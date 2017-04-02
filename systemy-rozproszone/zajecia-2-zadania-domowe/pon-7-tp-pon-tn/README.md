Kontynuujemy implementowanie namiastki systemu zarz�dzania zadaniami. 

W ramach zadania domowego zajmiemy si� tworzeniem dodatkowych funkcjonalno�ci zwi�zanych ze wsp�dzieleniem danych.

Nale�y zaimplementowa�: 

**Client**, uruchamiany na maszynie u�ytkownika. W tej klasie b�dziemy dodawa� zadania do �ledzenia. W czasie test�w b�dziemy uruchamia� dwie instancje klienta.

**IssueTrackerServer**, reprezentuj�cy dzia�anie serwera �ledzenia zada�.

Typ **Issue** reprezentuj�cy zadanie. Klasa **Issue** powinna udost�pnia� pola: `String title` i `int priority`.

**IssueTrackerServer** powinien oferowa� 4 operacje:

 - `int putIssue(Issue issue, String projectName)` - umieszcza zadanie `issue` w prywatnym projekcie `projectName` i zwraca nadany mu identyfikator.
 Identyfikator powinien by� unikalny dla ka�dego zadania. Zadanie powinno by� zawsze dost�pne pod danym identyfikatorem (a� do usuni�cia), 
 niezale�nie od innych zmian w systemie �ledzenia.
 - `Issue get(String project, int id)` - zwraca zadanie znajduj�ce si� pod podanym id w zadanym projekcie
 Je�li zadanie o podanym id nie istnieje, nale�y zwr�ci� `null`
 - `bool delete(String project, int id)` - usuni�cie danego zadania z systemu �ledzenia
 Je�li zadanie zosta�o usuni�te nale�y zwr�ci� `true`, w przeciwnym razie - `false`.
 
to oznacza, �e w stanie serwera **IssueTrackerServer** nale�y przechowa� w jaki� spos�b (proponuj� map�) identyfikatory zada�, nazwy projekt�w i zadania.

identyfikatory musz� by� unikalne w ramach projektu, ale nie musz� by� unikalne w ramach ca�ego serwera (tzn. ten sam liczbowy identyfikator mo�e odpowiada�
r�nym zadaniom w ramach r�nych projekt�w)

Ka�da wywo�ana funkcja na serwerze powinna drukowa� swoje wykonanie i argumenty.

W testowaniu serwera wykorzystamy dwie instancje klienta. Klienty powinny by� uruchomione **w osobnych procesach**. Przekazywanie id pomi�dzy klientami
mo�na wykonywa� r�cznie, tzn. wczytywa� identyfikator zadania do pobrania z poziomu wiersza polece� (nie ma potrzeby implementowania komunikacji mi�dzy klientami)

W pliku .puml w tym folderze znajduje si� diagram UML obrazuj�cy sekwencj� komunikacji pomi�dzy klientami a serwerem. 
Diagram jest w formacie PlantUML, ale dla wygody wrzucam tak�e png.
