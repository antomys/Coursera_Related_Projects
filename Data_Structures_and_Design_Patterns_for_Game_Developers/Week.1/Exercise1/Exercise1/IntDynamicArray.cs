using System.Text;

namespace Exercise1
{
    /// <remarks>
    /// Provides a dynamically-sized array of integers
    /// </remarks>
    public abstract class IntDynamicArray
    {
        private const int ExpandMultiplyFactor = 2;
        protected int[] Items;

        #region Constructor

        /// <summary>
        /// Constructor
        /// </summary>
        protected IntDynamicArray()
        {
            Items = new int[4];
            Count = 0;
        }

        #endregion

        #region Properties

        /// <summary>
        /// Gets the number of elements
        /// </summary>
        protected int Count { get; set; }

        #endregion

        #region Public methods

        /// <summary>
        /// Adds the given item to the IntDynamicArray
        /// </summary>
        /// <param name="item">the item to add</param>
        public abstract void Add(int item);
        /// <summary>
        /// Removes the first occurence of the given item from the 
        /// IntDynamicArray
        /// </summary>
        /// <param name="item">the item to remove</param>
        /// <returns>true if the item is successfully removed, false
        ///  otherwise</returns>
        public abstract bool Remove(int item);
        /// <summary>
        /// Determines the index of the given item
        /// </summary>
        /// <param name="item">the item to find</param>
        /// <returns>the index of the item or -1 if it's not found</returns>
        public abstract int IndexOf(int item);
        
        /// <summary>
        /// Removes all the items from the IntDynamicArray
        /// </summary>
        public void Clear()
        {
            Count = 0;
        }

        /// <summary>
        /// Converts the IntDynamicArray to a comma-separated string of
        /// values
        /// </summary>
        /// <returns>the comma-separated string of values</returns>
        public override string ToString()
        {
            var builder = new StringBuilder();
            for (var i = 0; i < Count; i++)
            {
                builder.Append(Items[i]);
                if (i < Count - 1)
                {
                    builder.Append(',');
                }
            }
            return builder.ToString();
        }

        #endregion

        #region Protected methods

        /// <summary>
        /// Expands the array
        /// </summary>
        protected void Expand()
        {
            var newItems = new int[Items.Length * ExpandMultiplyFactor];

            // copy elements from old array into new array
            for (var i = 0; i < Items.Length; i++)
            {
                newItems[i] = Items[i];
            }

            // change to use new array
            Items = newItems;
        }
        
        #endregion
    }
}
