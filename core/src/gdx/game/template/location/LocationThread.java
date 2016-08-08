package gdx.game.template.location;

/**
 * Created by Lam on 8/6/2016.
 */
public class LocationThread extends Thread {

public static int a = 1;

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
