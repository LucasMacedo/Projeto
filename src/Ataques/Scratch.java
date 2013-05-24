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

public class Scratch extends Ataque {

    public Scratch(int x, int y, Pokemon pokemon) {
        this.pokemonsAcertados = new ArrayList<Pokemon>();
        this.setContador(0);
        this.pokemon = pokemon;
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
            this.sprite = new SpriteSheet("resources/ataques/" + name + "/" + name + ".png", 64, 78);
        } catch (SlickException ex) {
            JOptionPane.showMessageDialog(null, "ERRO: " + ex.getMessage());
        }
        this.animation = new Animation();
        for (int i = 0; i < 2; i++) {
            animation.addFrame(sprite.getSprite(i, 0), 100);
        }
                
        this.x = x + (this.pokemon.animacaoAtual.getImage().getWidth() / 2);
        this.y = y + (this.pokemon.animacaoAtual.getImage().getHeight() / 2) - this.animation.getHeight() / 2;
    
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) {
        if(this.contadorDano > 35){
            return;
        }
        if (this.desativado && this.acertou == true) {
            this.contadorDano++;
        }
        if (animation.isStopped()) {
            this.desativado = true;
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) {
        if (this.animation.isStopped()) {
            return;
        }
        this.animation.draw(this.x, this.y);
    }
}
