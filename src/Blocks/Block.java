package Blocks;

import java.io.Serializable;

import Items.Items;
import Mainpackage.Mainclass;
public abstract class Block implements Serializable{
	int xp, yp, gr�sse = Mainclass.blockgr�sse;
	public boolean T�rbegehbar;
	public abstract void anzeigen(int xpos, int ypos);
	public abstract void abbau(int Wert);
	public abstract void sound();
	public abstract Items drop();
	public abstract int getharte();
	public abstract boolean begehbar();
	public abstract boolean verwendbar();
	public abstract void aktion();

}
