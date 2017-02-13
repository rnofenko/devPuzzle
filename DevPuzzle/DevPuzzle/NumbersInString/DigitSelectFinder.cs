using System.Collections.Generic;
using System.Linq;

namespace DevPuzzle.NumbersInString
{
    public class DigitSelectFinder : INumberFinder
    {
        private List<DigitStat> _digits;

        public MatchNumberInLineResult Find(string line, List<string> numbers)
        {
            checkDigitStats(line);
            foreach (var digit in _digits.OrderBy(x => x.Count).ThenBy(x=>x.Digit))
            {
                var res = find(line, numbers, digit.Digit);
                if (res.Found)
                {
                    digit.Inc();
                    return res;
                }
            }

            foreach (var digit in _digits.OrderBy(x => x.Count).ThenBy(x => x.Digit))
            {
                var res = findSecond(line, numbers, digit.Digit);
                if (res.Found)
                {
                    digit.Inc();
                    return res;
                }
            }

            var number = numbers.First();
            numbers.Remove(number);
            return new MatchNumberInLineResult {Found = true, NewLine = line + number};
        }

        private MatchNumberInLineResult find(string line, List<string> numbers, int digit)
        {
            var number = line.Substring(line.Length - 2, 2) + digit;
            if (numbers.Contains(number))
            {
                numbers.Remove(number);
                return new MatchNumberInLineResult { Found = true, NewLine = line + digit };
            }

            return new MatchNumberInLineResult();
        }

        private MatchNumberInLineResult findSecond(string line, List<string> numbers, int digit)
        {
            var numberStart = line.Substring(line.Length - 1, 1) + digit;
            var number = numbers.FirstOrDefault(x => x.StartsWith(numberStart));
            if (number != null)
            {
                numbers.Remove(number);
                return new MatchNumberInLineResult { Found = true, NewLine = line + number.Substring(1) };
            }

            return new MatchNumberInLineResult();
        }

        private void checkDigitStats(string line)
        {
            if (_digits != null)
            {
                return;
            }
            _digits = Enumerable.Range(0, 10).Select(x => new DigitStat(x)).ToList();
            foreach (var character in line)
            {
                var index = character - 48;
                var pair = _digits.First(x => x.Digit == index);
                pair.Inc();
            }
        }
    }
}