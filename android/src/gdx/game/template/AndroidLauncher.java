package gdx.game.template;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import gdx.game.template.location.*;
import gdx.game.template.location.LocationThread;

public class AndroidLauncher extends AndroidApplication {

	LocationManager locationManager;
	LocationListener locationListener;
	LocationThread locationThread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		locationThread = new LocationThread();
		locationThread.setDaemon(true);
		locationThread.start();
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new MyGame(), config);

		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		locationListener = new LocationListener() {
			@Override
			public void onLocationChanged(Location location) {
				MyGame.locationX = location.getLatitude();
				MyGame.locationY = location.getLongitude();
			}

			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {

			}

			@Override
			public void onProviderEnabled(String provider) {

			}

			@Override
			public void onProviderDisabled(String provider) {

			}
		};
	}
}
