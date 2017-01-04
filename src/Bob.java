
public class Bob {
    private double yAccel = .82 * 7;
    public double yVelocity,x,y;
    private boolean previousState = false;
    String image;

    public Bob(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(int x, int bobStage) {
        if(y == 125){
            switch (bobStage){
                case 0:
                    image = "/images/bob1.png";
                    break;
                case 1:
                    image = "/images/bob2.png";
                    break;
                case 2:
                    image = "/images/bob3.png";
                    break;
                case 3:
                    image = "/images/bob4.png";
                    break;
                case 4:
                    image = "/images/bob5.png";
                    break;
            }
        }
        StdDraw.picture(x,y,image, 25, 50);
    }

    private void checkJump() {
        if(!StdDraw.isKeyPressed(87)) {
            previousState = false;
        }
        if(StdDraw.isKeyPressed(87) && !previousState && y == 125) {
            yVelocity = 0;
            yVelocity += 120 * .2;

            previousState = true;
        }
    }

    private void applyPhysics() {
        y += yVelocity * .1;
        if(y >= 225)
            yVelocity -= yAccel * .1;
        if(y <= 125) {
            yVelocity = 0;
            y = 125;
        }
    }
    public void update() {
        checkJump();
        applyPhysics();
    }
}
