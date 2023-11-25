package amazons.board;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PositionIterator implements Iterator<Position> {



    private final int numberOfRows;
    private final int numberOfColumns;
    private int currentRow;
    private int currentColumn;

    public PositionIterator(int numberOfColumns,int numberOfRows)
    {
        this.numberOfRows=numberOfRows;
        this.numberOfColumns=numberOfColumns;
        currentRow=0;
        currentColumn=0;
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        return false;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public Position next() {
        return null;
    }
}
