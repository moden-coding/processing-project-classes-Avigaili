import java.util.ArrayList;

import processing.core.*;

public class App extends PApplet{

    double countTime;
    int scene;
    double highScore;
    boolean roundOver = false;
    


    public static void main(String[] args)  {
        PApplet.main("App");
    }

    public void setup(){
        background(135, 206, 235);
     pipes = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            pipesIn();
        }
         scene = 0;
        countTime = millis();
    }

    public void settings(){

    size(1200, 800);
        
    }

    public void draw(){
       

    }
}
