package Obstacles;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;

public class Background extends ImageView { // l'arrière fond

    public Background() {
        setImage(new Image("Images/GameElements/background.png"));

        /*setOnMouseClicked(event -> {
            MouseButton button = event.getButton();
            if (button == MouseButton.PRIMARY) {
                System.out.println(event.getSceneX() + ", " + event.getSceneY());
            }
        });
        */
        // utilisé pour placer les objets dans les niveaux
    }


}
