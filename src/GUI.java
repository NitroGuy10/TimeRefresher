import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GUI
{
	JTextArea hourArea;
	JTextArea minArea;
	JTextArea secArea;
	JLabel conclusion;
	JLabel completeLabel;
	
	public GUI ()
	{
		JFrame frame = new JFrame("NitroGuy's Time Refresher");
		JPanel panel = new JPanel();
		
		JLabel label = new JLabel("This program will press F5 (browser refresh) at whatever time you tell it to.");
		panel.add(label);
		JLabel label2 = new JLabel("It will press F5 once and then the program will stop.");
		panel.add(label2);
		JLabel label3 = new JLabel("Pressing ` (grave/tilde key) at any time from anywhere will terminate the program immediately (emergency stop).");
		panel.add(label3);
		
		hourArea = new JTextArea(1, 3);
		hourArea.setText("12");
		panel.add(hourArea);
		
		panel.add(new JLabel(":"));
		
		minArea = new JTextArea(1, 3);
		minArea.setText("30");
		panel.add(minArea);
		
		panel.add(new JLabel(":"));
		
		secArea = new JTextArea(1, 3);
		secArea.setText("00");
		panel.add(secArea);
		
		
		conclusion = new JLabel("...uhh if you can read this, i think the program crashed");
		panel.add(conclusion);
		
		JLabel finalThoughts1 = new JLabel("Finally, make sure you have \"focused\" on your browser window (i.e. click it and then don't click any other window.)");
		panel.add(finalThoughts1);
		JLabel finalThoughts2 = new JLabel("And make sure your computer will not fall asleep.");
		panel.add(finalThoughts2);
		
		completeLabel = new JLabel("---------------- We'll let you know when the key press is complete. ----------------");
		panel.add(completeLabel);
		
		frame.add(panel);
		frame.setSize(680, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void end ()
	{
		hourArea.setEnabled(false);
		minArea.setEnabled(false);
		secArea.setEnabled(false);
		
		completeLabel.setText("-=-=-=-=-=-=-=-=- F5 was pressed at the designated time. Have a nice day! <3 -=-=-=-=-=-=-=-=-");
	}

}
