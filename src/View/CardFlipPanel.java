package View;

import javax.swing.*;
import java.awt.*;

public class CardFlipPanel extends JPanel {
    private String questionContent;
    private String answerContent;
    private boolean isShowQuestion;
    private Timer timer;
    private int animationSteps = 20;
    private int currentStep = 0;

    public CardFlipPanel() {
        isShowQuestion = true;
        timer = new Timer(10, e -> animateFlip());
        setBackground(Color.lightGray);
    }

    public void setQuestionContent(String front) {
        questionContent = front;
        revalidate();
        repaint();      //Yêu cầu hệ thống vẽ lại thành phần, từ đó tự động gọi paintComponent(Graphics g).
    }

    public void setAnswerContent(String front) {
        answerContent = front;
        revalidate();
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
        int w = getWidth(); // Lấy chiều rộng của panel
        int h = getHeight(); // Lấy chiều cao của panel
        
        // Di chuyển gốc tọa độ đến giữa panel
        if(timer.isRunning()) {
            g2d.translate(w/2, h/2);
        
            // Co giãn theo trục X
            g2d.scale(scale, 1);
            
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
        int maxWidth = w - 20;
        int x;
        int y = fm.getAscent() + 10;
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();
        if(words.length == 0) {
            return;
        }
        for(String word : words) {
            if(fm.stringWidth(line + word) < maxWidth) {
                line.append(word).append(" ");
            }
            else {
                line.delete(line.length() - 1, line.length());
                x = (w - fm.stringWidth(line.toString())) / 2;
                g2d.drawString(line.toString(), x, y);
                y += fm.getHeight();
                line = new StringBuilder(word + " ");
            }
        }
        x = (w - fm.stringWidth(line.toString())) / 2;
        g2d.drawString(line.toString(), x, y);
        g2d.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        // Tính toán chiều cao cần thiết dựa trên nội dung
        Dimension size = super.getPreferredSize();
        String text = isShowQuestion ? questionContent : answerContent;
        if(text == null || text.isEmpty()) {
            return new Dimension(400, 300);
        }
        Graphics2D g2d = (Graphics2D) getGraphics();
        if(g2d == null) {
            return new Dimension(400, 300);
        }
        FontMetrics fm = g2d.getFontMetrics(new Font("Arial", Font.BOLD, 40));
        int width = getParent() != null ? getParent().getWidth() - 20 : 400; // Chiều rộng mặc định nếu chưa được vẽ
        int maxWidth = width - 20;
        int height = 20; // Padding trên
        // Tính toán chiều cao dựa trên text được chia dòng
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();
        for (String word : words) {
            if (fm.stringWidth(line + word) < maxWidth) {
                line.append(word).append(" ");
            } else {
                height += fm.getHeight();
                line = new StringBuilder(word + " ");
            }
        }
        // Thêm chiều cao cho dòng cuối
        height += fm.getHeight() + 20; // Thêm padding dưới
        
        size.height = height;
        return size;
    }
}
