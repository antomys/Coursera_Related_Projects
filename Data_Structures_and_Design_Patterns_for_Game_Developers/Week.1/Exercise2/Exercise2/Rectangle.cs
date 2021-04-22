using System;

namespace Exercise2
{
    /// <summary>
    /// 
    /// </summary>
    public class Rectangle : IComparable
    {
        /// <summary>
        /// 
        /// </summary>
        private const float Tolerance = 0.0001f;
        /// <summary>
        /// 
        /// </summary>
        private float Width { get;}
        private float Height { get;}
        /// <summary>
        /// 
        /// </summary>
        /// <param name="width"></param>
        /// <param name="height"></param>

        public Rectangle(float width, float height)
        {
            Width = width > 0 ? width : throw new ArgumentNullException(nameof(width));
            Height = height > 0 ? height : throw new ArgumentNullException(nameof(height));
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="obj"></param>
        /// <returns></returns>
        /// <exception cref="ArgumentNullException"></exception>
        public int CompareTo(object obj)
        {
            if (!(obj is Rectangle rectangle))
                throw new ArgumentNullException(nameof(obj));

            if ((rectangle.Height >=Height  && rectangle.Width > Width) || (rectangle.Height > Height && rectangle.Width >= Width))
                return 1;
            if (rectangle.Height.Equals(Height) && rectangle.Width.Equals(Width))
                return 0;
            return -1;
        }

        public override string ToString()
        {
            return $"{Width};{Height}";
        }
    }
}