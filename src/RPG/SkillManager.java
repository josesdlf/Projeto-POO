package RPG;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SkillManager {
	private static final List<Skill> ALL_SKILLS = new ArrayList<>();
	private static final Random RANDOM = new Random();
	
	static {
		initializeAllSkills();
	}
	
	private static void initializeAllSkills() {
        ALL_SKILLS.add(new Skill("Corte Poderoso", Skill.Type.ATTACK, 1.5, 10, 
            "Um ataque poderoso que causa 150% de dano"));
        ALL_SKILLS.add(new Skill("Golpe Duplo", Skill.Type.ATTACK, 0.8, 15, 
            "Dois ataques rápidos que causam 80% de dano cada"));
        ALL_SKILLS.add(new Skill("Investida", Skill.Type.ATTACK, 1.8, 20, 
            "Um ataque devastador que causa 180% de dano"));
        
        ALL_SKILLS.add(new Skill("Defesa Total", Skill.Type.DEFENSE, 0.0, 15, 
            "Aumenta sua defesa em 50% por 3 turnos"));
        ALL_SKILLS.add(new Skill("Contra-ataque", Skill.Type.DEFENSE, 1.0, 10, 
            "Bloqueia um ataque e retorna 100% do dano"));
        
        ALL_SKILLS.add(new Skill("Cura Pequena", Skill.Type.HEAL, 0.3, 15, 
            "Recupera 30% da vida máxima"));
        ALL_SKILLS.add(new Skill("Cura Total", Skill.Type.HEAL, 1.0, 30, 
            "Recupera toda a vida"));
        
        ALL_SKILLS.add(new Skill("Fúria", Skill.Type.BUFF, 0.0, 20, 
            "Aumenta o ataque em 50% por 3 turnos"));
        
        ALL_SKILLS.add(new Skill("Fraqueza", Skill.Type.DEBUFF, 0.0, 15, 
            "Reduz o ataque do inimigo em 30% por 3 turnos"));
        ALL_SKILLS.add(new Skill("Desarmar", Skill.Type.DEBUFF, 0.0, 10, 
            "Reduz a defesa do inimigo em 40% por 2 turnos"));
	}
	
	public static List<Skill> getRandomSkills(int count) {
		if (count > ALL_SKILLS.size()) {
			throw new IllegalArgumentException("Não há skills suficientes");
		}
		
		List<Skill> shuffled = new ArrayList<>(ALL_SKILLS);
		Collections.shuffle(shuffled);
		return shuffled.subList(0, count);
	}
	
	public static Skill getRandomSkill() {
		return ALL_SKILLS.get(RANDOM.nextInt(ALL_SKILLS.size()));
	}
}
