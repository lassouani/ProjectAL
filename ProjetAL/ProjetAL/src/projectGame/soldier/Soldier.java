package projectGame.soldier;



import soldier.core.Unit;

import java.awt.Canvas;
import java.awt.Graphics;

import java.awt.Rectangle;




public class Soldier extends SoldierUnit {
	
	public Soldier(Canvas defaultCanvas, Unit u) {
		super(defaultCanvas, u, "images/soldier.gif");
	}

	public void draw(Graphics g) {
		super.draw(g);
	}
	
	@Override
	public void oneStepMoveAddedBehavior() {
		super.oneStepMoveAddedBehavior();
	}

	public Rectangle getBoundingBox() {
		return (new Rectangle(0, 0, RENDERING_SIZE, RENDERING_SIZE));
	}
	
	
	public void setTypeWeapon(String weaponType){
		super.setTypeWeapon(weaponType);
	}
	
	public void setFight(boolean f){
		super.setFight(f);
	}
}
