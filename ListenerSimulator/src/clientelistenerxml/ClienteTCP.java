package clientelistenerxml;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.net.Socket;

public class ClienteTCP {
	private static String host = "10.85.140.5";
	private static int port = 11009;

	// private static int port = 47067 ;

	public static void main(String[] argv) throws Exception {
		try {
			Socket s = new Socket(host, port);
			procesoCliente(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final void procesoCliente( Socket s ) throws Exception
   {
      final DataInputStream dis = new DataInputStream( s.getInputStream() );
      final DataOutputStream dos = new DataOutputStream( s.getOutputStream() );

      /*for( int i = 0; i < 100; i++ )
		{*/
         //String strToWrite = ".+LSN137AOFCI040000000008787884105279267440";
         //String strToWrite = "FACFAFAFAFISO0234000500200A23A000128A084000080000000000000211000091621323815006116323809160916110000000005237000001000000000040566234D            34560080001512670052000000000008AVENIDA 19        11001BOGOTA       170045000000001233300000000000000000000000001233300000000";
      	 String strToWrite = "E5C9C2C1114040D7C5C3D6D7D7C5F0F0F0F3F1F0F0F0F4F0F0F0F1F4F0F2F0F2F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0119149D7C5D6F111C46DF0F5F0F7F1F311914AF1F4F0F2F0F21140C3F111C37BF0F0F4F7F3F9F3F0F7F911B2C3F2F0F1F3F0F5F0F7F3F0F2C5C1F8F0F0F0F4C3D740404040404040404040"; 
         short sSize = ( short )strToWrite.length();
         dos.writeShort( sSize + 2 );
         dos.write( strToWrite.getBytes() );
         System.out.println( "ESCRITO:[" + strToWrite + "]" );
         dos.flush();
         sSize = dis.readShort();
         byte vecbData[] = new byte[ sSize - 2 ];
         dis.readFully( vecbData );
         System.out.println( "Respuesta capturada:[" + new String( vecbData ) + 
               "]" );
         try
         {
            Thread.sleep( 100 );
         }
         catch( InterruptedException e )
         {
            ;
         }
      //}

      dos.close();
      dis.close();
      System.out.println( "FINALIZA PRUEBA" );
   }
}
