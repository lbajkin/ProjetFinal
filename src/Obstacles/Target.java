package Obstacles;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Target extends ImageView {

    private int x;
    private int y;
    private String color;
    static int targetXY = 40;

    public Target(int x, int y, String color, Level level) {
        if (color.equals("blue")) { // need to redraw blue target to be like red target, dont use requestedWidth and requestedHeight its really really hard to use it in the coordonate system because i dont know the dimensions of the image
            setImage(new Image("Images/GameElements/blue target.png"));
            level.setBlueRemaining(level.getBlueRemaining() + 1);
        }
        if (color.equals("red"))
            setImage(new Image("Images/GameElements/red target.png"));

        this.x = x;
        this.y = y;
        this.color = color;
        setX(x);
        setY(y);

        for (int i=0;i+x<targetXY+x;i++) {
            for (int j=0;j+y<targetXY+y;j++) { // sets coordonates of the image as a target
                level.getCoordonate()[i+x][j+y] = "target";
            }
        }
    }

    public void explosion(LaserV2 laser, Level level) {

        if (color.equals("red")) {
            setImage(new Image("Images/GameElements/red explosion.gif"));
            level.loseLife();
        }
        else if (color.equals("blue")) {
            setImage(new Image("Images/GameElements/blue explosion.gif"));
            level.setBlueRemaining(level.getBlueRemaining() - 1);
            level.winCondition();
        }

        for (int i=0;i+x<targetXY+x;i++) {
            for (int j=0;j+y<targetXY+y;j++) { // sets coordonates of the image as a target
                level.getCoordonate()[i+x][j+y] = "empty";
            }
        }

        laser.looking(level);
    }
}

/*
Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

            }
        };
        timer.schedule(timerTask, 900);
 */
