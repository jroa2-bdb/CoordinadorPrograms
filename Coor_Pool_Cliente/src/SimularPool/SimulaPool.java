package SimularPool;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

import java.net.ServerSocket;
import java.net.Socket;


public class SimulaPool {

    public static void main(String[] args) {
        //Puerto Pool de pruebas
        final int PUERTO = 5002;
        ServerSocket sc;

        Socket so;

        DataOutputStream salida;

        String mensajeRecibido;
        DataInputStream entrada;
        try {

            sc = new ServerSocket(PUERTO); /* crea socket servidor que escuchara en puerto 5000*/
            so = new Socket();
            boolean flag = true;
            System.out.println("Esperando una conexion:");
            while(true){
                so = sc.accept();
                HilosTransacciones ht=new HilosTransacciones(so);
                ht.start();
            }
            
            //Inicia el socket, ahora esta esperando una conexi�n por parte del cliente

            /*System.out.println("Un cliente se ha conectado.");
            //Canales de entrada y salida de datos
            entrada =
                    new DataInputStream(so.getInputStream());
            salida = new DataOutputStream(so.getOutputStream());
            System.out.println("Confirmando conexion al cliente....");
            //salida.writeUTF("Conexi�n exitosa...n envia un mensaje :D");

            //Recepcion de mensaje

            //mensajeRecibido = entrada.readShort();
            System.out.println(entrada.readShort());
            salida.writeUTF("Se recibio tu mensaje.n Terminando conexion...");
            salida.writeUTF("Gracias por conectarte, adios!");*/
            
            /*while (true){
                mensajeRecibido = entrada.readLine();
                System.out.println(mensajeRecibido);
                salida.writeUTF(mensajeRecibido);
                salida.flush();
                
            }*/

            //System.out.println("Cerrando conexi�n...");
            //sc.close(); //Aqui se cierra la conexi�n con el cliente


        } catch (Exception e)

        {
            System.out.println("Error: " + e.getMessage());
        }
        
        /* try{
        System.out.println("Iniciando Cliente");
        ServerSocket ss = new ServerSocket(5000);
        Socket s = new Socket();
               s = ss.accept();
        System.out.println("Recibio Conexion");
        DataOutputStream output= new DataOutputStream(s.getOutputStream());
        DataInputStream intput = new DataInputStream(s.getInputStream());
        short size = 0;
        byte[] tramaRec;
        String strEnv = "Fernando Neira";
        byte[] tramaEnv = strEnv.getBytes();
        while(true){
            size = intput.readShort();
            System.out.println("Tamaño " + size);
            tramaRec = new byte[size-2];
            intput.readFully(tramaRec);
            System.out.println(new String(tramaRec));
            output.writeShort(tramaEnv.length+2);
            output.write(tramaEnv);
            output.flush();
        }

        }
        catch(Exception ex){
        ex.printStackTrace();
        } */
    }


}


