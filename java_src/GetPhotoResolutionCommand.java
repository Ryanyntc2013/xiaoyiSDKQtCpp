/*     */ package com.xiaoyi.action;
/*     */ 
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ final class GetPhotoResolutionCommand extends ActionCameraCommand
/*     */ {
/*     */   private ActionCameraCommandCallback1<PhotoResolution> mSuccess;
/*     */ 
/*     */   public GetPhotoResolutionCommand(ActionCameraCommandCallback1<PhotoResolution> success, ActionCameraCommandCallback1<Error> fail)
/*     */   {
/* 205 */     super(1, null, fail);
/* 206 */     this.mSuccess = success;
/*     */   }
/*     */ 
/*     */   public JSONObject getData() throws JSONException
/*     */   {
/* 211 */     return super.getData().put("type", "photo_size");
/*     */   }
/*     */ 
/*     */   protected Error onChildSuccess(JSONObject response) throws JSONException
/*     */   {
/* 216 */     if (this.mSuccess != null) {
/* 217 */       this.mSuccess.onInvoke(PhotoResolution.parse(response.getString("param")));
/*     */     }
/* 219 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.GetPhotoResolutionCommand
 * JD-Core Version:    0.6.2
 */