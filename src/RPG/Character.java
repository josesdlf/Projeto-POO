package RPG;

public abstract class Character {
	protected String name;
	protected int level;
	protected int hp;
	protected int maxHp;
	protected int attack;
	protected int defense;
	
	public Character(String name, int level, int hp, int attack, int defense) {
		this.name = name;
		this.level = level;
        this.hp = hp;
        this.maxHp = hp;
        this.attack = attack;
        this.defense = defense;
	}

	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}

	public int getHp() {
		return hp;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getAttack() {
		return attack;
	}

	public int getDefense() {
		return defense;
	}
	
	public void takeDamage(int damage) {
		hp -= damage;
		
		if (hp < 0) {
			hp = 0;
		}
	}
	
	public boolean isAlive() {
		return hp > 0;
	}
	
	public abstract void attack(Character target);
}
