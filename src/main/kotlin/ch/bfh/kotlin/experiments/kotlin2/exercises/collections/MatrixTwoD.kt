package ch.bfh.kotlin.experiments.kotlin2.exercises.collections

/**
 * Defines a rudimentary generic 2-dimensional matrix based on a 2-dimentional array.
 *
 * Note that using arrays should be avoided especially with genericity due to some weakness of Java.
 * Consult Effective Java by Joshua Bloch for more details.
 *
 * Using arrays in this exercise allow us to focus on the iterator pattern.
 *
 * @author Olivier Biberstein
 */
class MatrixTwoD<E>(array: Array<Array<E>>) : Iterable<E> {

    val rows: Int = array.size
    val columns: Int = if (array.isNotEmpty()) array[array.lastIndex].size else 0
    private val mat: Array<Array<E>> = array
    override fun iterator(): Iterator<E> = MatrixTwoDIterator()

    /**
     * @param r row index
     * @param c column index
     * @return the item at position r,c (row, column)
     */
    fun getItem(r: Int, c: Int): E {
        return mat[r][c]
    }

    /**
     * Sets the item at position r,c (row, colum).
     *
     * @param r    row index
     * @param c    column index
     * @param item to be defined at position r,c
     */
    fun setItem(r: Int, c: Int, item: E) {
        mat[r][c] = item
    }

    /**
     * Iterator that iterates the elements of the generic 2-dimentional matrix.
     * The order starts horizontally and then vertically.
     *
     * @param <E>
    </E> */
    private inner class MatrixTwoDIterator<E>
    /**
     * Creates a matrix iterator and initializes its states (position of the first element).
     */
        : MutableIterator<E> {
        /**
         * State of the iterator that corresponds to the horizontal and vertical indexes.
         */
        private var row = 0
        private var column = 0

        /**
         * {@inheritDoc}
         *
         * Determines if all the elements of the structure have been consulted or not.
         * According to the behavior of the iterator this situation occurs when
         * the row index is greater or equal than the number of rows.
         *
         * @return false if all the elements have been consulted otherwise true
         */
        override fun hasNext(): Boolean {
            return row < rows
        }

        /**
         * {@inheritDoc}
         *
         * @return the next element available in the structure.  The row/column indexes are incremented according to
         * the behavior of the iterator.
         * @throws NoSuchElementException if no element is available
         */
        override fun next(): E {
            if (!hasNext()) {
                throw NoSuchElementException("next(), no more element")
            }
            val item = getItem(row, column) as E
            column++
            if (column >= columns) {
                column = 0
                row++
            }
            return item
        }

        /**
         * {@inheritDoc}
         *
         * @throws NoSuchElementException if no element is available
         */
        override fun remove() {
            throw UnsupportedOperationException("remove operation not implemented")
        }
    }
}

inline fun <reified E> createMatrix(rows: Int, columns: Int, init: (Int, Int) -> E) =
    Array(rows) { Array(columns) { it2 -> init(it, it2) } }