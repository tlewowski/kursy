using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Client
{
    class Program
    {
        static void Main(string[] args)
        {
            // duża część magii jest zawarta w klikaniu, którego niestety nie widać w kodzie końcowym
            // adresy i takie tam są w pliku App.config, generowanym automatycznie
            ServiceReference1.ExampleServiceClient c = new ServiceReference1.ExampleServiceClient();

            // wstawiamy elementy
            c.InsertElement(0, 1.0);
            c.InsertElement(0, 2.1);
            c.InsertElement(2, 4);

            // sprawdzenie czy wszystkie operacje działają - wywołania synchroniczne
            Console.Out.WriteLine(c.GetValue(0));
            Console.Out.WriteLine(c.GetPartialSum(0, 1));
            Console.Out.WriteLine(c.GetStats().Average);
            Console.ReadKey();
        }
    }
}
