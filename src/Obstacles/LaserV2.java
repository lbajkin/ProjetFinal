package Obstacles;

import javafx.scene.paint.Color;

import javafx.scene.shape.Line;

import static Main.Main.WIDTH;
import static Obstacles.Mirror.mirrorX;
import static Obstacles.Target.targetXY;

public class LaserV2 extends Line {

    private double startX;
    private double startY;
    private double a;
    private double b;
    private boolean goingBackwards = false;
    private boolean special = false;

    public LaserV2(double startX, double startY, double a) {
        if (a > 7) { // si la pente est trop vérticale (c'est pas idéal mais ca marche)
            a = 7;
            special = true;
        }
        else if (a < -7) {
            a = -7;
        }

        setStrokeWidth(15);
        setStroke(Color.CRIMSON); // la couleur du laser

        setStartX(startX);
        setStartY(startY);

        this.a = a;
        this.startX = startX;
        this.startY = startY;
        b = startY - (a * startX);
    }

    public void looking(Level level) { // laser cherche pour un obstacle
        level.getLasers().clear();

        int x = 1;
        if (goingBackwards) { // si le laser va vers l'arrière, il va vers l'x négatif
            x = x * -1;
        }
        for (int i = 0; i< WIDTH; i++) {
            if (i >= 10 || Math.abs(((startX + i * x) * a + b) - startY) >= 10) { // le 10 est ici pour que le laser ne s'arrête pas sur le miroir qu'il vient de réfléchir dessus
                if (!level.getCoordonate()[(int) (startX + i * x)][(int) ((startX + i * x) * a + b)].equals("empty")) { // si le laser rentre dans une coordonnée empty, il continue, sinon il arrête

                    setEndX(startX + i * x);
                    setEndY((startX + i * x) * a + b);

                    collision((int)(i * x + startX), (int)((i * x + startX) * a + b), level.getCoordonate()[(int) (startX + i * x)][(int) ((startX + i * x) * a + b)], level);

                    i = 1901; // end of loop (ca pourrait être mieux :) )
                }
            }
        }

        level.getRoot().getChildren().clear();
        level.getRoot().getChildren().addAll(level.getLasers()); // reset les lasers sur l'écran
    }

    public void collision(int collideX, int collideY, String objectHit, Level level) {
        if (objectHit.equals("mirrorReflect")) hitMirror(collideX, collideY, level);
        if (objectHit.equals("target")) hitTarget(collideX, collideY, level);
        if (objectHit.equals("teleporter")) hitTeleporter(collideX, collideY, level);
    }

    public void hitMirror(int collideX, int collideY, Level level) {
        for (int i = 0; i< level.getMirrors().size(); i++) { // l'application vérifie quel miroir à été frappé
            if ((int)((collideX - (level.getMirrors().get(i).getX() + mirrorX / 2)) / 50) == 0 && (int)((collideY - level.getMirrors().get(i).getY()) / 50) == 0) { // vérifie par bloc de 50x50 quel miroir a été frappé
                level.getMirrors().get(i).reflection(this, level.getMirrors().get(i).getRotateValue()[0], level); // laser est réfléchi

                level.getRoot().getChildren().removeAll(level.getLasers());
                level.getRoot().getChildren().addAll(level.getLasers()); // reset les lasers sur l'écran
            }
        }
    }

    public void hitTarget(int collideX, int collideY, Level level) { // vérifie quel cible est frappé
        for (int i = 0; i< level.getTargets().size(); i++) {
            if ((int)((collideX - (level.getTargets().get(i).getX() + targetXY / 2)) / 50) == 0 && (int)((collideY - level.getTargets().get(i).getY()) / 50) == 0) {
                level.getTargets().get(i).explosion(this, level);
            }
        }
    }

    public void hitTeleporter(int collideX, int collideY, Level level) {
        for (int i=0;i < level.getTeleporters().size();i++) { // vérifie quel téléporteur est frappé
            if ((int)((collideX - (level.getTeleporters().get(i).getEnterX())) / 100) == 0 && (int)((collideY - level.getTeleporters().get(i).getEnterY()) / 50) == 0) {
                level.getTeleporters().get(i).exitTeleporter(level);
            }
        }
    }

    public void specialCase() {
        if (special) {
            goingBackwards = true;
        }
    }

    public double getA() {
        return a;
    }

    public void setGoingBackwards(boolean goingBackwards) {
        this.goingBackwards = goingBackwards;
    }

    public boolean isGoingBackwards() {
        return goingBackwards;
    }

    public void setA(double a) {
        this.a = a;
    }
}
