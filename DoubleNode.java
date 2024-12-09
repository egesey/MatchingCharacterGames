package Comp2112.SecondPart;

class DoubleNode {

    String name;
    DoubleNode next;
    DoubleNode prev;
    int messageCount;
    int matchingCharacter;
 
    public DoubleNode(String name) {
        this.name = name;
        this.next = null;
        this.prev = null;
        this.messageCount = 0;
        this.matchingCharacter = 0;
    }

    public int setmatchingCharacter(int matchingCharacter) {
        return this.matchingCharacter += matchingCharacter;
    }

    public int getmatchingCharacter() {
        return matchingCharacter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
