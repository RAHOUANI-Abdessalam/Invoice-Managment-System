
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class jasper {
    public jasper(int Fid,Connection con) throws FileNotFoundException{
    HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("Fid", Fid);
        
        try {
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream("Invoice.jrxml"));
            
            JasperPrint jp = JasperFillManager.fillReport(jasperReport, parameters, con);
            
            JasperViewer.viewReport(jp, true);
            
            //JasperExportManager.exportReportToPdfStream(jp, new FileOutputStream(new File(System.getProperty("user.home")+File.separator+"challanreport.pdf")));
        } catch (JRException ex) {
            Logger.getLogger(jasper.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
   
}