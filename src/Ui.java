import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ui {

    public Time timer;
    public Time restTimer;

    public boolean pomodoro_mode = true;
    public boolean settings_mode = false;

    Color grey = new Color(222, 222, 222);
    Style style = new Style();
    Counter counter = new Counter();
    Files files = new Files();
    private Audio audio;

    public JFrame frame = new JFrame();
    public JLabel timeLabel = new JLabel();
    public JLabel rest_timeLabel = new JLabel();
    private JTextField count;

    JButton resetButton = new JButton("RESET");
    JButton startButton = new JButton("START");
    JButton plusButton = new JButton("+");
    JButton subButton = new JButton("-");
    JButton settingsBtn = new JButton("SETTINGS");
    JButton pomodoroBtn = new JButton("Pomodoro");
    JButton restBtn = new JButton("Rest");
    JSpinner spinnerRest = new JSpinner(new SpinnerNumberModel(50, 0, 120, 1));
    JSpinner spinnerPomodoro = new JSpinner(new SpinnerNumberModel(50, 0, 120, 1));
    JButton testSoundBtn = new JButton("test");
    JComboBox<String> comboBoxBgImage = new JComboBox<>();
    JComboBox<String> comboBoxAlarm = new JComboBox<>();
    JSlider sliderVolume = new JSlider();
    JLabel pomodoroText = new JLabel("Pomodoro (minutes)");
    JLabel restText = new JLabel("Rest (minutes)");
    JLabel alarmText = new JLabel("Alarm Sound");
    JLabel BgImageText = new JLabel("Background Image");
    JLabel alarmVolumeText = new JLabel("Alarm Volume");
    JSpinner spinnerVolume = new JSpinner();
    private final JLabel bgImageLabel = new JLabel();
    
    
    public Ui() {
    	audio = new Audio();
        timer = new Time(timeLabel);
        restTimer = new Time(rest_timeLabel);

        timeLabel.setText(timer.minutes_string + ":" + timer.seconds_string);
        timeLabel.setBounds(148, 144, 200, 100);
        timeLabel.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 45));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        rest_timeLabel.setText(restTimer.minutes_string + ":" + restTimer.seconds_string);
        rest_timeLabel.setBounds(148, 144, 200, 100);
        rest_timeLabel.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 45));
        rest_timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        rest_timeLabel.setOpaque(true);
        rest_timeLabel.setHorizontalAlignment(JTextField.CENTER);
        frame.setLocation(0, -49);

        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);

        count = new JTextField();
        count.setEnabled(true);
        count.setEditable(false);
        count.setFont(new Font("Lucida Console", Font.PLAIN, 18));
        count.setText(String.valueOf(counter.pomodoro_count));
        count.setBounds(230, 323, 39, 20);
        count.setColumns(10);
        count.setHorizontalAlignment(JTextField.CENTER);

        // START BUTTON
        startButton.setBounds(148, 255, 88, 50);
        style.DefaultButtonStyle(startButton);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pomodoro_mode) {
                    if (timer.started) {
                        timer.stop();
                        timer.started = false;
                        startButton.setText("START");
                    } else {
                        timer.start();
                        timer.started = true;
                        startButton.setText("STOP");
                    }
                } else {
                    if (restTimer.started) {
                        restTimer.stop();
                        restTimer.started = false;
                        startButton.setText("START");
                    } else {
                        restTimer.start();
                        restTimer.started = true;
                        startButton.setText("STOP");
                    }
                }
            }
        });

        // RESET BUTTON
        resetButton.setBounds(260, 255, 88, 50);
        style.DefaultButtonStyle(resetButton);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pomodoro_mode) {
                    startButton.setText("START");
                    timer.reset("pomodoro");
                    timer.started = false;
                } else {
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
        plusButton.setBounds(277, 316, 44, 34);

        // - BUTTON
        style.DefaultButtonStyle(subButton);
        subButton.setBounds(176, 316, 44, 34);
        subButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (counter.pomodoro_count > 0) counter.pomodoro_count--;
                count.setText(String.valueOf(counter.pomodoro_count));
            }
        });

        // MODO POMODORO <---------
        pomodoroBtn.setBounds(148, 110, 89, 23);
        style.DefaultModeButtonStyle(pomodoroBtn);
        pomodoroBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	pomodoro_mode = true;
            	frame.getContentPane().remove(rest_timeLabel);
            	frame.getContentPane().add(timeLabel);
            	
            	frame.getContentPane().add(bgImageLabel);
                pomodoroBtn.setEnabled(false);
                restBtn.setEnabled(true);
                restTimer.reset("rest");
                timer.reset("pomodoro");
                timeLabel.setText(timer.minutes_string + ":" + timer.seconds_string);               
                startButton.setText("STOP");
                
                timer.start();
                
                frame.revalidate();
                frame.repaint();
            }
        });

        // MODO REST <---------
        restBtn.setBounds(259, 110, 89, 23);
        style.DefaultModeButtonStyle(restBtn);
        restBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pomodoro_mode = false;
                restBtn.setEnabled(false);
                pomodoroBtn.setEnabled(true);   
                frame.getContentPane().remove(timeLabel);
                frame.getContentPane().add(rest_timeLabel);
                frame.getContentPane().add(bgImageLabel);

                restTimer.reset("rest");
                timer.reset("pomodoro");
                rest_timeLabel.setText(restTimer.minutes_string + ":" + restTimer.seconds_string);
                startButton.setText("STOP");

                restTimer.start();
                
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
                if (!settings_mode) {
                    settingsPage();
                } else {
                    pomodoroPage();
                }
            }
        });

        // POMODORO MINUTOS TEXTO
        pomodoroText.setBounds(39, 118, 113, 26);
        // REST MINUTOS TEXTO
        restText.setBounds(39, 167, 113, 26);
        // ALARME TIPO TEXTO
        alarmText.setBounds(39, 222, 113, 26);
        // BG IMAGE TEXTO
        BgImageText.setBounds(39, 326, 113, 26);
        // VOLUME ALARME TEXTO
        alarmVolumeText.setBounds(39, 274, 113, 26);              

        // SPINNER POMODORO MINUTOS
        spinnerPomodoro.setBounds(173, 116, 47, 26);
        spinnerPomodoro.setValue(timer.default_minutes_pomodoro);
        spinnerPomodoro.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				timer.default_minutes_pomodoro = (int) spinnerPomodoro.getValue();
				spinnerPomodoro.setValue(timer.default_minutes_pomodoro);
				timer.reset("pomodoro");
				frame.revalidate();
		        frame.repaint();
				// System.out.println(timer.default_minutes_pomodoro + "\n");
			}
        });

        // SPINNER REST MINUTOS
        spinnerRest.setBounds(173, 165, 47, 26);
        spinnerRest.setValue(restTimer.default_minutes_rest);
        spinnerRest.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				restTimer.default_minutes_rest = (int) spinnerRest.getValue();
				spinnerRest.setValue(restTimer.default_minutes_rest);
		        restTimer.reset("rest"); 
		        frame.revalidate();
		        frame.repaint();
				// System.out.println(restTimer.default_minutes_rest + "\n");
			}

        });
        
        // SOUNDS COMBOBOX
        files.addSoundsComboBox(comboBoxAlarm);
        comboBoxAlarm.setBounds(173, 220, 200, 26);
        comboBoxAlarm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // String selecao = comboBoxAlarm.getSelectedItem().toString();
                setAudio(comboBoxAlarm.getSelectedIndex());
            }
        });

        // COMBO BOX IMAGENS BG
        files.addImagesComboBox(comboBoxBgImage);
        comboBoxBgImage.setBounds(173, 326, 200, 26);
        comboBoxBgImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selecao = comboBoxBgImage.getSelectedItem().toString();
                ImageIcon bgIcon = new ImageIcon("src/BgImages/" + selecao);   	  	
            	bgImageLabel.setIcon(bgIcon);                
            }
        });
        
        // SLIDER VOLUME
        sliderVolume.setBounds(173, 272, 200, 26);
        sliderVolume.setValue(audio.volume);
        System.out.println((audio.volume) + " audiovolume");
        sliderVolume.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				int volume;
				volume = sliderVolume.getValue();
				setVolume(volume);
				spinnerVolume.setValue((int) volume);
			}
        });
        
        // SPINNER VOLUME
        spinnerVolume.setValue(audio.volume);
        spinnerVolume.setBounds(385, 274, 47, 23);
        spinnerVolume.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				int volume = (int) spinnerVolume.getValue();
				setVolume(volume);          
                sliderVolume.setValue(volume);
			}
        });
        
        // BOTAO TESTAR AUDIO
        testSoundBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		audio.Play();
        	}
        });
        testSoundBtn.setBounds(385, 220, 28, 26);

        pomodoroPage();
    }

    public void setVolume(int v) {
    	timer.audio.volume = v;
    	restTimer.audio.volume = v;
    	audio.volume = v;
    }
    
    public void setAudio(int a) {
    	 audio.selectedAudio = a;
         timer.audio.selectedAudio = a;
         restTimer.audio.selectedAudio = a;
    }
    
    public void setBg(String file) {
    	ImageIcon bgIcon = new ImageIcon("src/BgImages/" + file);   	  	
    	bgImageLabel.setIcon(bgIcon);   	
    }
    
    public void pomodoroPage() {    	
        settings_mode = false; 
        frame.getContentPane().removeAll();
        
        if(pomodoro_mode == true) {
        	frame.getContentPane().add(timeLabel);  	
        	pomodoroBtn.setEnabled(false);
        	restBtn.setEnabled(true);
        }
        else if(pomodoro_mode == false){
        	frame.getContentPane().add(rest_timeLabel);
        	pomodoroBtn.setEnabled(true);
        	restBtn.setEnabled(false);
        }
        // timer.reset("pomodoro");
        // restTimer.reset("rest"); 
        
        frame.getContentPane().add(startButton);
        frame.getContentPane().add(resetButton);               
        frame.getContentPane().add(count);
        frame.getContentPane().add(plusButton);
        frame.getContentPane().add(subButton);
        frame.getContentPane().add(restBtn);
        frame.getContentPane().add(pomodoroBtn);
        frame.getContentPane().add(settingsBtn);
        
        settingsBtn.setText("SETTINGS");
  
        frame.revalidate();
        frame.repaint();
        bgImageLabel.setLocation(0, 0);
        frame.getContentPane().add(bgImageLabel);
        frame.setVisible(true);
    }

    public void settingsPage() {
        settings_mode = true;
        frame.getContentPane().removeAll();
        frame.getContentPane().add(settingsBtn);
        settingsBtn.setText("BACK");        
        frame.getContentPane().add(comboBoxBgImage);
        frame.getContentPane().add(pomodoroText);
        frame.getContentPane().add(restText);
        frame.getContentPane().add(alarmText);
        frame.getContentPane().add(BgImageText);
        frame.getContentPane().add(alarmVolumeText);
        frame.getContentPane().add(sliderVolume);
        frame.getContentPane().add(spinnerPomodoro);
        frame.getContentPane().add(spinnerRest);
        frame.getContentPane().add(comboBoxAlarm);        
        frame.getContentPane().add(testSoundBtn);
        frame.getContentPane().add(spinnerVolume);        
        bgImageLabel.setBounds(0, 0, 484, 461);
        
        
        pomodoroBtn.setEnabled(true);
        restBtn.setEnabled(false);
        
        frame.getContentPane().add(bgImageLabel);

        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }
}
