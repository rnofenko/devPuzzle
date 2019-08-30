using NUnit.Framework;
using System;
using System.Collections.Generic;
using System.Diagnostics;

namespace DevPuzzle.Tests.Standard
{
    [TestFixture]
    public class HashCodeDictionaryTests
    {
        [Test]
        public void Test1()
        {
            var w = Stopwatch.StartNew();
            var dic = new Dictionary<Banana, int>();
            for(var i = 0; i < 10000; i++)
            {
                dic.Add(new Banana(i), i);
            }

            Console.WriteLine($"{w.Elapsed.TotalSeconds:N2}");
        }
    }

    class Apple
    {
        public int Value { get; set; }

        public Apple(int value)
        {
            Value = value;
        }

        public override bool Equals(object obj)
        {
            return obj is Apple apple &&
                   Value == apple.Value;
        }

        public override int GetHashCode()
        {
            return Value.GetHashCode();
        }
    }

    class Banana
    {
        public int Value { get; set; }

        public Banana(int value)
        {
            Value = value;
        }

        public override bool Equals(object obj)
        {
            return obj is Apple apple &&
                   Value == apple.Value;
        }

        public override int GetHashCode()
        {
            return 1;
        }
    }
}
