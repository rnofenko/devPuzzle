using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using DevPuzzle.Core;

namespace DevPuzzle.Tiles
{
    public class TileOptimizer
    {
        private const int ROOM_SIZE = 11;
        private SquareTile _one = new SquareTile {Size = 1};
        private SquareTile _two = new SquareTile { Size = 2 };
        private readonly SquareTile _three = new SquareTile { Size = 3 };
        private readonly List<string> _allSolutions = new List<string>();

        public int[,] Find()
        {
            var w = Stopwatch.StartNew();
            var sequence = new List<SquareTile> {_one};
            while (true)
            {
                try
                {
                    find(sequence, 1, w);
                    saveSolution(sequence);
                    changeTile(sequence, sequence.Count - 1);
                }
                catch (NotFiniteNumberException)
                {
                    foreach (var allSolution in _allSolutions)
                    {
                        var solution = allSolution.Split(',').Select(int.Parse).Select(x => new SquareTile {Size = x}).ToList();
                        var res = build(solution);
                        print(res.Room);
                    }
                }
            }
        }

        private void saveSolution(List<SquareTile> sequence)
        {
            var solution = string.Join(",", sequence.Select(x => x.Size));
            if (!_allSolutions.Contains(solution))
            {
                _allSolutions.Add(solution);
            }
        }

        private void print(int[,] room)
        {
            var colors = new Dictionary<int, ConsoleColor> { {1,ConsoleColor.Red}, { 2, ConsoleColor.Yellow }, { 3, ConsoleColor.DarkCyan } };
            Console.WriteLine();
            Console.WriteLine();
            for (var y = 0; y < ROOM_SIZE; y++)
            {
                for (var x = 0; x < ROOM_SIZE; x++)
                {
                    Console.ForegroundColor = colors[room[x, y]];
                    Console.Write(room[x, y] + " ");
                }
                Console.WriteLine();
            }
        }

        private int[,] find(List<SquareTile> sequence, int maxOne, Stopwatch w)
        {
            var res = new BuildResult();
            var attempt = 0;
            while (res.Room == null)
            {
                attempt++;
                if (attempt%1000000 == 0)
                {
                    Console.WriteLine($"Attemp {attempt / 1000000}M sequence={sequence.Count} {Math.Round(w.Elapsed.TotalSeconds)}s");
                }                
                res = build(sequence);
                if (res.IsEmpty)
                {
                    sequence.Add(_one);
                    if (sequence.Count(x => x.Size == 1) > maxOne)
                    {
                        changeTile(sequence, sequence.Count - 1);
                    }
                }
                else if (res.IsTooBig)
                {
                    changeTile(sequence, res.ProblemTile);
                }
            }
            return res.Room;
        }

        private void changeTile(List<SquareTile> sequence, int index)
        {
            if (index < 0)
            {
                throw new NotFiniteNumberException("This is final solution");
            }

            if (sequence[index].Size == _one.Size)
            {
                sequence[index] = _two;
            }
            else if (sequence[index].Size == _two.Size)
            {
                sequence[index] = _three;
            }
            else
            {
                while (index < sequence.Count)
                {
                    sequence.RemoveAt(index);
                }
                changeTile(sequence, index - 1);
            }
        }

        private BuildResult build(List<SquareTile> sequence)
        {
            var room = new int[ROOM_SIZE, ROOM_SIZE];
            var coord = new Coordinate();
            for (var i = 0; i < sequence.Count; i++)
            {
                var tile = sequence[i];
                var res = PutResult.Busy;
                while (res == PutResult.Busy)
                {
                    res = putTile(tile, room, coord);
                    if (res == PutResult.Ok)
                    {
                        coord.GoNext(tile.Size, ROOM_SIZE);
                    }
                    else if (res == PutResult.Busy)
                    {
                        coord.GoNext(1, ROOM_SIZE);
                    }
                    else
                    {
                        return new BuildResult { IsTooBig = true, ProblemTile = i };
                    }

                    if (coord.Y == ROOM_SIZE)
                    {
                        return new BuildResult { Room = room };
                    }
                }
            }
            return new BuildResult { IsEmpty = true };
        }

        private PutResult putTile(SquareTile tile, int[,] room, Coordinate coord)
        {
            if (room[coord.X, coord.Y] > 0)
            {
                return PutResult.Busy;
            }
            if (coord.X + tile.Size > ROOM_SIZE || coord.Y + tile.Size > ROOM_SIZE)
            {
                return PutResult.TooBig;
            }
            for (var x = coord.X; x < coord.X + tile.Size; x++)
            {
                for (var y = coord.Y; y < coord.Y + tile.Size; y++)
                {
                    if (room[x, y] > 0)
                    {
                        return PutResult.TooBig;
                    }
                    room[x, y] = tile.Size;
                }
            }
            return PutResult.Ok;
        }
    }
}