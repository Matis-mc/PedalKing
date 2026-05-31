package hohure.pedalking;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import hohure.pedalking.artefacts.riders.Rider;
import hohure.pedalking.artefacts.riders.RiderHandler;
import hohure.pedalking.screen.BackgroundMap;


/** First screen of the application. Displayed after the application is created. */
public class GameScreen implements Screen {

    TiledMap tiledMap;
    BitmapFont font;
    SpriteBatch batch;
    BackgroundMap backgroundMap;
    Rider rider;

    // HUD
    // Caméra et viewport dédiés au HUD (texte, UI)
    private ScreenViewport hudViewport;


    public GameScreen(){
        batch = new SpriteBatch();
        backgroundMap = new BackgroundMap();
        hudViewport = new ScreenViewport();
        font = new BitmapFont();
        rider = new Rider();
        setUpFont();

    }

    @Override
    public void show() {
        MapProperties props = backgroundMap.getTiledMap().getProperties();
        float mapCenterX = props.get("width", Integer.class) / 2f;
        float mapCenterY = props.get("height", Integer.class) / 2f;
        rider.getSprite().setCenter(mapCenterX, mapCenterY);

    }

    @Override
    public void render(float delta) {
        input();
        draw();
    }

    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a normal size before updating.
        if(width <= 0 || height <= 0) return;
        // Les deux viewports doivent être mis à jour quand la fenêtre change
        backgroundMap.resize(width, height);
        hudViewport.update(width, height, true);
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
        rider.dispose();
        backgroundMap.dispose();
    }

    private void setUpFont(){
        font = new BitmapFont();
        //font.setUseIntegerPositions(false);
    }

    public void draw(){
        // Draw your screen here. "delta" is the time since last render in seconds.
        ScreenUtils.clear(Color.BLACK);
        backgroundMap.updateCameraOnSprite(rider.getSprite());
        backgroundMap.render();

        batch.setProjectionMatrix(backgroundMap.getViewport().getCamera().combined);
        batch.begin();
        rider.draw(batch);
        batch.end();

        // --- 2. Rendu du HUD (texte, UI) ---
        // On change de viewport : maintenant on travaille en pixels
        hudViewport.apply();
        batch.setProjectionMatrix(hudViewport.getCamera().combined);
        batch.begin();
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20);
        batch.end();
    }

    private void input() {
        RiderHandler.handle(rider);
    }


}
