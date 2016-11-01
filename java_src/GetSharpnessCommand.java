/*     */ package com.xiaoyi.action;
/*     */ 
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ final class GetSharpnessCommand extends ActionCameraCommand
/*     */ {
/*     */   private String mSettingName;
/*     */   private ActionCameraCommandCallback1<Sharpness> mSuccess;
/*     */ 
/*     */   public GetSharpnessCommand(String settingName, ActionCameraCommandCallback1<Sharpness> success, ActionCameraCommandCallback1<Error> fail)
/*     */   {
/* 506 */     super(1, null, fail);
/* 507 */     this.mSettingName = settingName;
/* 508 */     this.mSuccess = success;
/*     */   }
/*     */ 
/*     */   public JSONObject getData() throws JSONException
/*     */   {
/* 513 */     return super.getData().put("type", this.mSettingName);
/*     */   }
/*     */ 
/*     */   protected Error onChildSuccess(JSONObject response) throws JSONException
/*     */   {
/* 518 */     if (this.mSuccess != null) {
/* 519 */       this.mSuccess.onInvoke(Sharpness.parse(response.getString("param")));
/*     */     }
/* 521 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.GetSharpnessCommand
 * JD-Core Version:    0.6.2
 */