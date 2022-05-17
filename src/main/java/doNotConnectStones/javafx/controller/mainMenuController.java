package doNotConnectStones.javafx.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import lombok.extern.slf4j.Slf4j;
import javafx.scene.control.TextField;

@Slf4j
public class mainMenuController {

    private final FXMLLoader fxmlLoader= new FXMLLoader();

    @FXML
    private TextField player1Name;

    @FXML
    private TextField player2Name;

    @FXML
    private javafx.scene.text.Text player1NameMissingLabel;

    @FXML
    private javafx.scene.text.Text player2NameMissingLabel;

    public void switchMainMenuToGameScene(ActionEvent actionEvent) throws  IOException{
        log.debug("User just clicked Start!");

        if(player1Name.getText().isEmpty() && player2Name.getText().isEmpty()){
            log.warn("You haven't entered any Player's Name....");
            player1NameMissingLabel.setText("Please Enter Player One's Name!");
            player2NameMissingLabel.setText("Please Enter Player Two's Name!");
        }
        else if(player1Name.getText().isEmpty()){
            log.warn("You have entered only one Player's Name....");
            player1NameMissingLabel.setText("Please Enter Player One's Name!");

            if(!player2Name.getText().isEmpty()){
                player2NameMissingLabel.setText("");
            }
        }
        else if(player2Name.getText().isEmpty()){
            log.warn("You have entered only one Player's Name....");
            player2NameMissingLabel.setText("Please Enter Player Two's Name!");

            if(!player1NameMissingLabel.getText().isEmpty()){
                player1NameMissingLabel.setText("");
            }
        }
        else{
            fxmlLoader.setLocation(getClass().getResource("/fxml/game.fxml"));
            log.info("You have entered both Player's Name correctly....");
            log.info("Name of the Player # 01 is: {}!", player1Name.getText());
            log.info("Name of the Player # 02 is: {}!", player2Name.getText());

            log.info("Loading gameScene....");
            Parent gameScene = fxmlLoader.load();
            fxmlLoader.<gameController>getController().setPlayer1Name(player1Name.getText());
            fxmlLoader.<gameController>getController().setPlayer2Name(player2Name.getText());
            Stage gameStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            gameStage.setTitle("Do-not Connect Stones Game Scene");
            gameStage.setScene(new Scene(gameScene));
            gameStage.setResizable(false);
            gameStage.show();
        }
    }
}