package SimularPool;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.Socket;

import javax.xml.crypto.Data;

public class HilosTransacciones extends Thread {
    Socket so;
    public HilosTransacciones(Socket so) {
        super();
        this.so=so;
    }
    
    public void run ()
    {
        System.out.println("Un cliente se ha conectado.");
        //Canales de entrada y salida de datos
        try {
            DataOutputStream output= new DataOutputStream(so.getOutputStream());
            DataInputStream intput = new DataInputStream(so.getInputStream());
            short size = 0;
            byte[] tramaRec;
            String strEnv = "Respuesta prueba transaccion";
            byte[] tramaEnv = strEnv.getBytes();
            while(true){
                size = intput.readShort();
                System.out.println("Tamaño: " + size +" of: "+ so.getPort());
                tramaRec = new byte[size-2];
                intput.readFully(tramaRec);
                System.out.println(new String(tramaRec));
                output.writeShort(tramaEnv.length+2);
                output.write(tramaEnv);
                output.flush();
            }
        //salida.writeUTF("Gracias por conectarte, adios!");
        } catch (IOException e) {
        e.printStackTrace();
        }
    }
}