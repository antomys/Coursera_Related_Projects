using System;
using System.Collections.Generic;
using System.Runtime.CompilerServices;

namespace Exercise1
{
    /// <summary>
    /// Exercise 1 solution
    /// </summary>
    internal static class MainClass
    {
        private const int FirstNumber = 1;

        /// <summary>
        /// Tests LastIndexOf and AllIndexesOf methods
        /// </summary>
        private static void Main()
        {
            
            // build test dynamic array
            var testArray = new UnorderedIntDynamicArray();
            testArray.Add(FirstNumber);
            
            // test LastIndexOf with one item in dynamic array
            Console.WriteLine(testArray.LastIndexOf(FirstNumber));
            testArray.PopulateArray();
            
            // test LastIndexOf with multiple items in dynamic array
            Console.WriteLine(testArray.LastIndexOf(FirstNumber));
            
            // test LastIndexOf with item not in dynamic array
            testArray = new UnorderedIntDynamicArray();
            Console.WriteLine(testArray.LastIndexOf(FirstNumber));
            
            // test AllIndexesOf with one item in dynamic array
            testArray.Add(1);
            var indexes = testArray.AllIndexOf(FirstNumber);
            indexes.ShowIntArray();
            
            // test AllIndexesOf with multiple items in dynamic array
            Array.Clear(indexes,0,indexes.Length);
            testArray.PopulateArray();
            indexes = testArray.AllIndexOf(FirstNumber);
            indexes.ShowIntArray();
            
            // test AllIndexesOf with item not in dynamic array
            Array.Clear(indexes,0,indexes.Length);
            testArray = new UnorderedIntDynamicArray();
            indexes = testArray.AllIndexOf(FirstNumber);
            indexes.ShowIntArray();

            Console.WriteLine();
        }

        private static void ShowIntArray(this IEnumerable<int> indexes)
        {
            foreach(var index in indexes)
                Console.Write($"{index} ");
            Console.Write('\n');
        }

        private static void PopulateArray(this IntDynamicArray array)
        {
            for (var i = 0; i < 100; i++)
            {
                if(i % 2 == 0) array.Add(FirstNumber);
                array.Add(i);
            }
        }
    }
}
