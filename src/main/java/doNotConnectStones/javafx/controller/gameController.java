package doNotConnectStones.javafx.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lombok.Data;

import java.util.Objects;
import java.io.IOException;
import java.time.ZonedDateTime;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
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

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

import doNotConnectStones.resultsController.fieldsToStore;
import doNotConnectStones.states.doNotConnectStonesStates;
import doNotConnectStones.resultsController.resultsRepository;

@Data
@Slf4j
public class gameController {

    private int playerTurn = 0;
    private String winnerName;
    private String player1Name;
    private String player2Name;
    private ZonedDateTime zonedDateTime;
    public doNotConnectStonesStates gameStates;
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    private final FXMLLoader fxmlLoader = new FXMLLoader();
    private BooleanProperty isGameOver = new SimpleBooleanProperty();
    private IntegerProperty player1Steps = new SimpleIntegerProperty();
    private IntegerProperty player2Steps = new SimpleIntegerProperty();
    private IntegerProperty totalMoves = new SimpleIntegerProperty();

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
    private javafx.scene.text.Text playerTurnText;

    @FXML
    private void initialize() {
        zonedDateTime = ZonedDateTime.now();
        log.debug("Grid of the Game Populated....");

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
        log.debug("Re-setting all entities to Default.");
        gameStates = new doNotConnectStonesStates();

        playerTurn = 0;
        totalMoves.setValue(0);
        player1Steps.setValue(0);
        player2Steps.setValue(0);
        isGameOver.setValue(false);
        finishButton.setText("Gave Up");
        numberOfMoves.setText(String.valueOf(totalMoves.get()));

        log.info("Game has been Successfully Reset!");
        log.info("New Game State is:");
        gameStates.stateAfterMove();

        Platform.runLater(() -> welcomeText.setText("Good Luck " + player1Name + " and " + player2Name + "!"));
        Platform.runLater(() -> playerTurnText.setText(player1Name + "'s Turn!"));
    }

    public void checkIfGameFinished(int row, int col, int playerTurn) {
        if (gameStates.isGameFinished(row, col)) {
            log.info("Game Over!");
            isGameOver.setValue(true);

            if (playerTurn == 0) {
                winnerName = player1Name;
            } else {
                winnerName = player2Name;
            }

            log.info("Winner of this Game is: {}", winnerName);
            log.info("{} has Won the Game in {} Steps.", winnerName, (totalMoves.get()));
            welcomeText.setText("Winner of the Game is " + winnerName + "!");

            log.debug("Storing Data into a Database....");
            storeUserInformation();

            playerTurnText.setText("");
            resetButton.setDisable(true);
            finishButton.setDisable(false);
            finishButton.setText("Finish");
        }
    }

