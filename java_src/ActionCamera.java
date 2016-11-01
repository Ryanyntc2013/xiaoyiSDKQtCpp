/*      */ package com.xiaoyi.action;
/*      */ 
/*      */ import com.google.zxing.BarcodeFormat;
/*      */ import com.google.zxing.MultiFormatWriter;
/*      */ import com.google.zxing.WriterException;
/*      */ import com.google.zxing.common.BitMatrix;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.net.HttpURLConnection;
/*      */ import java.net.InetSocketAddress;
/*      */ import java.net.MalformedURLException;
/*      */ import java.net.URL;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.security.InvalidParameterException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Date;
/*      */ import java.util.Timer;
/*      */ import java.util.TimerTask;
/*      */ import java.util.regex.Matcher;
/*      */ import java.util.regex.Pattern;
/*      */ import org.json.JSONException;
/*      */ import org.json.JSONObject;
/*      */ 
/*      */ public class ActionCamera
/*      */   implements AsyncSocketListener
/*      */ {
/*      */   private final ActionCameraListener mListener;
/*      */   private final DispatchQueue mWorkThreadQueue;
/*      */   private ArrayList<ActionCameraCommand> mCommands;
/*      */   private ArrayList<ActionCameraCommand> mGroupCommands;
/*      */   private Timer mTimeoutTimer;
/*      */   private AsyncSocket mSocket;
/*      */   private HttpURLConnection mHttpDownloadConnection;
/*      */   private State mState;
/*      */   private int mHeartbeat;
/*      */   private int mToken;
/*      */   private String mRtspURL;
/*      */   private ActionCameraResponseCache mResponseCache;
/*      */   private String mCameraIP;
/*      */ 
/*      */   public ActionCamera(ActionCameraListener listener, DispatchQueue workThreadQueue)
/*      */   {
/*   68 */     this.mListener = listener;
/*   69 */     this.mWorkThreadQueue = workThreadQueue;
/*   70 */     this.mState = State.Disconnected;
/*   71 */     this.mResponseCache = new ActionCameraResponseCache();
/*      */   }
/*      */ 
/*      */   public void connect(final String connectionString)
/*      */   {
/*   87 */     final ActionCamera obj = this;
/*   88 */     this.mWorkThreadQueue.dispatch(new Runnable()
/*      */     {
/*      */       public void run() {
/*   91 */         if (ActionCamera.this.mState != ActionCamera.State.Disconnected) {
/*   92 */           return;
/*      */         }
/*      */ 
/*   96 */         Pattern pattern = Pattern.compile("^([^:]+):(\\d+\\.\\d+\\.\\d+\\.\\d+):(\\d+)$");
/*   97 */         Matcher matcher = pattern.matcher(connectionString);
/*   98 */         if ((!matcher.find()) || (matcher.groupCount() != 3)) {
/*   99 */           ActionCamera.this.mWorkThreadQueue.dispatch(new Runnable()
/*      */           {
/*      */             public void run() {
/*  102 */               ActionCamera.this.mListener.onClosed(new Error(ErrorCode.InvalidParameter));
/*      */             }
/*      */           });
/*  105 */           return;
/*      */         }
/*      */ 
/*  108 */         String protocol = matcher.group(1);
/*  109 */         String ip = matcher.group(2);
/*  110 */         int port = Integer.parseInt(matcher.group(3));
/*      */ 
/*  113 */         if (!protocol.equals("tcp")) {
/*  114 */           ActionCamera.this.mWorkThreadQueue.dispatch(new Runnable()
/*      */           {
/*      */             public void run() {
/*  117 */               ActionCamera.this.mListener.onClosed(new Error(ErrorCode.NotSupported));
/*      */             }
/*      */           });
/*  120 */           return;
/*      */         }
/*      */ 
/*  123 */         Log.info(this, "try connect to camera: " + connectionString);
/*  124 */         ActionCamera.this.resetTimeoutTimer();
/*  125 */         ActionCamera.this.mState = ActionCamera.State.Connecting;
/*  126 */         ActionCamera.this.mCommands = new ArrayList();
/*  127 */         ActionCamera.this.mHeartbeat = 0;
/*  128 */         ActionCamera.this.mToken = 0;
/*  129 */         ActionCamera.this.mRtspURL = null;
/*  130 */         ActionCamera.this.mResponseCache.clear();
/*  131 */         ActionCamera.this.mSocket = new AsyncSocket(obj, ActionCamera.this.mWorkThreadQueue);
/*  132 */         ActionCamera.this.mCameraIP = ip;
/*  133 */         ActionCamera.this.mHttpDownloadConnection = null;
/*      */         try {
/*  135 */           ActionCamera.this.mSocket.connect(new InetSocketAddress(ip, port));
/*      */         } catch (IOException ex) {
/*  137 */           ActionCamera.this.internalDisconnect(new Error(ErrorCode.IOException, ex.toString()));
/*      */         }
/*      */       }
/*      */     });
/*      */   }
/*      */ 
/*      */   public void disconnect()
/*      */   {
/*  152 */     this.mWorkThreadQueue.dispatch(new Runnable()
/*      */     {
/*      */       public void run() {
/*  155 */         if (ActionCamera.this.mState != ActionCamera.State.Disconnected)
/*  156 */           ActionCamera.this.internalDisconnect(null);
/*      */       }
/*      */     });
/*      */   }
/*      */ 
/*      */   public ActionCamera startCommandGroup()
/*      */   {
/*  164 */     this.mWorkThreadQueue.dispatch(new Runnable()
/*      */     {
/*      */       public void run() {
/*  167 */         ActionCamera.this.mGroupCommands = new ArrayList();
/*      */       }
/*      */     });
/*  170 */     return this;
/*      */   }
/*      */ 
/*      */   public ActionCamera submitCommandGroup(final ActionCameraCommandCallback success, final ActionCameraCommandCallback1<Error> fail) {
/*  174 */     this.mWorkThreadQueue.dispatch(new Runnable()
/*      */     {
/*      */       public void run() {
/*  177 */         if (ActionCamera.this.mGroupCommands == null) {
/*  178 */           if (fail != null) {
/*  179 */             fail.onInvoke(new Error(ErrorCode.InvalidState, "StartCommandGroup() is not invoked"));
/*      */           }
/*  181 */           return;
/*      */         }
/*      */ 
/*  184 */         if (ActionCamera.this.mGroupCommands.isEmpty()) {
/*  185 */           if (success != null) {
/*  186 */             success.onInvoke();
/*      */           }
/*  188 */           ActionCamera.this.mGroupCommands = null;
/*  189 */           return;
/*      */         }
/*      */ 
/*  192 */         if (ActionCamera.this.mState != ActionCamera.State.Connected) {
/*  193 */           for (ActionCameraCommand cmd : ActionCamera.this.mGroupCommands) {
/*  194 */             cmd.onFail(new Error(ErrorCode.InvalidState));
/*      */           }
/*  196 */           ActionCamera.this.mGroupCommands = null;
/*  197 */           return;
/*      */         }
/*      */ 
/*  271 */         Object groupContext = new Object()
/*      */         {
/*  201 */           public boolean isSuccess = true;
/*  202 */           public Error lastCommandError = null;
/*      */         };
/*  272 */         for (int cmdIndex = 0; 
/*  273 */           cmdIndex < ActionCamera.this.mGroupCommands.size() - 1; cmdIndex++) {
/*  274 */           ActionCamera.this.mCommands.add(new ActionCameraCommand(groupContext, (ActionCameraCommand)ActionCamera.this.mGroupCommands.get(cmdIndex), null)
/*      */           {
/*      */             final ActionCamera.4.1CommandGroupContext mGroupContext;
/*      */             final ActionCameraCommand mCommand;
/*      */             final ActionCameraCommandCallback mGroupSuccess;
/*      */             final ActionCameraCommandCallback1<Error> mGroupFail;
/*      */ 
/*      */             public JSONObject getData()
/*      */               throws JSONException
/*      */             {
/*  224 */               if (!this.mGroupContext.isSuccess)
/*      */               {
/*  226 */                 return null;
/*      */               }
/*  228 */               return this.mCommand.getData();
/*      */             }
/*      */ 
/*      */             public Error onSuccess(JSONObject response)
/*      */             {
/*  234 */               Error error = this.mCommand.onSuccess(response);
/*  235 */               if (error == null)
/*  236 */                 processGroupCommandSuccess();
/*      */               else {
/*  238 */                 processGroupCommandFail(error);
/*      */               }
/*  240 */               return error;
/*      */             }
/*      */ 
/*      */             public void onFail(Error error)
/*      */             {
/*  245 */               if (!this.mGroupContext.isSuccess) {
/*  246 */                 error = new Error(ErrorCode.PreConditionFailed);
/*      */               }
/*  248 */               this.mCommand.onFail(error);
/*  249 */               processGroupCommandFail(error);
/*      */             }
/*      */ 
/*      */             private void processGroupCommandSuccess() {
/*  253 */               assert (this.mGroupContext.isSuccess == true);
/*  254 */               if (this.mGroupSuccess != null)
/*  255 */                 this.mGroupSuccess.onInvoke();
/*      */             }
/*      */ 
/*      */             private void processGroupCommandFail(Error error)
/*      */             {
/*  260 */               if (this.mGroupContext.isSuccess) {
/*  261 */                 this.mGroupContext.isSuccess = false;
/*  262 */                 this.mGroupContext.lastCommandError = error;
/*      */               }
/*      */ 
/*  265 */               if (this.mGroupFail != null) {
/*  266 */                 this.mGroupFail.onInvoke(this.mGroupContext.lastCommandError);
/*      */               }
/*      */ 
/*      */             }
/*      */ 
/*      */           });
/*      */         }
/*      */ 
/*  276 */         ActionCamera.this.mCommands.add(new ActionCameraCommand(groupContext, (ActionCameraCommand)ActionCamera.this.mGroupCommands.get(cmdIndex), success)
/*      */         {
/*      */           final ActionCamera.4.1CommandGroupContext mGroupContext;
/*      */           final ActionCameraCommand mCommand;
/*      */           final ActionCameraCommandCallback mGroupSuccess;
/*      */           final ActionCameraCommandCallback1<Error> mGroupFail;
/*      */ 
/*      */           public JSONObject getData()
/*      */             throws JSONException
/*      */           {
/*  224 */             if (!this.mGroupContext.isSuccess)
/*      */             {
/*  226 */               return null;
/*      */             }
/*  228 */             return this.mCommand.getData();
/*      */           }
/*      */ 
/*      */           public Error onSuccess(JSONObject response)
/*      */           {
/*  234 */             Error error = this.mCommand.onSuccess(response);
/*  235 */             if (error == null)
/*  236 */               processGroupCommandSuccess();
/*      */             else {
/*  238 */               processGroupCommandFail(error);
/*      */             }
/*  240 */             return error;
/*      */           }
/*      */ 
/*      */           public void onFail(Error error)
/*      */           {
/*  245 */             if (!this.mGroupContext.isSuccess) {
/*  246 */               error = new Error(ErrorCode.PreConditionFailed);
/*      */             }
/*  248 */             this.mCommand.onFail(error);
/*  249 */             processGroupCommandFail(error);
/*      */           }
/*      */ 
/*      */           private void processGroupCommandSuccess() {
/*  253 */             assert (this.mGroupContext.isSuccess == true);
/*  254 */             if (this.mGroupSuccess != null)
/*  255 */               this.mGroupSuccess.onInvoke();
/*      */           }
/*      */ 
/*      */           private void processGroupCommandFail(Error error)
/*      */           {
/*  260 */             if (this.mGroupContext.isSuccess) {
/*  261 */               this.mGroupContext.isSuccess = false;
/*  262 */               this.mGroupContext.lastCommandError = error;
/*      */             }
/*      */ 
/*  265 */             if (this.mGroupFail != null)
/*  266 */               this.mGroupFail.onInvoke(this.mGroupContext.lastCommandError);
/*      */           }
/*      */         });
/*  277 */         ActionCamera.this.mGroupCommands = null;
/*  278 */         ActionCamera.this.internalSendCommand(null);
/*      */       }
/*      */     });
/*  281 */     return this;
/*      */   }
/*      */ 
/*      */   public ActionCamera setDateTime(Date datetime, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  292 */     return sendCommand(new SetDateTimeCommand(datetime, success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera startRecording(ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  300 */     return sendCommand(new StartRecordingCommand(success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera startRecording(int hour, int minute, int second, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  311 */     return sendCommand(new StartRecordingCommand(hour, minute, second, success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera stopRecording(ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  319 */     return sendCommand(new StopRecordingCommand(success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera capturePhoto(ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  327 */     return sendCommand(new CapturePhotoCommand(success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera getSettings(ActionCameraCommandCallback1<ActionCameraSettings> success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  336 */     return sendCommand(new GetSettingsCommand(success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera setSystemMode(SystemMode mode, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  345 */     switch (mode) {
/*      */     case Record:
/*  347 */       return startCommandGroup().sendCommand(new SetSystemModelCommand(mode, null, null))
/*  348 */         .sendCommand(new SetSettingCommand("rec_mode", 
/*  348 */         RecordMode.getString(RecordMode.Normal), 
/*  348 */         null, null))
/*  349 */         .submitCommandGroup(success, fail);
/*      */     case Capture:
/*  352 */       return startCommandGroup().sendCommand(new SetSystemModelCommand(mode, null, null))
/*  353 */         .sendCommand(new SetSettingCommand("capture_mode", 
/*  353 */         CaptureMode.getString(CaptureMode.Normal), 
/*  353 */         null, null))
/*  354 */         .submitCommandGroup(success, fail);
/*      */     }
/*      */ 
/*  357 */     return sendCommand(new SetSystemModelCommand(mode, success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera getStatus(ActionCameraCommandCallback1<CameraStatus> success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  366 */     return sendCommand(new GetStatusCommand(success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera getVideoResolution(ActionCameraCommandCallback1<VideoResolution> success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  374 */     return sendCommand(new GetVideoResolutionCommand(success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera setVideoResolution(VideoResolution resolution, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  383 */     return sendCommand(new SetVideoResolutionCommand(resolution, success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera getPhotoResolution(ActionCameraCommandCallback1<PhotoResolution> success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  391 */     return sendCommand(new GetPhotoResolutionCommand(success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera setPhotoResolution(PhotoResolution photoResolution, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  400 */     return sendCommand(new SetPhotoResolutionCommand(photoResolution, success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera getPhotoWhiteBalance(ActionCameraCommandCallback1<WhiteBalance> success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  408 */     return sendCommand(new GetWhiteBalanceCommand("iq_photo_wb", success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera setPhotoWhiteBalance(WhiteBalance whiteBalance, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  417 */     return sendCommand(new SetWhiteBalanceCommand("iq_photo_wb", whiteBalance, success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera getVideoWhiteBalance(ActionCameraCommandCallback1<WhiteBalance> success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  425 */     return sendCommand(new GetWhiteBalanceCommand("iq_video_wb", success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera setVideoWhiteBalance(WhiteBalance whiteBalance, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  434 */     return sendCommand(new SetWhiteBalanceCommand("iq_video_wb", whiteBalance, success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera getPhotoISO(ActionCameraCommandCallback1<ISO> success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  442 */     return sendCommand(new GetISOCommand("iq_photo_iso", success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera setPhotoISO(ISO iso, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  451 */     return sendCommand(new SetISOCommand("iq_photo_iso", iso, success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera getVideoISO(ActionCameraCommandCallback1<ISO> success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  459 */     return sendCommand(new GetISOCommand("iq_video_iso", success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera setVideoISO(ISO iso, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  468 */     return sendCommand(new SetISOCommand("iq_video_iso", iso, success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera getPhotoExposureValue(ActionCameraCommandCallback1<ExposureValue> success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  476 */     return sendCommand(new GetExposureValueCommand("iq_photo_ev", success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera setPhotoExposureValue(ExposureValue ev, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  485 */     return sendCommand(new SetExposureValueCommand("iq_photo_ev", ev, success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera getVideoExposureValue(ActionCameraCommandCallback1<ExposureValue> success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  493 */     return sendCommand(new GetExposureValueCommand("iq_video_ev", success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera setVideoExposureValue(ExposureValue ev, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  502 */     return sendCommand(new SetExposureValueCommand("iq_video_ev", ev, success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera getPhotoShutterTime(ActionCameraCommandCallback1<ShutterTime> success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  510 */     return sendCommand(new GetShutterTimeCommand(success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera setPhotoShutterTime(ShutterTime shutterTime, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  519 */     return sendCommand(new SetShutterTimeCommand(shutterTime, success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera getVideoSharpness(ActionCameraCommandCallback1<Sharpness> success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  527 */     return sendCommand(new GetSharpnessCommand("video_sharpness", success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera setVideoSharpness(Sharpness sharpness, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  536 */     return sendCommand(new SetSharpnessCommand("video_sharpness", sharpness, success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera getPhotoSharpness(ActionCameraCommandCallback1<Sharpness> success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  544 */     return sendCommand(new GetSharpnessCommand("photo_sharpness", success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera setPhotoSharpness(Sharpness sharpness, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  553 */     return sendCommand(new SetSharpnessCommand("photo_sharpness", sharpness, success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera getRecordMode(final ActionCameraCommandCallback1<RecordMode> success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  561 */     return sendCommand(new GetSettingCommand("rec_mode", new ActionCameraCommandCallback1()
/*      */     {
/*      */       public void onInvoke(String val) {
/*  564 */         if (success != null)
/*  565 */           success.onInvoke(RecordMode.parse(val));
/*      */       }
/*      */     }
/*      */     , fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera setRecordMode(RecordMode recordMode, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  577 */     return sendCommand(new SetSettingCommand("rec_mode", RecordMode.getString(recordMode), success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera getCaptureMode(final ActionCameraCommandCallback1<CaptureMode> success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  585 */     return sendCommand(new GetSettingCommand("capture_mode", new ActionCameraCommandCallback1()
/*      */     {
/*      */       public void onInvoke(String val) {
/*  588 */         if (success != null)
/*  589 */           success.onInvoke(CaptureMode.parse(val));
/*      */       }
/*      */     }
/*      */     , fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera setCaptureMode(CaptureMode captureMode, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  601 */     return sendCommand(new SetSettingCommand("capture_mode", CaptureMode.getString(captureMode), success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera getFileList(ActionCameraCommandCallback1<File[]> success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  609 */     return sendCommand(new GetFileListCommand(success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera deleteFile(String fileName, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  618 */     return sendCommand(new DeleteFileCommand(fileName, success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera downloadFile(final String fileName, final String destFilePath, final ActionCameraCommandCallback1<DownloadTask> success, final ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  628 */     final ActionCamera camera = this;
/*  629 */     this.mWorkThreadQueue.dispatch(new Runnable()
/*      */     {
/*      */       public void run() {
/*  632 */         if (ActionCamera.this.mState != ActionCamera.State.Connected) {
/*  633 */           if (fail != null) {
/*  634 */             fail.onInvoke(new Error(ErrorCode.InvalidState));
/*      */           }
/*  636 */           return;
/*      */         }
/*      */ 
/*  639 */         if (ActionCamera.this.mHttpDownloadConnection != null) {
/*  640 */           if (fail != null) {
/*  641 */             fail.onInvoke(new Error(ErrorCode.CameraIsBusy));
/*      */           }
/*  643 */           return;
/*      */         }
/*      */ 
/*  646 */         final DownloadTask task = new DownloadTask();
/*  647 */         task.fileName = fileName;
/*  648 */         task.destFilePath = destFilePath;
/*      */         try
/*      */         {
/*  651 */           String url = "http://" + ActionCamera.this.mCameraIP + "/DCIM/100MEDIA/" + fileName;
/*  652 */           Log.info(camera, "download: " + url);
/*  653 */           task.downloadHttpConnection = ActionCamera.this.mHttpDownloadConnection = ((HttpURLConnection)new URL(url).openConnection());
/*      */         } catch (MalformedURLException ex) {
/*  655 */           if (fail != null) {
/*  656 */             fail.onInvoke(new Error(ErrorCode.InvalidParameter, ex.getMessage()));
/*      */           }
/*  658 */           return;
/*      */         } catch (IOException ex) {
/*  660 */           if (fail != null) {
/*  661 */             fail.onInvoke(new Error(ErrorCode.IOException, ex.getMessage()));
/*      */           }
/*  663 */           return;
/*      */         }
/*      */ 
/*  667 */         new Thread(new Runnable()
/*      */         {
/*      */           public void run() {
/*  670 */             OutputStream output = null;
/*      */             try {
/*  672 */               task.downloadHttpConnection.setReadTimeout(5000);
/*  673 */               task.downloadHttpConnection.connect();
/*      */ 
/*  676 */               if (task.downloadHttpConnection.getResponseCode() != 200) {
/*  677 */                 throw new IOException("Can't download from camera");
/*      */               }
/*      */ 
/*  680 */               task.totalBytes = Long.parseLong(task.downloadHttpConnection.getHeaderField("Content-Length"));
/*  681 */               InputStream input = task.downloadHttpConnection.getInputStream();
/*  682 */               output = new FileOutputStream(ActionCamera.7.this.val$destFilePath);
/*  683 */               byte[] data = new byte[4096];
/*      */               int count;
/*  685 */               while (((count = input.read(data)) != -1) && 
/*  686 */                 (task.downloadHttpConnection == ActionCamera.this.mHttpDownloadConnection))
/*      */               {
/*  690 */                 output.write(data);
/*  691 */                 task.downloadedBytes += count;
/*  692 */                 if (task.downloadedBytes > task.totalBytes)
/*  693 */                   throw new IOException("Too many bytes are downloaded");
/*  694 */                 if (task.downloadedBytes == task.totalBytes) {
/*      */                   break;
/*      */                 }
/*  697 */                 if (ActionCamera.7.this.val$success != null) {
/*  698 */                   final DownloadTask progressTask = new DownloadTask();
/*  699 */                   progressTask.fileName = task.fileName;
/*  700 */                   progressTask.destFilePath = task.destFilePath;
/*  701 */                   progressTask.downloadedBytes = task.downloadedBytes;
/*  702 */                   progressTask.totalBytes = task.totalBytes;
/*  703 */                   ActionCamera.this.mWorkThreadQueue.dispatch(new Runnable()
/*      */                   {
/*      */                     public void run() {
/*  706 */                       ActionCamera.7.this.val$success.onInvoke(progressTask);
/*      */                     }
/*      */ 
/*      */                   });
/*      */                 }
/*      */ 
/*      */               }
/*      */ 
/*  714 */               ActionCamera.this.mWorkThreadQueue.dispatch(new Runnable()
/*      */               {
/*      */                 public void run() {
/*  717 */                   if (ActionCamera.7.1.this.val$task.totalBytes == ActionCamera.7.1.this.val$task.downloadedBytes) {
/*  718 */                     if (ActionCamera.7.this.val$success != null)
/*  719 */                       ActionCamera.7.this.val$success.onInvoke(ActionCamera.7.1.this.val$task);
/*      */                   }
/*  721 */                   else if (ActionCamera.7.this.val$fail != null) {
/*  722 */                     if (ActionCamera.7.1.this.val$task.downloadHttpConnection != ActionCamera.this.mHttpDownloadConnection)
/*  723 */                       ActionCamera.7.this.val$fail.onInvoke(new Error(ErrorCode.Cancelled));
/*      */                     else {
/*  725 */                       ActionCamera.7.this.val$fail.onInvoke(new Error(ErrorCode.IOException));
/*      */                     }
/*      */                   }
/*      */ 
/*  729 */                   if (ActionCamera.7.1.this.val$task.downloadHttpConnection == ActionCamera.this.mHttpDownloadConnection)
/*  730 */                     ActionCamera.this.mHttpDownloadConnection = null;
/*      */                 }
/*      */               });
/*      */             }
/*      */             catch (IOException ex) {
/*  735 */               ActionCamera.this.mWorkThreadQueue.dispatch(new Runnable()
/*      */               {
/*      */                 public void run() {
/*  738 */                   if (ActionCamera.7.this.val$fail != null) {
/*  739 */                     if (ActionCamera.7.1.this.val$task.downloadHttpConnection != ActionCamera.this.mHttpDownloadConnection)
/*  740 */                       ActionCamera.7.this.val$fail.onInvoke(new Error(ErrorCode.Cancelled));
/*      */                     else {
/*  742 */                       ActionCamera.7.this.val$fail.onInvoke(new Error(ErrorCode.IOException, ex.getMessage()));
/*      */                     }
/*      */                   }
/*      */ 
/*  746 */                   if (ActionCamera.7.1.this.val$task.downloadHttpConnection == ActionCamera.this.mHttpDownloadConnection) {
/*  747 */                     ActionCamera.this.mHttpDownloadConnection = null;
/*      */                   }
/*      */                 }
/*      */ 
/*      */               });
/*      */             }
/*      */ 
/*  754 */             if (output != null)
/*      */               try {
/*  756 */                 output.close();
/*      */               }
/*      */               catch (IOException localIOException1)
/*      */               {
/*      */               }
/*      */           }
/*      */         }).start();
/*      */       }
/*      */     });
/*  764 */     return this;
/*      */   }
/*      */ 
/*      */   public ActionCamera cancelDownload()
/*      */   {
/*  769 */     this.mWorkThreadQueue.dispatch(new Runnable()
/*      */     {
/*      */       public void run() {
/*  772 */         if (ActionCamera.this.mHttpDownloadConnection != null) {
/*  773 */           final HttpURLConnection oldConnection = ActionCamera.this.mHttpDownloadConnection;
/*  774 */           ActionCamera.this.mHttpDownloadConnection = null;
/*  775 */           new Thread(new Runnable()
/*      */           {
/*      */             public void run() {
/*  778 */               oldConnection.disconnect();
/*      */             }
/*      */           }).start();
/*      */         }
/*      */       }
/*      */     });
/*  784 */     return this;
/*      */   }
/*      */ 
/*      */   public int[] buildLiveVideoQRCode(String ssid, String password, LiveVideoResolution resolution, LiveVideoBitrate bitrate, int duration, String rtmpUrl, int size)
/*      */     throws WriterException
/*      */   {
/*  798 */     int black = -16777216;
/*      */     String res;
/*      */     String res;
/*      */     String res;
/*  801 */     switch (30.$SwitchMap$com$xiaoyi$action$LiveVideoResolution[resolution.ordinal()]) {
/*      */     case 1:
/*  803 */       res = "480p";
/*  804 */       break;
/*      */     case 2:
/*  807 */       res = "720p";
/*  808 */       break;
/*      */     case 3:
/*  811 */       res = "1080p";
/*  812 */       break;
/*      */     default:
/*  815 */       throw new InvalidParameterException("resolution is unsupported");
/*      */     }
/*      */     String res;
/*      */     String rate;
/*      */     String rate;
/*      */     String rate;
/*      */     String rate;
/*  819 */     switch (30.$SwitchMap$com$xiaoyi$action$LiveVideoBitrate[bitrate.ordinal()]) {
/*      */     case 1:
/*  821 */       rate = "0";
/*  822 */       break;
/*      */     case 2:
/*  825 */       rate = "1";
/*  826 */       break;
/*      */     case 3:
/*  829 */       rate = "2";
/*  830 */       break;
/*      */     case 4:
/*  833 */       rate = "3";
/*  834 */       break;
/*      */     default:
/*  837 */       throw new InvalidParameterException("bitrate is unsupported");
/*      */     }
/*      */     String rate;
/*  840 */     String jsonStr = "{\"ssid\":" + JSONObject.quote(ssid) + ",\"pwd\":" + JSONObject.quote(password) + ",\"res\":\"" + res + "\",\"rate\":\"" + rate + "\",\"dur\":\"" + duration + "\",\"url\":\"" + rtmpUrl + "\"}";
/*      */ 
/*  849 */     BitMatrix matrix = new MultiFormatWriter().encode(jsonStr, BarcodeFormat.QR_CODE, size, size);
/*  850 */     int[] pixels = new int[size * size];
/*  851 */     for (int y = 0; y < size; y++) {
/*  852 */       for (int x = 0; x < size; x++) {
/*  853 */         if (matrix.get(x, y)) {
/*  854 */           pixels[(x + y * size)] = -16777216;
/*      */         }
/*      */       }
/*      */     }
/*  858 */     return pixels;
/*      */   }
/*      */ 
/*      */   public ActionCamera getVideoFieldOfView(ActionCameraCommandCallback1<FieldOfView> success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  866 */     return sendCommand(new GetFieldOfViewCommand("fov", success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera setVideoFieldOfView(FieldOfView fieldOfView, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  875 */     return sendCommand(new SetFieldOfViewCommand("fov", fieldOfView, success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera getMeteringMode(final ActionCameraCommandCallback1<MeteringMode> success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  883 */     return sendCommand(new GetSettingCommand("meter_mode", new ActionCameraCommandCallback1()
/*      */     {
/*      */       public void onInvoke(String val) {
/*  886 */         if (success != null)
/*  887 */           success.onInvoke(MeteringMode.parse(val));
/*      */       }
/*      */     }
/*      */     , fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera setMeteringMode(MeteringMode meteringMode, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  899 */     return sendCommand(new SetSettingCommand("meter_mode", MeteringMode.getString(meteringMode), success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera getVideoQuality(final ActionCameraCommandCallback1<Quality> success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  907 */     return sendCommand(new GetSettingCommand("video_quality", new ActionCameraCommandCallback1()
/*      */     {
/*      */       public void onInvoke(String val) {
/*  910 */         if (success != null)
/*  911 */           success.onInvoke(Quality.parse(val));
/*      */       }
/*      */     }
/*      */     , fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera setVideoQuality(Quality quality, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  923 */     return sendCommand(new SetSettingCommand("video_quality", Quality.getString(quality), success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera getVideoColorMode(final ActionCameraCommandCallback1<ColorMode> success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  931 */     return sendCommand(new GetSettingCommand("video_flat_color", new ActionCameraCommandCallback1()
/*      */     {
/*      */       public void onInvoke(String val) {
/*  934 */         if (success != null)
/*  935 */           success.onInvoke(ColorMode.parse(val));
/*      */       }
/*      */     }
/*      */     , fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera setVideoColorMode(ColorMode colorMode, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  947 */     return sendCommand(new SetSettingCommand("video_flat_color", ColorMode.getString(colorMode), success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera getPhotoColorMode(final ActionCameraCommandCallback1<ColorMode> success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  955 */     return sendCommand(new GetSettingCommand("photo_flat_color", new ActionCameraCommandCallback1()
/*      */     {
/*      */       public void onInvoke(String val) {
/*  958 */         if (success != null)
/*  959 */           success.onInvoke(ColorMode.parse(val));
/*      */       }
/*      */     }
/*      */     , fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera setPhotoColorMode(ColorMode colorMode, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  971 */     return sendCommand(new SetSettingCommand("photo_flat_color", ColorMode.getString(colorMode), success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera getElectronicImageStabilizationState(final ActionCameraCommandCallback1<ToggleState> success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  979 */     return sendCommand(new GetSettingCommand("iq_eis_enable", new ActionCameraCommandCallback1()
/*      */     {
/*      */       public void onInvoke(String val) {
/*  982 */         if (success != null)
/*  983 */           success.onInvoke(ToggleState.parse(val));
/*      */       }
/*      */     }
/*      */     , fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera setElectronicImageStabilizationState(ToggleState state, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/*  995 */     return sendCommand(new SetSettingCommand("iq_eis_enable", ToggleState.getString(state), success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera getVideoMuteState(final ActionCameraCommandCallback1<ToggleState> success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/* 1003 */     return sendCommand(new GetSettingCommand("video_mute_set", new ActionCameraCommandCallback1()
/*      */     {
/*      */       public void onInvoke(String val) {
/* 1006 */         if (success != null)
/* 1007 */           success.onInvoke(ToggleState.parse(val));
/*      */       }
/*      */     }
/*      */     , fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera setVideoMuteState(ToggleState state, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/* 1019 */     return sendCommand(new SetSettingCommand("video_mute_set", ToggleState.getString(state), success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera getVideoTimestamp(final ActionCameraCommandCallback1<Timestamp> success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/* 1027 */     return sendCommand(new GetSettingCommand("video_stamp", new ActionCameraCommandCallback1()
/*      */     {
/*      */       public void onInvoke(String val) {
/* 1030 */         if (success != null)
/* 1031 */           success.onInvoke(Timestamp.parse(val));
/*      */       }
/*      */     }
/*      */     , fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera setVideoTimestamp(Timestamp timestamp, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/* 1043 */     return sendCommand(new SetSettingCommand("video_stamp", Timestamp.getString(timestamp), success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera getPhotoTimestamp(final ActionCameraCommandCallback1<Timestamp> success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/* 1051 */     return sendCommand(new GetSettingCommand("photo_stamp", new ActionCameraCommandCallback1()
/*      */     {
/*      */       public void onInvoke(String val) {
/* 1054 */         if (success != null)
/* 1055 */           success.onInvoke(Timestamp.parse(val));
/*      */       }
/*      */     }
/*      */     , fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera setPhotoTimestamp(Timestamp timestamp, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/* 1067 */     return sendCommand(new SetSettingCommand("photo_stamp", Timestamp.getString(timestamp), success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera getLEDMode(final ActionCameraCommandCallback1<LEDMode> success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/* 1075 */     return sendCommand(new GetSettingCommand("led_mode", new ActionCameraCommandCallback1()
/*      */     {
/*      */       public void onInvoke(String val) {
/* 1078 */         if (success != null)
/* 1079 */           success.onInvoke(LEDMode.parse(val));
/*      */       }
/*      */     }
/*      */     , fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera setLEDMode(LEDMode ledMode, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/* 1091 */     return sendCommand(new SetSettingCommand("led_mode", LEDMode.getString(ledMode), success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera getVideoStandard(final ActionCameraCommandCallback1<VideoStandard> success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/* 1099 */     return sendCommand(new GetSettingCommand("video_standard", new ActionCameraCommandCallback1()
/*      */     {
/*      */       public void onInvoke(String val) {
/* 1102 */         if (success != null)
/* 1103 */           success.onInvoke(VideoStandard.parse(val));
/*      */       }
/*      */     }
/*      */     , fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera setVideoStandard(VideoStandard videoStandard, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/* 1115 */     return sendCommand(new SetSettingCommand("video_standard", VideoStandard.getString(videoStandard), success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera getTimeLapseVideoInterval(final ActionCameraCommandCallback1<TimeLapseVideoInterval> success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/* 1123 */     return sendCommand(new GetSettingCommand("timelapse_video", new ActionCameraCommandCallback1()
/*      */     {
/*      */       public void onInvoke(String val) {
/* 1126 */         if (success != null)
/* 1127 */           success.onInvoke(TimeLapseVideoInterval.parse(val));
/*      */       }
/*      */     }
/*      */     , fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera setTimeLapseVideoInterval(TimeLapseVideoInterval interval, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/* 1139 */     return sendCommand(new SetSettingCommand("timelapse_video", TimeLapseVideoInterval.getString(interval), success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera getTimeLapsePhotoInterval(final ActionCameraCommandCallback1<TimeLapsePhotoInterval> success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/* 1147 */     return sendCommand(new GetSettingCommand("precise_cont_time", new ActionCameraCommandCallback1()
/*      */     {
/*      */       public void onInvoke(String val) {
/* 1150 */         if (success != null)
/* 1151 */           success.onInvoke(TimeLapsePhotoInterval.parse(val));
/*      */       }
/*      */     }
/*      */     , fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera setTimeLapsePhotoInterval(TimeLapsePhotoInterval interval, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/* 1163 */     return sendCommand(new SetSettingCommand("precise_cont_time", TimeLapsePhotoInterval.getString(interval), success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera getTimeLapseVideoDuration(final ActionCameraCommandCallback1<TimeLapseVideoDuration> success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/* 1171 */     return sendCommand(new GetSettingCommand("timelapse_video_duration", new ActionCameraCommandCallback1()
/*      */     {
/*      */       public void onInvoke(String val) {
/* 1174 */         if (success != null)
/* 1175 */           success.onInvoke(TimeLapseVideoDuration.parse(val));
/*      */       }
/*      */     }
/*      */     , fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera setTimeLapseVideoDuration(TimeLapseVideoDuration interval, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/* 1187 */     return sendCommand(new SetSettingCommand("timelapse_video_duration", TimeLapseVideoDuration.getString(interval), success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera getScreenAutoLock(final ActionCameraCommandCallback1<ScreenAutoLock> success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/* 1195 */     return sendCommand(new GetSettingCommand("screen_auto_lock", new ActionCameraCommandCallback1()
/*      */     {
/*      */       public void onInvoke(String val) {
/* 1198 */         if (success != null)
/* 1199 */           success.onInvoke(ScreenAutoLock.parse(val));
/*      */       }
/*      */     }
/*      */     , fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera setScreenAutoLock(ScreenAutoLock screenAutoLock, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/* 1211 */     return sendCommand(new SetSettingCommand("screen_auto_lock", ScreenAutoLock.getString(screenAutoLock), success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera getAutoPowerOff(final ActionCameraCommandCallback1<AutoPowerOff> success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/* 1219 */     return sendCommand(new GetSettingCommand("auto_power_off", new ActionCameraCommandCallback1()
/*      */     {
/*      */       public void onInvoke(String val) {
/* 1222 */         if (success != null)
/* 1223 */           success.onInvoke(AutoPowerOff.parse(val));
/*      */       }
/*      */     }
/*      */     , fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera setAutoPowerOff(AutoPowerOff autoPowerOff, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/* 1235 */     return sendCommand(new SetSettingCommand("auto_power_off", AutoPowerOff.getString(autoPowerOff), success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera getVideoRotateMode(final ActionCameraCommandCallback1<VideoRotateMode> success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/* 1243 */     return sendCommand(new GetSettingCommand("video_rotate", new ActionCameraCommandCallback1()
/*      */     {
/*      */       public void onInvoke(String val) {
/* 1246 */         if (success != null)
/* 1247 */           success.onInvoke(VideoRotateMode.parse(val));
/*      */       }
/*      */     }
/*      */     , fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera setVideoRotateMode(VideoRotateMode mode, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/* 1259 */     return sendCommand(new SetSettingCommand("video_rotate", VideoRotateMode.getString(mode), success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera getBuzzerVolume(final ActionCameraCommandCallback1<BuzzerVolume> success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/* 1267 */     return sendCommand(new GetSettingCommand("buzzer_volume", new ActionCameraCommandCallback1()
/*      */     {
/*      */       public void onInvoke(String val) {
/* 1270 */         if (success != null)
/* 1271 */           success.onInvoke(BuzzerVolume.parse(val));
/*      */       }
/*      */     }
/*      */     , fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera setBuzzerVolume(BuzzerVolume buzzerVolume, ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/* 1283 */     return sendCommand(new SetSettingCommand("buzzer_volume", BuzzerVolume.getString(buzzerVolume), success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera startViewFinder(ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/* 1293 */     return sendCommand(new StartViewFinderCommand(success, fail));
/*      */   }
/*      */ 
/*      */   public ActionCamera stopViewFinder(ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/* 1301 */     return sendCommand(new StopViewFinderCommand(success, fail));
/*      */   }
/*      */ 
/*      */   public String getRtspURL()
/*      */   {
/* 1306 */     return this.mRtspURL;
/*      */   }
/*      */ 
/*      */   public ActionCamera formatSDCard(ActionCameraCommandCallback success, ActionCameraCommandCallback1<Error> fail)
/*      */   {
/* 1314 */     return sendCommand(new FormatSDCardCommand(success, fail));
/*      */   }
/*      */ 
/*      */   public void onConnected(AsyncSocket socket)
/*      */   {
/* 1327 */     Log.info(this, "onConnected(), current state: " + this.mState);
/* 1328 */     Util.Assert((this.mState == State.Connecting) || (this.mState == State.Disconnecting));
/*      */ 
/* 1330 */     if (this.mState != State.Connecting) {
/* 1331 */       return;
/*      */     }
/*      */ 
/* 1334 */     this.mState = State.Connected;
/*      */ 
/* 1336 */     internalSendCommand(new StartSessionCommand(new ActionCameraCommandCallback2()
/*      */     {
/*      */       public void onInvoke(Integer sessionId, String rtspUrl)
/*      */       {
/* 1340 */         Log.info(this, new StringBuilder().append("Start session success, token: ").append(sessionId).append(", rtspUrl: ").append(rtspUrl == null ? "" : rtspUrl).toString());
/* 1341 */         ActionCamera.this.mToken = sessionId.intValue();
/* 1342 */         ActionCamera.this.mRtspURL = rtspUrl;
/* 1343 */         ActionCamera.this.mListener.onConnected();
/*      */       }
/*      */     }
/*      */     , new ActionCameraCommandCallback1()
/*      */     {
/*      */       public void onInvoke(Error error)
/*      */       {
/* 1349 */         Log.info(this, "Start session fail: " + error);
/* 1350 */         ActionCamera.this.internalDisconnect(error);
/*      */       }
/*      */     }));
/*      */   }
/*      */ 
/*      */   public void onClosed(AsyncSocket socket, Error error)
/*      */   {
/* 1358 */     Log.info(this, "onClosed(), current state: " + this.mState);
/* 1359 */     Util.Assert((this.mState == State.Connecting) || (this.mState == State.Connected) || (this.mState == State.Disconnecting));
/*      */ 
/* 1361 */     if (this.mState != State.Disconnecting) {
/* 1362 */       this.mState = State.Disconnecting;
/*      */     }
/*      */ 
/* 1366 */     for (ActionCameraCommand cmd : this.mCommands) {
/* 1367 */       cmd.onFail(error != null ? error : new Error(ErrorCode.Cancelled));
/*      */     }
/*      */ 
/* 1370 */     if (this.mHttpDownloadConnection != null) {
/* 1371 */       this.mHttpDownloadConnection.disconnect();
/*      */     }
/*      */ 
/* 1374 */     resetTimeoutTimer();
/* 1375 */     this.mState = State.Disconnected;
/* 1376 */     this.mCommands = null;
/* 1377 */     this.mHeartbeat = 0;
/* 1378 */     this.mToken = 0;
/* 1379 */     this.mRtspURL = null;
/* 1380 */     this.mResponseCache.clear();
/* 1381 */     this.mSocket = null;
/* 1382 */     this.mGroupCommands = null;
/* 1383 */     this.mCameraIP = null;
/* 1384 */     this.mHttpDownloadConnection = null;
/*      */ 
/* 1387 */     this.mListener.onClosed(error);
/*      */   }
/*      */ 
/*      */   public void onDataSent(AsyncSocket socket, ByteBuffer buffer)
/*      */   {
/* 1392 */     Log.verbose(this, "onDataSent(), current state: " + this.mState);
/* 1393 */     Util.Assert((this.mState == State.Connected) || (this.mState == State.Disconnecting));
/*      */   }
/*      */ 
/*      */   public void onDataReceived(AsyncSocket socket, ByteBuffer buffer)
/*      */   {
/* 1399 */     Log.verbose(this, "onDataReceived(), current state: " + this.mState);
/* 1400 */     Util.Assert((this.mState == State.Connected) || (this.mState == State.Disconnecting));
/*      */ 
/* 1402 */     if (this.mState != State.Connected) {
/* 1403 */       return;
/*      */     }
/*      */     try
/*      */     {
/* 1407 */       String str = new String(buffer.array(), 0, buffer.position());
/* 1408 */       Log.debugOnlyVerbose(this, "received data: " + str);
/*      */ 
/* 1410 */       this.mResponseCache.append(str);
/*      */       while (true)
/*      */       {
/* 1417 */         ActionCameraResponse response = this.mResponseCache.getResponse();
/* 1418 */         if (response == null)
/*      */         {
/*      */           break;
/*      */         }
/* 1422 */         if (response.isNotification())
/* 1423 */           internalProcessNotification(response);
/* 1424 */         else if (!this.mCommands.isEmpty())
/*      */         {
/* 1426 */           if (response.getMessageId() != ((ActionCameraCommand)this.mCommands.get(0)).getCommandId()) {
/* 1427 */             Log.error(this, "response doesn't match the command which id is: " + ((ActionCameraCommand)this.mCommands.get(0)).getCommandId());
/*      */           }
/*      */           else {
/* 1430 */             resetTimeoutTimer();
/* 1431 */             ((ActionCameraCommand)this.mCommands.remove(0)).onSuccess(response);
/* 1432 */             internalSendCommand(null);
/*      */           }
/*      */         }
/*      */       }
/*      */     } catch (JSONException ex) { ex.printStackTrace();
/* 1437 */       Log.error(this, "Invalid response format");
/* 1438 */       internalDisconnect(new Error(ErrorCode.InvalidResponse));
/*      */     }
/*      */   }
/*      */ 
/*      */   private void resetTimeoutTimer()
/*      */   {
/* 1444 */     if (this.mTimeoutTimer != null) {
/* 1445 */       this.mTimeoutTimer.cancel();
/* 1446 */       this.mTimeoutTimer = null;
/*      */     }
/*      */   }
/*      */ 
/*      */   private ActionCamera sendCommand(final ActionCameraCommand command) {
/* 1451 */     this.mWorkThreadQueue.dispatch(new Runnable()
/*      */     {
/*      */       public void run() {
/* 1454 */         if (ActionCamera.this.mState != ActionCamera.State.Connected)
/* 1455 */           command.onFail(new Error(ErrorCode.InvalidState));
/* 1456 */         else if (ActionCamera.this.mGroupCommands != null)
/* 1457 */           ActionCamera.this.mGroupCommands.add(command);
/*      */         else
/* 1459 */           ActionCamera.this.internalSendCommand(command);
/*      */       }
/*      */     });
/* 1463 */     return this;
/*      */   }
/*      */ 
/*      */   private void internalSendCommand(ActionCameraCommand command)
/*      */   {
/* 1468 */     Util.Assert(this.mState == State.Connected);
/*      */ 
/* 1470 */     if (command != null)
/*      */     {
/* 1472 */       this.mCommands.add(command);
/*      */     }
/*      */     try
/*      */     {
/* 1476 */       while ((this.mTimeoutTimer == null) && (!this.mCommands.isEmpty())) {
/* 1477 */         ActionCameraCommand cmd = (ActionCameraCommand)this.mCommands.get(0);
/*      */         try {
/* 1479 */           JSONObject obj = cmd.getData();
/* 1480 */           if (obj == null) {
/* 1481 */             cmd.onFail(new Error(ErrorCode.InvalidFormat));
/* 1482 */             this.mCommands.remove(0);
/*      */           } else {
/* 1484 */             obj.put("token", this.mToken);
/* 1485 */             obj.put("heartbeat", "" + ++this.mHeartbeat);
/* 1486 */             this.mTimeoutTimer = new Timer();
/* 1487 */             this.mTimeoutTimer.schedule(new TimerTask()
/*      */             {
/*      */               public void run()
/*      */               {
/* 1491 */                 ActionCamera.this.mWorkThreadQueue.dispatch(new Runnable()
/*      */                 {
/*      */                   public void run() {
/* 1494 */                     ActionCamera.this.internalDisconnect(new Error(ErrorCode.Timeout));
/*      */                   }
/*      */                 });
/*      */               }
/*      */             }
/*      */             , 5000L);
/*      */ 
/* 1499 */             this.mSocket.send(ByteBuffer.wrap(obj.toString().getBytes()));
/*      */           }
/*      */         } catch (JSONException ex) {
/* 1502 */           ex.printStackTrace();
/* 1503 */           resetTimeoutTimer();
/* 1504 */           cmd.onFail(new Error(ErrorCode.InvalidFormat, ex.toString()));
/* 1505 */           this.mCommands.remove(0);
/*      */         }
/*      */       }
/*      */     } catch (IOException ex) {
/* 1509 */       ex.printStackTrace();
/* 1510 */       Log.error(this, "Send command failed: " + ex.toString());
/* 1511 */       internalDisconnect(new Error(ErrorCode.IOException, ex.toString()));
/*      */     }
/*      */   }
/*      */ 
/*      */   private void internalDisconnect(Error error)
/*      */   {
/* 1517 */     Log.verbose(this, "internalDisconnect(), current state: " + this.mState);
/* 1518 */     Util.Assert((this.mState == State.Connecting) || (this.mState == State.Connected) || (this.mState == State.Disconnecting));
/* 1519 */     if ((this.mState == State.Disconnecting) || (this.mState == State.Disconnected)) {
/* 1520 */       return;
/*      */     }
/*      */ 
/* 1523 */     this.mState = State.Disconnecting;
/*      */     try {
/* 1525 */       this.mSocket.disconnect(error);
/*      */     }
/*      */     catch (IOException ex)
/*      */     {
/* 1529 */       onClosed(this.mSocket, error);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void onFileNameCallback(ActionCameraResponse response, FileNameCallback callback)
/*      */   {
/* 1538 */     String eventName = response.getNotificationType();
/* 1539 */     String fileName = response.getString("param");
/* 1540 */     if (fileName != null) {
/* 1541 */       if (fileName.startsWith("/tmp/fuse_d/DCIM/100MEDIA/"))
/* 1542 */         callback.onInvoke(fileName.substring("/tmp/fuse_d/DCIM/100MEDIA/".length()));
/*      */       else
/* 1544 */         Log.error(this, eventName + " received, but filename (" + fileName + ") doesn't contain default file root");
/*      */     }
/*      */     else
/* 1547 */       Log.error(this, eventName + " received, but no filename");
/*      */   }
/*      */ 
/*      */   private void internalProcessNotification(ActionCameraResponse response)
/*      */     throws JSONException
/*      */   {
/* 1553 */     if (response.getMessageId() != 7)
/*      */     {
/* 1555 */       return;
/*      */     }
/*      */ 
/* 1558 */     switch (response.getNotificationType())
/*      */     {
/*      */     case "start_video_record":
/* 1561 */       this.mListener.onRecordStarted();
/* 1562 */       break;
/*      */     case "video_record_complete":
/* 1565 */       this.mListener.onRecordStopped();
/* 1566 */       break;
/*      */     case "start_photo_capture":
/* 1571 */       this.mListener.onCaptureStarted();
/* 1572 */       break;
/*      */     case "photo_taken":
/* 1575 */       this.mListener.onCaptureStopped();
/* 1576 */       break;
/*      */     case "vf_start":
/* 1579 */       this.mListener.onViewFinderStarted();
/* 1580 */       break;
/*      */     case "vf_stop":
/* 1583 */       this.mListener.onViewFinderStopped();
/* 1584 */       break;
/*      */     case "battery":
/* 1587 */       this.mListener.onBatteryLifeChanged(Integer.parseInt(response.getString("param")));
/* 1588 */       break;
/*      */     case "sdcard_format_done":
/* 1591 */       this.mListener.onSDCardFormatted();
/*      */     }
/*      */   }
/*      */ 
/*      */   private static abstract interface FileNameCallback
/*      */   {
/*      */     public abstract void onInvoke(String paramString);
/*      */   }
/*      */ 
/*      */   private static enum State
/*      */   {
/*   38 */     Disconnected, 
/*   39 */     Disconnecting, 
/*   40 */     Connecting, 
/*   41 */     Connected;
/*      */   }
/*      */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.ActionCamera
 * JD-Core Version:    0.6.2
 */