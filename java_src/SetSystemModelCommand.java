/*     */ package com.xiaoyi.action;
/*     */ 
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ final class SetSystemModelCommand extends ActionCameraCommand
/*     */ {
/*     */   private SystemMode mMode;
/*     */ 
/*     */   public SetSystemModelCommand(SystemMode mode, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*     */   {
/* 331 */     super(2, success, fail);
/* 332 */     this.mMode = mode;
/*     */   }
/*     */ 
/*     */   public JSONObject getData() throws JSONException
/*     */   {
/* 337 */     return super.getData().put("type", "system_mode").put("param", ActionCameraSettings.getString(this.mMode));
/*     */   }
/*     */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.SetSystemModelCommand
 * JD-Core Version:    0.6.2
 */