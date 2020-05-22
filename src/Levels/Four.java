package Levels;

import Obstacles.*;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.ArrayList;

public class Four extends Scene {

    public static Scene create() {
        return new Four(group());
    }

    private Four(Parent root) {
        super(root);
    }

    private static Group group() {
        Level level = new Level(3);
        Background bg = new Background();

        ArrayList<Wall> walls = new ArrayList<>(level.getWalls());
        for (int i=0;i<6;i++) {
            walls.add(new Wall(i * 96 + 96, 624, level));
        }
        for (int i=0;i<4;i++) {
            walls.add(new Wall(672, 624 - i * 96, level));
        }
        for (int i=0;i<7;i++) {
            if (i < 3)
                walls.add(new Wall(864, 864 - i * 96, level));
            else walls.add(new Wall(960, 864 - i * 96, level));
        }
        for (int i=0;i<3;i++) {
            walls.add(new Wall(1248 + i * 96, 384, level));
        }
        walls.add(new Wall(1248, 480, level));
        walls.add(new Wall(1440, 480, level));
        walls.add(new Wall(1056, 576, level));
        walls.add(new Wall(1152, 672, level));
        walls.add(new Wall(1152, 768, level));

        LaserV2 alpha = new LaserV2(96, 802, Math.tan(0 * Math.PI / 180));
        level.setAlphaLaser(alpha);

        level.getTargets().add(new Target(824, 746, "red", level));
        level.getTargets().add(new Target(883, 99, "red", level));
        level.getTargets().add(new Target(371, 403, "red", level));
        level.getTargets().add(new Target(111, 483, "red", level));
        level.getTargets().add(new Target(107, 136, "blue", level));
        level.getTargets().add(new Target(1145, 512, "red", level));
        level.getTargets().add(new Target(1272, 709, "red", level));
        level.getTargets().add(new Target(1547, 542, "red", level));
        level.getTargets().add(new Target(1106, 755, "red", level));
        level.getTargets().add(new Target(971, 687, "blue", level));
        level.getTargets().add(new Target(966, 778, "red", level));

        level.getMirrors().add(new Mirror(770, 804, level));
        level.getMirrors().add(new Mirror(893, 143, level));
        level.getMirrors().add(new Mirror(128, 403, level));
        level.getMirrors().add(new Mirror(348, 581, level));
        level.getMirrors().add(new Mirror(1555, 155, level));
        level.getMirrors().add(new Mirror(1083, 330, level));
        level.getMirrors().add(new Mirror(1656, 790, level));
        level.getMirrors().add(new Mirror(1518, 824, level));
        level.getMirrors().add(new Mirror(1037, 829, level));

        level.getTeleporters().add(new Teleporter(1348, 480, 960, 817, 1, 1, level));

        Group root = new Group();
        root.getChildren().addAll(walls);
        root.getChildren().addAll(level.getMirrors());
        root.getChildren().addAll(level.getTargets());

        alpha.looking(level);

        return new Group(bg, alpha, level.getRoot(), level.getCircles(), root, level.getElements());
    }
}
