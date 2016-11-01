/*    */ package com.xiaoyi.action;
/*    */ 
/*    */ public enum VideoStandard
/*    */ {
/*  6 */   Unknown, 
/*    */ 
/*  8 */   PAL, 
/*    */ 
/* 10 */   NTSC;
/*    */ 
/*    */   static VideoStandard parse(String str) {
/* 13 */     switch (str) {
/*    */     default:
/* 15 */       return Unknown;
/*    */     case "PAL":
/* 18 */       return PAL;
/*    */     case "NTSC":
/*    */     }
/* 21 */     return NTSC;
/*    */   }
/*    */ 
/*    */   static String getString(VideoStandard videoStandard)
/*    */   {
/* 26 */     switch (1.$SwitchMap$com$xiaoyi$action$VideoStandard[videoStandard.ordinal()]) {
/*    */     case 1:
/* 28 */       return "PAL";
/*    */     case 2:
/* 31 */       return "NTSC";
/*    */     }
/*    */ 
/* 34 */     return "";
/*    */   }
/*    */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.VideoStandard
 * JD-Core Version:    0.6.2
 */