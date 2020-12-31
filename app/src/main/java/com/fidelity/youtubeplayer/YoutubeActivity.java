package com.fidelity.youtubeplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private static final String TAG = "YoutubeActivity";
    static final String GOOGLE_API_KEY = "AIzaSyBYGCdmAe1c_xUqZAEQ4wgVvRRxVjzd3Ps";
    static final String YOUTUBE_VIDEO_ID = "ElpitAfkRS4";
    static final String YOUTUBE_PLAYLIST = "PLXtTjtWmQhg1SsviTmKkWO5n0a_-T0bnD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_youtube);
        ConstraintLayout layout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_youtube, null);
        setContentView(layout);

        YouTubePlayerView youTubePlayerView = new YouTubePlayerView(this);
        youTubePlayerView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.addView(youTubePlayerView);
        youTubePlayerView.initialize(GOOGLE_API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        Log.d(TAG, "onInitializationSuccess: Provider is "+ provider.getClass().toString());
        Toast.makeText(this, "Player was initialised suucessfully", Toast.LENGTH_LONG).show();

        youTubePlayer.setPlaybackEventListener(mPlaybackEventListener);
        youTubePlayer.setPlayerStateChangeListener(mPlayerStateChangeListener);
        if(!wasRestored){
            youTubePlayer.loadVideo(YOUTUBE_VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        final int REQUEST_CODE = 1;

        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show();
        }else{
            String errorMessage = String.format("Error occured while initializing %1$s", youTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }

    }

    private YouTubePlayer.PlaybackEventListener mPlaybackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {
            Toast.makeText(YoutubeActivity.this," Video has started playing" , Toast.LENGTH_LONG).show();
        }

        @Override
        public void onPaused() {
            Toast.makeText(YoutubeActivity.this," Video has paused" , Toast.LENGTH_LONG).show();

        }

        @Override
        public void onStopped() {

        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };

    private YouTubePlayer.PlayerStateChangeListener mPlayerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {
            Toast.makeText(YoutubeActivity.this,"Click ad now and make the video creator richer" , Toast.LENGTH_LONG).show();
        }

        @Override
        public void onVideoStarted() {
            Toast.makeText(YoutubeActivity.this," Video has started" , Toast.LENGTH_LONG).show();

        }

        @Override
        public void onVideoEnded() {
            Toast.makeText(YoutubeActivity.this," Video has ended" , Toast.LENGTH_LONG).show();

        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };
}