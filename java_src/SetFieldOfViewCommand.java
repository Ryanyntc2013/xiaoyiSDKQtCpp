/*     */ package com.xiaoyi.action;
/*     */ 
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ final class SetFieldOfViewCommand extends ActionCameraCommand
/*     */ {
/*     */   private String mSettingName;
/*     */   private FieldOfView mFieldOfView;
/*     */ 
/*     */   public SetFieldOfViewCommand(String settingName, FieldOfView fieldOfView, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*     */   {
/* 571 */     super(2, success, fail);
/* 572 */     this.mSettingName = settingName;
/* 573 */     this.mFieldOfView = fieldOfView;
/*     */   }
/*     */ 
/*     */   public JSONObject getData() throws JSONException
/*     */   {
/* 578 */     return super.getData().put("type", this.mSettingName)
/* 579 */       .put("param", 
/* 579 */       FieldOfView.getString(this.mFieldOfView));
/*     */   }
/*     */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.SetFieldOfViewCommand
 * JD-Core Version:    0.6.2
 */