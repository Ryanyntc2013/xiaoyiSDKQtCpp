/*     */ package com.xiaoyi.action;
/*     */ 
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ final class GetVideoResolutionCommand extends ActionCameraCommand
/*     */ {
/*     */   private ActionCameraCommandCallback1<VideoResolution> mSuccess;
/*     */ 
/*     */   public GetVideoResolutionCommand(ActionCameraCommandCallback1<VideoResolution> success, ActionCameraCommandCallback1<Error> fail)
/*     */   {
/* 168 */     super(1, null, fail);
/* 169 */     this.mSuccess = success;
/*     */   }
/*     */ 
/*     */   public JSONObject getData() throws JSONException
/*     */   {
/* 174 */     return super.getData().put("type", "video_resolution");
/*     */   }
/*     */ 
/*     */   protected Error onChildSuccess(JSONObject response) throws JSONException
/*     */   {
/* 179 */     if (this.mSuccess != null) {
/* 180 */       this.mSuccess.onInvoke(VideoResolution.parse(response.getString("param")));
/*     */     }
/* 182 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.GetVideoResolutionCommand
 * JD-Core Version:    0.6.2
 */