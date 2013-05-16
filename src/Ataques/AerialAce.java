package Ataques;

import DAO.AtaqueDAO;
import javax.swing.JOptionPane;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;
import Principal.Pokemon;

public class AerialAce extends Ataque {

    int frameElapsed;
    int frame;

    public AerialAce(int x, int y, Pokemon pokemon) {
    	this.pokemon = pokemon;
        this.setContador(0);
        String name = this.toString();
        if (name.lastIndexOf('.') > 0) {
            name = name.substring(name.lastIndexOf('.') + 1, name.indexOf('@'));
        }
        model.Ataque a = AtaqueDAO.getAtaque(name);
        this.setDanoBruto(a.getAtk());

        this.desativado = false;
        this.x = x;
        this.y = y;
        this.frame = 0;

        try {
            this.sprite = new SpriteSheet("resources/ataques/" + name + "/" + name + ".png", 42, 46);
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
        if (this.desativado) {
            this.contadorDano++;
            return;
        }
        this.y += this.dy;
        if (acertou == true) {
            this.contadorDano++;
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) {
        this.animation.draw(this.x, this.y);
    }

    public int getFrames() {
        return this.frameElapsed;
    }
}
