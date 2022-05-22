package doNotConnectStones.resultsController;

import java.time.ZonedDateTime;

import lombok.Data;
import lombok.Builder;

/**
 * This is the Class which has all the fields of Tableview that we need to show to the user.
 */
@Data
@Builder
public class highScoreTable {
    private String nameOfWinner;
    private Integer numberOfMoves;
    private Long numberOfWins;
    private ZonedDateTime zonedDateTime;
}