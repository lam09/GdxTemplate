package gdx.game.template;

import android.location.LocationListener;
import android.location.LocationManager;

/**
 * Created by Lam on 8/6/2016.
 */
public class LocationThread extends Thread {
    LocationManager locationManager;
    LocationListener locationListener;

    public LocationThread(){

    }
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

    private void printLocation()
    {

    }
}
