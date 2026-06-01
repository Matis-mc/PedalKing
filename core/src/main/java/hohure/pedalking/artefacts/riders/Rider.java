package hohure.pedalking.artefacts.riders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Rider {

    // rider
    private Texture riderTexture;
    private Sprite riderSprite;
    private RiderData riderData;

    public Rider(){

        riderTexture = new Texture("riders/rider.png");
        riderSprite = new Sprite(riderTexture);
        float riderWitdh = (riderTexture.getWidth() / 32f) / 2;
        float riderHeight = (riderTexture.getHeight() / 32f) / 2;
        riderSprite.setSize(riderWitdh, riderHeight);
        riderData = new RiderData(100, 100);
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

    public void moveForward(float distance){
        riderSprite.translateY(distance);
    }
    public void moveLeft(float distance){
        riderSprite.translateX(distance);
    }
    public void moveRight(float distance){
        riderSprite.translateX(distance);
    }
    public float sprint(float speed){
        if(riderData.endurance <=0) return speed;
        riderData.endurance -= 1;
        return speed * 2;
    }

}
