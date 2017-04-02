Kontynuujemy implementowanie namiastki systemu zarz¹dzania biblioteczk¹. 

W ramach zadania domowego zajmiemy siê tworzeniem dodatkowych funkcjonalnoœci zwi¹zanych ze wspó³dzieleniem danych. 

Nale¿y zaimplementowaæ: 

**Client**, uruchamiany na maszynie u¿ytkownika. W tej klasie bêdziemy wywo³ywaæ ¿¹dania dostêpu do zasobów.

**ReverseProxy**, reprezentuj¹cy dzia³anie proxy widocznego dla klienta (endpoint) - w praktyce np. Nginx.

**CachingProxy**, reprezentuj¹cy pamiêæ podrêczn¹ zasobów - w praktyce np. Varnish lub Squid

**Application**, reprezentuj¹cy aplikacjê zapewniaj¹c¹ dostêp do wszystkich zasobów
 
Typ **Resource** reprezentuj¹cy pobierany zasób. Klasa **Resource** powinna udostêpniaæ pola: `String uri` i `int value`.

**ReverseProxy** powinien oferowaæ dwie operacje:

 - `Resource get(String uri)` - ma zwróciæ u¿ytkownikowi zasób. Procedura pobrania zasobu obejmuje odpytanie serwera cache. W przypadku, jeœli zasób
 znajduje siê na serwerze CachingProxy jest natychmiast zwracany, w przeciwnym razie CachingProxy wykonuje zapytanie do Application i zapisuje sobie wynik. 
 Jeœli zasób znajdowa³ siê na serwerze aplikacji, nale¿y zwróciæ do u¿ytkownikowi i umieœciæ na serwerze cache (zobacz diagram)
 - `int create(Resource res)` - przekierowuje zapytanie do serwera `Application`. 
 
**CachingProxy** powinno oferowaæ jedn¹ operacjê:
 
 - `Resource get(String uri)` - zwraca zasób jeœli jest dostêpny na serwerze CachingProxy, jeœli nie jest dostêpny wysy³a zapytanie do serwera Application
  jeœli zasób jest na serwerze Application zapisuje go lokalnie w ramach serwera CachingProxy (¿eby nastêpnym razem nie odpytywaæ). 
 
**Application** ma oferowaæ dwie operacje:

 - `Resource get(String uri)` - pobranie podanego zasobu z serwera. Jeœli zasób nie istnieje na serwerze, nale¿y zwróciæ `null`
 - `int create(Resource res)` - utworzenie podanego zasobu na serwerze. Funkcja ma zwracaæ kody odpowiedzi HTTP - 201 (CREATED) jeœli utworzenie siê powiod³o,
 403 (FORBIDDEN) jeœli na serwerze istnieje ju¿ zasób o podanym `uri` (`uri` jest polem w ramach obiektów `Resource`)
 
Scenariusz testowania:

 - `Client` ma dostêp tylko do `ReverseProxy`
 - `Client` dodaje dwa zasoby - oczekujemy na 2x 201
 - `Client` próbuje dodaæ drugi raz ten sam zasób - oczekujemy na 403
 - `Client` próbuje pobraæ nieistniej¹cy zasób - oczekujemy na ³añcuch ¿¹dañ Client -> ReverseProxy -> CachingProxy -> Application i wynik `null`
 - `Client` próbuje pobraæ istniej¹cy zasób - oczekujemy na ³añcuch ¿¹dañ Client -> ReverseProxy -> CachingProxy -> Application
 - `Client` próbuje pobraæ ten sam zasób po raz drugi - oczekujemy na ³añcuch ¿¹dañ Client -> ReverseProxy -> CachingProxy (bez Application!)
 
W pliku .puml w tym folderze znajduje siê diagram UML obrazuj¹cy sekwencjê komunikacji pomiêdzy klientami a serwerem. 
Diagram jest w formacie PlantUML, ale dla wygody wrzucam tak¿e png.
