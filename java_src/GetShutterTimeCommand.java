/*     */ package com.xiaoyi.action;
/*     */ 
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ final class GetShutterTimeCommand extends ActionCameraCommand
/*     */ {
/*     */   private ActionCameraCommandCallback1<ShutterTime> mSuccess;
/*     */ 
/*     */   public GetShutterTimeCommand(ActionCameraCommandCallback1<ShutterTime> success, ActionCameraCommandCallback1<Error> fail)
/*     */   {
/* 468 */     super(1, null, fail);
/* 469 */     this.mSuccess = success;
/*     */   }
/*     */ 
/*     */   public JSONObject getData() throws JSONException
/*     */   {
/* 474 */     return super.getData().put("type", "iq_photo_shutter");
/*     */   }
/*     */ 
/*     */   protected Error onChildSuccess(JSONObject response) throws JSONException
/*     */   {
/* 479 */     if (this.mSuccess != null) {
/* 480 */       this.mSuccess.onInvoke(ShutterTime.parse(response.getString("param")));
/*     */     }
/* 482 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.GetShutterTimeCommand
 * JD-Core Version:    0.6.2
 */