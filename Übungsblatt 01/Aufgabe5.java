





class Konto {
	private String bezeichnung;
	private Kunde[] zeichnungsberechtigter; // nin 1
	
	public Geldbetrag saldo();
	public void einzahlen(Geldbetrag eing_betrag);
}


class Kunde {
	private Konto[] konto; // nin 1
}


class Privatkunde extends Kunde {
	private String vorname;
	private String nachname;
	private Adresse postadresse;
}

class Geschäftskunde extends Kunde {
	private String firmenname;
	private Adresse domiziladresse;

}


class Adresse {
	private String strasse;
	private int hausnummer;
	private String plz;
	private String ort;

}


































