/*    */ package com.xiaoyi.action;
/*    */ 
/*    */ public enum ExposureValue
/*    */ {
/*  6 */   Unknown, 
/*    */ 
/*  8 */   ev_negative_2, 
/*    */ 
/* 10 */   ev_negative_1_point_5, 
/*    */ 
/* 12 */   ev_negative_1, 
/*    */ 
/* 14 */   ev_negative_0_point_5, 
/*    */ 
/* 16 */   ev_0, 
/*    */ 
/* 18 */   ev_positive_0_point_5, 
/*    */ 
/* 20 */   ev_positive_1, 
/*    */ 
/* 22 */   ev_positive_1_point_5, 
/*    */ 
/* 24 */   ev_positive_2;
/*    */ 
/*    */   static ExposureValue parse(String str) {
/* 27 */     switch (str) {
/*    */     default:
/* 29 */       return Unknown;
/*    */     case "-2.0":
/* 32 */       return ev_negative_2;
/*    */     case "-1.5":
/* 35 */       return ev_negative_1_point_5;
/*    */     case "-1.0":
/* 38 */       return ev_negative_1;
/*    */     case "-0.5":
/* 41 */       return ev_negative_0_point_5;
/*    */     case "0":
/* 44 */       return ev_0;
/*    */     case "+0.5":
/* 47 */       return ev_positive_0_point_5;
/*    */     case "+1.0":
/* 50 */       return ev_positive_1;
/*    */     case "+1.5":
/* 53 */       return ev_positive_1_point_5;
/*    */     case "+2.0":
/*    */     }
/* 56 */     return ev_positive_2;
/*    */   }
/*    */ 
/*    */   static String getString(ExposureValue ev)
/*    */   {
/* 61 */     switch (1.$SwitchMap$com$xiaoyi$action$ExposureValue[ev.ordinal()]) {
/*    */     default:
/* 63 */       return "";
/*    */     case 1:
/* 66 */       return "-2.0";
/*    */     case 2:
/* 69 */       return "-1.5";
/*    */     case 3:
/* 72 */       return "-1.0";
/*    */     case 4:
/* 75 */       return "-0.5";
/*    */     case 5:
/* 78 */       return "0";
/*    */     case 6:
/* 81 */       return "+0.5";
/*    */     case 7:
/* 84 */       return "+1.0";
/*    */     case 8:
/* 87 */       return "+1.5";
/*    */     case 9:
/*    */     }
/* 90 */     return "+2.0";
/*    */   }
/*    */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.ExposureValue
 * JD-Core Version:    0.6.2
 */