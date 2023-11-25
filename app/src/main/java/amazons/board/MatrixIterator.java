package amazons.board;

import java.util.Iterator;

public class MatrixIterator <T> implements Iterator<T>{


    private final int numberOfRows;
    private final int numberOfColumns;
    private final T[][] matrix;

    private int currentRow;
    private int currentColumn;


    public MatrixIterator(int numberOfColumns, int numberOfRows, T[][] matrix)
    {
        this.numberOfRows=numberOfRows;
        this.numberOfColumns=numberOfColumns;
        this.matrix=matrix;
        this.currentColumn=0;
        this.currentRow=0;
    }



    public boolean hasNext()
    {
        return currentRow < numberOfRows && currentColumn < numberOfColumns;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public T next() {
        return null;
    }
}
