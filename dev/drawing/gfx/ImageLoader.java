/*    */ package dev.drawing.gfx;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class ImageLoader
/*    */ {
/*    */   public static java.awt.image.BufferedImage loadImage(String path)
/*    */   {
/*    */     try
/*    */     {
/* 11 */       return javax.imageio.ImageIO.read(ImageLoader.class.getResource(path));
/*    */     } catch (IOException e) {
/* 13 */       e.printStackTrace();
/* 14 */       System.exit(1);
/*    */     }
/* 16 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\SAVED PROJECTS FROM OLD WINDOWS V2\Desktop\Game2\Test.jar!\dev\drawing\gfx\ImageLoader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */