/*     */ package com.xiaoyi.action;
/*     */ 
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ final class SetSharpnessCommand extends ActionCameraCommand
/*     */ {
/*     */   private String mSettingName;
/*     */   private Sharpness mSharpness;
/*     */ 
/*     */   public SetSharpnessCommand(String settingName, Sharpness sharpness, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*     */   {
/* 530 */     super(2, success, fail);
/* 531 */     this.mSettingName = settingName;
/* 532 */     this.mSharpness = sharpness;
/*     */   }
/*     */ 
/*     */   public JSONObject getData() throws JSONException
/*     */   {
/* 537 */     return super.getData().put("type", this.mSettingName)
/* 538 */       .put("param", 
/* 538 */       Sharpness.getString(this.mSharpness));
/*     */   }
/*     */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.SetSharpnessCommand
 * JD-Core Version:    0.6.2
 */