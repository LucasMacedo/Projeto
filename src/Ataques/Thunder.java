package Ataques;

import DAO.AtaqueDAO;
import Principal.Pokemon;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;


public class Thunder extends Ataque {

    public Thunder(int x, int y, Pokemon pokemon) {
    	this.pokemon = pokemon;
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
            this.sprite = new SpriteSheet("resources/ataques/" + name + "/" + name + ".png", 224, 56);
        } catch (SlickException ex) {
            JOptionPane.showMessageDialog(null, "ERRO: " + ex.getMessage());
        }
        this.animation = new Animation();
        for (int i = 0; i < 7; i++) {
            animation.addFrame(sprite.getSprite(i, 0), 100);
        }

        this.animation.setLooping(false);
        

        this.animation.setLooping(false);
        

    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) {
        if (this.desativado == true) {
            this.contadorDano++;
            return;
        }
        if (this.getAcertou() == true) {
            this.contadorDano++;
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) {
    	if (this.animation.isStopped()) {
            return;
        }
        int angulo;
        if(this.pokemon.tipo.equals("Player")){
            angulo = 90;
        }else{
        	angulo = -90;
        }

        g.rotate(this.x, this.y + this.animation.getHeight() / 2, -angulo);
        this.animation.draw(this.x, this.y);
        g.rotate(this.x, this.y + this.animation.getHeight() / 2, angulo);

    }
}
