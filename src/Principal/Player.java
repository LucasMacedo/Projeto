package Principal;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

public class Player{

	public Pokemon pokemon;
	public boolean atacou;
	
	public Player(Pokemon pokemon, int x, int y){
		this.pokemon = pokemon;
		this.pokemon.setX(x);
		this.pokemon.setY(y);
	}
	
	public void update(GameContainer gc, StateBasedGame game, int delta) {
		this.input(gc.getInput());
		this.pokemon.update(gc, game, delta);
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics g) {
		this.pokemon.render(gc, game, g);
	}
	
	public void input(Input input){
		if(input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT)){
			this.pokemon.moveEsquerda(this.pokemon.speed);
		}
		if(input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN)){
			this.pokemon.moveBaixo(this.pokemon.speed);
		}
		if(input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT)){
			this.pokemon.moveDireita(this.pokemon.speed);
		}
		if(input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP)){
			this.pokemon.moveCima(this.pokemon.speed);
		}
		
		if(input.isKeyDown(Input.KEY_SPACE)){
			this.ataca();
		}				
	}
	
	public void ataca(){
		this.atacou = true;
	}
}
