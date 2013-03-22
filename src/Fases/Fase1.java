package Fases;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Principal.Inimigo;
import Principal.Player;
import Principal.Pokemon;

public class Fase1 extends BasicGameState{
	
	public static final int ID = 3;
	StateBasedGame game;
	Player player;
	Pokemon pokemon , pokinimigo;
	Inimigo inimigo;

	public Fase1(){
		
	}
	
	@Override
	public int getID() {
		return ID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame game)throws SlickException {
		this.game = game;
		pokemon = new Pokemon(1, "Bulbasaur", "Player");
		pokinimigo = new Pokemon(1,"Bulbasaur","Inimigo");
		this.player = new Player(pokemon, 100, 100);
		this.inimigo = new Inimigo(pokinimigo, 100, 100);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int i)throws SlickException {
		this.player.update(gc, game, i);
		this.inimigo.update(gc, game, i);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g)throws SlickException {
		this.player.render(gc, game, g);
		this.inimigo.render(gc, game, g);
		
		g.drawRoundRect(gc.getWidth()/2 + 110, gc.getHeight()/2 + 180, 180, 150, 10);
		g.drawString(pokemon.getNome(), gc.getWidth()/2 + 120, gc.getHeight()/2 + 190);
		g.drawString(" "+ pokemon.getLvl(), gc.getWidth()/2 + 250 , gc.getHeight()/2 + 190);
	}
	
}
