package Fases;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Ataques.Ataque;
import Principal.Inimigo;
import Principal.Player;
import Principal.Pokemon;
import Telas.CharacterSelect;

import Ataques.Ember;
import DAO.PokemonDAO;


public class Fase1 extends BasicGameState{
	
	public static final int ID = 4;
	StateBasedGame game;
	CharacterSelect charSelect;
	Player player;
	ArrayList<model.Pokemon> listaInimigos;
	ArrayList<Ataque> ataquesPlayer, ataquesInimigo;
	Pokemon pokemon, pokemonInimigo;
	Inimigo inimigo;
	boolean inicializou = false;

	public Fase1(CharacterSelect charSelect){
		this.charSelect = charSelect;
		System.out.println("FASE1 - "+charSelect.player1);
	}
	
	@Override
	public int getID() {
		return ID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame game)throws SlickException {
		this.game = game;
		this.listaInimigos = PokemonDAO.getLista();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int i)throws SlickException {		
		this.player.update(gc, game, i);
		this.inimigo.update(gc, game, i);
		
		this.lancaAtaques();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g)throws SlickException {
		if(!inicializou){
			this.inicializa();
		}
		this.player.render(gc, game, g);
		this.inimigo.render(gc, game, g);
		
		g.drawRoundRect(gc.getWidth()/2 + 110, gc.getHeight()/2 + 180, 180, 150, 10);
		g.drawString(pokemon.getNome(), gc.getWidth()/2 + 120, gc.getHeight()/2 + 190);
		g.drawString(" "+ pokemon.getLvl(), gc.getWidth()/2 + 250 , gc.getHeight()/2 + 190);
		
		for(Ataque a: this.ataquesPlayer){
			a.render(gc, game, g);
		}
	}
	
	public void inicializa(){

		System.out.println("ERRO - " + this.charSelect.getPlayer1());
		pokemon = new Pokemon(this.charSelect.getIdPokemonPlayer1(), this.charSelect.getPlayer1(), "Player");
		
		
		for(int x = 1 ; x < 10 ;x++){	
			
		int i = (int) ( 1 + (Math.random()* 40));	
			String nomeIni = this.listaInimigos.get(i).getNome();
			int iniID = this.listaInimigos.get(i).getId();
		pokemonInimigo = new Pokemon(iniID,nomeIni,"Inimigo");
		System.out.print(pokemonInimigo.getNome());
		this.inimigo = new Inimigo(pokemonInimigo);
		}
		
		
		this.player = new Player(pokemon, 100, 100);
		
		
		this.ataquesPlayer = new ArrayList<Ataque>();
		this.ataquesInimigo = new ArrayList<Ataque>();
		this.inicializou = true;
	}
	
	public void lancaAtaques(){
		if(this.player.atacou == true){
			this.ataquesPlayer.add(new Ember(this.player.pokemon.getX(), this.player.pokemon.getY(), this.player.pokemon));
			this.player.atacou = false;
		}
	}
	
}
