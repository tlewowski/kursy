Kontynuujemy implementowanie namiastki systemu œledzenia zadañ. 

W ramach zadania domowego zajmiemy siê tworzeniem osobnego serwera zajmuj¹cego siê pomiarem czasu komunikacji.

Nale¿y zaimplementowaæ trzy klasy: 

**Client**, uruchamiany na maszynie u¿ytkownika (np. administratora systemu). W tej klasie bêdziemy wyœwietlaæ u¿ytkownikowi wszystkie pomiary.

**MeasurementServer**, reprezentuj¹cy dzia³aj¹cy w ustalonym miejscu punkt docelowy. W tej klasie bêdziemy wykonywaæ pomiary.

**BugtrackerServer**, reprezentuj¹cy dzia³aj¹cy w sieci serwer aplikacji. W tej klasie bêdziemy wykonywaæ operacje.

**BugtrackerServer** powinien oferowaæ 3 operacje:

 - `List<String> getTasks(int priority)` - zwracaj¹cy listê identyfikatorów zadañ o zadanym priorytecie
 - `int setPriority(String taskId, int priority)` - zmiana priorytetu dla zadania. 
 Jeœli zadanie o podanym ID nie istnieje, nale¿y zwróciæ 1, jeœli operacja siê uda nale¿y zwróciæ 0.
 - `int addTask(String taskId, int priority)` - dodanie nowego zadania. 
 Jeœli zadanie o podanym ID ju¿ istnieje nale¿y zwróciæ 1, w przeciwnym razie dodaæ nowe zadanie i zwróciæ 0
 
w stanie serwera **BugtrackerServer** nale¿y przechowaæ *zahardkodowane* dane (nie pobieraæ ich z dysku ani znik¹d indziej) - lista zadañ (np. w formie
`Map<String, Integer>`). Mo¿na rozpoczynaæ od dowolnego stanu pocz¹tkowego, np. 0 zadañ

priorytet mo¿e byæ dowoln¹ liczb¹ naturaln¹

Ka¿da wywo³ana funkcja z interfejsu powinna drukowaæ swoje wykonanie i argumenty.

**MeasurementServer** powinien oferowaæ dwie operacje:

 - `double measureAddSync(List<String> ids, List<Integer> priorities)`, która synchronicznie wywo³uje `addTask` dla ka¿dej pary (id, priority), a na koniec zwraca czas wykonania wszystkich operacji.
 Jeœli rozmiar froms i tos jest ró¿ny, wynikiem powinno byæ `NaN` (Not a Number), tak samo jeœli dla którejœ z operacji serwer zwróci b³¹d (brak pliku o zadanej nazwie)
 - `double measureAddAsync(List<String> ids, List<Integer> priorities)` - to samo tylko ¿e `addTask` ma byæ wywo³ywany asynchronicznie. Pomiar mo¿e byæ trudny w przypadku Apache XMLRPC 1.2b, gdzie nie 
 jest dostêpny TimingOutCallback (oczekiwanie na odpowiedŸ) - w przypadku tej wersji biblioteki mo¿na ewentualnie przy oczekiwaniu na wynik u¿yæ funkcji `sleep` w pêtli z krótkim interwa³em czasowym (~10ms)

Ka¿da wywo³ana funkcja z interfejsu powinna drukowaæ swoje wykonanie i argumenty. 
 
**Client** powinien wykonaæ nastêpuj¹ce operacje: 
 
 - wywo³aæ `getTasks(0)` z `SupervisedServer` i wydrukowaæ uzyskan¹ listê
 - wywo³aæ `setPriority(id, 123)` z nieistniej¹cym ID zadania - nale¿y oczekiwaæ odpowiedzi 1
 - wywo³aæ `measureAddSync` z co najmniej 150 zadaniami - jednym z dodanych id powinno byæ id u¿yte w poprzednim punkcie, wydrukowaæ wynik
 - wywo³aæ `setPriority(id, 123)` z t¹ sam¹ œcie¿k¹ co poprzednio - zadanie ju¿ istnieje, wiêc nale¿y oczekiwaæ odpowiedzi 0
 - wywo³aæ `measureAddAsync` z co najmniej 150 zadaniami (tyle co poprzednio), wydrukowaæ wynik
 - wywo³aæ `getTasks(123)`, sprawdziæ ¿e `id` znajduje siê na liœcie
 
Komunikacja miêdzy klientem a ka¿dym z serwerów powinna byæ synchroniczna. 

Ka¿da wywo³ana funkcja z interfejsu powinna drukowaæ swoje wykonanie i argumenty.

W pliku .puml w tym folderze znajduje siê pomocniczy diagram UML obrazuj¹cy sekwencjê komunikacji pomiêdzy obiektami. Diagram jest w formacie PlantUML, ale dla wygody wrzucam tak¿e png.
