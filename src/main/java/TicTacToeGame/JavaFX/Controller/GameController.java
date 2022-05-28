package TicTacToeGame.JavaFX.Controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

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
import javafx.scene.image.Image;
import javafx.scene.control.Alert;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import java.io.FileNotFoundException;
import javafx.scene.control.ButtonType;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

import TicTacToeGame.Model.TicTacToeGameModel;
import TicTacToeGame.ResultsController.FieldsToStore;
import TicTacToeGame.ResultsController.ResultsRepository;

/**
 * This is the Main Game Controlling Class.
 * It will handle all the functionality of playing the Game.
 * And storing the Information if there is a Winner otherwise not.
 */
@Data
@Slf4j
public class GameController {

    private int playerTurn = 0;
    private String winnerName;
    private String player1Name;
    private String player2Name;
    private ZonedDateTime zonedDateTime;
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    private final FXMLLoader fxmlLoader = new FXMLLoader();
    private BooleanProperty isGameOver = new SimpleBooleanProperty();
    private IntegerProperty player1Steps = new SimpleIntegerProperty();
    private IntegerProperty player2Steps = new SimpleIntegerProperty();
    private IntegerProperty totalMoves = new SimpleIntegerProperty();
    private final String[] images = {"/Images/Letter_X.png", "/Images/Letter_O.png"};

    @FXML
    private GridPane boardGrid;
    @FXML
    private Button resetButton;
    @FXML
    private Button finishButton;
    @FXML
    private Button switchButton;
    @FXML
    private javafx.scene.text.Text numberOfMoves;
    @FXML
    private javafx.scene.text.Text welcomeText;
    @FXML
    private javafx.scene.text.Text playerTurnText;

    /**
     * This is the Object of Model. We will use all the Methods of the Model.
     * For Example: To check if Game has been Finished using {@code hasGameFinished} Method or, to check if Game has Tied or not using the {@code checkIfGameTied} Method.
     */
    private TicTacToeGameModel gameStates;

    /**
     * This Method will is the Initializing Method. It will populate the {@code boardGrid}
     * It will create a Stack pane which is clickable.
     * And then, it will call {@code resetUserProgress} Method which will Reset all User Details and Progress.
     */
    @FXML
    private void initialize() {
        log.debug("Grid of the Game Populated....");
        for (int i = 0; i < boardGrid.getRowCount(); i++) {
            for (int j = 0; j < boardGrid.getRowCount(); j++) {
                var square = new StackPane();
                square.getStyleClass().add("square");
                square.setOnMouseClicked(this::handleMouseClick);
                boardGrid.add(square, j, i);
            }
        }
        resetUserProgress();
    }

    /**
     * This Method will Reset all User Details and Progress.
     * It also calls a {@code clearGrid} Method which will clear the Grid, if there are some Marks placed.
     * This Method will also Set the TextFields with Initial Messages.
     */
    public void resetUserProgress() {
        log.debug("Re-setting all entities to Default.");
        gameStates = new TicTacToeGameModel();

        playerTurn = 0;
        clearGrid();
        totalMoves.setValue(0);
        player1Steps.setValue(0);
        player2Steps.setValue(0);

        isGameOver.setValue(false);
        resetButton.setText("Reset");
        switchButton.setDisable(false);

        finishButton.setText("Gave Up");
        zonedDateTime = ZonedDateTime.now();
        numberOfMoves.setText(String.valueOf(totalMoves.get()));

        log.info("Game has been Successfully Reset!");
        log.info("New Game State is:");
        log.info("{}\n", (Object) gameStates.getGameBoard());

        Platform.runLater(() -> welcomeText.setText("Good Luck " + player1Name + " and " + player2Name + "!"));
        Platform.runLater(() -> playerTurnText.setText(player1Name + "'s Turn!"));
    }

    /**
     * This Method will be Used to Reset the Board which can be done by clearing the Grid, if there are some Marks placed.
     */
    private void clearGrid() {
        log.debug("Clearing all Nodes....");
        log.info("Resetting the Nodes to Default....");
        boardGrid.getChildren().stream()
                .filter(node -> !Objects.equals(node.getTypeSelector(), "Group"))
                .forEach(node -> ((StackPane) node).getChildren().clear());
    }

