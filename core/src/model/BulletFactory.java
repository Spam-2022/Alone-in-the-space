package model;

import com.badlogic.gdx.math.Vector2;

import javafx.scene.image.Image;

public class BulletFactory {

	private static final String assetFolder = "";

	public static Bullet BasicBullet(Vector2 position, Vector2 direction) {

		class BasicBullet extends AbstractBullet {

			public BasicBullet(float maxSpeed, float acceleration, float rotationSpeed, float damage, Vector2 position,
					Vector2 direction) {
				super(maxSpeed, acceleration, rotationSpeed, damage, position);
				// TODO Auto-generated constructor stub
			}

			@Override
			public void move(float deltaTime) {
				super.speed = super.speed.mulAdd(direction.cpy(), deltaTime);
				if (super.speed.len2() > maxSpeed) {
					super.speed.cpy().scl(super.maxSpeed / super.speed.len());
				}
				super.position = super.position.add(super.speed);
			}

		}
		;
		Bullet var = new BasicBullet(0, 0, 0, 0, position, direction);
		var.setSprite(loadImage("bullet_01.png"));
		return var;
	}

	public static Bullet RifleBullet(Vector2 position, Vector2 direction) {

		class RifleBullet extends AbstractBullet {

			public RifleBullet(float maxSpeed, float acceleration, float rotationSpeed, float damage, Vector2 position,
					Vector2 direction) {
				super(maxSpeed, acceleration, rotationSpeed, damage, position);
				// TODO Auto-generated constructor stub
			}

			@Override
			public void move(float deltaTime) {
				super.speed = super.speed.mulAdd(direction.cpy(), deltaTime);
				if (super.speed.len2() > maxSpeed) {
					super.speed.cpy().scl(super.maxSpeed / super.speed.len());
				}
				super.position = super.position.add(super.speed);
			}

		}
		;
		Bullet var = new RifleBullet(0, 0, 0, 0, position, direction);
		var.setSprite(loadImage("bullet_02.png"));
		return var;

	}

	public static Bullet missile(Vector2 position, Vector2 direction, final Ship enemy) {

		class missile extends AbstractBullet {
			public missile(float maxSpeed, float acceleration, float rotationSpeed, float damage, Vector2 position,
					Vector2 direction,Ship enemy) {
				super(maxSpeed, acceleration, rotationSpeed, damage, position);
				// TODO Auto-generated constructor stub
				this.target=enemy;
			}

			private final Ship target;

			@Override
			public void move(float deltaTime) {
				// TODO Auto-generated method stub
				if (target.isAlive()) {

				} else {
					super.destroy();
				}
			}
		}
		;
		Bullet var = new missile(0, 0, 0, 0, position, direction,enemy);
		var.setSprite(loadImage("missile_01.png"));
		return var;

	}

	private static Image loadImage(String pathName) throws IllegalArgumentException {
		Image img;

		try {
			img = new Image("" + assetFolder + pathName);
		} catch (Exception e) {
			throw e;
		}
		return img;
	}

}
