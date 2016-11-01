/*     */ package com.xiaoyi.action;
/*     */ 
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ final class GetISOCommand extends ActionCameraCommand
/*     */ {
/*     */   private String mISOSettingName;
/*     */   private ActionCameraCommandCallback1<ISO> mSuccess;
/*     */ 
/*     */   public GetISOCommand(String isoSettingName, ActionCameraCommandCallback1<ISO> success, ActionCameraCommandCallback1<Error> fail)
/*     */   {
/* 387 */     super(1, null, fail);
/* 388 */     this.mISOSettingName = isoSettingName;
/* 389 */     this.mSuccess = success;
/*     */   }
/*     */ 
/*     */   public JSONObject getData() throws JSONException
/*     */   {
/* 394 */     return super.getData().put("type", this.mISOSettingName);
/*     */   }
/*     */ 
/*     */   protected Error onChildSuccess(JSONObject response) throws JSONException
/*     */   {
/* 399 */     if (this.mSuccess != null) {
/* 400 */       this.mSuccess.onInvoke(ISO.parse(response.getString("param")));
/*     */     }
/* 402 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.GetISOCommand
 * JD-Core Version:    0.6.2
 */