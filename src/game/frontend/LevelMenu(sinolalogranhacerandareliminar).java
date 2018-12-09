/*package game.frontend;

import game.backend.level.Level1;
import game.backend.level.Level2;
import javafx.stage.Stage;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LevelMenu extends JFrame implements ActionListener {
    private JButton level1Button;
    private JButton level2Button;
    private JButton level3Button;
    private JButton exitButton;

    LevelMenu(Stage stage){
        setSize(300,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        level1Button=new JButton("Level 1");
        level1Button.addActionListener(this);
        add(level1Button);
        level2Button=new JButton("Level 2");
        level2Button.addActionListener(this);
        add(level2Button);
        level3Button=new JButton("Level 3");
        level3Button.addActionListener(this);
        add(level3Button);
        exitButton=new JButton("Exit");
            exitButton.addActionListener(this);
        add(exitButton);

        pack();

    }
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e){

        if(e.getSource()==level1Button){
            dispose();
            new LevelRunner(Level1.class).run();
        }
        if(e.getSource()==level2Button){
            dispose();
            new LevelRunner(Level2.class).run();
        }
        if(e.getSource()==level3Button){
            dispose();
            //  new LevelRunner(Level3.class).run();
        } else
            System.exit(0);

    }
}
*/