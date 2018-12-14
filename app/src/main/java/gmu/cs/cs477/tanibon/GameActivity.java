package gmu.cs.cs477.tanibon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import gmu.cs.cs477.tanibon.services.MainMusicService;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        startService(new Intent(GameActivity.this, MainMusicService.class));

    }
}
