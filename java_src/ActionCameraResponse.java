/*    */ package com.xiaoyi.action;
/*    */ 
/*    */ import org.json.JSONException;
/*    */ import org.json.JSONObject;
/*    */ 
/*    */ class ActionCameraResponse extends JSONObject
/*    */ {
/*    */   private final int messageId;
/*    */ 
/*    */   public ActionCameraResponse(String responseString)
/*    */     throws JSONException
/*    */   {
/* 10 */     super(responseString);
/*    */ 
/* 12 */     this.messageId = getInt("msg_id");
/*    */   }
/*    */ 
/*    */   public int getMessageId() {
/* 16 */     return this.messageId;
/*    */   }
/*    */ 
/*    */   public boolean isNotification() {
/* 20 */     return (this.messageId == 7) || (this.messageId == 1793);
/*    */   }
/*    */ 
/*    */   public String getNotificationType() throws JSONException {
/* 24 */     return getString("type");
/*    */   }
/*    */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.ActionCameraResponse
 * JD-Core Version:    0.6.2
 */