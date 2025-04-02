package View;
import Model.*;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Choice;
import java.awt.event.*;

import javax.swing.JLabel;
import javax.swing.JButton;

public class index extends screen {

	private static final long serialVersionUID = 1L;
	private Choice choice;
	private JPanel contentPane;
	private flipScreen flashcardScreen;

	public index() {
		super();
		setupIndex();
	}

	private void setupIndex() {
		// Xóa layout mặc định của JFrame để có thể sử dụng null layout
        this.getContentPane().removeAll();
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);
        
        choice = new Choice();
        choice.setBounds(175, 55, 374, 45);
        contentPane.add(choice);
        
        JLabel lblNewLabel = new JLabel("Chọn bài học: ");
        lblNewLabel.setBounds(80, 55, 93, 40);
        contentPane.add(lblNewLabel);
        
        JButton btn_BatDau = new JButton("Bắt đầu");
        btn_BatDau.setBounds(175, 95, 374, 40);
        contentPane.add(btn_BatDau);
        
        JButton btn_TaoBaiHocMoi = new JButton("Tạo bài học mới");
        btn_TaoBaiHocMoi.setBounds(175, 139, 374, 40);
        contentPane.add(btn_TaoBaiHocMoi);
        
        // Xử lý sự kiện khi nhấn nút "Bắt đầu"
        btn_BatDau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedList = choice.getSelectedItem();
                if (selectedList != null && lesson != null) {
                    ListCard listCard = lesson.getListCardByName(selectedList);
                    if (listCard != null) {
                        openFlipScreen(listCard);
                    }
                }
            }
        });
        
        this.setVisible(true);
	}

	private void openFlipScreen(ListCard listCard) {
		flashcardScreen = new flipScreen();
		flashcardScreen.setQuestionContent(listCard.getQuestion());
		flashcardScreen.setAnswerContent(listCard.getAnswer());
		flashcardScreen.setActionListener(listCard);
		flashcardScreen.open();
		this.setVisible(false);
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
		for (ListCard listCard : lesson.getListCards()) {
			choice.add(listCard.getName());
		}
	}
}
