import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.sound.sampled.*;

public class Audio {
	
	float volume = 0.8f;
	String path = "Sounds/Danger.wav";
	ArrayList<String> sounds;
	int selectedAudio = 0;
	
	
	public Audio() {
		sounds = new ArrayList<String>();
		sounds.add("Sounds/Danger.wav");
		sounds.add("Sounds/DingDong.wav");
	}
	
	public void Play(int selectedIndex) {
        try (InputStream audioSrc = getClass().getResourceAsStream(sounds.get(selectedIndex));
             InputStream bufferedIn = new BufferedInputStream(audioSrc)) {
            if (bufferedIn == null) {
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
