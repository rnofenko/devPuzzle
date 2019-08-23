using System.Collections.Generic;

namespace DevPuzzle.NumbersInString
{
    public interface INumberFinder
    {
        MatchNumberInLineResult Find(string line, List<string> numbers);
    }
}