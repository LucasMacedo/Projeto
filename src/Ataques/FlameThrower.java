package Ataques;

import DAO.AtaqueDAO;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;
import Principal.Pokemon;

public class FlameThrower extends Ataque {

    public FlameThrower(int x, int y, int destX, int destY, float angulo, Pokemon pokemon) {
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
        this.xInicial = x;
        this.yInicial = y;
        this.x = x;
        this.y = y;
        this.velocidade = 10;

        this.desativado = false;
        try {
            this.sprite = new SpriteSheet("resources/ataques/" + name + "/" + name + ".png", 215, 65);
        } catch (SlickException ex) {
            Logger.getLogger(FlameThrower.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.animation = new Animation();
        for (int i = 0; i < 7; i++) {
            animation.addFrame(sprite.getSprite(i, 0), 150);
        }
        this.animation.setLooping(false);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) {
        if (this.desativado) {
            this.contadorDano++;
        }
        if (acertou == true) {
            this.contadorDano++;
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) {
        if (this.animation.isStopped()) {
            return;
        }
        this.animation.draw(this.x, this.y);

    }

    public boolean estaAtivo() {
        return (this.desativado == false);
    }
}
