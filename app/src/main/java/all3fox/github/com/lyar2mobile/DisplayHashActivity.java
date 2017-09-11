package all3fox.github.com.lyar2mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayHashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_hash);

        Intent intent = getIntent();
        String message = intent.getStringExtra("computedHash");

        TextView text_view = (TextView) findViewById(R.id.computed_hash);
        text_view.setText(message);
    }
}
