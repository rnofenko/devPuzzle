namespace DevPuzzle.NumbersInString
{
    public class DigitStat
    {
        public int Digit { get; }
        public int Count { get; private set; }

        public DigitStat(int digit)
        {
            Digit = digit;
        }

        public void Inc()
        {
            Count++;
        }
    }
}