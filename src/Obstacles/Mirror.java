package Obstacles;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;

public class Mirror extends ImageView {

    private int centerX; // center x in mirror
    private int centerY; // center y in mirror
    final int[] rotateValue = {0};
    static int mirrorX = 64;
    static int mirrorY = 16;

    public Mirror(int x, int y, Level level) {
        int[][] coordX = new int[mirrorX][mirrorY]; // coordonnées du miroir utilisé pour la rotation du miroir
        int[][] coordY = new int[mirrorX][mirrorY];
        setImage(new Image("Images/GameElements/rotatable mirror.png"));
        setX(x);
        setY(y);
        centerX = mirrorX / 2 + x; // centre du miroir
        centerY = mirrorY / 2 + y;
        for (int i=0;i<mirrorX;i++) { // coordonnées = mirror
            for (int j=0;j<mirrorY;j++) {
                if (j < 6 && i > 4 && i < 60) // les nombres bizarres ici sont la partie blanche du miroir qui est la seule partie qui réfléchi les lasers
                    level.getCoordonate()[i+x][j+y] = "mirrorReflect"; // mirrorReflect = white part (laser can bounce off here)
                else level.getCoordonate()[i+x][j+y] = "mirror"; // mirror = blue part (laser can't bounce)
                coordX[i][j] = i+x;
                coordY[i][j] = j+y;
            }
        }

        setOnMouseClicked(event -> {
            MouseButton button = event.getButton();
            if (button == MouseButton.PRIMARY) {
                setRotate(rotateValue[0] + 20); // clique gauche = tourne à droite
                rotateValue[0] = rotateValue[0] + 20;
            }
            if (button == MouseButton.SECONDARY) { // clique droite = tourne à gauche
                setRotate(rotateValue[0] - 20);
                rotateValue[0] = rotateValue[0] - 20;
            }

            if (rotateValue[0] < 0) {
                rotateValue[0] = rotateValue[0] + 360;
            }
            if (rotateValue[0] > 360) {
                rotateValue[0] = rotateValue[0] - 360;
            }

            int[][] newCoordX = new int[mirrorX][mirrorY]; // nouvelles coordonnées du miroir tourné
            int[][] newCoordY = new int[mirrorX][mirrorY];

            for(int i=0;i<mirrorX;i++) { // calcul des nouvelles coordonnées
                for(int j=0;j<mirrorY;j++) {
                    newCoordX[i][j] = (int)(((coordX[i][j] - centerX) * Math.cos(rotateValue[0] * Math.PI / 180) - (coordY[i][j] - centerY) * Math.sin(rotateValue[0] * Math.PI / 180)) + centerX);
                    newCoordY[i][j] = (int)(((coordY[i][j] - centerY) * Math.cos(rotateValue[0] * Math.PI / 180) + (coordX[i][j] - centerX) * Math.sin(rotateValue[0] * Math.PI / 180)) + centerY);
                }
            }

            for (int i=0;i<74;i++) { // les coordonnées du vieux miroir sont vidées
                for (int j=0;j<74;j++) {
                    level.getCoordonate()[i+x-5][j+y-24-5] = "empty";
                }
            }

            for (int i=0;i<mirrorX;i++) { // nouvelles coordonnées sont implementées
                for (int j=0;j<mirrorY;j++) {
                    if (j < 6 && i > 4 && i < 60)
                        level.getCoordonate()[newCoordX[i][j]][newCoordY[i][j]] = "mirrorReflect";
                    else level.getCoordonate()[newCoordX[i][j]][newCoordY[i][j]] = "mirror";
                }
            }

            level.getAlphaLaser().looking(level); // le premier laser parcours le niveau
        });

        ImageView circle = new ImageView(new Image("Images/GameElements/mirror thing.png")); // visuel (4 lignes)
        circle.setX(x);
        circle.setY(y - 24);
        level.getCircles().getChildren().add(circle);
    }

    public void reflection(LaserV2 laser, int mirrorRotation, Level level) { // refléxion
        LaserV2 bounce = new LaserV2(laser.getEndX(), laser.getEndY(), Math.tan(-(-mirrorRotation * 2 + (Math.atan(laser.getA()) * 180 / Math.PI)) * Math.PI / 180)); // nouveau laser refléchi
        if ((-(-mirrorRotation * 2 + (Math.atan(laser.getA()) * 180 / Math.PI))) % 360 > 90 && (-(-mirrorRotation * 2 + (Math.atan(laser.getA()) * 180 / Math.PI))) % 360 < 270) // pour savoir dans quel direction (+ ou - x) le laser refléchi veut s'en allser
            bounce.setGoingBackwards(true);
        else bounce.setGoingBackwards(false);

        if (laser.isGoingBackwards()) { // si le laser qui se fait réfléchir va vers l'x négatif, il faut inverser la direction du nouveau laser
            if (bounce.isGoingBackwards()) bounce.setGoingBackwards(false);
            else bounce.setGoingBackwards(true);
        }

        bounce.specialCase(); // si la pente du nouveau laser est trop vertical (ca causait des bugs, alors j'ai implementé une correction temporaire)

        bounce.looking(level); // nouveau laser cherche une collision

        level.getLasers().add(bounce); // nouveau laser est rajouté au système
    }

    public int[] getRotateValue() {
        return rotateValue;
    }
}