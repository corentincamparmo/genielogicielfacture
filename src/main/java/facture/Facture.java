package facture;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;

public class Facture {
    private int numero;
    private Date emission;
    private Client destinataire;
    ArrayList <LigneFacture> facture=new ArrayList<>();

   

    public Facture(Client destinataire, Date emission, int numero ) {
        this.numero = numero;
        this.emission = emission;
        this.destinataire = destinataire;
    }

    

    /**
     * Get the value of numero
     *
     * @return the value of numero
     */
    public int getNumero() {
        return numero;
    }

    public Date getEmission() {
        return emission;
    }

    
       
    public Client getDestinataire() {
         return destinataire;
    }


    
    public void ajouteLigne(Article a, int nombre, float remise) {
         LigneFacture ligne= new LigneFacture(nombre,this,a,remise);
         facture.add(ligne);
         
         
   }
    
   public float montantTTC(float tauxTVA) {
       float ttc=0;
       for( LigneFacture l : facture ){
        ttc +=  l.montantLigne() * (1+tauxTVA);
       }
       return ttc;
        
   }
   
   public void print(PrintStream out, float tva) {
         // TODO: Implémenter cette méthode
        throw new UnsupportedOperationException("Pas encore implémenté");
   }
   
}
