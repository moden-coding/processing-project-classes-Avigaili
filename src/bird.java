import processing.core.PApplet;

public class bird {
    private float x;
    private float y;
    private PApplet canvas;
    private float vy; // vertical velocity because basically I needed to know how to to make it so the
                      // bird moves up and down

    private float jumpStrength; // this is just like vy but negitive so it makes you more up instead of down so
                                // more times you click bigger the jump

    private float gravity; // Thank you Dr. Moden's youtube channel for ur help on this
    private float size;

    // makes the birds starter place
    public bird(float xPos, float yPos, PApplet c) {
        x = xPos;
        y = yPos;
        canvas = c;
        size = 40;
        gravity = 0.8f; // btw f just means that this is a float
        jumpStrength = 12;
        vy = 0;

    }

    public void display() {
        canvas.fill(252, 239, 145);
        canvas.circle(x, y, size);

    }

    // updating where the bird is using the vy and gravity
    public void update() {
        vy += gravity;
        y += vy;

    }

    public void flap() {
        vy = -jumpStrength; // jump up
    }

    public boolean hitsWall() {
        return (y - size / 2 < 0 || y + size / 2 > canvas.height);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getRadius() {
        return size / 2;
    }

}
