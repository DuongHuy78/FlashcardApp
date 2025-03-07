package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CardFlipPanel extends JPanel {
    private String questionContent;
    private String answerContent;
    private boolean isShowQuestion = true;
    private Timer timer;
    private int animationSteps = 20;
    private int currentStep = 0;

    public CardFlipPanel() {
        // setLayout(new CardLayout());
        // this.isShowQuestion = true;

        
        // this.answerPanel = new JPanel();
        // answerPanel.setLayout(new BorderLayout());
        // answerPanel.setBackground(Color.WHITE);
        // JLabel answerLabel = new JLabel("back");
        // answerLabel.setFont(new Font("Arial", Font.BOLD, 40));
        // answerLabel.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa text theo chiều ngang
        // answerLabel.setVerticalAlignment(SwingConstants.CENTER);   // Căn giữa text theo chiều dọc
        // answerPanel.add(answerLabel);

        // this.add(questionPanel, "question");
        // this.add(answerPanel, "answer");
        timer = new Timer(10, e -> animateFlip());

        setBackground(Color.lightGray);

    }

    public void setQuestionContent(String front) {
        questionContent = front;
        repaint();      //Yêu cầu hệ thống vẽ lại thành phần, từ đó tự động gọi paintComponent(Graphics g).
    }

    public void setAnswerContent(String front) {
        answerContent = front;
        repaint();      //Yêu cầu hệ thống vẽ lại thành phần, từ đó tự động gọi paintComponent(Graphics g).
    }

    public void flip() {
        currentStep = 0;
        timer.start();
    }

    private void animateFlip() {
        currentStep++;
        repaint();      //Yêu cầu hệ thống vẽ lại thành phần, từ đó tự động gọi paintComponent(Graphics g).
                        //Mỗi lần Timer chạy (15ms một lần), phương thức animateFlip() sẽ được gọi, 
                        //nó cập nhật giá trị scale, rồi gọi repaint(). 
                        //Khi đó, paintComponent(Graphics g) được Swing gọi để vẽ lại giao diện với hiệu ứng mới.
        if(currentStep == animationSteps / 2) {
            // CardLayout temp = (CardLayout) this.getLayout();
            // if(isShowQuestion) {
            //     temp.show(this, "answer");
            // }
            // else {
            //     temp.show(this, "question");
            // }
            isShowQuestion = !isShowQuestion;
        }

        if(currentStep >= animationSteps) {
            timer.stop();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g.create();
        
        // Tạo hiệu ứng co đối tượng (text)
        double scale = Math.abs(Math.cos(Math.PI * currentStep / animationSteps));
        int w = getWidth();
        int h = getHeight();
        
        // Di chuyển gốc tọa độ đến giữa panel
        if(timer.isRunning()) {
            g2d.translate(w/2, h/2);
        
            // Co giãn theo trục X
            g2d.scale(scale, scale);
            
            // Di chuyển ngược lại
            g2d.translate(-w/2, -h/2);
        }

        String text;
        if(isShowQuestion) {
            text = questionContent;
        }
        else {
            text = answerContent;
        }
        // Vẽ text ở giữa panel
        g2d.setFont(new Font("Arial", Font.BOLD, 40));
        FontMetrics fm = g2d.getFontMetrics(); // Để lấy kích thước của font
        int x = (w - fm.stringWidth(text)) / 2; // Căn giữa theo chiều ngang lấy width của Panel trừ đi width của text rồi chia 2
        // Căn giữa theo chiều dọc
        // lấy ascent() của font cộng với height của Panel trừ đi ascent và descent của font rồi chia 2
        int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2); 
        g2d.drawString(text, x, y);
        g2d.dispose();
    }
}
