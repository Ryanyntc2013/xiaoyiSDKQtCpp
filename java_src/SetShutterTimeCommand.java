/*     */ package com.xiaoyi.action;
/*     */ 
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ final class SetShutterTimeCommand extends ActionCameraCommand
/*     */ {
/*     */   private ShutterTime mShutterTime;
/*     */ 
/*     */   public SetShutterTimeCommand(ShutterTime shutterTime, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*     */   {
/* 490 */     super(2, success, fail);
/* 491 */     this.mShutterTime = shutterTime;
/*     */   }
/*     */ 
/*     */   public JSONObject getData() throws JSONException
/*     */   {
/* 496 */     return super.getData().put("type", "iq_photo_shutter")
/* 497 */       .put("param", 
/* 497 */       ShutterTime.getString(this.mShutterTime));
/*     */   }
/*     */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.SetShutterTimeCommand
 * JD-Core Version:    0.6.2
 */