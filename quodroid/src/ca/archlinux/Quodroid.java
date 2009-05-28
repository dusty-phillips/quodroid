package ca.archlinux;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Quodroid extends Activity {
    /** Called when the activity is first created. */
    private Button prev_button, next_button, pause_button;
    private TextView status_text;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //FIXME: copy-paste
        ButtonAction prev_listener = new PrevAction();
        prev_button = (Button) findViewById(R.id.prev_button);
        prev_button.setOnClickListener(prev_listener);

        ButtonAction next_listener = new NextAction();
        next_button = (Button) findViewById(R.id.next_button);
        next_button.setOnClickListener(next_listener);

        ButtonAction pause_listener = new PauseAction();
        pause_button = (Button) findViewById(R.id.pause_button);
        pause_button.setOnClickListener(pause_listener);

        status_text = (TextView) findViewById(R.id.status_text);
    }

    private abstract class ButtonAction implements Button.OnClickListener {
        public void process_url(String parameter) {
            try {
                URL url = new URL("http://192.168.1.110:8000/" + 
                        parameter + "/");
                InputStreamReader r = new InputStreamReader(
                        url.openStream());
                BufferedReader br = new BufferedReader(r);
                String response = br.readLine();
                status_text.setText(response);
            }
            catch (Exception e) {}
        }

        @Override
        public abstract void onClick(View arg0);
    }

    private class PrevAction extends ButtonAction {
        @Override
        public void onClick(View arg0) {
            process_url("prev");
        }
    }

    private class NextAction extends ButtonAction {
        @Override
        public void onClick(View arg0) {
            process_url("next");
        }
    }

    private class PauseAction extends ButtonAction {
        @Override
        public void onClick(View arg0) {
            process_url("pause");
        }
    }
}
