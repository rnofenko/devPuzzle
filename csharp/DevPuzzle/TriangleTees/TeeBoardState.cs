using System.Collections.Generic;
using System.Linq;

namespace DevPuzzle.TriangleTees
{
    public class TeeBoardState
    {
        private const int SIZE = 15;

        public List<TeeMove> History { get; set; }

        public int[] Board { get; set; }

        public int StartHole { get; }

        public TeeBoardState(int startHolde)
        {
            StartHole = startHolde;
            Board = Enumerable.Range(1, SIZE).ToArray();
            Board[startHolde] = 0;
            History = new List<TeeMove>();
        }

        public TeeBoardState(TeeBoardState state, TeeMove move)
        {
            StartHole = state.StartHole;
            Board = state.Board.ToArray();
            History = state.History.ToList();
            apply(move);
        }

        public bool CanBeApplied(TeeMove move)
        {
            return Board[move.From] > 0 && Board[move.To] == 0 && Board[move.Kill] > 0;
        }

        private void apply(TeeMove move)
        {
            History.Add(move);
            Board[move.To] = Board[move.From];
            Board[move.From] = 0;
            Board[move.Kill] = 0;
        }

        public override string ToString()
        {
            var moves = string.Join("_", History.Select(x => x.Id));
            return $"H{History.Count} {StartHole}_{moves}";
        }
    }
}