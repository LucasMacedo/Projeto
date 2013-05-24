package Fases;

import java.util.ArrayList;

import org.newdawn.slick.Color;
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
	ArrayList<Inimigo> InimigoLista;
	ArrayList<Ataque> ataquesPlayer, ataquesInimigo;
	Pokemon pokemon, pokemonInimigo;
	Inimigo inimigo;
	boolean inicializou = false;
	int cooldown = 0;
	int VidaInicial;
	
	

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
		this.InimigoLista = new ArrayList<Inimigo>();
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int i)throws SlickException {		
		this.player.update(gc, game, i);
		
		for(Inimigo x : this.InimigoLista){
			x.update(gc, game, i);
		}
		
		this.lancaAtaques();
		
			
			if(this.cooldown <= 0){
				this.CriaInimigo();
				this.cooldown = (int) (100 + (Math.random()* 500));
			}else{
			this.cooldown--;
			}
		
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g)throws SlickException {
		if(!inicializou){
			this.inicializa();
		}
		
		this.player.render(gc, game, g);
		
		
		for(Inimigo i : this.InimigoLista){
			i.render(gc, game, g);
		}
		
		g.drawRoundRect(gc.getWidth()/2 + 110, gc.getHeight()/2 + 180, 180, 150, 10);
		g.drawString(pokemon.getNome(), gc.getWidth()/2 + 120, gc.getHeight()/2 + 190);
		g.drawString(" "+ pokemon.getLvl(), gc.getWidth()/2 + 250 , gc.getHeight()/2 + 190);
		g.drawRoundRect(gc.getWidth()/2 + 120, gc.getHeight()/2 + 220, 150, 20, 0);
		g.setColor(Color.green);
		g.fillRect(gc.getWidth()/2 + 120, gc.getHeight()/2 + 220, (pokemon.getHp()*150)/this.VidaInicial, 20);
		g.setColor(Color.white);
		
		
		for(Ataque a: this.ataquesPlayer){
			a.render(gc, game, g);
		}
	}
	
	public void inicializa(){

		System.out.println("ERRO - " + this.charSelect.getPlayer1());
		pokemon = new Pokemon(this.charSelect.getIdPokemonPlayer1(), this.charSelect.getPlayer1(), "Player");
		
		this.player = new Player(pokemon, 100, 100);
				
		this.ataquesPlayer = new ArrayList<Ataque>();
		this.ataquesInimigo = new ArrayList<Ataque>();
		this.inicializou = true;
		
		this.VidaInicial = pokemon.getHp();
	}
	
	
	public void lancaAtaques(){
		if(this.player.atacou == true){
			this.ataquesPlayer.add(new Ember(this.player.pokemon.getX(), this.player.pokemon.getY(), this.player.pokemon));
			this.player.atacou = false;
		}
	}
	
	public void CriaInimigo(){
		int i = (int) ( 1 + (Math.random()* 38));	
			String nomeIni = this.listaInimigos.get(i).getNome();
			int iniID = this.listaInimigos.get(i).getId();
		pokemonInimigo = new Pokemon(iniID,nomeIni,"Inimigo");
		this.inimigo = new Inimigo(pokemonInimigo);
		InimigoLista.add(this.inimigo);
		
	}
	
}
