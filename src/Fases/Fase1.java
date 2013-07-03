package Fases;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import Ataques.Ataque;
import Principal.Inimigo;
import Principal.Mapa;
import Principal.Player;
import Principal.Pokemon;
import Telas.CharacterSelect;

import DAO.AtaqueDAO;
import DAO.PokemonDAO;

public class Fase1 extends BasicGameState {

	public static final int ID = 4;
	StateBasedGame game;
	CharacterSelect charSelect;
	Player player;
	ArrayList<model.Pokemon> listaInimigos;
	ArrayList<Inimigo> InimigoLista;
	ArrayList<String> listaNomes;
	ArrayList<Ataque> ataquesPlayer, ataquesInimigo;
	ArrayList<model.Ataque> listaAtaques;
	Pokemon pokemon, pokemonInimigo;
	Inimigo inimigo;
	boolean inicializou = false;
	int cooldown = 0;
	
	Mapa map;
	int MapY = -2500;
	int cooldownMap = 0;

	public Fase1(CharacterSelect charSelect) {
		this.charSelect = charSelect;
		System.out.println("FASE1 - " + charSelect.player1);
	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
		this.game = game;
		this.map = new Mapa("fase1", MapY,"Poison");
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int i)
			throws SlickException {
		this.player.update(gc, game, i);

		for (Inimigo x : this.InimigoLista) {
			x.update(gc, game, i);
		}

		for (Ataque a : this.ataquesPlayer) {
			a.update(gc, game, i);
		}

		// this.lancaAtaques();
		this.CriaInimigo();
		this.MoveMapa(1);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g)
			throws SlickException {
		if (!inicializou) {
			this.inicializa();
		}
		this.map.render(gc, game, g);
		this.player.render(gc, game, g);

		for (Inimigo i : this.InimigoLista) {
			i.render(gc, game, g);
		}

		g.drawRoundRect(gc.getWidth() / 2 + 110, gc.getHeight() / 2 + 180, 180,
				150, 10);
		g.drawString(pokemon.getNome(), gc.getWidth() / 2 + 120,
				gc.getHeight() / 2 + 190);
		g.drawString(" " + pokemon.getLvl(), gc.getWidth() / 2 + 250,
				gc.getHeight() / 2 + 190);
		g.drawRoundRect(gc.getWidth() / 2 + 120, gc.getHeight() / 2 + 220, 150,
				20, 0);
		g.setColor(Color.green);
		g.fillRect(gc.getWidth() / 2 + 120, gc.getHeight() / 2 + 220,
				(pokemon.getHp() * 150) / pokemon.getVidaInicial(), 20);
		g.setColor(Color.white);

		for (Ataque a : this.ataquesPlayer) {
			a.render(gc, game, g);
		}
	}

	public void inicializa() {

		System.out.println("ERRO - " + this.charSelect.getPlayer1());
		pokemon = new Pokemon(this.charSelect.getIdPokemonPlayer1(),
				this.charSelect.getPlayer1(), "Player");

		this.InimigoLista = new ArrayList<Inimigo>();

		this.player = new Player(pokemon, 300, 600);
		
		this.listaInimigos = PokemonDAO.getLista();
		this.InimigoLista = new ArrayList<Inimigo>();
		this.ataquesPlayer = new ArrayList<Ataque>();
		this.ataquesInimigo = new ArrayList<Ataque>();
		this.listaAtaques = new ArrayList<model.Ataque>();
		this.listaNomes = new ArrayList<String>();

		this.listaAtaques = AtaqueDAO.getListaAtaque();

		this.inicializou = true;
		
		this.TipoInimigo();

	}

