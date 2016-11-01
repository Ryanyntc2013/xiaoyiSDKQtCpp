/*    */ package com.xiaoyi.action;
/*    */ 
/*    */ public enum TimeLapseVideoInterval
/*    */ {
/*  6 */   Unknown, 
/*    */ 
/*  8 */   t_0_5s, 
/*    */ 
/* 10 */   t_1s, 
/*    */ 
/* 12 */   t_2s, 
/*    */ 
/* 14 */   t_5s, 
/*    */ 
/* 16 */   t_10s, 
/*    */ 
/* 18 */   t_30s, 
/*    */ 
/* 20 */   t_60s;
/*    */ 
/*    */   static TimeLapseVideoInterval parse(String str) {
/* 23 */     switch (str) {
/*    */     default:
/* 25 */       return Unknown;
/*    */     case "0.5":
/* 28 */       return t_0_5s;
/*    */     case "1":
/* 31 */       return t_1s;
/*    */     case "2":
/* 34 */       return t_2s;
/*    */     case "5":
/* 37 */       return t_5s;
/*    */     case "10":
/* 40 */       return t_10s;
/*    */     case "30":
/* 43 */       return t_30s;
/*    */     case "60":
/*    */     }
/* 46 */     return t_60s;
/*    */   }
/*    */ 
/*    */   static String getString(TimeLapseVideoInterval interval)
/*    */   {
/* 51 */     switch (1.$SwitchMap$com$xiaoyi$action$TimeLapseVideoInterval[interval.ordinal()]) {
/*    */     case 1:
/* 53 */       return "0.5";
/*    */     case 2:
/* 56 */       return "1";
/*    */     case 3:
/* 59 */       return "2";
/*    */     case 4:
/* 62 */       return "5";
/*    */     case 5:
/* 65 */       return "10";
/*    */     case 6:
/* 68 */       return "30";
/*    */     case 7:
/* 71 */       return "60";
/*    */     }
/*    */ 
/* 74 */     return "";
/*    */   }
/*    */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.TimeLapseVideoInterval
 * JD-Core Version:    0.6.2
 */