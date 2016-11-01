/*    */ package com.xiaoyi.action;
/*    */ 
/*    */ public enum ColorMode
/*    */ {
/*  6 */   Unknown, 
/*    */ 
/*  8 */   YIColor, 
/*    */ 
/* 10 */   Flat;
/*    */ 
/*    */   static ColorMode parse(String str) {
/* 13 */     switch (str) {
/*    */     default:
/* 15 */       return Unknown;
/*    */     case "yi":
/* 18 */       return YIColor;
/*    */     case "flat":
/*    */     }
/* 21 */     return Flat;
/*    */   }
/*    */ 
/*    */   static String getString(ColorMode colorMode)
/*    */   {
/* 26 */     switch (1.$SwitchMap$com$xiaoyi$action$ColorMode[colorMode.ordinal()]) {
/*    */     case 1:
/* 28 */       return "yi";
/*    */     case 2:
/* 31 */       return "flat";
/*    */     }
/*    */ 
/* 34 */     return "";
/*    */   }
/*    */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.ColorMode
 * JD-Core Version:    0.6.2
 */