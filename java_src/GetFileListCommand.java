/*     */ package com.xiaoyi.action;
/*     */ 
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.json.JSONArray;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ final class GetFileListCommand extends ActionCameraCommand
/*     */ {
/* 624 */   private static Pattern fileSizeAndTimePattern = Pattern.compile("^(\\d+) bytes\\|(\\d\\d\\d\\d-\\d\\d-\\d\\d \\d\\d:\\d\\d:\\d\\d)$");
/* 625 */   private static SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*     */   private ActionCameraCommandCallback1<File[]> mSuccess;
/*     */ 
/*     */   public GetFileListCommand(ActionCameraCommandCallback1<File[]> success, ActionCameraCommandCallback1<Error> fail)
/*     */   {
/* 629 */     super(1282, null, fail);
/* 630 */     this.mSuccess = success;
/*     */   }
/*     */ 
/*     */   public JSONObject getData() throws JSONException
/*     */   {
/* 635 */     return super.getData().put("param", "/tmp/fuse_d/DCIM/100MEDIA");
/*     */   }
/*     */ 
/*     */   protected Error onChildSuccess(JSONObject response) throws JSONException
/*     */   {
/* 640 */     ArrayList files = new ArrayList();
/* 641 */     JSONArray array = response.getJSONArray("listing");
/* 642 */     for (int i = 0; i < array.length(); i++) {
/* 643 */       JSONObject obj = array.getJSONObject(i);
/* 644 */       Iterator it = obj.keys();
/* 645 */       while (it.hasNext()) {
/* 646 */         String name = (String)it.next();
/* 647 */         String value = obj.getString(name);
/* 648 */         Matcher matcher = fileSizeAndTimePattern.matcher(value);
/* 649 */         if (!matcher.find()) {
/* 650 */           Log.error(this, "The format of file is incorrect: " + value);
/*     */         }
/*     */         else {
/*     */           try
/*     */           {
/* 655 */             long size = Long.parseLong(matcher.group(1));
/* 656 */             Date time = timeFormat.parse(matcher.group(2));
/* 657 */             files.add(new File(name, size, time));
/*     */           } catch (ParseException ex) {
/* 659 */             Log.error(this, "The format of datetime is incorrect: " + matcher.group(1));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 665 */     if (this.mSuccess != null) {
/* 666 */       File[] res = new File[files.size()];
/* 667 */       this.mSuccess.onInvoke(files.toArray(res));
/*     */     }
/* 669 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.GetFileListCommand
 * JD-Core Version:    0.6.2
 */