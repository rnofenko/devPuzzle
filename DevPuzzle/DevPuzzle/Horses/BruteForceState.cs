using System.Collections.Generic;

namespace DevPuzzle.Horses
{
    public class BruteForceState
    {
        public HorseBoard Board { get; }

        public List<HorseBoard> History { get; } = new List<HorseBoard>();

        public BruteForceState(HorseBoard board)
        {
            Board = board;
        }

        public BruteForceState(HorseBoard board, BruteForceState state)
        {
            Board = board;
            History.AddRange(state.History);
            History.Add(state.Board);
        }
    }
}