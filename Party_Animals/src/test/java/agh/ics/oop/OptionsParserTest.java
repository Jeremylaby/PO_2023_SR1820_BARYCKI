package agh.ics.oop;

import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirections;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void convertToEnum() {
        String [] args = {"f","b","r","x","l"};
        MoveDirections [] directions = {MoveDirections.FORWARD,MoveDirections.BACKWARD,
                                        MoveDirections.RIGHT, MoveDirections.LEFT};
        assertArrayEquals(directions,OptionsParser.convertToEnum(args));
    }
}