package Model;

import java.util.LinkedList;

public class ListCard {
    private String name;
    private LinkedList<Card> listCard;
    private int Length;

    public ListCard(String newName) {
        this.name = newName;
        this.listCard = new LinkedList<Card>();
        this.Length = 0;
    }

    public void addCard(Card card) {
        this.listCard.add(card);
        this.Length++;
    }

    public void removeCard(int index) {
        this.listCard.remove(index);
        this.Length--;
    }

    public Card getCard(int index) {
        return this.listCard.get(index);
    }

    public void UpdateCard(int index, Card card) {
        this.listCard.set(index, card);
    }

    public void show() {
        for(Card next : this.listCard) {
            next.show();
        }
    }
}
