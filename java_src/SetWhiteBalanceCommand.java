/*     */ package com.xiaoyi.action;
/*     */ 
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ final class SetWhiteBalanceCommand extends ActionCameraCommand
/*     */ {
/*     */   private String mBalanaceSettingName;
/*     */   private WhiteBalance mWhiteBalance;
/*     */ 
/*     */   public SetWhiteBalanceCommand(String balanceName, WhiteBalance whiteBalance, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*     */   {
/* 370 */     super(2, success, fail);
/* 371 */     this.mBalanaceSettingName = balanceName;
/* 372 */     this.mWhiteBalance = whiteBalance;
/*     */   }
/*     */ 
/*     */   public JSONObject getData() throws JSONException
/*     */   {
/* 377 */     return super.getData().put("type", this.mBalanaceSettingName)
/* 378 */       .put("param", 
/* 378 */       WhiteBalance.getString(this.mWhiteBalance));
/*     */   }
/*     */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.SetWhiteBalanceCommand
 * JD-Core Version:    0.6.2
 */