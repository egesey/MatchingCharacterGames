package Comp2112.SecondPart;

import java.util.*;

public class Main {
 
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the hubName num: ");
        int numOfHub = scan.nextInt();
        DoubleLinkedList names30 = new DoubleLinkedList();
        DoubleLinkedList selectedHubNames = new DoubleLinkedList();
        ArrayList<String> allNames = new ArrayList();
        ArrayList<String> allMessages = new ArrayList();
        NameFile namesfile = new NameFile();
        namesfile.getnames(allNames);
        namesfile.ChooseSelect(allNames, names30);

        DoubleLinkedList.selectRandomNames(names30, numOfHub, selectedHubNames);
        names30.displayWithSelected(selectedHubNames);
        names30.message(names30, selectedHubNames, allMessages);

    }

}
 