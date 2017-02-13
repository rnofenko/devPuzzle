using System.Linq;
using DevPuzzle.Core;

namespace DevPuzzle.Horses
{
    public class HorseBoard
    {
        private const int SIDE_SIZE = 3;
        public Size Size { get; } = new Size {Width = SIDE_SIZE, Height = SIDE_SIZE };

        public Horse[] Horses { get; private set; }

        public HorseBoard Clone()
        {
            return new HorseBoard {Horses = Horses.ToArray()};
        }
        
        public bool MoveHorse(int index, Pos coord)
        {
            if (Horses.Any(x => x.Coord == coord))
            {
                return false;
            }
            Horses[index].Coord = coord;
            return true;
        }

        public string GetKey()
        {
            var signs = Horses.OrderBy(x => x.Coord.CoordinateAsOneNumber(Size.Width)).Select(x => x.Color.ToString() + x.Coord.CoordinateAsOneNumber(Size.Width));
            return string.Join(",", signs);
        }

        public bool IsFinalState()
        {
            return Horses.All(isFinalState);
        }

        private bool isFinalState(Horse horse)
        {
            if (horse.Coord.Y == 0)
            {
                if (horse.Coord.X == 0 || horse.Coord.X == SIDE_SIZE - 1)
                {
                    if (horse.Color != HorseColor.Black)
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }
            else if (horse.Coord.Y == SIDE_SIZE - 1)
            {
                if (horse.Coord.X == 0 || horse.Coord.X == SIDE_SIZE - 1)
                {
                    if (horse.Color != HorseColor.White)
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
            return true;
        }

        public static HorseBoard CreateStartPostion()
        {
            return new HorseBoard
            {
                Horses = new[]
                {
                    new Horse {Coord = new Pos(0, 0), Color = HorseColor.White},
                    new Horse {Coord = new Pos(SIDE_SIZE - 1, 0), Color = HorseColor.White},
                    new Horse {Coord = new Pos(0, 2), Color = HorseColor.Black},
                    new Horse {Coord = new Pos(SIDE_SIZE - 1, SIDE_SIZE - 1), Color = HorseColor.Black}
                }
            };
        }
    }
}