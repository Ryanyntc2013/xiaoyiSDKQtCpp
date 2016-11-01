/*     */ package com.xiaoyi.action;
/*     */ 
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ final class GetWhiteBalanceCommand extends ActionCameraCommand
/*     */ {
/*     */   private String mBalanceSettingName;
/*     */   private ActionCameraCommandCallback1<WhiteBalance> mSuccess;
/*     */ 
/*     */   public GetWhiteBalanceCommand(String balanceSettingName, ActionCameraCommandCallback1<WhiteBalance> success, ActionCameraCommandCallback1<Error> fail)
/*     */   {
/* 346 */     super(1, null, fail);
/* 347 */     this.mBalanceSettingName = balanceSettingName;
/* 348 */     this.mSuccess = success;
/*     */   }
/*     */ 
/*     */   public JSONObject getData() throws JSONException
/*     */   {
/* 353 */     return super.getData().put("type", this.mBalanceSettingName);
/*     */   }
/*     */ 
/*     */   protected Error onChildSuccess(JSONObject response) throws JSONException
/*     */   {
/* 358 */     if (this.mSuccess != null) {
/* 359 */       this.mSuccess.onInvoke(WhiteBalance.parse(response.getString("param")));
/*     */     }
/* 361 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.GetWhiteBalanceCommand
 * JD-Core Version:    0.6.2
 */