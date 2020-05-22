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
        Level level = new Level(0);
        Background bg = new Background();

        ArrayList<Wall> walls = new ArrayList<>(level.getWalls());
        for (int i=0;i<12;i++) {
            if (i < 6) walls.add(new Wall(500, i * 96 + 76, level));
            else walls.add(new Wall(596, i*96 - 5 * 96 - 20, level));
        }
        for (int i=0;i<10;i++) {
            if (i < 5) walls.add(new Wall(900, i * 96 + 960 - 6 * 96, level));
            else walls.add(new Wall(996, i * 96 + 960 - 11 * 96, level));
        }
        for (int i=0;i<14;i++) {
            if (i < 7) walls.add(new Wall(1824 - (7 - i) * 96, 96, level));
            else if (i < 12) walls.add(new Wall(1824 - (12 - i) * 96, 192, level));
            else walls.add(new Wall(1824 - (15 - i) * 96, 288, level));
        }
        walls.add(new Wall(1400, 656, level));

        LaserV2 alpha = new LaserV2(96, 116, Math.tan(50 * Math.PI / 180));
        level.setAlphaLaser(alpha);

        level.getTargets().add(new Target(100, 655, "red", level));
        level.getTargets().add(new Target(420, 810, "blue", level));
        level.getTargets().add(new Target(590, 820, "red", level));
        level.getTargets().add(new Target(960, 110, "red", level));
        level.getTargets().add(new Target(1111, 455, "red", level));
        level.getTargets().add(new Target(1100, 815, "blue", level));

        level.getMirrors().add(new Mirror(390, 480, level));
        level.getMirrors().add(new Mirror(255, 810, level));
        level.getMirrors().add(new Mirror(470, 690, level));
        level.getMirrors().add(new Mirror(640, 810, level));
        level.getMirrors().add(new Mirror(820, 705, level));
        level.getMirrors().add(new Mirror(742, 180, level));
        level.getMirrors().add(new Mirror(1660, 475, level));
        level.getMirrors().add(new Mirror(1600, 815, level));

        Group root = new Group();
        root.getChildren().addAll(walls);
        root.getChildren().addAll(level.getMirrors());
        root.getChildren().addAll(level.getTargets());

        alpha.looking(level);

        return new Group(bg, alpha, level.getRoot(), level.getCircles(), root, level.getElements());
    }
}
