package View;
import View.CardFlipPanel;
import javax.swing.*;
import Model.Lesson;

import java.awt.*;
import java.awt.event.*;

public class Screen extends JFrame{
    private Lesson lesson;
    public Screen() {
        this.lesson = new Lesson();
        this.setTitle("Flashcard App");
        this.setSize(800, 600);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.darkGray);

        CardFlipPanel cardFlipPanel = new CardFlipPanel();
        
        cardFlipPanel.setQuestionContent("Hello");
        cardFlipPanel.setAnswerContent("you are success");
        
        // JPanel centralPanel = new JPanel();
        // centralPanel.setLayout(new BorderLayout());
        // centralPanel.setBackground(Color.LIGHT_GRAY);
        // centralPanel.setPreferredSize(new Dimension(500,300));
        // centralPanel.add(cardFlipPanel, BorderLayout.CENTER);
        this.add(cardFlipPanel, BorderLayout.CENTER);


        JButton button_Swap = new JButton("Swap");
        JButton button_next = new JButton("Next");
        JButton button_previous = new JButton("Previous");

        button_Swap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardFlipPanel.flip();
            }
        });

        JPanel panel_button = new JPanel();
        panel_button.setBackground(Color.darkGray);
        panel_button.setLayout(new GridLayout(1, 3, 10, 0));  // Căn giữa các nút
    
        panel_button.add(button_next);
        panel_button.add(button_Swap);
        panel_button.add(button_previous);

        Dimension buttonSize = new Dimension(100, 50);
        button_Swap.setPreferredSize(buttonSize);
        // button_Swap.setBackground(Color.GRAY);
        // //set lại vì Feel and Look sẽ làm mất màu nền
        // button_Swap.setOpaque(true);
        // button_Swap.setBorderPainted(false);

        button_next.setPreferredSize(buttonSize);
        button_previous.setPreferredSize(buttonSize);

        panel_button.setBorder(BorderFactory.createEmptyBorder(20, 10, 40, 10));  // top, left, bottom, right
        // Thêm panel nút vào vị trí SOUTH (dưới cùng)
        this.add(panel_button, BorderLayout.SOUTH);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            new Screen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
