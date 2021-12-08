import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class OtherServer {


    public static void main(String[] args) throws Exception {
        System.out.println(isPortUsing("127.0.0.1", 8852));
        ServerSocket serverSocket = new ServerSocket(8852);
        System.out.println("Server 等待接收数据~");
        while (true) {
            Socket socket = serverSocket.accept(); //这里来一个连接请求就让deal去处理，自己在这里等下一个连接请求
            deal(socket);
        }
    }

    public static boolean isPortUsing(String host, int port) throws UnknownHostException {
        boolean flag = false;
        InetAddress theAddress = InetAddress.getByName(host);
        try {
            Socket socket = new Socket(theAddress, port);
            flag = true;
        } catch (IOException e) {
            //如果所测试端口号没有被占用，那么会抛出异常，这里利用这个机制来判断
            //所以，这里在捕获异常后，什么也不用做
        }
        return flag;
    }

    public static void deal(Socket client) { //这里创建了一个新的线程
        new Thread(new Runnable() {

            @Override
            public void run() {
                DataInputStream dataInputStream = null;
                DataOutputStream dataOutputStream = null;
                String receiveFromClient = "";
                try {
                    dataInputStream = new DataInputStream(client.getInputStream());
                    dataOutputStream = new DataOutputStream(client.getOutputStream());
                    //只是这个线程中一直while循环，不影响其他的线程。
                    while (true) {
                        receiveFromClient = dataInputStream.readUTF();
                        System.out.println("receive: " + receiveFromClient);
                        if (receiveFromClient.equals("bye")) {
                            dataOutputStream.writeUTF("终于厌倦我了吗？客户端都是大猪蹄子！！");
                            dataOutputStream.flush();
                            break;
                        } else {
                            dataOutputStream.writeUTF("你TM给我发 " + receiveFromClient + " 干什么？");
                            dataOutputStream.flush();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        dataInputStream.close();
                        dataOutputStream.close();
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}