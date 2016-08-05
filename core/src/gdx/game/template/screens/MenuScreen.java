package gdx.game.template.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import gdx.game.template.MyGame;

/**
 * Created by a.lam.tuan on 5. 8. 2016.
 */
public class MenuScreen implements Screen {
    Texture texture;
    private SpriteBatch batch;
    public MenuScreen() {
        texture = MyGame.manager.get("badlogic.jpg",Texture.class);
        batch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
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
