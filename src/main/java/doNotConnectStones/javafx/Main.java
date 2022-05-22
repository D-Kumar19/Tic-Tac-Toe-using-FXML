package doNotConnectStones.javafx;

import lombok.extern.slf4j.Slf4j;

import javafx.application.Application;

@Slf4j
public class Main {

    public static void main(String[] args) {

        log.info("Starting Application....");
        Application.launch(doNotConnectStonesApplication.class, args);
    }
}