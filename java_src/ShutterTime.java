/*    */ package com.xiaoyi.action;
/*    */ 
/*    */ public enum ShutterTime
/*    */ {
/*  6 */   Unknown, 
/*    */ 
/*  8 */   st_Auto, 
/*    */ 
/* 10 */   st_2s, 
/*    */ 
/* 12 */   st_5s, 
/*    */ 
/* 14 */   st_10s, 
/*    */ 
/* 16 */   st_20s, 
/*    */ 
/* 18 */   st_30s;
/*    */ 
/*    */   static ShutterTime parse(String str) {
/* 21 */     switch (str) {
/*    */     default:
/* 23 */       return Unknown;
/*    */     case "auto":
/* 26 */       return st_Auto;
/*    */     case "2s":
/* 29 */       return st_2s;
/*    */     case "5s":
/* 32 */       return st_5s;
/*    */     case "10s":
/* 35 */       return st_10s;
/*    */     case "20s":
/* 38 */       return st_20s;
/*    */     case "30s":
/*    */     }
/* 41 */     return st_30s;
/*    */   }
/*    */ 
/*    */   static String getString(ShutterTime shutterTime)
/*    */   {
/* 46 */     switch (1.$SwitchMap$com$xiaoyi$action$ShutterTime[shutterTime.ordinal()]) {
/*    */     default:
/* 48 */       return "";
/*    */     case 1:
/* 51 */       return "auto";
/*    */     case 2:
/* 54 */       return "2s";
/*    */     case 3:
/* 57 */       return "5s";
/*    */     case 4:
/* 60 */       return "10s";
/*    */     case 5:
/* 63 */       return "20s";
/*    */     case 6:
/*    */     }
/* 66 */     return "30s";
/*    */   }
/*    */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.ShutterTime
 * JD-Core Version:    0.6.2
 */