using System;
using System.Collections;

namespace DevPuzzle.Tests.Tools
{
    public class Ass
    {
        public static void Equal(IList control, IList examinated)
        {
            for (var i = 0; i < control.Count; i++)
            {
                if (!control[i].Equals(examinated[i]))
                {
                    throw new InvalidOperationException($"Element with index={i} of {examinated} is different.");
                }
            }
        }
    }
}
