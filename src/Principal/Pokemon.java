package Principal;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

public class Pokemon extends GameObject{
	
	protected int id;
	protected String nome;
	protected int atk;
	protected int def;
	protected int speed;
	protected int hp;
	protected int lvl;
	
	String tipo;
	protected Animacao animacaoCima;
	protected Animacao animacaoBaixo;

	public Pokemon(){
	}
	
	public Pokemon(int id, String nome, String tipo){//tipo seria player ou inimigo
		
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
		
		////// mudar
		this.speed = 5;
		///// mudar
		
		try{
			if(tipo.equals("Inimigo")){
				this.animacaoCima = new Animacao(300);
				this.animacaoCima.add(new Image("resources/personagens/"+id+" - "+nome+"/"+nome+"_Up.png"));
				this.animacaoCima.add(new Image("resources/personagens/"+id+" - "+nome+"/"+nome+"_Up2.png"));
			}else{
				this.animacaoBaixo = new Animacao(300);
				this.animacaoBaixo.add(new Image("resources/personagens/"+id+" - "+nome+"/"+nome+"_Down.png"));
				this.animacaoBaixo.add(new Image("resources/personagens/"+id+" - "+nome+"/"+nome+"_Down2.png"));
			}
		}catch(Exception ex){
			System.out.println(ex);
		}
		
		
		try{
			if(tipo.equals("Player")){
				this.animacaoCima = new Animacao(300);
				this.animacaoCima.add(new Image("resources/personagens/"+id+" - "+nome+"/"+nome+"_Up.png"));
				this.animacaoCima.add(new Image("resources/personagens/"+id+" - "+nome+"/"+nome+"_Up2.png"));
			}else{
				this.animacaoBaixo = new Animacao(300);
				this.animacaoBaixo.add(new Image("resources/personagens/"+id+" - "+nome+"/"+nome+"_Down.png"));
				this.animacaoBaixo.add(new Image("resources/personagens/"+id+" - "+nome+"/"+nome+"_Down2.png"));
			}
		}catch(Exception ex){
			System.out.println(ex);
		}
		
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) {
		if(this.tipo.equals("Player")){
			this.animacaoCima.update();
		}else{
			this.animacaoBaixo.update();
		}
		
		if(this.tipo.equals("Inimigo")){
			this.animacaoCima.update();
		}else{
			this.animacaoBaixo.update();
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) {
		if(this.tipo.equals("Player")){
			this.animacaoCima.render(this.x, this.y, 1, true);
		}else{
			this.animacaoBaixo.render(this.x, this.y, 1, true);
		}
		
		if(this.tipo.equals("Inimigo")){
			this.animacaoCima.render(this.x, this.y, 1, true);
		}else{
			this.animacaoBaixo.render(this.x, this.y, 1, true);
		}
		
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

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	
	
	
}
