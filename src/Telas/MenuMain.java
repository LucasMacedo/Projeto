package Telas;



import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Fases.Fase1;

public class MenuMain extends BasicGameState {

	public static final int ID = 1;
	GameContainer gc;
	StateBasedGame game;
	String[] opcao = {"Iniciar","Opções","Sair"};
	private int selected;
	
	@Override
	public int getID() {
		return ID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame game)	throws SlickException {
		this.gc = gc;
		this.game = game;
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		
		for(int i = 0; i < this.opcao.length; i++){
			g.drawString(opcao[i], gc.getWidth()/2, 120 + (40 * i));
			
			if(i == this.selected){
				g.drawRect(gc.getWidth()/2 - 20, 120 + (40 * i),100,20);
			}
			
			
		}
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int i)
			throws SlickException {
	}

	
	public void keyPressed(int key,char c){
		
		if (key == Input.KEY_UP){
			this.selected--;
			if (this.selected < 0){
				this.selected = this.opcao.length -1;
			}
		}
			
		if (key == Input.KEY_DOWN){
			this.selected++;
			if(this.selected >= this.opcao.length){
				this.selected = 0;
			}
		}
 		
		if (key == Input.KEY_ENTER){
			if(this.opcao[this.selected].equals("Iniciar")){
				this.game.enterState(new Fase1().getID());
			}
			
			if (this.opcao[this.selected].equals("Opções")){
				this.game.enterState(new MenuOpcoes().getID());
			}
			
			
			if (this.opcao[this.selected].equals("Sair")){
				this.gc.exit();
			}
		}
		
}}
	
	

