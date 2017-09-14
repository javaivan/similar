package com.ivanmix.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Helper {
    private static int faultInColor = 8;
    private static int widthStrokes = 5;

    public static boolean compareRgb(int imageRgbOne, int imageRgbTwo) {
        if (imageRgbOne != imageRgbTwo) {
            Color colorOne = new Color(imageRgbOne);
            Color colorTwo = new Color(imageRgbOne);

            int verifyingMin = colorOne.getRed() - faultInColor;
            int verifyingMax = colorOne.getRed() + faultInColor;
            if (verifyingMin < colorTwo.getRed() || verifyingMax > colorTwo.getRed()) {
                return false;
            }

            verifyingMin = colorOne.getGreen() - faultInColor;
            verifyingMax = colorOne.getGreen() + faultInColor;
            if (verifyingMin < colorTwo.getGreen() || verifyingMax > colorTwo.getGreen()) {
                return false;
            }

            verifyingMin = colorOne.getBlue() - faultInColor;
            verifyingMax = colorOne.getBlue() + faultInColor;
            if (verifyingMin < colorTwo.getBlue() || verifyingMax > colorTwo.getBlue()) {
                return false;
            }
        }
        return true;
    }

    public static void addErrorBlocks(List<Difference> differences, Difference difference) {
        boolean thisNew = true;
        for (Difference dif : differences) {
            if (
                    dif.getStartX() < difference.getStartX()
                            && dif.getFinishX() > difference.getFinishX()
                            && dif.getStartY() < difference.getStartY()
                            && dif.getFinishY() > difference.getFinishY()) {
                dif.setFinishX(difference.getStartX() + widthStrokes);
                dif.setFinishY(difference.getStartY() + widthStrokes);
                thisNew = false;
            }
        }
        if (thisNew) {
            Difference dif = new Difference();
            dif.setStartX(difference.getStartX() - widthStrokes);
            dif.setFinishX(difference.getStartX() + widthStrokes);
            dif.setStartY(difference.getStartY() - widthStrokes);
            dif.setFinishY(difference.getStartY() + widthStrokes);
            differences.add(dif);
        }
    }

    public static int getMinimum(int valueOne, int valueTwo) {
        if (valueOne == valueTwo) {
            return valueOne;
        } else {
            if (valueOne > valueTwo) {
                return valueTwo;
            } else {
                return valueOne;
            }
        }
    }

    public static BufferedImage getImageBuffer(BufferedReader buffer){
        System.out.println("Enter the path to the first image: for example D:\\similar\\image1.png");
        BufferedImage bufferedImage;
        while (true){
            try {
                bufferedImage = ImageIO.read(new File(buffer.readLine()));
                break;
            } catch (IOException e) {
                System.out.println("File not found, try it again");
            }
        }
        return bufferedImage;
    }

    public static void saveCompareResult(BufferedImage bufferedImage, BufferedReader buffer){
        System.out.println("Enter the path where to save the image: for example D:\\similar\\image.png");
        while (true){
            try {
                File outputfile = new File( buffer.readLine());
                ImageIO.write(bufferedImage, "jpg", outputfile);
                break;
            } catch (IOException e) {
                System.out.println("Error, try it again with another directory");
            }
        }
    }
}
