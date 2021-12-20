
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class jasper {
            SimpleDateFormat dFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date dat = new Date();
            String date = dFormat.format(dat);
    public jasper(int Fid,Connection con) throws FileNotFoundException{
    HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("Fid", Fid);
        
        try {
            JasperDesign jasperReport = JRXmlLoader.load(getClass().getResourceAsStream("Invoice.jrxml"));
            JasperReport jreport = JasperCompileManager.compileReport(jasperReport);
            
            JasperPrint jp = JasperFillManager.fillReport(jreport, parameters, con);
            JasperViewer jv = new JasperViewer(jp,false);
            jv.setTitle("Facture");
            jv.setVisible(true);
            //JasperViewer.viewReport(jp, false);
            FileOutputStream fileOutputStream =new FileOutputStream(new File(System.getProperty("user.home")+File.separator+"facture_"+DeshboardController.nom+"_"+date+"_N°"+DeshboardController.Fid+".pdf"));
            JasperExportManager.exportReportToPdfStream(jp,fileOutputStream);
        try {
            fileOutputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(jasper.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error in close jasper pdf file"+ex.toString());
        }
        } catch (JRException ex) {
            Logger.getLogger(jasper.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error in second jasper"+ex.toString());
        } 
        
    }
   
}