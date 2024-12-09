package Comp2112.SecondPart;

import java.util.ArrayList;
import java.util.Scanner;

class DoubleLinkedList {
 
    MatchingCharacter s1 = new MatchingCharacter();
    private int size;
    private DoubleNode first;
    private DoubleNode last;

    public DoubleLinkedList() {

        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public void add(String name) {
        DoubleNode newNode = new DoubleNode(name);
        if (first == null) {
            first = newNode;
            last = newNode;
            newNode.next = first;
            newNode.prev = last;
        } else {
            last.next = newNode;
            newNode.prev = last;
            newNode.next = first;
            last = newNode;
            first.prev = last;
        }
        size++;
    }

    public int getSize() {
        return size;
    }

    private boolean isSelected(String name, DoubleLinkedList selectedHubNames) {
        DoubleNode temp = selectedHubNames.first;
        if (temp == null) {
            return false;
        }
        do {
            if (temp.name.equals(name)) {
                return true;
            }
            temp = temp.next;
        } while (temp != selectedHubNames.first);

        return false;
    }

    public void displayWithSelected(DoubleLinkedList selectedHubNames) {
        if (first == null) {
            System.out.println("List is empty!");
            return;
        }

        DoubleNode temp = first;
        do {

            if (isSelected(temp.name, selectedHubNames)) {
                System.out.print(temp.messageCount + temp.name + "* <-> ");
            } else {
                System.out.print(temp.messageCount + temp.name + " <-> ");
            }
            temp = temp.next;
        } while (temp != first);

        System.out.println();
    }

    public DoubleNode getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        DoubleNode tmp = first;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        return tmp;
    }

    public static void selectRandomNames(DoubleLinkedList names30, int numOfHub, DoubleLinkedList selectedHubNames) {
        if (numOfHub <= 0 || numOfHub > names30.getSize()) {
            System.out.println("Invalid number of names to select.");
        } else {

            while (selectedHubNames.getSize() < numOfHub) {
                // Get a random node from the linked list
                int randomIndex = (int) (Math.random() * (names30.getSize()));
                String name = names30.getNode(randomIndex).name;

                selectedHubNames.add(name);

            }
        }
    }

    public boolean allHaveAtLeastOneMessage() {
        DoubleNode temp = first;
        do {
            if (temp.messageCount == 0) {
                return false;
            }
            temp = temp.next;
        } while (temp != first);
        return true;
    }

    public boolean isHubStudent(DoubleNode name, DoubleLinkedList selectedHubNames, ArrayList<String> allmessages) {
        boolean isHub = false;

        for (int index = 0; index < selectedHubNames.getSize(); index++) {
            DoubleNode hubStudent = selectedHubNames.getNode(index);

            if (name.name.equals(hubStudent.name)) {
                isHub = true;
                break;
            }
        }

        if (isHub) {
            if (allHaveAtLeastOneMessage()) {
                displayWithSelected(selectedHubNames);
            }
            System.out.println("\nARRIVED TO HUB...\n");
            return true;
        }

        return false;
    }

    public void message(DoubleLinkedList names30, DoubleLinkedList selectedHubNames, ArrayList<String> allmessages) {
        int randomIndex = (int) (Math.random() * (names30.getSize()));
        DoubleNode name = names30.getNode(randomIndex);
        System.out.println("Randomly choosing a student: " + name.name + "\n");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the message: ");
        String messageTo = scanner.nextLine();
        allmessages.add(messageTo);
        int RightOrLeft = (int) ((Math.random() * 2) + 1); // [1,2]

        while (!allHaveAtLeastOneMessage()) {
            int randomK = (int) ((Math.random() * 5) + 1); // [1,5]
            System.out.println("\nRandomly generating the value of k: " + randomK + "\n-----");

            System.out.println("Randomly generating a direction: " + (RightOrLeft == 1 ? "left" : "right") + "\n");

            for (int i = 0; i < randomK; i++) {
                if (RightOrLeft == 1) { // sola hareket
                    name = name.prev;
                    randomIndex--;
                    if (isHubStudent(name, selectedHubNames, allmessages)) {
                        name.messageCount++;
                        displayWithSelected(selectedHubNames);
                        System.out.println("Enter your message:");
                        messageTo = scanner.nextLine();
                        allmessages.add(messageTo);
                        s1.messageCharacter(messageTo, name.name, names30, randomIndex);
                        break;
                    }
                    if (name == names30.first) {
                        name.messageCount++;
                        if (allHaveAtLeastOneMessage()) {
                             s1.messageCharacter(messageTo, name.name, names30, randomIndex);
                            break;
                        }
                        displayWithSelected(selectedHubNames);
                        System.out.println("You've come to the first");
                        System.out.println("Enter new message:");
                        messageTo = scanner.nextLine();
                        allmessages.add(messageTo);
                        s1.messageCharacter(messageTo, name.name, names30, randomIndex);
                        RightOrLeft = 2; // Sağa git
                        break;
                    }

                    name.messageCount++;
                    s1.messageCharacter(messageTo, name.name, names30, randomIndex);

                } else { // sağa hareket
                    name = name.next;
                    randomIndex++;
                    if (isHubStudent(name, selectedHubNames, allmessages)) {
                        name.messageCount++;
                        displayWithSelected(selectedHubNames);
                        System.out.println("Enter your message:");
                        messageTo = scanner.nextLine();
                        allmessages.add(messageTo);
                        s1.messageCharacter(messageTo, name.name, names30, randomIndex);
                        break;
                    }

                    if (name == names30.last) { // Son elemana ulaşıldığında
                        name.messageCount++;
                        if (allHaveAtLeastOneMessage()) {
                             s1.messageCharacter(messageTo, name.name, names30, randomIndex);
                            break;
                        }
                        displayWithSelected(selectedHubNames);
                        System.out.println("You've come to the last");
                        System.out.println("Enter new message:");
                        messageTo = scanner.nextLine();
                        allmessages.add(messageTo);
                        s1.messageCharacter(messageTo, name.name, names30, randomIndex);

                        RightOrLeft = 1; // Sola git
                        break;
                    }

                    name.messageCount++;
                    s1.messageCharacter(messageTo, name.name, names30, randomIndex);
                }
            }
            displayWithSelected(selectedHubNames);
        }

        System.out.println("All students have received at least one message.");
        System.out.println("\n\t-All Messages-\n");
        for (int i = 0; i < allmessages.size(); i++) {
            System.out.println("\t" + (i + 1) + ") " + allmessages.get(i));
        }
        System.out.println("");
        System.out.println("\t-Char List-\n");
        for (int i = 0; i < names30.getSize(); i++) {
            System.out.println("\t" + (i + 1) + ") " + names30.getNode(i).name + " => " + names30.getNode(i).matchingCharacter);
        }
    }

}
