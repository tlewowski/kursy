using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel;
using System.ServiceModel.Description;
using System.Text;
using System.Threading.Tasks;

namespace Task2ServiceHost
{
    class Program
    {
        static void Main(string[] args)
        {
            Uri root = new Uri("http://localhost:9999/task2");
            ServiceHost host = new ServiceHost(typeof(zajecia2wcf.ExampleService), root);

            try
            {
                WSHttpBinding binding = new WSHttpBinding();
                host.AddServiceEndpoint(typeof(zajecia2wcf.IExampleService), binding, "Task2Service");

                ServiceMetadataBehavior behavior = new ServiceMetadataBehavior();
                behavior.HttpGetEnabled = true;
                host.Description.Behaviors.Add(behavior);

                Console.WriteLine($"Root: {root.OriginalString}");
                host.Open();
                Console.WriteLine("Service started!");
                Console.ReadLine();
                host.Close();
            }
            catch(CommunicationException err)
            {
                Console.Error.WriteLine("Service failed!");
                Console.Error.WriteLine(err.Message);
                Console.ReadKey();
                host.Abort();
            }
        }
    }
}
