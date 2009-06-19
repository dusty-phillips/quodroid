package ca.archlinux;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.KeyEvent;
import android.view.View;

public class QuodroidSettings extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        final EditText host = (EditText) findViewById(R.id.host);
        final EditText port = (EditText) findViewById(R.id.port );
        SettingsListener key_down = new SettingsListener();
        host.setOnKeyListener(key_down);
        port.setOnKeyListener(key_down);
    }
    
    private class SettingsListener implements View.OnKeyListener {
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
              return true;
            }
            return false;
        }
}

}
