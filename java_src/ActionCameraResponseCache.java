/*    */ package com.xiaoyi.action;
/*    */ 
/*    */ import org.json.JSONException;
/*    */ 
/*    */ class ActionCameraResponseCache
/*    */ {
/* 29 */   private String mCachedResponseString = "";
/*    */ 
/*    */   public void append(String responseString) {
/* 32 */     this.mCachedResponseString += responseString;
/*    */   }
/*    */ 
/*    */   public void clear() {
/* 36 */     this.mCachedResponseString = "";
/*    */   }
/*    */ 
/*    */   public ActionCameraResponse getResponse()
/*    */     throws JSONException
/*    */   {
/* 43 */     if (this.mCachedResponseString.isEmpty()) {
/* 44 */       return null;
/*    */     }
/*    */ 
/* 48 */     if (this.mCachedResponseString.charAt(0) != '{') {
/* 49 */       throw new JSONException(this.mCachedResponseString + " is not a valid JSON string");
/*    */     }
/*    */ 
/* 52 */     int braceCount = 1;
/* 53 */     for (int i = 1; i < this.mCachedResponseString.length(); i++) {
/* 54 */       char ch = this.mCachedResponseString.charAt(i);
/* 55 */       if (ch == '{') {
/* 56 */         braceCount++;
/*    */       }
/* 58 */       else if (ch == '}') {
/* 59 */         braceCount--;
/* 60 */         if (braceCount == 0) {
/* 61 */           String validJsonString = this.mCachedResponseString.substring(0, i + 1);
/* 62 */           this.mCachedResponseString = this.mCachedResponseString.substring(i + 1);
/* 63 */           return new ActionCameraResponse(validJsonString);
/*    */         }
/*    */       }
/*    */     }
/* 67 */     return null;
/*    */   }
/*    */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.ActionCameraResponseCache
 * JD-Core Version:    0.6.2
 */