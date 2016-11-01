/*    */ package com.xiaoyi.action;
/*    */ 
/*    */ public enum VideoRotateMode
/*    */ {
/*  6 */   Unknown, 
/*    */ 
/*  8 */   Off, 
/*    */ 
/* 10 */   On, 
/*    */ 
/* 12 */   Auto;
/*    */ 
/*    */   static VideoRotateMode parse(String str) {
/* 15 */     switch (str) {
/*    */     default:
/* 17 */       return Unknown;
/*    */     case "off":
/* 20 */       return Off;
/*    */     case "on":
/* 23 */       return On;
/*    */     case "auto":
/*    */     }
/* 26 */     return Auto;
/*    */   }
/*    */ 
/*    */   static String getString(VideoRotateMode mode)
/*    */   {
/* 31 */     switch (1.$SwitchMap$com$xiaoyi$action$VideoRotateMode[mode.ordinal()]) {
/*    */     case 1:
/* 33 */       return "off";
/*    */     case 2:
/* 36 */       return "on";
/*    */     case 3:
/* 39 */       return "auto";
/*    */     }
/*    */ 
/* 42 */     return "";
/*    */   }
/*    */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.VideoRotateMode
 * JD-Core Version:    0.6.2
 */