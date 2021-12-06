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
    
    String codecodeproduit,Désignation,PrixTransport,PrixUnitaire,quantité,TVA,Totale;

    public tableview(String codecodeproduit, String Désignation, String PrixTransport, String PrixUnitaire, String quantité, String TVA, String Totale) {
        this.codecodeproduit = codecodeproduit;
        this.Désignation = Désignation;
        this.PrixTransport = PrixTransport;
        this.PrixUnitaire = PrixUnitaire;
        this.quantité = quantité;
        this.TVA = TVA;
        this.Totale = Totale;
    }

    public String getCodecodeproduit() {
        return codecodeproduit;
    }

    public String getDésignation() {
        return Désignation;
    }

    public String getPrixTransport() {
        return PrixTransport;
    }

    public String getPrixUnitaire() {
        return PrixUnitaire;
    }

    public String getQuantité() {
        return quantité;
    }

    public String getTVA() {
        return TVA;
    }

    public String getTotale() {
        return Totale;
    }

    public void setCodecodeproduit(String codecodeproduit) {
        this.codecodeproduit = codecodeproduit;
    }

    public void setDésignation(String Désignation) {
        this.Désignation = Désignation;
    }

    public void setPrixTransport(String PrixTransport) {
        this.PrixTransport = PrixTransport;
    }

    public void setPrixUnitaire(String PrixUnitaire) {
        this.PrixUnitaire = PrixUnitaire;
    }

    public void setQuantité(String quantité) {
        this.quantité = quantité;
    }

    public void setTVA(String TVA) {
        this.TVA = TVA;
    }

    public void setTotale(String Totale) {
        this.Totale = Totale;
    }
    
}
