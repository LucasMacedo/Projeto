package Fases;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import Principal.Player;
import Principal.Pokemon;

public class Fase1 extends BasicGameState{
	
	public static final int ID = 3;
	StateBasedGame game;
	Player player;

	public Fase1(){
		
	}
	
	@Override
	public int getID() {
		return Fase1.ID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame game)throws SlickException {
		this.game = game;
		Pokemon pokemon = new Pokemon(1, "Bulbasaur", "Player");
		this.player = new Player(pokemon, 100, 100);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int i)throws SlickException {
		this.player.update(gc, game, i);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g)throws SlickException {
		this.player.render(gc, game, g);
	}
	
}
