package no.hvl.dat102.tabell;

import no.hvl.dat102.adt.OrdnetListeADT;
import no.hvl.dat102.exceptions.EmptyCollectionException;

public class TabellOrdnetListe<T extends Comparable<T>> implements OrdnetListeADT<T> {

	private final static int STDK = 100;
	private final static int IKKE_FUNNET = -1;
	private int bak;
	private T[] liste;

	public TabellOrdnetListe() {
		this(STDK);
	}

	public TabellOrdnetListe(int startKapasitet) {
		bak = 0;
		liste = (T[]) (new Comparable[startKapasitet]);
	}

	@Override
	public T fjernSiste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		T resultat = null;
		// ... Fyll ut
		resultat = liste[bak - 1];
		liste[bak - 1] = null;
		bak--;

		return resultat;
	}

	@Override
	public T fjernFoerste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		T resultat = null;
		// ... Fyll ut
		resultat = liste[0];
		for (int j = 0; j < bak - 1; j++) {  // kan være bak istedetfor bak-1
			liste[j] = liste[j + 1];
		}
		return resultat;
	}

	@Override
	public T foerste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		T resultat = liste[0];
		return resultat;
	}

	@Override
	public T siste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		// T resultat = null;
		return liste[bak - 1];
		// ...Fyll ut
		T forrige = null;
		for (int i = 0; i < liste.length; i++) {
			for (T element : liste) {
				if (element == null) {
					return forrige;
				}
				forrige = element;
			}
		}

		return forrige; // erstatt alle forrige med resultat
	}

	@Override
	public boolean erTom() {
		return (bak == 0);
	}

	@Override
	public int antall() {
		return bak;
	}

	@Override
	public void leggTil(T element) {

		// ...Fyll ut
		if (antall() == liste.length) {
			utvid();
		}
		int indeks = 0;
		for (int i = 0; i < liste.length; i++) {
			indeks = i;
			if (liste[i].compareTo(element) >= 0) {
				break;
			}
		}
		for (int j = bak - 1; j >= indeks; j--) {
			liste[j + 1] = liste[j];
		}
		liste[indeks] = element;
		bak++;
	}

	@Override
	public boolean inneholder(T element) {
		return (finn(element) != IKKE_FUNNET);
	}

	@Override
	public T fjern(T element) {
		// ...Fyll ut
		if (erTom())
			throw new EmptyCollectionException("ordnet liste ");

		boolean funnet = false;
		T svar = null;
		int indeks = 0;

		for (int i = 0; (i < bak && !funnet); i++) {
			if (liste[i].equals(element)) {
				indeks = i;
				svar = liste[i];
				bak--;
				funnet = true;
			}
		}
		if (funnet) {
			for (int j = indeks; j < bak - 1; j++) { // kan være bak istedetfor bak-1
				liste[j] = liste[j + 1];
			}
		}
		return svar;

	}

	private int finn(T el) {
		int i = 0, resultat = IKKE_FUNNET;
		// ...Fyll ut		
		for (int j = 0; j < liste.length; j++) {
			if (liste[j].equals(el)) {
				resultat = 1;
				break;
			}
		}
		
		return resultat;
	}

	public String toString() {
		String resultat = "";

		for (int i = 0; i < bak; i++) {
			resultat = resultat + liste[i].toString() + "\n";
		}
		return resultat;
	}

	private void utvid() {
		T[] hjelpeTabell = (T[]) (new Comparable[liste.length * 2]);

		for (int i = 0; i < liste.length; i++) {
			hjelpeTabell[i] = liste[i];
		}

		liste = hjelpeTabell;
	}

}// class
