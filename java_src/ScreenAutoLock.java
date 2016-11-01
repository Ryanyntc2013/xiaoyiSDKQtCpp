/*    */ package com.xiaoyi.action;
/*    */ 
/*    */ public enum ScreenAutoLock
/*    */ {
/*  6 */   Unknown, 
/*    */ 
/*  8 */   s_Never, 
/*    */ 
/* 10 */   s_30s, 
/*    */ 
/* 12 */   s_60s, 
/*    */ 
/* 14 */   s_120s;
/*    */ 
/*    */   static ScreenAutoLock parse(String str) {
/* 17 */     switch (str) {
/*    */     default:
/* 19 */       return Unknown;
/*    */     case "never":
/* 22 */       return s_Never;
/*    */     case "30s":
/* 25 */       return s_30s;
/*    */     case "60s":
/* 28 */       return s_60s;
/*    */     case "120s":
/*    */     }
/* 31 */     return s_120s;
/*    */   }
/*    */ 
/*    */   static String getString(ScreenAutoLock screenAutoLock)
/*    */   {
/* 36 */     switch (1.$SwitchMap$com$xiaoyi$action$ScreenAutoLock[screenAutoLock.ordinal()]) {
/*    */     case 1:
/* 38 */       return "never";
/*    */     case 2:
/* 41 */       return "30s";
/*    */     case 3:
/* 44 */       return "60s";
/*    */     case 4:
/* 47 */       return "120s";
/*    */     }
/*    */ 
/* 50 */     return "";
/*    */   }
/*    */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.ScreenAutoLock
 * JD-Core Version:    0.6.2
 */