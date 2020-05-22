package Obstacles;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Wall extends ImageView {

    public Wall(int x, int y, Level level) {
        setImage(new Image("Images/GameElements/wall.png"));
        setX(x);
        setY(y);

        for (int i=0;i+x<96+x;i++) { // coordonnées = mur
            for (int j=0;j+y<96+y;j++) {
                level.getCoordonate()[i+x][j+y] = "wall";
            }
        }
    }
}
