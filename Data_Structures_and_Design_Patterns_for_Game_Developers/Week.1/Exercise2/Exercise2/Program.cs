namespace Exercise2
{
    /// <summary>
    /// Exercise 2 solution
    /// </summary>
    internal static class MainClass
    {
        /// <summary>
        /// Uses ordered generic dynamic array
        /// </summary>
        public static void Main()
        {
            var array = new OrderedDynamicArray<Rectangle>();
            for (var i = 0.1f; i <= 100; i++)
                array.Add(new Rectangle(i, i));
            
            array.Print();
        }
    }
}
