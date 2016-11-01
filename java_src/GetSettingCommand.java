/*     */ package com.xiaoyi.action;
/*     */ 
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ final class GetSettingCommand extends ActionCameraCommand
/*     */ {
/*     */   private String mSettingName;
/*     */   private ActionCameraCommandCallback1<String> mSuccess;
/*     */ 
/*     */   public GetSettingCommand(String settingName, ActionCameraCommandCallback1<String> success, ActionCameraCommandCallback1<Error> fail)
/*     */   {
/* 588 */     super(1, null, fail);
/* 589 */     this.mSettingName = settingName;
/* 590 */     this.mSuccess = success;
/*     */   }
/*     */ 
/*     */   public JSONObject getData() throws JSONException
/*     */   {
/* 595 */     return super.getData().put("type", this.mSettingName);
/*     */   }
/*     */ 
/*     */   protected Error onChildSuccess(JSONObject response) throws JSONException
/*     */   {
/* 600 */     if (this.mSuccess != null) {
/* 601 */       this.mSuccess.onInvoke(response.getString("param"));
/*     */     }
/* 603 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.GetSettingCommand
 * JD-Core Version:    0.6.2
 */