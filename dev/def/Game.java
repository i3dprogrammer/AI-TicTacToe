/*     */ package dev.def;
/*     */ 
/*     */ import dev.drawing.display.Display;
/*     */ import dev.drawing.gfx.Assets;
/*     */ import dev.drawing.input.MouseManager;
/*     */ import dev.drawing.space.AiMove;
/*     */ import java.awt.Canvas;
/*     */ import java.awt.Color;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.BufferStrategy;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class Game implements Runnable
/*     */ {
/*  19 */   private String[] board = { "NNN", "NNN", "NNN" };
/*  20 */   private boolean playerTurn = true;
/*  21 */   private boolean gameOver = false;
/*     */   
/*     */   private Display display;
/*     */   public int m_width;
/*     */   public int m_height;
/*     */   public String m_title;
/*  27 */   public boolean running = false;
/*     */   
/*     */   private Thread thread;
/*     */   
/*     */   private BufferStrategy bs;
/*     */   private Graphics g;
/*     */   private MouseManager mouseManager;
/*     */   
/*     */   public Game(String title, int width, int height)
/*     */   {
/*  37 */     this.m_title = title;
/*  38 */     this.m_width = width;
/*  39 */     this.m_height = height;
/*  40 */     this.mouseManager = new MouseManager();
/*     */   }
/*     */   
/*     */   private void init() {
/*  44 */     this.display = new Display(this.m_title, this.m_width, this.m_height);
/*  45 */     this.display.getFrame().addMouseListener(this.mouseManager);
/*  46 */     this.display.getFrame().addMouseMotionListener(this.mouseManager);
/*  47 */     this.display.getCanvas().addMouseListener(this.mouseManager);
/*  48 */     this.display.getCanvas().addMouseMotionListener(this.mouseManager);
/*  49 */     Assets.init();
/*  50 */     restartGame();
/*     */   }
/*     */   
/*     */   private void restartGame() {
/*  54 */     Random rnd = new Random();
/*  55 */     if (rnd.nextBoolean()) {
/*  56 */       TicTacToeAPI.changeSpace(this.board, rnd.nextInt(3), rnd.nextInt(3), TicTacToeAPI.AgentType);
/*     */     }
/*  58 */     this.playerTurn = true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void onMouseClick()
/*     */   {
/*  64 */     if (this.gameOver) {
/*  65 */       int b_width = 150;
/*  66 */       int b_height = 50;
/*  67 */       int b_y = this.m_height / 4 * 3;
/*  68 */       int b_x = this.m_width / 2 - b_width / 2;
/*     */       
/*  70 */       if ((this.mouseManager.getMouseX() < b_x + b_width) && (this.mouseManager.getMouseX() > b_x) && 
/*  71 */         (this.mouseManager.getMouseY() < b_y + b_height) && (this.mouseManager.getMouseY() > b_y))
/*     */       {
/*  73 */         this.board = new String[] { "NNN", "NNN", "NNN" };
/*  74 */         restartGame();
/*  75 */         this.gameOver = false;
/*     */       }
/*  77 */     } else if ((this.playerTurn) && (!this.gameOver) && (this.mouseManager.isMouseInGame())) {
/*  78 */       int mouseX = this.mouseManager.getMouseX();
/*  79 */       int mouseY = this.mouseManager.getMouseY();
/*  80 */       int stepX = mouseX / 200;
/*  81 */       int stepY = mouseY / 200;
/*  82 */       if (this.board[stepY].charAt(stepX) == TicTacToeAPI.NullType)
/*     */       {
/*  84 */         TicTacToeAPI.changeSpace(this.board, stepX, stepY, TicTacToeAPI.PlayerType);
/*  85 */         this.playerTurn = (!this.playerTurn);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void update()
/*     */   {
/*  92 */     if ((!this.playerTurn) && (!this.gameOver)) {
/*  93 */       AiMove bestMove = TicTacToeAPI.GetBestMoveVersion2(this.board, false);
/*  94 */       TicTacToeAPI.changeSpace(this.board, bestMove.X, bestMove.Y, TicTacToeAPI.AgentType);
/*  95 */       this.playerTurn = true;
/*     */     }
/*     */   }
/*     */   
/*     */   private void draw() {
/* 100 */     this.bs = this.display.getCanvas().getBufferStrategy();
/* 101 */     if (this.bs == null)
/*     */     {
/* 103 */       this.display.getCanvas().createBufferStrategy(3);
/* 104 */       return;
/*     */     }
/* 106 */     this.g = this.bs.getDrawGraphics();
/*     */     
/* 108 */     this.g.clearRect(0, 0, this.m_width, this.m_height);
/*     */     
/*     */ 
/* 111 */     this.g.drawImage(Assets.border, 0, 0, null);
/*     */     
/*     */ 
/* 114 */     for (int i = 0; i < 3; i++) {
/* 115 */       for (int j = 0; j < 3; j++) {
/* 116 */         if (this.board[i].charAt(j) == 'X') {
/* 117 */           this.g.drawImage(Assets.X, j * 200 + 30, i * 200 + 30, null);
/* 118 */         } else if (this.board[i].charAt(j) == 'O') {
/* 119 */           this.g.drawImage(Assets.O, j * 200 + 30, i * 200 + 30, null);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 125 */     if ((TicTacToeAPI.CheckLose(this.board)) || (TicTacToeAPI.CheckWin(this.board)) || (TicTacToeAPI.CheckDraw(this.board))) {
/* 126 */       this.gameOver = true;
/* 127 */       shadeScreen(this.g);
/*     */     }
/*     */     
/*     */ 
/* 131 */     if (TicTacToeAPI.CheckLose(this.board)) {
/* 132 */       drawText(this.g, new Rectangle(0, 0, this.m_width, this.m_height), "Calibri", 90, "YOU WIN", Color.white);
/* 133 */     } else if (TicTacToeAPI.CheckWin(this.board)) {
/* 134 */       drawText(this.g, new Rectangle(0, 0, this.m_width, this.m_height), "Calibri", 90, "YOU LOSE", Color.white);
/* 135 */     } else if (TicTacToeAPI.CheckDraw(this.board)) {
/* 136 */       drawText(this.g, new Rectangle(0, 0, this.m_width, this.m_height), "Calibri", 90, "DRAW", Color.white);
/*     */     }
/*     */     
/*     */ 
/* 140 */     if (this.gameOver) {
/* 141 */       int b_width = 150;
/* 142 */       int b_height = 50;
/* 143 */       int b_y = this.m_height / 4 * 3;
/* 144 */       int b_x = this.m_width / 2 - b_width / 2;
/* 145 */       int b_shadow = 1;
/*     */       
/* 147 */       this.g.setColor(Color.white);
/* 148 */       this.g.fillRect(b_x - b_shadow, b_y - b_shadow, b_width, b_height);
/* 149 */       this.g.setColor(Color.black);
/* 150 */       this.g.fillRect(b_x + b_shadow, b_y + b_shadow, b_width, b_height);
/* 151 */       this.g.setColor(Color.blue);
/* 152 */       this.g.fillRect(b_x, b_y, b_width, b_height);
/* 153 */       drawText(this.g, new Rectangle(b_x, b_y, b_width, b_height), "Calibri", 30, "Play again", Color.white);
/*     */     }
/*     */     
/*     */ 
/* 157 */     if ((this.playerTurn) && (!this.gameOver) && (this.mouseManager.isMouseInGame())) {
/* 158 */       int mouseX = this.mouseManager.getMouseX();
/* 159 */       int mouseY = this.mouseManager.getMouseY();
/* 160 */       int stepX = mouseX / 200;
/* 161 */       int stepY = mouseY / 200;
/* 162 */       if (this.board[stepY].charAt(stepX) == TicTacToeAPI.NullType)
/*     */       {
/* 164 */         this.g.drawImage(Assets.alphaX, stepX * 200 + 30, stepY * 200 + 30, null);
/*     */       }
/*     */     }
/*     */     
/* 168 */     this.bs.show();
/* 169 */     this.g.dispose();
/*     */   }
/*     */   
/*     */   public void run() {
/* 173 */     init();
/*     */     
/* 175 */     while (this.running) {
/* 176 */       update();
/* 177 */       draw();
/*     */       try {
/* 179 */         Thread.sleep(1L);
/*     */       }
/*     */       catch (InterruptedException e) {
/* 182 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */     
/* 186 */     stop();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void shadeScreen(Graphics g)
/*     */   {
/* 197 */     g.setColor(new Color(0.0F, 0.0F, 0.0F, 0.7F));
/* 198 */     g.fillRect(0, 0, this.m_width, this.m_height);
/*     */   }
/*     */   
/*     */   private void drawText(Graphics g, Rectangle rect, String fontName, int size, String text, Color color) {
/* 202 */     Graphics2D g2 = (Graphics2D)g;
/*     */     
/* 204 */     java.awt.Font font = new java.awt.Font(fontName, 1, size);
/* 205 */     FontMetrics metrics = g.getFontMetrics(font);
/*     */     
/* 207 */     int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
/* 208 */     int y = rect.y + (rect.height - metrics.getHeight()) / 2 + metrics.getAscent();
/*     */     
/* 210 */     g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
/* 211 */       RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
/*     */     
/* 213 */     g2.setColor(color);
/* 214 */     g2.setFont(font);
/* 215 */     g2.drawString(text, x, y);
/*     */   }
/*     */   
/*     */   public synchronized void start() {
/* 219 */     if (this.running)
/* 220 */       return;
/* 221 */     this.running = true;
/* 222 */     this.thread = new Thread(this);
/* 223 */     this.thread.start();
/*     */   }
/*     */   
/*     */   public synchronized void stop() {
/*     */     try {
/* 228 */       this.thread.join();
/*     */     } catch (InterruptedException e) {
/* 230 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void drawBoard() {
/* 235 */     for (int i = 0; i < 3; i++) {
/* 236 */       System.out.println(this.board[i]);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\SAVED PROJECTS FROM OLD WINDOWS V2\Desktop\Game2\Test.jar!\dev\def\Game.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */