package doNotConnectStones.resultsController;

import lombok.Data;
import lombok.Builder;

import java.time.ZonedDateTime;

/**
 * This is the Class which has all the fields that we need to store when storing user data in the Database.
 */
@Data
@Builder
public class fieldsToStore {
    private String player1Name;
    private String player2Name;
    private String winnerName;
    private int player1Moves;
    private int player2Moves;
    private int totalMoves;
    private ZonedDateTime zonedDateTime;
}