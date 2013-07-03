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

public class DoubleSlap extends Ataque {

    public DoubleSlap(int x, int y, Pokemon pokemon) {
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
            this.sprite = new SpriteSheet("resources/ataques/" + name + "/" + name + ".png", 40, 23);
        } catch (SlickException ex) {
            JOptionPane.showMessageDialog(null, "ERRO: " + ex.getMessage());
        }
        this.animation = new Animation();
        for (int i = 0; i < 2; i++) {
            animation.addFrame(sprite.getSprite(i, 0), 100);
        }

        if(this.pokemon.tipo.equals("Player")){
        	this.dy = -this.velocidade;
        } else {
        	this.dy = this.velocidade;
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) {
        if (this.desativado && acertou == true) {
            this.contadorDano++;
            return;
        }
        if(this.acertou){
            this.desativado = true;
        }
        this.y += this.dy;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) {
        if (this.desativado == true) {
            return;
        }
        this.animation.draw(this.x, this.y);
    }
}
