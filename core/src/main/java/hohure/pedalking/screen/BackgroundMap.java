package hohure.pedalking.screen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Optional;

public class BackgroundMap {

    private static BackgroundMap instance;

    private float speed;
    TiledMap tiledMap;
    TiledMapRenderer renderer;
    TiledMapTileLayer groundLayer;

    // Le Viewport gère la caméra en interne
    private ExtendViewport viewport;

    // Taille de référence en unités monde (ce qu'on veut voir au minimum)
    private static final float WORLD_WIDTH = 17f;
    private static final float WORLD_HEIGHT = 13f;

    public static BackgroundMap getInstance(){
        if(instance == null){
            instance = new BackgroundMap();
        }
        return instance;
    }

    private BackgroundMap(){

        viewport = new ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT);
        tiledMap = new TmxMapLoader().load("maps/map.tmx");
        renderer = new OrthogonalTiledMapRenderer(tiledMap, 1f/ 32f);
        groundLayer = (TiledMapTileLayer) tiledMap.getLayers().get(0);

    }
    public void setSpeed(float speed)
    {
        this.speed = speed;
    }

    public void update(float x, float y)
    {
        // On déplace la caméra vers le haut au fil du temps
        viewport.getCamera().position.set(x, y, 0);
        viewport.getCamera().update();
    }

    public void render()
    {
        viewport.apply();
        renderer.setView((OrthographicCamera) viewport.getCamera());
        renderer.render();
    }

    public Viewport getViewport() {
        return viewport;
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public void dispose() {
        tiledMap.dispose();
    }

    // Appelé quand la fenêtre est redimensionnée — indispensable !
    public void resize(int width, int height) {
        // Le "true" centre la caméra automatiquement dans la vue
        viewport.update(width, height, true);
    }

    public void updateCameraOnSprite(Sprite sprite){
        this.update(
            sprite.getX() + sprite.getWidth() / 2f,  // centre X du sprite
            sprite.getY() + sprite.getHeight() / 2f  // centre Y du sprite
        );
    }

    public float interactWithMap(int x, int y){
        return Optional.ofNullable(groundLayer.getCell(x, y).getTile().getProperties().get("roul"))
            .map( r -> (float) r)
            .filter(r -> r > 0f)
            .orElse(1f);
    }


}
