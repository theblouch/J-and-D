package com.projet.j_and_d;

import com.projet.j_and_d.model.Druid;
import com.projet.j_and_d.model.Mage;
import com.projet.j_and_d.model.Monster;
import com.projet.j_and_d.model.Rogue;
import com.projet.j_and_d.model.Role;
import com.projet.j_and_d.model.Stats;
import com.projet.j_and_d.model.Warrior;

public class TestCreationPerso {
    public static void main(String[] args) {

        System.out.println("=== TEST DE CRÉATION DE PERSONNAGES ===\n");

        Role warrior = new Warrior();
        Role rogue = new Rogue();
        Role druid = new Druid();
        Role mage = new Mage();
        Role monster = new Monster();

        afficherRole(warrior);
        afficherRole(rogue);
        afficherRole(druid);
        afficherRole(mage);
        afficherRole(monster);
    }

    private static void afficherRole(Role role) {
        System.out.println("----- " + role.getName() + " -----");
        System.out.println("HP : " + role.getBaseHp());
        System.out.println("MP : " + role.getBaseMp());
        System.out.println("MS : " + role.getBaseMs());
        System.out.println("Armor : " + role.getBaseArmor());
        System.out.println("Initiative : " + role.getBaseIni());

        Stats s = role.getBaseStats();
        System.out.println("Stats :");
        System.out.println("  Wisdom       = " + s.getWisdom());
        System.out.println("  Strength     = " + s.getStrength());
        System.out.println("  Constitution = " + s.getConstitution());
        System.out.println("  Intelligence = " + s.getIntelligence());
        System.out.println("  Dexterity    = " + s.getDexterity());
        System.out.println("  Charisma     = " + s.getCharisma());

        System.out.println();
    }
}
