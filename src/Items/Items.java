package Items;

import Blocks.Block;
import Mainpackage.Mainclass;

public abstract class Items{
	int xp, yp, gr�sse = Mainclass.blockgr�sse;
	Items itemdrop;
	public abstract void render(int xpos, int ypos);
	public abstract boolean setzbar();
	public abstract int starke();
	public abstract void setzaktion();
	public abstract boolean food();
	 }
	

