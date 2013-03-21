package Principal;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame{
	
	public static void main(String[] args){
		AppGameContainer app;
		
		try{
		app = new AppGameContainer(new Main("a"));
		app.setDisplayMode(600, 700, false);
		app.setTargetFrameRate(60);
		
		app.start();
		} catch(SlickException ex){
			System.out.println(ex);
		}
	}

	public Main(String name) {
		super(name);
		System.out.println("teste");
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		
		this.addState(new MenuMain());
		this.addState(new MenuOpcoes());
		
	}


}
