package Principal;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

public class Player{

	Pokemon pokemon;
	
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
		if(input.isKeyDown(input.KEY_A)){
			this.pokemon.moveEsquerda(this.pokemon.speed);
		}
		if(input.isKeyDown(input.KEY_S)){
			this.pokemon.moveBaixo(this.pokemon.speed);
		}
		if(input.isKeyDown(input.KEY_D)){
			this.pokemon.moveDireita(this.pokemon.speed);
		}
		if(input.isKeyDown(input.KEY_W)){
			this.pokemon.moveCima(this.pokemon.speed);
		}
	}
}
