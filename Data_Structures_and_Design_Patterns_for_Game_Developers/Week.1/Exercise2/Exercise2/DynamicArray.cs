using System.Text;

namespace Exercise2
{
    /// <remarks>
    /// Provides a dynamically-sized array of a data type
    /// </remarks>
    public abstract class DynamicArray<T>
    {
        private const int ExpandMultiplyFactor = 2;
        protected T[] Items;
        protected int count;
        

        /// <summary>
        /// Constructor
        /// </summary>
        protected DynamicArray()
        {
            Items = new T[4];
            count = 0;
        }
        
        /// <summary>
        /// Gets the number of elements
        /// </summary>
        public int Count => count;
        
        public abstract void Add(T item);
        public abstract bool Remove(T item);
        public abstract int IndexOf(T item);

        /// <summary>
        /// Removes all the items from the DynamicArray
        /// </summary>
        public void Clear()
        {
            count = 0;
        }

        /// <summary>
        /// Converts the DynamicArray to a comma-separated string of values
        /// </summary>
        /// <returns>the comma-separated string of values</returns>
        public override string ToString()
        {
            var builder = new StringBuilder();
            for (var i = 0; i < count; i++)
            {
                builder.Append(Items[i]);
                if (i < count - 1)
                {
                    builder.Append(',');
                }
            }
            return builder.ToString();
        }
        
        /// <summary>
        /// Expands the array
        /// </summary>
        protected void Expand()
        {
            var newItems = new T[Items.Length * ExpandMultiplyFactor];

            // copy elements from old array into new array
            for (var i = 0; i < Items.Length; i++)
            {
                newItems[i] = Items[i];
            }

            // change to use new array
            Items = newItems;
        }
    }
}