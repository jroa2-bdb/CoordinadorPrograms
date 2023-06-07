import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cliente_FTP_EBCDIC {

	OutputStream out;
	InputStream in;
	DataOutputStream dos;
	DataInputStream dis;
	BufferedInputStream bis;
	BufferedOutputStream bos;
	Socket s;
	static int r0=0;
	static int r1=0;
	static int r9=0;
	static int rOt=0;
	static SimpleDateFormat formarFecha = new SimpleDateFormat("HH:mm:ss");
	
	String	nombreDeLaClase = "";
	
	public String operacion = "consulta";
	//public String operacion = "pago";
	public String strHeader = "";

	public static void main(String[] args) {
		
		System.out.print("INICIA CLIENTEISC \n");
		Cliente_FTP_EBCDIC ClienteISC = new Cliente_FTP_EBCDIC();
		ClienteISC.conectar();
		Date now = new Date();
		String nowDate = formarFecha.format(now);
		System.out.println(nowDate);
//		Incrementos de las transacciones
		for (int ni = 0; ni<100; ni++){		
			ClienteISC.enviar();
			ClienteISC.recibir();	
		}
		
		ClienteISC.desconectar();
		Date end = new Date();
		String endDate = formarFecha.format(end);
		System.out.println(endDate);
		System.out.println("Desconectado:");
		System.out.println("Resultados");
		System.out.println("Viba00 "+r0);
		System.out.println("Viba10 "+r1);
		System.out.println("Viba99 "+r9);
		System.out.println("Eco "+rOt);
		
	}

	public void conectar() {
		nombreDeLaClase = this.getClass().getName().substring(0,8);
		nombreDeLaClase = nombreDeLaClase.substring(7,8); 
		System.out.println(nombreDeLaClase);
		String IPDestino = "10.85.140.5";
		//int PuertoDestino = 35004;
		int PuertoDestino = 40267;
		

		int LongTramaRespuesta;
		byte tramaRespuestaBytes[];

		try {
			s = new Socket(IPDestino, PuertoDestino);
			dis = new DataInputStream(new BufferedInputStream(s.getInputStream()));
			dos = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
			LongTramaRespuesta = (0x0000FFFF & dis.readShort()) - 2;
			tramaRespuestaBytes = new byte[LongTramaRespuesta];
			dis.readFully(tramaRespuestaBytes);
			ebcdic2Ascii(tramaRespuestaBytes);
			System.out.println("RespuestaConexión:" + "*" + new String(tramaRespuestaBytes) + "*");

		} catch (Exception e) {
			System.out.println("Error= " + e);
		}

	}

	public static final void ebcdic2Ascii(byte[] vecbParam) {
		for (int i = 0; i < vecbParam.length; i++) {
			vecbParam[i] = (byte) vecbEBCDIC2ASCII[0x00FF & vecbParam[i]];
		}
	}

	public static final byte vecbEBCDIC2ASCII[] = new byte[] { (byte) 0x00,
			(byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04, (byte) 0x09,
			(byte) 0x06, (byte) 0x7f, (byte) 0x08, (byte) 0x09, (byte) 0x0a,
			(byte) 0x0b, (byte) 0x0c, (byte) 0x0d, (byte) 0x0e, (byte) 0x0f,
			(byte) 0x10, (byte) 0x11, (byte) 0x12, (byte) 0x13, (byte) 0x14,
			(byte) 0x0a, (byte) 0x08, (byte) 0x17, (byte) 0x18, (byte) 0x19,
			(byte) 0x1a, (byte) 0x1b, (byte) 0x1c, (byte) 0x1d, (byte) 0x1e,
			(byte) 0x1f, (byte) 0x20, (byte) 0x21, (byte) 0x22, (byte) 0x23,
			(byte) 0x24, (byte) 0x25, (byte) 0x17, (byte) 0x1b, (byte) 0x28,
			(byte) 0x29, (byte) 0x2a, (byte) 0x2b, (byte) 0x2c, (byte) 0x05,
			(byte) 0x06, (byte) 0x07, (byte) 0x30, (byte) 0x31, (byte) 0x16,
			(byte) 0x33, (byte) 0x34, (byte) 0x35, (byte) 0x36, (byte) 0x04,
			(byte) 0x38, (byte) 0x39, (byte) 0x3a, (byte) 0x3b, (byte) 0x14,
			(byte) 0x15, (byte) 0x3e, (byte) 0x1a, (byte) 0x20, (byte) 0x41,
			(byte) 0x42, (byte) 0x43, (byte) 0x44, (byte) 0x45, (byte) 0x46,
			(byte) 0x47, (byte) 0x48, (byte) 0x49, (byte) 0x4a, (byte) 0x2e,
			(byte) 0x3c, (byte) 0x28, (byte) 0x2b, (byte) 0x7c, (byte) 0x26,
			(byte) 0x51, (byte) 0x52, (byte) 0x53, (byte) 0x54, (byte) 0x55,
			(byte) 0x56, (byte) 0x57, (byte) 0x58, (byte) 0x59, (byte) 0x21,
			(byte) 0x24, (byte) 0x2a, (byte) 0x29, (byte) 0x3b, (byte) 0x5e,
			(byte) 0x2d, (byte) 0x2f, (byte) 0x62, (byte) 0x63, (byte) 0x64,
			(byte) 0x65, (byte) 0x66, (byte) 0x67, (byte) 0x68, (byte) 0x69,
			(byte) 0x6a, (byte) 0x2c, (byte) 0x25, (byte) 0x5f, (byte) 0x3e,
			(byte) 0x3f, (byte) 0x70, (byte) 0x71, (byte) 0x72, (byte) 0x73,
			(byte) 0x74, (byte) 0x75, (byte) 0x76, (byte) 0x77, (byte) 0x78,
			(byte) 0x60, (byte) 0x3a, (byte) 0x23, (byte) 0x40, (byte) 0x27,
			(byte) 0x3d, (byte) 0x22, (byte) 0x80, (byte) 0x61, (byte) 0x62,
			(byte) 0x63, (byte) 0x64, (byte) 0x65, (byte) 0x66, (byte) 0x67,
			(byte) 0x68, (byte) 0x69, (byte) 0x8a, (byte) 0x8b, (byte) 0x8c,
			(byte) 0x8d, (byte) 0x8e, (byte) 0x8f, (byte) 0x90, (byte) 0x6a,
			(byte) 0x6b, (byte) 0x6c, (byte) 0x6d, (byte) 0x6e, (byte) 0x6f,
			(byte) 0x70, (byte) 0x71, (byte) 0x72, (byte) 0x9a, (byte) 0x9b,
			(byte) 0x9c, (byte) 0x9d, (byte) 0x9e, (byte) 0x9f, (byte) 0xa0,
			(byte) 0x7e, (byte) 0x73, (byte) 0x74, (byte) 0x75, (byte) 0x76,
			(byte) 0x77, (byte) 0x78, (byte) 0x79, (byte) 0x7a, (byte) 0xaa,
			(byte) 0xab, (byte) 0xac, (byte) 0x5b, (byte) 0xae, (byte) 0xaf,
			(byte) 0xb0, (byte) 0xb1, (byte) 0xb2, (byte) 0xb3, (byte) 0xb4,
			(byte) 0xb5, (byte) 0xb6, (byte) 0xb7, (byte) 0xb8, (byte) 0xb9,
			(byte) 0xba, (byte) 0xbb, (byte) 0xbc, (byte) 0x5d, (byte) 0xbe,
			(byte) 0xbf, (byte) 0x7b, (byte) 0x41, (byte) 0x42, (byte) 0x43,
			(byte) 0x44, (byte) 0x45, (byte) 0x46, (byte) 0x47, (byte) 0x48,
			(byte) 0x49, (byte) 0xca, (byte) 0xcb, (byte) 0xcc, (byte) 0xcd,
			(byte) 0xce, (byte) 0xcf, (byte) 0x7d, (byte) 0x4a, (byte) 0x4b,
			(byte) 0x4c, (byte) 0x4d, (byte) 0x4e, (byte) 0x4f, (byte) 0x50,
			(byte) 0x51, (byte) 0x52, (byte) 0xda, (byte) 0xdb, (byte) 0xdc,
			(byte) 0xdd, (byte) 0xde, (byte) 0xdf, (byte) 0x5c, (byte) 0xe1,
			(byte) 0x53, (byte) 0x54, (byte) 0x55, (byte) 0x56, (byte) 0x57,
			(byte) 0x58, (byte) 0x59, (byte) 0x5a, (byte) 0xea, (byte) 0xeb,
			(byte) 0xec, (byte) 0xed, (byte) 0xee, (byte) 0xef, (byte) 0x30,
			(byte) 0x31, (byte) 0x32, (byte) 0x33, (byte) 0x34, (byte) 0x35,
			(byte) 0x36, (byte) 0x37, (byte) 0x38, (byte) 0x39, (byte) 0xfa,
			(byte) 0xfb, (byte) 0xfc, (byte) 0xfd, (byte) 0xfe, (byte) 0xff };

	public void enviar() {

		byte bodyRequestBytes[] = hexa2Bytes(

				//"E3E3 4e323636 E3 E3 C3 C1 11 40 40 D6 C6 C3 C6 C4 F2 F3 F7 F7 F0 F4 F4 F0 F0 F1 F0 F0 F0 F0 F1 F6 F5 F9 F1 F4 E6 F7 E2 C9 C3 E2 F0 F7 F0 F0 F0 F0 F0 F0 F0 F0 11 91 49 D6 C6 F0 F1 11 C4 6D F0 F6 F2 F0 F1 F6 11 91 4A F1 F6 F5 F9 F1 F4 11 94 11 F0 11 C3 7B F4 F6 F8 F6 F9 F2 F8 F9 F2 11 91 10 F0 F0 11 91 03 F0 F0 11 91 01 F0 F0 F4 F0 "
		//		"4c53 4e303039 434f5753 F0 F0 F0 F0 F0 F0 56 49 42 41 11 40 40 F2 F1 F6 F4 F7 F9 F5 F1 F7 F5 F7 F3 F0 F0 F1 F7 F0 F0 F0 F0 F8 F2 F7 F2 F5 E6 F7 E2 C9 C3 E2 F0 F5 F0 F0 F0 F0 F0 F0 F0 F0 11 91 49 D6 C6 F0 F1 11 C4 6D F0 F4 F2 F5 F1 F7 11 C5 D3 F2 11 E4 F7 F0 F0 11 E4 F8 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 11 92 C7 F0 F1 11 C3 7B F0 F0 F0 F0 F0 F9 F0 F4 F9 F2 F0 11 91 01 F0 F9 F8 F3 11 C6 D4 F0 F0 F1 F7 11 95 01 F7 F9 F5 F1 F7 F5 F7 F3 11 95 02 F1 11 92 49 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F2 F0 F1 F8 F0 F9 F6 F0 F1 F3 F4 F2 11 92 E2 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 11 93 B4 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 11 93 B5 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 11 93 B6 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 11 96 B7 F0 F4 F2 F5 F2 F0 F1 F7 11 93 C2 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 11 95 04 F1 F9 F0 F0 F0 F1 F0 F1 11 C3 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0"
		  //"F0 F0 F0 F0 F0 F0 56 49 42 41 11 40 40 F2 F1 F6 F4 F7 F9 F5 F1 F7 F5 F7 F3 F0 F0 F1 F7 F0 F0 F0 F0 F8 F2 F7 F2 F5 E6 F7 E2 C9 C3 E2 F0 F5 F0 F0 F0 F0 F0 F0 F0 F0 11 91 49 D6 C6 F0 F1 11 C4 6D F0 F4 F2 F5 F1 F7 11 C5 D3 F2 11 E4 F7 F0 F0 11 E4 F8 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 11 92 C7 F0 F1 11 C3 7B F0 F0 F0 F0 F0 F9 F0 F4 F9 F2 F0 11 91 01 F0 F9 F8 F3 11 C6 D4 F0 F0 F1 F7 11 95 01 F7 F9 F5 F1 F7 F5 F7 F3 11 95 02 F1 11 92 49 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F2 F0 F1 F8 F0 F9 F6 F0 F1 F3 F4 F2 11 92 E2 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 11 93 B4 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 11 93 B5 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 11 93 B6 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 11 96 B7 F0 F4 F2 F5 F2 F0 F1 F7 11 93 C2 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 11 95 04 F1 F9 F0 F0 F0 F1 F0 F1 11 C3 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0"
				"4C 53 4E 30 30 36 E3 E3 C3 C1 11 40 40 C3 D9 C7 C3 F1 F9 F2 F4 F3 F6 F7 F1 F0 F0 F3 F0 F0 F0 F0 F1 F4 F5 F3 F1 F2 E6 F7 E2 C9 C7 C4 F1 F3 F0 F0 F0 F0 F0 F0 F0 F0 11 91 49 D6 C6 F0 F1 11 C4 6D F2 F0 F1 F7 F0 F4 11 91 4A F1 F4 F5 F3 F1 F2 11 40 C3 F0 11 91 01 F0 F0 F0 F0 "
//				"4c53 4e323636 E3 E3 C3 C1 11 40 40 D6 C6 C3 C6 F8 F0 F9 F0 F1 F3 F6 F1 F0 F0 F0 F7 F0 F0 F0 F1 F0 F4 F2 F0 F7 E6 F7 E2 C9 C3 E2 F0 F8 F0 F0 F0 F0 F0 F0 F0 F0 11 91 49 D6 C6 F0 F1 11 C4 6D F0 F6 F2 F0 F1 F6 11 91 4A F1 F0 F4 F2 F0 F7 11 94 11 F1 11 C3 7B F0 F0 F0 F0 F0 F1 F0 F8 F1 11 91 10 F0 F0 11 91 03 F0 F0 11 91 01 F0 F0 F7 F9  "
//			    "4C 53 e5 c9 c2 c1 11 40 40 d6 c6 c4 c7 d7 d7 c5 f0 f0"
//				+ "f0 f7 f0 f0 f0 f0 f2 f0 f0 f0 f1 f0 f1 f3 f2 f3"
//				+ "f0 f0 f0 f0 f0 f0 f0 f0 f0 f0 f0 f0 f0 f0 f0 f0"
//				+ "11 91 49 d6 c6 f0 f1 11 c4 6d f0 f8 f1 f2 f1 f6"
//				+ "11 91 4a f1 f0 f1 f3 f2 f3 11 7a 19 f0 f0 f4 f7"
//				+ "f0 f0 f0 f0 f0 f0 11 7a 26 f0 f0 f7 f0 f0 f3 f2"
//				+ "f8 f8 11 7a 87 f1 11 e4 f3 f0 f0 f0 f0 f0 f0 f0"
//				+ "f0 f0 f0 f0 11 e4 f2 40 11 c6 d4 f0 f0 f0 f0 11"
//				+ "91 01 f0 f0 f0 f0 11 b2 c3 f2 f0 f1 f6 f0 f8 f1"
//				+ "f2 f2 f3 f1 f9 f4 f0 f0 f0 f0 f2 f9 d7 40 40 40"
//				+ "40 40 40 40 40 40 40"
				
		);         

		byte headerRequestBytes[] = strHeader.getBytes();
		//System.out.println(headerRequestBytes);

		byte bodyRequestBytesPago[] = hexa2Bytes("4c53 4e303036 e3e3c3c1 114040f2"
				+ "f1f6f5f1 f9f2f4f3 f6f7f1f0 f0f8f0f0"
				+ "f0f0f0f9 f5f0f1f9 c3c1d5c1 d3c5e2f1"
				+ "f0f0f0f0 f01193c1 f011c15b f0f0f011"
				+ "911df011 9192f0f0 f011c3f0 f0f0f0f0"
				+ "f0f1f9f0"
				+ "f0f01192 c7f0f211 4040f4f3 f4f6f2f9"
				+ "f2f3f411 c3f5f0f0 f0119616 f2f1f6f0"
				+ "119249f0 f0f0f0f0 f0f0f0f0 f0f0f0f0"
				+ "f0f0f0f0 f0f2f4f0 f0f0f4f5 f8f1f7f2"
				+ "f81192e2 f0f0f0f0 f0f0f0f0 f0f0f0f0"
				+ "f0f0f0f0 f0f0f0f0 f0f0f0f0 f0f0f0f0"
				+ "f0f0f211 93b4f0f0 f0f0f0f0 f0f0f0f0"
				+ "f0f0f0f0 f0f0f0f0 f0f0f0f0 f0f0f0f0"
				+ "f0f0f0f0 1193b5f0 f0f0f0f0 f0f0f0f0"
				+ "f0f0f0f0 f0f0f0f0 f0f0f0f0 f0f0f0f0"
				+ "f0f0f0f0 f093b6f0 f0f0f0f0 f0f0f0"
				+ "f0f0f0f0 f0f0f0f0 f0f0f0f0 1193b7f1"
				+ "f8f41193 b8f0f0f0 f0f0f0f0 f0f0f0f0"
				+ "f0f0f0f0 f0f0f0f0 1193b9f0 f0f0f0f0"
				+ "f0f0f0f0 f0f0f0f0 f0f0f0f0 f0f0f0f0"
				+ "1193b0f0 1193baf0 f211c6d4 f0f0f0f8"
				+ "f0119201"
				+ "f1f8f411 c3f3f0f0 f0f011c3 f4f0f0f0"
				+ "f0119105"
				+ "f0f0f0f0 11c37bf0"
				+ "f0f0f0f0 f0f0f0f0 11c3f2f0 f0f01193"
				+ "c4f01140 d5d51191 01f0f0f0 1193a1f1"
				+ "11b2c3f2 f0f0f8f1 f0f2f9f1 f7f3f1f1"
				+ "f1f1f9f2 f1f6f1f0 f011a2d1 f0f0f0f0"
				+ "f0f0f0f0 f1f1f0f0 f2f0f511 a2d2f111"
				+ "a2d4f0f0 f0f3f4f3 f4f3f411 b2c3f0f1" + "f6f1f0f0");

		int TotalRequestBytesLenght = 0;
		if (operacion.equalsIgnoreCase("consulta")) {
			TotalRequestBytesLenght = headerRequestBytes.length
					+ bodyRequestBytes.length;
		} else {
			TotalRequestBytesLenght = headerRequestBytes.length
					+ bodyRequestBytesPago.length;
		}

		byte TotalRequestBytes[] = new byte[TotalRequestBytesLenght];

		if (operacion.equalsIgnoreCase("consulta")) {
			System.arraycopy(headerRequestBytes, 0, TotalRequestBytes, 0,
					headerRequestBytes.length);
			System.arraycopy(bodyRequestBytes, 0, TotalRequestBytes,
					headerRequestBytes.length, bodyRequestBytes.length);

		} else {
			System.arraycopy(headerRequestBytes, 0, TotalRequestBytes, 0,
					headerRequestBytes.length);
			System.arraycopy(bodyRequestBytesPago, 0, TotalRequestBytes,
					headerRequestBytes.length, bodyRequestBytesPago.length);
		}

		try {
			dos.writeShort((short) TotalRequestBytes.length + 2); // (Comentario
																	// para
																	// Monetarias
																	// 2161)
			dos.write(TotalRequestBytes);
			dos.flush();

		} catch (Exception e) {
			System.out.println("Error= " + e);
		}
	}

	public void recibir() {
		try {
			int longResponse = ((0x0000FFFF & dis.readShort()) - 2);
			byte responseBytes[] = new byte[longResponse];
			dis.readFully(responseBytes);
//			System.out.println("Respuesta:" + new String(responseBytes, "cp037"));
			String Prueba = new String(responseBytes, "cp037");
//			System.out.println("Lectura de variable " + Prueba.substring(11, 17));
			String statusCode = Prueba.substring(11, 17);
			if (statusCode.equals("VIBA00")) {
				r0++;
				System.out.println("0 ");
			} else if (statusCode.equals("VIBA10")) {
				r1++;
				System.out.println("1 ");
			} else if (statusCode.equals("VIBA99")) {
				r9++;
				System.out.println("9 ");
			} else {
				rOt++;
				System.out.println("0tro s ");
			}			
//			System.out.println("Cantidad de variable " + Prueba.length());
		} catch (Exception e) {
			System.out.println("Error= " + e);
		}

	}

	public void desconectar() {
		try {
			dis.close();
			dos.close();
			s.close();

		} catch (Exception e) {
			System.out.println("Error= " + e);
		}

	}

	public static final byte[] hexa2Bytes(String strHexa) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		for (int i = 0; i < strHexa.length(); i += 2) {
			if (strHexa.charAt(i) == ' ') {
				i--;
				continue;
			}
			baos.write((0x00ff & Integer.parseInt(strHexa.substring(i, i + 2),
					16)));
		}
		return baos.toByteArray();
	}
}
