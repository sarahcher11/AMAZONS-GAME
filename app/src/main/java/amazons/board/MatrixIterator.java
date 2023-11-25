package amazons.board;

import java.util.Iterator;

public class MatrixIterator <T> implements Iterable<T>{


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

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
