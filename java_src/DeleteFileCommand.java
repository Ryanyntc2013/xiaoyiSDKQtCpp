/*     */ package com.xiaoyi.action;
/*     */ 
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ final class DeleteFileCommand extends ActionCameraCommand
/*     */ {
/*     */   private String mFileName;
/*     */ 
/*     */   public DeleteFileCommand(String fileName, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*     */   {
/* 677 */     super(1281, success, fail);
/* 678 */     this.mFileName = fileName;
/*     */   }
/*     */ 
/*     */   public JSONObject getData() throws JSONException
/*     */   {
/* 683 */     return super.getData().put("param", "/tmp/fuse_d/DCIM/100MEDIA/" + this.mFileName);
/*     */   }
/*     */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.DeleteFileCommand
 * JD-Core Version:    0.6.2
 */