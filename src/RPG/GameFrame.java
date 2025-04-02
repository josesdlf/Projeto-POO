package RPG;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame{
	private CardLayout cardLayout;
	private JPanel cardPanel;
	
	public GameFrame() {
		setTitle("RPG de POO");
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		
        cardPanel.add(new MenuPanel(this), "MENU");
        cardPanel.add(new BattlePanel(this), "BATTLE");
        
        add(cardPanel);
		
		showMenu();
	}
	
	public void showMenu() {
		cardLayout.show(cardPanel, "MENU");
	}
	
    public void startNewGame() {
        cardLayout.show(cardPanel, "BATTLE");
    }
}
