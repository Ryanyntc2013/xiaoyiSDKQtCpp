/*     */ package com.xiaoyi.action;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import org.json.JSONArray;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ final class GetSettingOptions extends ActionCameraCommand
/*     */ {
/*     */   private final String mSettingName;
/*     */   private final ActionCameraCommandCallback1<ArrayList<String>> mSuccess;
/*     */ 
/*     */   public GetSettingOptions(String settingName, ActionCameraCommandCallback1<ArrayList<String>> success, ActionCameraCommandCallback1<Error> fail)
/*     */   {
/* 243 */     super(9, null, fail);
/* 244 */     this.mSettingName = settingName;
/* 245 */     this.mSuccess = success;
/*     */   }
/*     */ 
/*     */   public JSONObject getData() throws JSONException
/*     */   {
/* 250 */     return super.getData().put("param", this.mSettingName);
/*     */   }
/*     */ 
/*     */   protected Error onChildSuccess(JSONObject response) throws JSONException
/*     */   {
/* 255 */     if (response.getString("param").equals(this.mSettingName)) {
/* 256 */       ArrayList res = new ArrayList();
/* 257 */       JSONArray array = response.getJSONArray("options");
/* 258 */       for (int i = 0; i < array.length(); i++) {
/* 259 */         res.add(array.getString(i));
/*     */       }
/* 261 */       if (this.mSuccess != null) {
/* 262 */         this.mSuccess.onInvoke(res);
/*     */       }
/* 264 */       return null;
/*     */     }
/* 266 */     return new Error(ErrorCode.InvalidResponse);
/*     */   }
/*     */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.GetSettingOptions
 * JD-Core Version:    0.6.2
 */