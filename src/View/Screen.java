package View;
import javax.swing.*;
import Model.*;

import java.awt.*;
import java.awt.event.*;

public class screen extends JFrame {
    protected ActionListener ac;
    protected Lesson lesson; // Biến lesson để lưu thông tin bài học hiện tại

    // Constructor cho lớp cơ sở
    public screen() {
        this.setTitle("Flashcard App");
        this.setSize(800, 600);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.darkGray);

        setupMenuBar(); // Phần chung cho tất cả các màn hình

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false); // Ẩn mặc định
    }

    // MenuBar - phần chung cho tất cả các màn hình
    protected void setupMenuBar() {
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

        // Thêm chức năng quay về trang index từ menu Home
        JMenuItem jMenuItem_home_index = new JMenuItem("Home Screen");
        jMenuItem_home_index.addActionListener(e -> openIndexScreen(lesson));
        jMenu_home.add(jMenuItem_home_index);
        
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

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
    
    // Phương thức để mở màn hình Index
    protected void openIndexScreen(Lesson lesson) {
        this.setVisible(false);
        EventQueue.invokeLater(() -> {
            // Tạo một instance mới của index và hiển thị
            screen screen = new index();
            screen.setLesson(lesson); // Set lesson to the screen
            setVisible(true);
        });
    }
}