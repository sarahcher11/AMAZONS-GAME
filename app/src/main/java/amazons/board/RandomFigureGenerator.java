package amazons.board;

import amazons.figures.MovableFigure;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class RandomFigureGenerator {


    private Random random;
   private List<MovableFigure> figures;
    private  Iterator<Position> positionIterator;


    public RandomFigureGenerator(Random random, List<MovableFigure> figures, Iterator<Position> positionIterator)
    {
         this.random=random;
         this.figures=figures;
         this.positionIterator=positionIterator;
    }
}
