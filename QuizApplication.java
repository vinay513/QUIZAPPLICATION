import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizApplication extends JFrame {
    private JLabel questionLabel;
    private JRadioButton[] options;
    private JButton nextButton;
    private ButtonGroup buttonGroup;
    private int currentQuestionIndex;
    private int score;

    private String[][] questions = {
            {"What is the capital of France?", "Paris", "London", "Berlin", "Madrid"},
            {"What is the largest mammal?", "Elephant", "Blue Whale", "Giraffe", "Polar Bear"},
            {"Which planet is known as the Red Planet?", "Earth", "Mars", "Venus", "Jupiter"}
    };

    private String[] correctAnswers = {
            "Paris", "Blue Whale", "Mars"
    };

    public QuizApplication() {
        super("Java Quiz Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel questionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        questionLabel = new JLabel();
        questionPanel.add(questionLabel);
        add(questionPanel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        options = new JRadioButton[4];
        buttonGroup = new ButtonGroup();
        for (int i = 0; i < options.length; i++) {
            options[i] = new JRadioButton();
            optionsPanel.add(options[i]);
            buttonGroup.add(options[i]);
        }
        add(optionsPanel, BorderLayout.CENTER);

        nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (checkAnswer()) {
                    score++;
                }
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.length) {
                    loadQuestion(currentQuestionIndex);
                } else {
                    JOptionPane.showMessageDialog(QuizApplication.this, "Quiz completed! Your score: " + score);
                    System.exit(0);
                }
            }
        });
        add(nextButton, BorderLayout.SOUTH);

        currentQuestionIndex = 0;
        score = 0;
        loadQuestion(currentQuestionIndex);
    }

    private void loadQuestion(int questionIndex) {
        questionLabel.setText(questions[questionIndex][0]);
        for (int i = 0; i < options.length; i++) {
            options[i].setText(questions[questionIndex][i + 1]);
            options[i].setSelected(false);
        }
    }

    private boolean checkAnswer() {
        for (int i = 0; i < options.length; i++) {
            if (options[i].isSelected()) {
                return options[i].getText().equals(correctAnswers[currentQuestionIndex]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new QuizApplication().setVisible(true);
            }
        });
    }
}
