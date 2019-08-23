using System.Collections.Generic;

namespace DevPuzzle.TriangleTees
{
    public class TeeMove
    {
        private static List<TeeMove> _allMoves;
        private static int _id;

        public int From { get; set; }
        public int To { get; set; }
        public int Kill { get; set; }
        public int Id { get; }

        public TeeMove()
        {
            Id = ++_id;
        }

        public override string ToString()
        {
            return $"{From}-{To}";
        }

        public static List<TeeMove> GetAll()
        {
            if (_allMoves == null)
            {
                _allMoves = getAll();
            }
            return _allMoves;
        }

        private static List<TeeMove> getAll()
        {
            return new List<TeeMove>
            {
                new TeeMove {From = 0, Kill = 1, To = 3},
                new TeeMove {From = 0, Kill = 2, To = 5},

                new TeeMove {From = 1, Kill = 3, To = 6},
                new TeeMove {From = 1, Kill = 4, To = 8},

                new TeeMove {From = 2, Kill = 4, To = 7},
                new TeeMove {From = 2, Kill = 5, To = 9},

                new TeeMove {From = 3, Kill = 1, To = 0},
                new TeeMove {From = 3, Kill = 4, To = 5},
                new TeeMove {From = 3, Kill = 6, To = 10},
                new TeeMove {From = 3, Kill = 7, To = 12},

                new TeeMove {From = 4, Kill = 7, To = 11},
                new TeeMove {From = 4, Kill = 8, To = 14},

                new TeeMove {From = 5, Kill = 2, To = 0},
                new TeeMove {From = 5, Kill = 4, To = 3},
                new TeeMove {From = 5, Kill = 8, To = 12},
                new TeeMove {From = 5, Kill = 9, To = 14},

                new TeeMove {From = 6, Kill = 3, To = 1},
                new TeeMove {From = 6, Kill = 7, To = 8},

                new TeeMove {From = 7, Kill = 4, To = 2},
                new TeeMove {From = 7, Kill = 8, To = 9},

                new TeeMove {From = 8, Kill = 4, To = 1},
                new TeeMove {From = 8, Kill = 7, To = 6},

                new TeeMove {From = 9, Kill = 5, To = 2},
                new TeeMove {From = 9, Kill = 8, To = 7},

                new TeeMove {From = 10, Kill = 6, To = 3},
                new TeeMove {From = 10, Kill = 11, To = 12},

                new TeeMove {From = 11, Kill = 7, To = 4},
                new TeeMove {From = 11, Kill = 12, To = 13},

                new TeeMove {From = 12, Kill = 7, To = 3},
                new TeeMove {From = 12, Kill = 8, To = 5},
                new TeeMove {From = 12, Kill = 11, To = 10},
                new TeeMove {From = 12, Kill = 13, To = 14},

                new TeeMove {From = 13, Kill = 8, To = 4},
                new TeeMove {From = 13, Kill = 12, To = 11},

                new TeeMove {From = 14, Kill = 9, To = 5},
                new TeeMove {From = 14, Kill = 13, To = 12}
            };
        }
    }
}