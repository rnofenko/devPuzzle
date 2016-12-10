using System.Collections.Generic;

namespace DevPuzzle.Horses
{
    public class HorseBoardState
    {
        public HorseBoard Board { get; }

        public List<HorseBoard> History { get; } = new List<HorseBoard>();

        public HorseBoardState(HorseBoard board)
        {
            Board = board;
        }

        public HorseBoardState(HorseBoard board, HorseBoardState state)
        {
            Board = board;
            History.AddRange(state.History);
            History.Add(state.Board);
        }
    }
}