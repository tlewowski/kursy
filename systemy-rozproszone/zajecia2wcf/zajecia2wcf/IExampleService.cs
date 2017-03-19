using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace zajecia2wcf
{
    // UWAGA: możesz użyć polecenia „Zmień nazwę” w menu „Refaktoryzuj”, aby zmienić nazwę interfejsu „IService1” w kodzie i pliku konfiguracji.
    [ServiceContract]
    public interface IExampleService
    {

        // pobieranie danych spod indeksu
        [OperationContract]
        double GetValue(int index);

        // pobieranie sumy 'count' liczb zaczynając od indeksu 'start'
        [OperationContract]
        double GetPartialSum(int start, int count);

        // wstawianie nowego elementu
        [OperationContract]
        void InsertElement(int index, double value);

        // pobranie statystyk zbioru danych
        [OperationContract]
        Statistics GetStats();
    }

    // Użyj kontraktu danych, jak pokazano w poniższym przykładzie, aby dodać typy złożone do operacji usługi.
    // Możesz dodać pliki XSD do projektu. Po skompilowaniu projektu możesz bezpośrednio użyć zdefiniowanych w nim typów danych w przestrzeni nazw „zajecia2wcf.ContractType”.
    [DataContract]
    public class Statistics
    {
        double average = 0.0;
        double variance = 0.0;
        int count = 0;

        [DataMember]
        public double Average
        {
            get { return average; }
            set { average = value; }
        }

        [DataMember]
        public double Variance
        {
            get { return variance; }
            set { variance = value; }
        }

        [DataMember]
        public int Count
        {
            get { return count; }
            set { count = value; }
        }
    }
}
