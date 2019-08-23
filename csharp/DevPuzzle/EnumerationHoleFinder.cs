using System;
using System.Collections.Generic;
using System.Linq;

namespace DevPuzzle
{
    public class EnumerationHoleFinder
    {
        public string[] Find(int[] sortedSource)
        {
            var foundHoles = new string[0];
            
            var counter = 0;
            

            for (var i = 0; i < sortedSource.Length; i++)
            {
                var result = new List<int>();
                var tempStr = string.Empty;

                if (i >= sortedSource.Length - 1) continue;

                if (sortedSource[i + 1] - sortedSource[i] <= 1) continue;

                var holeSize = (sortedSource[i + 1] - sortedSource[i]) - 1;

                result.Add(sortedSource[i] + 1);

                if (holeSize > 1)
                {
                    result.Add(sortedSource[i] + holeSize);
                }

                tempStr = result.Aggregate(tempStr, (current, t) => current + $"{t}-");
                Array.Resize(ref foundHoles, foundHoles.Length + 1);

                foundHoles[counter] = tempStr.Substring(0, tempStr.Length - 1);
                counter++;
            }

            return foundHoles;
        }
    }
}