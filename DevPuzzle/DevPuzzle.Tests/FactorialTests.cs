using NUnit.Framework;

namespace DevPuzzle.Tests
{
    [TestFixture]
    public class FactorialTests
    {
        private FactorialCalculator _calculator;

        [SetUp]
        public void SetUp()
        {
            _calculator = new FactorialCalculator();
        }

        [Test]
        public void Factorial_should_not_throw_exception_when_it_is_1()
        {
            _calculator.Calc(0);
        }
    }
}