//
//import java.sql.Connection;
//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
///**
// *
// * @author issam
// */
//public class jasper {
//    public jasper(String Fid,Connection con){
//    HashMap<String, Object> parameters = new HashMap<>();
//        parameters.put("chid", chid);
//        
//        try {
//            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream("/jaspertutorial/jasperReport.jasper"));
//            
//            JasperPrint jp = JasperFillManager.fillReport(jasperReport, parameters, conn);
//            
//            JasperViewer.viewReport(jp, true);
//            
//            JasperExportManager.exportReportToPdfStream(jp, new FileOutputStream(new File(System.getProperty("user.home")+File.separator+"challanreport.pdf")));
//        } catch (JRException ex) {
//            Logger.getLogger(Print.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Print.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }
//   
//}
