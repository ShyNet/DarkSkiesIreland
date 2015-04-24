package project.antoin.homepage1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import java.util.HashMap;
import java.util.Map;


public class PlanAhead extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_ahead);

        Bundle bundle = getIntent().getExtras();

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("lat", "" + bundle.getDouble("lat"));
        headers.put("long", "" + bundle.getDouble("lng"));
        headers.put("Content-Type", "text/html");

        WebView mobileWeb = (WebView) findViewById(R.id.planAheadView);
        mobileWeb.loadUrl("http://rubydemo-192402.euw1.nitrousbox.com:3000/three_days", headers);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_plan_ahead, menu);
        return true;
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
