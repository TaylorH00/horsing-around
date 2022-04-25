package Resources;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.border.Border;

public final class Styles {
	public static final Color textColor = Color.WHITE;
	public static final Color borderColor = Color.WHITE;
	public static final Color backgroundColor = new Color(127, 85, 57);
	public static final Border border = BorderFactory.createLineBorder(Styles.textColor, 4);
	
	public static final JButton StyledJButton(String title) {
		JButton button = new JButton(title);
		button.setOpaque(true);
		button.setBorder(Styles.border);
		button.setBackground(Styles.backgroundColor);
		button.setFont(new Font("Verdana", Font.BOLD, 15));
		button.setForeground(Styles.textColor);
		return button;
	}
}