    /**
     * This Method will handle the Input from Mouse.
     * When User will click on the {@code boardGrid} it will get the {@code row} and {@code col} of the Position where we clicked.
     * It will check if that position is Empty or not which can be checked by {@code isMoveValid} Method.
     * If Game is Over then it will call a Function to find the Player, and it's Mark.
     * If it is not Empty it will not put the Mark. If {@code boardGrid} is full it will still not put the Mark.
     * It will also check if Game has been Finished or not using {@code checkIfGameFinished}.
     * And it will check if Game has been Tied or not using {@code checkIfGameTied}.
     * @param mouseEvent This is the {@code MouseEvent}. When User will click on {@code boardGrid} the Input will be from Mouse.
     */
    @FXML
    private void handleMouseClick(MouseEvent mouseEvent) {
        int row = GridPane.getRowIndex((Node) mouseEvent.getSource());
        int col = GridPane.getColumnIndex((Node) mouseEvent.getSource());

        if (!isGameOver.getValue()) {
            if (gameStates.isMoveValid(row, col)) {
                putMarkOnBoard(row, col);

                switchButton.setDisable(true);
                log.info("{}\n", (Object) gameStates.getGameBoard());
                log.info("User just clicked on [{},{}] cell and isMoveValid is true", row, col);

            } else {
                log.warn("User just clicked on [{},{}] cell and isMoveValid is false", row, col);
            }

            checkIfGameFinished(row, col);
            if(!isGameOver.getValue()) {
                checkIfGameTied();
            }
        }
        else{
            log.warn("User just clicked on [{},{}] cell but Game has already Finished!", row, col);
        }
    }

    /**
     * This Method will be called to put the specific Mark of Player on the {@code boardGrid}.
     * First it will find the Player, and it's specific Mark using the {@code findMarkOfPlayer} Method.
     * Then it will place the Image of the Mark at  {@param row} and {@param col} position.
     * @param row This is the row number where we need to place the Mark.
     * @param col This is the column number where we need to place the Mark.
     */
    private void putMarkOnBoard(int row, int col){
        totalMoves.setValue(totalMoves.get() + 1);
        ImageView markOfPlayer = findMarkOfPlayer(row, col);
        numberOfMoves.setText(String.valueOf(totalMoves.get()));

        markOfPlayer.setFitWidth(80);
        markOfPlayer.setFitHeight(80);

        boardGrid.getChildren().stream()
                .filter(node -> !Objects.equals(node.getTypeSelector(), "Group"))
                .filter(child -> GridPane.getRowIndex(child) == row && GridPane.getColumnIndex(child) == col)
                .forEach(node -> ((StackPane) node).getChildren().add(markOfPlayer));
    }

    /**
     * This Method will find the Player, and it's specific Mark.
     * Then it will update the Turn of the Player. And set the Mark on {@code boardGrid} using {@code setOnBoard}.
     * It will also update the TextFields later.
     * @param row This is the row number where we need to place the Mark.
     * @param col This is the column number where we need to place the Mark.
     * @return Imageview This is the Image of the Mark that belongs to that specific Player which we need to put on the {@code boardGrid}.
     */
    private ImageView findMarkOfPlayer(int row, int col){

        ImageView markOfPlayer;
        if(playerTurn == 0){
            playerTurn = 1;
            gameStates.setOnBoard(row, col, 'X');
            log.info("Setting a Mark 'X' on Board at [{},{}]!", row, col);

            markOfPlayer = getImage(0);
            playerTurnText.setText(player2Name + "'s Turn!");
            player1Steps.setValue(player1Steps.getValue() + 1);
        }
        else {
            playerTurn = 0;
            gameStates.setOnBoard(row, col, 'O');
            log.info("Setting a Mark 'O' on Board at [{},{}]!", row, col);

            markOfPlayer = getImage(1);
            playerTurnText.setText(player1Name + "'s Turn!");
            player2Steps.setValue(player2Steps.getValue() + 1);
        }
        return markOfPlayer;
    }

    /**
     * This Method will get the Image according to the Player from String of Images which is {@code images}. Then it will return that Image.
     * @param i This is the position of the Image that we need to put on {@code boardGrid}.
     * @return Imageview This is the Image of the Mark that belongs to that specific Player which we need to put on the {@code boardGrid}.
     */
    private ImageView getImage(int i){
        var image = new Image(images[i]);
        return new ImageView(image);
    }

    /**
     * This Method will check if Game is Finished or not. It will use the Model which has a {@code hasGameFinished} Method.
     * If {@code hasGameFinished} returns {@code true} which means Game has Ended or {@code false} otherwise which means Game is not Over yet.
     * If Game is Over it will set the TextFields with Name Winning Player. Then, it will Disable {@code Reset} and {@code Switch} Buttons.
     * It will also store the User Information using the {@code storeUserInformation} Method.
     * @param row This is the row number where we placed the Mark where we need to check if Game is Over or not.
     * @param col This is the column number where we placed the Mark where we need to check if Game is Over or not
     */
    public void checkIfGameFinished(int row, int col) {
        if (gameStates.hasGameFinished(row, col)) {
            log.info("Game Over!");
            isGameOver.setValue(true);

            if (playerTurn == 0) {
                winnerName = player2Name;
            } else {
                winnerName = player1Name;
            }

            log.info("Winner of this Game is: {}", winnerName);
            log.info("{} has Won the Game in {} Steps.", winnerName, (totalMoves.get()));
            welcomeText.setText("Winner of the Game is " + winnerName + "!");

            log.debug("Storing Data into a Database....");
            storeUserInformation();

            playerTurnText.setText("");
            resetButton.setDisable(true);
            switchButton.setDisable(true);
            finishButton.setDisable(false);
            finishButton.setText("Finish");
        }
    }

    /**
     * This Method will check if Game is Tied or not. It will use the Model which has a {@code isGameBoardFilled} Method.
     * If {@code isGameBoardFilled} returns {@code true} which means Game has been Tied or {@code false} otherwise which means Game has not been Tied yet.
     * If Game has been Tied it will set the TextFields with Messages.
     */
    public void checkIfGameTied() {
        if (gameStates.isGameBoardFilled()) {
            isGameOver.setValue(true);
            log.info("It is a Tie between {} and {}! Congratulations both!", player1Name, player2Name);

            playerTurnText.setText("");
            finishButton.setText("Finish");
            resetButton.setText("Re-Match");
            welcomeText.setText("Game has Tied. Congratulations both!");
        }
    }

    /**
     * This Method will handle the {@code Reset} Button.
     * If User clicks on {@code Reset} Button it will first check using a Confirmation Message.
     * User clicks 'OK' it will Reset the Game by calling {@code resetUserProgress} Method which will clear User Details and {@code boardGrid} too.
     * If Text of {@code Reset} Button is changed when Game is Tied to {@code Re-Match} then it will also do the same.
     * But if User clicks 'Cancel' it will not Reset the Game.
     */
    public void resetButtonController() {
        String textOfButton = resetButton.getText();
        if (textOfButton.equals("Reset")) {
            log.info("Reset Button was Clicked....");
            log.debug("Resetting Game....");

            if (confirmationMessage("ARE YOU SURE YOU WANT TO RESET THE GAME!", "If you click 'OK' your progress will not be saved!")) {
                log.info("Final States before Reset:");
                log.info("{}\n", (Object) gameStates.getGameBoard());
                resetUserProgress();
            }
            else{
                log.info("Game wasn't Reset....");
            }
        } else if (textOfButton.equals("Re-Match")) {
            log.info("Re-Match Button was Clicked....");
            log.info("Setting Board for Re-Match....");
            resetUserProgress();

        }
    }

    /**
     * This Method will handle the {@code Switch} Button.
     * If User clicks on {@code Switch} Button it will first check using a Confirmation Message.
     * User clicks 'OK' it will Switch the Initial Positions and Player # 02 will play first then.
     * Then, it will set the TextFields accordingly.
     * But if User clicks 'Cancel' it will not Switch the Positions of the Player.
     */
    public void switchButtonController(){
        log.info("Switch Button was Clicked....");

        if (confirmationMessage("ARE YOU SURE YOU WANT TO SWITCH THE SIDES?", "If you click 'OK', Game will Restart!")) {
            int playerTurnCopy = playerTurn;
            resetUserProgress();

            if(playerTurnCopy == 0){
                playerTurn = 1;
                Platform.runLater(() -> playerTurnText.setText(player2Name + "'s Turn!"));
            }
            else{
                playerTurn = 0;
                Platform.runLater(() -> playerTurnText.setText(player1Name + "'s Turn!"));
            }
        }
        else{
            log.info("Player Side wasn't switched....");
        }
    }

    /**
     * This Method will handle the {@code Finish} Button.
     * First we will get the Text of the Button if it is 'Finish', it will Finish the Game.
     * But if Text of the Button is {@code Gave Up} and User clicks on the Button then it will first check using a Confirmation Message.
     * If User clicks 'OK' it will Finish the Game and switch the Scenes to {@code HighScoreTable}.
     * For that, it will use the {@code implementFinishing} Method to which we will pass the {@code ActionEvent}.
     * But if User clicks 'Cancel' it will not Finish the Game.
     * @param actionEvent This is the {@code ActionEvent} which will help to switch Scene from the Recent Scene to Next Scene.
     * @throws IOException If Program is not able to find the {@code MainGame} or it is not defined then it will throw an Error.
     */
    public void finishButtonController(ActionEvent actionEvent) throws IOException {
        String textOfButton = finishButton.getText();
        if (textOfButton.equals("Gave Up")) {
            log.info("Gave Up Button was Clicked....");
            log.debug("Game Given up....");

            if (confirmationMessage("ARE YOU SURE YOU WANT TO LEAVE THE GAME IN-BETWEEN!", "If you click 'OK' your progress will not be saved!")) {
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

    /**
     * This Method will implement the Finishing.
     * It will switch the scene and will load the {@code HighScoreTable}.
     * @param actionEvent This is the {@code ActionEvent} which will help to switch Scene from the Recent Scene to Next Scene.
     * @throws IOException If Program is not able to find the {@code MainGame} or it is not defined then it will throw an Error.
     */
    public void implementFinishing(ActionEvent actionEvent) throws IOException {
        log.debug("Loading HighScore Table....");

        fxmlLoader.setLocation(getClass().getResource("/FXML/HighScores.fxml"));
        Parent highScoreScene = fxmlLoader.load();
        Stage highScoreStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        highScoreStage.setTitle("Do-not Connect Stones Main Menu");
        highScoreStage.setScene(new Scene(highScoreScene));
        highScoreStage.show();
    }

    /**
     * This Method will be used for Confirmation Messages of all Buttons.
     * It will get two parameters which are its {@code HeaderText} and {@code ContentText}.
     * It will return {@code true} if User clicks on 'OK' Button or {@code false} if User clicks on 'Cancel' Button.
     * @param headerText This is the {@code HeaderText} that we need to put on the Confirmation Message.
     * @param contentText This is the {@code ContentText} that we need to put on the Confirmation Message.
     * @return boolean It will return {@code true} if User clicks on 'OK' Button or {@code false} if User clicks on 'Cancel' Button.
     */
    private boolean confirmationMessage(String headerText, String contentText){
        alert.setTitle("Confirmation Message!");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
        return alert.getResult() == ButtonType.OK;
    }

    /**
     * This Method will store the User Information in a File using JSON Format.
     * It will call {@code bindResults} to Bind all the Fields.
     * It will also check if there is an existing File or not. If there is it will store Information in it.
     * If there isn't it will create a File and will store Information.
     */
    private void storeUserInformation(){
        if(isGameOver.get()){
            var repository = new ResultsRepository();
            FieldsToStore result = buildResults();

            log.info("Loading Data into Database....");
            try{
                repository.loadFromFile(ResultsRepository.file);
                log.info("Previous File Found. Storing Data in the Previous File....");
            }catch(FileNotFoundException e){
                log.info("No Previous File Found!");
            } catch (IOException e) {
                log.error("Unable to Open Previous File!");
            }
            repository.add(result);
            try {
                repository.saveToFile(ResultsRepository.file);
                log.info("Results saved to File Successfully!");
            } catch (IOException e){
                log.error("Unable to Save results to file!");
            }
        }
    }

    /**
     * This Method will bind all the Fields to store in a File.
     * @return FieldsToStore This method will return the Object of {@code FieldsToStore} Class after binding all the User Details.
     */
    private FieldsToStore buildResults(){
        return FieldsToStore.builder()
                .player1Name(player1Name)
                .player2Name(player2Name)
                .winnerName(winnerName)
                .player1Moves(player1Steps.get())
                .player2Moves(player2Steps.get())
                .totalMoves(totalMoves.get())
                .zonedDateTime(zonedDateTime)
                .build();
    }
}