// Les classes les plus importantes : LaserV2 (le laser), Miroir (le mirror), Level (la squelette de chaque niveau)

package Main;

import Menus.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }
    static Stage stage;
    public static final int LEVELS = 9; // nombre de niveaux (il y en a quatre implementé, mais on avait comme but d'en faire neuf)
    public static final int HEIGHT = 960; // hauteur du niveau
    public static final int WIDTH = 1824; // longeur du niveau
    public static boolean[] levelUnlocked = new boolean[LEVELS];

    public void start(Stage primaryStage) {
        levelUnlocked[0] = true;

        stage = primaryStage;
        primaryStage.setTitle("Lazer Puzzle");
        primaryStage.setHeight(HEIGHT + 32); // 32 = les 32 pixels en haut de chaque application
        primaryStage.setWidth(WIDTH);
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(t -> { // lorsqu'on ferme le jeu en cliquant sur le bouton X en haut à droite
            Platform.exit();
            System.exit(0);
        });
        primaryStage.setScene(TitleScreen.create());
        primaryStage.show();
    }

    public static void setScene(Scene scene) {
        stage.setScene(scene);
    }
}

/* level outline
package Levels;

import Obstacles.*;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.ArrayList;

public class One extends Scene {

    public static Scene create() {
        return new One(group());
    }

    private One(Parent root) {
        super(root);
    }

    private static Group group() {
        Level level = new Level();
        Background bg = new Background();

        ArrayList<Wall> walls = new ArrayList<>(level.getWalls());

        LaserV2 alpha = new LaserV2(96, 123, Math.tan(0 * Math.PI / 180));
        level.setAlphaLaser(alpha);

        level.getTargets().add(new Target(0, 0, "blue", level));

        level.getMirrors().add(new Mirror(0, 0, level));

        Group root = new Group();
        root.getChildren().addAll(walls);
        root.getChildren().addAll(level.getMirrors());
        root.getChildren().addAll(level.getTargets());

        alpha.looking(level);

        return new Group(bg, alpha, level.getRoot(), level.getCircles(), root, level.getElements());
    }
}
 */