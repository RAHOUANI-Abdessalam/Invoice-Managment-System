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
   
    String codeProduit,designation,PrixTransport,PrixUnitaire,qteProduit,totalTVA,montantTotale;

    public tableview(String codeProduit, String designation, String PrixTransport, String PrixUnitaire, String qteProduit, String totalTVA, String montantTotale) {
        this.codeProduit = codeProduit;
        this.designation = designation;
        this.PrixTransport = PrixTransport;
        this.PrixUnitaire = PrixUnitaire;
        this.qteProduit = qteProduit;
        this.totalTVA = totalTVA;
        this.montantTotale = montantTotale;
    }

    public void setCodeProduit(String codeProduit) {
        this.codeProduit = codeProduit;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setPrixTransport(String PrixTransport) {
        this.PrixTransport = PrixTransport;
    }

    public void setPrixUnitaire(String PrixUnitaire) {
        this.PrixUnitaire = PrixUnitaire;
    }

    public void setQteProduit(String qteProduit) {
        this.qteProduit = qteProduit;
    }

    public void setTotalTVA(String totalTVA) {
        this.totalTVA = totalTVA;
    }

    public void setMontantTotale(String montantTotale) {
        this.montantTotale = montantTotale;
    }

    public String getCodeProduit() {
        return codeProduit;
    }

    public String getDesignation() {
        return designation;
    }

    public String getPrixTransport() {
        return PrixTransport;
    }

    public String getPrixUnitaire() {
        return PrixUnitaire;
    }

    public String getQteProduit() {
        return qteProduit;
    }

    public String getTotalTVA() {
        return totalTVA;
    }

    public String getMontantTotale() {
        return montantTotale;
    }

    
}
