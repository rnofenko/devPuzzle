using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using DevPuzzle.Core;

namespace DevPuzzle.CricleInSquare
{
    public class SquareCountsCalculator
    {
        public void CalculateAll()
        {
            Console.WriteLine(toCsvFormatHeader("\t"));

            var builder = new StringBuilder();
            builder.AppendLine(toCsvFormatHeader(","));
            foreach (var oneSet in calculateAll(2, 500))
            {
                builder.AppendLine(toCsvFormat(oneSet, ","));
                print(oneSet);
            }
            File.WriteAllText(@"c:\temp\squareCombinations.csv", builder.ToString());
        }

        private IEnumerable<Dictionary<SquareStatus, int>> calculateAll(int from, int to)
        {
            for (var i = from; i < to; i++)
            {
                yield return calculate(i);
            }
        }

        private string toCsvFormatHeader(string delimiter)
        {
            return $"Size{delimiter}In{delimiter}Out{delimiter}Strikethrough";
        }

        private string toCsvFormat(Dictionary<SquareStatus, int> res, string delimiter)
        {
            var all = Convert.ToInt32(Math.Sqrt(res.Values.Sum()));
            return $"{all}{delimiter}{res[SquareStatus.In]}{delimiter}{res[SquareStatus.Out]}{delimiter}{res[SquareStatus.Strikethrough]}";
        }

        private void print(Dictionary<SquareStatus, int> res)
        {
            Console.WriteLine(toCsvFormat(res, "\t"));
        }

        private Dictionary<SquareStatus, int> calculate(int width)
        {
            var res = new Dictionary<SquareStatus, int> {{SquareStatus.In, 0}, {SquareStatus.Out, 0}, {SquareStatus.Strikethrough, 0}};
            var coord = new Coordinate();
            for (coord.Y = 0; coord.Y < width; coord.Y++)
            {
                for (coord.X = 0; coord.X < width; coord.X++)
                {
                    res[calcSquareStatus(coord, width)]++;
                }
            }
            return res;
        }

        private SquareStatus calcSquareStatus(Coordinate coord, int width)
        {
            var circleRadius = calcCircleRadius(width);
            var corners = new [] {coord, new Coordinate(coord.X + 1, coord.Y), new Coordinate(coord.X + 1, coord.Y + 1), new Coordinate(coord.X, coord.Y + 1)};
            var distances = corners.Select(x => calcDistanceToCenter(x, width)).ToList();
            if (distances.All(x => x > circleRadius))
            {
                return SquareStatus.Out;
            }
            if (distances.All(x => x < circleRadius))
            {
                return SquareStatus.In;
            }
            return SquareStatus.Strikethrough;
        }

        private double calcDistanceToCenter(Coordinate coord, int width)
        {
            var center = calcCenter(width);
            var k1 = Math.Abs(coord.X - center);
            var k2 = Math.Abs(coord.Y - center);
            if (k1 < 0.00001)
            {
                return k2;
            }
            if (k2 < 0.00001)
            {
                return k1;
            }
            return Math.Sqrt(k1*k1 + k2*k2);
        }

        private double calcCircleRadius(int width)
        {
            return calcCenter(width);
        }

        private double calcCenter(int width)
        {
            return width/2.0;
        }
    }

    public enum SquareStatus
    {
        Out,
        In,
        Strikethrough
    }
}
