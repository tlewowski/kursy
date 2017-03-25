Kontynuujemy implementowanie namiastki systemu zarz¹dzania biblioteczk¹. 

W ramach zadania domowego zajmiemy siê tworzeniem dodatkowych funkcjonalnoœci zwi¹zanych ze wspó³dzieleniem danych.

Nale¿y zaimplementowaæ: 

**Client**, uruchamiany na maszynie u¿ytkownika. W tej klasie bêdziemy dodawaæ ksi¹¿ki do biblioteczki. W czasie testów bêdziemy uruchamiaæ dwie instancje klienta.

**BookshelfServer**, reprezentuj¹cy dzia³anie biblioteczki.

Typ **Book** reprezentuj¹cy ksi¹¿kê. Klasa **Book** powinna udostêpniaæ pola: `String author` i `String title`.

**BookshelfServer** powinien oferowaæ 4 operacje:

 - `int putPrivate(Book book, String ownerName)` - umieszcza na w prywatnej bibliotecze u¿ytkownika `ownerName` ksi¹¿kê `book` i zwraca nadany jej identyfikator.
 Identyfikator powinien byæ unikalny dla ka¿dej ksi¹¿ki. Ksi¹¿ka powinna byæ zawsze dostêpna pod danym identyfikatorem (a¿ do jej usuniêcia z biblioteczki), 
 niezale¿nie od innych zmian na pó³ce. Dostêp do ksi¹¿ki powinien mieæ tylko u¿ytkownik `ownerName`.
 - `int putPublic(Book book)` - umieszcza w publicznie dostêpnej biblioteczce ksi¹¿kê `book` i zwraca nadany jej identyfikator. Ka¿dy u¿ytkownik powinien mieæ dostêp
 do ksi¹¿ki publicznej pod otrzymanym identyfikatorem.
 - `Book get(String user, int id)` - zwraca ksi¹¿kê znajduj¹c¹ siê pod podanym id, o ile jest dostêpna dla u¿ytkownika `user`. 
 Jeœli ksi¹¿ka o zadanym id nie istnieje, nale¿y zwróciæ `null`
 - `bool delete(String user, int id)` - usuniêcie danej ksi¹¿ki z biblioteczki, o ile jest dostêpna dla u¿ytkownika `user`.
 Jeœli ksi¹¿ka zosta³a usuniêta nale¿y zwróciæ `true`, w przeciwnym razie - `false`.
 
to oznacza, ¿e w stanie serwera **BookshelfServer** nale¿y przechowaæ w jakiœ sposób (proponujê mapê) identyfikatory ksi¹¿ek, w³aœcicieli i same ksi¹¿ki.

Ka¿da wywo³ana funkcja na serwerze powinna drukowaæ swoje wykonanie i argumenty.

W testowaniu serwera wykorzystamy dwie instancje klienta. Klienty powinny byæ uruchomione **w osobnych procesach**. Przekazywanie id pomiêdzy klientami
mo¿na wykonywaæ rêcznie, tzn. wczytywaæ identyfikator ksi¹¿ki do pobrania z poziomu wiersza poleceñ (nie ma potrzeby implementowania komunikacji miêdzy klientami)

W pliku .puml w tym folderze znajduje siê diagram UML obrazuj¹cy sekwencjê komunikacji pomiêdzy klientami a serwerem. 
Diagram jest w formacie PlantUML, ale dla wygody wrzucam tak¿e png.
