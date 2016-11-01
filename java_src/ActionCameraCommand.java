/*    */ package com.xiaoyi.action;
/*    */ 
/*    */ import org.json.JSONException;
/*    */ import org.json.JSONObject;
/*    */ 
/*    */ abstract class ActionCameraCommand
/*    */ {
/*    */   private final int mCommandId;
/*    */   private final ActionCameraCommandCallback mSuccess;
/*    */   private final ActionCameraCommandCallback1<Error> mFail;
/*    */ 
/*    */   public int getCommandId()
/*    */   {
/* 19 */     return this.mCommandId;
/*    */   }
/*    */ 
/*    */   public ActionCameraCommand(int commandId, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*    */   {
/* 26 */     this.mSuccess = success;
/* 27 */     this.mCommandId = commandId;
/* 28 */     this.mFail = fail;
/*    */   }
/*    */ 
/*    */   public JSONObject getData() throws JSONException {
/* 32 */     JSONObject obj = new JSONObject();
/* 33 */     obj.put("msg_id", this.mCommandId);
/* 34 */     return obj;
/*    */   }
/*    */ 
/*    */   public Error onSuccess(JSONObject response) {
/* 38 */     Error error = null;
/*    */     try {
/* 40 */       int rval = response.getInt("rval");
/* 41 */       if (rval != 0) {
/* 42 */         error = new Error(ErrorCode.CommandFailed, new Error(rval, new Error(ErrorCode.ConvertFirmwareError(rval))));
/*    */       }
/*    */       else
/* 45 */         error = onChildSuccess(response);
/*    */     }
/*    */     catch (JSONException ex) {
/* 48 */       error = new Error(ErrorCode.InvalidResponse);
/*    */     }
/*    */ 
/* 51 */     if (error != null) {
/* 52 */       onFail(error);
/*    */     }
/* 54 */     return error;
/*    */   }
/*    */ 
/*    */   public void onFail(Error error) {
/* 58 */     if (this.mFail != null)
/* 59 */       this.mFail.onInvoke(error);
/*    */   }
/*    */ 
/*    */   protected Error onChildSuccess(JSONObject response) throws JSONException
/*    */   {
/* 64 */     if (this.mSuccess != null) {
/* 65 */       this.mSuccess.onInvoke();
/*    */     }
/* 67 */     return null;
/*    */   }
/*    */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.ActionCameraCommand
 * JD-Core Version:    0.6.2
 */