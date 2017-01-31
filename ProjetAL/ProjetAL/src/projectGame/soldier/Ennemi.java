package projectGame.soldier;


import gameframework.moves_rules.MoveBlocker;
import soldier.core.Unit;

import java.awt.Canvas;
import java.awt.Graphics;

import java.awt.Rectangle;




public class Ennemi extends SoldierUnit implements MoveBlocker {

	protected boolean isFighting;
	
	public Ennemi(Canvas defaultCanvas, Unit u) {
		super(defaultCanvas, u, "images/sprite.png");
	}

	public void draw(Graphics g) {
		super.draw(g);
	}
	
	@Override
	public void oneStepMoveAddedBehavior() {
		super.oneStepMoveAddedBehavior();
	}

	public Rectangle getBoundingBox() {
		return super.getBoundingBox();
	}
	
	
	public void setTypeWeapon(String weapon){
		super.setTypeWeapon(weapon);
	}
	
	public void setFight(boolean f){
		super.setFight(f);
	}

	
}
