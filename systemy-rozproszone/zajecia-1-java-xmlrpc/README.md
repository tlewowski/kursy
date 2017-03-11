Projekt Mavenowy, ale powinien być bez problemu obsłużony zarówno przez Eclipse jak i IntelliJ (może być konieczne doinstalowanie
wtyczki wspierającej Maven, ale jest dostępna dla każdego ze środowisk). Żeby go użyć należy zaimportować aktualny katalog
(tzn. ten, w którym znajduje się aktualnie czytany plik README) jako projekt Maven do swojego IDE. Eclipse i IntelliJ
automatycznie ściągają i umieszczają konieczne paczki we właściwych ścieżkach. **Nie próbujcie** ściągać ręcznie bibliotek
wymaganych w pliku POM (definicji projektu Maven), bo potrzebne są jeszcze zależności przejściowe, które Maven ściąga
automatycznie, a których nie ma bezpośrednio opisanych w pliku POM (np. Apache XMLRPC polega na logowaniu przez SLF4J).

Używana wersja Apache XMLRPC (3.1.3) **nie jest** zgodna z wersją opisaną w instrukcji laboratoryjnej (1.2b) i ma względem niej sporo różnic.
Na prowadzonych przeze mnie zajęciach można używać którejkolwiek, obie zapewniają funkcjonalność wystarczającą do wykonania ćwiczenia.
Trzeba tylko wiedzieć, której wersji się używa, ponieważ występują pewne różnice (np. w obsłudze stanu - 1.2b przechowuje
jeden handler dla każdego fragmentu stanu, natomiast 3.1.3 tworzy osobny handler dla każdego żądania - przykład dostępny w kodzie)

Klasy są przeznaczone raczej do odpalania z poziomu IDE - nie trzeba wtedy ustawiać zmiennej CLASSPATH na zawierającą jar-y z xmlrpc.

Alternatywnie można przekonać Mavena żeby budował uberjary (tzn. lokalne klasy pakował razem ze wszystkimi zależnościami), ale to wymaga trochę konfiguracji i nie wchodzi w zakres tego kursu, więc nie musimy się tym przejmować.