package clientelistenerxml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ListenerXML   {

    private static final String HOST =   "10.85.140.5";
   private static final String PORT =  "8082";//crm
   //  private static final String HOST =   "10.85.140.5";
  //   private static final String PORT =    "8082";
   //  private static final String HOST =  "100.1.190.101";//autra
   //   private static final String PORT =   "29011"; //autra
   //private static final String XML_PRUEBA =          "C://Users/cvanega/Documents/BBS_BBTF.xml";  // ARCHIVO BBS
      private static final String XML_PRUEBA =  "D://simulador_listener/CRM-PRUEBA.xml";  // ARCHIVO CRM   XMLCRM4
   // private static final String XML_PRUEBA =  "C://Users/cvanega/Documents/WebSphere/mensaje.xml";

     public static void main(String[] args){
        try{

             File f = new File(XML_PRUEBA) ;

            FileInputStream fis=new FileInputStream(f);


            // URL url=new URL("http://" + HOST + ":" + PORT );
           //http://10.85.4.67:8040/PSIGW/HttpListeningConnector

             URL url=new URL("http://" + HOST + ":" + PORT + "/HTTP/GTWY");//listener HTTP BBS
         //URL url=new URL("http://10.86.118.90:8040/PSIGW/HttpListeningConnector");  //URL CRM
         //  URL url=new URL("http://127.0.0.1:8088/mockBalanceInquiryBinding/Binding");

            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            OutputStream out=conn.getOutputStream();

            byte[] bytes=new byte[5000];
            int count =0;
            while((count=fis.read(bytes, 0, 5000))>0)
            {
            	out.write(bytes,0,count);
            	System.out.println(new String(bytes));
            }
            fis.close();
            out.flush();
            out.close();

            InputStream is=conn.getInputStream();
            String res="";

            while((count=is.read(bytes))>0 )
            {
                res+=new String(bytes, 0, count);
                System.out.println(new String(bytes));
            }


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}