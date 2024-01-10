package agh.ics.oop.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class FileMapDisplay implements MapChangeListener{
    private final UUID mapId;

    public FileMapDisplay(UUID mapId) {
        this.mapId = mapId;
    }

    @Override
    public void mapChanged(WorldMap worldMap,String message) {
        String logFileName = mapId.toString() + ".log";

        try (FileWriter writer = new FileWriter(logFileName, true)) {
            writer.write( message + "\n"+worldMap+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
