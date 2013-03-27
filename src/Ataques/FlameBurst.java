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

public class FlameBurst extends Ataque {

    public FlameBurst(int x, int y, int destX, int destY, float angulo, Pokemon pokemon) {
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
        this.x = x;
        this.y = y;
        try {
            this.sprite = new SpriteSheet("resources/ataques/" + name + "/" + name + ".png", 240, 250);
        } catch (SlickException ex) {
            Logger.getLogger(FlameBurst.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.animation = new Animation();
        for (int i = 0; i < 3; i++) {
            animation.addFrame(sprite.getSprite(i, 0), 150);
        }
        this.animation.setLooping(false);

        this.x -= this.animation.getCurrentFrame().getWidth() / 2;
        this.y -= this.animation.getCurrentFrame().getHeight() / 2;
        this.x += this.pokemon.animacaoAtual.getImage().getWidth() / 2;
        this.y += this.pokemon.animacaoAtual.getImage().getHeight() / 2;
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
        if (!animation.isStopped()) {
            this.animation.draw(this.x, this.y);
        }
    }
}