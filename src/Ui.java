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
import java.awt.Component;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ui{

	public Time timer;
	public Time restTimer;
	Counter counter = new Counter();
	public JFrame frame = new JFrame();
	public JLabel timeLabel = new JLabel();
	public JLabel rest_timeLabel = new JLabel();
	public JButton resetButton = new JButton("RESET");
	public JButton startButton = new JButton("START");
	private JTextField count;
	private final JButton pomodoroBtn = new JButton("Pomodoro");
	private final JButton restBtn = new JButton("Rest");
	public boolean pomodoro_mode = true;
	
	// Audio audio = new Audio();
	
	public Ui() {
		timer = new Time(timeLabel);
		restTimer = new Time(rest_timeLabel);
		
		timeLabel.setText(timer.minutes_string+":"+timer.seconds_string);
		timeLabel.setBounds(148, 124, 200, 100);
		timeLabel.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 35));
		timeLabel.setBorder(BorderFactory.createBevelBorder(1));
		timeLabel.setOpaque(true);
		timeLabel.setHorizontalAlignment(JTextField.CENTER);
		
		rest_timeLabel.setText(restTimer.minutes_string+":"+restTimer.seconds_string);
		rest_timeLabel.setBounds(148, 124, 200, 100);
		rest_timeLabel.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 35));
		rest_timeLabel.setBorder(BorderFactory.createBevelBorder(1));
		rest_timeLabel.setOpaque(true);
		rest_timeLabel.setHorizontalAlignment(JTextField.CENTER);
		
		// START BUTTON
		startButton.setBounds(148,235,88,50);
		startButton.setFont(new Font("Lucida Console", Font.PLAIN, 16));
		startButton.setFocusable(true);
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
		resetButton.setFont(new Font("Lucida Console", Font.PLAIN, 16));
		resetButton.setFocusable(true);
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
		
		JButton plusButton = new JButton("+");
		plusButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		plusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				counter.pomodoro_count++;
				count.setText(String.valueOf(counter.pomodoro_count));
			}
		});
		plusButton.setBounds(277, 296, 44, 34);
		
		
		JButton subButton = new JButton("-");
		subButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		subButton.setBounds(176, 296, 44, 34);
		subButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(counter.pomodoro_count > 0) counter.pomodoro_count--;
				count.setText(String.valueOf(counter.pomodoro_count));
			}
		});
		
		frame.getContentPane().add(startButton);
		frame.getContentPane().add(resetButton);
		frame.getContentPane().add(timeLabel);
		frame.getContentPane().add(count);
		frame.getContentPane().add(plusButton);
		frame.getContentPane().add(subButton);
		frame.getContentPane().add(restBtn);
		frame.getContentPane().add(pomodoroBtn);
		
		// MODO POMODORO <---------
		pomodoroBtn.setBounds(148, 90, 89, 23);
		pomodoroBtn.setEnabled(false);
		pomodoroBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pomodoro_mode = true;
				pomodoroBtn.setEnabled(false);
				restBtn.setEnabled(true);
				frame.getContentPane().remove(rest_timeLabel);
				frame.getContentPane().add(timeLabel);
				startButton.setText("START");
				
				timer.reset("pomodoro");
				timeLabel.setText(timer.minutes_string+":"+timer.seconds_string);
				
				frame.revalidate();
		        frame.repaint();
			}
		});
		
		// MODO REST <---------
		restBtn.setBounds(259, 90, 89, 23);
		restBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pomodoro_mode = false;
				restBtn.setEnabled(false);
				pomodoroBtn.setEnabled(true);
				frame.getContentPane().remove(timeLabel);
				frame.getContentPane().add(rest_timeLabel);
				startButton.setText("START");
				
				restTimer.reset("rest");
				rest_timeLabel.setText(restTimer.minutes_string+":"+restTimer.seconds_string);
				
				frame.revalidate();
		        frame.repaint();
			}
		});
		
		frame.setVisible(true);
	}
}
