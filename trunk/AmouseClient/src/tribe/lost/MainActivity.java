package tribe.lost;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import static tribe.lost.AClientServerInterface.*;


/**
 * Created by Tobias Ericsson
 */
public class MainActivity extends Activity {

    private final static String TAG = MainActivity.class.getName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);


        PagerAdapter aPagerAdapter = new APagerAdapter();
        ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(aPagerAdapter);
        pager.setCurrentItem(3);

        OnCheckedChangePageListener onCheckedChangeListener = new OnCheckedChangePageListener();

        RadioButton radioButton1 = (RadioButton) MainActivity.this.findViewById(R.id.radioButton1);
        radioButton1.setOnCheckedChangeListener(onCheckedChangeListener);

        RadioButton radioButton2 = (RadioButton) MainActivity.this.findViewById(R.id.radioButton2);
        radioButton2.setOnCheckedChangeListener(onCheckedChangeListener);

        RadioButton radioButton3 = (RadioButton) MainActivity.this.findViewById(R.id.radioButton3);
        radioButton3.setOnCheckedChangeListener(onCheckedChangeListener);

        RadioButton radioButton4 = (RadioButton) MainActivity.this.findViewById(R.id.radioButton4);
        radioButton4.setChecked(true);

        radioButton4.setOnCheckedChangeListener(onCheckedChangeListener);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int i, float v, int i1) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        RadioButton radioButton1 = (RadioButton) MainActivity.this.findViewById(R.id.radioButton1);
                        radioButton1.setChecked(true);
                        break;
                    case 1:
                        RadioButton radioButton2 = (RadioButton) MainActivity.this.findViewById(R.id.radioButton2);
                        radioButton2.setChecked(true);
                        break;
                    case 2:
                        RadioButton radioButton3 = (RadioButton) MainActivity.this.findViewById(R.id.radioButton3);
                        radioButton3.setChecked(true);
                        break;
                    case 3:
                        RadioButton radioButton4 = (RadioButton) MainActivity.this.findViewById(R.id.radioButton4);
                        radioButton4.setChecked(true);
                        break;
                }

                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void onPageScrollStateChanged(int i) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });


        /*
   takeKeyEvents(true);
   MusicOnClickListener musicOnClickListener = new MusicOnClickListener();

   Button button = (Button) findViewById(R.id.startStopButton);
   button.setOnClickListener(musicOnClickListener);
   button = (Button) findViewById(R.id.playPauseButton);
   button.setOnClickListener(musicOnClickListener);
   button = (Button) findViewById(R.id.prevButton);
   button.setOnClickListener(musicOnClickListener);
   button = (Button) findViewById(R.id.nextButton);
   button.setOnClickListener(musicOnClickListener);
   button = (Button) findViewById(R.id.volDownButton);
   button.setOnClickListener(musicOnClickListener);
   button = (Button) findViewById(R.id.volUpButton);
   button.setOnClickListener(musicOnClickListener);     */


    }

    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        ((AApplication) getApplicationContext()).connectToServerIfNotConnected(this);


    }

    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        ((AApplication) getApplicationContext()).disconnectFromServer();
    }

    class OnCheckedChangePageListener implements RadioButton.OnCheckedChangeListener {

        public void onCheckedChanged(CompoundButton compoundButton, boolean clicked) {

            Log.d(TAG, "button " + compoundButton + " clicked " + clicked);

            if (clicked) {
                ViewPager pager = (ViewPager) MainActivity.this.findViewById(R.id.viewPager);
                switch (compoundButton.getId()) {
                    case R.id.radioButton1:
                        pager.setCurrentItem(0);
                        break;
                    case R.id.radioButton2:
                        pager.setCurrentItem(1);
                        break;
                    case R.id.radioButton3:
                        pager.setCurrentItem(2);
                        break;
                    case R.id.radioButton4:
                        pager.setCurrentItem(3);
                        break;
                }
            }
        }
    }

}