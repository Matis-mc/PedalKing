package hohure.pedalking;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static hohure.pedalking.utils.constant.ScreenConstant.HEIGHT_VIEW;
import static hohure.pedalking.utils.constant.ScreenConstant.WIDTH_VIEW;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class PedalKingGame extends Game {

    public SpriteBatch spriteBatch;
    public Viewport viewport;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(WIDTH_VIEW, HEIGHT_VIEW);
        setScreen(new GameScreen());
    }

    @Override
    public void render(){
        super.render();
    }

    @Override
    public void dispose(){
        spriteBatch.dispose();
    }
}
