package Blocks;

import java.io.Serializable;

import org.lwjgl.opengl.GL11;

import Items.Items;
import Items.Stoneitem;
import Items.T�ritem;
import Mainpackage.Mainclass;


public class T�roben extends Block implements Serializable{
	int T�rharte = 2;
	
	public T�roben(){
		}

	public void anzeigen(int xpos, int ypos) {
		GL11.glColor4f(1,1,1,1);
		if(T�rbegehbar = false){
		Mainclass.T�rezu.displayRect(xpos + Mainclass.xver,ypos + Mainclass.yver, gr�sse, gr�sse);
		}
		if(T�rbegehbar = true){
			Mainclass.T�reoffenoben.displayRect(xpos + Mainclass.xver,ypos + Mainclass.yver, gr�sse, gr�sse);
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
		System.out.println("verwendet");
		T�rbegehbar = false;
	}

	public void sound() {
		Mainclass.dig.playSound(Mainclass.dig.holzdig);
		
	}

	public void abbau(int Wert) {
		T�rharte -= Wert;
	}
}