import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import java.net.Socket;

public class ClienteISC_Charlie {

    OutputStream out;
    InputStream in;
    DataOutputStream dos;
    DataInputStream dis;
    BufferedInputStream bis;
    BufferedOutputStream bos;
    Socket s;
    
   // public String operacion = "pago";
    
   // public String strHeader = "";
    
    public String operacion =     "consulta"; 
    public String strHeader = ""; 
    
    public static void main(String[] args) {

        ClienteISC ClienteISC = new ClienteISC();
        ClienteISC.conectar();
        ClienteISC.enviar();
        ClienteISC.recibir();
        ClienteISC.desconectar(); 
    }

    public void conectar()   { 

       // String IPDestino= "10.87.148.3"; 
        //int PuertoDestino =  9991; 

           String IPDestino =   "10.85.140.5";     
        /*CANALES*/
       //    int   PuertoDestino  =  40290;          
      //   int   PuertoDestino  =   40291;                        
       //  int PuertoDestino = 40292;       
        
        /*IVR*/
           int PuertoDestino  =     40400;         
        //    int PuertoDestino =          40294;                        
        // int PuertoDestino =     40295;
       //   int PuertoDestino =      45207;
        
        
        
        int LongTramaRespuesta;
        byte tramaRespuestaBytes[];
        try {

            s = new Socket(IPDestino, PuertoDestino);
             dis = 
new DataInputStream(new BufferedInputStream(s.getInputStream()));
            dos = 
new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
            LongTramaRespuesta = (0x0000FFFF & dis.readShort()) - 2;
            tramaRespuestaBytes = new byte[LongTramaRespuesta];
            dis.readFully(tramaRespuestaBytes);
            ebcdic2Ascii(tramaRespuestaBytes);
            System.out.println("RespuestaConexión:" + "*" + 
                                 new String(tramaRespuestaBytes) + "*");


        } catch (Exception e) {
            System.out.println("Error= " + e); 
        }


    }

    public static final void ebcdic2Ascii(byte[] vecbParam) {
        for (int i = 0; i < vecbParam.length; i++) {
            vecbParam[i] = (byte)vecbEBCDIC2ASCII[0x00FF & vecbParam[i]];
        }
    }


    public static final byte vecbEBCDIC2ASCII[] =  
        new byte[] { (byte)0x00, (byte)0x01, (byte)0x02, (byte)0x03, 
                     (byte)0x04, (byte)0x09, (byte)0x06, (byte)0x7f, 
                     (byte)0x08, (byte)0x09, (byte)0x0a, (byte)0x0b, 
                     (byte)0x0c, (byte)0x0d, (byte)0x0e, (byte)0x0f, 
                     (byte)0x10, (byte)0x11, (byte)0x12, (byte)0x13, 
                     (byte)0x14, (byte)0x0a, (byte)0x08, (byte)0x17, 
                     (byte)0x18, (byte)0x19, (byte)0x1a, (byte)0x1b, 
                     (byte)0x1c, (byte)0x1d, (byte)0x1e, (byte)0x1f, 
                     (byte)0x20, (byte)0x21, (byte)0x22, (byte)0x23, 
                     (byte)0x24, (byte)0x25, (byte)0x17, (byte)0x1b, 
                     (byte)0x28, (byte)0x29, (byte)0x2a, (byte)0x2b, 
                     (byte)0x2c, (byte)0x05, (byte)0x06, (byte)0x07, 
                     (byte)0x30, (byte)0x31, (byte)0x16, (byte)0x33, 
                     (byte)0x34, (byte)0x35, (byte)0x36, (byte)0x04, 
                     (byte)0x38, (byte)0x39, (byte)0x3a, (byte)0x3b, 
                     (byte)0x14, (byte)0x15, (byte)0x3e, (byte)0x1a, 
                     (byte)0x20, (byte)0x41, (byte)0x42, (byte)0x43, 
                     (byte)0x44, (byte)0x45, (byte)0x46, (byte)0x47, 
                     (byte)0x48, (byte)0x49, (byte)0x4a, (byte)0x2e, 
                     (byte)0x3c, (byte)0x28, (byte)0x2b, (byte)0x7c, 
                     (byte)0x26, (byte)0x51, (byte)0x52, (byte)0x53, 
                     (byte)0x54, (byte)0x55, (byte)0x56, (byte)0x57, 
                     (byte)0x58, (byte)0x59, (byte)0x21, (byte)0x24, 
                     (byte)0x2a, (byte)0x29, (byte)0x3b, (byte)0x5e, 
                     (byte)0x2d, (byte)0x2f, (byte)0x62, (byte)0x63, 
                     (byte)0x64, (byte)0x65, (byte)0x66, (byte)0x67, 
                     (byte)0x68, (byte)0x69, (byte)0x6a, (byte)0x2c, 
                     (byte)0x25, (byte)0x5f, (byte)0x3e, (byte)0x3f, 
                     (byte)0x70, (byte)0x71, (byte)0x72, (byte)0x73, 
                     (byte)0x74, (byte)0x75, (byte)0x76, (byte)0x77, 
                     (byte)0x78, (byte)0x60, (byte)0x3a, (byte)0x23, 
                     (byte)0x40, (byte)0x27, (byte)0x3d, (byte)0x22, 
                     (byte)0x80, (byte)0x61, (byte)0x62, (byte)0x63, 
                     (byte)0x64, (byte)0x65, (byte)0x66, (byte)0x67, 
                     (byte)0x68, (byte)0x69, (byte)0x8a, (byte)0x8b, 
                     (byte)0x8c, (byte)0x8d, (byte)0x8e, (byte)0x8f, 
                     (byte)0x90, (byte)0x6a, (byte)0x6b, (byte)0x6c, 
                     (byte)0x6d, (byte)0x6e, (byte)0x6f, (byte)0x70, 
                     (byte)0x71, (byte)0x72, (byte)0x9a, (byte)0x9b, 
                     (byte)0x9c, (byte)0x9d, (byte)0x9e, (byte)0x9f, 
                     (byte)0xa0, (byte)0x7e, (byte)0x73, (byte)0x74, 
                     (byte)0x75, (byte)0x76, (byte)0x77, (byte)0x78, 
                     (byte)0x79, (byte)0x7a, (byte)0xaa, (byte)0xab, 
                     (byte)0xac, (byte)0x5b, (byte)0xae, (byte)0xaf, 
                     (byte)0xb0, (byte)0xb1, (byte)0xb2, (byte)0xb3, 
                     (byte)0xb4, (byte)0xb5, (byte)0xb6, (byte)0xb7, 
                     (byte)0xb8, (byte)0xb9, (byte)0xba, (byte)0xbb, 
                     (byte)0xbc, (byte)0x5d, (byte)0xbe, (byte)0xbf, 
                     (byte)0x7b, (byte)0x41, (byte)0x42, (byte)0x43, 
                     (byte)0x44, (byte)0x45, (byte)0x46, (byte)0x47, 
                     (byte)0x48, (byte)0x49, (byte)0xca, (byte)0xcb, 
                     (byte)0xcc, (byte)0xcd, (byte)0xce, (byte)0xcf, 
                     (byte)0x7d, (byte)0x4a, (byte)0x4b, (byte)0x4c, 
                     (byte)0x4d, (byte)0x4e, (byte)0x4f, (byte)0x50, 
                     (byte)0x51, (byte)0x52, (byte)0xda, (byte)0xdb, 
                     (byte)0xdc, (byte)0xdd, (byte)0xde, (byte)0xdf, 
                     (byte)0x5c, (byte)0xe1, (byte)0x53, (byte)0x54, 
                     (byte)0x55, (byte)0x56, (byte)0x57, (byte)0x58, 
                     (byte)0x59, (byte)0x5a, (byte)0xea, (byte)0xeb, 
                     (byte)0xec, (byte)0xed, (byte)0xee, (byte)0xef, 
                     (byte)0x30, (byte)0x31, (byte)0x32, (byte)0x33, 
                     (byte)0x34, (byte)0x35, (byte)0x36, (byte)0x37, 
                     (byte)0x38, (byte)0x39, (byte)0xfa, (byte)0xfb, 
                     (byte)0xfc, (byte)0xfd, (byte)0xfe, (byte)0xff };

