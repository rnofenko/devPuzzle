namespace DevPuzzle.Core
{
    public struct Coordinate
    {
        public int X;
        public int Y;

        public bool Equals(Coordinate other)
        {
            return X == other.X && Y == other.Y;
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            return obj is Coordinate && Equals((Coordinate) obj);
        }

        public override int GetHashCode()
        {
            unchecked
            {
                return (X*397) ^ Y;
            }
        }

        public static bool operator ==(Coordinate lhs, Coordinate rhs)
        {
            return lhs.Equals(rhs);
        }

        public static bool operator !=(Coordinate lhs, Coordinate rhs)
        {
            return !lhs.Equals(rhs);
        }
        
        public Coordinate(int x, int y)
        {
            X = x;
            Y = y;
        }

        public void GoNext(int step, int width)
        {
            X += step;
            if (X >= width)
            {
                X = 0;
                Y++;
            }
        }
        
        public int CoordinateAsOneNumber(int width)
        {
            return Y*width + X;
        }

        public override string ToString()
        {
            return $"{X}x{Y}";
        }
    }
}