	public void lancaAtaques() {
		if (this.player.atacou == true && this.player.cooldownAtaque <= 0) {

			model.Ataque a = AtaqueDAO.getPoder(this.player.pokemon.getNome());
			String s = "Ataques." + a.getNome();
			try {
				while (s.equals("Ataques.Metronome")) {
					int rand = util.Util.random(this.listaAtaques.size());
					s = "Ataques." + this.listaAtaques.get(rand - 1).getNome();
				}
				Class cls = Class.forName(s);
				Class[] parameters = new Class[] { int.class, int.class,
						Pokemon.class };
				java.lang.reflect.Constructor con = cls
						.getConstructor(parameters);
				Object o = con.newInstance(new Object[] {
						this.player.pokemon.getX(), this.player.pokemon.getY(),
						this.player.pokemon });
				this.ataquesPlayer.add((Ataque) o);
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
				JOptionPane
						.showMessageDialog(null,
								"ERROR: classe " + ex.getMessage()
										+ " não encontrada");
				System.exit(1);
			} catch (IllegalAccessException ex2) {
				ex2.printStackTrace();
				JOptionPane.showMessageDialog(null,
						"2ERROR: " + ex2.getMessage());
				System.exit(1);
			} catch (InstantiationException ex3) {
				ex3.printStackTrace();
				JOptionPane.showMessageDialog(null,
						"3ERROR: " + ex3.getMessage());
				System.exit(1);
			} catch (NoSuchMethodException ex4) {
				ex4.printStackTrace();
				JOptionPane.showMessageDialog(null,
						"4ERROR: " + ex4.getMessage());
				System.exit(1);
			} catch (IllegalArgumentException ex5) {
				ex5.printStackTrace();
				JOptionPane.showMessageDialog(null,
						"5ERROR: " + ex5.getMessage());
				System.exit(1);
			} catch (InvocationTargetException ex6) {
				ex6.printStackTrace();
				JOptionPane.showMessageDialog(null,
						"6ERROR: " + ex6.getMessage());
				System.exit(1);
			} catch (SecurityException ex7) {
				ex7.printStackTrace();
				JOptionPane.showMessageDialog(null,
						"7ERROR: " + ex7.getMessage());
				System.exit(1);
			}

			// this.ataquesPlayer.add(new Ember(this.player.pokemon.getX(),
			// this.player.pokemon.getY(), this.player.pokemon));
			this.player.atacou = false;
			this.player.cooldownAtaque = 120;
		}

		for (Inimigo i : this.InimigoLista) {
			if (i.atacou == true && i.cooldownAtaque <= 0) {

				model.Ataque a = AtaqueDAO.getPoder(i.pokemon.getNome());
				String s = "Ataques." + a.getNome();
				try {
					while (s.equals("Ataques.Metronome")) {
						int rand = util.Util.random(this.listaAtaques.size());
						s = "Ataques."
								+ this.listaAtaques.get(rand - 1).getNome();
					}
					Class cls = Class.forName(s);
					Class[] parameters = new Class[] { int.class, int.class,
							Pokemon.class };
					java.lang.reflect.Constructor con = cls
							.getConstructor(parameters);
					Object o = con.newInstance(new Object[] { i.pokemon.getX(),
							i.pokemon.getY(), i.pokemon });
					this.ataquesPlayer.add((Ataque) o);
				} catch (ClassNotFoundException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"ERROR: classe " + ex.getMessage()
									+ " não encontrada");
					System.exit(1);
				} catch (IllegalAccessException ex2) {
					ex2.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"2ERROR: " + ex2.getMessage());
					System.exit(1);
				} catch (InstantiationException ex3) {
					ex3.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"3ERROR: " + ex3.getMessage());
					System.exit(1);
				} catch (NoSuchMethodException ex4) {
					ex4.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"4ERROR: " + ex4.getMessage());
					System.exit(1);
				} catch (IllegalArgumentException ex5) {
					ex5.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"5ERROR: " + ex5.getMessage());
					System.exit(1);
				} catch (InvocationTargetException ex6) {
					ex6.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"6ERROR: " + ex6.getMessage());
					System.exit(1);
				} catch (SecurityException ex7) {
					ex7.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"7ERROR: " + ex7.getMessage());
					System.exit(1);
				}

				i.atacou = false;
				i.cooldownAtaque = 120;
			}
		}

	}

	public void CriaInimigo() {
		int index = 0;
		if (this.cooldown <= 0) {
			int i = (int) (0 + (Math.random() * (this.listaNomes.size() -1)  ));
						
			String nomeIni = this.listaNomes.get(i);
			System.out.println(i);
			i++;
			for(model.Pokemon p: this.listaInimigos){
				if(p.getNome() == nomeIni){
					index = p.getId();
				}
			}
			
			int iniID = index; 
			
			System.out.println(nomeIni +" "+ index);
			pokemonInimigo = new Pokemon(iniID, nomeIni, "Inimigo");
			this.inimigo = new Inimigo(pokemonInimigo);
			InimigoLista.add(this.inimigo);

			this.cooldown = (int) (100 + (Math.random() * 500));
		} else {
			this.cooldown--;
		}
	}

	public void MoveMapa(int MapVelo) {
		if (this.cooldownMap >= MapVelo) {
			if (this.MapY <= 0) {
				this.map.MoveMapa(MapY);
				this.MapY++;
			}
			this.cooldownMap = 0;
		} else {
			this.cooldownMap++;
		}
	}
	
	public void TipoInimigo(){
		for(model.Pokemon p: this.listaInimigos){
			if(p.getElementoPrimarioString().equals(this.map.getTipo())){
				this.listaNomes.add(p.getNome());
			}
		}
	}
	
	
}
