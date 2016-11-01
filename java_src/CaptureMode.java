/*    */ package com.xiaoyi.action;
/*    */ 
/*    */ public enum CaptureMode
/*    */ {
/*  6 */   Unknown, 
/*    */ 
/*  8 */   Normal, 
/*    */ 
/* 10 */   Timelapse;
/*    */ 
/*    */   static CaptureMode parse(String str) {
/* 13 */     switch (str) {
/*    */     case "precise quality":
/* 15 */       return Normal;
/*    */     case "precise quality cont.":
/* 18 */       return Timelapse;
/*    */     }
/*    */ 
/* 21 */     return Unknown;
/*    */   }
/*    */ 
/*    */   static String getString(CaptureMode mode)
/*    */   {
/* 26 */     switch (1.$SwitchMap$com$xiaoyi$action$CaptureMode[mode.ordinal()]) {
/*    */     case 1:
/* 28 */       return "precise quality";
/*    */     case 2:
/* 31 */       return "precise quality cont.";
/*    */     }
/*    */ 
/* 34 */     return "";
/*    */   }
/*    */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.CaptureMode
 * JD-Core Version:    0.6.2
 */