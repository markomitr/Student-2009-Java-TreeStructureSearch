import java.util.*;
public class Firma {
	Jazol gazda;
	
	Firma(String ime,String prezime)
	{
		gazda = new Jazol(ime,prezime,null);
	}
public void dodadiNaGazda(String ime,String prezime)
{
	Jazol nov = new Jazol(ime,prezime,gazda);
	gazda.dodadi(nov);
}
public void dodadiPoz(String ime,String prezime,String imePretpostaven,String prezimePretpostaven,String negovPretpostaven)
{	Jazol nov;
		if(imePretpostaven == gazda.vratiIme() && prezimePretpostaven == gazda.vratiPrezime() && negovPretpostaven == " ")
		{
			nov = new Jazol(ime,prezime,gazda);
			gazda.podNego.add(nov);
		}
		else
		{
			Jazol pomosen = new Jazol();
			Jazol pomosen2 = null;
			pomosen=gazda.baraj(pomosen2,imePretpostaven,prezimePretpostaven,negovPretpostaven);
			 nov = new Jazol(ime,prezime,pomosen);
			pomosen.podNego.add(nov);
		}
}
public void pecati()
{
	gazda.pecati();
}
public LinkedList vratiKolegi(String ime, String prezime)
{
	Jazol pomosen = new Jazol();
	Jazol pomosen2 = null;
	pomosen=gazda.barajImePrezime(pomosen2, ime, prezime);
	if(pomosen.pretpostaven.podNego != null)
	return pomosen.pretpostaven.podNego;
	else
		return null;
}
public LinkedList vratiPretpostaveni(String ime,String prezime)
{
	LinkedList<Jazol> pom = new LinkedList();
	LinkedList<Jazol> glavna = new LinkedList();
	glavna = gazda.barajPretpostaveni(pom, false, ime, prezime);
	for(int i=0;i<glavna.size(); i++)
		System.out.println(glavna.get(i).vratiIme() + " " +  glavna.get(i).prezime);
	return glavna;
}
public LinkedList vratiPotcineti(String ime , String prezime)
{
	Jazol pomosen = new Jazol();
	Jazol pomosen2 = null;
	pomosen=gazda.barajImePrezime(pomosen2, ime, prezime);
	LinkedList<Jazol> pom = new LinkedList();
	LinkedList<Jazol> glavna = new LinkedList();
	glavna = pomosen.barajPotcineti(pom);
	for(int i=0;i<glavna.size(); i++)
		System.out.println(glavna.get(i).vratiIme() + " " +  glavna.get(i).prezime);
	return glavna;
	
}
public Jazol barajKolega(String ime,String prezime)
{
	Jazol pomosen = new Jazol();
	Jazol pomosen2 = null;
	pomosen=gazda.barajImePrezime(pomosen2, ime, prezime);
	System.out.println("Postoi : " + pomosen.vratiIme() + " " + pomosen.vratiPrezime());
	return pomosen;
}
public static void main(String [] args)
{
}
}
