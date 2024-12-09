package Comp2112.SecondPart;

public class MatchingCharacter {
 
    public void messageCharacter(String message, String name, DoubleLinkedList names30, int index) {
        if (message == null || message.length() == 0) {
            System.out.println("Message is empty.");
            return;
        }

        int matchingCharacter = 0;
        message = message.toUpperCase();

        for (int i = 0; i < name.length(); i++) {
            char nameChar = name.charAt(i);

            for (int k = 0; k < message.length(); k++) {
                char messageChar = message.charAt(k);

                if (messageChar == ' ') {
                    continue;
                }

                if (messageChar == nameChar) {
                    matchingCharacter++;
                }
            }
        }

        names30.getNode(index).setmatchingCharacter(matchingCharacter);

    }

}
