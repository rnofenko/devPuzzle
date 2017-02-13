using System;
using System.IO;
using System.Linq;
using System.Text;

namespace DevPuzzle.NumbersInString
{
    public class NumberZipperToString
    {
        public void Zip()
        {
            var builder = new StringBuilder();
            var finder = new DigitSelectFinder();
            for (var i = 0; i <= 999; i++)
            {
                try
                {
                    var res = zip(999, i, finder);
                    Console.WriteLine($"{i},{res.Length}");
                    builder.AppendLine($"{i},{res.Length},{res}");
                }
                catch (InvalidOperationException e)
                {
                    Console.WriteLine($"{i},0,{e.Message}");
                    builder.AppendLine($"{i},0,{e.Message}");
                }
            }
            File.WriteAllText(@"c:\temp\numbersInString.csv", builder.ToString());
        }

        private void check(string final)
        {
            var allNumbers = Enumerable.Range(0, 999 + 1).Select(x => x.ToString($"D{3}")).ToList();
            foreach (var number in allNumbers)
            {
                if (!final.Contains(number))
                {
                    throw new InvalidOperationException("Fail");
                }
            }
        }

        private string zip(int count, int startingIndex, INumberFinder finder)
        {
            var width = count.ToString().Length;
            var stats = Enumerable.Range(0, width + 1).ToDictionary(x => x, x => 0);
            var allNumbers = Enumerable.Range(0, count + 1).Select(x => x.ToString($"D{width}")).ToList();
            var starting = allNumbers[startingIndex];
            var final = starting;
            allNumbers.Remove(starting);
            stats[0]++;

            while (allNumbers.Any())
            {
                var res = finder.Find(final, allNumbers);
                final = res.NewLine;
                stats[res.Size]++;
            }
            return final;
        }
    }
}
