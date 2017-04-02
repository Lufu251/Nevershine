package Items;

import org.lwjgl.opengl.GL11;

import Blocks.Block;
import Blocks.Dreck;
import Blocks.Eisen;
import Mainpackage.Mainclass;

public class Eisenerzitem extends Items{

	public void render(int xpos, int ypos) {
		GL11.glColor3f(1, 1, 1);
		Mainclass.Eisenerz.displayRect(xpos, ypos, gr�sse, gr�sse);
	}

	public boolean setzbar() {
		return true;
	}

	public int starke() {
		return 0;
	}

	public void setzaktion() {
		Mainclass.array1[Mainclass.ipos][Mainclass.bpos].ersetzen(new Eisen());
	}

}
