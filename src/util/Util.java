package util;

import java.util.Random;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Util {

    static public void sleep(int miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException ex) {
            System.out.println("Erro: " + ex);
        }
    }

    static public int random(int max) {
        Random r = new Random();
        return r.nextInt(max);
    }

    static public double calculaAngulo(int xAlvo, int xPersonagem, int yAlvo, int yPersonagem) {
        int xx = xAlvo - xPersonagem;
        int yy = (yAlvo - yPersonagem) * -1;

        double angle = Math.toDegrees(Math.atan2(yy, xx));

        if (angle < 0) {
            angle *= -1;
            angle = 360 - angle;
        }
        return angle;
    }

    public static void desenhaBarra(Graphics g, int x, int y, double tamanho, double parcela, double totalParcelas, boolean centralizado) {
        double total = tamanho;
        double fracao = (tamanho * parcela) / totalParcelas;
        if(centralizado){
            x -= total/2;
        }
        g.setColor(Color.black);
        g.fillRoundRect(x - 1, y-1, (int) total + 2, 22, 2);
        g.setColor(Color.white);
        g.fillRoundRect(x, y, (int) fracao, 20, 2);
    }

    public static double calculaDistancia(int x1, int y1, int x2, int y2) {
        int x = x2 - x1;
        int y = y2 - y1;

        //Pow é a função para elevar um número a uma potencia.
        double distanciaAoQuadrado = Math.pow(x, 2) + Math.pow(y, 2);
        //Agora, faz a raiz da distância ao quadrado para ter a distância.
        //Math.sqrt é a fórmula que faz a raiz de um número
        double distancia = Math.sqrt(distanciaAoQuadrado);

        return distancia;
    }
}
