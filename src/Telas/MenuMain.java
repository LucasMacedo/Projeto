package Telas;



import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuMain extends BasicGameState {

	public static final int ID = 1;
	GameContainer gc;
	StateBasedGame game;
	String[] opcao = {"Iniciar","Opções","Sair"};
	Image img, imgIcone;
	private int selected;
	
	@Override
	public int getID() {
		return ID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame game)	throws SlickException {
		this.img = new Image("resources/Fases/Title.png");
		this.imgIcone = new Image("resources/icone.png");
		this.gc = gc;
		this.game = game;
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		g.setBackground(Color.white);
		this.img.drawCentered(gc.getWidth()/2, gc.getHeight()/2);
		
		
		for(int i = 0; i < this.opcao.length; i++){
			g.setColor(Color.black);
			g.drawString(opcao[i], gc.getWidth()/2 - 50, 500 + (40 * i));
			
			if(i == this.selected){
				this.imgIcone.draw(gc.getWidth()/2 - 70, 500 + (40 * i));
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
				this.game.enterState( CharacterSelect.ID);
			}
			
			if (this.opcao[this.selected].equals("Opções")){
				this.game.enterState(MenuOpcoes.ID);
			}
			
			
			if (this.opcao[this.selected].equals("Sair")){
				this.gc.exit();
			}
		}
		
}}
	
	

