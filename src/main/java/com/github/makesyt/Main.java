package com.github.makesyt;

import java.io.File;

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
        File file=chatGPT.getRe("你是?");
    }
}