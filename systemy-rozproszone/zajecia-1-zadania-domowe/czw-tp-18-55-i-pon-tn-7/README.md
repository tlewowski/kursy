Kontynuujemy implementowanie namiastki systemu �ledzenia zada�. 

W ramach zadania domowego zajmiemy si� tworzeniem osobnego serwera zajmuj�cego si� pomiarem czasu komunikacji.

Nale�y zaimplementowa� trzy klasy: 

**Client**, uruchamiany na maszynie u�ytkownika (np. administratora systemu). W tej klasie b�dziemy wy�wietla� u�ytkownikowi wszystkie pomiary.

**MeasurementServer**, reprezentuj�cy dzia�aj�cy w ustalonym miejscu punkt docelowy. W tej klasie b�dziemy wykonywa� pomiary.

**BugtrackerServer**, reprezentuj�cy dzia�aj�cy w sieci serwer aplikacji. W tej klasie b�dziemy wykonywa� operacje.

**BugtrackerServer** powinien oferowa� 3 operacje:

 - `List<String> getTasks(int priority)` - zwracaj�cy list� identyfikator�w zada� o zadanym priorytecie
 - `int setPriority(String taskId, int priority)` - zmiana priorytetu dla zadania. 
 Je�li zadanie o podanym ID nie istnieje, nale�y zwr�ci� 1, je�li operacja si� uda nale�y zwr�ci� 0.
 - `int addTask(String taskId, int priority)` - dodanie nowego zadania. 
 Je�li zadanie o podanym ID ju� istnieje nale�y zwr�ci� 1, w przeciwnym razie doda� nowe zadanie i zwr�ci� 0
 
w stanie serwera **BugtrackerServer** nale�y przechowa� *zahardkodowane* dane (nie pobiera� ich z dysku ani znik�d indziej) - lista zada� (np. w formie
`Map<String, Integer>`). Mo�na rozpoczyna� od dowolnego stanu pocz�tkowego, np. 0 zada�

priorytet mo�e by� dowoln� liczb� naturaln�

Ka�da wywo�ana funkcja z interfejsu powinna drukowa� swoje wykonanie i argumenty.

**MeasurementServer** powinien oferowa� dwie operacje:

 - `double measureAddSync(List<String> ids, List<Integer> priorities)`, kt�ra synchronicznie wywo�uje `addTask` dla ka�dej pary (id, priority), a na koniec zwraca czas wykonania wszystkich operacji.
 Je�li rozmiar froms i tos jest r�ny, wynikiem powinno by� `NaN` (Not a Number), tak samo je�li dla kt�rej� z operacji serwer zwr�ci b��d (brak pliku o zadanej nazwie)
 - `double measureAddAsync(List<String> ids, List<Integer> priorities)` - to samo tylko �e `addTask` ma by� wywo�ywany asynchronicznie. Pomiar mo�e by� trudny w przypadku Apache XMLRPC 1.2b, gdzie nie 
 jest dost�pny TimingOutCallback (oczekiwanie na odpowied�) - w przypadku tej wersji biblioteki mo�na ewentualnie przy oczekiwaniu na wynik u�y� funkcji `sleep` w p�tli z kr�tkim interwa�em czasowym (~10ms)

Ka�da wywo�ana funkcja z interfejsu powinna drukowa� swoje wykonanie i argumenty. 
 
**Client** powinien wykona� nast�puj�ce operacje: 
 
 - wywo�a� `getTasks(0)` z `BugtrackerServer` i wydrukowa� uzyskan� list�
 - wywo�a� `setPriority(id, 123)` z nieistniej�cym ID zadania - nale�y oczekiwa� odpowiedzi 1
 - wywo�a� `measureAddSync` z co najmniej 150 zadaniami - jednym z dodanych id powinno by� id u�yte w poprzednim punkcie, wydrukowa� wynik
 - wywo�a� `setPriority(id, 123)` z t� sam� �cie�k� co poprzednio - zadanie ju� istnieje, wi�c nale�y oczekiwa� odpowiedzi 0
 - wywo�a� `measureAddAsync` z co najmniej 150 zadaniami (tyle co poprzednio), wydrukowa� wynik
 - wywo�a� `getTasks(123)`, sprawdzi� �e `id` znajduje si� na li�cie
 
Komunikacja mi�dzy klientem a ka�dym z serwer�w powinna by� synchroniczna. 

Ka�da wywo�ana funkcja z interfejsu powinna drukowa� swoje wykonanie i argumenty.

W pliku .puml w tym folderze znajduje si� pomocniczy diagram UML obrazuj�cy sekwencj� komunikacji pomi�dzy obiektami. Diagram jest w formacie PlantUML, ale dla wygody wrzucam tak�e png.
