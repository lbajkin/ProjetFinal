package Obstacles;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Teleporter{ // deux parties, premier téléporteur recoit le laser, deuxième le sort

    ImageView tp;
    ImageView tpExit;
    private int enterX;
    private int enterY;
    private int exitX;
    private int exitY;
    private int exitD; // direction du deuxième téléporteur

    public Teleporter(int enterX, int enterY, int exitX, int exitY, int enterD, int exitD, Level level) {
        this.exitD = exitD;
        this.enterX = enterX;
        this.enterY = enterY;
        this.exitX = exitX;
        this.exitY = exitY;

        tp = new ImageView(new Image("Images/GameElements/teleporter " + enterD +".png"));
        tpExit = new ImageView(new Image("Images/GameElements/teleporter exit " + exitD + ".png"));

        tp.setX(enterX);tpExit.setX(exitX);
        tp.setY(enterY);tpExit.setY(exitY);

        for (int i=0;i<60;i++) { // seule la partie mauve du téléporteur agit comme un téléporteur, le reste est un mur
            for (int j=0;j<6;j++) {
                level.getCoordonate()[i+enterX][j+enterY] = "wall";
            }
        }
        for (int i=0;i<54;i++) {
            for (int j=0;j<3;j++) {
                level.getCoordonate()[i+enterX + 3][j+enterY + 6] = "wall";
            }
        }
        for (int i=0;i<50;i++) {
            for (int j=0;j<5;j++) {
                level.getCoordonate()[i+enterX + 5][j+enterY + 9] = "wall";
            }
        }
        for (int i=0;i<42;i++) {
            for (int j=0;j<6;j++) {
                level.getCoordonate()[i+enterX + 9][j+enterY + 11] = "teleporter";
            }
        }

        level.getElements().getChildren().addAll(tp);
        level.getElements().getChildren().addAll(tpExit);
    }

    public void exitTeleporter(Level level) {
        LaserV2 laser = new LaserV2(exitX + 6, exitY + 13, 0); // laser qui sort du deuxième téléporteur

        if (exitD == 2) { // pas utilisé, on avait comme but de placé les téléporteurs dans toutes les quatres diréctions (haut, gauche, droite, bas)
            laser.setGoingBackwards(true);
        }

        laser.looking(level);

        level.getLasers().add(laser);
    }

    public int getEnterX() {
        return enterX;
    }

    public int getEnterY() {
        return enterY;
    }

    public int getExitX() {
        return exitX;
    }

    public int getExitY() {
        return exitY;
    }
}
