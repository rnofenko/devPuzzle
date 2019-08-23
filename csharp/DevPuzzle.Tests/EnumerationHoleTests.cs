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
        private EnumerationHoleFinder _finder;

        [SetUp]
        public void SetUp()
        {
            _sort = new BubbleSort();
            _filler = new EnumerationHoleFiller();
            _finder = new EnumerationHoleFinder();
        }

        [Test]
        public void Absent_elements_should_be_displayed_in_result()
        {
            var source = new[] { 6, 10, 5, 1, 12 };

            var sortedSource = _sort.Sort(source.ToList()).ToArray();
            var foundResults = _finder.Find(sortedSource);

            Ass.Equal(new[] { "2-4", "7-9", "11" }, foundResults);
        }

        [Test]
        public void Absent_elements_should_be_included_in_result()
        {
            var source = new[] { 6, 10, 5, 1 };

            var sortedSource = _sort.Sort(source.ToList()).ToArray();
            var filledSource = _filler.Fill(sortedSource);

            Ass.Equal(new[] { 1, 2, 3, 4, 5, 6, 7 ,8, 9, 10 }, filledSource);
        }
    }
}