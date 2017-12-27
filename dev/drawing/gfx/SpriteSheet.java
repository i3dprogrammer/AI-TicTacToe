/*    */ package dev.drawing.gfx;
/*    */ 
/*    */ import java.awt.image.BufferedImage;
/*    */ 
/*    */ public class SpriteSheet {
/*    */   private BufferedImage m_sheet;
/*    */   
/*    */   public SpriteSheet(BufferedImage sheet) {
/*  9 */     this.m_sheet = sheet;
/*    */   }
/*    */   
/*    */   public SpriteSheet(String path) {
/* 13 */     this.m_sheet = ImageLoader.loadImage(path);
/*    */   }
/*    */   
/*    */   public BufferedImage crop(int x, int y, int width, int height) {
/* 17 */     return this.m_sheet.getSubimage(x, y, width, height);
/*    */   }
/*    */ }


/* Location:              D:\SAVED PROJECTS FROM OLD WINDOWS V2\Desktop\Game2\Test.jar!\dev\drawing\gfx\SpriteSheet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */