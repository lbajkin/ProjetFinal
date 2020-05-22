package Levels;

import Obstacles.*;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.ArrayList;

public class Two extends Scene {

    public static Scene create() {
        return new Two(group());
    }

    private Two(Parent root) {
        super(root);
    }

    private static Group group() {
        Level level = new Level(1);
        Background bg = new Background();

        ArrayList<Wall> walls = new ArrayList<>(level.getWalls());
        for (int i=0;i<17;i++) {
            if (i != 13 && i != 14 && i != 12)
                walls.add(new Wall(i * 96 + 96, 384, level));
        }
        for (int i=0;i<6;i++) {
            walls.add(new Wall(1248, 96 + i * 96, level));
        }


        LaserV2 alpha = new LaserV2(1728, 123, Math.tan(-35 * Math.PI / 180));
        level.setAlphaLaser(alpha);
        alpha.setGoingBackwards(true);

        level.getTargets().add(new Target(1360, 120, "red", level));
        level.getTargets().add(new Target(1522, 509, "red", level));
        level.getTargets().add(new Target(1632, 690, "blue", level));
        level.getTargets().add(new Target(990, 760, "red", level));
        level.getTargets().add(new Target(1007, 109, "red", level));
        level.getTargets().add(new Target(468, 191, "red", level));
        level.getTargets().add(new Target(1189, 314, "blue", level));

        level.getMirrors().add(new Mirror(1394, 328, level));
        level.getMirrors().add(new Mirror(1340, 803, level));
        level.getMirrors().add(new Mirror(1010, 500, level));
        level.getMirrors().add(new Mirror(690, 813, level));
        level.getMirrors().add(new Mirror(457, 604, level));
        level.getMirrors().add(new Mirror(300, 761, level));
        level.getMirrors().add(new Mirror(346, 142, level));
        level.getMirrors().add(new Mirror(120, 177, level));
        level.getMirrors().add(new Mirror(296, 344, level));

        level.getTeleporters().add(new Teleporter(270, 480, 96, 116, 1, 1, level));

        Group root = new Group();
        root.getChildren().addAll(walls);
        root.getChildren().addAll(level.getMirrors());
        root.getChildren().addAll(level.getTargets());

        alpha.looking(level);

        return new Group(bg, alpha, level.getRoot(), level.getCircles(), root, level.getElements());
    }
}
