package TestingAshley.Utilities;

public class Constants {
    public static final float TIME_STEP = 1f / 60f;
    public static final int VELOCITY_ITERATIONS = 8;
    public static final int POSITION_ITERATIONS = 4;


    public static final short CATEGORY_PLAYER1 = 0x0001;
    public static final short CATEGORY_PLAYER2 = 0x0002;
    public static final short CATEGORY_ENVIRONMENT = 0x0004;
    public static final short CATEGORY_BULLET = 0x0008;
    public static final short CATEGORY_WORLDMOVER = 0x0010;
    public static final short MASK_PLAYER1 = ~CATEGORY_PLAYER1 & ~CATEGORY_BULLET;
    public static final short MASK_PLAYER2 = ~CATEGORY_PLAYER2 & ~CATEGORY_BULLET;
}
