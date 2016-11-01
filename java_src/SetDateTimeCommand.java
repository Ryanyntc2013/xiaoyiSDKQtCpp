/*     */ package com.xiaoyi.action;
/*     */ 
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ final class SetDateTimeCommand extends ActionCameraCommand
/*     */ {
/*     */   private final Date mDatetime;
/*     */ 
/*     */   public SetDateTimeCommand(Date datetime, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*     */   {
/* 275 */     super(2, success, fail);
/* 276 */     this.mDatetime = datetime;
/*     */   }
/*     */ 
/*     */   public JSONObject getData() throws JSONException
/*     */   {
/* 281 */     return super.getData().put("type", "camera_clock")
/* 282 */       .put("param", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
/* 282 */       .format(this.mDatetime));
/*     */   }
/*     */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.SetDateTimeCommand
 * JD-Core Version:    0.6.2
 */