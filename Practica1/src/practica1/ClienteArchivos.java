/* Práctica 1: Construcción de un servicio de transferencia de archivos utilizando sockets orientados a conexión.
 * Aranza Miranda Montellano
 * 3CM3
 * Aplicaciones para Comunicaciones en Red
 */
package practica1;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.*;
/**
 *
 * @author chiky
 */
public class ClienteArchivos {
    Socket cliente;
    InputStream entrada;
    OutputStream salida;
    DataInputStream datoentrada;
    DataOutputStream datossalida;
    // manejo de archivos
    File file;
    
    FileInputStream entradaarchivo;
    
    public ClienteArchivos(String ip, int port){
        try{
            cliente = new Socket(ip, port);
            entrada = cliente.getInputStream();
            salida = cliente.getOutputStream();
            datoentrada = new DataInputStream(entrada);
            datossalida = new DataOutputStream(salida);
            // PARA ENVIAR ARCHIVOS
            // 1.- LEER EL ARCHIVO A ENVIAR
            // 2. - ENVIAR EL ARCHIVO
            file = new File("C:\\Users\\chiky\\OneDrive\\Documentos\\NetBeansProjects\\Practica1\\archivosSubidos\\prueba.txt");
            entradaarchivo = new FileInputStream(file);
            
            long tam= file.length();// para conocer el tamaño del archivo
            // Enviar el tamaño del archivo al servidor
             int o = (int)tam;//esta variable almacena el casteo del tamaño del archivo
            datossalida.writeLong(tam);
            byte buffer[]= new byte[o];
            int bytes = 0;// lee desde el archivo
            while( (bytes = entradaarchivo.read(buffer)) != -1){// lectura del archivo mientras que la cantidad de bytes leidos no sea -1
                datossalida.write(buffer, 0, bytes);
                datossalida.flush();
            }
            entradaarchivo.close();

            cliente.close();
            
        }catch(UnknownHostException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String args[]){
        ClienteArchivos ca = new ClienteArchivos("127.0.0.1",1028);
    }
}


