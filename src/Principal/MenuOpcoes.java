package Principal;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuOpcoes extends BasicGameState {

	public static final int ID = 2;
	GameContainer gc;
	StateBasedGame game;
	String[] opcao = { "Volume", "Tela", "Voltar" };
	String[] volume = { "Musica", "Audio" };
	String[] tela = { "FullScreen", "Resolução" };
	int select, selVolume, selTela;
	boolean boolVolume, boolTela = false;
	boolean boolOpcao = true;

	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
		this.gc = gc;
		this.game = game;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g)
			throws SlickException {

		// Desenha Opções
		for (int i = 0; i < this.opcao.length; i++) {
			g.drawString(this.opcao[i], gc.getWidth() / 2 - 100, gc.getHeight()
					/ 2 + (150 * i) - 300);

			if (boolOpcao) {
				if (i == this.select) {
					g.drawRect(gc.getWidth() / 2 - 120, gc.getHeight() / 2
							+ (150 * i) - 300, 100, 20);
				}
			}
		}

		// Desenha Volume

		for (int f = 0; f < this.volume.length; f++) {
			g.drawString(this.volume[f], gc.getWidth() / 2 - 60, gc.getHeight()
					/ 2 + (50 * f) - 250);

			if (boolVolume) {
				if (f == this.selVolume) {
					g.drawRect(gc.getWidth() / 2 - 80, 100 + (50 * f), 100, 20);
				}
				if (this.volume[f].equals("Musica")) {
					g.drawRect(gc.getWidth() / 2 + 50,
							gc.getHeight() / 2 - 250, 100, 20);
					g.fillRect(gc.getWidth() / 2 + 50,
							gc.getHeight() / 2 - 250,
							gc.getMusicVolume() * 100, 20);
				}
				if (this.volume[f].equals("Audio")) {
					g.drawRect(gc.getWidth() / 2 + 50,
							gc.getHeight() / 2 - 200, 100, 20);
					g.fillRect(gc.getWidth() / 2 + 50,
							gc.getHeight() / 2 - 200,
							gc.getSoundVolume() * 100, 20);
				}
			}
		}

		// Desenha Tela

		for (int t = 0; t < this.tela.length; t++) {
			g.drawString(this.tela[t], gc.getWidth() / 2 - 60, gc.getHeight()
					/ 2 + (50 * t) - 100);
			if (boolTela) {
				if (t == this.selTela) {
					g.drawRect(gc.getWidth() / 2 - 60, gc.getHeight() / 2
							+ (50 * t) - 100, 100, 20);
				}
			}
		}

	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int i)
			throws SlickException {

	}

	@Override
	public int getID() {
		return ID;
	}

	public void keyPressed(int key, char c) {

		if (key == Input.KEY_UP) {

			if (boolOpcao) {
				this.select--;
				if (this.select < 0) {
					this.select = this.opcao.length - 1;
				}
			}

			if (boolVolume) {
				this.selVolume--;
				if (this.selVolume < 0) {
					this.selVolume = this.volume.length - 1;
				}
			}

			if (boolTela) {
				this.selTela--;
				if (this.selTela < 0) {
					this.selTela = this.tela.length - 1;
				}
			}

		}

		if (key == Input.KEY_DOWN) {
			if (boolOpcao) {
				this.select++;
				if (this.select >= this.opcao.length) {
					this.select = 0;
				}
			}

			if (boolVolume) {
				this.selVolume++;
				if (this.selVolume >= this.volume.length) {
					this.selVolume = 0;
				}
			}

			if (boolTela) {
				this.selTela++;
				if (this.selTela >= this.tela.length) {
					this.selTela = 0;
				}
			}

		}

		if (key == Input.KEY_LEFT) {
			if (boolVolume) {
				if (this.volume[this.selVolume].equals("Musica")) {
					if (gc.getMusicVolume() > 0) {
						gc.setMusicVolume(gc.getMusicVolume() - 0.1f);
					}
				}
				if (this.volume[this.selVolume].equals("Audio")) {
					if (gc.getSoundVolume() > 0) {
						gc.setSoundVolume(gc.getSoundVolume() - 0.1f);
					}
				}
			}
		}

		if (key == Input.KEY_RIGHT) {
			if (boolVolume) {
				if (this.volume[this.selVolume].equals("Musica")) {
					if (gc.getMusicVolume() < 1) {
						gc.setMusicVolume(gc.getMusicVolume() + 0.1f);
					}
				}
				if (this.volume[this.selVolume].equals("Audio")) {
					if (gc.getSoundVolume() < 1) {
						gc.setSoundVolume(gc.getSoundVolume() + 0.1f);
					}
				}
			}
		}

		if (key == Input.KEY_ENTER) {
			if (this.opcao[this.select].equals("Volume")) {
				this.boolOpcao = false;
				this.boolTela = false;
				this.boolVolume = true;
			}

			else if (this.opcao[this.select].equals("Tela")) {
				this.boolOpcao = false;
				this.boolTela = true;
				this.boolVolume = false;
			}

			else if (this.opcao[this.select].equals("Voltar")) {
				this.game.enterState(new MenuMain().getID());
			}
		}

		if (key == Input.KEY_ESCAPE || key == Input.KEY_BACK) {
			this.boolOpcao = true;
			this.boolVolume = false;
			this.boolTela = false;
		}

	}

}
