/*    */ package com.xiaoyi.action;
/*    */ 
/*    */ public enum FieldOfView
/*    */ {
/*  6 */   Unknown, 
/*    */ 
/*  8 */   Wide, 
/*    */ 
/* 10 */   Medium, 
/*    */ 
/* 12 */   Narrow;
/*    */ 
/*    */   static FieldOfView parse(String str) {
/* 15 */     switch (str) {
/*    */     default:
/* 17 */       return Unknown;
/*    */     case "wide":
/* 20 */       return Wide;
/*    */     case "medium":
/* 23 */       return Medium;
/*    */     case "narrow":
/*    */     }
/* 26 */     return Narrow;
/*    */   }
/*    */ 
/*    */   static String getString(FieldOfView fieldOfView)
/*    */   {
/* 31 */     switch (1.$SwitchMap$com$xiaoyi$action$FieldOfView[fieldOfView.ordinal()]) {
/*    */     case 1:
/* 33 */       return "wide";
/*    */     case 2:
/* 36 */       return "medium";
/*    */     case 3:
/* 39 */       return "narrow";
/*    */     }
/*    */ 
/* 42 */     return "";
/*    */   }
/*    */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.FieldOfView
 * JD-Core Version:    0.6.2
 */