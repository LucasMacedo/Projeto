package Principal;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class Inimigo{

	Pokemon pokemon;
	int x , y;
	
	public Inimigo(Pokemon pokemon, int x, int y){
		this.pokemon = pokemon;
		this.pokemon.setX(x);
		this.pokemon.setY(y);
	}
	
	public void update(GameContainer gc, StateBasedGame game, int delta) {
		this.pokemon.update(gc, game, delta);
		this.anda();
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics g) {
		this.pokemon.render(gc, game, g);
	}
	
	public void anda(){
	
		int getY = this.pokemon.getY();
	if(getY == 700){
		this.pokemon.setY(1);
		
	}else{
		y = this.pokemon.getY();
		y = y + 1;
		this.pokemon.setY(y);
	}
			
	}
	
	
}