    public void enviar() {

        /* VALIDACION DE pin
        byte bodyRequestBytes[] = hexa2Bytes( "e3e3c2c2 114040c9" +
                "e5d7e6f0 f2f2f040 404040f1 f2f3f4f0" +
                "f0f0f1f9 f1f5f1f2 40404040 40404040" +
                "f0f0f0f0 f0f0f0f0 119149c9 d5f0f111" +
                "c46df0f7 f1f1f0f5 11914af1 f9f1f5f1" +
                "f21140c3 f0f0f0f7 f9f3f6f9 f1f7f311" +
                "9405f1f2 f3f4f5f6 f7f8f9f0 c1c2c3c4" +
                "c5c61140 c4f01194 06f0f0f0 f0f0f0f0" +
                "f0f0f0f0 f0f0f0f0 f0119407 f0f0f0f0" +
                "f0f0f0f0 f0f0f0f0 f1f2f3f7 11e4f2f1" +
                "40404040 40404040 40404040 40404040" +
                "40404040 40404040 40404040 40404040" +
                "40404040 40404040 40404040 40404040" +
                "40404040 40404040" );*/

        /*2164*/
       /* byte bodyRequestBytes[] =
         hexa2Bytes("c3d6e6e2 114040f2"+
                    "f1f6f4f5 f2f4f1f4 f2f8f5f0 f0f4f3f0"+
                    "f0f0f1f6 f2f1f0f7 e6f2e2c9 d6d9f9f1"+
                    "f0f0f0f0 f0f0f0f0 119149d6 c6f0f111"+
                    "c46df1f0 f0f9f0f8 11c5d3f2 11e4f7f0"+
                    "fC11e4f8 f0f8f9f0 f9f0f5f2 f1f1f111"+
                    "9249f0f0 f0f0f0f0 f0f0f0f0 f0f0f0f0"+
                    "f0f0f0f1 f2f0f8f1 f6f5f3f1 f8f6f8f1"+
                    "1192c7f0 f211c37b f0f4f3f4 f6f2f9f2"+
                    "f3f41191 01f1f0f8 11c6d4f0 f0f4f311"+
                    "9501f5f2 f4f1f4f2 f8f51195 02f1");*/

        /*2164  * Consullta Valor Recaudo WS - Trama utilizando Concentrador*/
        /*byte bodyRequestBytes[] =
           hexa2Bytes("434f5753 56494241 114040f2"+
                     "f1f6f4f5 f2f4f1f4 f2f8f5f0 f0f4f3f0"+
                     "f0f0f1f6 f2f1f0f7 e6f2e2c9 d6d9f9f1"+
                     "f0f0f0f0 f0f0f0f0 119149d6 c6f0f111"+
                     "c46df1f0 f0f9f0f8 11c5d3f2 11e4f7f0"+
                     "f111e4f8 f0f0f0f8 f9f0f9f0 f5f2f1f1" +
                     "f1119249 f0f0f0f0 f0f0f0f0 f0f0f0f0"+
                     "f0f0f0f0 f0f1f2f0 f8f1f6f5 f3f1f8f6"+
                     "f8f11192 c7f0f211 c37bf0f4 f3f4f6f2"+
                     "f9f2f3f4 119101f0 f1f0f811 c6d4f0f0"+
                     "f4f31195 01f5f2f4 f1f4f2f8 f5119502"+
                     "f1");*/
                     
         /*2161 * Pago Recaudos WS */
         /*byte bodyRequestBytes[] =
         hexa2Bytes("4c53 4e303036 e3e3c3c1 114040f2" +
         "f1f6f1f1 f9f2f4f3 f6f7f1f0 f0f8f0f0" +
         "f0f0f0f9 f5f0f1f9 c3c1d5c1 d3c5e2f1" +
         "f0f0f0f0 f01193c1 f011c15b f0f0f011" +
         "91d1f011 9192f0f0 f011c3f0 f1f0f0f0" +
         "f0f01192 c7f0f211 4040f4f3 f4f6f2f9" +
         "f2f3f411 c3f5f0f0 f0119661 f2f1f6f0" +
         "119249f1 f1f1f1f1 1192e2f2 f2f2f2f2" +
         "1193b411 93b51193 b61193b7 f1f8f411" +
         "93b8f011 93b9f011 93baf0f2 11c6d4f0" +
         "f0f0f8f0 119201f1 f8f411c3 f311c3f4" +
         "11910511 c37bf0f0 f0f0f0f0 f0f0f011" +
         "c3f2f0f0 f01193c4 f01140d5 d5119101" +
         "f0f0f011 91a1f111 a211b2c3 f2f0f0f8" +
         "f1f0f2f9 f1f7f3f1 f1f9f2f1 f6f1f0f0" +
         "11a2c9f1 11b2c3f2 f0f0f8f1 f0f3f1f1" +
         "f1f5f9f2 f8f2f1f6 f1f0f0");*/


         /*2121 * Pago Recaudos WS */
         /*byte bodyRequestBytes[] =
          hexa2Bytes("01604c53 4e303036 e3e3c3c1 114040f2" +
          "f1f2f1f1 f9f2f4f3 f6f7f1f0 f0f8f0f0" +
          "f0f0f0f9 f5f0f1f9 c3c1d5c1 d3c5e2f1" +
          "f0f0f0f0 f0f0f0f0 1193a1f0 1193c2f7" +
          "f7f7f0f9 f9f9f8f0 f0f1f7f0 f11193c5" +
          "f0f0f0f0 f0f0f0f0 f0f1f1f1 f8f8f0f6" +
          "f4f7f8f0 f2f9f3f8 f8119205 f0f0f0f0" +
          "f0f0f0f0 f0f0f0f0 f0f0f0f0 f0f0f0f0" +
          "f0f0f0f0 f01193c1 f0f0f011 c15bf0f0" +
          "f011c35a f8f0f6f2 f0f0f011 c3f0f0f0" +
          "f011c37b f0f0f0f0 f0f0f0f0 f0119105" +
          "f011c3f1 f8f0f6f2 f0f0f011 960911e4" +
          "f6f0f1f0 f1f1f9f0 f0119298 f8f0f6f2" +
          "f0f0f011 a2c3f011 93b01193 b2f0f0f0" +
          "f0f0f0f0 f0f0f0f0 f3f4f8f1 f5f11193" +
          "b3f011c3 f5f0f0f0 1193b7f0 f0f01193" +
          "b8f01193 b9f011c6 d4f0f0f5 f6119201" +
          "f0f0f011 9141f011 44204040 40404040" +
          "40404040 40e4f7f2 f0f0f8f1 f0f3f1c3" +
          "f311c3f4 11a2c9f1 40d5d511 9101f0f0" +
          "f01191a1 f111b2c3 f2f0f0f8 f1f0f3f1" +
          "f0f9f0f8 f1f0f2f1 f2f1f0f0 11114040");*/
          
          //String strHeader = "      "; Para 2164
          //String strHeader ;//Para 2121, 2161
          //strHeader = "";//Para 2121, 2161
          byte headerRequestBytes[] = strHeader.getBytes();
          
        /*2164 *0220006376843 Consullta Valor Recaudo WS - Trama sin utilizar Concentrador*/
        
        /* trama para consulta por factura
         * "434f5753 f0f0f0f0 f0f05649 42411140"+
                   "40f2f1f6 f4f5f2f4 f1f4f2f8 f5f0f0f4"+
                   "f3f0f0f0 f1f6f2f1 f0f7e6f2 e2c9d6d9"+
                   "f9f1f0f0 f0f0f0f0 f0f01191 49d6c6f0"+
                   "f111c46d f1f0f0f9 f0f811c5 d3f211e4"+
                   "f7f0f111 e4f8f0f0 f0f8f9f0 f9f0f5f2"+
                   "f1f1f111 9249f0f0 f0f0f0f0 f0f0f0f0" +
                   "f0f0f0f0 f0f0f0f1 f1f0f9f1 f6f3f0f8"+
                   "f5f1f3f4 1192c7f0 f211c37b f0f4f3f4"+
                   "f6f2f9f2 f3f41191 01f0f1f0 f811c6d4"+
                   "f0f0f4f3 119501f5 f2f4f1f4 f2f8f511"+
                   "9502f1"
         */
        
        /* trama para consulta por identificacion
         * "434f5753 f0f0f0f0 f0f05649 42411140"+
                   "40f2f1f6 f4f5f2f4 f1f4f2f8 f5f0f0f4"+
                   "f3f0f0f0 f1f6f2f1 f0f7e6f2 e2c9d6d9"+
                   "f9f1f0f0 f0f0f0f0 f0f01191 49d6c6f0"+
                   "f111c46d f1f0f0f9 f0f811c5 d3f111e4"+
                   "f7f0f111 e4f8f0f0 f0f0f0f0 f8f2f4f1"+
                   "f2f6f111 9249f0f0 f0f0f0f0 f0f0f0f0" +
                   "f0f0f0f0 f0f0f0f0 f2f2f0f0 f0f6f3f7"+
                   "f6f8f4f3 1192c7f0 f211c37b f0f4f3f4"+
                   "f6f2f9f2 f3f41191 01f0f1f0 f811c6d4"+
                   "f0f0f4f3 119501f5 f2f4f1f4 f2f8f511"+
                   "9502f1"
         */
         
         /* valor de prueba factura= 220006376843 10288912 1650 0038 1254
          * valor de prueba identificacion= 39384332       184 8810 01
          */
     /*   byte bodyRequestBytes[] = 
            hexa2Bytes("434f5753 f0f0f0f0 f0f05649 42411140"+
                   "40f2f1f6 f4f5f2f4 f1f4f2f8 f5f0f0f4"+
                   "f3f0f0f0 f1f6f2f1 f0f7e6f2 e2c9d6d9"+
                   "f9f1f0f0 f0f0f0f0 f0f01191 49d6c6f0"+
                   "f111c46d f1f0f0f9 f0f811c5 d3f111e4"+
                   "f7f0f111 e4f8f0f0 f0f0f0f9 f8f5f8f8"+
                   "f7f9f711 9249f0f0 f0f0f0f0 f0f0f0f0" +
                   "f0f0f0f0 f0f0f0f0 f1f6f5f0 f0f0f3f8"+
                   "f1f2f5f4 1192c7f0 f211c37b f0f4f3f4"+
                   "f6f2f9f2 f3f41191 01f0f1f0 f811c6d4"+
                   "f0f0f4f3 119501f5 f2f4f1f4 f2f8f511"+
                   "9502f1");*/
                   
                   /*CANALES CONSULTA F900    CLIENTE OK   064*/ 
         /* byte bodyRequestBytes[] =                                 // CONSULTA F900
                hexa2Bytes("4c53 4e303136 434f5753 f0f0f0f0"+
                    "f0f05649 42411140 40f2f0f0 f1c6f9f0"+
                    "f0f7f9f9 f6f2f7f9 f7f0f0f0 f2f0f0f0"+
                    "f0f9f2f5 f3f2e6f7 e2c9d6d9 f0f7f0f0"+
                    "f0f0f0f0 f0f01140 c3f0f1f9 11950bf0"+
                    "f0f1f0f0 f0f0f0f0 f2f5f011 b8b8f911"+
                    "b8b9f9f9 f9f9f9f9 f9f9f911 9101f0f0"+
                    "f0f01191 a1f11193 a1f01111")                         ;  */
                    
        /*CANALES CONSULTA F900    CLIENTE OK   064*/ 
        /*byte bodyRequestBytes[] =                                 // CONSULTA F900 MAYO VARIOS TITULARES
        hexa2Bytes("4c53 4e303136 46494455 f0f0f0f0"+
         "f0f05649 42411140 40f2f0f0 f1c6f9f0"+
         "f0f7f9f9 f6f2f7f9 f7f0f0f0 f2f0f0f0"+
         "f0f9f2f5 f3f2e6f7 e2c9d6d9 f0f7f0f0"+
         "f0f0f0f0 f0f01140 c3f0f1f9 11950bf0"+//019 
         "f0f1f0f0 f0f0f1f2 f8f1f711 b8b8f911"+
         "b8b9f9f9 f9f9f9f9 f9f9f911 9101f0f0"+
         "f0f01191 a1f11193 a1f01111")     ; 
         */
        /*CANALES CONSULTA F900    CLIENTE OK   064*/ 
     /*   byte bodyRequestBytes[] =                                 // CONSULTA F900 MAYO encargo de inmobiliaria 
        hexa2Bytes("4c53 4e303136 434f5753 f0f0f0f0"+
         "f0f05649 42411140 40f2f0f0 f1c6f9f0"+
         "f0f7f9f9 f6f2f7f9 f7f0f0f0 f2f0f0f0"+
         "f0f9f2f5 f3f2e6f7 e2c9d6d9 f0f7f0f0"+
         "f0f0f0f0 f0f01140 c3f0f6f0 11950bf0"+//060 
         "f0f2f0f0 f0f0f0f0 f0f6f211 b8b8f911"+
         "b8b9f9f9 f9f9f9f9 f9f9f911 9101f0f0"+
         "f0f01191 a1f11193 a1f01111") ;  */
                    
        /*CANALES CONSULTA F900      CLIENTE ERROR   */   
     /*    byte bodyRequestBytes[] =   
        hexa2Bytes("4c53 4e303136 434f5753 f0f0f0f0"+
         "f0f05649 42411140 40f2f0f0 f1c6f9f0"+
         "f0f7f9f9 f6f2f7f9 f7f0f0f0 f2f0f0f0"+
         "f0f9f2f5 f3f2e6f7 e2c9d6d9 f0f7f0f0"+
         "f0f0f0f0 f0f01140 c3f0f6f1 11950bf0"+ //cod 061 no existe
         "f0f1f0f0 f0f0f0f0 f1f8f911 b8b8f911"+
         "b8b9f9f9 f9f9f9f9 f9f9f911 9101f0f0"+
         "f0f01191 a1f11193 a1f01111");   */
         
         
              
              
        /*CANALES SIMULADA INVERSION 2145    CLIENTE OK*/ 
      /* byte bodyRequestBytes[] =  
        hexa2Bytes("00f74c53 4e32 434f5753 f0f0f0f0"+ ///*  .7<œ+€
       "f0f05649 42411140 40f2f0f1 f1f2f1f4"+//00™‹á¦.  2011214  
       "f5f5f2f7 f2f9f4f6 f9f0f0f0 f5f0f0f0"+//5527294690005000
       "f0f9f2f1 f0f7e6f7 e2c9d6d9 f7f6f0f0"+//092107W7SIOR7600
       "f0f0f0f0 f0f01193 a1f01140 c3f0f6f4"+//000000.l~0. C064
       "11950bf0 f0f1f0f0 f0f0f0f0 f1f8f911"+//.n.001000000189.
       "c35af0f0 f0f0f0f0 f0f2f0f0 f0f0f0f0"+//C!00000002000000
       "f011c15b f0f0f0f0 f0f0f0f0 f0f0f0f0"+//0.A$000000000000
       "f0f0f011 912df111 c37bf0f0 f0f3f4f3"+//000.j1.C#0003
       "f4f3f411 c3f1f0f0 f0f0f0f0 f0f3f0f0"+//434.C10000000300
       "f0f0f0f0 f011c3f0 f0f0f0f0 f0f0f0f1"+//00000.C000000001
       "f0f0f0f0 f0f0f011 e4f2f011 c6d4f0f0"+//0000000.U20.FM00
       "f0f511e4 f3404040 40404040 40f1f8f6"+//05.U3        186
       "f2f6f9f9 1195a1f0 f0f011b8 b8f911b8"+//2699.n~000.¼¼9.¼
       "b9f9f9f9 f9f9f9f9 f9f91191 01f0f0f0"+//½999999999.j.000
       "f01191a1 f11111" );  //0.j~1..                   

              
        /*CANALES REAL INVERSION 2145    CLIENTE OK*/ 
      /*  byte bodyRequestBytes[] =
        hexa2Bytes( "00f74c53 4e32 43 4F 57 53 F0 F0 F0 F0 F0 F0 56 49 42 41 11 40"+
        "40 F2 F0 F1 F1 F2 F1 F4 F5 F7 F9 F9 F6 F2 F7 F9"+
        "F7 F0 F0 F0 F5 F0 F0 F0 F1 F6 F3 F3 F1 F8 F0 C2"+
        "D6 D7 F0 F0 F3 F1 F0 F0 F0 F0 F0 F0 F0 F0 11 93"+
        "A1 F0 11 40 C3 F0 F6 F4 11 95 0B F0 F0 F1 F0 F0"+
        "F0 F0 F0 F0 F1 F8 F9 11 C3 5A F0 F0 F0 F0 F0 F0"+
        "F0 F1 F5 F0 F0 F0 F0 F0 F0 11 C1 5B F0 F0 F0 F0"+
        "F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 11 C3 F1 F0 F0"+
        "F0 F0 F0 F0 F0 F1 F5 F0 F0 F0 F0 F0 F0 11 E4 F2"+
        "F0 11 C6 D4 F0 F0 F0 F5 11 E4 F3 40 40 40 40 40"+
        "40 40 F1 F2 F5 F3 F3 F0 F1 F3 11 95 A1 F0 F0 F0"+
        "11 B8 B8 F9 11 B8 B9 F9 F9 F9 F9 F9 F9 F9 F9 F9"+
        "11 91 01 F0 F0 F4 F0 11 91 A1 F1 11 11")    ;*/
        
        /*CANALES REAL INVERSION 2145                                                               OK OK OK CLIENTE sydar OK*/ 
     /*   byte bodyRequestBytes[] =
    hexa2Bytes( "00f74c53 4e32 43 4F 57 53 F0 F0 F0 F0 F0 F0 56 49 42 41 11 40"+ //| ....000000..... 
        "40 F2 F0 F1 F1 F2 F1 F4 F5 F7 F9 F9 F6 F2 F7"+    //| . 20112145799627
        "F9 F7 F0 F4 F6 F3 F0 F0 F0 F1 F1 F5 F5 F4 F8 F0"+ //| 9700030001155480
        "C2 D6 D7 F0 F0 F3 F1 F0 F0 F0 F0 F0 F0 F0 F0 11"+ //| BOP003100000000.
        "93 A1 F0 11 40 C3 F0 F1 F9 11 95 0B F0 F0 F1 F0"+ //| l~0. C019.n.0010
        "F0 F0 F0 F2 F5 F7 F8 F7 11 C3 5A F0 F0 F0 F0 F0"+ //| 00025787.C!00000
        "F0 F1 F4 F0 F0 F0 F0 F0 F0 F0 11 C1 5B F0 F0 F0"+ //| 0140000000.A$000
        "F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 11 C3 F1 F0"+ //| 000000000000.C10
        "F0 F0 F0 F0 F0 F1 F4 F0 F0 F0 F0 F0 F0 F0 11 E4"+ //| 00000140000000.U
        "F2 F3 11 C6 D4 F0 F0 F0 F3 11 E4 F3 40 40 40 40"+ //| 20.FM0003.U3    
        "40 40 40 40 40 F1 F8 F0 F1 F4 F2 11 95 A1 F0 F0"+ //|      180142.n~00
        "F0 11 B8 B8 F9 11 B8 B9 F9 F9 F9 F9 F9 F9 F9 F9"+ //| 0...9..`99999999
        "F9 11 91 01 F0 F0 F4 F0 11 91 A1 F1 11 11");                //| 9.j.0040.j~1.. */


   
        /*CANALES REAL INVERSION 2145    CLIENTE ERROR PRUEBA 2*/ 
      /*  byte bodyRequestBytes[] =
        hexa2Bytes("00f74c53 4e32 43 4F 57 53 F0 F0 F0 F0 F0 F0 56 49 42 41 11 40"+
        "40 F2 F0 F1 F1 F2 F1 F4 F5 F7 F9 F9 F6 F2 F7"+ //BA ??
        "F9 F7 F0 F5 F0 F3 F0 F0 F0 F1 F1 F5 F5 F4 F8 F0"+
        "C2 D6 D7 F0 F0 F3 F1 F0 F0 F0 F0 F0 F0 F0 F0 11"+
        "93 A1 F0 11 40 C3 F0 F6 F4 11 95 0B F0 F0 F1 F0"+
        "F0 F0 F0 F0 F0 F1 F8 F9 11 C3 5A F0 F0 F0 F0 F0"+
        "F0 F0 F0 F0 F0 F1 F4 F0 F0 F0 11 C1 5B F0 F0 F0"+
        "F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 11 C3 F1 F0"+
        "F0 F0 F0 F0 F0 F0 F0 F0 F0 F1 F4 F0 F0 F0 11 E4"+
        "F2 F0 11 C6 D4 F0 F0 F0 F3 11 E4 F3 40 40 40 40"+
        "40 40 40 40 40 F1 F2 F5 F3 F0 F0 11 95 A1 F0 F0"+
        "F0 11 B8 B8 F9 11 B8 B9 F9 F9 F9 F9 F9 F9 F9 F9"+
        "F9 11 91 01 F0 F0 F4 F0 11 91 A1 F1 11 11")     ;   */

      
                                                                                   
       //prueba Syddar                                                                  EXITOSA 2145
     /*  byte bodyRequestBytes[] =                                                        // **************************************
         hexa2Bytes("00f74c53 4e32 43 4F 57 53 F0 F0 F0 F0 F0 F0 56 49 42 41 11 40"+        // | ....000000..... 
       "40 F2 F0 F1 F1 F2 F1 F4 F5 F7 F9 F9 F6 F2 F7"+ // | . 20112145799627
       "F9 F7 F3 F1 F1 F8 F0 F0 F0 F1 F0 F1 F1 F0 F6 E6"+ // | 972050000101106W
       "F7 E2 C9 C7 C4 F5 F4 F0 F0 F0 F0 F0 F0 F0 F0 11"+ // | 7SIGD5400000000.   c1c5 F1 f0 f9 f1 11"+
       "93 A1 F0 11 40 C3 F1 F0 F9 11 95 0B F0 F0 F1 F0"+ // | l~0. C109.n.0010
       "F0 F0 F0 F0 F5 F0 F6 F4 11 C3 5A F0 F0 F0 F0 F0"+ // | 00005064.C!00000--EFECTIVO
       "F0 F0 F1 F2 F9 F0 F0 F0 F0 F0 11 C1 5B F0 F0 F0"+ // | 0002900000.A$000
       "F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 11 C3 F1 F0"+ // | 000000000000.C10
       "F0 F0 F0 F0 F0 F0 F1 F2 F9 F0 F0 F0 F0 F0 11 E4"+ // | 00000002900000.U--TOTAL
       "F2 F0 11 C6 D4 F0 F0 F0 F2 11 E4 F3 40 40 40 40"+ // | 23.FM0002.U3    
       "40 F1 F0 F2 F5 F3 F6 F9 F1 F4 F2 11 95 A1 F0 F0"+ // |  1025369142.n~00
       "F0 11 B8 B8 F9 11 B8 B9 F9 F9 F9 F9 F9 F9 F9 F9"+ // | 0...9..`99999999
       "F9 11 91 01 F0 F0 F0 F8 11 91 A1 F1 11 11");                */     //  | 9.j.0008.j~1.. 


       
        /*CANALES REAL INVERSION 2145    CLIENTE OK PRUEBA 2 FONDO 888         REVERSO   OK*/ 
    /*  byte bodyRequestBytes[] =
        hexa2Bytes("00f74c53 4e32 43 4F 57 53 F0 F0 F0 F0 F0 F0 56 49 42 41 11 40"+
        "40 F2 F0 F1 F1 F2 F1 F4 F5 F7 F9 F9 F6 F2 F7"+ //BA ??
        "F9 F7 F1 F0 F9 F2 F0 FC F0 F1 F1 F5 F5 F4 F8 F0"+//080
        "C2 D6 D7 F0 F0 F3 F1 F0 F0 F0 F0 F0 F0 F0 F0 11c1c5 F1 f0 f9 f1 11"+
        "93 A1 F0 11 40 C3 F8 F8 F8 11 95 0B F0 F0 F1 F0"+
        "F0 F0 F0 F0 F0 F2 F1 F2 11 C3 5A F0 F0 F0 F0 F0"+
        "F0 F0 F0 F0 F0 F1 F4 F0 F0 F0 11 C1 5B F0 F0 F0"+
        "F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 11 C3 F1 F0"+
        "F0 F0 F0 F0 F0 F0 F0 F0 F0 F1 F4 F0 F0 F0 11 E4"+
        "F2 F0 11 C6 D4 F0 F0 F0 F3 11 E4 F3 40 40 40 40"+
        "40 40 40 40 40 F1 F2 F5 F3 F0 F0 11 95 A1 F0 F0"+
        "F0 11 B8 B8 F9 11 B8 B9 F9 F9 F9 F9 F9 F9 F9 F9"+
        "F9 11 91 01 F0 F0 F0 F8 11 91 A1 F1 11 11") ; */
   
      /*CANALES REAL                                             CANCELACION 2145    CLIENTE PRUEBA 1  */  
         /*      byte bodyRequestBytes[] = 
        hexa2Bytes("00f74c53 4e32 43 4F 57 53 F0 F0 F0 F0 F0 F0 56 49 42 41 11 40"+
        
               "40 F2 F0 F9 F1 D6 C6 D4 C6 C3 F6 F1 F3 F6"+       // | TTCA.  OFMFC6136
              "F5 F1 F0 F2 F0 F4 F5 F0 F0 F0 F1 F1 F2 F7 F2 F0"+ // | 5100017000112720
              "C1 D8 E6 F7 F3 F6 F2 F2 F0 F0 F0 F0 F0 F0 F0 F0"+ // | AQW7362200000000
              "11 C6 D4 F0 F0 F1 F7 11 91 05 F1 11 40 C3 F8 F8"+ // | .FM0017.j.1. C06
              "F8 11 95 0B F0 F0 F1 F0 F0 F0 F0 F0 F1 F0 F5 F9"+ // | 0.n.001000001059
              "11 F4 F2 F0 11 E4 F3 F0 F0 F0 F0 F0 F1 F0 F1 F5 "+ // | .421.U300001015
              "F0 F0 F0 F0 F0 F1 11 E4 F6 F2 F4 F0 F4 F2 F0 F1"+ // | 000001.U62404201
              "F1 11 91 2D F0 11 93 A1 D7 11 92 4A F0 F0 F0 11"+ // | 3.j.0.l~P.k¢000.//93 A1
              "40 C5 F0 F0 F0 11 95 0C F0 F0 F0 F0 F0 F0 F0 F0"+ // |  E000.n.00000000
              "F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 11 C3 7B F0"+ // | 000000000000.C#0
              "F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0"+ // | 0000000000000000
              "11 40 C6 F0 11 F4 F3 F1 11 E4 F4 F0 F0 F0 F0 F0"+ // | . F1.431.U400000  //Digito ACCION 11 40 C6 = F1 o F0
              "F8 F1 F1 F0 F1 F9 F2 F3 F7 F4 11 C3 8B 7C 7C 7C"+ // | 8110192374.C.@@@
              "7C 7C 7C 7C 7C 7C 7C 7C 7C 7C 7C 7C 7C 7C 7C 7C"+ // | @@@@@@@@@@@@@@@@
              "7C 7C 7C 7C 7C 7C 7C 7C 7C 7C 7C 7C 7C 7C 7C 7C"+ // | @@@@@@@@@@@@@@@@
              "7C 7C 7C 7C 7C 7C 7C 7C 7C 7C 7C 7C 7C 7C 7C 11"+ // | @@@@@@@@@@@@@@@.
              "40 C7 F0 F2 11 B8 B8 F9 11 B8 B9 F9 F9 F9 F9 F9"+
              "F9 F9 F9 F9 11 91 01 F0 F0 F0 F8 11 91 A1 F0 11"+ // |  G02.j.0362.j~0.
              "11");                                       //    | .  */

      
        /* CANALES SYDDAR                                             CANCELACION OFMF    CLIENTE PRUEBA 1  */  
         /*    byte bodyRequestBytes[] = 
             hexa2Bytes("00f74c53 4e32 43 4F 57 53 F0 F0 F0 F0 F0 F0 56 49 42 41 11 40"+ // | ....000000..... 
               
                "40 F2 F0 F9 F1 D6 C6 D4 C6 F7 F9 F9 F6 F2 F7"+ // |  2091OFMF799627
                "F9 F7 F4 F0 F5 F7 F0 F0 F0 F0 F8 F5 F3 F5 F2 F0"+ // | 9740150000853520
                "C2 D6 D7 F0 F0 F3 F1 F0 F0 F0 F0 F0 F0 F0 F0 11"+ // | BOP003100000000.
                "C6 D4 F0 F0 F0 F4 11 91 05 F1 11 40 C3 F0 F1 F9"+ // | FM0004.j.1. C019
                "11 95 0B F0 F0 F1 F0 F0 F0 F0 F0 F0 F2 F5 F0 11"+ // | .n.001000000250.
                "B8 B8 F9 11 B8 B9 F9 F9 F9 F9 F9 F9 F9 F9 F9 11"+ // | ..9..`999999999.
                "40 C6 F1 11 F4 F2 F1 11 E4 F3 f0 f0 f0 f0 f0 f0"+ // |  F1.421.U3000000--//Digito ACCION 11 40 C6 = F1 o F0
                "F8 F5 F0 F4 F2 F0 F1 F5 F6 11 E4 F6 F0 F8 F0 F5"+ // | 850420156.U60805
                "F2 F0 F1 F3 11 91 2D F1 11 93 A1 C1 11 92 4A F0"+ // | 2013.j.1.l~P.k¢0--CANCELACION   P D7 y A C1????????
                "F0 F1 11 40 C5 F0 F0 F0 11 95 0C F0 F0 F0 F0 F0"+ // | 01. E000.n.00000
                "F0 F0 F0 F0 F0 F0 F0 F0 F0 F8 F2 F1 F4 F1 F3 11"+ // | 000000000821413.
                "C3 7B F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F0 F8 F2 F1"+ // | C#00000000000821
                "F4 F1 F3 11 F4 F3 F0 11 E4 F4 F0 F0 F0 F0 F0 F0"+ // | 413.430.U4000000
                "F0 F1 F2 F5 F3 F3 F0 F1 F3 11 C3 8B D1 E4 C1 D5"+ // | 012533013.C.JUAN
                "40 D4 C1 D9 D9 D6 D8 E4 C9 D5 7C 7C 7C 7C 7C 7C"+ // |  MARROQUIN@@@@@@
                "7C 7C 7C 7C 7C 7C 7C 7C 7C 7C 7C 7C 7C 7C 7C 7C"+ // | @@@@@@@@@@@@@@@@
                "7C 7C 7C 7C 7C 7C 7C 7C 7C 7C 7C 7C 7C 7C 11 40"+ // | @@@@@@@@@@@@@@. 
                 "C7 F0 F2 11 91 01 F0 F0 F0 F8 11 91 A1 F0 11 11");            */      // | G02.j.0008.j~0..

             
             /*
      WSRI DESINVERSION                                             DESINVERSION    CLIENTE PRUEBA 1  */  
           /*   byte bodyRequestBytes[] = 
             hexa2Bytes("00f74c53 4e32 43 4F 57 53 F0 F0 F0 F0 F0 F0 56 49 42 41 11 40"+ // | ....000000..... 
             
                "40 F0 F0 F0 F0 F2 F2 F4 F5 F5 F2 F2 F5 F7"+ //       | 2012.  224552257
                "F7 F1 F2 F0 F0 F3 F3 F0 F0 F0 F0 F9 F2 F3 F3 F5"+ // | 7120033000092335
                "C2 D6 E7 D7 F0 F1 F2 F0 F0 F0 F0 F0 F0 F0 F0 F0"+ // | BOXP012000000000
                "11 C3 F0 F8 F0 F0 F0 F0 F0 F0 F0 F0 F0 11 40 C3"+ // | .C08000000000. C
                "F0 F6 F0 11 E6 F5 11 E5 F1 F0 11 C3 5A F0 F0 F0"+ // | 060.W5.V10.C!000
                "11 95 15 F0 F0 F0 11 C3 7B F0 F8 F4 F1 F4 F3 F3"+ // | .n.000.C#0841433
                "F9 F5 11 C3 F1 F8 F0 F0 F0 F0 F0 F0 F0 F0 F0 11"+ // | 95.C18000000000.
                "E4 F2 F1 11 E4 F3 F9 F0 F0 F5 F0 F5 F0 F0 F9 F9"+ // | U21.U39005050099
                "11 91 16 F0 F0 F0 F3 F4 F4 F3 F2 F5 11 91 2D F1"+ // | .j.000344325.j.1
                "11 95 0B F0 F0 F1 F0 F0 F0 F1 F4 F0 F0 F7 F3 11"+ // | .n.001000140073.
                "C6 D4 F0 F0 F3 F3 11 40 C6 F0 11 91 A1 F1 11 93"+ // | FM0033. F0.j~1.l
                "A1 F0 11 91 01 F0 F0 F1 F2 11 11");           //        | ~0.j.0012..*/
                
                
            /*
            WSRI CORRECTORA                                             CORRECTORA F801    CLIENTE PRUEBA 1  */
          /*    byte bodyRequestBytes[] =                
             hexa2Bytes("00f74c53 4e32 43 4F 57 53 F0 F0 F0 F0 F0 F0 56 49 42 41 11 40"+ // | ....000000..... 
                "40 F2 F0 F3 F2 C6 F8 F0 F1 F7 F9 F9 F6 F2 F7 F9 "+ //  |  2032F801C071548 //F7 F9 F9 F6 F2 F7 F9 F7    //C3 F0 F7 F1 F5 F4 F8 F5
                "F7 F2 F0 F2 F0 F0 F0 F0 F0 F9 F3 F6 F5 F8 E6 F7"+ //  | 50001000093658W7
                "E2 C9 D6 D9 F0 F2 F0 F0 F0 F0 F0 F0 F0 F0 11 93"+ //  | SIOR0200000000.l
                "A1 F0 11 C4 6D F2 F1 F1 F1 F2 F0 F1 F1 11 40 C3"+ //  | ~0.D_21112011. C
                "F0 F1 F9 11 95 0B F0 F0 F1 F0 F0 F0 F0 F0 F0 F2"+ //  | 019.n.0010000002
                "F5 F0 11 B8 B8 F9 11 B8 B9 F9 F9 F9 F9 F9 F9 F9"+ //  | 50...9..`9999999
                "F9 F9 11 E4 E3 F1 11 E4 F2 F8 F5 F0 F4 F2 F0 F1"+ //  | 99.UT1.U28504201
                "F5 F2 11 C3 5A F1 F5 F0 F0 F0 F0 F0 11 C1 5B F0"+ //  | 52.C!1500000.A$0
                "F0 F0 11 C3 F0 F1 F5 F0 F0 F0 F0 F0 11 91 2D F1"+ //  | 00.C01500000.j.1
                "11 C6 D4 F1 11 91 01 F0 F0 F0 F8 11 91 A1 F1 11"+ //  | .FM1.j.0000.j~1.
                "11");                 */                         //       .                   

             /*
             WSRI CORRECTORA                                             CORRECTORA F801    MARIA ORDUZ  EXITOSA*/
             /*  byte bodyRequestBytes[] =                
               hexa2Bytes("00f74c53 4e32 43 4F 57 53 F0 F0 F0 F0 F0 F0 56 49 42 41 11 40"+ //   | ....000000..... 
            "40 F2 F0 F3 F2 C6 F8 F0 F1 C3 F0 F7 F1 F5 F4 F8"+ //   |  2032F801C071548
            "F5 F1 F2 F3 F9 F0 F4 F0 F1 F6 F5 F0 F2 F9 E6 F7"+ //   | 50008000165029W7
            "E2 C9 D6 D9 F0 F2 F0 F0 F0 F0 F0 F0 F0 F0 11 93"+ //   | SIOR0200000000.l
            "A1 F0 11 C4 6D F2 F2 F1 F1 F2 F0 F1 F1 11 40 C3"+ //   | ~0.D_18112011. C
            "F0 F1 F9 11 95 0B F0 F0 F1 F0 F0 F0 F0 F0 F0 F2"+ //   | 019.n.0010000002
            "F5 F0 11 B8 B8 F9 11 B8 B9 F9 F9 F9 F9 F9 F9 F9"+ //   | 50...9..`9999999
            "F9 F9 11 E4 E3 F1 11 E4 F2 F8 F5 F0 F4 F2 F0 F1"+ //   | 99.UT1.U28504201
            "F5 F2 11 C3 5A F2 F0 F0 F0 F0 F0 F0 F0 11 C1 5B"+ //   | 52.C!20000000.A$
            "F0 F0 F0 11 C3 F0 F2 F0 F0 F0 F0 F0 F0 F0 11 91"+ //   | 000.C020000000.j
            "2D F2 11 C6 D4 F8 11 91 01 F0 F0 F0 F8 11 91 A1"+ //   | .2.FM8.j.0000.j~
            "F1 11 11");          */                           //                  | 1..
 

             /*
             WSRI CORRECTORA                                             CORRECTORA F802    MARIA ORDUZ EXITOSA  */
               /*   byte bodyRequestBytes[] =
  hexa2Bytes("00f74c53 4e32 43 4F 57 53 F0 F0 F0 F0 F0 F0 56 49 42 41 11 40"+ //    | ....000000..... 
            "40 F2 F0 F3 F1 C6 F8 F0 F2 C3 F0 F7 F1 F5 F4 F8"+ //    |  2031F802C071548
            "F5 F0 F4 F1 F4 F0 F0 F0 F1 F6 F5 F4 F5 F9 E6 F7"+ //    | 50009000165459W7
            "E2 C9 D6 D9 F0 F2 F0 F0 F0 F0 F0 F0 F0 F0 11 93"+ //    | SIOR0200000000.l
            "A1 F0 11 C4 6D F2 F2 F1 F1 F2 F0 F1 F1 11 40 C3"+ //    | ~0.D_22112011. C
            "F0 F1 F9 11 95 0B F0 F0 F1 F0 F0 F0 F0 F0 F0 F2"+ //    | 019.n.0010000002
            "F5 F0 11 B8 B8 F9 11 B8 B9 F9 F9 F9 F9 F9 F9 F9"+ //    | 50...9..`9999999
            "F9 F9 11 E4 E3 F1 11 E4 F2 F8 F5 F0 F4 F2 F0 F1"+ //    | 99.UT1.U28504201
            "F5 F2 11 C3 F0 F1 F2 F0 F0 F0 F0 F0 11 91 2D F2"+ //    | 52.C01200000.j.2
            "11 C6 D4 F9 11 95 12 F0 11 91 01 F0 F0 F0 F8 11"+ //    | .FM9.n.0.j.0000.
            "91 A1 F1 11 11");             */                            //    | j~1..






            /*
         WSRI CORRECTORA                                             CORRECTORA F802    CLIENTE PRUEBA 1  */
         /*    byte bodyRequestBytes[] =                
       hexa2Bytes("00f74c53 4e32 43 4F 57 53 F0 F0 F0 F0 F0 F0 56 49 42 41 11 40"+ // | ....000000.....
                    "40 F2 F0 F3 F1 C6 F8 F0 F2 C3 F0 F7 F1 F5 F4 F8"+ //   |  2031F802C071548
                    "F5 F1 F0 F2 F1 F0 F4 F0 F1 F5 F5 F0 F0 F5 E6 F7"+ //   | 51001000155005W7
                    "E2 C9 D6 D9 F0 F2 F0 F0 F0 F0 F0 F0 F0 F0 11 93"+ //   | SIOR0200000000.l
                    "A1 F0 11 C4 6D F2 F5 F1 F1 F2 F0 F1 F1 11 40 C3"+ //   | ~0.D_04062013. C
                    "F0 F1 F9 11 95 0B F0 F0 F1 F0 F0 F0 F0 F0 F0 F2"+ //   | 019.n.0010000002
                    "F5 F0 11 B8 B8 F9 11 B8 B9 F9 F9 F9 F9 F9 F9 F9"+ //   | 50...9..`9999999
                    "F9 F9 11 E4 E3 F1 11 E4 F2 F8 F5 F0 F4 F2 F0 F1"+ //   | 99.UT1.U28504201
                    "F5 F6 11 C3 F0 F1 F6 F0 F0 F0 F0 F0 F0 11 91 2D"+ //   | 56.C016000000.j.
                    "F1 11 C6 D4 F1 11 95 12 F0 11 91 01 F0 F0 F0 F8"+ //   | 1.FM1.n.0.j.0000
                    "11 91 A1 F1 11 11");       */                             //                             | .j~1..
 
                     /*
                     WSRI CORRECTORA                                             CORRECTORA F802    CLIENTE PRUEBA 2  */
              /*       byte bodyRequestBytes[] =                
                     hexa2Bytes("00f74c53 4e32 43 4F 57 53 F0 F0 F0 F0 F0 F0 56 49 42 41 11 40"+ // | ....000000.....
                     "40 F2 F0 F3 F1 C6 F8 F0 F2 C3 F0 F7 F1 F5 F4 F8"+ //   |  2031F802C071548
                     "F5 F1 F0 F2 F4 F0 F0 F0 F1 F5 F5 F0 F0 F5 E6 F7"+ //   | 51001000155005W7
                     "E2 C9 D6 D9 F0 F2 F0 F0 F0 F0 F0 F0 F0 F0 11 93"+ //   | SIOR0200000000.l
                     "A1 F0 11 C4 6D F2 F1 F1 F1 F2 F0 F1 F1 11 40 C3"+ //   | ~0.D_04062013. C //fecha 21112011
                     "F0 F6 F4 11 95 0B F0 F0 F1 F0 F0 F0 F0 F0 F3 F7"+ //   | 064.n.0010000002
                     "F4 F7 11 B8 B8 F9 11 B8 B9 F9 F9 F9 F9 F9 F9 F9"+ //   | 50...9..`9999999
                     "F9 F9 11 E4 E3 F1 11 E4 F2 F8 F5 F0 F4 F2 F0 F1"+ //   | 99.UT1.U28504201
                     "F5 F6 11 C3 F0 F1 F6 F0 F0 F0 F0 F0 F0 11 91 2D"+ //   | 56.C016000000.j.
                     "F1 11 C6 D4 F1 11 95 12 F0 11 91 01 F0 F0 F0 F8"+ //   | 1.FM1.n.0.j.0000
                     "11 91 A1 F1 11 11");    */                                   //                             | .j~1..
                   
                   
                        /*      byte bodyRequestBytes[] =
                      hexa2Bytes("f0f0 f1f0f0f3 46494455 f2f0f0f2 f2f0f0f2 656565656565656565656565656565656565656565"+ // .ß001003€|ãœ .bõõôõõ?COWS0000
                             "f0f0 f1f0f0f3f0f0 f1f0f0f3f0f0 f1f0f0f3f0f0 f1f0f0f3"+ //  0020029999999999
                             "f0f0 f1f0f0f3 6e3d2231")  ; // û_%üÝ”°¬?> <?xml version=.1*/ 
                
                
                  /* WSRI CONSULTA            ****   CONCENTRADOR   ***                           CONSULTA IVR - IVCF  */  
                         /*  byte bodyRequestBytes[] =
            hexa2Bytes ("f0f0 f1f0f0f3 46494455"+ //   |  .ß001003Ÿ‹ë¥ .bõõôõõ?FIDU0000   //menos seis caracteres 000000
                            "3230 30323939 39393939 39393939"+ //   |   0020029999999999     10
                            "3c3f786d 6c207665 7273696f 6e3d2231"+ //   |  û_%üÝ”°¬?> <?xml version=.1 26
                            "2e302220 656e636f 64696e67 3d225554"+ //   |  Ý>Ü?š¬>˜¥Û .0. encoding=.UT 42
                            "462d3822 3f3e3c54 52414e53 41434349"+ //   |  ŸÛª¦+œ¦€€‹ F-8.?><TRANSACCI 58
                            "4f4e3e3c 48545843 493e5649 42413c2f"+ //   |  |+«Û¨€‹™‹á¦ ON><HTXCI>VIBA</ 74
                            "48545843 493e3c48 54585453 3e495643"+ //   |  «Û¨€‹«Û¨Ûœ‹™€ HTXCI><HTXTS>IVC 90
                            "463c2f48 54585453 3e3c4849 44434a3e"+ //   |  Ÿ«Û¨Ûœ«‹ë€› F</HTXTS><HIDCJ> 106
                            "49563030 31303033 3c2f4849 44434a3e"+ //   |  ‹™«‹ë€› IV001003</HIDCJ> 122
                            "3c485345 43553e30 3033383c 2f485345"+ //   |  «œ?€¥«œ? <HSECU>0038</HSE 138
                            "43553e3c 48425953 543e3030 303c2f48"+ //   |  €¥«ážœÛ« CU><HBYST>000</H 154
                            "42595354 3e3c4848 4f52413e 31343135"+ //   |  ážœÛ««|ª¦ BYST><HHORA>1415 170
                            "34303c2f 48484f52 413e3c49 5445523e"+ //   |  ««|ª¦‹Û?ª 40</HHORA><ITER> 186
                            "49563031 3c2f4954 45523e3c 54584643"+ //   |  ‹™‹Û?ªÛ¨Ÿ€ IV01</ITER><TXFC 202
                            "3e303732 3331333c 2f545846 433e3c48"+ //   |  Û¨Ÿ€« >072313</TXFC><H 218
                            "484f5241 3e313431 3534303c 2f48484f"+ //   |  «|ª¦««| HORA>141540</HHO 234
                            "52413e3c 454e4e4f 3e303031 38303031"+ //   |  ª¦?++| RA><ENNO>0019001    250
                            "30303030 32353738 373c2f45 4e4e4f3e"+ //   |  ?++| 000025787</ENNO>
                            "3c454e54 503e4642 3c2f454e 54503e3c"+ //   |  ?+Û&Ÿá?+Û& <ENTP>FB</ENTP><
                            "4e4f4944 3e313033 32333735 3934353c"+ //   |  +|‹ë NOID>1032375945<
                            "2f4e4f49 443e3c54 5049443e 433c2f54"+ //   |  +|‹ëÛ&‹ë€Û /NOID><TPID>C</T
                            "5049443e 3c2f5452 414e5341 4343494f"+ //   |  &‹ëÛª¦+œ¦€€‹| PID></TRANSACCIO
                            "4e3e");                                            //         +               N>*/
               
               
               /*IVR CONSULTA DIRECTO           *****   COORDINADOR   ******    RESPUESTA ERROR
                */ 
              /*  byte bodyRequestBytes[] =
                hexa2Bytes ("f0f0 f1f0f0f3 46494455 f1f2f3f4 f5f6f2f0f0f2"+ //   |   .*001003Ÿ‹ë¥2002 .\õõôõõ?FIDU123456£õõ£
                            "f9f9f9f9 f9f9f9f9 f9f94c6f a7949340"+ //   |  9999999999<?xml  ÑÑÑÑÑÑÑÑÑÑLo«@
                            "a58599a2 8996957e 7ff14bf0 7f408595"+ //   |  version=.1.0. en Ÿ.€~.ôKõ.@.
                            "83968489 95877e7f e4e3c660 f87f6f6e"+ //   |  coding=.UTF-8.?> .~.¿Ùó`ä.on
                            "4ce3d9c1 d5e2c1c3 c3c9d6d5 6e4cc8e3"+ //   |  <TRANSACCION><HT LÙ¶Ö[?ÖøøÅò[nL®Ù
                            "e7c3c96e e5c9c2c1 4c61c8e3 e7c3c96e"+ //   |  XCI>VIBA</HTXCI> ÄøÅn]ÅûÖLa®ÙÄøÅn
                            "4cc8e3e7 e3e26ec9 e5c3c64c 61c8e3e7"+ //   |  <HTXTS>IVCF</HTX L®ÙÄÙ?nÅ]øóLa®ÙÄ
                            "e3e26e4c c8c9c4c3 d16ec9e5 f0f0f1f0"+ //   |  TS><HIDCJ>IV0010 Ù?nL®Å–øÈnÅ]õõôõ
                            "f0f34c61 c8c9c4c3 d16e4cc8 e2c5c3e4"+ //   |  03</HIDCJ><HSECU õ?La®Å–øÈnL®?¤ø¿
                            "6ef0f0f0 f64c61c8 e2c5c3e4 6e4cc8c2"+ //   |  >0006</HSECU><HB nõõõŽLa®?¤ø¿nL®û
                            "e8e2e36e f0f0f04c 61c8c2e8 e2e36e4c"+ //   |  YST>000</HBYST>< Ë?ÙnõõõLa®ûË?ÙnL
                            "c8c8d6d9 c16ef1f6 f5f9f4f0 4c61c8c8"+ //   |  HHORA>165940</HH ®®ò¶ÖnôŽ…Ñ õLa®®
                            "d6d9c16e 4cc9e3c5 d96ec9e5 f0f14c61"+ //   |  ORA><ITER>IV01</ ò¶ÖnLÅÙ¤¶nÅ]õôLa
                            "c9e3c5d9 6e4ce3e7 c6c36ef0 f7f2f5f1"+ //   |  ITER><TXFC>07251 ÅÙ¤¶nLÙÄóønõé£…ô
                            "f34c61e3 e7c6c36e 4cc8c8d6 d9c16ef1"+ //   |  3</TXFC><HHORA>1 ?LaÙÄóønL®®ò¶Önô
                            "f6f5f9f4 f04c61c8 c8d6d9c1 6e4cc5d5"+ //   |  65940</HHORA><EN Ž…Ñ õLa®®ò¶ÖnL¤[
                            "d5d66ef0 f0f6f4f0 f0f1f0f0 f0f0f0f0"+ //   |  NO>0064001000000 [ònõõŽ õõôõõõõõõ
                            "f1f1f74c 61c5d5d5 d66e4cc5 d5e3d76e"+ //   |  117</ENNO><ENTP> ôôéLa¤[[ònL¤[Ùùn
                            "c6c24c61 c5d5e3d7 6e4cd5d6 c9c46ef1"+ //   |  FB</ENTP><NOID>1 óûLa¤[ÙùnL[òÅ–nô
                            "f0f3f2f3 f7f5f9f4 f54c61d5 d6c9c46e"+ //   |  032375945</NOID> õ?£?é…Ñ …La[òÅ–n
                            "4ce3d7c9 c46ec34c 61e3d7c9 c46e4c61"+ //   |  <TPID>C</TPID></ LÙùÅ–nøLaÙùÅ–nLa
                            "e3d9c1d5 e2c1c3c3 c9d6d56e");      //          TRANSACCION>     Ù¶Ö[?ÖøøÅò[n*/

               /*IVR CONSULTA DIRECTO           *****   COORDINADOR   ******        RESPUESTA EXITOSA
                */ 
              /*  byte bodyRequestBytes[] =
                hexa2Bytes ("f0f0 f1f0f0f3 46494455 f1f2f3f4 f5f6f2f0f0f2"+ //   |   .*001003Ÿ‹ë¥2002 .\õõôõõ?FIDU123456£õõ£
                            "f9f9f9f9 f9f9f9f9 f9f94c6f a7949340"+ //   |  9999999999<?xml  ÑÑÑÑÑÑÑÑÑÑLo«@
                            "a58599a2 8996957e 7ff14bf0 7f408595"+ //   |  version=.1.0. en Ÿ.€~.ôKõ.@.
                            "83968489 95877e7f e4e3c660 f87f6f6e"+ //   |  coding=.UTF-8.?> .~.¿Ùó`ä.on
                            "4ce3d9c1 d5e2c1c3 c3c9d6d5 6e4cc8e3"+ //   |  <TRANSACCION><HT LÙ¶Ö[?ÖøøÅò[nL®Ù
                            "e7c3c96e e5c9c2c1 4c61c8e3 e7c3c96e"+ //   |  XCI>VIBA</HTXCI> ÄøÅn]ÅûÖLa®ÙÄøÅn
                            "4cc8e3e7 e3e26ec9 e5c3c64c 61c8e3e7"+ //   |  <HTXTS>IVCF</HTX L®ÙÄÙ?nÅ]øóLa®ÙÄ
                            "e3e26e4c c8c9c4c3 d16ec9e5 f0f0f1f0"+ //   |  TS><HIDCJ>IV0010 Ù?nL®Å–øÈnÅ]õõôõ
                            "f0f34c61 c8c9c4c3 d16e4cc8 e2c5c3e4"+ //   |  03</HIDCJ><HSECU õ?La®Å–øÈnL®?¤ø¿
                            "6ef0f0f0 f64c61c8 e2c5c3e4 6e4cc8c2"+ //   |  >0006</HSECU><HB nõõõŽLa®?¤ø¿nL®û
                            "e8e2e36e f0f0f04c 61c8c2e8 e2e36e4c"+ //   |  YST>000</HBYST>< Ë?ÙnõõõLa®ûË?ÙnL
                            "c8c8d6d9 c16ef1f6 f5f9f4f0 4c61c8c8"+ //   |  HHORA>165940</HH ®®ò¶ÖnôŽ…Ñ õLa®®
                            "d6d9c16e 4cc9e3c5 d96ec9e5 f0f14c61"+ //   |  ORA><ITER>IV01</ ò¶ÖnLÅÙ¤¶nÅ]õôLa
                            "c9e3c5d9 6e4ce3e7 c6c36ef0 f7f2f5f1"+ //   |  ITER><TXFC>07251 ÅÙ¤¶nLÙÄóønõé£…ô
                            "f34c61e3 e7c6c36e 4cc8c8d6 d9c16ef1"+ //   |  3</TXFC><HHORA>1 ?LaÙÄóønL®®ò¶Önô
                            "f6f5f9f4 f04c61c8 c8d6d9c1 6e4cc5d5"+ //   |  65940</HHORA><EN Ž…Ñ õLa®®ò¶ÖnL¤[
                            "d5d66ef0 f0f6f0f0 f0f1f0f0 f0f0f0f1"+ //   |  NO>0060001000001 [ònõõŽ õõôõõõõõõ
                            "f4f4f64c 61c5d5d5 d66e4cc5 d5e3d76e"+ //   |  446</ENNO><ENTP> ôôéLa¤[[ònL¤[Ùùn
                            "c6c24c61 c5d5e3d7 6e4cd5d6 c9c46ef1"+ //   |  FB</ENTP><NOID>1 óûLa¤[ÙùnL[òÅ–nô
                            "f0f3f2f3 f7f5f9f4 f54c61d5 d6c9c46e"+ //   |  032375945</NOID> õ?£?é…Ñ …La[òÅ–n
                            "4ce3d7c9 c46ec34c 61e3d7c9 c46e4c61"+ //   |  <TPID>C</TPID></ LÙùÅ–nøLaÙùÅ–nLa
                             "e3d9c1d5 e2c1c3c3 c9d6d56e");        //          TRANSACCION>     Ù¶Ö[?ÖøøÅò[n*/
          
/*VOTP*/
                /*   byte bodyRequestBytes[] =
                   hexa2Bytes ( "f0f0 f1f0f0f3 e5d6e3d7 30303130"+
                    "3c3f786d 6c207665 7273696f 6e3d2231"+
                    "2e302220 656e636f 64696e67 3d225554"+
                    "462d3822 3f3e3c54 52414e53 41434349"+
                    "4f4e3e3c 42425f48 44525f43 4f445f54"+
                    "585f5453 3e495656 543c2f42 425f4844"+
                    "525f434f 445f5458 5f54533e 3c544354"+
                    "443e393c 2f544354 443e3c4e 4354443e"+
                    "39393939 39393939 383c2f4e 4354443e"+
                    "3c42414e 434f3e30 30303130 3031363c"+
                    "2f42414e 434f3e3c 43414e41 4c3e4956"+
                    "523c2f43 414e414c 3e3c5450 49443e43"+
                    "3c2f5450 49443e3c 4e4f4944 3e303030"+
                    "30303036 35393835 3c2f4e4f 49443e3c"+
                    "4e4f5450 3e313233 3435363c 2f4e4f54"+
                    "503e3c2f 5452414e 53414343 494f4e3e");*/
               
                   /* WSRI DESINVERSION                                             DESINVERSION IVR -  IVTE  OK OK    */
                          /*    byte bodyRequestBytes[] =
                    hexa2Bytes(  "f0f0 f1f0f0f2 46494455 f0f0f0f0"+ //   |   .X001002Ÿ‹ë¥0000 
                                "f0f0f2f0 f1f3f9f9 f9f9f9f9 f9f9f9f9"+ //   |   0020139999999999 
                                "4c6fa794 9340a585 99a28996 957e7ff1"+ //   |   <?xml version=.1 
                                "4bf07f40 85958396 84899587 7e7fe4e3"+ //   |   .0. encoding=.UT 
                                "c660f87f 6f6e4ce3 d9c1d5e2 c1c3c3c9"+ //   |   F-8.?><TRANSACCI 
                                "d6d56e4c c8e3e7c3 c96ee5c9 c2c14c61"+ //   |   ON><HTXCI>VIBA</ 
                                "c8e3e7c3 c96e4cc8 e3e7e3e2 6ec9e5e3"+ //   |   HTXCI><HTXTS>IVT 
                                "c54c61c8 e3e7e3e2 6e4cc8c9 c4c3d16e"+ //   |   E</HTXTS><HIDCJ> 
                                "c9e5f0f0 f1f0f0f2 4c61c8c9 c4c3d16e"+ //   |   IV001002</HIDCJ> 
                                "4cc8e2c5 c3e46ef1 f8f9f95c 61c8e2c5"+ //   |   <HSECU>1193</HSE /1898
                                "c3e46e4c c8c2e8e2 e36ef0f0 f04c61c8"+ //   |   CU><HBYST>000</H 
                                "c2e8e2e3 6e4cc8c8 d6d9c16e f1f7f4f3"+ //   |   BYST><HHORA>1743 
                                "f1f14c61 c8c8d6d9 c16e4cc9 e3c5d96e"+ //   |   11</HHORA><ITER> 
                                "c9e5f0f1 4c61c9e3 c5d96e4c e3e7c6c3"+ //   |   IV01</ITER><TXFC 
                                "6ef0f9f1 f8f1f34c 61e3e7c6 c36e4cc8"+ //   |   >091813</TXFC><H 
                                "c8d6d9c1 6ef1f7f4 f3f1f14c 61c8c8d6"+ //   |   HORA>174311</HHO 
                                "d9c16e4c e3c5e3c4 6ec6c24c 61e3c5e3"+ //   |   RA><TETD>FB</TET 
                                "c46e4ce3 c5e3c36e c6c24c61 e3c5e3c3"+ //   |   D><TETC>FB</TETC 
                                "6e4ce5d3 e3e76ef0 f0f0f0f0 f0f0f0f0"+ //   |   ><VLTX>000000000 
                                "f8f0f6f5 f0f04c61 e5d3e3e7 6e4ce3c5"+ //   |   806500</VLTX><TE 
                                "d5c36ef0 f0f1f9f0 f0f1f0f0 f0f0f2f5"+ //   |   NC>0019001000025 
                                "f3f2f24c 61e3c5d5 c36e4ce3 c5d5c46e"+ //   |   322</TENC><TEND> ?
                                "f0f0f1f9 f0f0f1f0 f0f0f0f3 f9f9f0f5"+ //   |   0019001000039905 
                                "4c61e3c5 d5c46e4c d5d6c9c4 6e404040"+ //   |   </TEND><NOID>    
                                "f5f2f7f6 f2f0f1f6 4c61d5d6 c9c46e4c"+ //   |   52762016</NOID>< 
                                "e3d7c9c4 6ec34c61 e3d7c9c4 6e4ce3c5"+ //   |   TPID>C</TPID><TE 
                                "e3c26ef0 f1f94c61 e3c5e3c2 6e4cd5c5"+ //   |   TB>019</TETB><NE 
                                "c4d76ef0 4c61d5c5 c4d76e4c d5c5c3d7"+ //   |   DP>0</NEDP><NECP 
                                "6ef04c61 d5c5c3d7 6e4ce3c5 c3d96ef0"+ //   |   >0</NECP><TECR>0 
                                "f1f94c61 e3c5c3d9 6e4c61e3 d9c1d5e2"+ //   |   19</TECR></TRANS 
                                "c1c3c3c9 d6d56e");          //                   ACCION>          
          

                    
        
                                 /* WSRI DESINVERSION                                             DESINVERSION IVR -  IVTE      */
                                  /*        byte bodyRequestBytes[] =
                       hexa2Bytes("f0f0 f1f0f0f1 46494455 f0f0f0f0"+  // .\001001Ÿ‹ë¥0000         
                         "f0f0f2f0 f1f3f9f9 f9f9f9f9 f9f9f9f9"+  // 0020139999999999       10
                         "4c6fa794 9340a585 99a28996 957e7ff1"+  // <?xml version=.1       26
                         "4bf07f40 85958396 84899587 7e7fe4e3"+  // .0. encoding=.UT       42
                         "c660f87f 6f6e4ce3 d9c1d5e2 c1c3c3c9"+  // F-8.?><TRANSACCI       58
                         "d6d56e4c c8e3e7c3 c96ee5c9 c2c14c61"+  // ON><HTXCI>VIBA</       74
                         "c8e3e7c3 c96e4cc8 e3e7e3e2 6ec9e5e3"+  // HTXCI><HTXTS>IVT       90
                         "c54c61c8 e3e7e3e2 6e4cc8c9 c4c3d16e"+  // E</HTXTS><HIDCJ>       106
                         "c9e5f0f0 f1f0f0f1 4c61c8c9 c4c3d16e"+  // IV001001</HIDCJ>       122
                         "4cc8e2c5 c3e46ef0 f2f4f04c 61c8e2c5"+  // <HSECU>0240</HSE       138
                         "c3e46e4c c8c2e8e2 e36ef0f0 f04c61c8"+  // CU><HBYST>000</H       154
                         "c2e8e2e3 6e4cc8c8 d6d9c16e f2f0f1f7"+  // BYST><HHORA>2017       170
                         "f3f64c61 c8c8d6d9 c16e4cc9 e3c5d96e"+  // 36</HHORA><ITER> ?     186
                         "c9e5f0f1 4c61c9e3 c5d96e4c e3e7c6c3"+  // IV01</ITER><TXFC       202
                         "6ef0f8f0 f6f1f34c 61e3e7c6 c36e4cc8"+  // >080613</TXFC><H       218
                         "c8d6d9c1 6ef2f0f1 f7f3f64c 61c8c8d6"+  // HORA>201736</HHO       234
                         "d9c16e4c e3c5e3c4 6ec6c24c 61e3c5e3"+  // RA><TETD>FB</TET       250
                         "c46e4ce3 c5e3c36e c6c24c61 e3c5e3c3"+  // D><TETC>FB</TETC       266
                         "6e4ce5d3 e3e76ef3 f2f0f0f0 f0f0f04c"+  // ><VLTX>32000000<       282  OJO long 15??
                         "61e5d3e3 e76e4ce3 c5d5c36e f0f0f1f9"+  // /VLTX><TENC>0019       298
                         "f0f0f1f0 f0f0f0f0 f2f1f7f8 4c61e3c5"+  // 001000002178</TE       314
                         "d5c36e4c e3c5d5c4 6ef0f0f6 f4f0f0f1"+  // NC><TEND>0064001       330
                         "f0f0f0f0 f2f2f6f8 f24c61e3 c5d5c46e"+  // 000022682</TEND>       346
                         "4cd5d6c9 c46e4040 40f5f2f7 f6f2f0f1"+  // <NOID>   5276201       362
                         "f64c61d5 d6c9c46e 4ce3d7c9 c46ec34c"+  // 6</NOID><TPID>C<       378
                         "61e3d7c9 c46e4ce3 c5e3c26e f0f6f44c"+  // /TPID><TETB>064<       394
                         "61e3c5e3 c26e4cd5 c5c4d76e f04c61d5"+  // /TETB><NEDP>0</N       410
                         "c5c4d76e 4cd5c5c3 d76ef04c 61d5c5c3"+  // EDP><NECP>0</NEC       426
                         "d76e4ce3 c5c3d96e f0f1f94c 61e3c5c3"+  // P><TECR>019</TEC       442
                         "d96e4c61 e3d9c1d5 e2c1c3c3 c9d6d56e");  // R></TRANSACCION> 

                         /* WSRI INVERSION                                             INVERSION IVR -  IVTE      */
          /*                      byte bodyRequestBytes[] =
            hexa2Bytes ( */             
                
                    //hexa2Bytes("            00f74c53 4e32434F 5753F0F0 F0F0F0F0 56494241 1140"+
                
               /* INVERSION BBS                                             INVERSION BBS*/ 
/* byte bodyRequestBytes[] =
            hexa2Bytes( "0816264c 534e434f 5753 f0f0 f0f0f0f0 56494241 114040f2"+ //   ..<œ+ÛÛ
                        "f0f1f4f0 f2f2f140 404040f1 f2f3f4f0"+ //   BFB0221    12340 
                        "f0f0f0f8 f4f2f2f2 40404040 40404040"+ //   00084222         
                        "f0f0f0f0 f0f0f0f0"    +  
                         
                         "11 b8b8f911 b8b9f9f9 f9f9f9f9 f9f9f9"+     // ..   
                        
                        "119149c2 c2f0f111"+ //   00000000.j‹BB01. 
                        "c46df0f6 f1f2f1f3 1140c3f0 f6f21191"+ //   D_061213. C060.j 
                        "2df011c3 f0f0f0f0 f0f0f0f0 f0f0f0f0"+ //   0.C00000000000
                        "f0f1f0f0 11914af0 f8f4f2f2 f211c37b"+ //   0100.j›084222.C# 
                        "f0f0f0f9 f1f9f3f6 f511e4 f3f1f1f0"+ //   0033961483.U3960 
                        "f9f6f6f7 f4f5f811 e4f2d911 950bf0f0"+ //   5205202.U2R.n.01 
                        "f1f0f0f0 f0f0f1f4 f4f61111 40404040"+ //   2563214521..     
                         "40404040 40404040 40404040 40404040");        //                    


 // c2 c2c3c6  sustituye por f2f0f1f4    //BBCF por 2014      y c2 c2c6c2
//  54544242                        56494241 // TTBB por VIBA                        
                         
                         
                         /*CONSULTA BBS ??????????????????*/
                        
                           
                        /*  byte bodyRequestBytes[] =
                                       hexa2Bytes( "4c534e31 3236434f 5753f0f0 f0f0f0f0"+//
                                                "56494241"+ 
                                                
                                                "114040f2 f0f1f4c2 c2c3c6 f0 f2f2f140"+ //  ÛÛáá.  20140221 
                                                "404040f1 f2f3f4f0 f0f0f0f8 f4f2f2f2"+ //     1234000084222
                                                "40404040 40404040 f0f0f0f0 f0f0f0f0"+ //          00000000
                                                
                                                 "11 b8b8f911 b8b9f9f9 f9f9f9f9 f9f9f9"+     // ..   
                                                
                                                "119149c2 c2f0f111 c46df0f6 f1f2f1f3"+ //  .j‹BB01.D_061213
                                                "11d179c6 1140c3f0 11c5d2f0 11c3f0f0"+ //  .J`F. C0.EK0.C00
                                                "f0f0f0f0 f0f0f0f0 f0f0f0f1 f0f01191"+ //  00000000000100.j
                                                "4af0f8f4 f2f2f211 c37bf0f0 f0f0f0f0"+ //  ›084222.C#000000
                                                "f0f0f0f0 114040f0 f0f3f3f9 f6f1f4f8"+ //  0000.  003396148
                                                "f311e4f3 f9f6f0f5 f2f0f5f2 f0f211e4"+ //  3.U39605205202.U
                                                "f2d51195 0bf0f0f0 f0f0f0f0 f0f0f1f2"+ //  2N.n.00000000012
                                                "f5f6f3f2 f1f4f5f2 f11140c4 f0f6f040"+ //  563214521. D060 
                                                "40404040 40404040 40404040 40404040") ;*/
                           
                           
                        
                         
                         
                         /*
             WSRI DESINVERSION CANALES EFECTIVO                                            2245    SYDDAR  EXITOSA*/
            /*   byte bodyRequestBytes[] =                
               hexa2Bytes("00f74c53 4e32 46 49 44 55 F0 F0 F0 F0 F0 F0 56 49 42 41 11 40"+ //   | ....000000..... 
                            "40 F2 F0 F1 F2 F2 F2 F4 F5 F7 F9 F9 F6 F2 F7 F9"+ //   |  201222457996279
                            "F7 F5 F8 F9 F6 F0 F0 F0 F1 F4 F1 F9 F2 F4 E6 F7"+ //   | 70020000141924W7
                            "E2 C9 C7 C4 F5 F4 F0 F0 F0 F0 F0 F0 F0 F0 11 C3"+ //   | SIGD5400000000.C
                            "F0 F0 F0 F0 11 40 C3 F0 F1 F9 11 E6 F5 11 E5 F1"+ //   | 0000. C019.W5.V1
                            "F0 11 C3 5A F0 F0 F0 F0 F2 F0 F0 F0 11 95 15 F0"+ //   | 0.C!20000000.n.0
                            "F0 F0 11 B8 B8 F9 11 B8 B9 F9 F9 F9 F9 F9 F9 F9"+ //   | 00...9..`9999999
                            "F9 F9 11 C3 F1 F0 F0 F0 F0 F2 F0 F0 F0 11 E4 F2"+ //   | 99.C120000000.U2
                            "F2 11 E4 F3 F9 F9 F0 F5 F0 F6 F1 F4 F3 F6 F8 11"+ //   | 2.U399050614368.
                            "91 16 F0 F0 F0 F3 F5 F5 F3 F6 F5 11 95 0B F0 F0"+ //   | j.000355365.n.00
                            "F1 F0 F0 F0 F0 F0 F5 F9 F7 F8 11 C6 D4 F0 F0 F2"+ //   | 1000005978.FM002
                            "F0 11 40 C6 F0 11 91 A1 F1 11 93 A1 F0 11 91 01"+ //   | 0. F0.j~1.l~0.j.
                            "F0 F0 F4 F0 11 11");    */                        //                               | 0040..

                        
                                       

// c2 c2c3c6  sustituye por f2f0f1f4    //BBCF por 2014
//  54544242                        56494241 // TTBB por VIBA
 

                   
                   
        /*2165 *    Pago Recaudos WS 
         *  VALOR: c3f0
         *  RECIBO: 9249
         *  NUMERO CUENTA: 4040
         * 
         * */
          
          /*PECO DE PPE*/ 
          byte bodyRequestBytes[] =                                 // CONSULTA PECO 31032014
          hexa2Bytes(
        		   "4c53"+
        		   "00833635 34333231 e5c9c2c1 114040d7"+
        		   "c5c3d6d7 d7c5f0f0 f0f3f1f0 f0f0f4f0"+
        		   "f0f0f1f4 f0f2f0f2 f0f0f0f0 f0f0f0f0"+
        		   "f0f0f0f0 f0f0f0f0 119149d7 c5d6f111"+
        		   "c46df0f5 f0f7f1f3 11914af1 f4f0f2f0"+
        		   "f21140c3 f111c37b f0f0f4f7 f3f9f3f0"+
        		   "f7f911b2 c3f2f0f1 f3f0f5f0 f7f3f0f2"+
        		   "c5c1f8f0 f0f0f4c3 d7404040 40404040"+
        		   "404040                             "); 
          
          
        byte bodyRequestBytesPago[] = 
            hexa2Bytes("4c53 4e303036 e3e3c3c1 114040f2" + 
        "f1f6f5f1 f9f2f4f3 f6f7f1f0 f0f8f0f0" + 
        "f0f0f0f9 f5f0f1f9 c3c1d5c1 d3c5e2f1" + 
        "f0f0f0f0 f01193c1 f011c15b f0f0f011" +
        "911df011 9192f0f0 f011c3f0 f0f0f0f0" +
        "f0f1f9f0" + 
        "f0f01192 c7f0f211 4040f4f3 f4f6f2f9" +
        "f2f3f411 c3f5f0f0 f0119616 f2f1f6f0" +
        "119249f0 f0f0f0f0 f0f0f0f0 f0f0f0f0" +
        "f0f0f0f0 f0f2f4f0 f0f0f4f5 f8f1f7f2" +
        "f81192e2 f0f0f0f0 f0f0f0f0 f0f0f0f0" +
        "f0f0f0f0 f0f0f0f0 f0f0f0f0 f0f0f0f0" +
        "f0f0f211 93b4f0f0 f0f0f0f0 f0f0f0f0" +
        "f0f0f0f0 f0f0f0f0 f0f0f0f0 f0f0f0f0" +
        "f0f0f0f0 1193b5f0 f0f0f0f0 f0f0f0f0" +
        "f0f0f0f0 f0f0f0f0 f0f0f0f0 f0f0f0f0" +
        "f0f0f0f0 f093b6f0 f0f0f0f0 f0f0f0" +
        "f0f0f0f0 f0f0f0f0 f0f0f0f0 1193b7f1" +
        "f8f41193 b8f0f0f0 f0f0f0f0 f0f0f0f0" +
        "f0f0f0f0 f0f0f0f0 1193b9f0 f0f0f0f0" +
        "f0f0f0f0 f0f0f0f0 f0f0f0f0 f0f0f0f0" +
        "1193b0f0 1193baf0 f211c6d4 f0f0f0f8" +
        "f0119201" +
        "f1f8f411 c3f3f0f0 f0f011c3 f4f0f0f0" +
        "f0119105" +
        "f0f0f0f0 11c37bf0" +
        "f0f0f0f0 f0f0f0f0 11c3f2f0 f0f01193" +
        "c4f01140 d5d51191 01f0f0f0 1193a1f1" +
        "11b2c3f2 f0f0f8f1 f0f2f9f1 f7f3f1f1" +
        "f1f1f9f2 f1f6f1f0 f011a2d1 f0f0f0f0" +
        "f0f0f0f0 f1f1f0f0 f2f0f511 a2d2f111" +
        "a2d4f0f0 f0f3f4f3 f4f3f411 b2c3f0f1" +
        "f6f1f0f0");


        /* 0 con encabezado (2164)*/ 
        // byte TotalRequestBytes[] = null;  2200 0636 4115  4877833 cuenta 434629234
        int TotalRequestBytesLenght = 0;
        if (operacion.equalsIgnoreCase("consulta")) {
            TotalRequestBytesLenght = 
                    headerRequestBytes.length + bodyRequestBytes.length;
        } else {
            TotalRequestBytesLenght = 
                    headerRequestBytes.length + bodyRequestBytesPago.length;
        }

        byte TotalRequestBytes[] = new byte[TotalRequestBytesLenght];


        if (operacion.equalsIgnoreCase("consulta")) {
            System.arraycopy(headerRequestBytes, 0, TotalRequestBytes, 0, 
                             headerRequestBytes.length);
            System.arraycopy(bodyRequestBytes, 0, TotalRequestBytes, 
                             headerRequestBytes.length, 
                             bodyRequestBytes.length);

        } else {
            System.arraycopy(headerRequestBytes, 0, TotalRequestBytes, 0, 
                             headerRequestBytes.length);
            System.arraycopy(bodyRequestBytesPago, 0, TotalRequestBytes, 
                             headerRequestBytes.length, 
                             bodyRequestBytesPago.length);

        }

        /* Transacciones sin encabezado 2121, 2161*/
        /*byte TotalRequestBytes[] = new byte[bodyRequestBytes.length ];
        System.arraycopy(bodyRequestBytes,0,TotalRequestBytes,0,bodyRequestBytes.length);*/

        try {
            dos.writeShort((short)TotalRequestBytes.length +   2); //(Comentario para Monetarias 2161)
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
            System.out.println("Respuesta:" + 
                               new String(responseBytes, "cp037"));
        } catch (Exception e) {
            System.out.println("Error= " + e);
        }


    }


    public void desconectar() {
        try {
            dis.close(); 
            dos.close(); 
            s.close();  
            System.out.println("Desconectado:");
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
            baos.write((0x00ff & 
                        Integer.parseInt(strHexa.substring(i, i + 2), 16)));
        }
        return baos.toByteArray();
    }
}

