import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizApp extends JFrame implements ActionListener {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JTextField usernameField;
    private JLabel questionLabel, scoreLabel, timerLabel;
    private JButton[] optionButtons;
    private JButton startButton, exitButton;
    private Timer timer;
    private int currentQuestion = 0, score = 0, timeRemaining = 30;
    private String username;

    private String[][] questions = new String[30][6]; // Array to hold 30 questions

    public QuizApp() {
        setTitle("Quiz Application");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        createQuestions(); // Initialize questions
        createUsernamePanel();
        createMenuPanel();
        createQuizPanel();
        createScorePanel();

        add(mainPanel);
        setVisible(true);
    }

    // Method to initialize the questions array with 30 questions
    private void createQuestions() {
        questions[0] = new String[]{"What is the capital of France?", "Paris", "London", "Berlin", "Rome", "Paris"};
        questions[1] = new String[]{"Which planet is known as the Red Planet?", "Earth", "Mars", "Jupiter", "Venus", "Mars"};
        questions[2] = new String[]{"Who wrote 'Hamlet'?", "Charles Dickens", "J.K. Rowling", "William Shakespeare", "Mark Twain", "William Shakespeare"};
        questions[3] = new String[]{"What is the largest mammal?", "Elephant", "Blue Whale", "Giraffe", "Shark", "Blue Whale"};
        questions[4] = new String[]{"What is the boiling point of water?", "90°C", "80°C", "100°C", "120°C", "100°C"};
        questions[5] = new String[]{"Which element has the atomic number 1?", "Oxygen", "Hydrogen", "Helium", "Carbon", "Hydrogen"};
        questions[6] = new String[]{"What is the chemical symbol for gold?", "Au", "Ag", "Pb", "Pt", "Au"};
        questions[7] = new String[]{"Which planet is closest to the Sun?", "Earth", "Venus", "Mercury", "Mars", "Mercury"};
        questions[8] = new String[]{"What is the largest organ in the human body?", "Heart", "Skin", "Liver", "Lungs", "Skin"};
        questions[9] = new String[]{"Who painted the Mona Lisa?", "Vincent Van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Claude Monet", "Leonardo da Vinci"};
        questions[10] = new String[]{"Which country is known as the Land of the Rising Sun?", "China", "South Korea", "Japan", "Thailand", "Japan"};
        questions[11] = new String[]{"What is the smallest prime number?", "1", "2", "3", "5", "2"};
        questions[12] = new String[]{"Which planet has the most moons?", "Earth", "Mars", "Jupiter", "Saturn", "Jupiter"};
        questions[13] = new String[]{"What is the hardest natural substance?", "Iron", "Gold", "Diamond", "Quartz", "Diamond"};
        questions[14] = new String[]{"Who invented the telephone?", "Albert Einstein", "Alexander Graham Bell", "Isaac Newton", "Nikola Tesla", "Alexander Graham Bell"};
        questions[15] = new String[]{"Which is the largest ocean on Earth?", "Atlantic", "Indian", "Arctic", "Pacific", "Pacific"};
        questions[16] = new String[]{"What is the powerhouse of the cell?", "Nucleus", "Mitochondria", "Ribosome", "Cytoplasm", "Mitochondria"};
        questions[17] = new String[]{"What is the tallest mountain in the world?", "K2", "Kangchenjunga", "Mount Everest", "Makalu", "Mount Everest"};
        questions[18] = new String[]{"What gas do plants absorb from the atmosphere?", "Oxygen", "Carbon Dioxide", "Nitrogen", "Hydrogen", "Carbon Dioxide"};
        questions[19] = new String[]{"Who discovered penicillin?", "Marie Curie", "Alexander Fleming", "Louis Pasteur", "Thomas Edison", "Alexander Fleming"};
        questions[20] = new String[]{"Which continent is the Sahara Desert located?", "Asia", "South America", "Australia", "Africa", "Africa"};
        questions[21] = new String[]{"Which is the smallest country in the world?", "Monaco", "San Marino", "Vatican City", "Liechtenstein", "Vatican City"};
        questions[22] = new String[]{"What is the main ingredient in guacamole?", "Tomato", "Avocado", "Onion", "Pepper", "Avocado"};
        questions[23] = new String[]{"Which planet is known as the Blue Planet?", "Mars", "Earth", "Neptune", "Uranus", "Earth"};
        questions[24] = new String[]{"Who was the first President of the United States?", "Thomas Jefferson", "George Washington", "Abraham Lincoln", "John Adams", "George Washington"};
        questions[25] = new String[]{"Which element has the atomic number 6?", "Carbon", "Oxygen", "Nitrogen", "Hydrogen", "Carbon"};
        questions[26] = new String[]{"Which is the longest river in the world?", "Amazon", "Nile", "Yangtze", "Mississippi", "Nile"};
        questions[27] = new String[]{"Which country invented pizza?", "France", "USA", "Greece", "Italy", "Italy"};
        questions[28] = new String[]{"What is the freezing point of water?", "0°C", "10°C", "32°C", "100°C", "0°C"};
        questions[29] = new String[]{"What is the speed of light?", "300,000 km/s", "150,000 km/s", "450,000 km/s", "600,000 km/s", "300,000 km/s"};
    }

    private void createUsernamePanel() {
        JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel nameLabel = new JLabel("Enter Username:");
        usernameField = new JTextField(20);

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {
            username = usernameField.getText().trim();
            if (!username.isEmpty()) {
                cardLayout.show(mainPanel, "Menu");
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a username.");
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        usernamePanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        usernamePanel.add(usernameField, gbc);
        gbc.gridy = 1;
        usernamePanel.add(nextButton, gbc);

        mainPanel.add(usernamePanel, "Username");
    }

    private void createMenuPanel() {
        JPanel menuPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        startButton = new JButton("Start Quiz");
        exitButton = new JButton("Exit");

        startButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "Quiz");
            startQuiz();
        });

        exitButton.addActionListener(e -> System.exit(0));

        gbc.gridx = 0;
        gbc.gridy = 0;
        menuPanel.add(startButton, gbc);
        gbc.gridy = 1;
        menuPanel.add(exitButton, gbc);

        mainPanel.add(menuPanel, "Menu");
    }

    private void createQuizPanel() {
        JPanel quizPanel = new JPanel(new BorderLayout());

        questionLabel = new JLabel("Question", JLabel.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        quizPanel.add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        optionButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JButton();
            optionButtons[i].addActionListener(this);
            optionsPanel.add(optionButtons[i]);
        }

        timerLabel = new JLabel("Time left: 30 seconds", JLabel.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        quizPanel.add(timerLabel, BorderLayout.SOUTH);

        quizPanel.add(optionsPanel, BorderLayout.CENTER);

        mainPanel.add(quizPanel, "Quiz");
    }

    private void createScorePanel() {
        JPanel scorePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        scoreLabel = new JLabel("Your Score: ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        scorePanel.add(scoreLabel, gbc);

        JButton finishButton = new JButton("Finish");
        finishButton.addActionListener(e -> System.exit(0));
        gbc.gridy = 1;
        scorePanel.add(finishButton, gbc);

        mainPanel.add(scorePanel, "Score");
    }

    private void startQuiz() {
        currentQuestion = 0;
        score = 0;
        timeRemaining = 30;
        startTimer();
        displayQuestion();
    }

    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeRemaining--;
                timerLabel.setText("Time left: " + timeRemaining + " seconds");
                if (timeRemaining <= 0) {
                    timer.stop();
                    currentQuestion++;
                    displayQuestion();
                }
            }
        });
        timer.start();
    }

    private void displayQuestion() {
        if (currentQuestion < questions.length) {
            timeRemaining = 30;
            timerLabel.setText("Time left: " + timeRemaining + " seconds");
            timer.restart();

            String[] question = questions[currentQuestion];
            questionLabel.setText("Q" + (currentQuestion + 1) + ": " + question[0]);
            for (int i = 0; i < 31; i++) {
                optionButtons[i].setText(question[i + 1]);
            }
        } else {
            timer.stop();
            showScore();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton selectedButton = (JButton) e.getSource();
        String selectedAnswer = selectedButton.getText();
        if (selectedAnswer.equals(questions[currentQuestion][5])) {
            score++;
        }
        timer.stop();
        currentQuestion++;
        displayQuestion();
    }

    private void showScore() {
        String resultMessage = (score >= 12) ? "Congratulations! You passed!" : "You failed. Better luck next time.";
        scoreLabel.setText("<html>Your Score: " + score + "/" + questions.length + "<br>" + resultMessage + "</html>");
        cardLayout.show(mainPanel, "Score");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(QuizApp::new);
    }
}
