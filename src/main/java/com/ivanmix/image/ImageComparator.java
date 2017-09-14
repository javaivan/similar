package com.ivanmix.image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ImageComparator {
    private BufferedImage bufferedImageOne;
    private BufferedImage bufferedImageTwo;
    private BufferedReader buffer;
    private Integer resultImageWidth;
    private Integer resultImageHeight;
    private List<Difference> notSimilarPixels;
    private BufferedImage resultImage;

    public ImageComparator(){
        buffer = new BufferedReader(new InputStreamReader(System.in));
    }


    //Step One
    public void download(){
        bufferedImageOne = Helper.getImageBuffer(buffer);
        bufferedImageTwo = Helper.getImageBuffer(buffer);
        resultImageWidth = Helper.getMinimum(bufferedImageOne.getWidth(), bufferedImageTwo.getWidth());
        resultImageHeight = Helper.getMinimum(bufferedImageOne.getHeight(), bufferedImageTwo.getHeight());
        notSimilarPixels = new ArrayList<>();
    }

    //Step Second
    public void compare() {
        if(Objects.nonNull(bufferedImageOne)
                && Objects.nonNull(bufferedImageTwo)
                && Objects.nonNull(resultImageWidth)
                && Objects.nonNull(resultImageHeight)
                && Objects.nonNull(notSimilarPixels)) {
            resultImage = new BufferedImage(resultImageWidth, resultImageHeight, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < resultImageWidth; x++) {
                for (int y = 0; y < resultImageHeight; y++) {
                    int rgbOne = bufferedImageOne.getRGB(x, y);
                    int rgbTwo = bufferedImageTwo.getRGB(x, y);

                    resultImage.setRGB(x, y, rgbTwo);
                    if (!Helper.compareRgb(rgbOne, rgbTwo)) {
                        notSimilarPixels.add(new Difference(x, y));
                    }
                }
            }
        }
    }

    //Step Third
    public void unloadResult(){
        if(Objects.nonNull(notSimilarPixels)
                && Objects.nonNull(resultImage)
                && Objects.nonNull(buffer)) {
            List<Difference> errorBlocks = new ArrayList<>();
            for (Difference difference : notSimilarPixels) {
                Helper.addErrorBlocks(errorBlocks, difference);
            }
            for (Difference difference : errorBlocks) {
                Graphics2D g2d = resultImage.createGraphics();
                g2d.setColor(Color.RED);
                g2d.drawRect(difference.getStartX(), difference.getStartY(),
                        difference.getFinishX() - difference.getStartX(),
                        difference.getFinishY() - difference.getStartY());
            }
            Helper.saveCompareResult(resultImage, buffer);
        }
    }
}