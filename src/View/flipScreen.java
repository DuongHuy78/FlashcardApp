package View;
import javax.swing.*;
import Model.*;
import Controller.FlashCardListener;

import java.awt.*;

public class flipScreen extends screen{
    CardFlipPanel cardFlipPanel = new CardFlipPanel();

    public flipScreen() {
        super();
        setupCardPanel(); // Thiết lập phần riêng của màn hình này
        setupButtons(); // Thiết lập các nút
    }

    protected void setupButtons() {
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

        // Đặt nội dung mặc định để tránh lỗi null
        cardFlipPanel.setQuestionContent("");
        cardFlipPanel.setAnswerContent("");

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
        // // Khiến scrollPane cập nhật lại trạng thái hiển thị của thanh cuộn
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

        Component southComponent = ((BorderLayout)getContentPane().getLayout()).getLayoutComponent(BorderLayout.SOUTH);
        if (southComponent instanceof JPanel) {
            JPanel buttonPanel = (JPanel)southComponent;
            // Thêm action listeners cho tất cả các nút trong panel
            for (Component comp : buttonPanel.getComponents()) {
                if (comp instanceof JButton) {
                    JButton btn = (JButton)comp;
                    btn.addActionListener(ac);
                }
            }
        }
    }

    public void open() {
        this.setVisible(true);
    }

    public void close() {
        this.setVisible(false);
    }
}