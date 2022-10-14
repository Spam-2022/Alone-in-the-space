package view;

import controller.GameEngine;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Bullet;
import model.Entity;
import model.Ship;

import java.util.Set;

public interface GameMap {

    /**
     * @return the panel where the content is drawn.
     */
    AnchorPane getGameContainer();

    /**
     * @return the active entities on the GameMap.
     */
    Set<Entity> getActiveEntities();

    /**
     * @return Width.
     */
    Number getWidth();

    /**
     * @return Height.
     */
    Number getHeight();

    /**
     * @param add the player Entity to the game.
     */
    void setPlayer(Ship player);

    /**
     * @return the player Entity.
     */
    Ship getPlayer();

    /**
     * @return a set with all the current enemy ships on the map.
     */
    Set<Ship> getActiveEnemyShips();

    /**
     * @return all active bullets shot by the player.
     */
    Set<Bullet> getBulletsShotByPlayer();

    /**
     * Add player bullet.
     *
     * @param bullet
     */
    void addPlayerBullet(Bullet bullet);

    /**
     * @return all active bullets shot by the enemies.
     */
    Set<Bullet> getBulletsShotByEnemies();

    /**
     * Adds enemy bullets.
     *
     * @param bullet
     */
    void addEnemyBullet(Bullet bullet);

    /**
     * Remove entities from the game.
     *
     * @param entity to be removed.
     */
    void removeEntity(Entity entity);

    /**
     * @return the scene.
     */
    Scene getScene();

    /**
     * Set the scene.
     *
     * @param scene to be set.
     */
    void setScene(Scene scene);

    /**
     * This method help for end game: set a stage reference.
     *
     * @param stage
     */
    void setStageReference(Stage stage);

    /**
     * Set the background image.
     *
     * @param the path of the file.
     */
    void setBackgroundImage(String path);

    /**
     *
     * @return the background.
     */
    Node[] getBackground();

    /**
     * @return Stage reference.
     */
    Stage getStage();

    //background image

    /**
     * Add an enemy ship to the game.
     *
     * @param enemy
     */
    void addEnemyShip(Ship enemy);

    /**
     * Set a GameLoop reference
     *
     * @param gameEngine
     */
    void setGameEngine(GameEngine gameEngine);

    /**
     * @return GameLoop reference.
     */
    GameEngine getGameEngine();


}
