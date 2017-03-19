package core;

// interfejs jest nam potrzebny żeby wygodnie używać ClientFactory
public interface IHandler {

  // funkcje, które będą rejestrowane dla tego interfejsu
  String addHello(String s);

  // funkcja przykładowa do przesyłania własnych obiektów
  DomainObject toDomainObject(String s);
}