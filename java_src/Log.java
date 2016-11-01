/*    */ package com.xiaoyi.action;
/*    */ 
/*    */ class Log
/*    */ {
/*    */   static Logger sLogger;
/*    */ 
/*    */   public static void verbose(Object obj, String message)
/*    */   {
/* 34 */     sLogger.verbose(objAddress(obj) + message);
/*    */   }
/*    */ 
/*    */   public static void debugOnlyVerbose(Object obj, String message) {
/* 38 */     if (Platform.sDebugOnlyVerboseEnabled)
/* 39 */       sLogger.verbose(objAddress(obj) + message);
/*    */   }
/*    */ 
/*    */   public static void info(Object obj, String message)
/*    */   {
/* 44 */     sLogger.info(objAddress(obj) + message);
/*    */   }
/*    */ 
/*    */   public static void warning(Object obj, String message) {
/* 48 */     sLogger.warning(objAddress(obj) + message);
/*    */   }
/*    */ 
/*    */   public static void error(Object obj, String message) {
/* 52 */     sLogger.error(objAddress(obj) + message);
/*    */   }
/*    */ 
/*    */   private static String objAddress(Object obj) {
/* 56 */     return "[" + Util.objAddress(obj) + "] ";
/*    */   }
/*    */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.Log
 * JD-Core Version:    0.6.2
 */