Kontynuujemy implementowanie namiastki systemu zarz¹dzania zasobami. 

W ramach zadania domowego zajmiemy siê tworzeniem osobnego serwera zajmuj¹cego siê pomiarem czasu komunikacji.

Nale¿y zaimplementowaæ trzy klasy: 

**Client**, uruchamiany na maszynie u¿ytkownika. W tej klasie bêdziemy wyœwietlaæ u¿ytkownikowi wszystkie pomiary.

**MeasurementServer**, reprezentuj¹cy dzia³aj¹cy w ustalonym miejscu punkt docelowy. W tej klasie bêdziemy wykonywaæ pomiary.

**ResourceServer**, reprezentuj¹cy dzia³aj¹cy w sieci serwer aplikacji. W tej klasie bêdziemy wykonywaæ operacje.

**ResourceServer** powinien oferowaæ 3 operacje:

 - `String put(String uri, double value)` - umieszcza na serwerze wartoœæ `value` pod identyfikatorem `uri`. Jeœli
 jakiœ zasób jest ju¿ na serwerze pod tym identyfikatorem nale¿y zwróciæ "403 Forbidden", jeœli operacja siê powiedzie - "200 OK"
 - `Object get(String uri)` - zwraca zasób znajduj¹cy siê pod podanym uri. 
 Jeœli zasób o zadanej nazwie nie istnieje, nale¿y zwróciæ String "404 Not Found"
 - `String delete(String uri)` - usuniêcie danego zasobu. 
 Jeœli plik o zadanej nazwie nie istnieje nale¿y zwróciæ "404 Not Found", w przeciwnym razie "200 OK"
 
w stanie serwera **ResourceServer** nale¿y przechowaæ mapê zasób -> wartoœæ

Ka¿da wywo³ana funkcja z interfejsu powinna drukowaæ swoje wykonanie i argumenty.

**MeasurementServer** powinien oferowaæ dwie operacje:

 - `double measureSyncManagement(List<String> uris, List<Double> values)`, która dla ka¿dej pary (uri, value) synchronicznie wywo³uje sekwencjê `get(uri)` -> `put(uri, value)` -> `get(uri)` -> `delete(uri)` -> `get(uri)` `moveFile` dla ka¿dej pary (from, to), a na koniec zwraca czas wykonania wszystkich operacji.
  Nale¿y sprawdziæ wynik - pierwszy i ostatni get powinny zwróciæ b³¹d 404, natomiast trzy œrodkowe operacje (put - get - delete) powinny siê powieœæ dla ka¿dej danej wejœciowej.
 Jeœli rozmiar uris i values jest ró¿ny, wynikiem powinno byæ `NaN` (Not a Number), tak samo jeœli dla którejœ z operacji serwer zwróci b³¹d (brak pliku o zadanej nazwie)
 - `double measureAsyncManagement(List<String> uris, List<Double> values,)` - scenariusz jest ten sam co w `measureSyncManagement`, tyle ¿e sekwencje musz¹ byæ synchronizowane, tzn. nie mo¿na za¿¹daæ pobrania zasobu przed jego wstawieniem.
 Mo¿na to zrobiæ na dwa sposoby:
 
  1. Zsynchronizowaæ wykonanie wszystkich getów, wszystkiech putów itd. (tak robiliœmy to na zajêciach)
  2. Synchronizowaæ w ramach jednego uri (tzn. triggerem dla zawo³ania put jest wykonanie poprzedniego zapytania get itd.)
  
  Drugie rozwi¹zanie jest trudniejsze z u¿yciem Apache XMLRPC (znacznie ³atwiej by³oby je wykonaæ z u¿yciem takich mechanizów jak CompletableFuture) - prawdopodobnie
  bêdzie te¿ bardziej wydajne. W przypadku jego poprawnej implementacji mo¿na liczyæ na dodatkowe punkty, ale zaznaczam, ¿e napisanie tego mo¿e zaj¹æ doœæ du¿o czasu i nie bêdzie wymagane (10 pkt jest do zdobycia za wersjê 1, 13 za wersjê 2)
  
 Pomiar mo¿e byæ trudny w przypadku Apache XMLRPC 1.2b, gdzie nie jest dostêpny TimingOutCallback (oczekiwanie na odpowiedŸ) - w przypadku tej wersji biblioteki mo¿na ewentualnie przy oczekiwaniu na wynik u¿yæ funkcji `sleep` w pêtli z krótkim interwa³em czasowym (~10ms)

Ka¿da wywo³ana funkcja z interfejsu powinna drukowaæ swoje wykonanie i argumenty. 
 
**Client** powinien wykonaæ nastêpuj¹ce operacje: 
 
 - wywo³aæ `delete()` z `ResourceServer` i wydrukowaæ uzyskan¹ wartoœæ
 - wywo³aæ `put(...)` ze œcie¿k¹ do nieistniej¹cego pliku - nale¿y oczekiwaæ odpowiedzi 1
 - wywo³aæ `put` z co najmniej 15 plikami - jedn¹ ze œcie¿ek docelowych powinna byæ œcie¿ka u¿yta w poprzednim punkcie, wydrukowaæ wynik
 - wywo³aæ `measureSyncManagement` z co najmniej 150 parami uri/wartoœæ - wydrukowaæ wynik
 - wywo³aæ `measureAsyncManagement` z co najmniej 150 parami uri/wartosæ - wydrukowaæ wynik

Komunikacja miêdzy klientem a ka¿dym z serwerów powinna byæ synchroniczna. 

Ka¿da wywo³ana funkcja z interfejsu powinna drukowaæ swoje argumenty.

W pliku .puml w tym folderze znajduje siê pomocniczy diagram UML obrazuj¹cy sekwencjê komunikacji pomiêdzy obiektami. Diagram jest w formacie PlantUML, ale dla wygody wrzucam tak¿e png.
