package Ataques;

import DAO.AtaqueDAO;
import Principal.Pokemon;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class BodySlam extends Ataque {

    public BodySlam(int x, int y, Pokemon pokemon) {
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


        if(this.pokemon.tipo.equals("Player")){
        	this.dy = -this.velocidade;
        	this.imagem = pokemon.animacaoBaixo.getImage();
        } else {
        	this.dy = this.velocidade;
        	this.imagem = pokemon.animacaoCima.getImage();
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
        Color cor = new Color(255, 255, 255, 200);
        this.imagem.drawFlash(this.x, this.y, this.imagem.getWidth(), this.imagem.getHeight(), cor);
    }
}
