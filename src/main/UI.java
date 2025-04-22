package main;

import object.Obj_key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;


public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_30, arial_80B;
    //BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;

    public boolean gameFinished = false;
    public String currentDialogue = "";

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");



    public UI(GamePanel gp){
        this.gp = gp;
        arial_30 = new Font("Arial",Font.PLAIN,30);
        arial_80B = new Font("Arial",Font.BOLD,80);



    }
    public void showMessage(String text){
        message = text;
        messageOn = true;

    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(arial_30);
        g2.setColor(Color.white);

        if(gp.gameState == gp.playState){

        }
        if(gp.gameState == gp.pauseState){
            drawPauseScreeen();
        }

        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }


    }

    public void drawPauseScreeen(){
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,80F));
        String text = "PAUSED";
        int x;
        int y;
        x = getXforCentredText(text);
        y = gp.screenHeight/2;

        g2.drawString(text,x,y);

    }

    public void drawDialogueScreen(){
        int x;
        int y;
        int width;
        int height;
        x = gp.tileSize*2;
        y=gp.tileSize/2;
        width = gp.screenWidth - (gp.tileSize*4);
        height = gp.tileSize * 4;

        drawSubWindow(x,y,width,height);

        x+= gp.tileSize;
        y += gp.tileSize;
        g2.drawString(currentDialogue,x,y);
    }

    public void drawSubWindow(int x,int y,int width, int height){
        Color c = new Color(0,0,0,200);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height,35,35);
        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5,width-10,height-10,25,25);
    }
    public int getXforCentredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.screenWidth/2-length/2;
        return  x;
    }
}
