package entity;
import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Random;

public class NPC_Oldman extends Entity{


    public NPC_Oldman(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 1;
        getImage();
        setDialogue();


    }
    public void getImage(){

        up1 = setup("/npc/oldman_up_1.png");
        up2 = setup("/npc/oldman_up_2.png");
        down1 = setup("/npc/oldman_down_1.png");
        down2 = setup("/npc/oldman_down_2.png");
        right1 = setup("/npc/oldman_right_1.png");
        right2 = setup("/npc/oldman_right_2.png");
        left1 = setup("/npc/oldman_left_1.png");
        left2 = setup("/npc/oldman_left_2.png");
    }

    public void setDialogue(){
        dialogues[0]="Hello Soldier, its good to see you!!";
    }

    public void setAction(){

        actionLockCounter++;
        if(actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100)+1;
            if(i <= 25){
                direction = "up";

            }

            if(i > 25 && i <= 50){
                direction = "down";
            }
            if(i > 50 && i <= 75){
                direction = "left";
            }
            if(i > 75 && i <= 100){
                direction = "right";
            }
            actionLockCounter = 0;

        }

    }
    public void speak(){
        gp.ui.currentDialogue =  dialogues[0];

    }

}
