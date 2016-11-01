/*     */ package com.xiaoyi.action;
/*     */ 
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ final class SetPhotoResolutionCommand extends ActionCameraCommand
/*     */ {
/*     */   private PhotoResolution mPhotoResolution;
/*     */ 
/*     */   public SetPhotoResolutionCommand(PhotoResolution photoResolution, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*     */   {
/* 227 */     super(2, success, fail);
/* 228 */     this.mPhotoResolution = photoResolution;
/*     */   }
/*     */ 
/*     */   public JSONObject getData() throws JSONException
/*     */   {
/* 233 */     return super.getData().put("type", "photo_size")
/* 234 */       .put("param", 
/* 234 */       PhotoResolution.getString(this.mPhotoResolution));
/*     */   }
/*     */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.SetPhotoResolutionCommand
 * JD-Core Version:    0.6.2
 */