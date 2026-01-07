import processing.core.PApplet;

public class bird {
    private int x;
    private int y;
    private PApplet canvas;
    private int speed;
    private int color;
    private int size;

    public bird(int xPos, int yPos, PApplet c) {
        x = xPos;
        y = yPos;
        canvas = c;
        speed = 5;
        size = 250;

    }

    public bird(String line, PApplet c) {
        String[] parts = line.split(",");
        x = Integer.valueOf(parts[0]);
        y = Integer.valueOf(parts[1]);
        speed = Integer.valueOf(parts[2]);
        canvas = c;
        size = 250;

    }

    public void display() {
        canvas.fill(252, 239, 145);
        canvas.circle(x, y, size);

    }

    public void update() {
        x += speed;
        if (x + size / 2 > canvas.width || x - size / 2 < 0) {
            speed = -speed;

        }

    }

    public boolean checkTouch(int mouseX, int mouseY) {
        float distanceFromCenter = canvas.dist(x, y, mouseX, mouseY);
        if (distanceFromCenter < size / 2) {
            // remove but figure out how
            return false;
        }
        else{
            return true;
        }
    }
}
