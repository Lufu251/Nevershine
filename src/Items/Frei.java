package Items;

import org.lwjgl.opengl.GL11;

import Blocks.Block;
import Blocks.Dreck;
import Mainpackage.Mainclass;

public  class Frei extends Items{
	public Frei(){
}
	public void render(int xpos, int ypos) {
		GL11.glColor4f(1, 1, 1, 0);
		Mainclass.quader(xpos, ypos, gr�sse);
		
	}

	public boolean setzbar() {
		return false;
	}

	public int starke() {
		return 0;
	}

	public void setzaktion() {
	}
}
