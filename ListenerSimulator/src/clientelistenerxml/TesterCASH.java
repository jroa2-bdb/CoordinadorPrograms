package clientelistenerxml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
      
public class TesterCASH {

	private static final String HOST = "10.85.140.5"; // SNV2
	// private static final String HOST = "10.84.64.45"; // SNV2
	// private static final String HOST = "10.85.140.10"; // SNV4 (FIDELITY)

	// private static final String PORT = "9592"; //IVR2
	// private static final String PORT = "9591"; //IVR
	// private static final String PORT = "9594"; //PE2
	  private static final String PORT = "9593"; // PEE
	// private static final String PORT = "9596"; //PEMTZ
//	private static final String PORT = "8082"; // CRM
	// private static final String PORT = "9595"; // CRMSNV4
	// private static final String PORT = "8085"; // NSP
	// private static final String PORT = "8089"; //BBS
	// private static final String PORT = "8084"; // CAsh4U

	// private static final String PORT = "7594"; //PE2_DES
	// private static final String PORT = "7591"; // IVR DESARROLLO

	// private static final String PORT = "7084"; // CAsh4UDESA
	// private static final String PORT = "7591"; // CAsh4UDESA
	 //private static final String PORT = "7081"; //CRM_DES
	//private static final String PORT = "9080";
	//private static final String PORT = "7593"; //PEE DESARROLLO
	 //private static final String PORT = "7596"; //PEmtz DESARROLLO
	 //private static final String PORT = "7083"; // NSPDES
	// private static final String PORT = "7099";
	// private static final String XML_PRUEBA =
	// "D://simulador_listener/prueba_CRNA.xml";
	private static final String XML_PRUEBA = "D://JuanPrograms/Eclipse_coordinador/prueba_AM6I.xml";

	// private static final String XML_PRUEBA =
	// "D://simulador_listener/prueba_AM6I_2.xml";

	public static void main(String[] args) {
		try {

			File f = new File(XML_PRUEBA);

			URL url = new URL("http://" + HOST + ":" + PORT + "/HTTP/GTWY");

			FileInputStream fis = new FileInputStream(f);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			OutputStream out = conn.getOutputStream();

			byte[] bytes = new byte[1024];
			int count = 0;
			while ((count = fis.read(bytes, 0, 1024)) > 0)
				out.write(bytes, 0, count);
			fis.close();
			out.flush();
			out.close();
			
			// **********************************************************
			FileInputStream fis2 = new FileInputStream(f);
			HttpURLConnection conn2 = (HttpURLConnection) url.openConnection();
			conn2.setDoOutput(true);
			OutputStream out2 = conn2.getOutputStream();

			//byte[] bytes = new byte[1024];
			count = 0;
			while ((count = fis2.read(bytes, 0, 1024)) > 0)
				out2.write(bytes, 0, count);
			fis2.close();
			out2.flush();
			out2.close();
			
			// **********************************************************

			InputStream is = conn.getInputStream();
			InputStream is2 = conn2.getInputStream();
			String res = "";

			while ((count = is.read(bytes)) > 0)
				res += new String(bytes, 0, count);
			// System.out.println("---------------------------------"+i+"---------------------------------");
			System.out.println("REQUEST N�MERO ENVIADO A http://" + HOST + ":"
					+ PORT + "/HTTP/GTWY");
			System.out.println(res + "\n");
			
			
			while ((count = is2.read(bytes)) > 0)
				res += new String(bytes, 0, count);
			// System.out.println("---------------------------------"+i+"---------------------------------");
			System.out.println("REQUEST N�MERO ENVIADO A http://" + HOST + ":"
					+ PORT + "/HTTP/GTWY");
			System.out.println(res + "\n");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
