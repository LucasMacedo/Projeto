package Principal;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class Mapa extends GameObject {
		
	TiledMap mapa;
	
	
	public Mapa(String mapa, int y){
		try {
			this.mapa = new TiledMap("resources/Fases/"+mapa+".tmx");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.y = y;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) {
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) {
		this.mapa.render(x, y);
	}
	
	public void MoveMapa(int y){
		this.setX(0);
		this.setY(y);
	}

}
