/*    */ package com.xiaoyi.action;
/*    */ 
/*    */ import org.json.JSONException;
/*    */ import org.json.JSONObject;
/*    */ 
/*    */ final class StartRecordingCommand extends ActionCameraCommand
/*    */ {
/*    */   private final int mHour;
/*    */   private final int mMinute;
/*    */   private final int mSecond;
/*    */ 
/*    */   public StartRecordingCommand(ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*    */   {
/* 78 */     super(513, success, fail);
/* 79 */     this.mHour = 0;
/* 80 */     this.mMinute = 0;
/* 81 */     this.mSecond = 0;
/*    */   }
/*    */ 
/*    */   public StartRecordingCommand(int hour, int minute, int second, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*    */   {
/* 87 */     super(16777247, success, fail);
/* 88 */     this.mHour = hour;
/* 89 */     this.mMinute = minute;
/* 90 */     this.mSecond = second;
/*    */   }
/*    */ 
/*    */   public JSONObject getData() throws JSONException
/*    */   {
/* 95 */     return super.getData().put("Synctime_Hour", this.mHour)
/* 96 */       .put("Synctime_Minute", this.mMinute)
/* 97 */       .put("Synctime_Second", this.mSecond);
/*    */   }
/*    */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.StartRecordingCommand
 * JD-Core Version:    0.6.2
 */