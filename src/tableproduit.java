/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author issam
 */
public class tableproduit {
  int  codeProduit  ;
String designation ,prixTransport,prixUnitaire;

    public tableproduit(int codeProduit, String designation, String prixTransport, String prixUnitaire) {
        this.codeProduit = codeProduit;
        this.designation = designation;
        this.prixTransport = prixTransport;
        this.prixUnitaire = prixUnitaire;
    }

    public int getCodeProduit() {
        return codeProduit;
    }

    public String getDesignation() {
        return designation;
    }

    public String getPrixTransport() {
        return prixTransport;
    }

    public String getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setCodeProduit(int codeProduit) {
        this.codeProduit = codeProduit;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setPrixTransport(String prixTransport) {
        this.prixTransport = prixTransport;
    }

    public void setPrixUnitaire(String prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }
    
}
