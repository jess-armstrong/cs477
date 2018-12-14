package gmu.cs.cs477.tanibon;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import gmu.cs.cs477.tanibon.services.MusicService;

public class NewGameActivity extends AppCompatActivity {
    ImageView title_bird, selectEgg, willowEgg, lambEgg, dandyEgg;
    ObjectAnimator rotate;
    Animation egg_shake;
    TextView text;
    SoundPoolHelper soundPool;
    int textCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        soundPool = new SoundPoolHelper(this);
        willowEgg = findViewById(R.id.egg3);
        lambEgg = findViewById(R.id.egg2);
        dandyEgg = findViewById(R.id.egg1);

        selectEgg = findViewById(R.id.select_egg);
        title_bird = findViewById(R.id.title_bird2);
        title_bird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("chirp", "CHIRP HERE");
                soundPool.play(R.raw.primarina);
            }
        });
        rotate = ObjectAnimator.ofFloat(title_bird, "rotation",   0f, 30f, 0f, -10f, 0f, 30f, 0f);
        rotate.setRepeatCount(20000);
        rotate.setDuration(7000);
        rotate.start();

        egg_shake = AnimationUtils.loadAnimation(this, R.anim.egg_shake);

        text = findViewById(R.id.new_game_text);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (textCount){
                    case 0:
                        text.setText("Some weird plant-bird monstrosity...");
                        textCount++;
                        break;
                    case 1:
                        text.setText("and now you'll be responsible for one!");
                        textCount ++;
                        break;
                    case 2:
                        text.setText("Congratulations on parenthood!");
                        soundPool.play(R.raw.primarina);
                        textCount ++;
                        break;
                    case 3:
                        text.setText("");
                        title_bird.setEnabled(false);
                        title_bird.setVisibility(View.INVISIBLE);
                        selectEgg.setVisibility(View.VISIBLE);
                        willowEgg.setVisibility(View.VISIBLE);
                        lambEgg.setVisibility(View.VISIBLE);
                        dandyEgg.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
    }

    public void selectEgg(int id){
        final int egg_id = id;
        AlertDialog.Builder builder = new AlertDialog.Builder(NewGameActivity.this);
        builder.setMessage("Are you sure you want this egg?");
        builder.setPositiveButton("Yes! This one!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(NewGameActivity.this, GameActivity.class);
                intent.putExtra("egg type", egg_id);
                stopService(new Intent(NewGameActivity.this, MusicService.class));
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        builder.setNegativeButton("No, this one's terrible.", null);
        AlertDialog viewDialog = builder.create();
        viewDialog.show();
    }
    public void onClick(View src){
        switch (src.getId()){
            case R.id.egg1:
                dandyEgg.startAnimation(egg_shake);
                selectEgg(R.id.egg1);
                break;
            case R.id.egg2:
                lambEgg.startAnimation(egg_shake);
                selectEgg(R.id.egg2);
                break;
            case R.id.egg3:
                willowEgg.startAnimation(egg_shake);
                selectEgg(R.id.egg3);
                break;

        }
    }
}
