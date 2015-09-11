using DevPuzzle.Tests.Tools;
using NUnit.Framework;

namespace DevPuzzle.Tests
{
    [TestFixture]
    public class EnumerationHoleTests
    {
        [Test]
        public void Absent_elements_should_be_in_result()
        {
            var source = new[] {6, 10, 4, 5, 1, 3};

            Ass.Equal(new[] {"2", "7-9"}, new[] {""});
        }
    }
}