package Comp2112.SecondPart;

import java.io.*;
import java.util.*;

public class NameFile {
 
    public void getnames(ArrayList allNames) {

        try {
            Scanner namescanner = new Scanner(new File("C:\\Users\\Ahmet Eren\\Desktop\\names\\names.txt"));
            while (namescanner.hasNextLine()) {
                String name = namescanner.nextLine();
                allNames.add(name);
            }

            Collections.shuffle(allNames);

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");

        }
    }

    public void ChooseSelect(ArrayList allNames, DoubleLinkedList names30) {
        for (int i = 0; i < 30; i++) {
            names30.add((String) allNames.get(i));
        }
    }
}
