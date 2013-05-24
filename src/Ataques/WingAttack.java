package Ataques;

import DAO.AtaqueDAO;
import Principal.Pokemon;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class WingAttack extends Ataque {

    public WingAttack(int x, int y, int destX, int destY, float angulo, Pokemon pokemon) {
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
            this.imagem = new Image("resources/ataques/" + name + "/" + name + ".png");
        } catch (SlickException ex) {
            JOptionPane.showMessageDialog(null, "ERRO: " + ex.getMessage());
        }

       
        this.dx = Math.cos(Math.toRadians(angulo)) * velocidade;
        this.dy = -Math.sin(Math.toRadians(angulo)) * velocidade;
        
        this.imagem.rotate(-angulo);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) {
        if (this.desativado == true) {
            this.contadorDano++;
            return;
        }
        this.x += this.dx;
        this.y += this.dy;
        if (this.getAcertou() == true) {
            this.contadorDano++;
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) {
        if (this.desativado == true) {
            return;
        }
        this.imagem.draw(this.x, this.y);
    }
}
