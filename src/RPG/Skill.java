package RPG;

public class Skill {
	public enum Type {
		ATTACK, DEFENSE, HEAL, BUFF, DEBUFF
	}
	
	private String name;
	private Type type;
	private double multiplier;
	private int mpCost;
	private String description;
	
	public Skill(String name, Type type, double multiplier, int mpCost, String description) {
        this.name = name;
        this.type = type;
        this.multiplier = multiplier;
        this.mpCost = mpCost;
        this.description = description;
	}

	public String getName() {
		return name;
	}

	public Type getType() {
		return type;
	}

	public double getMultiplier() {
		return multiplier;
	}

	public int getMpCost() {
		return mpCost;
	}

	public String getDescription() {
		return description;
	}
	
	@Override
	public String toString() {
		return name + " (MP: " + mpCost + ") - " + description;
	}
}
