package Mainpackage;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;

import java.util.*;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;

import Blocks.Felder;
import Blocks.Schneedecke;
import Effekte.Feuerpartikel;
import Effekte.Schnee;
import Gui.Button;
import Gui.Inventar;
import Gui.Mainmenu;
import Items.Eisenerzitem;
import Items.Eisenspitzhacke;
import Items.Feuerstelleitem;
import Items.Frei;
import Items.Holzitem;
import Items.Apfel;
import Items.Türitem;
import Mainpackage.Texture;
import Items.Items;
import Items.Holzspitzhacke; 
import Items.Steinspitzhacke; 

import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Array;

import org.lwjgl.opengl.GL11;

import javax.imageio.ImageIO;

public class Mainclass {

	private static final int FRAMERATE = 60;
	static boolean finished = false;
	static ArrayList<Spieler> spielers = new ArrayList<Spieler>();
	static ArrayList<Schnee> schnees = new ArrayList<Schnee>();
	public static ArrayList<Feuerpartikel> feuerpartikels = new ArrayList<Feuerpartikel>();
	static boolean aktive = false, oben = false;
	public static boolean Feuerinventar = false;
	public static int Menustatus = 1;
	public static int usecooldown = 10;
	public static int inventar = 0;
	public static int inventarcooldown = 0;
	public static int blockgrösse = 25;
	public static int einmal;
	public static int verwendung = 0;
	public static int speed = 3;
	public static int spawnx = 500, spawny = 500;
	public static int xmausaus, ymausaus;
	public static int xauswahl;
	public static int xmouse;
	public static int ymouse;
	public static int xcraft = 3;
	public static int ycraft = 3;
	public static int spieleri, spielerb, spielerip, spielerbp, spieleripp, gravitation = 9, facing = 2, jumps = 5, jumph = 50;
	public static int counter;
	public static int xver;
	public static int yver;
	public static int xsize = 400;
	public static int ysize = 800;
	public static int xpos, ypos;
	public static int abbaucooldown;
	public static int bc = 10;
	public static int ipos = 0;
	public static int bpos = 0;
	public static Texture faceleft;
	public static Texture face;
	public static Texture faceright;
	public static Texture dreck;
	public static Texture stone;
	public static Texture Holz;
	public static int xinventar = 18, yinventar = 10;
	public static Texture Eisenerz;
	public static Texture Menubackground;
	public static Texture logo;
	public static Texture Itemframe;
	public static Texture Eisenitem;
	public static Texture schneedreck;
	public static Texture Apfel;
	public static Texture Herz;
	public static Texture Herzh;
	public static Texture schneeflocke;
	public static Texture schneelandschaft;
	public static Texture schneedecke;
	public static Texture Bletter;
	public static Texture Feuerstelle;
	public static Texture ausgewählt;
	public static Texture Holzspitzhacke;
	public static Texture Steinspitzhacke;
	public static Texture Eisenspitzhacke;
	public static Texture Türezu;
	public static Texture Türeoffenoben;
	public static Texture Türeoffenunten;
	public static Texture Feuerhintergrund;
	public static Texture Türitem;
	public static SoundManager sm;
	public static SoundManager dig;
	public static SoundManager lauf;
	static public Button Singleplayer;
	static public Button load;
	static public Button restart;
	static public Button exit;
	static public Button resum;
	static public Button mainmenu;
	public static Font superschrift;
	public static Button speichern;
	public static Stak Mousestak;
	public final static String datafolder = "data";
	public static Felder[][] array1 = new Felder[xsize][ysize];
	public static Inventarfelder[][] inventarfeld = new Inventarfelder[xinventar][yinventar];

	private Mainclass() {
	}

	public static void main(String[] args) {
		try {
			init();
			run();
		} catch (Exception e) {
			Sys.alert("Hallo", "Es hat einen Fehler gegeben");
			e.printStackTrace(System.err);
		} finally {
			cleanup();
		}

	}

	private static void init() throws Exception {
		Display.setTitle("Titel");
		Display.setFullscreen(true);
		// Display.setDisplayMode(new DisplayMode(1920,1080));
		Display.setVSyncEnabled(true);
		Display.create();
		AL.create();
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0.0, Display.getDisplayMode().getWidth(), Display.getDisplayMode().getHeight(), 0.0, -1.0, 1.0);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glViewport(0, 0, Display.getDisplayMode().getWidth(), Display.getDisplayMode().getHeight());
		spielers.add(new Spieler(spawnx, spawny, speed, 100, 200));
		sm = new SoundManager();
		dig = new SoundManager();
		lauf = new SoundManager();
		sm.loadSounds();
		dig.loadSounds();
		lauf.loadSounds();
		lauf.modifyVolume(lauf.schneelauf, +5);
		dig.modifyVolume(dig.holzdig, +5);
		dig.modifyVolume(dig.bletterdig, +5);
		sm.modifyVolume(sm.GameMusic, -30);
		sm.modifyVolume(sm.Menumusic, -30);
		Mousestak = new Stak();
		Mousestak.art = new Frei();
		Singleplayer = new Button(860, 490, 100, 50, "Singelplayer", 1.2f) {
			public void action() {
				Menustatus = 2;
			}
		};
		load = new Button(860, 440, 100, 50, "Load", 1.2f) {
			public void action() {
				Map.laden();
				spielers.get(0).spawn();
			}
		};
		restart = new Button(860, 540, 100, 50, "Restart", 1.2f) {
			public void action() {
				Map.init();
				spielers.get(0).spawn();
				Mousestak.anzahl = 0;
			}
		};
		exit = new Button(860, 700, 100, 50, "Exit", 1.2f) {
			public void action() {
				finished = true;
			}
		};
		resum = new Button(860, 500, 100, 50, "Resum", 1.2f) {
			public void action() {
				Menustatus = 0;
			}
		};
		mainmenu = new Button(860, 400, 100, 50, "Mainmenu", 1.2f) {
			public void action() {
				Menustatus = 1;
			}
		};
		speichern = new Button(860, 600, 100, 50, "Speichern", 1.2f) {
			public void action() {
				Map.speichern();
			}
		};
		superschrift = Font.loadFont(new FileInputStream(datafolder + "\\MS_Gothic_80.wbf"));
		Menubackground = Texture.createTexture(ImageIO.read(new File("data\\Menubackground.png")));
		schneelandschaft = Texture.createTexture(ImageIO.read(new File("data\\schnee landschaft.png")));
		faceleft = Texture.createTexture(ImageIO.read(new File("data\\steveleft.png")));
		faceright = Texture.createTexture(ImageIO.read(new File("data\\steveright.png")));
		face = Texture.createTexture(ImageIO.read(new File("data\\stevefront.png")));
		dreck = Texture.createTexture(ImageIO.read(new File("data\\Dreck.png")));
		stone = Texture.createTexture(ImageIO.read(new File("data\\Stone.png")));
		Holz = Texture.createTexture(ImageIO.read(new File("data\\Holz.png")));
		Eisenerz = Texture.createTexture(ImageIO.read(new File("data\\Eisenerz.png")));
		Itemframe = Texture.createTexture(ImageIO.read(new File("data\\Itemframe.png")));
		Eisenitem = Texture.createTexture(ImageIO.read(new File("data\\Eisenitem.png")));
		schneedreck = Texture.createTexture(ImageIO.read(new File("data\\schneedreck.png")));
		Apfel = Texture.createTexture(ImageIO.read(new File("data\\Apfel.png")));
		Herz = Texture.createTexture(ImageIO.read(new File("data\\Herz.png")));
		Herzh = Texture.createTexture(ImageIO.read(new File("data\\Herzhalb.png")));
		schneeflocke = Texture.createTexture(ImageIO.read(new File("data\\schneeflocke.png")));
		schneedecke = Texture.createTexture(ImageIO.read(new File("data\\schneedecke.png")));
		Bletter = Texture.createTexture(ImageIO.read(new File("data\\Bletter.png")));
		Feuerstelle = Texture.createTexture(ImageIO.read(new File("data\\Feuer.png")));
		ausgewählt = Texture.createTexture(ImageIO.read(new File("data\\ausgewählt.png")));
		Holzspitzhacke = Texture.createTexture(ImageIO.read(new File("data\\Holzspitzhacke.png")));
		Steinspitzhacke = Texture.createTexture(ImageIO.read(new File("data\\Steinspitzhacke.png")));
		Eisenspitzhacke = Texture.createTexture(ImageIO.read(new File("data\\Eisenspitzhacke.png")));
		Türezu = Texture.createTexture(ImageIO.read(new File("data\\Türzu.png")));
		Türeoffenoben = Texture.createTexture(ImageIO.read(new File("data\\Türoffenoben.png")));
		Türeoffenunten = Texture.createTexture(ImageIO.read(new File("data\\Türoffenunten.png")));
		Türitem = Texture.createTexture(ImageIO.read(new File("data\\Türitem.png")));
		
		for (int c = 0; c < Mainclass.xinventar; c++) {
			for (int d = 0; d < Mainclass.yinventar; d++) {
				inventarfeld[c][d] = new Inventarfelder(c, d, 100);
			}
		}
		inventarfeld[0][0].inventarstak.anzahl = 1;
		inventarfeld[0][0].inventarstak.art = new Holzspitzhacke();
		inventarfeld[1][0].inventarstak.anzahl = 1;
		inventarfeld[1][0].inventarstak.art = new Feuerstelleitem();
		inventarfeld[2][0].inventarstak.anzahl = 1;
		inventarfeld[2][0].inventarstak.art = new Türitem();
	}

	private static void run() {
		while (!finished) {
			Display.update();

			if (Display.isCloseRequested()) {
				finished = true;
			} else if (Display.isActive()) {
				logic();
				render();
				Display.sync(FRAMERATE);
			} else { 
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
				logic();
				if (Display.isVisible() || Display.isDirty()) {
					render();
				}
			}
		}
	}

	private static void cleanup() {
		AL.destroy();
		Display.destroy();
	}

	private static void logic() {
		xmouse = (int) (Mouse.getX() - xver);
		ymouse = (int) (Display.getHeight() - Mouse.getY()) - yver;
		ipos = (int) xmouse / blockgrösse;
		bpos = (int) ymouse / blockgrösse;
		if (Menustatus == 0 && Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			Menustatus = 3;
		}
	}

	private static void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_STENCIL_BUFFER_BIT);
		glClear(GL_COLOR_BUFFER_BIT);
		glColor3f(0, 0, 1);
		if (Menustatus == 0) {
			glColor3f(1,1,1);
			schneelandschaft.displayRect(0, 0, 1920, 1080);
			GL11.glColor4f(0, 0, 0, 0.2f);
			rect(0,0,1920,1080);
			for (int i = 0; i < schnees.size(); i++) {
				Schnee schnee = (Schnee) schnees.get(i);
				schnee.anzeigen();
				schnee.bewegen();
			}
			for (int i = 0; i < xsize; i++) {
				for (int b = 0; b < ysize; b++) {
					if (i * blockgrösse + xver <= 1980 && (i + 1) * blockgrösse + xver >= 0 && b * blockgrösse + yver <= 1080 && (b + 1) * blockgrösse + yver >= 0) {
						array1[i][b].inhalt(i * blockgrösse, b * blockgrösse);
					}
					array1[i][b].zerstören();
				}
			}
			for (int i = 0; i < feuerpartikels.size(); i++) {
				Feuerpartikel feuerpartikel = (Feuerpartikel) feuerpartikels.get(i);
				feuerpartikel.anzeigen();
				feuerpartikel.tick();
				feuerpartikel.beweg();
				if(feuerpartikels.get(i).grösse < 0){
					feuerpartikels.remove(i);
				}
			}
			for (int i = 0; i < spielers.size(); i++) {
				Spieler spieler = (Spieler) spielers.get(i);
				spieler.verschiebung();
				spieler.bewegen();
				spieler.fall();
				spieler.jump();

				spieler.anzeigen();
				spieler.abbaue();
				spieler.setzen();
				spieler.verwenden();
			}
			Map.update();
			sm.stopSound(sm.Menumusic);
			sm.loopSound(sm.GameMusic);
			inventarcooldown += 1;
			if (Keyboard.isKeyDown(Keyboard.KEY_E) && inventarcooldown >= 15 && inventar == 0) {
				inventar = 1;
				inventarcooldown = 0;
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_E) && inventar == 1 && inventarcooldown >= 15) {
				inventar = 0;
				Feuerinventar = false;
				inventarcooldown = 0;
			}
			if (inventar == 1) {
				Inventar.render();
			}
			if(inventar == 0){
				Inventar.render2();
		}
			//Map.schneesturm();
		}
		if (Menustatus == 1) {
			Mainmenu.Mainmenu();
			sm.stopSound(sm.GameMusic);
		}
		if (Menustatus == 2) {
			Mainmenu.Menu2();
			sm.stopSound(sm.GameMusic);
		}
		if (Menustatus == 3) {
			Mainmenu.IngameMenu();
		}
	}

	public static void ellipse(float f, float g, int xg, int yg, int detail) {
		int ecken = detail;
		glBegin(GL_TRIANGLE_FAN);
		for (int i = 0; i <= ecken; i++) {
			double angle = i * Math.PI * 2 / ecken;
			glVertex2f(f + (float) Math.cos(angle) * xg, g + (float) Math.sin(angle) * yg);
		}
		glEnd();
	}

	public static void quader(int xa, int ya, int l) {
		glBegin(GL_QUADS);
		glVertex2f(xa, ya);
		glVertex2f(xa + l, ya);
		glVertex2f(xa + l, ya + l);
		glVertex2f(xa, ya + l);
		glEnd();

	}

	static void healthbar(int xa, int ya, int l, int b, double health, double maxhealth) {
		double healthm = (l / maxhealth);
		l = (int) (healthm * health);
		glBegin(GL_QUADS);
		glVertex2d(xa, ya);
		glVertex2d(xa + l, ya);
		glVertex2d(xa + l, ya + b);
		glVertex2d(xa, ya + b);
		glEnd();
		glLineWidth(2.5f);
		glBegin(GL_LINES);
		glVertex2f(xa, ya);
		glVertex2f(xa + l, ya);
		glVertex2f(xa + l, ya + b);
		glVertex2f(xa + l, ya + b);
		glEnd();
	}
	public static void rect(int xa, int ya, int l, float b) {
		glBegin(GL_QUADS);
		glVertex2f(xa, ya);
		glVertex2f(xa + l, ya);
		glVertex2f(xa + l, ya + b);
		glVertex2f(xa, ya + b);
		glEnd();

	}
}