# 2021-2022-1 网络编程课程期末大作业 - 选课系统



### 疑问

1. 服务器管理程序：管理包括什么内容
2. 用户信息验证：验证什么，怎么验证
3. 分级实现课程设置：什么分级
4. 如何实现选课的线程同步
5. 如何实现数据库安全
6. URL
7. URLConnection
8. Socket
9. 同一门课选课人数即将达上限的处理



## 解决



#### 线程同步

```java
synchronized (Servlet.class) {
    if (Lessen.getNum() > 0) {
        Lessen.setNum(Lessen.getNum() - 1);
        printWriter.println("有人选课，还剩" + Lessen.getNum());
    } else {
        printWriter.println("没有课了");
    }
}
```



#### Socket

```java
public static void main(String[] args) throws IOException {
    //1.连接服务器(ip , 端口)
    //解读：连接本机的9999端口 , 如果连接成功返回一个socket对象
    Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
    System.out.println("客户端socket 返回= " + socket.getClass());

    //2.连接上后，生成Socket，通过Socket.getOutputSteram()
    //得到和socket对象关联的输出流对象
    OutputStream outputStream = socket.getOutputStream();

    //3.通过输出流，写入数据到数据通道里面
    outputStream.write("hello,server!".getBytes());

    outputStream.close();
    socket.close();
    System.out.println("客户端退出...");
}
```

```java
public static void main(String[] args) throws IOException {
    //思路
    //1.在本机的9999端口监听，等待连接
    // 细节：要求本级的9999端口没有被占用，没有其他服务占用9999
    // 细节：这个ServerSocket 可以通过accept()方法 返回多个 socket
    ServerSocket serverSocket = new ServerSocket(9999);
    System.out.println("服务端，在9999端口监听，等待连接...");
    //2.当没有客户端连接9999端口时，程序会 阻塞， 等待连接
    // 如果有客户端连接，则会返回Socket对象，程序连接
    Socket socket = serverSocket.accept();
    System.out.println("服务器端socket = " + socket.getClass());

    //3.通过输入流读取数据
    InputStream inputStream = socket.getInputStream();
    //4.IO读取
    byte[] buf = new byte[1024];
    int readLen = 0;
    while ((readLen = inputStream.read(buf)) != -1) {
        System.out.println(new String(buf, 0, readLen));
    }
    inputStream.close();
    socket.close();
    serverSocket.close();//关闭
}
```

