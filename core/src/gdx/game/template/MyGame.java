package gdx.game.template;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import java.util.Random;

import gdx.game.template.location.LocationThread;
import gdx.game.template.screens.LoadingScreen;
import gdx.game.template.screens.MenuScreen;

public class MyGame extends Game {
	public static final int SCREEN_WIDTH =  1680;
	public static final int SCREEN_HEIGHT =  1050;
	public static MyGame instance;
	public static double locationX = 0f;
	public static double locationY= 0f;
	LocationThread locationThread ;

	public static LoadingScreen.LoadingStatus doneLoading = LoadingScreen.LoadingStatus.NOT_STARTED;

	public static LoadingScreen loading;
	public static MenuScreen menu;
	public static final Random RANDOM = new Random();

	public static Skin skin;
	public static TextureAtlas textureAtlas;
	public static AssetManager manager;
	public static BitmapFont font;


	@Override
	public void create () {
		manager= new AssetManager();
		locationThread = new LocationThread();
		locationThread.setDaemon(true);
		locationThread.start();

		font = new BitmapFont(Gdx.files.internal("loading/default.fnt"), Gdx.files.internal("loading/default.png"), false);
		//SKIN
		textureAtlas = new TextureAtlas(Gdx.files.internal("loading/uiskin.atlas"));
		skin = new Skin(Gdx.files.internal("loading/uiskin.json"),textureAtlas);
		loading = new LoadingScreen(this, skin, font);
		setScreen(loading);
		instance = this;
	}

	@Override
	public void render () {
		super.render();
	}
}
