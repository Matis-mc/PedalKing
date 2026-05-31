package hohure.pedalking.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import hohure.pedalking.debogging.OrthoCamController;
import hohure.pedalking.utils.constant.ScreenConstant;

public class BackgroundMap {

    private OrthographicCamera orthographicCamera;
    private float speed;
    TiledMap tiledMap;
    TiledMapRenderer renderer;
    OrthoCamController orthoCamController;

    public BackgroundMap(SpriteBatch spriteBatch){
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        orthographicCamera = new OrthographicCamera();
        orthographicCamera.setToOrtho(false, (w / h) * 13, 13);
        tiledMap = new TmxMapLoader().load("maps/map.tmx");
        renderer = new OrthogonalTiledMapRenderer(tiledMap, 1f/ 32f);
        orthoCamController = new OrthoCamController(orthographicCamera);
        Gdx.input.setInputProcessor(orthoCamController);
        orthographicCamera.position.set(ScreenConstant.WIDTH_VIEW / 2f, ScreenConstant.HEIGHT_VIEW / 2f, 0);
        spriteBatch.setProjectionMatrix(orthographicCamera.combined);

    }
    public void setSpeed(float speed)
    {
        this.speed = speed;
    }

    public void update(float delta)
    {
        orthographicCamera.translate(0, speed * delta);
        orthographicCamera.update();
    }

    public void render()
    {
        orthographicCamera.update();
        renderer.setView(orthographicCamera);
        renderer.render();
    }


}
