namespace DevPuzzle.Tiles
{
    public struct Coord
    {
        public int X;
        public int Y;

        public void GoNext(int step, int width)
        {
            X += step;
            if (X >= width)
            {
                X = 0;
                Y++;
            }
        }
    }
}