package Menus;

import Levels.*;
import Main.Main;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static Main.Main.LEVELS;
import static Main.Main.levelUnlocked;

public class LevelSelect extends Scene { // sélecteur de niveau

    private static Image bg = new Image("Images/LevelSelect/background.png");
    private static Image back = new Image("Images/LevelSelect/back.png");
    private static ImageView[] images = new ImageView[2];

    private static ImageView[] levels = new ImageView[9];
    private static ImageView[] numbers = new ImageView[9];

    public static Scene create() {
        return new LevelSelect(group());
    }

    private LevelSelect(Parent root) {
        super(root);
    }

    private static Group group() {
        images[0] = new ImageView(bg);
        images[1] = new ImageView(back);
        images[1].setX(20);images[1].setY(20);

        images[1].setOnMouseClicked(e -> Main.setScene(TitleScreen.create())); // bouton « back »

        for (int i=0;i<LEVELS;i++) { // génère les icônes pour chaque niveau (débloqué = vert, fini = arc-en-ciel, bloqué = gris)
            levels[i] = new ImageView(new Image("Images/LevelSelect/U.png"));
            if (levelUnlocked[i]) {
                numbers[i] = new ImageView(new Image("Images/LevelSelect/" + (i + 1) + ".png")); // niveau débloqué
            }
            else numbers[i] = new ImageView(new Image("Images/LevelSelect/L" + (i + 1) + ".png")); // niveau bloqué

            if (i > 0 && levelUnlocked[i]) { // si un niveau est completé
                levels[i-1].setImage(new Image("Images/LevelSelect/C.png"));
            }

            if (i<6) { // placement des icônes
                levels[i].setX(432 + i * 170);numbers[i].setX(432 + i * 170);
                levels[i].setY(315);numbers[i].setY(315);
            }
            else {
                levels[i].setX(618 + (i-6) * 170);numbers[i].setX(618 + (i-6) * 170);
                levels[i].setY(515);numbers[i].setY(515);
            }
        }

        numbers[0].setOnMouseClicked(e -> Main.setScene(One.create())); // en cliquant sur le premier niveau, le premier niveau recommence
        numbers[1].setOnMouseClicked(e -> { if(levelUnlocked[1]) Main.setScene(Two.create()); }); // en cliquant sur le deuxième niveau, etc
        numbers[2].setOnMouseClicked(e -> { if(levelUnlocked[2]) Main.setScene(Three.create()); });
        numbers[3].setOnMouseClicked(e -> { if(levelUnlocked[3]) Main.setScene(Four.create()); });

        Group root = new Group(levels);
        Group root2 = new Group(numbers);

        return new Group(images[0], images[1], root, root2);
    }
}
