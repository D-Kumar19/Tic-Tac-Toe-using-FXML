package TicTacToeGame.Util.Repository;

import java.io.File;
import java.util.List;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import TicTacToeGame.Util.GSON.LocalDateAdapter;
import TicTacToeGame.Util.GSON.ZonedDateTimeAdapter;

public class GsonRepository<T> extends Repository<T> {

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter())
            .create();

    public GsonRepository(Class<T> elementType) {
        super(elementType);
    }

    public void loadFromFile(File file) throws IOException {
        try (var reader = new FileReader(file)) {
            var listType = TypeToken.getParameterized(List.class, elementType).getType();
            elements = GSON.fromJson(reader, listType);
        }
    }

    public void saveToFile(File file) throws IOException {
        try (var writer = new FileWriter(file)) {
            GSON.toJson(elements, writer);
        }
    }
}