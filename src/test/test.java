package test;

import javax.swing.UIManager;
import Model.*;
import View.*;

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
        listCard.addCard(new Card("kh alk sjhfk  jashd fk hsa iuf h s sh fu i qw h uhf ijhw ui hf sh iufhs i a jh  f u a h w uh i uw hi uhi uawh f u h w k j  n sj  if h s u i h is  uh sh d d d d d d d d d d d d  d d c d d d đ hj j jiu ẹ ịe ị   ị i u n i  c i u i b i b i b i b i u n i n i n i n i i i i i i i i k k k k k k k k k k k k k k k k k ckk k k kk  k k k kk k kk k k kk k k k kk k  k k k k k k kk k k k k k k k k k k k k k k k k k k k k k k k m m m m m m m m m m m m m m m m mmmmmmmmmmmmmmmmmnnnnnnnnnnnnnnnn", "hello",7));

        ListCard listCard2 = new ListCard("Math");
        listCard2.addCard(new Card("What is 2+2?", "4",1));
        listCard2.addCard(new Card("What is 3+3?", "6",2));
        listCard2.addCard(new Card("What is 4+4?", "8",3));
        listCard2.addCard(new Card("What is 5+5?", "10",4));
        listCard2.addCard(new Card("What is 6+6?", "12",5));
        listCard2.addCard(new Card("What is 7+7?", "14",6));

        lesson.addListCard(listCard);
        // lesson.addListCard(listCard2);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            Screen screen = new Screen();
            screen.setActionListener(listCard);
            screen.setQuestionContent(listCard.getQuestion());
            screen.setAnswerContent(listCard.getAnswer());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
