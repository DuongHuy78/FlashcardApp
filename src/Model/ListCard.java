package Model;

import java.util.LinkedList;

public class ListCard {
    private String name;
    private LinkedList<Card> listCard;
    private int Length;
    private int currentIndex;

    public ListCard(String newName) {
        this.name = newName;
        currentIndex = 0;
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

    public LinkedList<Card> getListCard() {
        return this.listCard;
    }

    public void setListCard(LinkedList<Card> listCard) {
        this.listCard = listCard;
    }

    public int getLength() {
        return this.Length;
    }

    public String getQuestion() {
        return this.listCard.get(currentIndex).getQuesion();
    }

    public String getAnswer() {
        return this.listCard.get(currentIndex).getAnswer();
    }

    public void nextCard() {
        if(currentIndex == this.Length - 1) {
            currentIndex = 0;
        } else {
            currentIndex++;
        }
    }

    public void previousCard() {
        if(currentIndex == 0) {
            currentIndex = this.Length - 1;
        } else {
            currentIndex--;
        }
    }

    // public String getNextQuesion() {
    //     if(currentIndex == this.Length - 1) {
    //         currentIndex = 0;
    //     } else {
    //         currentIndex++;
    //     }
    //     return this.listCard.get(currentIndex).getQuesion();
    // }

    // public String getNextAnswer() {
    //     if(currentIndex == this.Length - 1) {
    //         currentIndex = 0;
    //     } else {
    //         currentIndex++;
    //     }
    //     return this.listCard.get(currentIndex).getAnswer();
    // }

    // public String getPreviousQuesion() {
    //     if(currentIndex == 0) {
    //         currentIndex = this.Length - 1;
    //     } else {
    //         currentIndex--;
    //     }
    //     return this.listCard.get(currentIndex).getQuesion();
    // }

    // public String getPreviousAnswer() {
    //     if(currentIndex == 0) {
    //         currentIndex = this.Length - 1;
    //     } else {
    //         currentIndex--;
    //     }
    //     return this.listCard.get(currentIndex).getAnswer();
    // }

    public void show() {
        for(Card next : this.listCard) {
            next.show();
        }
    }
}
