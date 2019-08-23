using System;
using System.Collections.Generic;
using System.Linq;
using DevPuzzle.Core;

namespace DevPuzzle.SquareContagious
{
    public class SquareContagiousRunner
    {
        public void Run()
        {
            run(2);
        }

        private void run(int size)
        {
            var pointsCount = 0;
            while (pointsCount < size)
            {
                pointsCount++;
                run(size, pointsCount);
            }
        }

        private void run(int size, int pointsCount)
        {
            Console.WriteLine($"\r\nSize={size} Points count={pointsCount}");
            var points = Enumerable.Range(0, pointsCount).Select(x => new Pos {X = x}).ToList();
            
            while (points != null)
            {
                Console.WriteLine(string.Join("  ", points));
                points = nextPositions(points, size);
            }
        }

        private List<Pos> nextPositions(IList<Pos> points, int size)
        {
            return null;
            //var res = new List<>();
        }
    }
}
