package fr.diginamic.recensement.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.exceptions.MenuServiceException;
import fr.diginamic.recensement.services.comparators.EnsemblePopComparateur;

/**
 * Cas d'utilisation: affichage des N villes les plus peuplées d'une région
 * donnée
 * 
 * @author DIGINAMIC
 *
 */
public class RechercheVillesPlusPeupleesRegion extends MenuService {

	@Override
	public void traiter(Recensement recensement, Scanner scanner) throws MenuServiceException {

		System.out.println("Veuillez saisir un nom de région:");
		String nomRegion = scanner.nextLine();
		boolean nomRegionNotValid = true;

		List<Ville> villesRegions = new ArrayList<Ville>();

		List<Ville> villes = recensement.getVilles();
		for (Ville ville : villes) {
			if (ville.getNomRegion().toLowerCase().startsWith(nomRegion.toLowerCase())) {
				villesRegions.add(ville);
				nomRegionNotValid = false;
			}
		}

		if (nomRegionNotValid) {
			throw new MenuServiceException("Veuillez saisir un nom de région valide");
		}

		System.out.println("Veuillez saisir un nombre de villes:");
		String nbVillesStr = scanner.nextLine();
		
		if (!NumberUtils.isDigits(nbVillesStr)) {
			throw new MenuServiceException("Veuillez saisir un nombre");
		}
		if (Integer.parseInt(nbVillesStr) < 1) {
			throw new MenuServiceException("Veuillez saisir un nombre plus grand que 0");
		}
		
		int nbVilles = Integer.parseInt(nbVillesStr);

		Collections.sort(villesRegions, new EnsemblePopComparateur(false));
		System.out.println("Les " + nbVilles + " villes les plus peuplées de la région " + nomRegion + " sont :");
		if (villesRegions.size() > 0) {
			for (int i = 0; i < nbVilles; i++) {
				Ville ville = villesRegions.get(i);
				System.out.println(ville.getNom() + " : " + ville.getPopulation() + " habitants.");
			}
		}

	}

}
