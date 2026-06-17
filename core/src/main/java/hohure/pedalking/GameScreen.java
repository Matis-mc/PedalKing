package hohure.pedalking;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import hohure.pedalking.managers.RiderManager;
import hohure.pedalking.screen.BackgroundMap;


/** First screen of the application. Displayed after the application is created. */
public class GameScreen implements Screen {

    TiledMap tiledMap;
    BitmapFont font;
    SpriteBatch batch;
    BackgroundMap backgroundMap;
    RiderManager riderManager;
    // HUD
    // Caméra et viewport dédiés au HUD (texte, UI)
    private ScreenViewport hudViewport;
    ShapeRenderer shapeRenderer;


    public GameScreen(){
        batch = new SpriteBatch();
        backgroundMap = BackgroundMap.getInstance();
        hudViewport = new ScreenViewport();
        font = new BitmapFont();
        riderManager = new RiderManager();

        shapeRenderer = new ShapeRenderer();

        riderManager.generate();
        setUpFont();

    }

    @Override
    public void show() {
        riderManager.show();

    }

    @Override
    public void render(float delta) {
        riderManager.update();
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
        riderManager.dispose();
        backgroundMap.dispose();
    }

    private void setUpFont(){
        font = new BitmapFont();
        //font.setUseIntegerPositions(false);
    }

    public void draw(){
        ScreenUtils.clear(Color.BLACK);
        drawGameScene();
        drawhHUD();

    }

    private void drawGameScene() {
        backgroundMap.updateCameraOnSprite(riderManager.getPlayerSprite());
        backgroundMap.render();

        batch.setProjectionMatrix(backgroundMap.getViewport().getCamera().combined);
        batch.begin();
        riderManager.draw(batch);
        batch.end();

        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        riderManager.drawShape(shapeRenderer);
        shapeRenderer.end();
    }

    private void drawhHUD(){
        hudViewport.apply();
        batch.setProjectionMatrix(hudViewport.getCamera().combined);
        batch.begin();
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20);
        font.draw(batch, "Player endurance: " + riderManager.getPlayer().getRiderData().getEndurance(), 10, 40);
        font.draw(batch, "Player vitesse: " + Math.round(riderManager.getPlayer().getRiderSpeed().getCurrentSpeed()*500) + "km/h", 10, 60);

        batch.end();
    }

}
