package projectGame.entity;

import gameframework.core.Drawable;
import gameframework.core.DrawableImage;
import gameframework.core.GameEntity;
import gameframework.core.Overlappable;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import soldier.core.BehaviorSoldier;
import soldier.core.WeaponDefense;

public class Shield extends WeaponDefense implements Drawable, GameEntity, Overlappable{

	protected static DrawableImage image = null;
	protected Point position;
	public static final int RENDERING_SIZE = 40;
	
	
	public Shield(Canvas defaultCanvas, Point pos) {
		image = new DrawableImage("images/shield.png", defaultCanvas);
		position = pos;
	}

	public Point getPosition() {
		return position;
	}

	public void draw(Graphics g) {
		g.drawImage(image.getImage(), (int) getPosition().getX(),
				(int) getPosition().getY(), RENDERING_SIZE, RENDERING_SIZE,
				null);

	}
	
	public Rectangle getBoundingBox() {
		return (new Rectangle((int) position.getX(), (int) position.getY(),
				RENDERING_SIZE, RENDERING_SIZE));
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "shield";
	}

	@Override
	public BehaviorSoldier createExtension(BehaviorSoldier s) {
		// TODO Auto-generated method stub
		return null;
	}

}
