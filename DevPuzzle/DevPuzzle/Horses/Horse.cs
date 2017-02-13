using System.Collections.Generic;
using System.Linq;
using DevPuzzle.Core;

namespace DevPuzzle.Horses
{
    public struct Horse
    {
        public HorseColor Color { get; set; }

        public Pos Coord { get; set; }

        public IEnumerable<Pos> GetAllPossibleMoves(Size size)
        {
            return getAllPossibleMoves(Coord).Where(x => x.X > -1 && x.Y > -1 && x.X < size.Width && x.Y < size.Height);
        }

        private List<Pos> getAllPossibleMoves(Pos coord)
        {
            var boards = new List<Pos>
            {
                new Pos(coord.X + 2, coord.Y + 1),
                new Pos(coord.X + 2, coord.Y - 1),

                new Pos(coord.X - 2, coord.Y + 1),
                new Pos(coord.X - 2, coord.Y - 1),

                new Pos(coord.X + 1, coord.Y + 2),
                new Pos(coord.X + 1, coord.Y - 2),

                new Pos(coord.X - 1, coord.Y + 2),
                new Pos(coord.X - 1, coord.Y - 2)
            };

            return boards;
        }
    }
}