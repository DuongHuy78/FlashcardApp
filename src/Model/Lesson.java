package Model;

import java.util.LinkedList;

public class Lesson {
    private LinkedList<ListCard> listCards;
    private int Length;

    public Lesson() {
        this.listCards = new LinkedList<>();
        this.Length = 0;
    }

    public void addListCard(ListCard newListCard) {
        listCards.add(newListCard);
        this.Length++;
    }

    public void removeListCard(int index) {
        listCards.remove(index);
        this.Length--;
    }

    public LinkedList<ListCard> getListCards() {
        return this.listCards;
    }

    public int getLength() {
        return this.Length;
    }

    public void show() {
        for(ListCard next : this.listCards) {
            next.show();
        }
    }
}
