Kontynuujemy implementowanie namiastki systemu zarz¹dzania serwerami. 

W ramach zadania domowego zajmiemy siê tworzeniem osobnego serwera zajmuj¹cego siê pomiarem czasu komunikacji.

Nale¿y zaimplementowaæ trzy klasy: 

**Client**, uruchamiany na maszynie u¿ytkownika (np. administratora systemu). W tej klasie bêdziemy wyœwietlaæ u¿ytkownikowi wszystkie pomiary.

**MeasurementServer**, reprezentuj¹cy dzia³aj¹cy w ustalonym miejscu punkt docelowy. W tej klasie bêdziemy wykonywaæ pomiary.

**SupervisedServer**, reprezentuj¹cy dzia³aj¹cy w sieci serwer aplikacji. W tej klasie bêdziemy wykonywaæ operacje.

**SupervisedServer** powinien oferowaæ 3 operacje:

 - `int getLoad()` - zwracaj¹cy dowoln¹ sta³¹ liczbê z zakresu 0-100
 - `int moveFile(String fromLocation, String toLocation)` - przesuniêcie pliku w obrêbie serwera (tzn. usun¹æ plik `fromLocation` ze stanu i dodaæ plik `toLocation` o takich samych uprawnieniach do stanu). 
 Jeœli plik o zadanej nazwie nie istnieje, nale¿y zwróciæ 1, jeœli plik docelowy ju¿ istnieje i zosta³by nadpisany nale¿y zwróciæ 2, jeœli operacja siê uda nale¿y zwróciæ 0.
 - `int setPermissions(String fileName, int permissions)` - zmiana uprawnieñ dla zadanego pliku. 
 Jeœli plik o zadanej nazwie nie istnieje nale¿y zwróciæ 1, w przeciwnym razie wykonaæ operacjê i zwróciæ 0
 
w stanie serwera **SupervisedServer** nale¿y przechowaæ *zahardkodowane* dane (nie pobieraæ ich z dysku ani znik¹d indziej) - obci¹¿enie serwera (int load) 
i obs³ugiwane pliki (pary nazwa-uprawnienia, np. w postaci obiektów albo mapy nazwa -> uprawnienia). W stanie powinno byæ przynajmniej 15 plików.

uprawnienia mog¹ byæ podane w dowolnej formie, tzn. nie musi byæ to format u¿ywany przez systemy POSIXowe, mo¿na u¿yæ po prostu liczb 1, 2, 3 itd.

Ka¿da wywo³ana funkcja z interfejsu powinna drukowaæ swoje wykonanie i argumenty.

**MeasurementServer** powinien oferowaæ dwie operacje:

 - `double measureSyncMove(List<String> froms, List<String> tos)`, która synchronicznie wywo³uje `moveFile` dla ka¿dej pary (from, to), a na koniec zwraca czas wykonania wszystkich operacji.
 Jeœli rozmiar froms i tos jest ró¿ny, wynikiem powinno byæ `NaN` (Not a Number), tak samo jeœli dla którejœ z operacji serwer zwróci b³¹d (brak pliku o zadanej nazwie)
 - `double measureAsyncMove(List<String> from, List<String> to,)` - to samo tylko ¿e `moveFile` ma byæ wywo³ywany asynchronicznie. Pomiar mo¿e byæ trudny w przypadku Apache XMLRPC 1.2b, gdzie nie 
 jest dostêpny TimingOutCallback (oczekiwanie na odpowiedŸ) - w przypadku tej wersji biblioteki mo¿na ewentualnie przy oczekiwaniu na wynik u¿yæ funkcji `sleep` w pêtli z krótkim interwa³em czasowym (~10ms)

Ka¿da wywo³ana funkcja z interfejsu powinna drukowaæ swoje wykonanie i argumenty. 
 
**Client** powinien wykonaæ nastêpuj¹ce operacje: 
 
 - wywo³aæ `getLoad()` z `SupervisedServer` i wydrukowaæ uzyskan¹ wartoœæ
 - wywo³aæ `setPermissions(...)` ze œcie¿k¹ do nieistniej¹cego pliku - nale¿y oczekiwaæ odpowiedzi 1
 - wywo³aæ `measureSyncMove` z co najmniej 15 plikami - jedn¹ ze œcie¿ek docelowych powinna byæ œcie¿ka u¿yta w poprzednim punkcie, wydrukowaæ wynik
 - wywo³aæ `setPermissions(...)` z t¹ sam¹ œcie¿k¹ co poprzednio - plik ju¿ istnieje, wiêc nale¿y oczekiwaæ odpowiedzi 0
 - wywo³aæ `measureAsyncMove` z tymi samymi plikami co poprzednio, tylko zamieniæ `froms` z `tos` (tzn. przesun¹æ pliki z powrotem na miejsca pocz¹tkowe), wydrukowaæ wynik 

Komunikacja miêdzy klientem a ka¿dym z serwerów powinna byæ synchroniczna. 

Ka¿da wywo³ana funkcja z interfejsu powinna drukowaæ swoje wykonanie i argumenty.

W pliku .puml w tym folderze znajduje siê pomocniczy diagram UML obrazuj¹cy sekwencjê komunikacji pomiêdzy obiektami. Diagram jest w formacie PlantUML, ale dla wygody wrzucam tak¿e png.
