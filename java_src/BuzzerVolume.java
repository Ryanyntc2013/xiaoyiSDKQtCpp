/*    */ package com.xiaoyi.action;
/*    */ 
/*    */ public enum BuzzerVolume
/*    */ {
/*  6 */   Unknown, 
/*    */ 
/*  8 */   High, 
/*    */ 
/* 10 */   Low, 
/*    */ 
/* 12 */   Mute;
/*    */ 
/*    */   static BuzzerVolume parse(String str) {
/* 15 */     switch (str) {
/*    */     default:
/* 17 */       return Unknown;
/*    */     case "high":
/* 20 */       return High;
/*    */     case "low":
/* 23 */       return Low;
/*    */     case "mute":
/*    */     }
/* 26 */     return Mute;
/*    */   }
/*    */ 
/*    */   static String getString(BuzzerVolume buzzerVolume)
/*    */   {
/* 31 */     switch (1.$SwitchMap$com$xiaoyi$action$BuzzerVolume[buzzerVolume.ordinal()]) {
/*    */     case 1:
/* 33 */       return "high";
/*    */     case 2:
/* 36 */       return "low";
/*    */     case 3:
/* 39 */       return "mute";
/*    */     }
/*    */ 
/* 42 */     return "";
/*    */   }
/*    */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.BuzzerVolume
 * JD-Core Version:    0.6.2
 */