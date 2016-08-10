package gdx.game.template.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import gdx.game.template.MyGame;
import gdx.game.template.gameItems.Basket;

/**
 * Created by a.lam.tuan on 5. 8. 2016.
 */
public class MenuScreen implements Screen {
    Texture texture;
    private SpriteBatch batch;
    Music backgroundSound;
    Basket basket ;
    public MenuScreen() {
        texture = MyGame.manager.get("badlogic.jpg",Texture.class);
        backgroundSound = MyGame.manager.get("sound/background.mp3", Music.class);
        batch = new SpriteBatch();
        backgroundSound.play(  );
        basket = new Basket();
    }

    public void update(float delta){
        if(Gdx.input.justTouched())
        {
            MyGame.instance.setScreen(basket);
        }
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        batch.begin();
        batch.draw(texture,0,0);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
