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
		app.setDisplayMode(800, 600, false);
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
	public void initStatesList(GameContainer arg0) throws SlickException {
				
		System.out.println("Hello World");
	}

	
	

}
