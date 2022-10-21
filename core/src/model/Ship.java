package model;

import java.io.IOException;
import java.util.List;

import com.badlogic.gdx.math.Vector2;

public interface Ship extends Entity{
	
	/**
	 * Generate a bullet with the current direction and position of the ship.
	 * @return
	 */
	Bullet shot();
	
	/**
	 * destroy the Ship.
	 */
	void destroy();
	
	/**
	 * Set the gun for this ship.
	 * @param gun
	 */
	void setGun(Gun gun);
	
	/**
	 * decrease the ship current health for the damage specified.
	 * @param damage
	 */
	void hit(float damage);
	
	/**
	 * set the current target of this ship.
	 * @param target
	 */
	void setTarget(Ship target);
	
	/**
	 * Drop of this ship when destroyed.
	 * @return
	 */
	String drop();
	
	/**
	 * check if at least an enemy is in range of this ship.
	 * @param enemy
	 * @return
	 */
	Boolean isInRangeOfAttack(List<Vector2> enemy);
	
}
