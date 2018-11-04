package com.gunmachan.game;

import android.os.Bundle;


import android.media.MediaPlayer;
import android.media.MediaRecorder;

import android.os.Environment;
import android.view.View;

import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.Random;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.support.v4.app.ActivityCompat;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import asu.gunma.GunmaChan;

public class AndroidLauncher extends AndroidApplication {

	Button buttonStart, buttonStop;
	String AudioSavePathInDevice = null;
	MediaRecorder mediaRecorder;
	public static final int RequestPermissionCode = 1;
	MediaPlayer mediaPlayer;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
			initialize(new GunmaChan(), config);
		}
}
