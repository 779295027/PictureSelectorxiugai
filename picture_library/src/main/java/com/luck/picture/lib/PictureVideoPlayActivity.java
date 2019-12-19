package com.luck.picture.lib;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * @author：luck
 * @data：2017/8/28 下午11:00
 * @描述: 视频播放类
 */
public class PictureVideoPlayActivity extends PictureBaseActivity implements
        MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnInfoListener,
        MediaPlayer.OnCompletionListener, View.OnClickListener {
    private String video_path = "";
    private ImageView picture_left_back, iv_background;
    private MediaController mMediaController;
    private VideoView mVideoView;
    private ImageView iv_play;
    private int mPositionWhenPaused = -1;
    private String thumbnail;
    private View iv_loding;

    @Override
    public boolean isImmersive() {
        return false;
    }

    @Override
    public boolean isRequestedOrientation() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getResourceId() {
        return R.layout.picture_activity_video_play;
    }

    @Override
    protected void initWidgets() {
        super.initWidgets();
        video_path = getIntent().getStringExtra("video_path");
        picture_left_back = findViewById(R.id.picture_left_back);
        mVideoView = findViewById(R.id.video_view);
        iv_play = findViewById(R.id.iv_play);
        iv_loding = findViewById(R.id.iv_loding);
        iv_background = findViewById(R.id.iv_background);
        if (getIntent().hasExtra("thumbnail")) {
            thumbnail = getIntent().getStringExtra("thumbnail");
            config.imageEngine.loadImage(this, thumbnail, iv_background);
        } else {
            iv_background.setBackgroundColor(Color.BLACK);
        }
        mMediaController = new MediaController(this);
        mVideoView.setOnCompletionListener(this);
        mVideoView.setOnPreparedListener(this);
        mVideoView.setOnInfoListener(this);
        mVideoView.setMediaController(mMediaController);
        picture_left_back.setOnClickListener(this);
        iv_play.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        // Play Video
        mVideoView.setVideoPath(video_path);
        // mVideoView.start();
        super.onStart();
    }

    @Override
    public void onPause() {
        // Stop video when the activity is pause.
        mPositionWhenPaused = mVideoView.getCurrentPosition();
        mVideoView.stopPlayback();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mMediaController = null;
        mVideoView = null;
        iv_play = null;
        super.onDestroy();
    }

    @Override
    public void onResume() {
        // Resume video player
        //   iv_background.setVisibility(View.GONE);
        if (mPositionWhenPaused >= 0) {
            mVideoView.seekTo(mPositionWhenPaused);
            mPositionWhenPaused = -1;
        }

        super.onResume();
    }

    @Override
    public boolean onError(MediaPlayer player, int arg1, int arg2) {
        return false;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if (null != iv_play) {
            iv_play.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.picture_left_back) {
            finish();
        } else if (id == R.id.iv_play) {
            mVideoView.start();
            iv_play.setVisibility(View.INVISIBLE);
            mVideoView.setVisibility(View.VISIBLE);
            iv_loding.setVisibility(iv_background.getVisibility());
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(new ContextWrapper(newBase) {
            @Override
            public Object getSystemService(String name) {
                if (Context.AUDIO_SERVICE.equals(name)) {
                    return getApplicationContext().getSystemService(name);
                }
                return super.getSystemService(name);
            }
        });
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
//        mp.setOnInfoListener((mp1, what, extra) -> {
//            switch (what) {
//                case MediaPlayer.MEDIA_INFO_BUFFERING_START:
//                    iv_background.setVisibility(View.GONE);
//                    mVideoView.setBackgroundColor(Color.TRANSPARENT);
//                    return true;
//                case MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
//                    iv_loding.setVisibility(View.GONE);
//                    return true;
//                default:
//                    return false;
//            }
//        });
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        switch (what) {
            case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                iv_background.setVisibility(View.GONE);
                mVideoView.setBackgroundColor(Color.TRANSPARENT);
                return true;
            case MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
                iv_loding.setVisibility(View.GONE);
                return true;
            default:
                return false;
        }
    }
}
