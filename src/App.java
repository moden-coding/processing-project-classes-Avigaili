
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.*;

public class App extends PApplet {
    private bird b;
    private ArrayList<Pipe> pipes;
    private int scene; // 0 = start, 1 = playing, 2 = game over
    private int score;
    private int highScore;
    private int countTime = 1200; // its like how long till the next pipe thing
    private int lastSpawnTime;
    private boolean roundOver = false;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {

        textAlign(CENTER, CENTER);
        resetGame();
        scene = 0;
        readHighScore();
    }

    public void settings() {

        size(1200, 800);

    }

    public void draw() {
        background(135, 206, 235);

        if (scene == 0) {
            startScreen();
        } else if (scene == 1) {
            playGame();
        } else if (scene == 2) {
            gameOverScreen();
        }

    }

    private void playGame() {
        // learned to add more stucture like this from the last game-

        // Spawn pipes :)
        if (millis() - lastSpawnTime > countTime) {
            pipes.add(new Pipe(width, height));
            lastSpawnTime = millis();
        }

        // Update bird but i need it to update everytime so idk guys
        b.update();
        b.display();

        // Wall collision! bc if it hits then bye bye
        if (b.hitsWall()) {
            endGame();
            return;
        }
        // looping through the pipes
        for (int i = pipes.size() - 1; i >= 0; i--) {
            Pipe p = pipes.get(i);
            p.update();
            p.display(this);

            if (p.hitsBird(b)) {
                endGame();
                return;
            }

            if (p.passedBy(b)) {
                score++;
            }

            if (p.offScreen()) {
                pipes.remove(i);
            }
        }
        fill(0);
        textSize(40);
        text(score, width / 2, 60);
    }

    private void startScreen() {
        fill(0);
        textSize(60);
        text("FLAPPY BIRD", width / 2, height / 2 - 100);

        textSize(28);
        text("Press SPACE to start", width / 2, height / 2);
    }

    private void gameOverScreen() {
        fill(0);
        textSize(60);
        text("GAME OVER", width / 2, height / 2 - 100);

        textSize(30);
        text("Score: " + score, width / 2, height / 2 - 20);
        text("High Score: " + highScore, width / 2, height / 2 + 30);

        textSize(22);
        text("Press R to restart", width / 2, height / 2 + 90);
    }

    public void keyPressed() {
        if (key == ' ') {
            if (scene == 0)
                scene = 1;
            if (scene == 1)
                b.flap();
        }

        if (key == 'r' || key == 'R') {
            resetGame();
            scene = 1;
        }
    }

    private void resetGame() {
        b = new bird(width * 0.25f, height * 0.5f, this);
        // making the bird go back to where it started
        pipes = new ArrayList<>();
        score = 0;
        lastSpawnTime = millis();
    }

    private void endGame() {
        if (score > highScore) {
            highScore = score;
            saveHighScore();
        }
        scene = 2;
    }

    // Moden code from Bubbles to help with reading and saving HighScore
    public void saveHighScore() {
        try (PrintWriter writer = new PrintWriter("highscore.txt")) {
            writer.println(highScore);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public void readHighScore() {
        try (Scanner scanner = new Scanner(Paths.get("highscore.txt"))) {
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                highScore = Integer.valueOf(row);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
