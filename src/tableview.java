/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author issam
 */
public class tableview {
    double PrixTransport,PrixUnitaire,quantité,Totale;
    int codecodeproduit;
    String Désignation,TVA;

    public tableview(int codecodeproduit, String Désignation, double PrixTransport, double PrixUnitaire, double quantité) {
        this.codecodeproduit = codecodeproduit;
        this.Désignation = Désignation;
        this.PrixTransport = PrixTransport;
        this.PrixUnitaire = PrixUnitaire;
        this.quantité = quantité;
  
    }



    public int getCodecodeproduit() {
        return codecodeproduit;
    }

    public String getDésignation() {
        return Désignation;
    }

    public double getPrixTransport() {
        return PrixTransport;
    }

    public double getPrixUnitaire() {
        return PrixUnitaire;
    }

    public double getQuantité() {
        return quantité;
    }

    public String getTVA() {
        return TVA;
    }

    public double getTotale() {
        return Totale;
    }

    public void setCodecodeproduit(int codecodeproduit) {
        this.codecodeproduit = codecodeproduit;
    }

    public void setDésignation(String Désignation) {
        this.Désignation = Désignation;
    }

    public void setPrixTransport(double PrixTransport) {
        this.PrixTransport = PrixTransport;
    }

    public void setPrixUnitaire(double PrixUnitaire) {
        this.PrixUnitaire = PrixUnitaire;
    }

    public void setQuantité(double quantité) {
        this.quantité = quantité;
    }

    public void setTVA(String TVA) {
        this.TVA = TVA;
    }

    public void setTotale(double Totale) {
        this.Totale = Totale;
    }
    
}
