package com.example.exoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.ObjectKey;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

public class MainActivity extends AppCompatActivity {
    public PlayerView playerView;
    public TextView txtChannelName, txtChannelDesc, txtChannelNumber;
    private ImageView imgChannelLogo;
    public static String drmLicenseUrl = "https://proxy.uat.widevine.com/proxy?provider=widevine_test";
    public static SimpleExoPlayer player;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);
        playerView = findViewById(R.id.playerView);
        txtChannelName = findViewById(R.id.txtChannelName);
        txtChannelDesc = findViewById(R.id.txtChannelDesc);
        txtChannelNumber = findViewById(R.id.txtChannelNumber);
        imgChannelLogo = findViewById(R.id.imgChannelLogo);
        changeChannelInitializePlayer();

    }

    public void changeChannelInitializePlayer() {
        String path = "https://file-examples-com.github.io/uploads/2017/04/file_example_MP4_480_1_5MG.mp4";
        Uri uri = Uri.parse(path);
        Glide.with(mContext)
                .load(R.drawable.atv)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .signature(new ObjectKey(String.valueOf(System.currentTimeMillis() / 24 * 60 * 60 * 1000)))
                .into(imgChannelLogo);
        txtChannelName.setText("ATV");
        txtChannelDesc.setText("Selena");

        MediaItem mediaItem = new MediaItem.Builder()
                .setUri(uri)
                .setDrmUuid(C.WIDEVINE_UUID)
                .setDrmLicenseUri(drmLicenseUrl)
                .setDrmMultiSession(true)
                .build();
        if (player == null) {
            player = new SimpleExoPlayer.Builder(mContext).build();
            playerView.setPlayer(player);
            player.setPlayWhenReady(true);

        }
        player.setMediaItem(mediaItem);
        player.prepare();
    }
}