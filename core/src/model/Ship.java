package model;

import java.util.List;

import com.badlogic.gdx.math.Vector2;

public interface Ship extends Entity{
	Bullet shot();
	void destroy();
	
	void setGun(Gun gun);
	void hit(float damage);
	void setTarget(Ship target);
	String drop();
	Boolean isInRangeOfAttack(List<Vector2> enemy);
	
}