/*    */ package com.xiaoyi.action;
/*    */ 
/*    */ public enum Timestamp
/*    */ {
/*  6 */   Unknown, 
/*    */ 
/*  8 */   Off, 
/*    */ 
/* 10 */   Time, 
/*    */ 
/* 12 */   Date, 
/*    */ 
/* 14 */   DateAndTime;
/*    */ 
/*    */   static Timestamp parse(String str) {
/* 17 */     switch (str) {
/*    */     default:
/* 19 */       return Unknown;
/*    */     case "off":
/* 22 */       return Off;
/*    */     case "time":
/* 25 */       return Time;
/*    */     case "date":
/* 28 */       return Date;
/*    */     case "date/time":
/*    */     }
/* 31 */     return DateAndTime;
/*    */   }
/*    */ 
/*    */   static String getString(Timestamp timestamp)
/*    */   {
/* 36 */     switch (1.$SwitchMap$com$xiaoyi$action$Timestamp[timestamp.ordinal()]) {
/*    */     case 1:
/* 38 */       return "off";
/*    */     case 2:
/* 41 */       return "time";
/*    */     case 3:
/* 44 */       return "date";
/*    */     case 4:
/* 47 */       return "date/time";
/*    */     }
/*    */ 
/* 50 */     return "";
/*    */   }
/*    */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.Timestamp
 * JD-Core Version:    0.6.2
 */