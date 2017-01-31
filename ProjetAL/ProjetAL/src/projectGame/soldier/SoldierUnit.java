package projectGame.soldier;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.core.Drawable;
import gameframework.core.GameEntity;
import gameframework.core.GameMovable;
import gameframework.core.Overlappable;
import gameframework.core.SpriteManager;
import gameframework.core.SpriteManagerDefaultImpl;

import gameframework.moves_rules.MoveBlocker;
import soldier.core.Unit;

public abstract class SoldierUnit extends GameMovable implements Drawable, GameEntity,
Overlappable, MoveBlocker  {
	protected final SpriteManager spriteManager;
	public static final int RENDERING_SIZE = 50;
	protected boolean movable = true;	
	protected String Direction = "";	
	protected String weapon ;	
	protected Unit unit;	
	protected boolean Fighting;
	
	public SoldierUnit(Canvas defaultCanvas, Unit u, String picture) {
		this.unit = u;
		spriteManager = new SpriteManagerDefaultImpl(picture,
				defaultCanvas, RENDERING_SIZE, 13);
		spriteManager.setTypes(
				"unused","unused", "unused", "unused",
				"up-th", "left-th", "down-th", "right-th",
				"up", "left", "down", "right",
				"up-sword", "left-sword", "down-sword", "right-sword", 
				"hurt");
	}

	public void draw(Graphics g) {
		String spriteType = "";
		Point tmp = getSpeedVector().getDirection();
		
		if(!this.unit.alive()){
			spriteType="hurt";
					
		}else{
		if(!Fighting){
			movable = true;
			if (tmp.getX() == 1) {
				spriteType += "right";
			} else if (tmp.getX() == -1) {
				spriteType += "left";
			} else if (tmp.getY() == 1) {
				spriteType += "down";
			} else if (tmp.getY() == -1) {
				spriteType += "up";
			} else {
				if(Direction.equalsIgnoreCase(""))
					spriteType = "down";
				else
					spriteType = this.Direction;
				spriteManager.reset();
				movable = false;
			}
			this.Direction = spriteType;
		}
		else
		{
			movable = true;
			spriteType += Direction + "-";
			spriteType += weapon;	
			Fighting = false; 
		}
	}
		spriteManager.setType(spriteType);
		spriteManager.draw(g, getPosition());
	}
	
	@Override
	public void oneStepMoveAddedBehavior() {
		if (movable) {
			spriteManager.increment();
		}
	}

	
	public Rectangle getBoundingBox() {
		return (new Rectangle(this.getPosition().x+20, this.getPosition().y+20, RENDERING_SIZE-40, RENDERING_SIZE-49));
	}
	
	
	public void setTypeWeapon(String weapon){
		this.weapon = weapon;
	}
	
		
	public void setFight(boolean f){
		this.Fighting = f;
	}
	
	
	
	public boolean isAlive(){
	    return this.unit.alive();
	  }
	
	public float Strike(){
		return this.unit.strike();
	}
	
	
	public float Attack(){
		return this.unit.parry(Strike());
	}
	
	public float HealthPoint(){
		return this.unit.getHealthPoints();
	}
	
	
	
	
}
