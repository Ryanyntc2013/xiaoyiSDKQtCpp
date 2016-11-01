/*     */ package com.xiaoyi.action;
/*     */ 
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ final class CapturePhotoCommand extends ActionCameraCommand
/*     */ {
/*     */   public CapturePhotoCommand(ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*     */   {
/* 318 */     super(16777220, success, fail);
/*     */   }
/*     */ 
/*     */   public JSONObject getData() throws JSONException
/*     */   {
/* 323 */     return super.getData().put("param", "precise quality;off");
/*     */   }
/*     */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.CapturePhotoCommand
 * JD-Core Version:    0.6.2
 */