    public void checkIfGameTied() {
        if (gameStates.isGameBoardFilled()) {
            isGameOver.setValue(true);
            log.info("It is a Tie between {} and {}!", player1Name, player2Name);

            playerTurnText.setText("");
            finishButton.setText("Finish");
            resetButton.setText("Re-Match");
            welcomeText.setText("Game has Tied. Congratulations both!");
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

        log.info("Resetting the Nodes to Default....");
    }

    private void handleMouseClick(MouseEvent mouseEvent) {
        var square = (StackPane) mouseEvent.getSource();
        int row = GridPane.getRowIndex((Node) mouseEvent.getSource());
        int col = GridPane.getColumnIndex((Node) mouseEvent.getSource());

        if (!isGameOver.getValue()) {
            if (gameStates.isMoveValid(row, col)) {
                log.info("User just clicked on [{},{}] cell and isMoveValid is true", row, col);
                var circle = (Circle) square.getChildren().get(0);
                circle.setFill(findPlayerColor((Color) circle.getFill(), row, col));

                log.info("States after placing a Stone:");
                gameStates.stateAfterMove();
            } else {
                log.warn("User just clicked on [{},{}] cell and isMoveValid is false", row, col);
            }

            checkIfGameFinished(row, col, playerTurn);
            if(!isGameOver.getValue()) {
                checkIfGameTied();
            }
        }
        else{
                log.warn("User just clicked on [{},{}] cell but Game has already Finished!", row, col);
        }
    }

    private Color findPlayerColor(Color color, int row, int col) {
        if (playerTurn == 0) {
            playerTurn = 1;
            color = Color.RED;
            gameStates.setOnBoard(row, col, 'R');
            playerTurnText.setText(player2Name + "'s Turn!");
            player1Steps.setValue(player1Steps.getValue() + 1);

        } else if (playerTurn == 1) {
            playerTurn = 0;
            color = Color.BLUE;
            gameStates.setOnBoard(row, col, 'B');
            playerTurnText.setText(player1Name + "'s Turn!");
            player2Steps.setValue(player2Steps.getValue() + 1);
        }
        totalMoves.setValue(totalMoves.get() + 1);
        numberOfMoves.setText(String.valueOf(totalMoves.get()));

        return color;
    }

    public void resetButtonController(ActionEvent actionEvent) {
        String textOfButton = resetButton.getText();
        if (textOfButton.equals("Reset")) {
            log.info("Reset Button was Clicked....");
            log.debug("Resetting Game....");

            alert.setTitle("Error!");
            alert.setHeaderText("ARE YOU SURE YOU WANT TO RESET THE GAME!");
            alert.setContentText("If you click 'OK' your progress will not be saved!");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                log.info("Final States before Reset:");
                gameStates.stateAfterMove();
                implementResetting();
            }
            else{
                log.info("Game wasn't Reset....");
            }
        } else if (textOfButton.equals("Re-Match")) {
            log.info("Re-Match Button was Clicked....");
            log.info("Setting Board for Re-Match....");
            implementResetting();
        }
    }

    public void implementResetting(){
        clearAllNodes();
        resetUserDetails();
    }

    public void finishButtonController(ActionEvent actionEvent) throws IOException {
        String textOfButton = finishButton.getText();
        if (textOfButton.equals("Gave Up")) {
            log.info("Gave Up Button was Clicked....");
            log.debug("Game Given up....");

            alert.setTitle("Error!");
            alert.setHeaderText("ARE YOU SURE YOU WANT TO LEAVE THE GAME IN-BETWEEN!");
            alert.setContentText("If you click 'OK' your progress will not be saved!");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                implementFinishing(actionEvent);
            }
            else{
                log.info("Game has not Finished....");
            }

        } else if (textOfButton.equals("Finish")) {
            log.info("Finish button was Clicked....");
            log.debug("Finishing Game....");
            implementFinishing(actionEvent);
        }
    }

    public void implementFinishing(ActionEvent actionEvent) throws IOException {
        log.debug("Loading highScore Table....");
        isGameOver.setValue(true);

        fxmlLoader.setLocation(getClass().getResource("/fxml/highScores.fxml"));
        Parent highScoreScene = fxmlLoader.load();
        Stage highScoreStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        highScoreStage.setTitle("Do-not Connect Stones Main Menu");
        highScoreStage.setScene(new Scene(highScoreScene));
        highScoreStage.setResizable(false);
        highScoreStage.show();
    }

    private void storeUserInformation(){
        if(isGameOver.get()){
            var repository = new resultsRepository();
            var result = fieldsToStore.builder()
                    .player1Name(player1Name)
                    .player2Name(player2Name)
                    .winnerName(winnerName)
                    .player1Moves(player1Steps.get())
                    .player2Moves(player2Steps.get())
                    .totalMoves(totalMoves.get())
                    .zonedDateTime(zonedDateTime)
                    .build();

            log.info("Loading Data into Database....");
            try{
                repository.loadFromFile(resultsRepository.file);
                log.info("Previous File Found. Storing Data in the Previous File....");
            }catch(FileNotFoundException e){
                log.info("No previous file found!");
            } catch (IOException e) {
                log.error("Unable to open previous file!");
            }
            repository.add(result);
            try {
                repository.saveToFile(resultsRepository.file);
                log.info("Results added to File Successfully!");
            } catch (IOException e){
                log.error("Unable to Save results to file!");
            }
        }
    }
}