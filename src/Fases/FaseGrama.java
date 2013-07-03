package Fases;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import Principal.Mapa;
import Telas.CharacterSelect;

public class FaseGrama extends Fase {
	
	public static final int ID = 4;

	public FaseGrama(CharacterSelect charSelect) {
		this.charSelect = charSelect;
		String[] elementos = {"Grass", "Flying", "Normal"};
		this.setElementosPermitidos(elementos);
	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame game)	throws SlickException {
		this.game = game;
		this.map = new Mapa("fase1", MapY,"Poison");
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int i) throws SlickException {
		super.update(gc, game, i);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		super.render(gc, game, g);
	}
}
