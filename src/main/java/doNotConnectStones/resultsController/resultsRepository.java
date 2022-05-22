package doNotConnectStones.resultsController;

import java.io.File;
import java.util.Map;
import java.util.Objects;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

import doNotConnectStones.util.repository.GsonRepository;

public class resultsRepository extends GsonRepository<fieldsToStore> {

    public resultsRepository(){super(fieldsToStore.class);}

    public static final File file = new File("playerResultsFile.json");

    public Map<String, Long> mapAllWinningPlayers(){
        return elements.stream()
                .collect(groupingBy(fieldsToStore::getWinnerName, counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    public ZonedDateTime getLatestTime(String name){
        return elements.stream()
                .filter(entry -> Objects.equals(entry.getWinnerName(), name))
                .map(fieldsToStore::getZonedDateTime)
                .reduce((first, second) -> second)
                .orElse(null);
    }

    public Integer getLatestMoves(String name){
        return elements.stream()
                .filter(entry -> Objects.equals(entry.getWinnerName(), name))
                .map(fieldsToStore::getTotalMoves)
                .min(Integer::compareTo)
                .orElse(null);
    }
}