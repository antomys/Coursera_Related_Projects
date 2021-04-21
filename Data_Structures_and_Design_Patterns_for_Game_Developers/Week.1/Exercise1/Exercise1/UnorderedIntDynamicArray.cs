namespace Exercise1
{
    /// <remarks>
    /// An unordered IntDynamicArray
    /// </remarks>
    public class UnorderedIntDynamicArray : IntDynamicArray
    {
        #region Public methods
        
        public override void Add(int item)
        {
            // expand array if necessary
            if (Count == Items.Length)
            {
                Expand();
            }

            // add new item and increment count
            Items[Count] = item;
            Count++;
        }
        
        public override bool Remove(int item)
        {
            // check for given item in array
            var itemLocation = IndexOf(item);
            if (itemLocation == -1)
            {
                return false;
            }
            else
            {
                // move last element in array here and change count
                Items[itemLocation] = Items[Count - 1];
                Count--;
                return true;
            }
        }
        
        public override int IndexOf(int item)
        {
            // look for first occurrence of item in array
            for (var i = 0; i < Count; i++)
            {
                if (Items[i] == item)
                {
                    return i;
                }
            }

            // didn't find the item in the array
            return -1;
        }
        
        public int LastIndexOf(int item)
        {
            // look for first occurrence of item in array
            for (var i = Count; i >= 0; i--)
            {
                if (Items[i] == item)
                {
                    return i;
                }
            }

            // didn't find the item in the array
            return -1;
        }

        public int[] AllIndexOf(int item)
        {
            if (Count == 0)
                return System.Array.Empty<int>();
            //creating an array
            var itemIndexes = new int[CountOccurence(item)];
            var currIndex = 0;
            for (var i = 0; i < Count; i++)
            {
                if (Items[i] != item) continue;
                itemIndexes[currIndex] = i;
                currIndex++;
            }

            return itemIndexes;
        }
        
        #endregion
        private int CountOccurence(int item)
        {
            var count = 0;
            for (var i = 0; i < Count; i++)
            {
                if (Items[i] == item)
                    count++;
            }
            return count;
        }
        

    }
}
