package all3fox.github.com.lyar2mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.github.all3fox.lyra2.Lyra2;
import com.github.all3fox.lyra2.LyraParams;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void computeHash(View view) {
        Intent intent = new Intent(this, DisplayHashActivity.class);

        EditText sponge_field = (EditText) findViewById(R.id.sponge);

        String sponge_name = sponge_field.getText().toString();

        if (sponge_name.isEmpty()) {
            sponge_name = "blake2b";
        }

        Integer tcost;
        EditText tcost_field = (EditText) findViewById(R.id.tcost);
        String tcost_str = tcost_field.getText().toString();
        if (tcost_str.isEmpty()) {
            tcost = 100;
        } else {
            tcost = Integer.parseInt(tcost_str);
        }

        Integer mcost;
        EditText mcost_field = (EditText) findViewById(R.id.mcost);
        String mcost_str = mcost_field.getText().toString();
        if (mcost_str.isEmpty()) {
            mcost = 100;
        } else {
            mcost = Integer.parseInt(mcost_str);
        }

        Integer outlen;
        EditText outlen_field = (EditText) findViewById(R.id.outlen);
        String outlen_str = outlen_field.getText().toString();
        if (outlen_str.isEmpty()) {
            outlen = 10;
        } else {
            outlen = Integer.parseInt(outlen_str);
        }

        String pass;
        EditText pass_field = (EditText) findViewById(R.id.password);
        pass = pass_field.getText().toString();
        if (pass.isEmpty()) {
            pass = "password";
        }

        String salt;
        EditText salt_field = (EditText) findViewById(R.id.salt);
        salt = salt_field.getText().toString();
        if (salt.isEmpty()) {
            salt = "salt";
        }

        Integer rounds;
        EditText rounds_field = (EditText) findViewById(R.id.rounds);
        String rounds_str = rounds_field.getText().toString();
        if (rounds_str.isEmpty()) {
            rounds = 12;
        } else {
            rounds = Integer.parseInt(rounds_str);
        }

        Integer blocks;
        EditText blocks_field = (EditText) findViewById(R.id.blocks);
        String blocks_str = blocks_field.getText().toString();
        if (blocks_str.isEmpty()) {
            blocks = 12;
        } else {
            blocks = Integer.parseInt(blocks_str);
        }

        Integer columns;
        EditText columns_field = (EditText) findViewById(R.id.columns);
        String columns_str = columns_field.getText().toString();
        if (columns_str.isEmpty()) {
            columns = 256;
        } else {
            columns = Integer.parseInt(columns_str);
        }

        LyraParams params = new LyraParams(
                outlen
                , tcost
                , mcost
                , columns
                , sponge_name
                , 12
                , rounds
                , blocks
        );

        byte[] hash = new byte[10];
        byte[] pass_bytes = pass.getBytes();
        byte[] salt_bytes = salt.getBytes();

        Lyra2.phs(hash, pass_bytes, salt_bytes, params);

        StringBuilder message = new StringBuilder();
        for (int i = 0; i != hash.length - 1; ++i) {
            message.append(String.format("%02X", hash[i]));
            message.append(" ");
        } message.append(String.format("%02X", hash[hash.length - 1]));

        intent.putExtra("computedHash", message.toString());
        startActivity(intent);
    }
}
