package hohure.pedalking;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.ScreenUtils;
import hohure.pedalking.screen.BackgroundMap;


/** First screen of the application. Displayed after the application is created. */
public class GameScreen implements Screen {

    TiledMap tiledMap;
    BitmapFont font;
    SpriteBatch batch;
    BackgroundMap backgroundMap;

    // rider
    Texture riderTexture;
    Sprite riderSprite;


    public GameScreen(){
        batch = new SpriteBatch();
        backgroundMap = new BackgroundMap(batch);
        setUpFont();

        // rider
        riderTexture = new Texture("riders/rider.png");
        riderSprite = new Sprite(riderTexture);
    }

    @Override
    public void show() {
        // Prepare your screen here.

    }

    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.
        ScreenUtils.clear(Color.BLACK);
        backgroundMap.render();
        batch.begin();
        //font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20);
        riderSprite.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a normal size before updating.
        if(width <= 0 || height <= 0) return;

        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        // Destroy screen's assets here.
        font.dispose();
        tiledMap.dispose();
    }

    private void setUpFont(){
        font = new BitmapFont();
        //font.setUseIntegerPositions(false);
    }

}
