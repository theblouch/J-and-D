package com.projet.j_and_d.context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Singleton {

	private static Singleton instance = null;

	/*
	 * bonusDice représente la liste de dés bonus qui vont être utilisés pour le
	 * lancer (et leur nombre)
	 * ATTENTION : CES DES SONT EN BONUS DU LANCER DE DÉ CLASSIQUE
	 * dans l'ordre [d4, d6, d8, d10, d12]
	 */

	public Integer[] bonusDice;
	private Random rd;

	private Singleton() {
		bonusDice = new Integer[5];
		rd = new Random();
	}

	public static Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}

	public Integer[] getBonusDice() {
		return bonusDice;
	}

	public void setBonusDice(Integer[] bonusDice) {
		this.bonusDice = bonusDice;
	}

	/*
	 * fonction getSpecificDice :
	 * - l'entier value représente un dé bonus (donc doit être une valeur paire
	 * comprise entre 4 et 12)
	 */
	public Integer getSpecificDice(Integer value) {
		return this.bonusDice[(value - 4) / 2];
	}

	/*
	 * fonction setSpecificDice :
	 * - l'entier value représente un dé bonus (donc doit être une valeur paire
	 * comprise entre 4 et 12)
	 * - l'entier dice représente le nombre de dé de la valeur [value] qui seront
	 * lancés (donc un entier naturel)
	 */
	public void setSpecificDice(Integer value, Integer dice) {
		this.bonusDice[(value - 4) / 2] = dice;
	}

	/*
	 * fonction diceThrow :
	 * Le lancer de dés 20 avec les avantages, prend en compte les dés bonus
	 * et le bonus total (proficiency), puis reset les dés bonus
	 * L'integer Advantage permet de savoir si on bénéficie d'un avantage
	 * pour le lancer de dés (0 si lancer classique,
	 * 1 si avantage, 2 double avantage, -1 si désavantage...)
	 */

	public boolean diceThrow(Integer successThreshold, Integer advantage, Integer proficiency) {

		int sum = 0;
		int len = bonusDice.length;

		// Gestion des dés bonus
		int sumBonusDice = 0;
		for (int i = 0; i < len; i++) {
			// calcul du max des lancers de dés bonus
			int range = 2 * i + 4;
			for (int j = 0; j < bonusDice[i]; j++) {
				// On lance le dé bonus
				int resultBonusDice = rd.nextInt(range) + 1;
				// System.out.println("Résultat du jet bonus d"+ range + " : " +
				// resultBonusDice);
				sumBonusDice += resultBonusDice;
			}
		}

		sum += sumBonusDice;
		sum += proficiency;

		// Gestion du d20 et des avantages
		ArrayList<Integer> resD20 = new ArrayList<Integer>();
		for (int i = 0; i < Math.abs(advantage) + 1; i++) {
			resD20.add(rd.nextInt(20) + 1);
		}

		if (advantage < 0) {
			sum = Collections.min(resD20);
		} else {
			sum = Collections.max(resD20);
		}
		return sum >= successThreshold;
	}

	public void bonusDiceReset() {
		int len = bonusDice.length;
		for (int i = 0; i < len; i++) {
			bonusDice[i] = 0;
		}
	}
}
