package hohure.pedalking.artefacts.riders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Rider {


    // rider
    private Texture riderTexture;
    private Sprite riderSprite;

    public Rider(){

        riderTexture = new Texture("riders/rider.png");
        riderSprite = new Sprite(riderTexture);
        float riderWitdh = (riderTexture.getWidth() / 32f) / 2;
        float riderHeight = (riderTexture.getHeight() / 32f) / 2;
        riderSprite.setSize(riderWitdh, riderHeight);
    }

    public Sprite getSprite(){
        return riderSprite;
    }

    public void draw(SpriteBatch batch){
        riderSprite.draw(batch);
    }

    public void dispose(){
        riderTexture.dispose();
    }

    public void moveForward(float speed){

    }

}
