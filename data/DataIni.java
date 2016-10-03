package gestionSuivi.data;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import gestionSuivi.compte.Compte;
import gestionSuivi.fenetre.tableauTransaction.ZModel;
import gestionSuivi.placement.Placement;

public class DataIni implements DataInterf {

	@Override
	public String nameSvg(Placement place) {
		// TODO Auto-generated method stub
		return "svg_"+place.getMnemo()+".TransSvg";
	}
	
	public Object[][] defaultData(){
		Compte[] listeCompte = Compte.values();
		Object[][] data = {
			      {"16/08/1983", listeCompte[0], new Float(15.0d), new Float(1), new Float(0), new Float(15d), new Float(0), "-"},
			      {"01/01/2001", listeCompte[0], new Float(15.0d), new Float(0), new Float(2), new Float(0), new Float(30),"-"},
			      {"02/02/2002", listeCompte[0], new Float(15.0d), new Float(3), new Float(0), new Float(45d), new Float(0),"-"},
			      {"13/12/2013", listeCompte[0], new Float(15.0d), new Float(0), new Float(4), new Float(0d), new Float(60d),"-"}		
				};
		return data;
	}
	
	public Object[] defaultLine(){
		Compte[] listeCompte = Compte.values();
		Object[] ligneDefault= {"16/08/1983", listeCompte[0], new Float(15.0d), new Float(1), new Float(0), new Float(15d), new Float(0), "-"};
		return ligneDefault;
	}

	public void svgData(Placement place, ZModel model){
	    ObjectOutputStream oos;
	    try {	
	      //On envoie maintenant les donn�es !
	      oos = new ObjectOutputStream(
	              new BufferedOutputStream(
	                new FileOutputStream(
	                  new File(this.nameSvg(place)))));
	            
	      try {
	        oos.writeObject(model.getData());
	        /*
	        System.out.println("Svg effectu�e ! "+place.getName());
	        System.out.println("nbr lignes : "+model.getData().length);
	        System.out.println(model.getData());
	        */
	      } catch (IOException e) {
	        e.printStackTrace();
	      }
		
	      oos.close();
	        	
	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	}
	
	public Object[][] lireData(Placement place){
	    ObjectInputStream ois;
	    Object[][] data;
	    try {	
	      //On r�cup�re maintenant les donn�es !
	      ois = new ObjectInputStream(
	              new BufferedInputStream(
	                new FileInputStream(
	                  new File(this.nameSvg(place)))));
	            
	      try {
	        data = (Object[][])ois.readObject();
		    System.out.println("Fichier retrouv� et lu... "+place.getName());
	      } catch (ClassNotFoundException e) {
		    System.err.println("Probl�me : ClassNotFoundException");
	        e.printStackTrace();
	    	data = this.defaultData();
	      }
		  
	    ois.close();
	        	
	    } catch (FileNotFoundException e) {
    	  System.err.println("Pas de sauvegarde trouv�e, on prend les valeurs par d�faut � la place !");
    	  data = this.defaultData();
	    } catch (IOException e) {
	      System.err.println("Autre probl�me IO...");
	      e.printStackTrace();
    	  data = this.defaultData();
	    }
	  return data;
	}
}
