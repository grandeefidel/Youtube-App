package com.fidelity.youtubeplayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.youtube.player.YouTubeStandalonePlayer;

public class StandaloneActivity extends AppCompatActivity implements View.OnClickListener {
        Button  btnPlayVideo;
        Button  btnPlayPlaylist;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standalone);

    btnPlayVideo = findViewById(R.id.playVideo);
    btnPlayPlaylist = findViewById(R.id.playPlaylist);

    btnPlayVideo.setOnClickListener(this);
    btnPlayPlaylist.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent =  null;

        switch (view.getId()){
            case R.id.playVideo:
                intent = YouTubeStandalonePlayer.createVideoIntent(this, YoutubeActivity.GOOGLE_API_KEY, YoutubeActivity.YOUTUBE_VIDEO_ID, 0 , true, false);
                break;
            case R.id.playPlaylist:
                intent = YouTubeStandalonePlayer.createPlaylistIntent(this, YoutubeActivity.GOOGLE_API_KEY, YoutubeActivity.YOUTUBE_PLAYLIST, 0,0, true, false);
                break;
            default:

        }
        if(intent !=null)
            startActivity(intent);
    }
}
