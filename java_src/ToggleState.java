/*    */ package com.xiaoyi.action;
/*    */ 
/*    */ public enum ToggleState
/*    */ {
/*  6 */   Unknown, 
/*    */ 
/*  8 */   On, 
/*    */ 
/* 10 */   Off;
/*    */ 
/*    */   static ToggleState parse(String str) {
/* 13 */     switch (str) {
/*    */     default:
/* 15 */       return Unknown;
/*    */     case "on":
/* 18 */       return On;
/*    */     case "off":
/*    */     }
/* 21 */     return Off;
/*    */   }
/*    */ 
/*    */   static String getString(ToggleState enabled)
/*    */   {
/* 26 */     switch (1.$SwitchMap$com$xiaoyi$action$ToggleState[enabled.ordinal()]) {
/*    */     case 1:
/* 28 */       return "on";
/*    */     case 2:
/* 31 */       return "off";
/*    */     }
/*    */ 
/* 34 */     return "";
/*    */   }
/*    */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.ToggleState
 * JD-Core Version:    0.6.2
 */