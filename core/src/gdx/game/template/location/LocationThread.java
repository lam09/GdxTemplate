package gdx.game.template.location;

import gdx.game.template.MyGame;

/**
 * Created by Lam on 8/6/2016.
 */
public class LocationThread extends Thread {


    //GPSTraker
    @Override
    public void run(){
        while (true){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
