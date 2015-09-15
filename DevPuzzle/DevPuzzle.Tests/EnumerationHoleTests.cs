using System.Linq;
using DevPuzzle.Tests.Tools;
using NUnit.Framework;

namespace DevPuzzle.Tests
{
    [TestFixture]
    public class EnumerationHoleTests
    {
        private BubbleSort _sort;
        private EnumerationHoleFiller _filler;

        [SetUp]
        public void SetUp()
        {
            _sort = new BubbleSort();
            _filler = new EnumerationHoleFiller();
        }

        [Test]
        public void Absent_elements_should_be_in_result()
        {
            var source = new[] {6, 10, 5, 1 };

            var sortedSource = _sort.Sort(source.ToList()).ToArray();
            var filledSource = _filler.Fill(sortedSource);

            Ass.Equal(new[] { "2-4", "7-9" }, filledSource);
        }
    }
}