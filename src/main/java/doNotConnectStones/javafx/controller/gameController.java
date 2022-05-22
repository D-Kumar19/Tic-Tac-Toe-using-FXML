package doNotConnectStones.javafx.controller;

import java.io.File;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import java.time.ZonedDateTime;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import lombok.extern.slf4j.Slf4j;
import javafx.scene.shape.Circle;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import java.io.FileNotFoundException;

import doNotConnectStones.resultsController.fieldsToStore;
import doNotConnectStones.states.doNotConnectStonesStates;
import doNotConnectStones.resultsController.resultsRepository;

@Slf4j
public class gameController {

    private final FXMLLoader fxmlLoader = new FXMLLoader();

    private int countSteps = 0;

    private int playerTurn = 0;

    private String winnerName;

    private String player1Name;

    private String player2Name;

    private boolean gameOver = false;

    public doNotConnectStonesStates gameStates;

    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    public void setPlayer2Name(String player2Name) {
        this.player2Name = player2Name;
    }

    private ZonedDateTime zonedDateTime;

    @FXML
    private GridPane boardGrid;

    @FXML
    private Button resetButton;

    @FXML
    private Button finishButton;

    @FXML
    private javafx.scene.text.Text numberOfMoves;

    @FXML
    private javafx.scene.text.Text welcomeText;

    @FXML
    private void initialize() {
        zonedDateTime = ZonedDateTime.now();
        log.debug("Grid of the Game Initialized....");
        finishButton.setDisable(true);
        for (int i = 0; i < boardGrid.getRowCount(); i++) {
            for (int j = 0; j < boardGrid.getRowCount(); j++) {
                var square = createSquare();
                boardGrid.add(square, i, j);
            }
        }
        resetUserDetails();
    }

    private StackPane createSquare() {
        var square = new StackPane();
        square.getStyleClass().add("square");
        var circle = new Circle(50);
        circle.setFill(Color.TRANSPARENT);
        square.getChildren().add(circle);
        square.setOnMouseClicked(this::handleMouseClick);
        return square;
    }

    public void resetUserDetails() {
        log.debug("Re-set all values to Default.");
        gameStates = new doNotConnectStonesStates();
        finishButton.setDisable(true);
        countSteps = 0;
        playerTurn = 0;
        gameOver = false;
        numberOfMoves.setText(String.valueOf(countSteps));
        log.info("New Game State is:");
        gameStates.stateAfterMove();
        Platform.runLater(() -> welcomeText.setText("Good Luck " + player1Name + " and " + player2Name + "!"));
    }

    public void checkIfGameFinished(int row, int col, int playerTurn) {
        if (gameStates.isGameFinished(row, col)) {
            gameOver = true;
            log.debug("Game Over!");

            if (playerTurn == 0) {
                winnerName = player1Name;
            } else {
                winnerName = player2Name;
            }

            log.info("Winner of this Game is: {}", winnerName);
            log.info("{} has Won the Game in {} Steps.", winnerName, countSteps);
            welcomeText.setText("Winner of the Game is " + winnerName + "!");
            log.debug("Storing Data to a Database....");
            storeUserInformation();
            resetButton.setDisable(true);
            finishButton.setDisable(false);
        }
    }

    public void checkIfGameTied() {
        if (gameStates.isGameBoardFilled()) {
            gameOver = true;
            log.info("It is a Tie between {} and {}!", player1Name, player2Name);
            welcomeText.setText("Game has been Tied. Congratulations both!");
            resetButton.setText("Re-Match");
            finishButton.setDisable(false);
        }
    }

    private void clearAllNodes() {
        log.debug("Clearing all Nodes....");
        boardGrid.getChildren().stream()
                .filter(node -> !Objects.equals(node.getTypeSelector(), "Group"))
                .forEach(node -> {
                    var circle = (Circle) ((StackPane) node).getChildren().get(0);
                    circle.setFill(Color.TRANSPARENT);
                });
        log.info("Resetting the Game....");
        log.debug("New Game State is:");
        gameStates.stateAfterMove();
    }

    private void handleMouseClick(MouseEvent mouseEvent) {
        var square = (StackPane) mouseEvent.getSource();
        int row = GridPane.getRowIndex((Node) mouseEvent.getSource());
        int col = GridPane.getColumnIndex((Node) mouseEvent.getSource());

        if (!gameOver) {
            if (gameStates.isMoveValid(row, col)) {
                log.info("User just clicked on [{},{}] cell and isMoveValid is true", row, col);
                var circle = (Circle) square.getChildren().get(0);
                circle.setFill(findPlayerColor((Color) circle.getFill(), row, col));
                numberOfMoves.setText(String.valueOf(countSteps));
            } else {
                log.warn("User just clicked on [{},{}] cell and isMoveValid is false", row, col);
            }

            log.info("States after placing a Stone:");
            gameStates.stateAfterMove();
            // checkIfGameFinished(row, col, playerTurn);
            if(!gameOver) {
                checkIfGameTied();
            }
        }
        else{
                log.warn("User just clicked on [{},{}] cell but Game has already Finished! And we have a Winner", row, col);
        }
    }

    private Color findPlayerColor(Color color, int row, int col) {
        if (playerTurn == 0) {
            countSteps += 1;
            gameStates.setOnBoard(row, col, 'R');
            playerTurn = 1;
            color = Color.RED;
        } else if (playerTurn == 1) {
            countSteps += 1;
            gameStates.setOnBoard(row, col, 'B');
            playerTurn = 0;
            color = Color.BLUE;
        }
        return color;
    }

    public void resetButtonController(ActionEvent actionEvent) {
        String textOfButton = resetButton.getText();
        if (textOfButton.equals("Reset")) {
            log.debug("Reset Button was Clicked....");
            log.info("Resetting Game....");
        } else if (textOfButton.equals("Re-Match")) {
            log.debug("Re-Match Button was Clicked....");
            log.info("Setting Board for Re-Match....");
        }

        log.debug("Final States before Re-Match:");
        gameStates.stateAfterMove();
        clearAllNodes();
        resetUserDetails();
    }

    public void finishButtonController(ActionEvent actionEvent) throws IOException {
        String textOfButton = finishButton.getText();
        log.debug("{} Button was Clicked", textOfButton);
        log.info("The Game has Finished....");
        log.debug("Loading highScore Table....");

        gameOver = true;
        fxmlLoader.setLocation(getClass().getResource("/fxml/highScores.fxml"));
        Parent highScoreScene = fxmlLoader.load();
        Stage highScoreStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        highScoreStage.setTitle("Do-not Connect Stones Main Menu");
        highScoreStage.setScene(new Scene(highScoreScene));
        highScoreStage.setResizable(false);
        highScoreStage.show();
    }

    private void storeUserInformation(){
        if(gameOver){
            var repository = new resultsRepository();
            var result = fieldsToStore.builder()
                    .storePlayer1Name(player1Name)
                    .storePlayer2Name(player2Name)
                    .storeWinnerName(winnerName)
                    .storeTotalMoves(countSteps)
                    .storeZonedDateTime(zonedDateTime)
                    .build();

            log.info("Loading Data into Database....");

            var file = new File("playerResultsFile.json");
            try{
                repository.loadFromFile(file);
                log.info("A previous File Found. Storing Data in the File....");
            } catch (FileNotFoundException e){
                log.warn("No Previous File Found!");
            } catch (IOException e) {
                log.warn("Unable to Open the File!");
            }

            repository.add(result);
            try{
                repository.saveToFile(file);
                log.info("Results added to File Successfully!");
            }
            catch(IOException e){
                log.warn("Unable to Save Results to the File!");
            }
        }
    }
}