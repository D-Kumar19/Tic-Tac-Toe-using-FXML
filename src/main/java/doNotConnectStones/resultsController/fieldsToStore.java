package doNotConnectStones.resultsController;

import lombok.Data;
import lombok.Builder;
import java.time.ZonedDateTime;

@Builder
@Data
public class fieldsToStore {
    private String storePlayer1Name;
    private String storePlayer2Name;
    private String storeWinnerName;
    private int storeTotalMoves;
    private ZonedDateTime storeZonedDateTime;

}
