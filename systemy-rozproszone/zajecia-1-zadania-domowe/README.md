W tym folderze bêd¹ umieszczane zadania domowe dotycz¹ce komunikacji z u¿yciem XMLRPC.

Zadania s¹ osobne dla ka¿dej z grup. Po rozwi¹zaniu zadania nale¿y projekt skompresowaæ w pojednczy .zip, wzglêdnie .tar.gz i wgraæ na ePortal - zadania bêd¹ odbierane na pocz¹tku
nastêpnych zajêæ.

W razie powa¿nych problemów zachêcam do przegl¹dania StackOverflow lub kontaktu ze mn¹ przez standardowy mail PWr - w szczególnoœci jeœli przyk³ady nie obrazuj¹

Uwagi ogólne: 

W szczególnoœci przy u¿ywaniu Eclipse, jeœli wydaje Wam siê ¿e kod zosta³ poprawnie zmodyfikowany, a na kliencie otrzymujecie informacjê, ¿e "nie da siê znaleŸæ funkcji", "niew³aœciwe typy parametrów"
lub coœ innego tego typu, upewnijcie siê, ¿e odpalona jest poprawna wersja serwera - WebServer Apache XMLRPC dopiero po ok. 30 sekundach od uruchomienia rzuca b³êdem "Cannot bind - port already in use", wiêc mo¿na
to przeoczyæ przy uruchamianiu. Wszystkie instancje uruchomione w Eclipse s¹ dostêpne z poziomu tamtejszej konsoli - przy wprowadzaniu zmian w serwerze nale¿y najpierw wy³¹czyæ wszystkie instancje, a dopiero
potem uruchomiæ nowy serwer.

Apache XMLRPC w wersji 3.1.3 tworzy now¹ instancjê handlera dla ka¿dego po³¹czenia, wiêc nie mo¿na przechowywaæ stanu w obiekcie handlera.
Przyk³ad dzia³aj¹cego rozwi¹zania jest w kodzie przyk³adu w tym repozytorium.

Apache XMLRPC umo¿liwia przesy³anie w³asnych typów danych, ale wymaga to implementacji specjalnej klasy t³umacz¹cej. Nie jest to nic trudnego,
ale jest trochê pisania dodatkowego kodu, wiêc nie trzeba tego robiæ. Dla zainteresowanych szczegó³y s¹ tutaj: https://ws.apache.org/xmlrpc/advanced.html

Kody odpowiedzi s¹ raczej dzia³aniem w stylu C/Basha ni¿ Javy i stosujemy je tutaj tylko dla uproszczenia. Zasadniczo równie dobrze moglibyœmy 
rzuciæ wyj¹tek i przechwyciæ do w odpowiednim miejscu (nie robimy tego, bo wprowadza to trochê dodatkowej z³o¿onoœci)
