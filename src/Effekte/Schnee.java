package Effekte;

import org.lwjgl.opengl.GL11;

import Mainpackage.Mainclass;

public class Schnee {
	int xp;
	public int yp;
	int gr�sse;
	public Schnee(int x, int y, int g){
		xp = x;
		yp = y;
		gr�sse = g;
	}
	public void anzeigen(){
		GL11.glColor4f(1, 1, 1, 1);
		Mainclass.schneeflocke.displayRect(xp + Mainclass.xver, yp, gr�sse, gr�sse);
	}
	public void bewegen(){
		xp ++;
		yp += 8;
	}
}
