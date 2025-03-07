package test;

import Model.ListCard;
import Model.Card;
import Model.Lesson;

public class test {
    public static void main(String[] args) {
        Lesson lesson = new Lesson();

        ListCard listCard = new ListCard("Capital");
        listCard.addCard(new Card("What is the capital of France?", "Paris",1));
        listCard.addCard(new Card("What is the capital of Germany?", "Berlin",2));
        listCard.addCard(new Card("What is the capital of Italy?", "Rome",3));
        listCard.addCard(new Card("What is the capital of Spain?", "Madrid",4));
        listCard.addCard(new Card("What is the capital of Portugal?", "Lisbon",5));
        listCard.addCard(new Card("What is the capital of the United Kingdom?", "London",6));

        ListCard listCard2 = new ListCard("Math");
        listCard2.addCard(new Card("What is 2+2?", "4",1));
        listCard2.addCard(new Card("What is 3+3?", "6",2));
        listCard2.addCard(new Card("What is 4+4?", "8",3));
        listCard2.addCard(new Card("What is 5+5?", "10",4));
        listCard2.addCard(new Card("What is 6+6?", "12",5));
        listCard2.addCard(new Card("What is 7+7?", "14",6));

        lesson.addListCard(listCard);
        lesson.addListCard(listCard2);
        lesson.show();
    }
}
