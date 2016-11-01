/*    */ package com.xiaoyi.action;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ public final class Platform
/*    */ {
/*    */   private static boolean sIsInitialized;
/* 38 */   static boolean sDebugOnlyVerboseEnabled = false;
/*    */ 
/*    */   public static void initialize(Logger logger)
/*    */     throws IOException
/*    */   {
/* 48 */     if (!sIsInitialized)
/* 49 */       synchronized (Platform.class) {
/* 50 */         if (!sIsInitialized)
/*    */         {
/* 52 */           if (logger == null)
/* 53 */             logger = new Logger()
/*    */             {
/*    */               public void verbose(String message)
/*    */               {
/*    */               }
/*    */ 
/*    */               public void info(String message)
/*    */               {
/*    */               }
/*    */ 
/*    */               public void warning(String message)
/*    */               {
/*    */               }
/*    */ 
/*    */               public void error(String message)
/*    */               {
/*    */               }
/*    */             };
/* 71 */           Log.sLogger = logger;
/*    */ 
/* 74 */           AsyncSocketWorkThread.getInstance().start();
/*    */ 
/* 76 */           sIsInitialized = true;
/*    */         }
/*    */       }
/*    */   }
/*    */ 
/*    */   public static void uninitialize()
/*    */     throws IOException
/*    */   {
/* 87 */     if (sIsInitialized)
/* 88 */       synchronized (Platform.class) {
/* 89 */         if (sIsInitialized) {
/* 90 */           AsyncSocketWorkThread.getInstance().interrupt();
/*    */           try {
/* 92 */             AsyncSocketWorkThread.getInstance().join();
/*    */           }
/*    */           catch (InterruptedException localInterruptedException) {
/*    */           }
/* 96 */           sIsInitialized = false;
/*    */         }
/*    */       }
/*    */   }
/*    */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.Platform
 * JD-Core Version:    0.6.2
 */