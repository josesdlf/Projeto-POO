package RPG;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
	public MenuPanel(GameFrame frame) {
		setLayout(new GridBagLayout());
		setBackground(Color.BLACK);
		
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 50, 10, 50);
        
        JLabel title = new JLabel("RPG de POO", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        
        JButton newGameButton = new JButton("Novo Jogo");
        JButton loadGameButton = new JButton("Carregar Jogo");
        JButton exitButton = new JButton("Sair");
        
        add(title, gbc);
        add(newGameButton, gbc);
        add(loadGameButton, gbc);
        add(exitButton, gbc);
        
        newGameButton.addActionListener(e -> frame.startNewGame());
        exitButton.addActionListener(e -> System.exit(0));
	}
}
