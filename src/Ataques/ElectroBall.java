package Ataques;

import DAO.AtaqueDAO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;
import Principal.Pokemon;



public class ElectroBall extends Ataque {

    public ElectroBall(int x, int y, int destX, int destY, float angulo, Pokemon pokemon) {
        this.pokemonsAcertados = new ArrayList<Pokemon>();
        this.setContador(0);
        String name = this.toString();
        if (name.lastIndexOf('.') > 0) {
            name = name.substring(name.lastIndexOf('.') + 1, name.indexOf('@'));
        }
        model.Ataque a = AtaqueDAO.getAtaque(name);
        this.setDanoBruto(a.getAtk());
        
        this.desativado = false;
        this.xInicial = x;
        this.yInicial = y;
        this.x = x;
        this.y = y;

        try {
            this.sprite = new SpriteSheet("resources/ataques/" + name + "/" + name + ".png", 90, 65);
        } catch (SlickException ex) {
            JOptionPane.showMessageDialog(null, "ERRO: " + ex.getMessage());
        }
        this.animation = new Animation();
        for (int i = 0; i < 4; i++) {
            animation.addFrame(sprite.getSprite(i, 0), 100);
        }

        if(this.pokemon.tipo.equals("Player")){
        	this.dx = this.velocidade;
        	this.dy = -this.velocidade;
        } else {
        	this.dx = -this.velocidade;
        	this.dy = this.velocidade;
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) {
        if(this.desativado == true){
            this.contadorDano ++;
            return;
        }
        this.x += this.dx;
        this.y += this.dy;
        if(this.getAcertou() == true){
            this.contadorDano ++;
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) {
        if(this.desativado == true){
            return;
        }
        this.animation.draw(this.x, this.y);
    }
}
