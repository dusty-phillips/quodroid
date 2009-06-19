package ca.archlinux;

import android.app.Activity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.text.TextWatcher;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;

public class QuodroidSettings extends Activity {
    public static final String PREFS_NAME = "quodroid_preferences";
    private EditText host;
    private EditText port;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        host = (EditText) findViewById(R.id.host);
        port = (EditText) findViewById(R.id.port );

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        host.setText(settings.getString("host", "192.168.1.110"));
        port.setText(settings.getString("port", "9250"));

        SettingsListener listener = new SettingsListener();
        host.addTextChangedListener(listener);
        port.addTextChangedListener(listener);
    }
    
    private class SettingsListener implements TextWatcher {
        public void afterTextChanged(Editable s) {
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("host", host.getText().toString());
            editor.putString("port", port.getText().toString());
            editor.commit();
        }
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    }

}
