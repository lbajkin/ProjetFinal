package Levels;

import Obstacles.*;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.ArrayList;

public class Three extends Scene{

    public static Scene create() {
        return new Three(group());
    }

    private Three(Parent root) {
        super(root);
    }

    private static Group group() {
        Level level = new Level(2);
        Background bg = new Background();

        ArrayList<Wall> walls = new ArrayList<>(level.getWalls());
        for (int i=0;i<12;i++) {
            walls.add(new Wall(i*96 + 96 * 3, 96 * 2, level));
        }
        walls.add(new Wall(1344, 288, level));
        for (int i=0;i<2;i++) {
            walls.add(new Wall(1344, i* 96 + 96 * 5, level));
        }
        for (int i=0;i<10;i++) {
            walls.add(new Wall(i*96 + 96 * 4, 96 * 6, level));
        }
        walls.add(new Wall(1440, 96 * 6, level));
        for (int i=0;i<3;i++) {
            walls.add(new Wall(768, 96 * 3 + i * 96, level));
        }
        for (int i=0;i<4;i++) {
            walls.add(new Wall(768 - 96 * 4 + i *96, 96 * 4, level));
        }

        LaserV2 alpha = new LaserV2(96, 115, Math.tan(2 * Math.PI / 180));
        level.setAlphaLaser(alpha);

        level.getTargets().add(new Target(1517, 266, "red", level));
        level.getTargets().add(new Target(1548, 556, "red", level));
        level.getTargets().add(new Target(1291, 304, "blue", level));
        level.getTargets().add(new Target(940, 754, "red", level));
        level.getTargets().add(new Target(512, 728, "red", level));
        level.getTargets().add(new Target(1678, 719, "blue", level));
        level.getTargets().add(new Target(1176, 521, "red", level));
        level.getTargets().add(new Target(244, 327, "red", level));
        level.getTargets().add(new Target(330, 416, "blue", level));

        level.getMirrors().add(new Mirror(1570, 147, level));
        level.getMirrors().add(new Mirror(1660, 539, level));
        level.getMirrors().add(new Mirror(1490, 825, level));
        level.getMirrors().add(new Mirror(1130, 698, level));
        level.getMirrors().add(new Mirror(1112, 313, level));
        level.getMirrors().add(new Mirror(777, 828, level));
        level.getMirrors().add(new Mirror(1021, 524, level));
        level.getMirrors().add(new Mirror(101, 596, level));
        level.getMirrors().add(new Mirror(113, 507, level));

        level.getTeleporters().add(new Teleporter(893, 288, 762, 515, 1, 2, level));
        level.getTeleporters().add(new Teleporter(1061, 288, 762, 325, 1, 2, level));

        Group root = new Group();
        root.getChildren().addAll(walls);
        root.getChildren().addAll(level.getMirrors());
        root.getChildren().addAll(level.getTargets());

        alpha.looking(level);

        return new Group(bg, alpha, level.getRoot(), level.getCircles(), root, level.getElements());
    }
}
