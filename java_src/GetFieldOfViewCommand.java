/*     */ package com.xiaoyi.action;
/*     */ 
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ final class GetFieldOfViewCommand extends ActionCameraCommand
/*     */ {
/*     */   private String mSettingName;
/*     */   private ActionCameraCommandCallback1<FieldOfView> mSuccess;
/*     */ 
/*     */   public GetFieldOfViewCommand(String settingName, ActionCameraCommandCallback1<FieldOfView> success, ActionCameraCommandCallback1<Error> fail)
/*     */   {
/* 547 */     super(1, null, fail);
/* 548 */     this.mSettingName = settingName;
/* 549 */     this.mSuccess = success;
/*     */   }
/*     */ 
/*     */   public JSONObject getData() throws JSONException
/*     */   {
/* 554 */     return super.getData().put("type", this.mSettingName);
/*     */   }
/*     */ 
/*     */   protected Error onChildSuccess(JSONObject response) throws JSONException
/*     */   {
/* 559 */     if (this.mSuccess != null) {
/* 560 */       this.mSuccess.onInvoke(FieldOfView.parse(response.getString("param")));
/*     */     }
/* 562 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.GetFieldOfViewCommand
 * JD-Core Version:    0.6.2
 */