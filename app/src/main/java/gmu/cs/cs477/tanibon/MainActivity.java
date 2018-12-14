package gmu.cs.cs477.tanibon;

import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.SoundPool;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.config.AWSConfiguration;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;


import gmu.cs.cs477.tanibon.services.MusicService;

public class MainActivity extends AppCompatActivity {
    ImageView title_bird;
    ActivityOptions aO;
    ObjectAnimator rotate;
    boolean music_playing = true;
    int easter_egg = 0;
    public enum fragmentType { NEW_GAME, CONTINUE_GAME}
    DynamoDBMapper dynamoDBMapper; // Declare a DynamoDBMapper object


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(MainActivity.this, MusicService.class));
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f , 1.0f ) ;
        AlphaAnimation fadeOut = new AlphaAnimation( 1.0f , 0.0f ) ;
        title_bird = findViewById(R.id.title_bird);
        aO = ActivityOptions.makeSceneTransitionAnimation(this, title_bird, "first_bird");

        // AWSMobileClient enables AWS user credentials to access your table
        AWSMobileClient.getInstance().initialize(this).execute();

        AWSCredentialsProvider credentialsProvider = AWSMobileClient.getInstance().getCredentialsProvider();
        AWSConfiguration configuration = AWSMobileClient.getInstance().getConfiguration();


        // Add code to instantiate a AmazonDynamoDBClient
        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(credentialsProvider);

        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(configuration)
                .build();

        rotate = ObjectAnimator.ofFloat(title_bird, "rotation",   0f, 30f, 0f, -10f, 0f, 30f, 0f);
        rotate.setRepeatCount(20000);
        rotate.setDuration(7000);
        rotate.start();
        title_bird.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(easter_egg == 3) {
                    rotate.pause();
                    ObjectAnimator dumbRotate = ObjectAnimator.ofFloat(title_bird, "rotation", 100f, 0f, -100f, 0f, 100f);
                    dumbRotate.setRepeatCount(20);
                    dumbRotate.setDuration(100);
                    dumbRotate.start();
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                    }
                    easter_egg = 0;
                    rotate.start();
                }else {
                    easter_egg ++;
                }

            }
        });
    }

    @Override
    public void onPause(){
        super.onPause();
        //stopService(new Intent(MainActivity.this, MusicService.class));
    }

    @Override
    public void onResume(){
        super.onResume();
        //startService(new Intent(MainActivity.this, MusicService.class));
    }

    public void onClick(View src){
        switch (src.getId()){
            case R.id.audio_imgbtn:
                if(music_playing){
                    music_playing = false;
                    stopService(new Intent(MainActivity.this, MusicService.class));
                }
                else{
                    music_playing = true;
                    startService(new Intent(MainActivity.this, MusicService.class));
                }
                break;

            case R.id.new_imgbtn:
                //if save file, make sure player wants to delete old save and start fresh
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Are you sure you want to start a new game?");
                builder.setPositiveButton("Yeah, I hate those guys", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, NewGameActivity.class);
                        startActivity(intent, aO.toBundle());
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    }
                });
                builder.setNegativeButton("No, I love my Tanibons!", null);
                AlertDialog viewDialog = builder.create();
                viewDialog.show();
                //start new_game fragment
                //get egg select info, name, etc
                //load nest fragment
                break;

            case R.id.continue_imgbtn:
                //get firebase info
                //load nest fragment
                break;
        }
    }
}

    public String topScoreQuery() {

        final String maxName = null;

        new Thread(new Runnable() {
            @Override
            public void run() {

                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withConsistentRead(false);

                PaginatedList<UserDB> result = dynamoDBMapper.query(UserDB.class, queryExpression);

                UserDB max = null;
                // Loop through query results
                for (int i = 0; i < result.size(); i++) {
                    if (UserDB curr = result.get(i).score > max.score){
                        max = curr;
                    }
                }

                maxName = max.userName;

            }
        }).start();


    }
