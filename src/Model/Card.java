package Model;

public class Card {
    private int id;
    private String quesion;
    private String answer;

    public Card() {
        this.quesion = "";
        this.answer = "";
        this.id = 0;
    }

    public Card(String question, String answer, int id) {
        this.quesion = question;
        this.answer = answer;
        this.id = id;
    }

    public String getQuesion() {
        return quesion;
    }

    public void setQuesion(String quesion) {
        this.quesion = quesion;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getId() {
        return this.id;
    }

    public void show() {
        System.out.println(this.quesion + "   "+ this.answer);
    }
}
