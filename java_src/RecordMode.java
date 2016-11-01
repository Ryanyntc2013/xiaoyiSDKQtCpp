/*    */ package com.xiaoyi.action;
/*    */ 
/*    */ public enum RecordMode
/*    */ {
/*  6 */   Unknown, 
/*    */ 
/*  8 */   Normal, 
/*    */ 
/* 10 */   Timelapse;
/*    */ 
/*    */   static RecordMode parse(String str) {
/* 13 */     switch (str) {
/*    */     case "record":
/* 15 */       return Normal;
/*    */     case "record_timelapse":
/* 18 */       return Timelapse;
/*    */     }
/*    */ 
/* 21 */     return Unknown;
/*    */   }
/*    */ 
/*    */   static String getString(RecordMode mode)
/*    */   {
/* 26 */     switch (1.$SwitchMap$com$xiaoyi$action$RecordMode[mode.ordinal()]) {
/*    */     case 1:
/* 28 */       return "record";
/*    */     case 2:
/* 31 */       return "record_timelapse";
/*    */     }
/*    */ 
/* 34 */     return "";
/*    */   }
/*    */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.RecordMode
 * JD-Core Version:    0.6.2
 */