package game.frontend;

import javax.swing.*;

public class NextLevelWindow {

	private JFrame frame = new JFrame("You have won this level!");
	private JLabel label1 = new JLabel("Do u want to keep playing?", SwingConstants.CENTER);
	private JButton yesButton = new JButton("YES!");
	private JButton noButton = new JButton("No :(");
	
	public void addButtons() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setLayout(null);
		frame.add(label1);
		label1.setSize(300, 200);
		frame.add(yesButton);
		yesButton.setBounds(100,150,100,60);
        yesButton.setVisible(true);
        yesButton.addActionListener(e -> System.out.println("Cambiando nivel"));
		frame.add(noButton);
		noButton.setBounds(200,150,100,60);
        noButton.setVisible(true);
        noButton.addActionListener(e -> System.exit(1));
        frame.setVisible(true);
	}
		
}
