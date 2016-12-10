using System;
using System.Collections.Generic;
using System.Linq;

namespace DevPuzzle.TriangleTees
{
    public class BruteForceTeeCracker
    {
        private int _tries;

        public void Crack()
        {
            var solutions = Enumerable.Range(0, 1)
                .Select(x => new TeeBoardState(x))
                .Select(findSolution)
                .Where(x => x != null)
                .ToList();
        }

        private List<TeeBoardState> findSolution(TeeBoardState state)
        {
            var newStates = TeeMove.GetAll()
                .Select(x => makeMoveIfPossible(state, x))
                .Where(x => x != null)
                .ToList();
            if (state.History.Count > 11)
            {
                return newStates;
            }
            return newStates.SelectMany(findSolution).ToList();
        }

        private TeeBoardState makeMoveIfPossible(TeeBoardState from, TeeMove move)
        {
            _tries++;
            if (_tries%100000 == 0)
            {
                Console.WriteLine($"Tries {_tries}");
            }

            if (!from.CanBeApplied(move))
            {
                return null;
            }
            return new TeeBoardState(from, move);
        }
    }
}
