/*     */ package com.xiaoyi.action;
/*     */ 
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ final class SetVideoResolutionCommand extends ActionCameraCommand
/*     */ {
/*     */   private VideoResolution mResolution;
/*     */ 
/*     */   public SetVideoResolutionCommand(VideoResolution resolution, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*     */   {
/* 190 */     super(2, success, fail);
/* 191 */     this.mResolution = resolution;
/*     */   }
/*     */ 
/*     */   public JSONObject getData() throws JSONException
/*     */   {
/* 196 */     return super.getData().put("type", "video_resolution")
/* 197 */       .put("param", 
/* 197 */       VideoResolution.getString(this.mResolution));
/*     */   }
/*     */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.SetVideoResolutionCommand
 * JD-Core Version:    0.6.2
 */