package tribe.lost;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

/**
 * Created by IntelliJ IDEA.
 * User: TOER
 * Date: 2012-05-04
 * Time: 13:15
 * To change this template use File | Settings | File Templates.
 */
public class SettingsActivity extends Activity {

    SharedPreferences prefs;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        // Calling super after populating the menu is necessary here to ensure that the
        // action bar helpers have a chance to handle this event.
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Toast.makeText(this, "Tapped home", Toast.LENGTH_SHORT).show();
                //break;
                /*
          case R.id.menu_search:
              Toast.makeText(this, "Tapped search", Toast.LENGTH_SHORT).show();
              break;  */
            case R.id.menu_settings:
                //Toast.makeText(this, "Tapped share", Toast.LENGTH_SHORT).show();
                //Intent i = new Intent(this,HomeActivity.class);
                //startActivity(i);
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        prefs = SettingsActivity.this.getSharedPreferences("settings", MODE_PRIVATE);

        String ip = prefs.getString("ip",null);
        int port = prefs.getInt("port",AClientServerInterface.PORT);
        TextView textView = (TextView) findViewById(R.id.ip_address);
        textView.setText(ip);
        textView = (TextView) findViewById(R.id.port);
        textView.setText(port+"");

        Button button = (Button) findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView = (TextView) findViewById(R.id.ip_address);
                String ip = textView.getText().toString();
                textView = (TextView) findViewById(R.id.port);
                String port = textView.getText().toString();
                prefs.edit().putString("ip", ip).putInt("port", Integer.parseInt(port)).commit();
                Toast.makeText(SettingsActivity.this, "Settings saved!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
