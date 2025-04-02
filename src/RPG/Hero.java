package RPG;

import java.util.ArrayList;
import java.util.List;

public class Hero extends Character {
    private int experience;
    private int mp;
    private int maxMp;
    private List<Skill> skills;
    
    public Hero(String name) {
        super(name, 1, 100, 10, 5);
        this.experience = 0;
        this.maxMp = 50;
        this.mp = maxMp;
        this.skills = new ArrayList<>();
        
        skills.add(new Skill("Ataque Básico", Skill.Type.ATTACK, 1.0, 0, "Ataque normal"));
    }
    
    public void gainExperience(int exp) {
        experience += exp;
        
        while (experience >= experienceToNextLevel()) {
            experience -= experienceToNextLevel();
            levelUp();
        }
    }
    
    private int experienceToNextLevel() {
        return level * 100;
    }
    
    private void levelUp() {
        level++;
        maxHp += 20;
        hp = maxHp;
        maxMp += 10;
        mp = maxMp;
        attack += 3;
        defense += 2;
    }
    
    public boolean useSkill(Skill skill, Character target) {
        if (mp < skill.getMpCost()) {
            return false;
        }
        
        mp -= skill.getMpCost();
        
        switch (skill.getType()) {
            case ATTACK:
                int damage = (int)(attack * skill.getMultiplier()) - target.getDefense() / 2;
                if (damage < 1) damage = 1;
                target.takeDamage(damage);
                break;
                
            case HEAL:
                int healAmount = (int)(maxHp * skill.getMultiplier());
                hp = Math.min(hp + healAmount, maxHp);
                break;
        }
        
        return true;
    }
    
    public void learnNewSkill(Skill skill) {
        skills.add(skill);
    }
    
    public boolean shouldLearnNewSkill() {
        return level % 3 == 0;
    }
    
    @Override
    public void attack(Character target) {
        useSkill(skills.get(0), target);
    }
    
    public int getExperience() { 
    	return experience; 
    }
    
    public int getCurrentExperience() { 
    	return experience; 
    }
    
    public int getMp() { 
    	return mp; 
    }
    
    public int getMaxMp() { 
    	return maxMp; 
    }
    
    public List<Skill> getSkills() { 
    	return skills; 
    }
}