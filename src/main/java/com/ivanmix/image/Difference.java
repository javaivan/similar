package com.ivanmix.image;

public class Difference {
    private int startX;
    private int startY;
    private int finishX;
    private int finishY;

    public Difference() {
    }

    public Difference(int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
    }


    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        if(startX < 0){
            startX = 0;
        }
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        if(startY < 0){
            startY = 0;
        }
        this.startY = startY;
    }

    public int getFinishX() {
        return finishX;
    }

    public void setFinishX(int finishX) {
        this.finishX = finishX;
    }

    public int getFinishY() {
        return finishY;
    }

    public void setFinishY(int finishY) {
        this.finishY = finishY;
    }
}
