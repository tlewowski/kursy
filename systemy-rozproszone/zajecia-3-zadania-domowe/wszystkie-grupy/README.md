Kontynuujemy implementowanie namiastki systemu zarz�dzania biblioteczk�. 

W ramach zadania domowego zajmiemy si� tworzeniem dodatkowych funkcjonalno�ci zwi�zanych ze wsp�dzieleniem danych. 

Nale�y zaimplementowa�: 

**Client**, uruchamiany na maszynie u�ytkownika. W tej klasie b�dziemy wywo�ywa� ��dania dost�pu do zasob�w.

**ReverseProxy**, reprezentuj�cy dzia�anie proxy widocznego dla klienta (endpoint) - w praktyce np. Nginx.

**CachingProxy**, reprezentuj�cy pami�� podr�czn� zasob�w - w praktyce np. Varnish lub Squid

**Application**, reprezentuj�cy aplikacj� zapewniaj�c� dost�p do wszystkich zasob�w
 
Typ **Resource** reprezentuj�cy pobierany zas�b. Klasa **Resource** powinna udost�pnia� pola: `String uri` i `int value`.

**ReverseProxy** powinien oferowa� dwie operacje:

 - `Resource get(String uri)` - ma zwr�ci� u�ytkownikowi zas�b. Procedura pobrania zasobu obejmuje odpytanie serwera cache. W przypadku, je�li zas�b
 znajduje si� na serwerze CachingProxy jest natychmiast zwracany, w przeciwnym razie CachingProxy wykonuje zapytanie do Application i zapisuje sobie wynik. 
 Je�li zas�b znajdowa� si� na serwerze aplikacji, nale�y zwr�ci� do u�ytkownikowi i umie�ci� na serwerze cache (zobacz diagram)
 - `int create(Resource res)` - przekierowuje zapytanie do serwera `Application`. 
 
**CachingProxy** powinno oferowa� jedn� operacj�:
 
 - `Resource get(String uri)` - zwraca zas�b je�li jest dost�pny na serwerze CachingProxy, je�li nie jest dost�pny wysy�a zapytanie do serwera Application
  je�li zas�b jest na serwerze Application zapisuje go lokalnie w ramach serwera CachingProxy (�eby nast�pnym razem nie odpytywa�). 
 
**Application** ma oferowa� dwie operacje:

 - `Resource get(String uri)` - pobranie podanego zasobu z serwera. Je�li zas�b nie istnieje na serwerze, nale�y zwr�ci� `null`
 - `int create(Resource res)` - utworzenie podanego zasobu na serwerze. Funkcja ma zwraca� kody odpowiedzi HTTP - 201 (CREATED) je�li utworzenie si� powiod�o,
 403 (FORBIDDEN) je�li na serwerze istnieje ju� zas�b o podanym `uri` (`uri` jest polem w ramach obiekt�w `Resource`)
 
Scenariusz testowania:

 - `Client` ma dost�p tylko do `ReverseProxy`
 - `Client` dodaje dwa zasoby - oczekujemy na 2x 201
 - `Client` pr�buje doda� drugi raz ten sam zas�b - oczekujemy na 403
 - `Client` pr�buje pobra� nieistniej�cy zas�b - oczekujemy na �a�cuch ��da� Client -> ReverseProxy -> CachingProxy -> Application i wynik `null`
 - `Client` pr�buje pobra� istniej�cy zas�b - oczekujemy na �a�cuch ��da� Client -> ReverseProxy -> CachingProxy -> Application
 - `Client` pr�buje pobra� ten sam zas�b po raz drugi - oczekujemy na �a�cuch ��da� Client -> ReverseProxy -> CachingProxy (bez Application!)
 
W pliku .puml w tym folderze znajduje si� diagram UML obrazuj�cy sekwencj� komunikacji pomi�dzy klientami a serwerem. 
Diagram jest w formacie PlantUML, ale dla wygody wrzucam tak�e png.
