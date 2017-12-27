/*    */ package dev.drawing.gfx;
/*    */ 
/*    */ import java.awt.image.BufferedImage;
/*    */ 
/*    */ public class Assets {
/*    */   private static final int m_spriteWidth = 128;
/*    */   private static final int m_spriteHeight = 128;
/*    */   public static BufferedImage border;
/*    */   
/* 10 */   public static void init() { SpriteSheet sheet = new SpriteSheet("/textures/mainSpriteSheet.png");
/* 11 */     border = ImageLoader.loadImage("/textures/Tic-tac-toe.png");
/* 12 */     alphaX = sheet.crop(0, 0, 128, 128);
/* 13 */     X = sheet.crop(128, 0, 128, 128);
/* 14 */     alphaO = sheet.crop(256, 0, 128, 128);
/* 15 */     O = sheet.crop(384, 0, 128, 128);
/*    */   }
/*    */   
/*    */   public static BufferedImage alphaX;
/*    */   public static BufferedImage X;
/*    */   public static BufferedImage alphaO;
/*    */   public static BufferedImage O;
/*    */ }


/* Location:              D:\SAVED PROJECTS FROM OLD WINDOWS V2\Desktop\Game2\Test.jar!\dev\drawing\gfx\Assets.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */