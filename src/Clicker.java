import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

// Press a key at a certain time
//
// Press grave/tilde to exit

public class Clicker implements NativeKeyListener
{
	static boolean abort;

	public static void main (String[] args) throws AWTException, InterruptedException, IOException
	{
		Robot robot = new Robot();
		abort = false;

		try {
			GlobalScreen.registerNativeHook();

			Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
			logger.setLevel(Level.WARNING);

			logger.setUseParentHandlers(false);
		}
		catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}
		GlobalScreen.addNativeKeyListener(new Clicker());

		GUI gui = new GUI();

		while (true)
		{
			Thread.sleep(300);
			
			String hourStr = gui.hourArea.getText();
			String minStr = gui.minArea.getText();
			String secStr = gui.secArea.getText();
			if ((Pattern.matches("[0-9]|[0-9][0-9]", hourStr) && 
					Pattern.matches("[0-9]|[0-9][0-9]", minStr) &&
					Pattern.matches("[0-9]|[0-9][0-9]", secStr)))
			{
				int pressHour = Integer.parseInt(hourStr);
				int pressMin = Integer.parseInt(minStr);
				int pressSec = Integer.parseInt(secStr);

				if (pressHour > 23 || pressMin > 59 || pressSec > 59)
				{
					gui.conclusion.setText("Time input invalid. The program will NOT press F5 at any time.");
				}
				else
				{
					LocalDateTime time = LocalDateTime.now();
					if (time.getHour() == pressHour && time.getMinute() == pressMin && time.getSecond() == pressSec)
					{
						robot.keyPress(KeyEvent.VK_F5);
						break;
					}

					String conclusion = "As per your instructions, this program will press F5 at ";
					if (pressHour == 0)
					{
						conclusion += "12:";
					}
					else if (pressHour > 12)
					{
						conclusion += (pressHour - 12) + ":";
					}
					else
					{
						conclusion += pressHour + ":";
					}
					
					if (pressMin < 10)
					{
						conclusion += "0" + pressMin + ":";
					}
					else
					{
						conclusion += pressMin + ":";
					}

					if (pressSec < 10)
					{
						conclusion += "0" + pressSec + "";
					}
					else
					{
						conclusion += pressSec + "";
					}

					if (pressHour > 11)
					{
						conclusion += "pm. Sound good?";
					}
					else
					{
						conclusion += "am. Sound good?";
					}
					gui.conclusion.setText(conclusion);
				}
			}
			else
			{
				gui.conclusion.setText("Time input invalid. The program will NOT press F5 at any time.");
			}
		}

		try
		{
			GlobalScreen.unregisterNativeHook();
		}
		catch (NativeHookException e)
		{
			e.printStackTrace();
		}
		
		gui.end();

		System.out.println("Done!");

	}

	@Override
	public void nativeKeyPressed (NativeKeyEvent arg0)
	{
		if (arg0.getKeyCode() == NativeKeyEvent.VC_BACKQUOTE)
		{
			System.out.println("ABORT ABORT! :)");
			abort = true;
			try
			{
				GlobalScreen.unregisterNativeHook();
			}
			catch (NativeHookException e)
			{
				e.printStackTrace();
			}
			System.exit(0);
		}
	}

	@Override
	public void nativeKeyReleased (NativeKeyEvent arg0)
	{
		if (arg0.getKeyCode() == NativeKeyEvent.VC_BACKQUOTE)
		{
			System.out.println("ABORT ABORT! :)");
			abort = true;
			try
			{
				GlobalScreen.unregisterNativeHook();
			}
			catch (NativeHookException e)
			{
				e.printStackTrace();
			}
			System.exit(0);
		}

	}

	@Override
	public void nativeKeyTyped (NativeKeyEvent arg0)
	{
		if (arg0.getKeyCode() == NativeKeyEvent.VC_BACKQUOTE)
		{
			System.out.println("ABORT ABORT! :)");
			abort = true;
			try
			{
				GlobalScreen.unregisterNativeHook();
			}
			catch (NativeHookException e)
			{
				e.printStackTrace();
			}
			System.exit(0);
		}

	}

}