package Blocks;

import java.io.Serializable;

import org.lwjgl.opengl.GL11;

import Items.Items;
import Items.Stoneitem;
import Items.T�ritem;
import Mainpackage.Mainclass;


public class T�runten extends Block implements Serializable{
	static int T�rharte = 2;
	
	public T�runten (){
		}

	public void anzeigen(int xpos, int ypos) {
		GL11.glColor4f(1,1,1,1);
		if(T�rbegehbar = false){
		Mainclass.T�rezu.displayRect(xpos + Mainclass.xver,ypos + Mainclass.yver, gr�sse, gr�sse);
		}
		if(T�rbegehbar = true){
			Mainclass.T�reoffenunten.displayRect(xpos + Mainclass.xver,ypos + Mainclass.yver, gr�sse, gr�sse);
		}
	}

	public Items drop() {
		return new T�ritem();
		
	}

	public int getharte() {
		return T�rharte;
	}

	public boolean begehbar() {
		return T�rbegehbar;
	}

	public boolean verwendbar() {
		return true;
	}

	public void aktion() {
		this.T�rbegehbar =! this.T�rbegehbar;
	}
}