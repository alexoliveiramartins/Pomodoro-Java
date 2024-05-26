import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JPasswordField;

public class Ui{

	public Time timer;
	public Time restTimer;
	public JFrame frame = new JFrame();
	public JLabel timeLabel = new JLabel();
	public JLabel rest_timeLabel = new JLabel();
	
	private JTextField count;
	private final JButton pomodoroBtn = new JButton("Pomodoro");
	private final JButton restBtn = new JButton("Rest");
	
	public boolean pomodoro_mode = true;
	public boolean settings_mode = false;
	
	Color grey = new Color(222, 222, 222);
	Style style = new Style();
	Counter counter = new Counter();
	
	public JButton resetButton = new JButton("RESET");
	public JButton startButton = new JButton("START");
	JButton plusButton = new JButton("+");
	JButton subButton = new JButton("-");
	JButton settingsBtn = new JButton("SETTINGS");
	
	// Audio audio = new Audio();
	
	public Ui() {
		timer = new Time(timeLabel);
		restTimer = new Time(rest_timeLabel);
		
		timeLabel.setText(timer.minutes_string+":"+timer.seconds_string);
		timeLabel.setBounds(148, 124, 200, 100);
		timeLabel.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 45));
		timeLabel.setBorder(BorderFactory.createBevelBorder(1));
		timeLabel.setOpaque(true);
		timeLabel.setHorizontalAlignment(JTextField.CENTER);
		
		rest_timeLabel.setText(restTimer.minutes_string+":"+restTimer.seconds_string);
		rest_timeLabel.setBounds(148, 124, 200, 100);
		rest_timeLabel.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 45));
		rest_timeLabel.setBorder(BorderFactory.createBevelBorder(1));
		rest_timeLabel.setOpaque(true);
		rest_timeLabel.setHorizontalAlignment(JTextField.CENTER);
		
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.getContentPane().setLayout(null);
		
		count = new JTextField();
		count.setEditable(false);
		count.setFont(new Font("Lucida Console", Font.PLAIN, 18));
		count.setText(String.valueOf(counter.pomodoro_count));
		count.setBounds(230, 303, 39, 20);
		
		count.setColumns(10);
		count.setHorizontalAlignment(JTextField.CENTER);
		
		
		// START BUTTON
		startButton.setBounds(148,235,88,50);
		style.DefaultButtonStyle(startButton);
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(pomodoro_mode) {
					if(timer.started) {
						timer.stop();
						timer.started = false;
						startButton.setText("START");
					}
					else {
						timer.start();
						timer.started = true;
						startButton.setText("STOP");
					}
				}
				else {
					if(restTimer.started) {
						restTimer.stop();
						restTimer.started = false;
						startButton.setText("START");
					}
					else {
						restTimer.start();
						restTimer.started = true;
						startButton.setText("STOP");
					}
				}
			}
		});
		
		// RESET BUTTON
		resetButton.setBounds(260,235,88,50);
		style.DefaultButtonStyle(resetButton);
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(pomodoro_mode == true) {
					startButton.setText("START");
					timer.reset("pomodoro");
					timer.started = false;
				}
				else {
					startButton.setText("START");
					restTimer.reset("rest");
					restTimer.started = false;
				}
				
			}
		});
		
		// + BUTTON
		style.DefaultButtonStyle(plusButton);
		plusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				counter.pomodoro_count++;
				count.setText(String.valueOf(counter.pomodoro_count));
			}
		});
		plusButton.setBounds(277, 296, 44, 34);
		
		// - BUTTON
		style.DefaultButtonStyle(subButton);
		subButton.setBounds(176, 296, 44, 34);
		subButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(counter.pomodoro_count > 0) counter.pomodoro_count--;
				count.setText(String.valueOf(counter.pomodoro_count));
			}
		});
		
		// MODO POMODORO <---------
		pomodoroBtn.setBounds(148, 90, 89, 23);
		style.DefaultModeButtonStyle(pomodoroBtn);
		pomodoroBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pomodoro_mode = true;
				pomodoroBtn.setEnabled(false);
				restBtn.setEnabled(true);
				frame.getContentPane().remove(rest_timeLabel);
				frame.getContentPane().add(timeLabel);
				
				timer.reset("pomodoro");
				timeLabel.setText(timer.minutes_string+":"+timer.seconds_string);
				timer.start();
				startButton.setText("STOP");
				
				frame.revalidate();
		        frame.repaint();
			}
		});
		
		// MODO REST <---------
		restBtn.setBounds(259, 90, 89, 23);
		style.DefaultModeButtonStyle(restBtn);
		restBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pomodoro_mode = false;
				restBtn.setEnabled(false);
				pomodoroBtn.setEnabled(true);
				frame.getContentPane().remove(timeLabel);
				frame.getContentPane().add(rest_timeLabel);
				
				restTimer.reset("rest");
				rest_timeLabel.setText(restTimer.minutes_string+":"+restTimer.seconds_string);
				restTimer.start();
				startButton.setText("STOP");
				// System.out.println(restTimer.minutes_string + ":" + restTimer.seconds_string + "\n");
				
				frame.revalidate();
		        frame.repaint();
			}
		});
		
		// SETTINGS BUTTON
		style.SettingsButtonStyle(settingsBtn);
		settingsBtn.setText("SETTINGS");
		settingsBtn.setBounds(385, 11, 89, 23);
		settingsBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(settings_mode == false) {
					settingsPage();

				}
				else if(settings_mode == true) {
					pomodoroPage();
				}
			}
		});
		
		settingsPage();
	}
	
	public void pomodoroPage() {
		settings_mode = false;
		frame.getContentPane().removeAll();
		frame.getContentPane().add(startButton);
		frame.getContentPane().add(resetButton);
		frame.getContentPane().add(timeLabel);
		frame.getContentPane().add(count);
		frame.getContentPane().add(plusButton);
		frame.getContentPane().add(subButton);
		frame.getContentPane().add(restBtn);
		frame.getContentPane().add(pomodoroBtn);
		frame.getContentPane().add(settingsBtn);
		settingsBtn.setText("SETTINGS");
		
		
		frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
	}
	
	public void settingsPage() {
		settings_mode = true;
		frame.getContentPane().removeAll();
		frame.getContentPane().add(settingsBtn);

		
		settingsBtn.setText("BACK");
		
		frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
	}
}