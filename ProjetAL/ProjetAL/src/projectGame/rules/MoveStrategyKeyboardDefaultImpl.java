package projectGame.rules;


import java.awt.event.KeyEvent;

import projectGame.soldier.SoldierUnit;
import gameframework.moves_rules.MoveStrategyKeyboard;


public class MoveStrategyKeyboardDefaultImpl extends MoveStrategyKeyboard  {
	SoldierUnit player;
	
	
	public MoveStrategyKeyboardDefaultImpl(SoldierUnit player){
		this.player = player;
	}
	
	@Override
	public void keyPressed(KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.VK_SPACE)
			player.setFight(true);
		else{
			super.keyPressed(event);
		}
	}
	
		
}
