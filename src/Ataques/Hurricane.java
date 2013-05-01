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

public class Hurricane extends Ataque {

    int frameElapsed;
    int frame;

    public Hurricane(int x, int y, int destX, int destY, float angulo, Pokemon pokemon) {
        this.pokemonsAcertados = new ArrayList<Pokemon>();
        
        this.setContador(0);
        String name = this.toString();
        if (name.lastIndexOf('.') > 0) {
            name = name.substring(name.lastIndexOf('.') + 1, name.indexOf('@'));
        }
        model.Ataque a = AtaqueDAO.getAtaque(name);
        this.setDanoBruto(a.getAtk());

        this.pokemon = pokemon;
        this.desativado = false;
        this.x = x;
        this.y = y;
        this.frame = 0;
        this.desativado = false;

        try {
            this.sprite = new SpriteSheet("resources/ataques/" + name + "/" + name + ".png", 72, 70);
        } catch (SlickException ex) {
            JOptionPane.showMessageDialog(null, "ERRO: " + ex.getMessage());
        }
        this.animation = new Animation();
        for (int i = 0; i < 2; i++) {
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
        if (this.desativado) {
            this.contadorDano++;
            return;
        }
        this.x += this.dx;
        this.y += this.dy;
        if (acertou == true) {
            this.contadorDano++;
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) {
            this.animation.draw(this.x, this.y);
    }

    
    /* public Rectangle getRetangulo() {
        return new Rectangle(this.x, this.y, this.animation.getWidth(), this.animation.getHeight());
    }

    public boolean temColisao(Rectangle retangulo) {
        if (this.desativado || this.frame == 4) {
            return false;
        }

        if (this.getRetangulo().intersects(retangulo)) {
            this.desativado = true;
            return true;
        } else {
            return false;
        }
   }*/ 

    public int getFrames() {
        return this.frameElapsed;
    }
}
