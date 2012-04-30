package tribe.lost;

import android.view.View;

import static tribe.lost.AClientServerInterface.STATE_WINAMP;
import static tribe.lost.AClientServerInterface.SUB_COMMAND_DELIMITER;

/**
 * Created by Tobias Ericsson
 */
public class MusicOnClickListener implements View.OnClickListener {

    boolean isPlaying = false;
    boolean isStarted = false;

    public void onClick(View view) {
        String command = STATE_WINAMP + SUB_COMMAND_DELIMITER;
        switch (view.getId()) {
            case R.id.startStopButton:
                if (isStarted) {
                    AClient.commands.add(command + AClientServerInterface.Winamp.STOP);
                    isStarted = false;
                } else {
                    AClient.commands.add(command + AClientServerInterface.Winamp.START);
                    isStarted = true;
                }
                break;

            case R.id.playPauseButton:
                if (isPlaying) {
                    AClient.commands.add(command + AClientServerInterface.Winamp.PAUSE);
                    isPlaying = false;
                } else {
                    AClient.commands.add(command + AClientServerInterface.Winamp.PLAY);
                    isPlaying = true;
                }
                break;

            case R.id.prevButton:
                AClient.commands.add(command + AClientServerInterface.Winamp.PREV);
                break;

            case R.id.nextButton:
                AClient.commands.add(command + AClientServerInterface.Winamp.NEXT);
                break;
            case R.id.volUpButton:
                AClient.commands.add(command + AClientServerInterface.Winamp.VOL_UP);
                break;
            case R.id.volDownButton:
                AClient.commands.add(command + AClientServerInterface.Winamp.VOL_DOWN);
                break;
        }
    }
}
