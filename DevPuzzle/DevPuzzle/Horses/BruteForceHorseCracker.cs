using System;
using System.Collections.Generic;
using System.Linq;
using DevPuzzle.Core;

namespace DevPuzzle.Horses
{
    public class BruteForceHorseCracker
    {
        private readonly List<string> _uniqueBoards = new List<string>();

        public void Crack()
        {
            var board = HorseBoard.CreateStartPostion();
            var state = new BruteForceState(board);
            var result = doIteration(new[] {state});
            print(result);
        }

        private void print(BruteForceState state)
        {
            Console.ForegroundColor = ConsoleColor.Gray;
            Console.WriteLine();
            Console.WriteLine("Total Steps = " + state.History.Count);
            var i = 0;
            foreach (var horseBoard in state.History)
            {
                Console.WriteLine($"Step {++i}");
                print(horseBoard);
            }
            Console.WriteLine($"Step {++i}");
            print(state.Board);
        }

        private void print(HorseBoard board)
        {
            var coord = new Pos();
            for (coord.Y = 0; coord.Y < board.Size.Height; coord.Y++)
            {
                for (coord.X = 0; coord.X < board.Size.Width; coord.X++)
                {
                    var horse = board.Horses.FirstOrDefault(x => x.Coord == coord);
                    var sign = "_";
                    if (horse.Color == HorseColor.White)
                    {
                        sign = "W";
                        Console.ForegroundColor = ConsoleColor.White;
                    }
                    else if (horse.Color == HorseColor.Black)
                    {
                        sign = "R";
                        Console.ForegroundColor = ConsoleColor.Red;
                    }
                    Console.Write(sign + " ");
                    Console.ForegroundColor = ConsoleColor.Gray;
                }
                Console.WriteLine();
            }
            Console.WriteLine();
        }

        private BruteForceState doIteration(IEnumerable<BruteForceState> oldStates)
        {
            var newStates = new List<BruteForceState>();
            foreach (var state in oldStates)
            {
                var newBoards = findAllPossibleMoves(state.Board);
                foreach (var board in newBoards.Where(addToUniqueStorage))
                {
                    var newState = new BruteForceState(board, state);

                    if (board.IsFinalState())
                    {
                        return newState;
                    }

                    newStates.Add(newState);
                }
            }

            return doIteration(newStates);
        }

        private bool addToUniqueStorage(HorseBoard board)
        {
            var key = board.GetKey();
            if (_uniqueBoards.Contains(key))
            {
                return false;
            }
            _uniqueBoards.Add(key);
            return true;
        }

        private List<HorseBoard> findAllPossibleMoves(HorseBoard board)
        {
            var boards = new List<HorseBoard>();
            for(var i=0;i<board.Horses.Length;i++)
            {
                var horse = board.Horses[i];
                foreach (var horseMove in horse.GetAllPossibleMoves(board.Size))
                {
                    var newBoard = board.Clone();
                    if (newBoard.MoveHorse(i, horseMove))
                    {
                        boards.Add(newBoard);
                    }
                }
            }
            return boards;
        }
    }
}