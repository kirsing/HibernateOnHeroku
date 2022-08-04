package com.example.hiberdeployed.service;

import java.io.*;
import java.nio.file.Files;

public class FileUpload {
    public static void main(String[] args) throws IOException {
        File source = new File("/home/kiryl/IdeaProjects/HiberDeployed/example.jpg");
        File dest = new File("/home/kiryl/IdeaProjects/HiberDeployed/upload/example2.jpg");

        long start = System.nanoTime();
        //copyFileUsingStream(source, dest);
        copyFileUsingJava7Files(source, dest);
        System.out.println("Time taken by File Using = "+(System.nanoTime()-start));
    }
    private static void copyFileUsingStream(File source, File dest) throws IOException {
        try(InputStream is = new FileInputStream(source); OutputStream os = new FileOutputStream(dest)){
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        }
    }

    private static void copyFileUsingJava7Files(File source, File dest) throws IOException {
        Files.copy(source.toPath(), dest.toPath());
    }



}