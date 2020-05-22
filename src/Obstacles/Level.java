package Obstacles;
import Main.Main;
import Menus.GameOver;
import Menus.LevelSelect;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import java.util.ArrayList;
import static Main.Main.WIDTH;
import static Main.Main.HEIGHT;

public class Level {

    private int levelNumber = 0; // le niveau (niveau 1 = 0, niveau 2 = 1, etc)
    private int life = 3; // nombre de vies
    private int blueRemaining = 0; // nombres de cibles bleues réstantes
    private String[][] coordonate = new String[WIDTH][HEIGHT]; // coordonnées (1824x960)
    private ArrayList<LaserV2> lasers =  new ArrayList<>(); // les lasers dans le niveau
    private ArrayList<Mirror> mirrors = new ArrayList<>(); // les miroirs dans le niveau
    private ArrayList<Target> targets = new ArrayList<>(); // les cibles dans le niveau
    private ArrayList<Teleporter> teleporters = new ArrayList<>(); // les téléporteurs dans le niveau
    private LaserV2 alphaLaser; // premier laser
    private Group root = new Group(); // l'array des lasers est ici
    private Group circles = new Group(); // visuel pour les miroirs
    private Group elements = new Group(); // la vie et le contour du niveau
    private ArrayList<Wall> walls = new ArrayList<>(); // les murs ajoutés à chaque niveau
    private ImageView[] hearts = {new ImageView(new Image("Images/GameElements/heart.png")), new ImageView(new Image("Images/GameElements/heart.png")), new ImageView(new Image("Images/GameElements/heart.png"))};
    private ImageView heartCase = new ImageView(new Image("Images/GameElements/heart casing.png"));
    private HBox hbox = new HBox(10); // pour la barre de vie

    public Group getElements() {
        return elements;
    }

    public Level(int levelNumber) {
        this.levelNumber = levelNumber;

        hbox.getChildren().addAll(hearts); // barre de vie
        hbox.setTranslateX(WIDTH - 186 - 5);
        hbox.setTranslateY(12);
        heartCase.setX(WIDTH - 186 - 10);
        heartCase.setY(5);
        elements.getChildren().addAll(heartCase);
        elements.getChildren().addAll(hbox);

        for (int i=0;i<WIDTH;i++) { // initialize les coordonnées
            for (int j=0;j<HEIGHT;j++) {
                coordonate[i][j] = "empty";
            }
        }

        for (int i=0;i<38;i++) { // les murs qui contourent le niveau
            if (i < 19) { // walls at top
                walls.add(new Wall(i*96, 0, this));
            }
            if (i>=19) { // walls at bottom
                walls.add(new Wall(i*96 - 1824, 864, this));
            }
        }
        for (int i=0;i<8;i++) { // walls on the right
            walls.add(new Wall(1728, i*96 + 96, this));
        }
        for (int i=0;i<8;i++) { // walls on the left
            walls.add(new Wall(0, i*96 + 96, this));
        }
    }

    public void loseLife() { // lorsqu'une cible rouge est détruite
        life--; // -1 vie

        hearts[life].setImage(new Image("Images/GameElements/empty heart.png"));

        hbox.getChildren().clear(); // barre de vie mise à jour
        hbox.getChildren().addAll(hearts);

        if (life <= 0) { // 0 vies réstantes
            Main.setScene(GameOver.create(levelNumber));
        }
    }

    public void winCondition() { // vérifie constamment s'il reste des cibles bleues
        if (blueRemaining == 0) {

            Main.levelUnlocked[levelNumber + 1] = true; // débloque le prochain niveau
            Main.setScene(LevelSelect.create());
        }
    }

    public String[][] getCoordonate() {
        return coordonate;
    }

    public ArrayList<Teleporter> getTeleporters() {
        return teleporters;
    }

    public int getBlueRemaining() {
        return blueRemaining;
    }

    public Group getCircles() {
        return circles;
    }

    public ArrayList<LaserV2> getLasers() {
        return lasers;
    }

    public ArrayList<Mirror> getMirrors() {
        return mirrors;
    }

    public ArrayList<Target> getTargets() {
        return targets;
    }

    public Group getRoot() {
        return root;
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public LaserV2 getAlphaLaser() {
        return alphaLaser;
    }

    public void setAlphaLaser(LaserV2 alphaLaser) {
        this.alphaLaser = alphaLaser;
    }

    public void setBlueRemaining(int blueRemaining) { this.blueRemaining = blueRemaining; }
}
