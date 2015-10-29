import java.util.*;
public class Jazol {
	String ime;
	String prezime;
	Jazol pretpostaven;
	LinkedList<Jazol> podNego;
	Jazol(){}
	Jazol(String ime,String prezime,Jazol pretostaven)
	{
		if(podNego==null)
			podNego = new LinkedList();
		this.ime = ime;
		this.prezime = prezime;
		this.pretpostaven = pretostaven;
	}
public String vratiIme()
{
	return this.ime;
}
public String vratiPrezime()
{
	return this.prezime;
}
public void dodadi(Jazol nov)
{
	this.podNego.add(nov);
}
public Jazol baraj(Jazol pom,String ime,String prezime,String pretpostaven)
{
	if(pretpostaven == " ")
		return this;
	if(pom==null)
	{
		if(this.vratiIme() == ime && this.vratiPrezime()== prezime && this.pretpostaven.vratiPrezime() == pretpostaven)
		{
			pom=this;
			return pom;
		}
		else
		{
			for(int i=0;i<this.podNego.size();i++)
			{
				if(this.podNego.get(i).vratiIme() == ime && this.podNego.get(i).vratiPrezime() == prezime && this.podNego.get(i).pretpostaven.vratiIme() == pretpostaven )
				{
					pom = this.podNego.get(i);
					return pom;
				}
				else
					pom=this.podNego.get(i).baraj(pom, ime, prezime, pretpostaven);
			}
			return pom;
		}
	}
	else
		return pom;
}
public Jazol barajImePrezime(Jazol pom1,String ime, String prezime)
{
	if(this.vratiIme() == ime && this.vratiPrezime() == prezime)
		return this;
	if(pom1==null)
	{
		if(this.vratiIme() == ime && this.vratiPrezime() == prezime)
		{
			pom1=this;
			return pom1;
		}
		else
		{
			for(int i=0;i<this.podNego.size();i++)
			{
				if(this.podNego.get(i).vratiIme() == ime && this.podNego.get(i).vratiPrezime() == prezime)
				{
					pom1 = this.podNego.get(i);
					return pom1;
				}
				else
					pom1=this.podNego.get(i).barajImePrezime(pom1,ime,prezime);
			}
			return pom1;
		}
	}
	else
		return pom1;
	
}
public LinkedList barajPretpostaveni(LinkedList pomosnaLista,boolean najdov,String ime,String prezime)
{

	if(!najdov)
	{
    if(this.vratiIme() == ime && this.vratiPrezime() == prezime)
    {
         return pomosnaLista;
    }    	
    else
    {
	pomosnaLista.add(this);
    	for(int i=0;i<this.podNego.size();i++)
    	{
    		if(this.podNego.get(i).vratiIme() == ime && this.podNego.get(i).vratiPrezime() == prezime )
		{
			najdov = true;
			break;

		}    
    		
    	}
	if(najdov==false)
	{
		for(int i=0;i<this.podNego.size();i++)
    		{
    		    pomosnaLista.add(this.podNego.get(i));   
    		
    		}
		for(int i=0;i<this.podNego.size();i++)
    		{
    		  this.podNego.get(i).barajPretpostaveni(pomosnaLista,najdov,ime,prezime);   
    		
    		}
	
	}
	else
	return pomosnaLista;

    return pomosnaLista;
    }
	}
	else
		return pomosnaLista;
}
public LinkedList barajPotcineti(LinkedList pomosnaLista)
{
	if(!this.podNego.isEmpty())
	{
		for(int i=0;i<this.podNego.size();i++)
			pomosnaLista.add(this.podNego.get(i));
		for(int i=0;i<this.podNego.size();i++)
			this.podNego.get(i).barajPotcineti(pomosnaLista);
		return pomosnaLista;
	}
	else
		return pomosnaLista;
}
public void pecati()
{
	System.out.println("Pretpostaven: " + this.vratiIme() + " " + this.vratiPrezime());
	if(!this.podNego.isEmpty())
	{
		System.out.println("Podnego: ");
		for(int i=0;i<this.podNego.size();i++)
		System.out.print(this.podNego.get(i).vratiIme() + " " +this.podNego.get(i).vratiPrezime() + " " );
		System.out.println();
		for(int i=0;i<this.podNego.size();i++)
			this.podNego.get(i).pecati();
	}
	
}
}
