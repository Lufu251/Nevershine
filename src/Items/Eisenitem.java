package Items;

import org.lwjgl.opengl.GL11;

import Blocks.Block;
import Mainpackage.Mainclass;

public class Eisenitem extends Items{

	public void render(int xpos, int ypos) {
		GL11.glColor3f(1, 1, 1);
		Mainclass.Eisenitem.displayRect(xpos, ypos, gr�sse, gr�sse);
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