package page6;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args){
        Socket socket = null;
        try {
            socket = new Socket("localhost",8081);//创建Socket, 请求服务端
            System.out.println("客户端已经连接上");
            while(true){
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintStream ps = new PrintStream(socket.getOutputStream());
                Scanner scanner = new Scanner(System.in);   // 创建Scanner
                System.out.println("请输入一个字符串6:");    // 给出提示输入
                String line = scanner.nextLine();     // 从键盘输入读取一行
                ps.println(line);//发送到服务端
                ps.flush();
                if((socket.getInputStream().available())==0)
                    System.out.println(br.readLine());// 读取服务端发回的字符串, 打印
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally{
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
