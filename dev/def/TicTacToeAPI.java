/*     */ package dev.def;
/*     */ 
/*     */ import dev.drawing.space.AiMove;
/*     */ 
/*     */ public class TicTacToeAPI {
/*   6 */   public static char AgentType = 'O';
/*   7 */   public static char PlayerType = 'X';
/*   8 */   public static char NullType = 'N';
/*     */   
/*     */   public static boolean CheckDraw(String[] board)
/*     */   {
/*  12 */     for (int i = 0; i < 3; i++)
/*     */     {
/*  14 */       for (int j = 0; j < 3; j++)
/*     */       {
/*  16 */         if (board[i].charAt(j) == NullType)
/*  17 */           return false;
/*     */       }
/*     */     }
/*  20 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public static boolean CheckWin(String[] board)
/*     */   {
/*  26 */     boolean won = false;
/*  27 */     for (int i = 0; i < 3; i++)
/*     */     {
/*  29 */       if (((board[i].charAt(0) == board[i].charAt(1)) && (board[i].charAt(0) == board[i].charAt(2)) && (board[i].charAt(0) == AgentType)) || 
/*  30 */         ((board[0].charAt(i) == board[1].charAt(i)) && (board[0].charAt(i) == board[2].charAt(i)) && (board[0].charAt(i) == AgentType)) || 
/*  31 */         ((board[0].charAt(0) == board[1].charAt(1)) && (board[0].charAt(0) == board[2].charAt(2)) && (board[0].charAt(0) == AgentType)) || (
/*  32 */         (board[0].charAt(2) == board[1].charAt(1)) && (board[0].charAt(2) == board[2].charAt(0)) && (board[0].charAt(2) == AgentType)))
/*     */       {
/*  34 */         won = true;
/*  35 */         break;
/*     */       }
/*     */     }
/*  38 */     return won;
/*     */   }
/*     */   
/*     */ 
/*     */   public static boolean CheckLose(String[] board)
/*     */   {
/*  44 */     boolean won = false;
/*  45 */     for (int i = 0; i < 3; i++)
/*     */     {
/*  47 */       if (((board[i].charAt(0) == board[i].charAt(1)) && (board[i].charAt(0) == board[i].charAt(2)) && (board[i].charAt(0) == PlayerType)) || 
/*  48 */         ((board[0].charAt(i) == board[1].charAt(i)) && (board[0].charAt(i) == board[2].charAt(i)) && (board[0].charAt(i) == PlayerType)) || 
/*  49 */         ((board[0].charAt(0) == board[1].charAt(1)) && (board[0].charAt(0) == board[2].charAt(2)) && (board[0].charAt(0) == PlayerType)) || (
/*  50 */         (board[0].charAt(2) == board[1].charAt(1)) && (board[0].charAt(2) == board[2].charAt(0)) && (board[0].charAt(2) == PlayerType)))
/*     */       {
/*  52 */         won = true;
/*  53 */         break;
/*     */       }
/*     */     }
/*  56 */     return won;
/*     */   }
/*     */   
/*     */ 
/*     */   public static AiMove GetBestMove(String[] board, boolean Player)
/*     */   {
/*  62 */     AiMove bestMove = null;
/*     */     
/*  64 */     for (int y = 0; y < 3; y++) {
/*  65 */       for (int x = 0; x < 3; x++) {
/*  66 */         if (board[y].charAt(x) == NullType) {
/*  67 */           String[] cloneBoard = { board[0], board[1], board[2] };
/*  68 */           AiMove newMove = new AiMove(x, y);
/*     */           
/*  70 */           if (Player) {
/*  71 */             changeSpace(cloneBoard, x, y, PlayerType);
/*     */           } else {
/*  73 */             changeSpace(cloneBoard, x, y, AgentType);
/*     */           }
/*  75 */           if (CheckDraw(cloneBoard)) {
/*  76 */             newMove.Cost = 0;
/*  77 */           } else if (CheckWin(cloneBoard)) {
/*  78 */             newMove.Cost = 1;
/*  79 */           } else if (CheckLose(cloneBoard)) {
/*  80 */             newMove.Cost = -1;
/*     */           } else {
/*  82 */             newMove.Cost = GetBestMove(cloneBoard, !Player).Cost;
/*     */           }
/*     */           
/*  85 */           if ((bestMove == null) || 
/*  86 */             ((!Player) && (newMove.Cost > bestMove.Cost)) || (
/*  87 */             (Player) && (newMove.Cost < bestMove.Cost)))
/*     */           {
/*  89 */             bestMove = newMove;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  95 */     return bestMove;
/*     */   }
/*     */   
/*     */ 
/*     */   public static AiMove GetBestMoveVersion2(String[] board, boolean Player)
/*     */   {
/* 101 */     if (CheckWin(board))
/* 102 */       return new AiMove(1);
/* 103 */     if (CheckLose(board))
/* 104 */       return new AiMove(-1);
/* 105 */     if (CheckDraw(board)) {
/* 106 */       return new AiMove(0);
/*     */     }
/*     */     
/* 109 */     AiMove bestMove = null;
/*     */     
/* 111 */     for (int y = 0; y < 3; y++) {
/* 112 */       for (int x = 0; x < 3; x++) {
/* 113 */         if (board[y].charAt(x) == NullType) {
/* 114 */           AiMove newMove = new AiMove(x, y);
/* 115 */           if (Player) {
/* 116 */             changeSpace(board, x, y, PlayerType);
/*     */           }
/*     */           else {
/* 119 */             changeSpace(board, x, y, AgentType);
/*     */           }
/* 121 */           newMove.Cost = GetBestMoveVersion2(board, !Player).Cost;
/*     */           
/* 123 */           if ((bestMove == null) || 
/* 124 */             ((Player) && (bestMove.Cost > newMove.Cost)) || (
/* 125 */             (!Player) && (bestMove.Cost < newMove.Cost))) {
/* 126 */             bestMove = newMove;
/*     */           }
/* 128 */           changeSpace(board, x, y, NullType);
/*     */         }
/*     */       }
/*     */     }
/* 132 */     return bestMove;
/*     */   }
/*     */   
/*     */   public static void changeSpace(String[] board, int x, int y, char newChar) {
/* 136 */     StringBuilder temp = new StringBuilder(board[y]);
/* 137 */     temp.setCharAt(x, newChar);
/* 138 */     board[y] = temp.toString();
/*     */   }
/*     */ }


/* Location:              D:\SAVED PROJECTS FROM OLD WINDOWS V2\Desktop\Game2\Test.jar!\dev\def\TicTacToeAPI.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */