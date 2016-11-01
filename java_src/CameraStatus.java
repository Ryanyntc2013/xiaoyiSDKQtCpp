/*    */ package com.xiaoyi.action;
/*    */ 
/*    */ public enum CameraStatus
/*    */ {
/*  6 */   Unknown, 
/*    */ 
/*  8 */   Idle, 
/*    */ 
/* 10 */   Recording, 
/*    */ 
/* 12 */   ViewFinderStarted;
/*    */ 
/*    */   static CameraStatus parse(String str) {
/* 15 */     switch (str) {
/*    */     case "idle":
/* 17 */       return Idle;
/*    */     case "record":
/* 20 */       return Recording;
/*    */     case "vf":
/* 23 */       return ViewFinderStarted;
/*    */     }
/*    */ 
/* 26 */     return Unknown;
/*    */   }
/*    */ 
/*    */   static String getString(CameraStatus status)
/*    */   {
/* 31 */     switch (1.$SwitchMap$com$xiaoyi$action$CameraStatus[status.ordinal()]) {
/*    */     case 1:
/* 33 */       return "idle";
/*    */     case 2:
/* 36 */       return "record";
/*    */     case 3:
/* 39 */       return "vf";
/*    */     }
/*    */ 
/* 42 */     return "";
/*    */   }
/*    */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.CameraStatus
 * JD-Core Version:    0.6.2
 */