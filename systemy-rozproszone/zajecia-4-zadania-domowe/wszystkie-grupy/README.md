Implementujemy serwer multimedi�w z u�yciem C# i WCF.

Nale�y zaimplementowa�:

**Client**, uruchamiany na maszynie u�ytkownika. B�d� uruchamiane 2 instancje klienta.

**MultimediaServer** eksponuj�cy nast�puj�ce funkcjonalno�ci:

 - `List<String> getImages()`- metoda asynchroniczna (OneWay), zwracaj�ca kolekcj� dost�pnych obraz�w - powinna trwa� co
 najmniej 3 sekundy. 
 - `List<Tag> getTags()` - metoda asynchroniczna zwracaj�ca list� dost�pnych tag�w - powinna trwa� co najmniej 3 sekundy
 - `bool postTag(Tag tag)` - metoda asynchroniczna dodaj�ca tag do kolekcji - powinna zwraca� false je�li nie uda si� doda�, np. `tag == null`.
 Powinna trwa� co najmniej 1 sekund�
 - `Stream getImage(String imageUri)` - metoda zwracaj�ca strumie� zawieraj�cy ��dany obraz

`Tag` jest struktur� sk�adaj�c� si� z dw�ch p�l:
 
 - `String name` - nazwa taga
 - `Date createdAt` - data utworzenia taga
 
Metoda zwracaj�ca strumie� b�dzie wymaga�a osobnego endpointa, w razie potrzeby mo�na u�y� te� osobnego serwisu.
Wymogiem jest u�ywanie ich z jednego pliku �r�d�owego klienta. Transmisje asynchroniczne b�d� wymaga�y trybu Duplex.
Tagi powinny by� wsp�dzielone mi�dzy klientami (InstanceMode.Single)
 
Scenariusz testowania:

 - `Client` wysy�a ��dania o list� obraz�w i list� tag�w
 - `Client` dorzuca 3 tagi (oczekujemy �e zajmie to ok. 1 sekundy, bo dodawanie powinno by� r�wnoleg�e - nie mo�na u�y� kolejkowania na poziomie instancji)
 - Po otrzymaniu odpowiedzi na 3 dodane tagi `Client` ponownie wysy�a ��danie o list� tag�w
 - Na drugiej otrzymanej li�cie oczekujemy �e b�d� 3 dodatkowe tagi, na pierwszej mog� by� lub nie - nale�y sprawdzi� rozmiary list�
 - `Client` wysy�a ��danie o obraz z otrzymanej listy
 - Obraz jest przesy�any strumieniowo
 
 nale�y uruchomi� 2 instancje klienta r�wnolegle. Pomocniczy schemat UML dla pojedynczej interakcji w pliku zadanie-4.png