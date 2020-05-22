package Menus;

import Levels.One;
import Main.Main;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class TitleScreen extends Scene { // title screen

    private static Image exit = new Image("Images/MainMenu/exit button.png");
    private static Image exitHover = new Image("Images/MainMenu/exit button hover.png");
    private static Image exitClicked = new Image("Images/MainMenu/exit button clicked.png");
    private static Image level = new Image("Images/MainMenu/level button.png");
    private static Image levelHover = new Image("Images/MainMenu/level button hover.png");
    private static Image levelClicked = new Image("Images/MainMenu/level button clicked.png");
    private static Image bg = new Image("Images/MainMenu/background.png");
    private static Image play = new Image("Images/MainMenu/play button.png");
    private static Image playHover = new Image("Images/MainMenu/play button hover.png");
    private static Image playClicked = new Image("Images/MainMenu/play button clicked.png");
    private static ImageView[] images = new ImageView[4];

    public static Scene create() {
        return new TitleScreen(group());
    }

    private TitleScreen(Parent root) {
        super(root);
    }

    private static Group group() {
        images[0] = new ImageView(bg);
        images[1] = new ImageView(play);
        images[2] = new ImageView(level);
        images[3] = new ImageView(exit);

        VBox vBox = new VBox(images[1], images[2], images[3]);
        vBox.setTranslateX(1500);
        vBox.setTranslateY(400);
        vBox.setSpacing(75);

        images[1].setOnMouseClicked(event -> Main.setScene(One.create())); // bouton play (commence le premier niveau, un peu inutile)
        images[1].setOnMouseEntered(event -> images[1].setImage(playHover));
        images[1].setOnMousePressed(event -> images[1].setImage(playClicked));
        images[1].setOnMouseExited(event -> images[1].setImage(play));

        images[2].setOnMouseClicked(event -> Main.setScene(LevelSelect.create())); // bouton level (vous amène au sélécteur de niveau)
        images[2].setOnMouseEntered(event -> images[2].setImage(levelHover));
        images[2].setOnMousePressed(event -> images[2].setImage(levelClicked));
        images[2].setOnMouseExited(event -> images[2].setImage(level));

        images[3].setOnMouseClicked(event -> System.exit(0)); // bouton exit (quitte le jeu)
        images[3].setOnMouseEntered(event -> images[3].setImage(exitHover));
        images[3].setOnMousePressed(event -> images[3].setImage(exitClicked));
        images[3].setOnMouseExited(event -> images[3].setImage(exit));

        return new Group(images[0], vBox);
    }
}
