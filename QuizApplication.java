import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

// Define a class to represent a quiz question
class QuizQuestion {
    String questionText;
    String optionA;
    String optionB;
    String optionC;
    String optionD;
    String correctAnswer;

    // Constructor to create a new quiz question
    public QuizQuestion(String questionText, String optionA, String optionB, String optionC, String optionD, String correctAnswer) {
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
    }
}

// Define the main quiz application class
public class QuizApplication {
    ArrayList<QuizQuestion> quizQuestions;
    int currentQuestionIndex;
    int score;
    Timer timer;
    ArrayList<String> userAnswers;

    // Constructor to create a new quiz application
    public QuizApplication() {
        this.quizQuestions = new ArrayList<>();
        this.currentQuestionIndex = 0;
        this.score = 0;
        this.userAnswers = new ArrayList<>();
    }

    // Method to add a new quiz question
    public void addQuizQuestion(String questionText, String optionA, String optionB, String optionC, String optionD, String correctAnswer) {
        quizQuestions.add(new QuizQuestion(questionText, optionA, optionB, optionC, optionD, correctAnswer));
    }

    // Method to start the quiz
    public void startQuiz() {
        currentQuestionIndex = 0;
        score = 0;
        userAnswers.clear();
        displayNextQuestion();
    }

    // Method to display the next question
    public void displayNextQuestion() {
        if (currentQuestionIndex < quizQuestions.size()) {
            QuizQuestion currentQuestion = quizQuestions.get(currentQuestionIndex);

            // Display the question and options
            System.out.println("Question " + (currentQuestionIndex + 1) + ": " + currentQuestion.questionText);
            System.out.println("A) " + currentQuestion.optionA);
            System.out.println("B) " + currentQuestion.optionB);
            System.out.println("C) " + currentQuestion.optionC);
            System.out.println("D) " + currentQuestion.optionD);

            // Set a timer for 30 seconds
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Time's up!");
                    displayNextQuestion();
                }
            }, 30 * 1000);

            // Get the user's answer
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your answer (A, B, C, or D): ");
            String userAnswer = scanner.next();

            // Store the user's answer
            userAnswers.add(userAnswer);

            // Check if the answer is correct
            if (userAnswer.equalsIgnoreCase(currentQuestion.correctAnswer)) {
                score++;
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect. The correct answer is " + getCorrectOption(currentQuestion));
            }

            // Move on to the next question
            currentQuestionIndex++;
            displayNextQuestion();
        } else {
            displayResultScreen();
        }
    }

    // Method to display the result screen
    public void displayResultScreen() {
        System.out.println("Quiz Complete!");
        System.out.println("Your score: " + score + "/" + quizQuestions.size());
        System.out.println("Correct answers: ");
        for (int i = 0; i < quizQuestions.size(); i++) {
            QuizQuestion question = quizQuestions.get(i);
            String userAnswer = userAnswers.get(i);
            if (userAnswer.equalsIgnoreCase(question.correctAnswer)) {
                System.out.println((i + 1) + ". " + question.questionText + " - Correct");
            } else {
                System.out.println((i + 1) + ". " + question.questionText + " - Incorrect. The correct answer is " + getCorrectOption(question));
            }
        }
    }

    // Method to get the correct option for a question
    public String getCorrectOption(QuizQuestion question) {
        if (question.correctAnswer.equals("A")) {
            return question.optionA;
        } else if (question.correctAnswer.equals("B")) {
            return question.optionB;
        } else if (question.correctAnswer.equals("C")) {
            return question.optionC;
        } else {
            return question.optionD;
        }
    }

    // Main method to start the quiz
    public static void main(String[] args) {
        QuizApplication quizApp = new QuizApplication();

        // Add some quiz questions
        quizApp.addQuizQuestion("What is the capital of France?", "Paris", "Berlin","Rome","Itlay","A");
        quizApp.addQuizQuestion("What is the largest planet in our solar system?", "Earth", "Saturn", "Jupiter", "Uranus", "C");
        quizApp.addQuizQuestion("What is the smallest country in the world?", "Vatican City", "Monaco", "Nauru", "Tuvalu", "A");

         quizApp.startQuiz();
    }
}