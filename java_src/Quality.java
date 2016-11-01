/*    */ package com.xiaoyi.action;
/*    */ 
/*    */ public enum Quality
/*    */ {
/*  6 */   Unknown, 
/*    */ 
/*  8 */   Low, 
/*    */ 
/* 10 */   Middle, 
/*    */ 
/* 12 */   High;
/*    */ 
/*    */   static Quality parse(String str) {
/* 15 */     switch (str) {
/*    */     default:
/* 17 */       return Unknown;
/*    */     case "S.Fine":
/* 20 */       return High;
/*    */     case "Fine":
/* 23 */       return Middle;
/*    */     case "Normal":
/*    */     }
/* 26 */     return Low;
/*    */   }
/*    */ 
/*    */   static String getString(Quality quality)
/*    */   {
/* 31 */     switch (1.$SwitchMap$com$xiaoyi$action$Quality[quality.ordinal()]) {
/*    */     case 1:
/* 33 */       return "S.Fine";
/*    */     case 2:
/* 36 */       return "Fine";
/*    */     case 3:
/* 39 */       return "Normal";
/*    */     }
/*    */ 
/* 42 */     return "";
/*    */   }
/*    */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.Quality
 * JD-Core Version:    0.6.2
 */