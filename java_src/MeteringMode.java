/*    */ package com.xiaoyi.action;
/*    */ 
/*    */ public enum MeteringMode
/*    */ {
/*  6 */   Unknown, 
/*    */ 
/*  8 */   Center, 
/*    */ 
/* 10 */   Average, 
/*    */ 
/* 12 */   Spot;
/*    */ 
/*    */   static MeteringMode parse(String str) {
/* 15 */     switch (str) {
/*    */     default:
/* 17 */       return Unknown;
/*    */     case "center":
/* 20 */       return Center;
/*    */     case "average":
/* 23 */       return Average;
/*    */     case "spot":
/*    */     }
/* 26 */     return Spot;
/*    */   }
/*    */ 
/*    */   static String getString(MeteringMode meteringMode)
/*    */   {
/* 31 */     switch (1.$SwitchMap$com$xiaoyi$action$MeteringMode[meteringMode.ordinal()]) {
/*    */     case 1:
/* 33 */       return "center";
/*    */     case 2:
/* 36 */       return "average";
/*    */     case 3:
/* 39 */       return "spot";
/*    */     }
/*    */ 
/* 42 */     return "";
/*    */   }
/*    */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.MeteringMode
 * JD-Core Version:    0.6.2
 */