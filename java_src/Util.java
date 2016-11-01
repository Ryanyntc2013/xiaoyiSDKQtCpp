/*    */ package com.xiaoyi.action;
/*    */ 
/*    */ public class Util
/*    */ {
/*    */   public static String objAddress(Object obj)
/*    */   {
/*  5 */     return obj.getClass().getName() + "@" + Integer.toHexString(obj.hashCode());
/*    */   }
/*    */ 
/*    */   public static void Assert(boolean condition) {
/*  9 */     assert (condition);
/*    */   }
/*    */ 
/*    */   public static int parseInt(String str)
/*    */   {
/*    */     try {
/* 15 */       for (int digitEndPos = 0; 
/* 16 */         digitEndPos < str.length(); digitEndPos++) {
/* 17 */         char ch = str.charAt(digitEndPos);
/* 18 */         if ((ch < '0') || (ch > '9'))
/*    */         {
/*    */           break;
/*    */         }
/*    */       }
/* 23 */       return Integer.parseInt(str.substring(0, digitEndPos)); } catch (Exception ex) {
/*    */     }
/* 25 */     return 0;
/*    */   }
/*    */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.Util
 * JD-Core Version:    0.6.2
 */