namespace DevPuzzle
{
    public class FactorialCalculator
    {
        public int Calc(int factorial)
        {
            var result = 1;
            while (factorial > 0)
            {
                result *= factorial;
                factorial--;
            }
            return result;
        }
    }
}
