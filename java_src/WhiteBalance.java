/*    */ package com.xiaoyi.action;
/*    */ 
/*    */ public enum WhiteBalance
/*    */ {
/*  6 */   Unknown, 
/*    */ 
/*  8 */   Auto, 
/*    */ 
/* 10 */   Native, 
/*    */ 
/* 12 */   Tungsten, 
/*    */ 
/* 14 */   Daylight, 
/*    */ 
/* 16 */   Cloudy;
/*    */ 
/*    */   static WhiteBalance parse(String str) {
/* 19 */     switch (str) {
/*    */     default:
/* 21 */       return Unknown;
/*    */     case "auto":
/* 24 */       return Auto;
/*    */     case "native":
/* 27 */       return Native;
/*    */     case "3000k":
/* 30 */       return Tungsten;
/*    */     case "5500k":
/* 33 */       return Daylight;
/*    */     case "6500k":
/*    */     }
/* 36 */     return Cloudy;
/*    */   }
/*    */ 
/*    */   static String getString(WhiteBalance whiteBalance)
/*    */   {
/* 41 */     switch (1.$SwitchMap$com$xiaoyi$action$WhiteBalance[whiteBalance.ordinal()]) {
/*    */     case 1:
/* 43 */       return "auto";
/*    */     case 2:
/* 46 */       return "native";
/*    */     case 3:
/* 49 */       return "3000k";
/*    */     case 4:
/* 52 */       return "5500k";
/*    */     case 5:
/* 55 */       return "6500k";
/*    */     }
/*    */ 
/* 58 */     return "";
/*    */   }
/*    */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.WhiteBalance
 * JD-Core Version:    0.6.2
 */