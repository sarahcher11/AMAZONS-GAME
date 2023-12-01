package amazons.board;

import amazons.figures.*;
import amazons.figures.IllegalMoveException;
import amazons.player.Move;
import amazons.player.PlayerID;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MatrixBoard implements Board{


    private int numberOfRows;
    private int numberOfColumns;
    private Figure[][] plateau;

    public MatrixBoard(int numberOfColumns,int numberOfRows){
         plateau=new Figure[numberOfColumns][numberOfRows];
         this.numberOfColumns=numberOfColumns;
         this.numberOfRows=numberOfRows;
         fill(new EmptyFigureGenerator());

    }


    public void afficherBord()
    {
        for(int i=0;i<numberOfColumns;i++){
            for(int j=0;j<numberOfRows;j++){
                System.out.println("( "+i+" , "+ j+" ");
                System.out.println(plateau[i][j].getClass());

            }
        }
    }
    /**
     * Place the given figure {@code figure} at the position {@code position}. Nothing happens if {@code position}
     * is outside this board.
     *
     * @param position : where the figure has to be positioned
     * @param figure   : the figure to be placed at position {@code position}
     */
    @Override
    public void setFigure(Position position, Figure figure) {

        plateau[position.getX() ][position.getY()]=figure;

    }

    /**
     * Returns the figure at the specified position in the board.
     *
     * @param position : position of the figure to return
     * @return : the figure at the specified position in the board
     */
    @Override
    public Figure getFigure(Position position) {
        return plateau[position.getX()][position.getY()];
    }

    /**
     * Return {@code true} if the specified position is empty (i.e., contains EMPTY_FIGURE)
     *
     * @param position : position to test
     * @return {@code true} if the specified position is empty in the board.
     */
    @Override
    public boolean isEmpty(Position position) {

        return getFigure(position) instanceof EmptyFigure;
    }

    /**
     * Return {@code true} if the specified position is out of the board.
     *
     * @param position : position to test
     * @return {@code true} if the specified position is out of the board
     */
    @Override
    public boolean isOutOfBoard(Position position) {

        if(position.isOutOfBounds(numberOfColumns,numberOfRows))
            return true;
        return false;
    }

    public void moveFigure(Position source,Position destination) throws IllegalMoveException {
        if(getFigure(source) instanceof Amazon)
        {
            Amazon amazon= (Amazon) getFigure(source);
            if( amazon.canMoveTo(destination,this))
            {      setFigure(destination,amazon);
                   setFigure(source,EmptyFigure.EMPTY_FIGURE);
            }
            else
            {
                throw new IllegalMoveException("Impossible de se deplacer");
            }
        }
        else
        {
            throw new IllegalMoveException(" move impossible");
        }

    }

    /**
     * Place an arrow at  {@code dstPosition}. The arrow originates from {@code startPosition}
     *
     * @param startPosition    : the origin of the arrow shot
     * @param arrowDstPosition : the destination of the arrow
     * @throws IllegalMoveException: if {@code dstPosition} is not empty occupied or there is no amazon
     *                               at position {@code startPosition} or it is not legal for the amazon to shoot an arrow to {@code dstPosition}
     */
    @Override
    public void shootArrow(Position startPosition, Position arrowDstPosition) throws IllegalMoveException {
        if(getFigure(startPosition) instanceof Amazon)
        {
            Amazon amazon= (Amazon) getFigure(startPosition);
            if( amazon.canMoveTo(arrowDstPosition,this))
            {
                setFigure(arrowDstPosition,ArrowFigure.ARROW_FIGURE);
            }
            else {
                throw new IllegalMoveException("Impossible de se deplacer");
            }
        }
        else
        {
            throw new IllegalMoveException(" move impossible");
        }
    }

    @Override
    public void fill(FigureGenerator generator) {
        Position position;
        for(int i=0;i<numberOfColumns;i++){
            for(int j=0;j<numberOfRows;j++){
                position=new Position(i,j);
                plateau[i][j]=generator.nextFigure(position);

            }
        }
    }

    @Override
    public Iterator<Position> positionIterator() {
        return new PositionIterator(numberOfColumns,numberOfRows);
    }


    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }
    public Iterator<Figure> iterator(){
        return new MatrixIterator<>(numberOfColumns,numberOfRows,plateau);
    }

    @Override
    public String toString() {
        String result = "";
        int numJ=-1;

        // Upper border
        result += "+";
        for (int col = 0; col < numberOfColumns; col++) {
            result += "----+";
        }
        result += "\n";

        // Board content
        for (int row = 0; row < numberOfRows; row++) {
            result += "| ";
            for (int col = 0; col < numberOfColumns; col++) {
                Figure figure = getFigure(new Position(col,row));
                if (figure instanceof Amazon) {
                    Amazon amazon = (Amazon) figure;
                    if (amazon.getPlayerID()== PlayerID.PLAYER_ZERO)
                    {
                       numJ=0;
                    }
                    if (amazon.getPlayerID()==PlayerID.PLAYER_ONE)
                    {
                        numJ=1;
                    }
                    result += "A" + String.valueOf(numJ) + " | ";
                } else if (figure instanceof ArrowFigure) {
                    result += "XX | ";
                } else {
                    result += "   | ";
                }
            }
            result += row + "\n";

            // Inner borders
            result += "+";
            for (int col = 0; col < numberOfColumns; col++) {
                result += "----+";
            }
            result += "\n";
        }

        // Column numbers
        result += "  ";
        for (int col = 0; col < numberOfColumns; col++) {
            result += col + "    ";
        }
        result += "     ";
        result = result.trim(); // Supprimer les espaces à la fin de la chaîne
        result +="  ";
        return result;
    }
    /**
     * Obtient tous les mouvements possibles pour un joueur donné.
     *
     * @param playerID : l'ID du joueur pour lequel générer les mouvements
     * @return une liste de tous les mouvements possibles pour le joueur
     */
    public List<Move> getAllPossibleMoves(PlayerID playerID) {
        List<Move> possibleMoves = new ArrayList<>();

        for (int row = 0; row < getNumberOfRows(); row++) {
            for (int col = 0; col < getNumberOfColumns(); col++) {
                Position amazonPosition = new Position(col, row);
                Figure figure = getFigure(amazonPosition);

                if (figure instanceof Amazon && figure.getPlayerID().equals(playerID)) {
                    // Pour chaque amazone du joueur, ajoutez tous les mouvements possibles à la liste
                    List<Position> accessiblePositions = ((Amazon) figure).getAccessiblePositions(this);

                    for (Position destination : accessiblePositions) {
                        // Pour chaque position accessible, ajoutez un mouvement à la liste
                        possibleMoves.add(new Move(amazonPosition, destination, null));
                    }
                }
            }
        }

        return possibleMoves;
    }





}
