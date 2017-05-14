Dzisiaj zadanie nieco utrudnione, bo bez dok�adnego schematu sekwencji. 

Nale�y zaimplementowa�:

**Client**, uruchamiany na maszynie u�ytkownika. Mo�e by� dowolna liczba klient�w.

**RESTServer** eksponuj�cy nast�puj�ce metody HTTP:

 -  `POST` z obiektem `Book` na adresie `/books` (XML lub JSON), w wiadomo�ci zwrotnej - id zasobu (adres /book/<x> pod kt�rym b�dzie umieszczony zas�b)
 - `GET` na adresie `/book/<x>` zwracaj�cy wcze�niej wstawiony zas�b (XML i JSON) lub 404 w przypadku braku
 - `GET` na adresie `/books` zwracaj�cy wszystkie zasoby
 - `DELETE` na adresie `/book/<x>` usuwaj�cy istniej�cy obiekt lub 404 w przypadku braku zasobu

Przy oddaniu zadania nale�y zaprezentowa� zar�wno serwer jak i klienta tego serwera, wykorzystuj�cego wszystkie
podane powy�ej wywo�ania
 