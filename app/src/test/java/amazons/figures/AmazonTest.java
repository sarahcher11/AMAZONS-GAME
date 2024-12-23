package amazons.figures;

import amazons.board.Board;
// import amazons.board.EmptyFigureGenerator;
// import amazons.board.MatrixBoard;
import amazons.board.EmptyFigureGenerator;
import amazons.board.MatrixBoard;
import amazons.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

//import static amazons.figures.ArrowFigure.ARROW_FIGURE;
import static amazons.figures.ArrowFigure.ARROW_FIGURE;
import static org.assertj.core.api.Assertions.assertThat;

class AmazonTest {

    private final Position[][] allPositions = new Position[4][3];
    private final List<Position> accessiblePositions = new ArrayList<>();
    private final int NUMBER_OF_COLUMNS = 4;
    private final int NUMBER_OF_ROWS = 3;
    private final Board board = new MatrixBoard(NUMBER_OF_COLUMNS, NUMBER_OF_ROWS);
    private Amazon amazon11;

    @BeforeEach


    /*
                +----+----+----+----+
                |    |    | A1 |    | 0
                +----+----+----+----+
                |    | A0 |    |    | 1
                +----+----+----+----+
                |    | A0 | AR |    | 2
                +----+----+----+----+
                  0    1    2    3"""
    */



     void setUp(){
         for(int x=0; x<NUMBER_OF_COLUMNS; x++){
             for(int y=0; y<NUMBER_OF_ROWS; y++){
                 allPositions[x][y] = new Position(x,y);
             }
         }
         amazon11 = new Amazon(allPositions[1][1],0);
         board.fill(new EmptyFigureGenerator());
         board.setFigure(allPositions[1][1],amazon11);
         board.setFigure(allPositions[1][2], new Amazon(allPositions[1][2],0));
         board.setFigure(allPositions[2][0], new Amazon(allPositions[2][0],1));
         board.setFigure(allPositions[2][2], ARROW_FIGURE);
         accessiblePositions.clear();
         accessiblePositions.add(allPositions[0][0]);
         accessiblePositions.add(allPositions[0][1]);
         accessiblePositions.add(allPositions[0][2]);
         accessiblePositions.add(allPositions[1][0]);
         accessiblePositions.add(allPositions[2][1]);
         accessiblePositions.add(allPositions[3][1]);
     }



    @Test
    void testCanMoveTo() {
        setUp();
        for (int x = 0; x < NUMBER_OF_COLUMNS; x++) {
            for (int y = 0; y < NUMBER_OF_ROWS; y++) {
               // System.out.println("Testing position: " + x + ", " + y);
                try {
                    boolean canMoveResult = amazon11.canMoveTo(allPositions[x][y], board);
                    boolean isAccessible = accessiblePositions.contains(allPositions[x][y]);
                   // System.out.println("canMove: " + canMoveResult);
                  //  System.out.println("Accessible: " + isAccessible);
                    assertThat(canMoveResult).isEqualTo(isAccessible);
                } catch (ArrayIndexOutOfBoundsException e) {
                  //  System.err.println("Error accessing position: " + x + ", " + y);
                    e.printStackTrace();
                    throw e; // Rethrow the exception to fail the test
                }
            }
        }
    }




    @Test
    void testGetAccessiblePositions() {
         setUp();


        System.out.println("la taille de la liste est "+ amazon11.getAccessiblePositions(board).size());
       System.out.println("taille "+accessiblePositions.size());
         assertThat(amazon11.getAccessiblePositions(board))
                 .hasSameElementsAs(accessiblePositions)
                 .hasSize(accessiblePositions.size());
    }

    @Test
    void pathIsBlockedTest()
    {
        assertThat(amazon11.pathIsBlocked(allPositions[0][0],board)).isFalse();
    }

    @Test
    void isHorizentalTest()
    {
        assertThat(amazon11.isHorizental(allPositions[1][2])).isTrue();
        assertThat(amazon11.isHorizental(allPositions[2][2])).isFalse();
    }

    @Test
    void isVerticalTest()
    {
        assertThat(amazon11.isVertical(allPositions[2][2])).isFalse();
        assertThat(amazon11.isVertical(allPositions[2][1])).isTrue();
    }
    @Test
    void isOnTheSameDiagonalTest()
    {
        assertThat(amazon11.isOnTheSameDiagonal(allPositions[1][2])).isFalse();
        assertThat(amazon11.isOnTheSameDiagonal(allPositions[0][0])).isTrue();
        assertThat(amazon11.isOnTheSameDiagonal(allPositions[2][2])).isTrue();
    }


}