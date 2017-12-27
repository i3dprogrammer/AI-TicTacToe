/*    */ package dev.drawing.input;
/*    */ 
/*    */ import dev.def.Game;
/*    */ import dev.def.MyAgent;
/*    */ import java.awt.event.MouseEvent;
/*    */ import java.awt.event.MouseListener;
/*    */ import java.awt.event.MouseMotionListener;
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ 
/*    */ public class MouseManager
/*    */   implements MouseListener, MouseMotionListener
/*    */ {
/*    */   private boolean m_leftPressed;
/*    */   private boolean m_rightPressed;
/*    */   private boolean m_mouseInGame;
/*    */   private int m_mouseX;
/*    */   private int m_mouseY;
/*    */   
/*    */   public boolean isLeftPressed()
/*    */   {
/* 22 */     return this.m_leftPressed;
/*    */   }
/*    */   
/* 25 */   public boolean isRightPressed() { return this.m_rightPressed; }
/*    */   
/*    */   public boolean isMouseInGame() {
/* 28 */     return this.m_mouseInGame;
/*    */   }
/*    */   
/*    */   public int getMouseX() {
/* 32 */     return this.m_mouseX;
/*    */   }
/*    */   
/* 35 */   public int getMouseY() { return this.m_mouseY; }
/*    */   
/*    */ 
/*    */ 
/*    */   public void mouseDragged(MouseEvent e) {}
/*    */   
/*    */ 
/*    */ 
/*    */   public void mouseMoved(MouseEvent e)
/*    */   {
/* 45 */     this.m_mouseX = e.getX();
/* 46 */     this.m_mouseY = e.getY();
/*    */   }
/*    */   
/*    */   public void mouseClicked(MouseEvent e) {
/* 50 */     MyAgent.game.onMouseClick();
/*    */   }
/*    */   
/*    */   public void mouseEntered(MouseEvent e) {
/* 54 */     this.m_mouseInGame = true;
/*    */   }
/*    */   
/*    */   public void mouseExited(MouseEvent e) {
/* 58 */     this.m_mouseInGame = false;
/*    */   }
/*    */   
/*    */   public void mousePressed(MouseEvent e) {
/* 62 */     if (e.getButton() == 1) {
/* 63 */       this.m_leftPressed = true;
/* 64 */     } else if (e.getButton() == 3)
/* 65 */       this.m_rightPressed = true;
/*    */   }
/*    */   
/*    */   public void mouseReleased(MouseEvent e) {
/* 69 */     System.out.println("RELEASED!");
/* 70 */     if (e.getButton() == 1) {
/* 71 */       this.m_leftPressed = false;
/* 72 */     } else if (e.getButton() == 3) {
/* 73 */       this.m_rightPressed = false;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\SAVED PROJECTS FROM OLD WINDOWS V2\Desktop\Game2\Test.jar!\dev\drawing\input\MouseManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */