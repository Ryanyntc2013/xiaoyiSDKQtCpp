/*     */ package com.xiaoyi.action;
/*     */ 
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ final class SetExposureValueCommand extends ActionCameraCommand
/*     */ {
/*     */   private String mExposureValueSettingName;
/*     */   private ExposureValue mExposureValue;
/*     */ 
/*     */   public SetExposureValueCommand(String evSettingName, ExposureValue ev, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*     */   {
/* 452 */     super(2, success, fail);
/* 453 */     this.mExposureValueSettingName = evSettingName;
/* 454 */     this.mExposureValue = ev;
/*     */   }
/*     */ 
/*     */   public JSONObject getData() throws JSONException
/*     */   {
/* 459 */     return super.getData().put("type", this.mExposureValueSettingName)
/* 460 */       .put("param", 
/* 460 */       ExposureValue.getString(this.mExposureValue));
/*     */   }
/*     */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.SetExposureValueCommand
 * JD-Core Version:    0.6.2
 */