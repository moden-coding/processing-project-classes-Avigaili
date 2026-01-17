
import processing.core.PApplet;

public class Pipe {

    private static int patternIndex = 0;
    // basically this makes it to its lke a pattern so itll first be 120 then the
    // next ect and repeats

    private float x;
    private float w;
    private float topHeight;
    private float gapSize;
    private float speed;
    private int screenHeight;
    private boolean scored;

    private static final float[] GAP_PATTERNS = {
            120, 200, 280, 160, 240
    };
    // ^ lines 21-23 are chatGPT bc i had to create the pipes so they r only at
    // these certain heights and so its random

    public Pipe(int startX, int screenHeight) {
        this.screenHeight = screenHeight;
        // constuctor to make a new pipe and it starts at the right

        x = startX;
        w = 90;
        gapSize = 220;
        speed = 5;
        scored = false;

        topHeight = GAP_PATTERNS[patternIndex]; // this makes it so it goes out more (like down)
        patternIndex = patternIndex + 1;

        // this makes it so when the pattern is over it starts at 0 again
        if (patternIndex >= GAP_PATTERNS.length) {
            patternIndex = 0;
        }
    }

    public void update() {
        x = x - speed; // makes the spped of the pipe
    }

    public void display(PApplet app) {
        app.fill(34, 139, 34);
        app.noStroke();

        app.rect(x, 0, w, topHeight);

        app.rect(x, topHeight + gapSize, w, screenHeight - (topHeight + gapSize));
    }
    // the app.____ just makes the these be able to be displayed in main

    public boolean hitsBird(bird b) {
        // getRadius and getX and getY r in bird class

        float birdLeft = b.getX() - b.getRadius();
        float birdRight = b.getX() + b.getRadius();
        float birdTop = b.getY() - b.getRadius();
        float birdBottom = b.getY() + b.getRadius();

        float pipeLeft = x;
        float pipeRight = x + w;

        boolean overlapX = birdRight > pipeLeft && birdLeft < pipeRight;

        if (overlapX == false) {
            return false;
        }
        // checks if the bird overlaps the pipes and bc it isnt its false

        if (birdTop < topHeight) {
            return true;
        }
        // checking if the bird hits ONLY top pipe and is above the gap

        if (birdBottom > topHeight + gapSize) {
            return true;
        }
        // checks if its only bottom pipe and below the gap

        return false;
        // the bird is doing everything exractly right :)
    }

    public boolean passedBy(bird b) {
        if (scored == false) {
            if (b.getX() > x + w) {
                scored = true;
                return true;
            }
            // this just makes the game not count a pipe 2 times and makes it so the main
            // adds a point
        }
        return false;
        // says that the bird hasent passed the pipe or it already was scored
    }

    public boolean offScreen() {
        if (x + w < 0) {
            return true;
        }
        return false;
    }
    // removes pipe off the array list

}
