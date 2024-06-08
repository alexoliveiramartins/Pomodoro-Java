import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.sound.sampled.*;

public class Audio {
	
	int volume = 75;
	String path = "src/Sounds";
	File pasta = new File(path);
	ArrayList<String> sounds = new ArrayList<>();
	int selectedAudio = 0;
	
	
	public Audio() {
		if (pasta.isDirectory()) {
            String[] soundFiles = pasta.list();
            if (soundFiles != null) {
                for (String soundFile : soundFiles) {
                    sounds.add(path + "/" + soundFile);
                    System.out.println(soundFile);
                }
            } else {
                System.out.println("A pasta está vazia ou não pôde ser lida.");
            }
        } else {
            System.out.println("O caminho especificado não é uma pasta.");
        }
	}
		
	public void Play() {
        if (selectedAudio < 0 || selectedAudio >= sounds.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        try {
            File audioFile = new File(sounds.get(selectedAudio));
            if (!audioFile.exists()) {
                System.out.println("Arquivo de áudio não encontrado.");
                return;
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            setVolume(clip, volume);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
	
	private void setVolume(Clip clip, int volume) {
        FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float min = volumeControl.getMinimum();
        float max = volumeControl.getMaximum();
        
        // Convert volume percentage (0-100) to the FloatControl range
        float gain = min + ((max - min) * (volume / 100.0f));
        volumeControl.setValue(gain);
    }
}
