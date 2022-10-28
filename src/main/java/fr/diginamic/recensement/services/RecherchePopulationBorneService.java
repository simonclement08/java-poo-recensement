package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.exceptions.MenuServiceException;

/**
 * Recherche et affichage de toutes les villes d'un département dont la
 * population est comprise entre une valeur min et une valeur max renseignées
 * par l'utilisateur.
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationBorneService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner) throws MenuServiceException {
		List<Ville> villes = rec.getVilles();
		boolean choixIsNotValid = true;

		System.out.println("Quel est le code du département recherché ? ");
		String choix = scanner.nextLine();

		for (Ville ville : villes) {
			if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
				choixIsNotValid = false;
			}
		}

		if (choixIsNotValid) {
			throw new MenuServiceException("Veuillez saisir un numéro de département valide");
		}

		System.out.println("Choississez une population minimum (en milliers d'habitants): ");
		String saisieMin = scanner.nextLine();

		if (!NumberUtils.isDigits(saisieMin)) {
			throw new MenuServiceException("Veuillez saisir un nombre pour le minimum");
		}

		System.out.println("Choississez une population maximum (en milliers d'habitants): ");
		String saisieMax = scanner.nextLine();

		if (!NumberUtils.isDigits(saisieMax)) {
			throw new MenuServiceException("Veuillez saisir un nombre pour le maximum");
		}

		if (Integer.parseInt(saisieMin) < 0 || Integer.parseInt(saisieMax) < 0
				|| Integer.parseInt(saisieMax) - Integer.parseInt(saisieMin) < 0) {
			throw new MenuServiceException("Veuillez saisir un minimum et un maximum cohérent");
		}

		int min = Integer.parseInt(saisieMin) * 1000;
		int max = Integer.parseInt(saisieMax) * 1000;

		for (Ville ville : villes) {
			if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
				if (ville.getPopulation() >= min && ville.getPopulation() <= max) {
					System.out.println(ville);
				}
			}
		}

	}

}
