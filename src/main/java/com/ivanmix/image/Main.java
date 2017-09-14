package com.ivanmix.image;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        while (true) {
            ImageComparator comparator = new ImageComparator();
            comparator.download();
            comparator.compare();
            comparator.unloadResult();
            System.out.println("Successfully");

            System.out.println("Do you want to compare the other files? yes/not");
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            String again = buffer.readLine().toLowerCase();
            if(!again.equals("yes") && !again.equals("y")){
                break;
            }
        }
    }
}
