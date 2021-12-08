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
    double PrixTransport,PrixUnitaire,qteProduit,Totale;
    int codeProduit;
    String designation,TVA;

    public tableview(int codeProduit, String designation, double PrixTransport, double PrixUnitaire, double qteProduit) {
        this.codeProduit = codeProduit;
        this.designation = designation;
        this.PrixTransport = PrixTransport;
        this.PrixUnitaire = PrixUnitaire;
        this.qteProduit = qteProduit;
  
    }



    public int getcodeProduit() {
        return codeProduit;
    }

    public String getdesignation() {
        return designation;
    }

    public double getPrixTransport() {
        return PrixTransport;
    }

    public double getPrixUnitaire() {
        return PrixUnitaire;
    }

    public double getqteProduit() {
        return qteProduit;
    }

    public String getTVA() {
        return TVA;
    }

    public double getTotale() {
        return Totale;
    }

    public void setcodeProduit(int codeProduit) {
        this.codeProduit = codeProduit;
    }

    public void setdesignation(String designation) {
        this.designation = designation;
    }

    public void setPrixTransport(double PrixTransport) {
        this.PrixTransport = PrixTransport;
    }

    public void setPrixUnitaire(double PrixUnitaire) {
        this.PrixUnitaire = PrixUnitaire;
    }

    public void setqteProduit(double qteProduit) {
        this.qteProduit = qteProduit;
    }

    public void setTVA(String TVA) {
        this.TVA = TVA;
    }

    public void setTotale(double Totale) {
        this.Totale = Totale;
    }
    
}
