/*     */ package com.xiaoyi.action;
/*     */ 
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ final class GetExposureValueCommand extends ActionCameraCommand
/*     */ {
/*     */   private String mExposureValueSettingName;
/*     */   private ActionCameraCommandCallback1<ExposureValue> mSuccess;
/*     */ 
/*     */   public GetExposureValueCommand(String evSettingName, ActionCameraCommandCallback1<ExposureValue> success, ActionCameraCommandCallback1<Error> fail)
/*     */   {
/* 428 */     super(1, null, fail);
/* 429 */     this.mExposureValueSettingName = evSettingName;
/* 430 */     this.mSuccess = success;
/*     */   }
/*     */ 
/*     */   public JSONObject getData() throws JSONException
/*     */   {
/* 435 */     return super.getData().put("type", this.mExposureValueSettingName);
/*     */   }
/*     */ 
/*     */   protected Error onChildSuccess(JSONObject response) throws JSONException
/*     */   {
/* 440 */     if (this.mSuccess != null) {
/* 441 */       this.mSuccess.onInvoke(ExposureValue.parse(response.getString("param")));
/*     */     }
/* 443 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.GetExposureValueCommand
 * JD-Core Version:    0.6.2
 */