package entity;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

public class MusicPlayer {
	String file;
	AudioClip aau;

	public void loop() {
		File f = new File(file);
		try {
			aau = Applet.newAudioClip(f.toURL());
			aau.loop();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void stopMusic() {
		aau.stop();
	}

	public MusicPlayer(String file) {
		this.file = file;
	}
}
