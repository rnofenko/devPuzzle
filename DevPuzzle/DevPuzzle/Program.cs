using System;
using DevPuzzle.Tiles;

namespace DevPuzzle
{
    class Program
    {
        static void Main(string[] args)
        {
            var res = new TileOptimizer().Find();
            Console.WriteLine(res);
        }
    }
}
