package View;
import javax.swing.*;
import Model.Lesson;
import Model.ListCard;
import Controller.FlashCardListener;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class Screen extends JFrame{
    private ActionListener ac;
    CardFlipPanel cardFlipPanel = new CardFlipPanel();

    public Screen() {
        this.setTitle("Flashcard App");
        this.setSize(800, 600);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.darkGray);

        JScrollPane scrollPane = new JScrollPane(cardFlipPanel);
        // hàm này sẽ tự động thêm thanh cuộn vào panel nếu cần
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Tăng tốc độ cuộn
        this.add(scrollPane, BorderLayout.CENTER);


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

    public void setQuestionContent(String question) {
        cardFlipPanel.setQuestionContent(question);
        SwingUtilities.invokeLater(() -> {
            JScrollPane scrollPane = (JScrollPane)cardFlipPanel.getParent().getParent();
            scrollPane.getVerticalScrollBar().setValue(0);
        });
    }

    public void setAnswerContent(String answer) {
        cardFlipPanel.setAnswerContent(answer);
        SwingUtilities.invokeLater(() -> {
            JScrollPane scrollPane = (JScrollPane)cardFlipPanel.getParent().getParent();
            scrollPane.getVerticalScrollBar().setValue(0);
        });
    }

    public void setActionListener(ListCard listCard) {
        this.ac = new FlashCardListener(this, listCard);

        for (Component comp : ((JPanel)this.getContentPane().getComponent(1)).getComponents()) {
            if (comp instanceof JButton) {
                JButton btn = (JButton)comp;
                if (btn.getText().equals("Next") || btn.getText().equals("Previous")) {
                    btn.addActionListener(ac);
                }
            }
        }
    }
}
