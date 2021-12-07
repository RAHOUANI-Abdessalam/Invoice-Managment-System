
import javax.swing.table.TableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author issam
 */
public class tableclient {
  int numeroClient;
    String raisonSociale, adresse,matriculeFiscale,nArticle,registreDeCommerce;

    public tableclient(int numeroClient, String raisonSociale, String adresse, String matriculeFiscale, String nArticle, String registreDeCommerce) {
        this.numeroClient = numeroClient;
        this.raisonSociale = raisonSociale;
        this.adresse = adresse;
        this.matriculeFiscale = matriculeFiscale;
        this.nArticle = nArticle;
        this.registreDeCommerce = registreDeCommerce;
    }

    public int getNumeroClient() {
        return numeroClient;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getMatriculeFiscale() {
        return matriculeFiscale;
    }

    public String getnArticle() {
        return nArticle;
    }

    public String getRegistreDeCommerce() {
        return registreDeCommerce;
    }

    public void setNumeroClient(int numeroClient) {
        this.numeroClient = numeroClient;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setMatriculeFiscale(String matriculeFiscale) {
        this.matriculeFiscale = matriculeFiscale;
    }

    public void setnArticle(String nArticle) {
        this.nArticle = nArticle;
    }

    public void setRegistreDeCommerce(String registreDeCommerce) {
        this.registreDeCommerce = registreDeCommerce;
    }
   
}
