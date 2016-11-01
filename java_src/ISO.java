/*    */ package com.xiaoyi.action;
/*    */ 
/*    */ public enum ISO
/*    */ {
/*  6 */   Unknown, 
/*    */ 
/*  8 */   iso_Auto, 
/*    */ 
/* 10 */   iso_100, 
/*    */ 
/* 12 */   iso_200, 
/*    */ 
/* 14 */   iso_400, 
/*    */ 
/* 16 */   iso_800, 
/*    */ 
/* 18 */   iso_1600, 
/*    */ 
/* 20 */   iso_6400;
/*    */ 
/*    */   static ISO parse(String str) {
/* 23 */     switch (str) {
/*    */     default:
/* 25 */       return Unknown;
/*    */     case "auto":
/* 28 */       return iso_Auto;
/*    */     case "100":
/* 31 */       return iso_100;
/*    */     case "200":
/* 34 */       return iso_200;
/*    */     case "400":
/* 37 */       return iso_400;
/*    */     case "800":
/* 40 */       return iso_800;
/*    */     case "1600":
/* 43 */       return iso_1600;
/*    */     case "6400":
/*    */     }
/* 46 */     return iso_6400;
/*    */   }
/*    */ 
/*    */   static String getString(ISO iso)
/*    */   {
/* 51 */     switch (1.$SwitchMap$com$xiaoyi$action$ISO[iso.ordinal()]) {
/*    */     default:
/* 53 */       return "";
/*    */     case 1:
/* 56 */       return "auto";
/*    */     case 2:
/* 59 */       return "100";
/*    */     case 3:
/* 62 */       return "200";
/*    */     case 4:
/* 65 */       return "400";
/*    */     case 5:
/* 68 */       return "800";
/*    */     case 6:
/* 71 */       return "1600";
/*    */     case 7:
/*    */     }
/* 74 */     return "6400";
/*    */   }
/*    */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.ISO
 * JD-Core Version:    0.6.2
 */