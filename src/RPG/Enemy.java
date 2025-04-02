package RPG;

public class Enemy extends Character {
	private int experienceReward;
	
	public Enemy(String name, int level, int hp, int attack, int defense, int expReward) {
		super(name, level, hp, attack, defense);
		this.experienceReward = expReward;
	}
	
	@Override
	public void attack(Character target) {
		int damage = attack - target.getDefense() / 2;
		
		if (damage < 1) {
			damage = 1;
		}
		
		target.takeDamage(damage);
	}

	public int getExperienceReward() {
		return experienceReward;
	}
}
