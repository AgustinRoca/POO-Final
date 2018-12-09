package game.frontend;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class NextLevelWindow {

	JFrame frame = new JFrame("You have won this level!");
	JLabel label1 = new JLabel("Do u want to keep playing?", SwingConstants.CENTER);
	JButton b1 = new JButton("YES!");
	JButton b2 = new JButton("No :(");
	
	public void addButtons() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setLayout(null);
		frame.add(label1);
		label1.setSize(300, 200);
		frame.add(b1);
		b1.setBounds(100,150,100,60);
        b1.setVisible(true);
        b1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Cambiando nivel");
            }
        });
		frame.add(b2);
		b2.setBounds(200,150,100,60);
        b2.setVisible(true);
        b2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(1);;
            }
        });
        frame.setVisible(true);
	}
		
}
