package clientelistenerxml;

import com.sun.javafx.css.CssError;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;


public class TesterCashBetter {

	public static void main(String[] args) throws IOException {
		String XML_PROBE = "D://JuanPrograms/Eclipse_coordinador/prueba_AM6I.xml";
		String HOST = "10.85.140.5"; // SNV2
		String PORT = menu();

		try {
			File f = new File(XML_PROBE);
			URL url = new URL("http://" + HOST + ":" + PORT+ "/HTTP/GTWY");
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

			InputStream is = conn.getInputStream();
			String res = "";

			while ((count = is.read(bytes)) > 0)
				res += new String(bytes, 0, count);
			
			System.out.println("REQUEST ENVIADO A http://" + HOST + ":" + PORT + "/HTTP/GTWY");
			System.out.println("RESPUESTA:");
			System.out.println(res + "\n");
			xmlResponse(res);
			//xmlResponse(prettyFormat(res,2));
		} catch (Exception errorExection) {
			errorExection.printStackTrace();
		}

	}
		
	public static void xmlResponse(String message){
		try {
            PrintWriter writer = new PrintWriter("D://JuanPrograms/Eclipse_coordinador/RespuestaXML.xml", "UTF-8");
            writer.print(message);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public static String menu(){
		String channel="";
		Scanner input =  new Scanner(System.in);
		//Puertos Listenerhttp DEV
		HashMap<Integer,String> httpPortsDEv = new HashMap<Integer,String>();
		httpPortsDEv.put(1,"7089");
		httpPortsDEv.put(2,"7084");
		httpPortsDEv.put(3,"7081");
		httpPortsDEv.put(4,"7082");
		httpPortsDEv.put(5,"7087");
		httpPortsDEv.put(6,"7591");
		httpPortsDEv.put(7,"7592");
		httpPortsDEv.put(8,"8096");
		httpPortsDEv.put(9,"7594");
		httpPortsDEv.put(10,"7083");
		httpPortsDEv.put(11,"7596");
		httpPortsDEv.put(12,"7593");
		//Puertos Listenerhttp QA
		System.out.print("Seleccione el ambiente [1]DEV || [2]QA:");
		int inputOption = input.nextInt();
		switch (inputOption){
			case 1:
				System.out.println(":::::::::::::::::::DEV::::::::::::::::::::::::::");
				System.out.println("1)BBS:7089\n"+
						"2)C4U:7084\n"+
						"3)CRM1:7081\n"+
						"4)CRM2:7082\n"+
						"5)FCARD:7087\n"+
						"6)IVR1:7591\n"+
						"7)IVRS:7592\n"+
						"8(MANT:8086\n"+
						"9)MREC:7594\n"+
						"10)NSP:7083\n"+
						"11)PMTZ:7596\n"+
						"12)PREC:7593"
				);
				input.nextLine();
				channel= input.nextLine();
				channel= httpPortsDEv.get(Integer.parseInt(channel));
				break;
			case 2:
				System.out.println(":::::::::::::::::::QA::::::::::::::::::::::::::");
				System.out.println("1)BBS:7089\n"+
						"2)C4U:7084\n"+
						"3)CRM1:7081\n"+
						"4)CRM2:7082\n"+
						"5)FCARD:7087\n"+
						"6)IVR1:7591\n"+
						"7)IVRS:7592\n"+
						"8(MANT:8086\n"+
						"9)MREC:7594\n"+
						"10)NSP:7083\n"+
						"11)PMTZ:7596\n"+
						"12)PREC:7593"
				);
				channel= input.nextLine();
				channel= httpPortsDEv.get(Integer.parseInt(channel));
				break;

		}
		return channel.isEmpty()?"Puerto no Valido":channel;

	}
	

}
