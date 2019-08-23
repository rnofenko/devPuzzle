using System.Collections.Generic;

namespace DevPuzzle
{
    public class EnumerationHoleFiller
    {
        public int[] Fill(int[] sortedSource)
        {
            var filledSource = sortedSource;
            var result = new List<int>();
            for (var i = 0; i < filledSource.Length; i++)
            {
                result.Add(filledSource[i]);

                if (i >= filledSource.Length - 1) continue;

                if (filledSource[i + 1] - filledSource[i] <= 1) continue;

                var holeSize = filledSource[i + 1] - filledSource[i];
                for (var j = 1; j < holeSize; j++)
                {
                    result.Add(filledSource[i] + j);
                }
            }

            return result.ToArray();
        }
    }
}