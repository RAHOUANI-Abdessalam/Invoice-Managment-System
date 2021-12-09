/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author issam
 */
public class historiqueClient {
   
    String numeroFacture,date,montantTotale,modeDeReglement;

    public historiqueClient(String numeroFacture, String date, String montantTotale, String modeDeReglement) {
        this.numeroFacture = numeroFacture;
        this.date = date;
        this.montantTotale = montantTotale;
        this.modeDeReglement = modeDeReglement;
    }

    public String getNumeroFacture() {
        return numeroFacture;
    }

    public String getDate() {
        return date;
    }

    public String getMontantTotale() {
        return montantTotale;
    }

    public String getModeDeReglement() {
        return modeDeReglement;
    }

    public void setNumeroFacture(String numeroFacture) {
        this.numeroFacture = numeroFacture;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMontantTotale(String montantTotale) {
        this.montantTotale = montantTotale;
    }

    public void setModeDeReglement(String modeDeReglement) {
        this.modeDeReglement = modeDeReglement;
    }

    
    
}
