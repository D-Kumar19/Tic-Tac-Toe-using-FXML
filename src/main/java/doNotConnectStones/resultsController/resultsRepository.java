package doNotConnectStones.resultsController;

import java.io.File;
import java.util.Map;
import java.util.Objects;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

import doNotConnectStones.util.repository.GsonRepository;

/**
 * This is the Class which has all the Methods to get the data from file and then arrange it and show it to the user.
 */
public class resultsRepository extends GsonRepository<fieldsToStore> {

    /**
     * This is the Constructor to initialize the {@code fieldToStore} class.
     */
    public resultsRepository(){super(fieldsToStore.class);}

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
                .collect(groupingBy(fieldsToStore::getWinnerName, counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    /**
     * This Method will get the {@code winnerName} and it will check the {@code getZonedTime} when user played the game.
     * It will then compare each {@code getZonedTime} and will return the latest {@code getZonedTime}..
     * @param name This is the name of the {@code winnerName} for which we need to find the {@code getZonedTime}.
     * @return This method will find the latest {@code getZonedTime} for the {@code name} and will return {@code getZonedTime}.
     */
    public ZonedDateTime getLatestTime(String name){
        return elements.stream()
                .filter(entry -> Objects.equals(entry.getWinnerName(), name))
                .map(fieldsToStore::getZonedDateTime)
                .reduce((first, second) -> second)
                .orElse(null);
    }

    /**
     * This Method will get the {@code winnerName} and it will check the {@code getTotalMoves} of the player.
     * It will then compare the moves and return the least number of {@code getTotalMoves}.
     * @param name It is the name of the {@code winnerName} for which we need to find the {@code getTotalMoves}.
     * @return This method will find the least {@code getTotalMoves} for the {@code name} and will return {@code getTotalMoves}.
     */
    public Integer getLatestMoves(String name){
        return elements.stream()
                .filter(entry -> Objects.equals(entry.getWinnerName(), name))
                .map(fieldsToStore::getTotalMoves)
                .min(Integer::compareTo)
                .orElse(null);
    }
}