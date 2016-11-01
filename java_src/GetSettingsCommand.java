/*     */ package com.xiaoyi.action;
/*     */ 
/*     */ import org.json.JSONArray;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ final class GetSettingsCommand extends ActionCameraCommand
/*     */ {
/*     */   private ActionCameraCommandCallback1<ActionCameraSettings> mSuccess;
/*     */ 
/*     */   public GetSettingsCommand(ActionCameraCommandCallback1<ActionCameraSettings> success, ActionCameraCommandCallback1<Error> fail)
/*     */   {
/* 124 */     super(3, null, fail);
/* 125 */     this.mSuccess = success;
/*     */   }
/*     */ 
/*     */   protected Error onChildSuccess(JSONObject response) throws JSONException
/*     */   {
/* 130 */     JSONArray params = response.getJSONArray("param");
/* 131 */     if (params == null) {
/* 132 */       throw new JSONException("param of GetSetting response is null");
/*     */     }
/*     */ 
/* 135 */     if (this.mSuccess != null) {
/* 136 */       this.mSuccess.onInvoke(new ActionCameraSettings(params));
/*     */     }
/* 138 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.GetSettingsCommand
 * JD-Core Version:    0.6.2
 */