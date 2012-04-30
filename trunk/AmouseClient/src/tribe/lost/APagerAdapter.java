package tribe.lost;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.support.v4.view.ViewPager;

/**
 * Created by Tobias Ericsson
 */
public class APagerAdapter extends PagerAdapter {


    @Override
    public int getCount() {
        return 4;

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View view = null;
        Button button = null;
        switch (position) {
            case 0:
                view = inflater.inflate(R.layout.winamp_layout, null);
                MusicOnClickListener musicOnClickListener = new MusicOnClickListener();

                button = (Button) view.findViewById(R.id.startStopButton);
                button.setOnClickListener(musicOnClickListener);
                button = (Button) view.findViewById(R.id.playPauseButton);
                button.setOnClickListener(musicOnClickListener);
                button = (Button) view.findViewById(R.id.prevButton);
                button.setOnClickListener(musicOnClickListener);
                button = (Button) view.findViewById(R.id.nextButton);
                button.setOnClickListener(musicOnClickListener);
                button = (Button) view.findViewById(R.id.volDownButton);
                button.setOnClickListener(musicOnClickListener);
                button = (Button) view.findViewById(R.id.volUpButton);
                button.setOnClickListener(musicOnClickListener);
                break;
            case 1:
                view = inflater.inflate(R.layout.vlc_layout, null);
                ((TextView) view.findViewById(R.id.title)).setText("VIDEO");
                break;
            case 2:
                view = inflater.inflate(R.layout.wizmo_layout, null);
                ((TextView) view.findViewById(R.id.title)).setText("CLOSEDOWN");
                WizmoOnClickListener wizmoOnClickListener = new WizmoOnClickListener();

                button = (Button) view.findViewById(R.id.shutdownButton);
                button.setOnClickListener(wizmoOnClickListener);
                button = (Button) view.findViewById(R.id.hibernateButton);
                button.setOnClickListener(wizmoOnClickListener);
                button = (Button) view.findViewById(R.id.restartButton);
                button.setOnClickListener(wizmoOnClickListener);
                button = (Button) view.findViewById(R.id.screensaverButton);
                button.setOnClickListener(wizmoOnClickListener);
                break;
            case 3:
                view = inflater.inflate(R.layout.keyboard_layout, null);
                ((TextView) view.findViewById(R.id.title)).setText("KEYBOARD");
                KeyViewOnKeyListener keyViewOnKeyListener = new KeyViewOnKeyListener();

                AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) view.findViewById(R.id.keyView);
                autoCompleteTextView.setOnKeyListener(keyViewOnKeyListener);
                /*InputMethodManager imm =
                        (InputMethodManager) container.getContext().getSystemService
                                (Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                        InputMethodManager.HIDE_IMPLICIT_ONLY);  */
                break;
        }
        container.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        container.removeView((View) view);
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public void finishUpdate (ViewGroup container) {

    }
}