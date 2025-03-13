package View;
import javax.swing.*;
import Model.ListCard;
import Controller.FlashCardListener;

import java.awt.*;
import java.awt.event.*;

public class Screen extends JFrame{
    private ActionListener ac;
    CardFlipPanel cardFlipPanel = new CardFlipPanel();

    public Screen() {
        this.setTitle("Flashcard App");
        this.setSize(800, 600);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.darkGray);

        setupMenuBar();
        setupCardPanel();
        setupButtons();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setupMenuBar() {
        JMenuBar MenuBar = new JMenuBar(); 
        JMenu jMenu_home = new JMenu("Home");
        JMenu jMenu_add = new JMenu("Add");
        JMenu jMenu_edit = new JMenu("Edit");
        JMenu jMenu_delete = new JMenu("Delete");

        JMenuItem jMenuItem_add_card = new JMenuItem("Add Card");
        JMenuItem jMenuItem_add_list = new JMenuItem("Add List");
        JMenuItem jMenuItem_edit_card = new JMenuItem("Edit this Card");
        JMenuItem jMenuItem_edit_list = new JMenuItem("Edit this List");
        JMenuItem jMenuItem_delete_card = new JMenuItem("Delete this Card");
        JMenuItem jMenuItem_delete_list = new JMenuItem("Delete this List");

        jMenu_add.addSeparator(); 
        jMenu_add.add(jMenuItem_add_card);
        jMenu_add.addSeparator(); 
        jMenu_add.add(jMenuItem_add_list);
        jMenu_edit.addSeparator();
        jMenu_edit.add(jMenuItem_edit_card);
        jMenu_edit.addSeparator();
        jMenu_edit.add(jMenuItem_edit_list);
        jMenu_delete.addSeparator();
        jMenu_delete.add(jMenuItem_delete_card);
        jMenu_delete.addSeparator();
        jMenu_delete.add(jMenuItem_delete_list);
        
        MenuBar.add(jMenu_home);
        MenuBar.add(jMenu_add);
        MenuBar.add(jMenu_edit);
        MenuBar.add(jMenu_delete);

        this.setJMenuBar(MenuBar);
    }

    private void setupButtons() {
        JButton button_Swap = new JButton("Swap");
        JButton button_next = new JButton("Next");
        JButton button_previous = new JButton("Previous");

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
    }

    private void setupCardPanel() {
        cardFlipPanel = new CardFlipPanel();
        JScrollPane scrollPane = new JScrollPane(cardFlipPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Tăng tốc độ cuộn
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public void setQuestionContent(String question) {
        cardFlipPanel.setQuestionContent(question);
        SwingUtilities.invokeLater(() -> {
        // Lấy reference đến JScrollPane
        JScrollPane scrollPane = (JScrollPane)cardFlipPanel.getParent().getParent();
        // Đặt thanh cuộn về đầu
        scrollPane.getVerticalScrollBar().setValue(0);
        // Buộc tính toán lại kích thước và cập nhật UI
        cardFlipPanel.revalidate();
        scrollPane.revalidate();
        // Khiến scrollPane cập nhật lại trạng thái hiển thị của thanh cuộn
        scrollPane.repaint();
        });
    }

    public void setAnswerContent(String answer) {
        cardFlipPanel.setAnswerContent(answer);
        //đoạn này để di chuyển lên đầu mỗi trang khi chuyển thẻ
        SwingUtilities.invokeLater(() -> {
            JScrollPane scrollPane = (JScrollPane)cardFlipPanel.getParent().getParent();
            scrollPane.getVerticalScrollBar().setValue(0);
            cardFlipPanel.revalidate();
            scrollPane.revalidate();
            scrollPane.repaint();
        });
    }
    
    public void swap() {
    	cardFlipPanel.flip();
        SwingUtilities.invokeLater(() -> {
            JScrollPane scrollPane = (JScrollPane)cardFlipPanel.getParent().getParent();
            scrollPane.getVerticalScrollBar().setValue(0);
            cardFlipPanel.revalidate();
            scrollPane.revalidate();
            scrollPane.repaint();
        });
    }

    public void setActionListener(ListCard listCard) {
        this.ac = new FlashCardListener(this, listCard);

        for (Component comp : ((JPanel)this.getContentPane().getComponent(1)).getComponents()) {
            if (comp instanceof JButton) {
                JButton btn = (JButton)comp;
                if (btn.getText().equals("Next") || btn.getText().equals("Previous") || btn.getText().endsWith("Swap")) {
                    btn.addActionListener(ac);
                }
            }
        }
    }
}
