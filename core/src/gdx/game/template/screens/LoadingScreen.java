package gdx.game.template.screens;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import gdx.game.template.MyGame;

public class LoadingScreen implements Screen {


    final MyGame game;
    OrthographicCamera camera;
    Stage stage;
    private static Skin skin;
    Label LoadLabel;
    ProgressBarStyle barStyle;
    ProgressBar bar;
    Sprite BackgroundSprite;
    SpriteBatch batch;

    protected BitmapFont font = null;
    GlyphLayout layout = new GlyphLayout(); //get font width in newer libgdx

    private TextureAtlas initialAtlas = null;
    private Sprite[] circles = null;
    private float[] rotations = null;
    public static LoadingScreen instance = null;
    private boolean isInitialLoading = true;
    private float initialLoadingDuration = 10f;
    private boolean useRealLoadingDuration = true;

    protected float time = 0;
    public LoadingScreen(final MyGame game,Skin skin, BitmapFont font) {
        this.game = game;
        this.skin = skin;
        this.font = font;
        instance = this;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, MyGame.SCREEN_WIDTH*2, MyGame.SCREEN_HEIGHT);

        Texture background = new Texture("loading/LoadBG.png");
        background.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
        BackgroundSprite = new Sprite(background, 0, 0, MyGame.SCREEN_WIDTH, MyGame.SCREEN_HEIGHT);

        stage = new Stage();
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        LoadLabel = new Label("Načítavanie...", skin);
        LoadLabel.setPosition(MyGame.SCREEN_WIDTH/2-LoadLabel.getWidth()/2, MyGame.SCREEN_HEIGHT/2+50);
        stage.addActor(LoadLabel);

        Pixmap pixmap = new Pixmap(10, 25, Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));

        barStyle = new ProgressBarStyle(skin.newDrawable("white", Color.WHITE), skin.newDrawable("white", Color.GREEN));
        barStyle.knobBefore = barStyle.knob;
        bar = new ProgressBar(0, 100, 1f, false, barStyle);
        bar.setPosition(MyGame.SCREEN_WIDTH/2-MyGame.SCREEN_WIDTH/4, MyGame.SCREEN_HEIGHT/2);
        bar.setSize(MyGame.SCREEN_WIDTH/2, bar.getPrefHeight());
        bar.setAnimateDuration(0.001f);
        stage.addActor(bar);

        initialAtlas = new TextureAtlas(Gdx.files.internal("loading/initial-loader.atlas"));
        circles = new Sprite[5];
        rotations = new float[5];
        int[] xs = new int[]{755, 699, 645, 592, 540};
        int[] ys = new int[]{271, 218, 165, 112, 60};
        for (int i = 0; i < circles.length; i++) {
            Sprite circle = new Sprite(initialAtlas.findRegion(""+(20 + i*20)));
            circle.setPosition(xs[i], ys[i]);
            circles[i] = circle;
            rotations[i] = MyGame.RANDOM.nextInt(10) + 1;
        }
        StartLoading();
    }

    public void StartLoading(){
        initialLoadingDuration =10f;
        LoadAssets();
    }

    /**
     * Put Assets to loading here
     */
    private void LoadAssets() {

        MyGame.loading = this;
        MyGame.manager.load("loading/uiskin.atlas", TextureAtlas.class);
        MyGame.manager.load("loading/uiskin.json", Skin.class );
        MyGame.manager.load("loading/game-loader.atlas", TextureAtlas.class );
        MyGame.manager.load("loading/initial-loader.atlas", TextureAtlas.class );
        MyGame.manager.load("loading/loader.atlas", TextureAtlas.class );
        MyGame.manager.load("loading/default.fnt", BitmapFont.class );
        MyGame.manager.load("badlogic.jpg",Texture.class);
        MyGame.manager.load("sound/adz_upbeat.mp3", Sound.class);
        MyGame.manager.load("sound/background.mp3", Sound.class);
        MyGame.manager.load("sound/stinger_funky_hip_hop.mp3", Sound.class);
        MyGame.manager.load("sound/tring.mp3", Sound.class);

        MyGame.doneLoading = LoadingStatus.STARTED;
    }

    /**
     * Writes text on screen.
     *
     * @param text
     * @param x center point
     * @param y center point
     */
    protected void writeToCenter(String text, float x, float y) {
        layout.setText(font,text);
        font.draw(batch, text, x - layout.width / 2, y + layout.height / 2);
    }

    /**
     * Render progress of loading
     * @param delta - gdx delta time
     */
    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(BackgroundSprite, 0, 0,MyGame.SCREEN_WIDTH,MyGame.SCREEN_HEIGHT);

        batch.draw(initialAtlas.findRegion("Logo"), 632, 663);

        //camera.update();

/*        if(MyGame.doneLoading == LoadingStatus.NOT_STARTED){
            writeToCenter("Trying to connect to server", 841, 50);
            batch.end();
            return;
        }
*/
        batch.end();

        if (MyGame.doneLoading != LoadingStatus.DONE) {
            if (MyGame.manager.update()) {
                MyGame.doneLoading = LoadingStatus.DONE;

            }
        }

        if (Gdx.input.isKeyPressed(Keys.ENTER)) {
            time = 0;
        }

        batch.begin();

        float duration = 0;

        float percent = 0;

        if (useRealLoadingDuration) {
            percent = MyGame.manager.getProgress()*100;
        } else {
            if (isInitialLoading) {
                duration = initialLoadingDuration;
            } else {
               // duration = gameLoadingDuration[loadGameIndex];
            }
            percent = time/duration*100;
        }

        for (int i = 0; i < circles.length; i++) {
            Sprite circle = circles[i];
            float progress = Math.min(percent/(20*(i + 1)), 1);
            float start = 90;
            if (i % 2 == 1) {
                progress *= -1;
                start += 180;
            }
            float rotation = 1.25f;
            circle.setRotation(start + rotation*360*progress);
            circle.draw(batch);
        }

        int max100percent = Math.min(Math.round(percent), 100);
        writeToCenter(max100percent+" %", 841, 360);

        batch.end();

        if (percent >= 100) {
            if (MyGame.doneLoading != LoadingStatus.DONE) {
                //logger.debug("Loading didn't finish in {}, waiting for it to finish", time);
                MyGame.manager.finishLoading();
            }
            //logger.debug("Loading finished", time);
            if (isInitialLoading) {
                isInitialLoading = false;
                finishInitialLoading();
                //			return;
            } else {
                finishLoadingGame();
//				return;
            }
        }
        time += delta;
    }

    /**
     * will be ran when loading finished
     */
    private void finishLoadingGame() {
        // it is important the GameGreen gets new instance when assets are reloaded
        MyGame.menu = new MenuScreen();
        MyGame.instance.setScreen(MyGame.menu);

    }

    private void finishInitialLoading() {


    }


    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void show() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        font.dispose();
        skin.dispose();
        stage.dispose();
        batch.dispose();
    }

    public enum LoadingStatus {
        NOT_STARTED,
        STARTED,
        DONE
    }

}
