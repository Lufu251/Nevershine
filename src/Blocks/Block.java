package Blocks;

import java.io.Serializable;

import Items.Items;
import Mainpackage.Mainclass;
public abstract class Block implements Serializable{
	public int schl�ge;
	int xp, yp, gr�sse = Mainclass.blockgr�sse;
	public boolean T�rbegehbar;
	public abstract void anzeigen(int xpos, int ypos);
	public abstract Items drop();
	public abstract int getharte();
	public abstract boolean begehbar();
	public abstract boolean verwendbar();
	public abstract void aktion();

}
