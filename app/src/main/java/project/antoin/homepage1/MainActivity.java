package project.antoin.homepage1;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements LocationListener {
    private TextView latituteField;
    private TextView longitudeField;
    private LocationManager locationManager;
    private String provider;
    private double lat,lng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        latituteField = (TextView) findViewById(R.id.latText);
        longitudeField = (TextView) findViewById(R.id.longText);

        // Get the location manager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Define the criteria how to select the location provider -> use
        // default
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            onLocationChanged(location);
        } else {
            latituteField.setText("Location not available");
            longitudeField.setText("Location not available");
        }
    }
    /* Request updates at startup */
    @Override
    protected void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }
    /* Remove the locationlistener updates when Activity is paused */
    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }
    @Override
    public void onLocationChanged(Location location) {
         lat =  (location.getLatitude());
         lng =  (location.getLongitude());
        latituteField.setText("latitude is: " +String.valueOf(lat));
        longitudeField.setText("longitude is: "+ String.valueOf(lng));
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }
    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Enabled new provider " + provider,
                Toast.LENGTH_SHORT).show();

    }
    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }






    public void changeBestLocationNow(View view) {
        Intent intent = new Intent(this, BestLocationNow.class);
        Bundle b = new Bundle();
        b.putDouble("lat",lat);
        b.putDouble("lng",lng);

        intent.putExtra("lat",lat);
        intent.putExtra("lng",lng);

        startActivity(intent);



    }

    public  void changePlanAhead(View view){
        Intent intent = new Intent(this, PlanAhead.class);
        Bundle b = new Bundle();
        b.putDouble("lat",lat);
        b.putDouble("lng",lng);

        intent.putExtra("lat",lat);
        intent.putExtra("lng",lng);
        startActivity(intent);


    }
    public void changeNews(View view){
        Intent intent= new Intent(this, News.class);
        startActivity(intent);
    }
    public void changeImages(View view){
        Intent intent = new Intent(this, Images.class);
        startActivity(intent);
    }
    public void changeRecentActivity(View view){
        Intent intent = new Intent(this, RecentActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

