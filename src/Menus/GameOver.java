package Menus;

import Levels.*;
import Main.Main;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameOver extends Scene { // écran game over

    private static Image bg = new Image("Images/GameElements/gameover.gif");
    private static ImageView[] images = new ImageView[1];
    private static int levelNumber;

    public static Scene create(int level) {
        levelNumber = level;
        return new GameOver(group());
    }

    private GameOver(Parent root) {
        super(root);
    }

    private static Group group() {
        images[0] = new ImageView(bg);

        Rectangle rect = new Rectangle(1203, 350, 291, 100);
        rect.setFill(Color.TRANSPARENT);

        rect.setOnMouseClicked(event -> { // bouton retry (recommence le niveau)
            switch (levelNumber) {
                case 0 :
                    Main.setScene(One.create());
                    break;
                case 1 :
                    Main.setScene(Two.create());
                    break;
                case 2 :
                    Main.setScene(Three.create());
                    break;
                case 3 :
                    Main.setScene(Four.create());
                    break;
            }
        });

        Rectangle rect2 = new Rectangle(1223, 510, 241, 100);
        rect2.setFill(Color.TRANSPARENT);

        rect2.setOnMouseClicked(event -> Main.setScene(LevelSelect.create())); // bouton exit (quitte le niveau)

        return new Group(images[0], rect, rect2);
    }
}
