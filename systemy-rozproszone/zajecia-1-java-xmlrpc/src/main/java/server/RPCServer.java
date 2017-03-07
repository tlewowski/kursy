package server;

import core.IHandler;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcStreamServer;
import org.apache.xmlrpc.webserver.WebServer;

import java.io.IOException;

// główna klasa serwera XML-RPC
// RPCServer jest instancją IHandlera - to upraszcza wnętrznościom xmlrpc tworzenie nowych
// handlerów - można sobie z tym radzić przez podmienienie RequestFactoryFactory, ale to szczegół biblioteki
// a w tym kursie nie chodzi o szczegóły bibliotek
public class RPCServer implements IHandler {

  // prosta operacja - dodanie hello jako prefiksu do podanego stringa
  // w ramach monitorowania drukujemy sobie utworzony ciąg znaków
  // wraz z identyfikatorem wątku na którym jest wykonywana operacja
  // identyfikator nie jest konieczny, ale może być ciekawym eksperymentem -
  // czy mamy wątek per żądanie? może pulę? a może wszystko idzie na jednym wątku?
  public String addHello(String s) {
    String result = "hello " + s;
    System.out.println(String.format("%d: %s", Thread.currentThread().getId(), result));
    return result;
  }

  // to tylko uproszczony przykład dydaktyczny, w ramach którego ignorujemy błędy komunikacji
  // dlatego wszystkie możliwe wyjątki propagują się aż tutaj
  // w rzeczywistym kodzie należy oczywiście wyjątki obsłużyć najszybciej jak tylko ma to sens
  public static void main(String[] args) throws XmlRpcException, IOException {

    // tworzymy serwer HTTP nasłuchujący na porcie 10000
    WebServer server = new WebServer(10000);

    // z serwera HTTP wydobywamy serwer XML-RPC
    XmlRpcStreamServer rpcServer = server.getXmlRpcServer();
    // tworzymy obiekt "uchwytów" do klas obsługujących żądania RPC
    // tutaj - szczególny obiekt, wykorzystujący refleksję do wywoływania funkcji
    // teoretycznie można też używać czegokolwiek innego dziedziczącego z XmlRpcHandlerMapping, ale po co
    PropertyHandlerMapping handlers = new PropertyHandlerMapping();

    // dodajemy uchwyty do obsługiwania zdalnych wywołań
    // IHandler, żeby działało ClientFactory
    handlers.addHandler(IHandler.class.getName(), RPCServer.class);

    // nazwa klasy nie jest żadnym szczególnym ciągiem znaków
    // równie dobrze możemy zarejestrować jakikolwiek inny jako przestrzeń nazw dla metod z podanej klasy - tylko niektóre funkcje (np. ClientFactory) nie będą działać
    handlers.addHandler("babkazpiasku", RPCServer.class);

    // ustalamy mapowanie handlerów dla serwera RPC
    rpcServer.setHandlerMapping(handlers);

    // I voila, startujemy z serwerem!
    System.out.println("Startujemy serwer!");
    server.start();
  }
}
