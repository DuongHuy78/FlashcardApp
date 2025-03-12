package Controller;
import java.awt.event.*;
import Model.*;
import View.*;
public class FlashCardListener implements ActionListener {
    private Screen screen;
    private ListCard listCard;
    public FlashCardListener(Screen screen, ListCard listCard) {
        this.screen = screen;
        this.listCard = listCard;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Next")) {
            listCard.nextCard(); // First move to next card
            screen.setQuestionContent(listCard.getQuestion());
            screen.setAnswerContent(listCard.getAnswer());
        } else if(e.getActionCommand().equals("Previous")) {
            listCard.previousCard(); // First move to previous card
            screen.setQuestionContent(listCard.getQuestion());
            screen.setAnswerContent(listCard.getAnswer());
        }
    }

}