using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace zajecia2wcf
{
    // Przykład przechowywania stanu w przypadku użycia usług WCF
    public class ExampleService : IExampleService
    {
        public double GetPartialSum(int start, int count)
        {
            return values.GetRange(start, count).Sum();
        }

        public Statistics GetStats()
        {
            Statistics stats = new Statistics();
            stats.Count = values.Count;
            if(stats.Count > 0)
            {
                stats.Average = values.Sum() / stats.Count;

                // obliczanie wariancji - suma (x - xśr)^2
                stats.Variance = (from x in values select Math.Pow(x - stats.Average, 2)).Sum();
            }

            return stats;
        }

        public double GetValue(int index)
        {
            return values.ElementAt(index);
        }

        public void InsertElement(int index, double value)
        {
            values.Insert(index, value);
        }

        private List<double> values = new List<double>();
    }
}
