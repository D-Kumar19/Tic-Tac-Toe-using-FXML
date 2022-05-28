package TicTacToeGame.ResultsController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.time.ZonedDateTime;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

import TicTacToeGame.Util.Repository.GsonRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * This is the Class which has all the Methods to get the Data from File and then Arrange it and show it to the User.
 */
@Slf4j
public class ResultsRepository extends GsonRepository<FieldsToStore> {

    List<HighScoreTableColumns> highScoreTableList = new ArrayList<>();

    /**
     * This is the Constructor to initialize the {@code FieldToStore} class.
     */
    public ResultsRepository(){super(FieldsToStore.class);}

    /**
     * This is the File from which we will read the data.
     */
    public static final File file = new File("playerResultsFile.json");

    /**
     * This method will Map the {@code winnerName} and {@code numberOfWins}.
     * @return This method will return the Map with key as {@code winnerName} and value as {@code numberOfWins}.
     */
    public Map<String, Long> mapAllWinningPlayers(){
        return elements.stream()
                .collect(groupingBy(FieldsToStore::getWinnerName, counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    /**
     * This Method will get the {@code winnerName} and it will check the {@code getZonedTime} when user played the game.
     * It will then compare each {@code getZonedTime} and will return the latest {@code getZonedTime}..
     * @param name This is the name of the {@code winnerName} for which we need to find the latest {@code getZonedTime}.
     * @return This method will find the latest {@code getZonedTime} for the {@code name} and will return {@code getZonedTime}.
     */
    public ZonedDateTime getLatestTime(String name){
        return elements.stream()
                .filter(entry -> Objects.equals(entry.getWinnerName(), name))
                .map(FieldsToStore::getZonedDateTime)
                .reduce((first, second) -> second)
                .orElse(null);
    }

    /**
     * This Method will get the {@code winnerName} and it will check the {@code getTotalMoves} of the player.
     * It will then compare the moves and return the least number of {@code getTotalMoves}.
     * @param name It is the name of the {@code winnerName} for which we need to find the least {@code getTotalMoves}.
     * @return This method will find the least {@code getTotalMoves} for the {@code name} and will return {@code getTotalMoves}.
     */
    public Integer getLeastMoves(String name){
        return elements.stream()
                .filter(entry -> Objects.equals(entry.getWinnerName(), name))
                .map(FieldsToStore::getTotalMoves)
                .min(Integer::compareTo)
                .orElse(null);
    }

    /**
     * This Method will Read the File and Load every Item from File. Then it will use a Builder to Build a List in which it will store all the Columns that we need to store for the {@code HighScoreTable}.
     * It will then call a Method which will Sort the Lists according to their Wins first and if Wins are equal then Number of Moves. And it will then return the {@code HighScoreTableList}.
     * @param n This is the Number of Rows that we need to Extract.
     * @return This is return the List of {@code HighScoreTableColumns} which will be shown to User at the End of the Game.
     * @throws IOException If Method is not able to Find the File it will throw an {@code IOException}.
     */
    public List<HighScoreTableColumns> getTop5Players(int n) throws IOException {
        var repository = new ResultsRepository();
        try {
            repository.loadFromFile(ResultsRepository.file);
            var mapPlayersAndWins = repository.mapAllWinningPlayers();
            mapPlayersAndWins.forEach((nameOfPlayer, numberOfWins)->{
                HighScoreTableColumns singlePlayerScore = HighScoreTableColumns.builder()
                        .nameOfWinner(nameOfPlayer)
                        .numberOfMoves(repository.getLeastMoves(nameOfPlayer))
                        .numberOfWins(numberOfWins)
                        .zonedDateTime(repository.getLatestTime(nameOfPlayer))
                        .build();
                highScoreTableList.add(singlePlayerScore);
            });

            log.info("Sorting the List....");
            sortArrayList(n);
        } catch (FileNotFoundException e) {
            log.error("No Previous record Found!");
        }
        return highScoreTableList;
    }

    /**
     * This Method will use Comparator and will Sort the Lists according to their Wins first and if Wins are equal then Number of Moves.
     * @param n This is the Number of Rows that we need to Extract.
     */
    private void sortArrayList(int n){
        Comparator<HighScoreTableColumns> sortPlayersWithWinsAndMoves = (entity1, entity2) -> {
            if(entity2.getNumberOfWins().equals(entity1.getNumberOfWins())){
                return Long.compare(entity1.getNumberOfMoves(), entity2.getNumberOfMoves());
            }
            else{
                return Long.compare(entity2.getNumberOfWins(), entity1.getNumberOfWins());
            }
        };

        highScoreTableList.sort(sortPlayersWithWinsAndMoves);
        highScoreTableList = highScoreTableList.stream()
                .limit(n)
                .collect(Collectors.toList());
    }
}