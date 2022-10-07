package model;

import com.badlogic.gdx.math.Vector2;

import javafx.scene.Node;
import javafx.scene.image.Image;

public class BulletFactory {
	public static Bullet BasicBullet(Vector2 position) {

		return new AbstractBullet(100, 30, 0, 10, position, null) {
			
			@Override
			public void move(float deltaTime) {
				super.speed = super.speed.mulAdd(direction.cpy(), deltaTime);
				if (super.speed.len2() > maxSpeed) {
					super.speed.cpy().scl(super.maxSpeed / super.speed.len());
				}
				super.position = super.position.add(super.speed);
			}

			@Override
			public Node getNode() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void setSprite(Image img) throws IllegalArgumentException, NullPointerException {
				// TODO Auto-generated method stub
				
			}
		};

	}

	public static Bullet RifleBullet(Vector2 position, Vector2 direction) {

		return new AbstractBullet(0, 0, 0, 0, direction, null) {
			
			@Override
			public void move(float deltaTime) {
				super.speed = super.speed.mulAdd(direction.cpy(), deltaTime);
				if (super.speed.len2() > maxSpeed) {
					super.speed.cpy().scl(super.maxSpeed / super.speed.len());
				}
				super.position = super.position.add(super.speed);
			};
		};

	}

	public static Bullet missile(Vector2 position, final Ship enemy) {

		return new AbstractBullet(0, 0, 0, 0, null, null) {
			private final Ship target = enemy;

			@Override
			public void move(float deltaTime) {
				// TODO Auto-generated method stub
				if(target.isAlive()) {
					
					
					
				}else {
					super.destroy();
				}
			}
		};

	}

}