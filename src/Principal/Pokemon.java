package Principal;

import model.PokemonLiberado;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import DAO.PokemonDAO;
import DAO.PokemonLiberadoDAO;

public class Pokemon extends GameObject {

	protected int id;
	protected String nome;
	protected int atk;
	protected int def;
	protected int speed;
	protected int hp;
	protected int lvl;
	protected int VidaInicial;

	public String tipo;
	public Animacao animacaoCima;
	public Animacao animacaoBaixo;
	public Animacao animacaoAtual;

	public Pokemon() {
	}

	public Pokemon(int id, String nome, String tipo) {// tipo seria player ou
														// inimigo
		
		
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;

		this.SetaPokemon(id);
		
		// //// mudar
		this.speed = 5;
		// /// mudar

		try {
			if (tipo.equals("Player")) {
				this.animacaoCima = new Animacao(300);
				this.animacaoCima.add(new Image("resources/personagens/" + id
						+ " - " + nome + "/" + nome + "_Up.png"));
				this.animacaoCima.add(new Image("resources/personagens/" + id
						+ " - " + nome + "/" + nome + "_Up2.png"));
				this.animacaoAtual = this.animacaoCima;
			} else {
				this.animacaoBaixo = new Animacao(300);
				this.animacaoBaixo.add(new Image("resources/personagens/" + id
						+ " - " + nome + "/" + nome + "_Down.png"));
				this.animacaoBaixo.add(new Image("resources/personagens/" + id
						+ " - " + nome + "/" + nome + "_Down2.png"));
				this.animacaoAtual = this.animacaoBaixo;
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) {
		if (this.tipo.equals("Player")) {
			this.animacaoCima.update();
		} else {
			this.animacaoBaixo.update();
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) {
		if (this.tipo.equals("Player")) {
			this.animacaoCima.render(this.x, this.y, 1, true);
		} else {
			this.animacaoBaixo.render(this.x, this.y, 1, true);
		}

	}
	
	public Rectangle getRetangulo(){
		if (this.tipo.equals("Player")) {
			Rectangle rect = new Rectangle((int) this.x, (int) this.y, this.animacaoCima.getImage().getWidth(), this.animacaoCima.getImage().getHeight());
	        return rect;
		} else {
			Rectangle rect = new Rectangle((int) this.x, (int) this.y, this.animacaoBaixo.getImage().getWidth(), this.animacaoBaixo.getImage().getHeight());
	        return rect;
		}
	}


	public void perdeVida(int quantia){
		this.setHp(this.getHp()-quantia);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;	
	}

	public int getVidaInicial(){
		return VidaInicial;
	}
	
	public void setVidaInicial(int VidaInicial){
		this.VidaInicial = VidaInicial;
	}
	
	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}


	public void SetaPokemon(int id){
		model.Pokemon poke = PokemonDAO.getPokemon(id);
		//arrumar, botar nas formulas
			atk = poke.getAtkBase();
			def = poke.getAtkBase();
			hp = poke.getHpBase();
			lvl = 1;//poke.getLvl();
			hp += (((hp + 1 / 8 + 50) * lvl) / 50 + 10);
			VidaInicial = hp;
	}



}
