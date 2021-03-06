package Principal;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class Inimigo {

	public Pokemon pokemon;
	public boolean atacou;
	public int cooldownAtaque = 50;
	int x = (int) (50 + (Math.random() * 550));

	public Inimigo(Pokemon pokemon) {
		this.pokemon = pokemon;
		this.pokemon.setX(x);
		this.pokemon.setY(0);
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) {
		this.pokemon.update(gc, game, delta);
		this.anda();

		if (cooldownAtaque > 0) {
			this.cooldownAtaque--;
		}
		this.ataca();
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics g) {
		this.pokemon.render(gc, game, g);
	}

	public void anda() {

		int getY = this.pokemon.getY();
		if (getY == 700) {
			this.pokemon.setX((int) (50 + (Math.random() * 550)));
			this.pokemon.setY(0);

		} else {
			int y = this.pokemon.getY();
			y = y + 1;
			this.pokemon.setY(y);
		}

	}

	public void ataca() {
		if (this.cooldownAtaque <= 0) {
			this.atacou = true;
		}
	}

}
