/*    */ package com.xiaoyi.action;
/*    */ 
/*    */ public enum LEDMode
/*    */ {
/*  6 */   Unknown, 
/*    */ 
/*  8 */   AllOn, 
/*    */ 
/* 10 */   StatusLightOnly, 
/*    */ 
/* 12 */   AllOff;
/*    */ 
/*    */   static LEDMode parse(String str) {
/* 15 */     switch (str) {
/*    */     default:
/* 17 */       return Unknown;
/*    */     case "all enable":
/* 20 */       return AllOn;
/*    */     case "all disable":
/* 23 */       return AllOff;
/*    */     case "status enable":
/*    */     }
/* 26 */     return StatusLightOnly;
/*    */   }
/*    */ 
/*    */   static String getString(LEDMode ledMode)
/*    */   {
/* 31 */     switch (1.$SwitchMap$com$xiaoyi$action$LEDMode[ledMode.ordinal()]) {
/*    */     case 1:
/* 33 */       return "all enable";
/*    */     case 2:
/* 36 */       return "all disable";
/*    */     case 3:
/* 39 */       return "status enable";
/*    */     }
/*    */ 
/* 42 */     return "";
/*    */   }
/*    */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.LEDMode
 * JD-Core Version:    0.6.2
 */