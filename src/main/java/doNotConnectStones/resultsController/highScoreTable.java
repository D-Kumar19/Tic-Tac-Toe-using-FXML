package doNotConnectStones.resultsController;

import java.time.ZonedDateTime;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class highScoreTable {
    private String nameOfWinner;
    private Integer numberOfMoves;
    private Long numberOfWins;
    private ZonedDateTime zonedDateTime;
}