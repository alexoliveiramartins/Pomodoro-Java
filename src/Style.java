import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSpinner;

public class Style {
	
	Color grey = new Color(222, 222, 222);
	
	public Style() {
		
	}
	
	public void DefaultButtonStyle(JButton button) {
		button.setBackground(grey);
		button.setFont(new Font("Lucida Console", Font.PLAIN, 16));
		button.setFocusable(false);
	}
	
	public void DefaultModeButtonStyle(JButton button) {
		button.setBackground(grey);
		button.setFont(new Font("Lucida Console", Font.PLAIN, 10));
		button.setFocusable(false);
	}
	
	public void SettingsButtonStyle(JButton button) {
		button.setBackground(grey);
		button.setFont(new Font("Lucida Console", Font.PLAIN, 10));
		button.setFocusable(false);
	}
	
	public void DefaultComboBoxStyle(JComboBox box) {
		Dimension maxSize = new Dimension(120, box.getPreferredSize().height);
		box.setMaximumSize(maxSize);
		box.setPreferredSize(maxSize);
	}
	
	public void DefaultSpinnerStyle(JSpinner spinner) {
		Dimension maxSize = new Dimension(50, spinner.getPreferredSize().height + 3);
		spinner.setMaximumSize(maxSize);
		spinner.setPreferredSize(maxSize);
	}
	
}
