package server;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class BankServer {


    public void startServer() throws IOException {
        InetSocketAddress socket = new InetSocketAddress(8085);
        HttpServer server = HttpServer.create(socket, 0);

        server.createContext("/add", new AddHandler());
        server.createContext("/getone", new GetCardHandler());
        server.createContext("/getall", new GetAllCardHandler());
        server.createContext("/setamount", new SetAmountHandler());

        server.start();

    }
}
