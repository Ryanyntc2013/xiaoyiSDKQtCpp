/*     */ package com.xiaoyi.action;
/*     */ 
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ final class SetSettingCommand extends ActionCameraCommand
/*     */ {
/*     */   private String mSettingName;
/*     */   private String mValue;
/*     */ 
/*     */   public SetSettingCommand(String settingName, String value, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*     */   {
/* 612 */     super(2, success, fail);
/* 613 */     this.mSettingName = settingName;
/* 614 */     this.mValue = value;
/*     */   }
/*     */ 
/*     */   public JSONObject getData() throws JSONException
/*     */   {
/* 619 */     return super.getData().put("type", this.mSettingName).put("param", this.mValue);
/*     */   }
/*     */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.SetSettingCommand
 * JD-Core Version:    0.6.2
 */