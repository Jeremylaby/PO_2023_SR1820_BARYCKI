package agh.ics.oop.model;

import agh.ics.oop.OptionsParser;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextMapTest {
    @Test
    void main(){
        List<String> words =List.of(new String("Siema"),new String("ma"),
                new String("nima"),new String("co?"),new String("gdzie"),
                new String("jak"));
        TextMap textMap = new TextMap();
        String [] args = {"r","r","r","r","r","r","f","b","f","b","f","b","r","r","r","r","f","r"};
        List<MoveDirection> moves = OptionsParser.parse(args);
        for (String word:words){
            textMap.place(word);
        }
        int ind=0;
        for(MoveDirection move:moves){
            textMap.move(words.get(ind),move);
            System.out.println(textMap.toString());
            ind+=1;
            ind%=words.size();
        }
        assertTrue(textMap.objectAt(0).equals(new String("ma")));
        assertTrue(textMap.objectAt(1).equals(new String("co?")));
        assertTrue(textMap.objectAt(2).equals(new String("Siema")));
        assertTrue(textMap.objectAt(3).equals(new String("jak")));
        assertTrue(textMap.objectAt(4).equals(new String("nima")));
        assertTrue(textMap.objectAt(5).equals(new String("gdzie")));
    }

}