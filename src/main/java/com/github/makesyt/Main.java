package com.github.makesyt;

public class Main {
    public static void main(String[] args) {
//        try {
//            ProcessBuilder proc = new ProcessBuilder(
//                    "\"C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe\"","--remote-debugging-port=9222"
//                   );
//            proc.start();
//        } catch (Exception e) {
//            System.out.println("Error executing progarm.");
//        }
        ChatGPT chatGPT=new ChatGPT();
        System.out.println(chatGPT.getRe("你是?"));
    }
}