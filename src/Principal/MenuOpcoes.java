package Principal;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuOpcoes extends BasicGameState {

	public static final int ID = 2;
	GameContainer gc;
	StateBasedGame game;
	String[] opcao = {"Volume","Tela","Voltar"};
	int select;
	
	
	@Override
	public void init(GameContainer gc, StateBasedGame game)	throws SlickException {
		this.gc = gc;
		this.game = game;		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		
		for(int i = 0; i < this.opcao.length; i++){
			g.drawString(this.opcao[i], gc.getWidth()/2, gc.getHeight()/2 +(40 * i));
			
			if(i == this.select){
				g.drawRect(gc.getWidth()/2, gc.getHeight()/2 + (40 * i), 100, 20);
			}
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int i) throws SlickException {
		
	}

	@Override
	public int getID() {
		return ID;
	}

	public void keyPressed(int key,char c){
		
		if(key == Input.KEY_UP){
			this.select--;
			if(this.select < 0){
				this.select = this.opcao.length -1;
			}
		}
	
		if(key == Input.KEY_DOWN){
			this.select++;
			if(this.select >= this.opcao.length){
				this.select = 0;
			}
		}
	
		if(key == Input.KEY_ENTER){
			if(this.opcao[this.select].equals("Volume")){
				System.out.println("Volume");
			}
			
			else if(this.opcao[this.select].equals("Tela")){
				System.out.println("Tela");
			}
			
			else if(this.opcao[this.select].equals("Voltar")){
				this.game.enterState(new MenuMain().getID());
			}
		}
		
	
	}
	
}
