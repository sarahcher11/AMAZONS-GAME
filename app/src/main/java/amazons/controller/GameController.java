package amazons.controller;

import amazons.board.Board;
import amazons.board.Position;
import amazons.game.Game;
import amazons.game.TurnPhase;
import amazons.player.*;
import amazons.viewer.BoardView;
import amazons.viewer.MenuView;
import javafx.animation.PauseTransition;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Alert;
import javafx.util.Duration;

import java.util.Random;


public class GameController {
    private final Game game;
    private static final Random random = new Random();

    private Position lastAmazonStartPosition;
    private Position lastAmazonDstPosition;
    private Position lastArrowDstPosition;
    private Move lastMove = Move.DUMMY_MOVE;

    private final SimpleObjectProperty<TurnPhase> phase = new SimpleObjectProperty<>(TurnPhase.AMAZON_PHASE);
    private static final int PAUSE_MILLISECONDS = 1500;
    private final PauseTransition pause = new PauseTransition(Duration.millis(PAUSE_MILLISECONDS));

    private final static Player[] players = new Player[Game.NUMBER_OF_PLAYERS];
    private  BoardView view;
    private MenuView menu;


    public GameController(Game game, Player player0, Player player1){
        this.game = game;
        setPlayer(player0, PlayerID.PLAYER_ZERO);
        setPlayer(player1, PlayerID.PLAYER_ONE);
        players[0]=player0;
        players[1]=player1;
        initialize();
    }


    private void initialize(){
        game.initializeGame(players[0], players[1]);
        lastMove = Move.DUMMY_MOVE;
        resetPhase();
        addEndPhaseListener();
        setPauseAnimation();
        startGame();
    }
    public void resetGame(){
       initialize();
       view.updateFields();
       menu.setStatusText("");
       startGame();
    }
    private void setPauseAnimation(){
        pause.setOnFinished(e -> setPhase(TurnPhase.END_PHASE));
    }

    private void addEndPhaseListener(){
        getPhaseProperty().addListener((observable, oldValue, newValue) ->{
            if (newValue == TurnPhase.END_PHASE) endTurn();
        });
    }


    private void endTurn(){
        if(!game.hasEnded()) {
            game.incrementTurn();
            resetPhase();
            if(!getCurrentPlayer().isGUIControlled()) playComputerTurn();
        }
        else {
            menu.setStatusText(game.getWinner() + " wins!!!");
        }
    }


    public void setPlayer(Player player, PlayerID playerID){
        player.setPlayerID(playerID);
        players[playerID.index] = player;
    }


    public void setView(BoardView view){
        this.view = view;
    }

    public void setMenu(MenuView menu){
        this.menu = menu;
    }
    public void playComputerTurn(){
        Player currentPlayer = getCurrentPlayer();
        lastMove = currentPlayer.play(lastMove);
        game.updateGame(lastMove);
        view.showMove(lastMove);
        game.incrementTurn();
        pause.play();
    }



    public void startGame(){
        if(!getCurrentPlayer().isGUIControlled()) playComputerTurn();
    }

    public Board getBoard(){
        return game.getBoard();
    }

    public PlayerID getCurrentPlayerID(){
        return game.getPlayerID();
    }

    public Player getCurrentPlayer(){
        return game.getPlayer();
    }


    public void setPhase(TurnPhase turnPhase){
        this.phase.setValue(turnPhase);
    }

    public TurnPhase getPhase(){
        return phase.getValue();
    }

    public void  resetPhase() {
        setPhase(TurnPhase.AMAZON_PHASE);
    }

    public SimpleObjectProperty<TurnPhase> getPhaseProperty(){
        return phase;
    }

    public void nextPhase(){
        setPhase(getPhase().next());
    }

    // call by the view
    public void moveFigure(Position amazonStartPosition, Position amazonDstPosition)
    {
        game.updateGameAmazonMove(amazonStartPosition, amazonDstPosition);
        lastAmazonStartPosition = amazonStartPosition;
        lastAmazonDstPosition = amazonDstPosition;
    }


    public void checkGameEnd() {
        if (game.hasEnded()) {
            String winnerID = game.getWinner().toString();
            showGameEndDialog(winnerID);
        }
    }

    // call by the view
    public void shootArrow(Position startPosition, Position arrowDstPosition){
        game.updateGameArrowShot(startPosition, arrowDstPosition);
        lastArrowDstPosition = arrowDstPosition;
       // lastMove = new Move(lastAmazonStartPosition, lastAmazonDstPosition, lastArrowDstPosition);
    }

    private void showGameEndDialog(String winnerID) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Fin du jeu");
        alert.setHeaderText("Le joueur " + winnerID + " a remporté la partie!");
        alert.setContentText("Merci d'avoir joué.");

        alert.showAndWait();
    }

    public void setPlayerGUI(PlayerID playerID){
        setPlayer(new GUIPLayer(), playerID);
    }

    public void setPlayerBasic(PlayerID playerID){
        setPlayer(new Basic(game),playerID);
    }

    public void setPlayerRandom(PlayerID playerID){
        System.out.println("LE JOUEUR " + playerID);
        amazons.player.Random random1 = new amazons.player.Random(random, game);
        random1.setPlayerID(playerID);
        setPlayer(random1, playerID);
        System.out.println("aaaoooooooooooaa" + players[playerID.index].getClass());
    }

    public void setPlayerGreedy(PlayerID playerID){
        // TODO
    }



}
