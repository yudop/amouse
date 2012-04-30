package tribe.lost;

import android.view.View;

import static tribe.lost.AClientServerInterface.*;


/**
 * Created by Tobias Ericsson
 */
public class WizmoOnClickListener implements View.OnClickListener {


    public void onClick(View view) {
        String command = STATE_WIZMO + SUB_COMMAND_DELIMITER;
        switch (view.getId()) {
            case R.id.shutdownButton:
                AClient.commands.add(command + WIZMO.SHUTDOWN);
                break;
            case R.id.restartButton:
                AClient.commands.add(command + WIZMO.RESTART);
                break;
            case R.id.hibernateButton:
                AClient.commands.add(command + WIZMO.HIBERNATE);
                break;
            case R.id.screensaverButton:
                AClient.commands.add(command + WIZMO.SCREEN_SAVER);
                break;
        }

    }
}
