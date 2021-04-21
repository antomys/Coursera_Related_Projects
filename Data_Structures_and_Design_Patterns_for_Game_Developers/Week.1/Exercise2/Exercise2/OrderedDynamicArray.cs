using System;

namespace Exercise2
{
    /// <remarks>
    /// An ordered DynamicArray
    /// </remarks>
    public class OrderedDynamicArray<T> : DynamicArray<T> where T:IComparable
    {
        /// <summary>
        /// Adds the given item to the IntDynamicArray
        /// </summary>
        /// <param name="item">the item to add</param>
        public override void Add(T item)
        {
            // expand array if necessary
            if (Count == Items.Length)
            {
                Expand();
            }

            // find location at which to add the item
            var addLocation = 0;
            while ((addLocation < Count) &&
                (Items[addLocation].CompareTo(item) < 0))
            {
                addLocation++;
            }

            // shift array, add new item and increment Count
            ShiftUp(addLocation);
            Items[addLocation] = item;
            Count++;
        }

        /// <summary>
        /// Removes the first occurence of the given item from the IntDynamicArray
        /// </summary>
        /// <param name="item">the item to remove</param>
        /// <returns>true if the item is successfully removed, false otherwise</returns>
        public override bool Remove(T item)
        {
            // check for given item in array
            var itemLocation = IndexOf(item);
            if (itemLocation == -1)
            {
                return false;
            }

            // shift all the elements above the removed one down and change Count
            ShiftDown(itemLocation + 1);
            Count--;
            return true;
        }

        /// <summary>
        /// Determines the index of the given item using binary search
        /// </summary>
        /// <param name="item">the item to find</param>
        /// <returns>the index of the item or -1 if it's not found</returns>
        public override int IndexOf(T item)
        {
            var lowerBound = 0;
            var upperBound = Count - 1;
            var location = -1;

            // second part of Boolean expression added from defect discussed in reading
            // loop until found value or exhausted array
            while ((location == -1) &&
                (lowerBound <= upperBound))
            {
                // find the middle
                var middleLocation = lowerBound + (upperBound - lowerBound) / 2;
                var middleValue = Items[middleLocation];

                switch (middleValue.CompareTo(item))
                {
                    // check for match
                    case 0:
                        location = middleLocation;
                        break;
                    // split data set to search appropriate side
                    case > 0:
                        upperBound = middleLocation - 1;
                        break;
                    default:
                        lowerBound = middleLocation + 1;
                        break;
                }
            }
            return location;
        }
        
        /// <summary>
        /// Shifts all the array elements from the given index to the end of the array up one space
        /// </summary>
        /// <param name="index">the index at which to start shifting up</param>
        private void ShiftUp(int index)
        {
            for (var i = Count; i > index; i--)
            {
                Items[i] = Items[i - 1];
            }
        }

        /// <summary>
        /// Shifts all the array elements from the given index to the end of the array down one space
        /// </summary>
        /// <param name="index">the index at which to start shifting down</param>
        private void ShiftDown(int index)
        {
            for (var i = index; i < Count; i++)
            {
                Items[i - 1] = Items[i];
            }
        }

    }
}
