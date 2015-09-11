using System.Collections.Generic;
using System.Linq;
using NUnit.Framework;
namespace DevPuzzle.Tests
{
    [TestFixture]
    class BubbleSortTests
    {
        private BubbleSort _sort;

        [SetUp]
        public void SetUp()
        {
            _sort = new BubbleSort();
        }

        [Test]
        public void BubleSort_Should_Properly_Sort_List_Of_Integers()
        {
            var controlList = new List<int> {1, 2, 3, 4, 5, 6,7,8,9,10,11,12,13};
            var intList = new List<int> {9, 5, 2, 4, 1, 13, 6, 11, 7, 10, 8, 12, 3};
            var result = _sort.Sort(intList);

            var test = !controlList.Where((t, i) => t != result[i]).Any();

            Assert.True(test);
        }

        [Test]
        public void BubleSort_Should_Properly_Sort_List_Of_Strings()
        {
            var controlList = new List<string> { "A", "B", "C", "D", "E" };
            var strList = new List<string> { "E", "A", "C","B", "D" };
            var result = _sort.Sort(strList);

            var test = !controlList.Where((t, i) => t != result[i]).Any();

            Assert.True(test);
        }

        [Test]
        public void BubleSort_Should_Properly_Sort_List_Of_Strings_with_Numbers()
        {
            var controlList = new List<string> {"2", "3", "A", "C", "E"};
            var strList = new List<string> {"E", "A", "C", "2", "3"};
            var result = _sort.Sort(strList);

            var test = !controlList.Where((t, i) => t != result[i]).Any();

            Assert.True(test);
        }
    }
}
