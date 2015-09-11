using System.Collections.Generic;

namespace DevPuzzle
{
    public class BubbleSort
    {
        public List<T> Sort<T>(List<T> intList)
        {
            var list = intList.Count;

            for ( var j = 0; j < list; j++)
            {
                for (var i = 0; i < list - 1; i++)
                {
                    if (Comparer<T>.Default.Compare(intList[i], intList[i + 1]) < 0) continue;
                    Swap(intList, i);
                }
            }
            return intList;
        }

        private static void Swap<T>(IList<T> intList, int i)
        {
            var h = intList[i + 1];
            intList[i + 1] = intList[i];
            intList[i] = h;
        }
    }
}