package Blocks;
import java.io.Serializable;

import Gui.Inventar;
import Mainpackage.Mainclass;

public class Felder implements Serializable{
	int xp, yp, gr�sse;
	public Block content;
	
 public Felder(int x, int y,int g){
	 xp = x;
	 yp = y;
	 gr�sse = g;
	 content = new Dreck();
 }
 public void inhalt(int xpos, int ypos){
	 content.anzeigen(xpos,ypos);
	 }
 public void ersetzen(Block content2){
   	 content = content2;
    }
 public void zerst�ren() {
	 if(!Mainclass.array1[Mainclass.ipos][Mainclass.bpos].content.getClass().equals(new Luft().getClass())){
		if(Mainclass.array1[Mainclass.ipos][Mainclass.bpos].content.schl�ge >= Mainclass.array1[Mainclass.ipos][Mainclass.bpos].content.getharte()){
			Inventar.einsortieren();
			Mainclass.array1[Mainclass.ipos][Mainclass.bpos].ersetzen(new Luft());
		    Mainclass.abbaucooldown = 15;
		}
		}
 		}
 }