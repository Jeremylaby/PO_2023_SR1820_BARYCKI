package agh.ics.oop;

import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {   

    @Test
    void convertToEnum() {
        String [] args = {"f","b","r","x","l"};
        List<MoveDirection> directions = new ArrayList<>(Arrays.asList(MoveDirection.FORWARD,MoveDirection.BACKWARD,
                MoveDirection.RIGHT, MoveDirection.LEFT));
        assertTrue(directions.equals(OptionsParser.convertToEnum(args)));
    }
}