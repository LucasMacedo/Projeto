package Principal;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

public abstract class GameObject {
	
	protected int x;
	protected int y;
	protected Image imagem;
	
	public abstract void update(GameContainer gc, StateBasedGame game, int delta);
	public abstract void render(GameContainer gc, StateBasedGame game, Graphics g);
	
	public void moveDireita(int distancia){
		this.x += distancia;
	}
	public void moveEsquerda(int distancia){
		this.x -= distancia;
	}
	public void moveCima(int distancia){
		this.y -= distancia;
	}
	public void moveBaixo(int distancia){
		this.y += distancia;
	}

	public int getX(){
		return this.x;
	}
	public void setX(int x){
		this.x = x;
	}
	public int getY(){
		return this.y;
	}
	public void setY(int y){
		this.y = y;
	}
}
