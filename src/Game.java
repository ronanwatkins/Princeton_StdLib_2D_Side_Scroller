import java.util.Random;

/**
 * Created by lxn on 30/12/2016.
 */
public class Game{

    static Bob bob;
    static int bobx = 100, boby = 125, bobCount = 0, bobStage = 0, playerX = 0, playerX2 = 0;
    static int backgroundX1 = 600, backgroundX2 = 0;
    static int[] bottomblockx = new int[9];

    static boolean goingBack = false;

    public static void main(String[] args) {
        Random random = new Random();

        bob = new Bob(bobx, boby);
        StdDraw.setCanvasSize(600,400);
        StdDraw.setXscale(0,600);
        StdDraw.setYscale(0,400);
        StdDraw.enableDoubleBuffering();
        StdDraw.picture(400,200,"/images/background.png",800,400);

        int bottomCoords = 0;
        for (int i = 8; i >= 0; i--) {
            bottomblockx[i] = bottomCoords;
            bottomCoords -= 100;
        }
        for (int i = 0; i <= 8; i++) {
            System.out.println(bottomblockx[i]);
        }

        while (true) {
            buttonPressed();

            bob.update();

            drawBackground();

            bob.draw(bobx, bobStage);
            StdDraw.show();
        }
    }

    static void buttonPressed(){
        if(StdDraw.isKeyPressed(68)){
            goingBack = false;

            bobCount++;
            if(bobCount % 8 == 0){
                if(bobStage < 4)
                    bobStage += 1;
                else bobStage = 0;

                bobCount = 0;
            }

            if(bobx <= 100)
                bobx++;
            else{
                for (int i = 0; i < 9; i++) {
                    bottomblockx[i]++;
                }
                backgroundX1++;
                backgroundX2++;
                playerX+=2;
                playerX2++;
            }

        } else if(StdDraw.isKeyPressed(65)){
            goingBack = true;

            bobCount--;
            if(bobCount == 0)
                bobCount = 8;
            if(bobCount % 8 == 0 && bobx > 10){
                if(bobStage > 0)
                    bobStage -= 1;
                else bobStage = 4;

                bobCount = 4;
            }

            if(bobx > 10) {
                bobx--;
            }
            bob.draw(bobx, bobStage);
        }
    }

    static void drawBackground(){
        if(playerX != 0 && ((playerX - 400) % 3200 == 0))
            backgroundX2 = 0;
        if(playerX != 0 && ((playerX - 1600) % 3200 == 0))
            backgroundX1 = -200;

        StdDraw.picture((600 - backgroundX1)+400,200,"/images/background.png",800,400);
        StdDraw.picture((600 - backgroundX1)+400,50,"/images/bottomblock.png",800,100);
        if(playerX >= 400) {
            StdDraw.picture((600 - backgroundX2) + 400, 200, "/images/background.png", 800, 400);
            StdDraw.picture((600 - backgroundX2)+400,50,"/images/bottomblock.png",800,100);
        }
    }
}
