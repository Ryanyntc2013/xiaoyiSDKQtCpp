/*     */ package com.xiaoyi.action;
/*     */ 
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ final class GetStatusCommand extends ActionCameraCommand
/*     */ {
/*     */   private ActionCameraCommandCallback1<CameraStatus> mSuccess;
/*     */ 
/*     */   public GetStatusCommand(ActionCameraCommandCallback1<CameraStatus> success, ActionCameraCommandCallback1<Error> fail)
/*     */   {
/* 146 */     super(1, null, fail);
/* 147 */     this.mSuccess = success;
/*     */   }
/*     */ 
/*     */   public JSONObject getData() throws JSONException
/*     */   {
/* 152 */     return super.getData().put("type", "app_status");
/*     */   }
/*     */ 
/*     */   protected Error onChildSuccess(JSONObject response) throws JSONException
/*     */   {
/* 157 */     if (this.mSuccess != null) {
/* 158 */       this.mSuccess.onInvoke(CameraStatus.parse(response.getString("param")));
/*     */     }
/* 160 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.GetStatusCommand
 * JD-Core Version:    0.6.2
 */