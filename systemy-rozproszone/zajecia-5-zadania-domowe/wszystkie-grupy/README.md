Dzisiaj zadanie nieco utrudnione, bo bez dok³adnego schematu sekwencji. 

Nale¿y zaimplementowaæ:

**Client**, uruchamiany na maszynie u¿ytkownika. Mo¿e byæ dowolna liczba klientów.

**RESTServer** eksponuj¹cy nastêpuj¹ce metody HTTP:

 -  `POST` z obiektem `Book` na adresie `/books` (XML lub JSON), w wiadomoœci zwrotnej - id zasobu (adres /book/<x> pod którym bêdzie umieszczony zasób)
 - `GET` na adresie `/book/<x>` zwracaj¹cy wczeœniej wstawiony zasób (XML i JSON) lub 404 w przypadku braku
 - `GET` na adresie `/books` zwracaj¹cy wszystkie zasoby
 - `DELETE` na adresie `/book/<x>` usuwaj¹cy istniej¹cy obiekt lub 404 w przypadku braku zasobu

Przy oddaniu zadania nale¿y zaprezentowaæ zarówno serwer jak i klienta tego serwera, wykorzystuj¹cego wszystkie
podane powy¿ej wywo³ania
 