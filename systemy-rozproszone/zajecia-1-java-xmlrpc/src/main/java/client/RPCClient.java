package client;

import core.IHandler;
import org.apache.xmlrpc.XmlRpcRequest;
import org.apache.xmlrpc.client.AsyncCallback;
import org.apache.xmlrpc.client.TimingOutCallback;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.util.ClientFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

// główna klasa klienta RPC
// to tylko uproszczony przykład dydaktyczny, w ramach którego ignorujemy błędy komunikacji
// dlatego wszystkie możliwe wyjątki propagują się aż tutaj
// w rzeczywistym kodzie należy oczywiście wyjątki obsłużyć najszybciej jak tylko ma to sens
public class RPCClient {
  public static void main(String[] args) throws Throwable {
    // klienta trzeba skonfigurować - tworzymy konfigurację
    XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();

    // adres serwera musi zawierać protokół (http), nazwę hosta (localhost) i port (10000)
    config.setServerURL(new URL("http://localhost:10000"));
    config.setEnabledForExtensions(true);
    config.setConnectionTimeout(1000);

    // tworzymy klienta
    XmlRpcClient client = new XmlRpcClient();

    // konfigurujemy klienta
    client.setConfig(config);

    // i pierwsze proste, synchroniczne zapytanie
    Object result = client.execute("core.IHandler.addHello", Arrays.asList("world"));

    // Co dostaliśmy w wyniku? powinno być hello world
    System.out.println(String.format("Z serwera miało przyjść hello world a przyszło: %s", (String)result));

    // sprawdźmy też czy działają inne przestrzenie nazw dla funkcji!
    Object babkaResult = client.execute("babkazpiasku.addHello", Arrays.asList("universe"));

    // Co dostaliśmy w wyniku? powinno być hello universe
    System.out.println(String.format("Z serwera miało przyjść hello universe a przyszło: %s", (String)babkaResult));

    // ok, ale tracimy mnóstwo bezpieczeństwa wynikającego z typów. Czy można inaczej?
    // nie pytałbym gdyby nie można było, prawda?

    // fabryka klientów, podajemy prototyp jako argument
    ClientFactory factory = new ClientFactory(client);

    // warunek - musimy mieć wydzielony interfejs, więc utworzyliśmy IHandler
    IHandler handler = (IHandler)factory.newInstance(IHandler.class);

    // i teraz można synchronicznie wywoływać po sieci funkcje, jak z lokalnego obiektu
    // zupełnie ładnie się typy konwertują i w ogóle - ale trzeba z tym uważać, żeby mieć zgodność interfejsów
    // inaczej będą problemy, które ciężko wyśledzić - i dodatkowo gubimy wyjątki z sygnatur funkcji
    String s = handler.addHello("me!");

    // sprawdzenie wyniku
    System.out.println(String.format("Z serwera miało przyjść hello me! a przyszło %s", s));

    // I na koniec wywołania asynchroniczne
    // Tutaj raczej nie ma już takich ślicznych automatycznych konwerterów jak ClientFactory

    // tworzymy wywołanie zwrotne - wewnątrz funkcji bo jesteśmy leniwi, ale normalnie zrobilibyśmy to w osobnym pliku
    class Callback implements AsyncCallback {
      public void handleResult(XmlRpcRequest xmlRpcRequest, Object o) {
        // Czy to zawsze przychodzi? Czy zawsze w tym samym momencie?
        System.out.println(String.format("Z serwera miało przyjść hello koala a przyszło %s", (String)o));
      }

      public void handleError(XmlRpcRequest xmlRpcRequest, Throwable throwable) {
        System.err.println("Nie spodziewaliśmy się błędu!");
        System.err.println(throwable.getMessage());
        throwable.printStackTrace();
      }
    }

    // i wywołujemy funkcję asynchroniczną
    client.executeAsync("core.IHandler.addHello", Arrays.asList("koala"), new Callback());

    // kłopocik - nie wiemy kiedy się ta funkcja skończy!
    // ale i na to jest ratunek: TimingOutCallback!
    // co prawda nie dla poprzedniego wywołania, ale będziemy wiedzieć na przyszłość
    TimingOutCallback cb1 = new TimingOutCallback(1000); // timeout w milisekundach
    TimingOutCallback cb2 = new TimingOutCallback(1000); // timeout w milisekundach
    TimingOutCallback cb3 = new TimingOutCallback(1000); // timeout w milisekundach

    // wywołania z callbackami oczekującymi - każde wywołanie potrzebuje swojego callbacku
    client.executeAsync("core.IHandler.addHello", Arrays.asList("panda"), cb1);
    client.executeAsync("core.IHandler.addHello", Arrays.asList("zebra"), cb2);
    client.executeAsync("core.IHandler.addHello", Arrays.asList("waran"), cb3);

    // można teraz zaczekać aż zadania się skończą
    String res1 = (String)cb1.waitForResponse();
    String res2 = (String)cb2.waitForResponse();
    String res3 = (String)cb3.waitForResponse();

    System.out.println(String.format("Z serwera miało przyjść hello panda a przyszło %s", res1));
    System.out.println(String.format("Z serwera miało przyjść hello zebra a przyszło %s", res2));
    System.out.println(String.format("Z serwera miało przyjść hello waran a przyszło %s", res3));

    // można też połączyć własny callback z TimingOutCallbackiem, ale to już zostawiam Wam ;)
  }
}
