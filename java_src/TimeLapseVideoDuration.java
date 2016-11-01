/*    */ package com.xiaoyi.action;
/*    */ 
/*    */ public enum TimeLapseVideoDuration
/*    */ {
/*  6 */   Unknown, 
/*    */ 
/*  8 */   t_Off, 
/*    */ 
/* 10 */   t_6s, 
/*    */ 
/* 12 */   t_8s, 
/*    */ 
/* 14 */   t_10s, 
/*    */ 
/* 16 */   t_20s, 
/*    */ 
/* 18 */   t_30s, 
/*    */ 
/* 20 */   t_60s, 
/*    */ 
/* 22 */   t_120s;
/*    */ 
/*    */   static TimeLapseVideoDuration parse(String str) {
/* 25 */     switch (str) {
/*    */     default:
/* 27 */       return Unknown;
/*    */     case "off":
/* 30 */       return t_Off;
/*    */     case "6s":
/* 33 */       return t_6s;
/*    */     case "8s":
/* 36 */       return t_8s;
/*    */     case "10s":
/* 39 */       return t_10s;
/*    */     case "20s":
/* 42 */       return t_20s;
/*    */     case "30s":
/* 45 */       return t_30s;
/*    */     case "60s":
/* 48 */       return t_60s;
/*    */     case "120s":
/*    */     }
/* 51 */     return t_120s;
/*    */   }
/*    */ 
/*    */   static String getString(TimeLapseVideoDuration ledMode)
/*    */   {
/* 56 */     switch (1.$SwitchMap$com$xiaoyi$action$TimeLapseVideoDuration[ledMode.ordinal()]) {
/*    */     case 1:
/* 58 */       return "off";
/*    */     case 2:
/* 61 */       return "6s";
/*    */     case 3:
/* 64 */       return "8s";
/*    */     case 4:
/* 67 */       return "10s";
/*    */     case 5:
/* 70 */       return "20s";
/*    */     case 6:
/* 73 */       return "30s";
/*    */     case 7:
/* 76 */       return "60s";
/*    */     case 8:
/* 79 */       return "120s";
/*    */     }
/*    */ 
/* 82 */     return "";
/*    */   }
/*    */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.TimeLapseVideoDuration
 * JD-Core Version:    0.6.2
 */