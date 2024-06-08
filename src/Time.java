import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Time{
	
	Audio audio = new Audio();
	JLabel timeLabel, restLabel;
	JButton resetButton = new JButton("RESET");
	JButton startButton = new JButton("START");
	int default_minutes_pomodoro = 25;
	int default_minutes_rest = 5;
	int seconds = 0;
	int minutes = default_minutes_pomodoro;
	boolean started = false;
	String seconds_string = String.format("%02d", seconds);
	String minutes_string = String.format("%02d", minutes);
	
	Timer rest_timer = new Timer(1000, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(seconds <= 0 && minutes <= 0) {
				started = false;
				stop();
				return;
			}
			else if(seconds == 0) {
				seconds = 60;
				seconds--;
				minutes--;
			}
			else seconds--;
			seconds_string = String.format("%02d", seconds);
			minutes_string = String.format("%02d", minutes);
			
			restLabel.setText(minutes_string + ":" + seconds_string);
		}
	});
	
	Timer timer = new Timer(1000, new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			if(seconds <= 0 && minutes <= 0) {
				seconds = 0;
				minutes = 0;
				started = false;
				stop();
				playAlarm();
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
		started = true;
	}
	
	public void stop() {
		timer.stop();
		started = false;
	}
	
	public void playAlarm() {
		audio.Play();
	}
	
	public void reset(String pomodoro_rest) {
		timer.stop();
		this.seconds = 0;
		if(pomodoro_rest == "pomodoro") this.minutes = default_minutes_pomodoro;
		else if(pomodoro_rest == "rest") this.minutes = default_minutes_rest;
		
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
