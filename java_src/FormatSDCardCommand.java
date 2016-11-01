/*     */ package com.xiaoyi.action;
/*     */ 
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ final class FormatSDCardCommand extends ActionCameraCommand
/*     */ {
/*     */   public FormatSDCardCommand(ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*     */   {
/* 689 */     super(4, success, fail);
/*     */   }
/*     */ 
/*     */   public JSONObject getData() throws JSONException
/*     */   {
/* 694 */     return super.getData().put("param", "D:");
/*     */   }
/*     */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.FormatSDCardCommand
 * JD-Core Version:    0.6.2
 */