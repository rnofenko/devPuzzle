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
            var result = _calculator.Calc(0);
            Assert.AreEqual(1, result);

            result = _calculator.Calc(1);
            Assert.AreEqual(1, result);
        }

        [Test]
        public void Factorial_should_return_2_when_it_is_2()
        {
            var result = _calculator.Calc(2);
            Assert.AreEqual(2, result);
        }

        [Test]
        public void Factorial_should_return_6_when_it_is_3()
        {
            var result = _calculator.Calc(3);
            Assert.AreEqual(6, result);
        }

        [Test]
        public void Factorial_should_return_24_when_it_is_4()
        {
            var result = _calculator.Calc(4);
            Assert.AreEqual(24, result);
        }

        [Test]
        public void Factorial_should_return_120_when_it_is_5()
        {
            var result = _calculator.Calc(5);
            Assert.AreEqual(120, result);
        }
    }
}