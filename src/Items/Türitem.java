package Items;

import Blocks.Block;
import Blocks.Dreck;
import Blocks.Holz;
import Blocks.T�roben;
import Blocks.T�runten;
import Mainpackage.Mainclass;

public class T�ritem extends Items{
	public T�ritem(){
		
	}

	public void render(int xpos, int ypos) {
		Mainclass.T�ritem.displayRect(xpos, ypos, gr�sse, gr�sse);
		
	}

	public boolean setzbar() {
		return true;
	}

	public int starke() {
		return 0;
	}

	public void setzaktion() {
		if(Mainclass.array1[Mainclass.ipos][Mainclass.bpos + 1].content.begehbar() == false){
		Mainclass.array1[Mainclass.ipos][Mainclass.bpos].ersetzen(new T�runten());
		Mainclass.array1[Mainclass.ipos][Mainclass.bpos-1].ersetzen(new T�roben());
		}
	}
	
}
