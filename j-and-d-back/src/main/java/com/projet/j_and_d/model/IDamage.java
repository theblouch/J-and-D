package com.projet.j_and_d.model;

public interface IDamage {
    default void applyDamageIfTouch(Creature sender, Creature receiver, int value) {
        receiver.setHp(receiver.getHp() - value);

        if (receiver.getHp() <= 0) {
            receiver.setHp(0);
            receiver.setAlive(false);

            //Si receiver NPC et sender character alors gain xp
            if (receiver instanceof NPC && sender instanceof Character){
                NPC npc = (NPC) receiver;
                Character character = (Character) sender;

                character.levelUp(npc.getXP());
            }
        }
    }

}
