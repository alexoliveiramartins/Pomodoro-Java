import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.*;

public class Audio {
	
	float volume = 0.8f;
	String path = "Sounds/Danger.wav";
	
	public Audio() {
		
	}
	
	public void Play() {
        try (InputStream audioSrc = getClass().getResourceAsStream(path);
             InputStream bufferedIn = new BufferedInputStream(audioSrc)) {
            if (bufferedIn == null) {
                System.err.println("Could not find the audio file: " + path);
                return;
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            setVolume(clip, volume);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
	
	private void setVolume(Clip clip, float volume) {
        FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float min = volumeControl.getMinimum();
        float max = volumeControl.getMaximum();
        float range = max - min;
        float gain = min + (range * volume);
        volumeControl.setValue(gain);
    }
}
