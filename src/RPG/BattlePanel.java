package RPG;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class BattlePanel extends JPanel {
    private GameFrame frame;
    private Hero hero;
    private Enemy enemy;
    private JTextArea log;
    private JLabel heroStatus;
    private JLabel enemyStatus;
    
    public BattlePanel(GameFrame frame) {
        this.frame = frame;
        this.hero = new Hero("Herói");
        this.enemy = createNewEnemy();
        
        setupUI();
    }
    
    private void setupUI() {
        setLayout(new BorderLayout());
        
        JPanel statusPanel = new JPanel(new GridLayout(1, 2));
        heroStatus = new JLabel(getHeroStatus());
        enemyStatus = new JLabel(getEnemyStatus());
        statusPanel.add(heroStatus);
        statusPanel.add(enemyStatus);
        
        log = new JTextArea();
        log.setEditable(false);
        JScrollPane scroll = new JScrollPane(log);
        
        JPanel buttonsPanel = new JPanel(new GridLayout(2, 2));
        String[] actions = {"Atacar", "Habilidades", "Itens", "Fugir"};
        
        for (String action : actions) {
            JButton btn = new JButton(action);
            btn.addActionListener(this::handleAction);
            buttonsPanel.add(btn);
        }
        
        add(statusPanel, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
        
        log.append("Um " + enemy.getName() + " apareceu!\n");
    }
    
    private void handleAction(ActionEvent e) {
        String action = ((JButton)e.getSource()).getText();
        
        switch (action) {
            case "Atacar":
                hero.attack(enemy);
                log.append(hero.getName() + " atacou!\n");
                break;
                
            case "Habilidades":
                showSkills();
                return;
                
            case "Itens":
            	log.append("Sistema de itens não implementado ainda!\n");
            	return;
                
            case "Fugir":
                frame.showMenu();
                return;
        }
        
        processBattle();
    }
    
    private void showSkills() {
        List<Skill> skills = hero.getSkills();
        
        if (skills.size() <= 1) {
            log.append("Nenhuma habilidade disponível.\n");
            return;
        }
        
        JDialog dialog = new JDialog();
        dialog.setLayout(new BorderLayout());
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        for (int i = 1; i < skills.size(); i++) {
            Skill skill = skills.get(i);
            JButton btn = new JButton(skill.getName() + " (MP: " + skill.getMpCost() + ")");
            
            btn.addActionListener(e -> {
                if (hero.useSkill(skill, enemy)) {
                    log.append(hero.getName() + " usou " + skill.getName() + "!\n");
                    dialog.dispose();
                    processBattle();
                } 
                else {
                    log.append("MP insuficiente!\n");
                }
            });
            
            panel.add(btn);
        }
        
        dialog.add(new JScrollPane(panel), BorderLayout.CENTER);
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    private void processBattle() {
        if (!enemy.isAlive()) {
            int exp = enemy.getExperienceReward();
            log.append("Inimigo derrotado! +" + exp + " XP\n");
            
            int oldLevel = hero.getLevel();
            hero.gainExperience(exp);
            
            if (hero.getLevel() > oldLevel) {
                log.append(hero.getName() + " subiu para o nível " + hero.getLevel() + "!\n");
                
                if (hero.shouldLearnNewSkill()) {
                    showNewSkillSelection();
                }
            }
            
            enemy = createNewEnemy();
            log.append("Um " + enemy.getName() + " apareceu!\n");
        } 
        else {
            enemy.attack(hero);
            log.append(enemy.getName() + " atacou!\n");
            
            if (!hero.isAlive()) {
                log.append("Game Over!\n");
                frame.showMenu();
            }
        }
        
        updateStatus();
    }
    
    private void showNewSkillSelection() {
        List<Skill> options = SkillManager.getRandomSkills(2);
        
        String[] choices = {
            options.get(0).getName() + ": " + options.get(0).getDescription(),
            options.get(1).getName() + ": " + options.get(1).getDescription()
        };
        
        int choice = JOptionPane.showOptionDialog(
            this,
            "Escolha uma nova habilidade:",
            "Nova Habilidade",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            choices,
            choices[0]
        );
        
        if (choice >= 0) {
            hero.learnNewSkill(options.get(choice));
            log.append("Aprendeu: " + options.get(choice).getName() + "!\n");
        }
    }
    
    private Enemy createNewEnemy() {
        String[] names = {"Goblin", "Esqueleto", "Orc", "Lobisomem"};
        String name = names[(int)(Math.random() * names.length)];
        int level = hero.getLevel();
        
        return new Enemy(
            name,
            level,
            40 + (level * 10), //Vida
            6 + (level * 2), //Dano
            2 + level, //Defesa
            150 + (level * 5) //XP ganho. O valor aqui pode ser alterado, o valor padrão é 50, mas coloquei 150 por razões de teste.
        );
    }
    
    private void updateStatus() {
        heroStatus.setText(getHeroStatus());
        enemyStatus.setText(getEnemyStatus());
    }
    
    private String getHeroStatus() {
        return hero.getName() + " | Nv: " + hero.getLevel() + 
               " | HP: " + hero.getHp() + "/" + hero.getMaxHp() + 
               " | MP: " + hero.getMp() + "/" + hero.getMaxMp();
    }

    private String getEnemyStatus() {
        return enemy.getName() + " | Nv: " + enemy.getLevel() + 
               " | HP: " + enemy.getHp() + "/" + enemy.getMaxHp();
    }
}
