using System.Collections.Generic;
using System.Linq;

namespace DevPuzzle.NumbersInString
{
    public class NumberIteratorFinder : INumberFinder
    {
        private readonly FindNumberOptions _options;

        public NumberIteratorFinder(FindNumberOptions options)
        {
            _options = options;
        }

        public MatchNumberInLineResult Find(string line, List<string> numbers)
        {
            var width = numbers.First().Length;

            var res = findComplete(line, numbers);
            if (res.Found)
            {
                return res;
            }

            while (width > 1)
            {
                width--;
                res = findPartially(line, numbers, width);
                if (res.Found)
                {
                    return res;
                }
            }

            var number = numbers.First();
            numbers.Remove(number);
            return new MatchNumberInLineResult { Found = true, NewLine = line + number, Size = 0 };
        }

        private MatchNumberInLineResult findPartially(string line, List<string> numbers, int matchNumber)
        {
            if (_options.AddToTheEnd)
            {
                var back = findBack(line, numbers, matchNumber);
                if (back.Found)
                {
                    return back;
                }
            }

            if (_options.AddToTheBegining)
            {
                var front = findFront(line, numbers, matchNumber);
                if (front.Found)
                {
                    return front;
                }
            }

            return new MatchNumberInLineResult();
        }

        private MatchNumberInLineResult findFront(string line, List<string> numbers, int matchNumber)
        {
            foreach (var number in numbers)
            {
                var skipLength = number.Length - matchNumber;
                var back = number.Substring(skipLength, matchNumber);
                if (line.StartsWith(back))
                {
                    numbers.Remove(number);
                    return new MatchNumberInLineResult { Found = true, NewLine = number.Substring(0, skipLength) + line, Size = matchNumber };
                }
            }

            return new MatchNumberInLineResult();
        }

        private MatchNumberInLineResult findComplete(string line, List<string> numbers)
        {
            var number = numbers.FirstOrDefault(line.Contains);
            if (number == null)
            {
                return new MatchNumberInLineResult();
            }
            numbers.Remove(number);
            return new MatchNumberInLineResult { Found = true, NewLine = line, Size = number.Length };
        }

        private MatchNumberInLineResult findBack(string line, List<string> numbers, int matchNumber)
        {
            foreach (var number in numbers)
            {
                var front = number.Substring(0, matchNumber);
                if (line.EndsWith(front))
                {
                    numbers.Remove(number);
                    return new MatchNumberInLineResult { Found = true, NewLine = line + number.Substring(matchNumber), Size = matchNumber };
                }
            }

            return new MatchNumberInLineResult();
        }
    }
}