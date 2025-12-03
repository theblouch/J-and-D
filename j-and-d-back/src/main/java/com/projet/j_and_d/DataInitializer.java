package com.projet.j_and_d;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.projet.j_and_d.model.Druid;
import com.projet.j_and_d.model.Item;
import com.projet.j_and_d.model.Mage;
import com.projet.j_and_d.model.Rogue;
import com.projet.j_and_d.model.Warrior;
import com.projet.j_and_d.repo.ItemRepository;
import com.projet.j_and_d.repo.RoleRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ItemRepository itemRepository;

    DataInitializer() {
    }

    @Override
    public void run(String... args) throws Exception {

        if (itemRepository.findAll().toArray().length == 0) {
            createItems();
        }
        if (roleRepository.findAll().toArray().length == 0) {
            createRoles();
        }
    }

    private void createItems() {

        // Items mage
        Item staff = new Item("Baton Magique", "Un bâton de mage", true, new int[] { 0, 2 }, 0);
        Item robe = new Item("Robe", "Une robe de mage", false, new int[] {}, 1);

        itemRepository.save(staff);
        itemRepository.save(robe);

        // Item Druide
        Item baton = new Item("Baton", "Un baton de druide", true, new int[] { 2 }, 0);
        Item ronces = new Item("Armure de ronces", "Une armure de druide", false, new int[] {}, 2);

        itemRepository.save(baton);
        itemRepository.save(ronces);
        // Items Rogue
        Item dagues = new Item("Dagues", "Des dagues de voleur", false, new int[] { 1, 1 }, 0);
        Item chemise = new Item("Chemise", "Une chemise de voleur", false, new int[] {}, 1);

        itemRepository.save(dagues);
        itemRepository.save(chemise);

        // Items Warrior
        Item hache = new Item("Hache", "Une Hache de guerrier", true, new int[] { 0, 1 }, 0);
        Item mailles = new Item("Cote de mailles", "Une armure de vrai guerrier", false, new int[] {}, 3);

        itemRepository.save(hache);
        itemRepository.save(mailles);

    }

    private void createRoles() {
        Mage mage = new Mage();
        mage.setWeapon(itemRepository.findByName("Baton Magique").get());
        mage.setArmor(itemRepository.findByName("Robe").get());
        roleRepository.save(mage); // Cascade → Items enregistrés automatiquement

        Warrior warrior = new Warrior();
        warrior.setWeapon(itemRepository.findByName("Hache").get());
        warrior.setArmor(itemRepository.findByName("Cote de mailles").get());
        roleRepository.save(warrior);

        Druid druid = new Druid();
        druid.setWeapon(itemRepository.findByName("Baton").get());
        druid.setArmor(itemRepository.findByName("Armure de ronces").get());
        roleRepository.save(druid);

        Rogue rogue = new Rogue();
        rogue.setWeapon(itemRepository.findByName("Dagues").get());
        rogue.setArmor(itemRepository.findByName("Chemise").get());
        roleRepository.save(rogue);
    }
}