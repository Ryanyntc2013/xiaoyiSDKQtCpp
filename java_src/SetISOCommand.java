/*     */ package com.xiaoyi.action;
/*     */ 
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ final class SetISOCommand extends ActionCameraCommand
/*     */ {
/*     */   private String mISOSettingName;
/*     */   private ISO mISO;
/*     */ 
/*     */   public SetISOCommand(String isoSettingName, ISO iso, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*     */   {
/* 411 */     super(2, success, fail);
/* 412 */     this.mISOSettingName = isoSettingName;
/* 413 */     this.mISO = iso;
/*     */   }
/*     */ 
/*     */   public JSONObject getData() throws JSONException
/*     */   {
/* 418 */     return super.getData().put("type", this.mISOSettingName)
/* 419 */       .put("param", 
/* 419 */       ISO.getString(this.mISO));
/*     */   }
/*     */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.SetISOCommand
 * JD-Core Version:    0.6.2
 */