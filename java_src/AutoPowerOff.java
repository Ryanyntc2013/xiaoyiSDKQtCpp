/*    */ package com.xiaoyi.action;
/*    */ 
/*    */ public enum AutoPowerOff
/*    */ {
/*  6 */   Unknown, 
/*    */ 
/*  8 */   a_Off, 
/*    */ 
/* 10 */   a_3m, 
/*    */ 
/* 12 */   a_5m, 
/*    */ 
/* 14 */   a_10m;
/*    */ 
/*    */   static AutoPowerOff parse(String str) {
/* 17 */     switch (str) {
/*    */     default:
/* 19 */       return Unknown;
/*    */     case "off":
/* 22 */       return a_Off;
/*    */     case "3 minutes":
/* 25 */       return a_3m;
/*    */     case "5 minutes":
/* 28 */       return a_5m;
/*    */     case "10 minutes":
/*    */     }
/* 31 */     return a_10m;
/*    */   }
/*    */ 
/*    */   static String getString(AutoPowerOff autoPowerOff)
/*    */   {
/* 36 */     switch (1.$SwitchMap$com$xiaoyi$action$AutoPowerOff[autoPowerOff.ordinal()]) {
/*    */     case 1:
/* 38 */       return "off";
/*    */     case 2:
/* 41 */       return "3 minutes";
/*    */     case 3:
/* 44 */       return "5 minutes";
/*    */     case 4:
/* 47 */       return "10 minutes";
/*    */     }
/*    */ 
/* 50 */     return "";
/*    */   }
/*    */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.AutoPowerOff
 * JD-Core Version:    0.6.2
 */