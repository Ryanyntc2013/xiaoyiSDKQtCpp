/*    */ package com.xiaoyi.action;
/*    */ 
/*    */ public class Error
/*    */ {
/*    */   private int mCode;
/*    */   private Error mSubError;
/*    */   private String mDetail;
/*    */ 
/*    */   public int getCode()
/*    */   {
/* 12 */     return this.mCode;
/*    */   }
/*    */ 
/*    */   public Error getSubError()
/*    */   {
/* 17 */     return this.mSubError;
/*    */   }
/*    */ 
/*    */   public String getDetail()
/*    */   {
/* 22 */     return this.mDetail;
/*    */   }
/*    */ 
/*    */   public Error(int code)
/*    */   {
/* 28 */     this.mCode = code;
/*    */   }
/*    */ 
/*    */   public Error(int code, String detail) {
/* 32 */     this.mCode = code;
/* 33 */     this.mDetail = detail;
/*    */   }
/*    */ 
/*    */   public Error(int code, Error subError) {
/* 37 */     this.mCode = code;
/* 38 */     this.mSubError = subError;
/*    */   }
/*    */ 
/*    */   public Error(int code, Error subError, String detail) {
/* 42 */     this.mCode = code;
/* 43 */     this.mSubError = subError;
/* 44 */     this.mDetail = detail;
/*    */   }
/*    */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.Error
 * JD-Core Version:    0.6.2
 */