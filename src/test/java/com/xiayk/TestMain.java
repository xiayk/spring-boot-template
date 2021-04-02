package com.xiayk;

import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.net.URL;

public class TestMain {

    public static void main(String[] args) {
        String url = "http://gateway.lvzheng.com/auxiliary-api/dingdingNotify/pushChat";
        try {
            HttpClient client = HttpClient.New(new URL(url),false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
