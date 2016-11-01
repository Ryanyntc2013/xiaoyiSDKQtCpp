/*     */ package com.xiaoyi.action;
/*     */ 
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ final class StartSessionCommand extends ActionCameraCommand
/*     */ {
/*     */   private final ActionCameraCommandCallback2<Integer, String> mSuccess;
/*     */ 
/*     */   public StartSessionCommand(ActionCameraCommandCallback2<Integer, String> success, ActionCameraCommandCallback1<Error> fail)
/*     */   {
/* 291 */     super(257, null, fail);
/* 292 */     this.mSuccess = success;
/*     */   }
/*     */ 
/*     */   protected Error onChildSuccess(JSONObject response) throws JSONException
/*     */   {
/* 297 */     int sessionId = response.getInt("param");
/* 298 */     String rtspUrl = null;
/* 299 */     if (response.has("rtsp")) {
/* 300 */       rtspUrl = response.getString("rtsp");
/*     */     }
/* 302 */     if (this.mSuccess != null) {
/* 303 */       this.mSuccess.onInvoke(Integer.valueOf(sessionId), rtspUrl);
/*     */     }
/* 305 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.StartSessionCommand
 * JD-Core Version:    0.6.2
 */