/*    */ package dev.drawing.display;
/*    */ 
/*    */ import java.awt.Canvas;
/*    */ import java.awt.Dimension;
/*    */ import javax.swing.JFrame;
/*    */ 
/*    */ public class Display
/*    */ {
/*    */   private JFrame frame;
/*    */   private Canvas canvas;
/*    */   private String m_title;
/*    */   private int m_width;
/*    */   private int m_height;
/*    */   
/*    */   public Display(String title, int width, int height)
/*    */   {
/* 17 */     this.m_title = title;
/* 18 */     this.m_width = width;
/* 19 */     this.m_height = height;
/*    */     
/* 21 */     CreateDisplay();
/*    */   }
/*    */   
/*    */   public void CreateDisplay() {
/* 25 */     this.frame = new JFrame(this.m_title);
/* 26 */     this.frame.setSize(this.m_width, this.m_height);
/* 27 */     this.frame.setDefaultCloseOperation(3);
/* 28 */     this.frame.setResizable(false);
/* 29 */     this.frame.setLocationRelativeTo(null);
/* 30 */     this.frame.setVisible(true);
/*    */     
/* 32 */     this.canvas = new Canvas();
/* 33 */     this.canvas.setPreferredSize(new Dimension(this.m_width, this.m_height));
/* 34 */     this.canvas.setMaximumSize(new Dimension(this.m_width, this.m_height));
/* 35 */     this.canvas.setMinimumSize(new Dimension(this.m_width, this.m_height));
/* 36 */     this.frame.add(this.canvas);
/* 37 */     this.frame.pack();
/*    */   }
/*    */   
/*    */   public Canvas getCanvas() {
/* 41 */     return this.canvas;
/*    */   }
/*    */   
/*    */   public JFrame getFrame() {
/* 45 */     return this.frame;
/*    */   }
/*    */ }


/* Location:              D:\SAVED PROJECTS FROM OLD WINDOWS V2\Desktop\Game2\Test.jar!\dev\drawing\display\Display.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */