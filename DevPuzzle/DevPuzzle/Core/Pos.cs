namespace DevPuzzle.Core
{
    public struct Pos
    {
        public int X;
        public int Y;

        public bool Equals(Pos other)
        {
            return X == other.X && Y == other.Y;
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            return obj is Pos && Equals((Pos) obj);
        }

        public override int GetHashCode()
        {
            unchecked
            {
                return (X*397) ^ Y;
            }
        }

        public static bool operator ==(Pos lhs, Pos rhs)
        {
            return lhs.Equals(rhs);
        }

        public static bool operator !=(Pos lhs, Pos rhs)
        {
            return !lhs.Equals(rhs);
        }
        
        public Pos(int x, int y)
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