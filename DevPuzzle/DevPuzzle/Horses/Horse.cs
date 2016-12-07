using System.Collections.Generic;
using System.Linq;
using DevPuzzle.Core;

namespace DevPuzzle.Horses
{
    public struct Horse
    {
        public HorseColor Color { get; set; }

        public Coordinate Coord { get; set; }

        public IEnumerable<Coordinate> GetAllPossibleMoves(Size size)
        {
            return getAllPossibleMoves(Coord).Where(x => x.X > -1 && x.Y > -1 && x.X < size.Width && x.Y < size.Height);
        }

        private List<Coordinate> getAllPossibleMoves(Coordinate coord)
        {
            var boards = new List<Coordinate>
            {
                new Coordinate(coord.X + 2, coord.Y + 1),
                new Coordinate(coord.X + 2, coord.Y - 1),

                new Coordinate(coord.X - 2, coord.Y + 1),
                new Coordinate(coord.X - 2, coord.Y - 1),

                new Coordinate(coord.X + 1, coord.Y + 2),
                new Coordinate(coord.X + 1, coord.Y - 2),

                new Coordinate(coord.X - 1, coord.Y + 2),
                new Coordinate(coord.X - 1, coord.Y - 2)
            };

            return boards;
        }
    }
}