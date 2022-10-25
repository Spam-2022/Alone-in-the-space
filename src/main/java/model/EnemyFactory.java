package model;

import com.almasb.fxgl.core.math.Vec2;
import javafx.scene.image.Image;

public class EnemyFactory {
    public static Ship BasicEnemy(Vec2 newPosition) throws IllegalArgumentException {
        class BasicEnemy extends AbstractShip {

            public BasicEnemy(float health, float maxSpeed, float acceleration, float rotationSpeed, long attackCD,
                              Vec2 position) {
                super(health, maxSpeed, acceleration, rotationSpeed, attackCD, position);
                // TODO Auto-generated constructor stub
            }

        }
        ;
        Ship en = new BasicEnemy(50, 100, 0.5f, 45, 1000, newPosition);
        en.setGun(GunFactory.missile(en));
        en.setSprite(loadImage("ship_0.png"));
        return en;
    }

    public static Ship MissileEnemy(Vec2 newPosition) throws IllegalArgumentException {
        class MissileEnemy extends AbstractShip {

            public MissileEnemy(float health, float maxSpeed, float acceleration, float rotationSpeed, long attackCD,
                                Vec2 position) {
                super(health, maxSpeed, acceleration, rotationSpeed, attackCD, position);
                // TODO Auto-generated constructor stub
            }

        }
        ;
        Ship en = new MissileEnemy(100, 50, 0.3f, 20, 2000, newPosition);
        en.setGun(GunFactory.missile(en));
        en.setSprite(loadImage("ship_30.png"));
        return en;
    }

    public static Ship RifleEnemy(Vec2 newPosition) throws IllegalArgumentException {
        class RifleEnemy extends AbstractShip {

            public RifleEnemy(float health, float maxSpeed, float acceleration, float rotationSpeed, long attackCD,
                              Vec2 position) {
                super(health, maxSpeed, acceleration, rotationSpeed, attackCD, position);
                // TODO Auto-generated constructor stub
            }

        }
        ;
        Ship en = new RifleEnemy(25, 150, 0.6f, 60, 500, newPosition);
        en.setGun(GunFactory.rifle(en));
        en.setSprite(loadImage("ship_22.png"));
        return en;
    }

    private static Image loadImage(String name) throws IllegalArgumentException {
        Image img;

        try {
            img = new Image("file:" + name);
        } catch (Exception e) {
            throw e;
        }
        return img;
    }
}