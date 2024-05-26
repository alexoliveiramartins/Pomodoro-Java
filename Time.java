import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Time{
	
	JLabel timeLabel;
	JButton resetButton = new JButton("RESET");
	JButton startButton = new JButton("START");
	int default_minutes_pomodoro = 1;
	int default_minutes_pause = 5;
	int seconds = 0;
	int minutes = default_minutes_pomodoro;
	boolean started = false;
	String seconds_string = String.format("%02d", seconds);
	String minutes_string = String.format("%02d", minutes);
	
	Timer timer = new Timer(1000, new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			if(seconds <= 0 && minutes <= 0) {
				seconds = 0;
				minutes = 0;
				started = false;
				stop();
			}
			else if(seconds == 0) {
				seconds = 60;
				seconds--;
				minutes--;
			}
			else seconds--;
			seconds_string = String.format("%02d", seconds);
			minutes_string = String.format("%02d", minutes);
			
			timeLabel.setText(minutes_string + ":" + seconds_string);
		}
	});
	
	public Time(JLabel timeLabel) {
		this.timeLabel = timeLabel;
	}
	
	public void start() {
		timer.start();
	}
	
	public void stop() {
		timer.stop();
	}
	
	public void reset() {
		timer.stop();
		this.seconds = 0;
		this.minutes = default_minutes_pomodoro;
		seconds_string = String.format("%02d", seconds);
		minutes_string = String.format("%02d", minutes);
		timeLabel.setText(minutes_string + ":" + seconds_string);
	}
	
	public int getSeconds() {
		return seconds;
	}
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
	public int getMinutes() {
		return minutes;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	
}
