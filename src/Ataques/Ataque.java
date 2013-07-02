/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Ataques;

import java.util.ArrayList;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import Principal.GameObject;
import Principal.Pokemon;

/**
 *
 * @author Maike
 */
public abstract class Ataque extends GameObject {

    int velocidade = 15; //Determina a velocidade em que o personagem anda
    public boolean desativado;
    public boolean acertou = false;
    int danoCausado;// dano final
    int danoBruto;// dano inicial 
    int xInicial; // Possição inicial para 
    int yInicial;
    // Imagem imagem;
    public Image imagem;
    public SpriteSheet sprite;
    Animation animation;
    Pokemon pokemon; // personagem que ataca
    double dx, dy;
    int contadorDano; //contador de tempo para o dano que aparece na tela
    public ArrayList<Pokemon> pokemonsAcertados;
    
    
    public void setDanoCausado(int n) {
        this.danoCausado = n;
    }

    public void setDanoBruto(int n) {
        this.danoBruto = n;
    }

    public int getDanoCausado() {
        return this.danoCausado;
    }

    public int getDanoBruto() {
        return this.danoBruto;
    }

    public Rectangle getRetangulo() {
        if (this.imagem != null) {
            Rectangle rect = new Rectangle((int) this.x, (int) this.y, this.imagem.getWidth(), this.imagem.getHeight());
            return rect;
        } else {
            Rectangle rect = new Rectangle((int) this.x, (int) this.y, this.animation.getCurrentFrame().getWidth(), this.animation.getCurrentFrame().getHeight());
            return rect;
        }
    }   

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public float getxInicial() {
        return xInicial;
    }

    public void setxInicial(int xInicial) {
        this.xInicial = xInicial;
    }

    public float getyInicial() {
        return yInicial;
    }

    public void setyInicial(int yInicial) {
        this.yInicial = yInicial;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPersonagem(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public void setContador(int num) {
        this.contadorDano = num;
    }

    public int getContador() {
        return contadorDano;
    }

    public void setAcertou(boolean acertou) {
        this.acertou = acertou;
    }

    public boolean getAcertou() {
        return this.acertou;
    }

    public void desativado() {
        this.desativado = true;
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }
}
