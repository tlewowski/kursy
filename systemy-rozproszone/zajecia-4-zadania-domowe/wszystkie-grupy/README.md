Implementujemy serwer multimediów z u¿yciem C# i WCF.

Nale¿y zaimplementowaæ:

**Client**, uruchamiany na maszynie u¿ytkownika. Bêd¹ uruchamiane 2 instancje klienta.

**MultimediaServer** eksponuj¹cy nastêpuj¹ce funkcjonalnoœci:

 - `List<String> getImages()`- metoda asynchroniczna (OneWay), zwracaj¹ca kolekcjê dostêpnych obrazów - powinna trwaæ co
 najmniej 3 sekundy. 
 - `List<Tag> getTags()` - metoda asynchroniczna zwracaj¹ca listê dostêpnych tagów - powinna trwaæ co najmniej 3 sekundy
 - `bool postTag(Tag tag)` - metoda asynchroniczna dodaj¹ca tag do kolekcji - powinna zwracaæ false jeœli nie uda siê dodaæ, np. `tag == null`.
 Powinna trwaæ co najmniej 1 sekundê
 - `Stream getImage(String imageUri)` - metoda zwracaj¹ca strumieñ zawieraj¹cy ¿¹dany obraz

`Tag` jest struktur¹ sk³adaj¹c¹ siê z dwóch pól:
 
 - `String name` - nazwa taga
 - `Date createdAt` - data utworzenia taga
 
Metoda zwracaj¹ca strumieñ bêdzie wymaga³a osobnego endpointa, w razie potrzeby mo¿na u¿yæ te¿ osobnego serwisu.
Wymogiem jest u¿ywanie ich z jednego pliku Ÿród³owego klienta. Transmisje asynchroniczne bêd¹ wymaga³y trybu Duplex.
Tagi powinny byæ wspó³dzielone miêdzy klientami (InstanceMode.Single)
 
Scenariusz testowania:

 - `Client` wysy³a ¿¹dania o listê obrazów i listê tagów
 - `Client` dorzuca 3 tagi (oczekujemy ¿e zajmie to ok. 1 sekundy, bo dodawanie powinno byæ równoleg³e - nie mo¿na u¿yæ kolejkowania na poziomie instancji)
 - Po otrzymaniu odpowiedzi na 3 dodane tagi `Client` ponownie wysy³a ¿¹danie o listê tagów
 - Na drugiej otrzymanej liœcie oczekujemy ¿e bêd¹ 3 dodatkowe tagi, na pierwszej mog¹ byæ lub nie - nale¿y sprawdziæ rozmiary listê
 - `Client` wysy³a ¿¹danie o obraz z otrzymanej listy
 - Obraz jest przesy³any strumieniowo
 
 nale¿y uruchomiæ 2 instancje klienta równolegle. Pomocniczy schemat UML dla pojedynczej interakcji w pliku zadanie-4.png