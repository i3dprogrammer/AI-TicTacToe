/*    */ package dev.drawing.space;
/*    */ 
/*    */ public class AiMove {
/*  4 */   public int Cost = 0;
/*    */   public int X;
/*    */   public int Y;
/*    */   
/*    */   public AiMove(int x, int y)
/*    */   {
/* 10 */     this.X = x;
/* 11 */     this.Y = y;
/*    */   }
/*    */   
/*    */   public AiMove(int cost) {
/* 15 */     this.Cost = cost;
/*    */   }
/*    */   
/*    */   public AiMove() {}
/*    */ }


/* Location:              D:\SAVED PROJECTS FROM OLD WINDOWS V2\Desktop\Game2\Test.jar!\dev\drawing\space\AiMove.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */