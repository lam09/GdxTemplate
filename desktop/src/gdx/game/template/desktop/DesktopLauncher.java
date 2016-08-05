package gdx.game.template.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import gdx.game.template.MyGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = MyGame.SCREEN_WIDTH;
		config.height = MyGame.SCREEN_HEIGHT;
		new LwjglApplication(new MyGame(), config);
	}
}
