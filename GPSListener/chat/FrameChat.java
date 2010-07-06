package chat;

import java.awt.Dimension;

import javax.swing.JFrame;

public class FrameChat extends JFrame {
	private static final long serialVersionUID = -5777423832543978363L;
	PanelChat pc = new PanelChat();

	public FrameChat() {
		setPreferredSize(new Dimension(800, 600));
		getContentPane().add(pc);
		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new FrameChat();
	}
}
