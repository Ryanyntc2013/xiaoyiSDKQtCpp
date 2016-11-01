/*    */ package com.xiaoyi.action;
/*    */ 
/*    */ public enum Sharpness
/*    */ {
/*  6 */   Unknown, 
/*    */ 
/*  8 */   Low, 
/*    */ 
/* 10 */   Medium, 
/*    */ 
/* 12 */   High;
/*    */ 
/*    */   static Sharpness parse(String str) {
/* 15 */     switch (str) {
/*    */     default:
/* 17 */       return Unknown;
/*    */     case "low":
/* 20 */       return Low;
/*    */     case "medium":
/* 23 */       return Medium;
/*    */     case "high":
/*    */     }
/* 26 */     return High;
/*    */   }
/*    */ 
/*    */   static String getString(Sharpness sharpness)
/*    */   {
/* 31 */     switch (1.$SwitchMap$com$xiaoyi$action$Sharpness[sharpness.ordinal()]) {
/*    */     case 1:
/* 33 */       return "low";
/*    */     case 2:
/* 36 */       return "medium";
/*    */     case 3:
/* 39 */       return "high";
/*    */     }
/*    */ 
/* 42 */     return "";
/*    */   }
/*    */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.Sharpness
 * JD-Core Version:    0.6.2
 */