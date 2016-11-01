/*     */ package com.xiaoyi.action;
/*     */ 
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import org.json.JSONArray;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class ActionCameraSettings
/*     */ {
/*     */   static final String AppStatusSettingName = "app_status";
/*     */   static final String CameraClockSettingName = "camera_clock";
/*     */   static final String SoftwareVersionSettingName = "sw_version";
/*     */   static final String HardwareVersionSettingName = "hw_version";
/*     */   static final String SerialNumberSettingName = "serial_number";
/*     */   static final String ProductNameSettingName = "product_name";
/*     */   static final String SystemModeSettingName = "system_mode";
/*     */   static final String VideoStandardSettingName = "video_standard";
/*     */   static final String VideoResolutionSettingName = "video_resolution";
/*     */   static final String VideoQualitySettingName = "video_quality";
/*     */   static final String PhotoResolutionSettingName = "photo_size";
/*     */   static final String PhotoWhiteBalanceSettingName = "iq_photo_wb";
/*     */   static final String VideoWhiteBalanceSettingName = "iq_video_wb";
/*     */   static final String PhotoISOSettingName = "iq_photo_iso";
/*     */   static final String VideoISOSettingName = "iq_video_iso";
/*     */   static final String PhotoExposureValueSettingName = "iq_photo_ev";
/*     */   static final String VideoExposureValueSettingName = "iq_video_ev";
/*     */   static final String PhotoShutterTimeSettingName = "iq_photo_shutter";
/*     */   static final String VideoSharpnessSettingName = "video_sharpness";
/*     */   static final String PhotoSharpnessSettingName = "photo_sharpness";
/*     */   static final String DefaultFileRootInCamera = "/tmp/fuse_d/DCIM/100MEDIA/";
/*     */   static final String DefaultFileDownloadURLBase = "/DCIM/100MEDIA/";
/*     */   static final String FieldOfViewSettingName = "fov";
/*     */   static final String MeteringModeSettingName = "meter_mode";
/*     */   static final String VideoColorModeSettingName = "video_flat_color";
/*     */   static final String PhotoColorModeSettingName = "photo_flat_color";
/*     */   static final String ElectronicImageStabilizationSettingName = "iq_eis_enable";
/*     */   static final String VideoTimestampSettingName = "video_stamp";
/*     */   static final String PhotoTimestampSettingName = "photo_stamp";
/*     */   static final String VideoMuteSettingName = "video_mute_set";
/*     */   static final String LEDModeSettingName = "led_mode";
/*     */   static final String ScreenAutoLockSettingName = "screen_auto_lock";
/*     */   static final String AutoPowerOffSettingName = "auto_power_off";
/*     */   static final String VideoRotateSettingName = "video_rotate";
/*     */   static final String BuzzerVolumeSettingName = "buzzer_volume";
/*     */   static final String RecordModeSettingName = "rec_mode";
/*     */   static final String CaptureModeSettingName = "capture_mode";
/*     */   static final String TimeLapseVideoIntervalSettingName = "timelapse_video";
/*     */   static final String TimeLapsePhotoIntervalSettingName = "precise_cont_time";
/*     */   static final String TimeLapseVideoDurationSettingName = "timelapse_video_duration";
/*     */   public CameraStatus status;
/*     */   public Date clock;
/*     */   public String softwareVersion;
/*     */   public String hardwareVersion;
/*     */   public String serialNumber;
/*     */   public String productName;
/*     */   public SystemMode systemMode;
/*     */   public VideoStandard videoStandard;
/*     */   public VideoResolution videoResolution;
/*     */   public Quality videoQuality;
/*     */   public PhotoResolution photoResolution;
/*     */   public WhiteBalance photoWhiteBalance;
/*     */   public WhiteBalance videoWhiteBalance;
/*     */   public ISO photoISO;
/*     */   public ISO videoISO;
/*     */   public ExposureValue photoExposureValue;
/*     */   public ExposureValue videoExposureValue;
/*     */   public ShutterTime photoShutterTime;
/*     */   public Sharpness videoSharpness;
/*     */   public Sharpness photoSharpness;
/*     */   public FieldOfView videoFieldOfView;
/*     */   public MeteringMode meteringMode;
/*     */   public ColorMode videoColorMode;
/*     */   public ColorMode photoColorMode;
/*     */   public ToggleState electronicImageStabilizationState;
/*     */   public Timestamp videoTimestamp;
/*     */   public Timestamp photoTimestamp;
/*     */   public ToggleState videoMuteState;
/*     */   public LEDMode ledMode;
/*     */   public ScreenAutoLock screenAutoLock;
/*     */   public AutoPowerOff autoPowerOff;
/*     */   public VideoRotateMode videoRotateMode;
/*     */   public BuzzerVolume buzzerVolume;
/*     */   public RecordMode recordMode;
/*     */   public CaptureMode captureMode;
/*     */   public TimeLapseVideoInterval timeLapseVideoInterval;
/*     */   public TimeLapsePhotoInterval timeLapsePhotoInterval;
/*     */   public TimeLapseVideoDuration timeLapseVideoDuration;
/*     */ 
/*     */   public ActionCameraSettings(JSONArray param)
/*     */   {
/* 169 */     for (int i = 0; i < param.length(); i++) {
/* 170 */       JSONObject obj = param.getJSONObject(i);
/* 171 */       if ((parseCameraClock(obj)) || 
/* 172 */         (parseVideoStandard(obj)) || 
/* 173 */         (parseAppStatus(obj)) || 
/* 174 */         (parseVideoResolution(obj)) || 
/* 175 */         (parseSoftwareVersion(obj)) || 
/* 176 */         (parseHardwareVersion(obj)) || 
/* 177 */         (parseSerialNumber(obj)) || 
/* 178 */         (parseProductName(obj)) || 
/* 179 */         (parseSystemMode(obj)) || 
/* 180 */         (parseVideoQuality(obj)) || 
/* 181 */         (parsePhotoResolution(obj)) || 
/* 182 */         (parsePhotoWhiteBalance(obj)) || 
/* 183 */         (parseVideoWhiteBalance(obj)) || 
/* 184 */         (parsePhotoISO(obj)) || 
/* 185 */         (parseVideoISO(obj)) || 
/* 186 */         (parsePhotoExposureValue(obj)) || 
/* 187 */         (parseVideoExposureValue(obj)) || 
/* 188 */         (parsePhotoShutterTime(obj)) || 
/* 189 */         (parseVideoSharpness(obj)) || 
/* 190 */         (parsePhotoSharpness(obj)) || 
/* 191 */         (parseVideoFieldOfView(obj)) || 
/* 192 */         (parseMeteringMode(obj)) || 
/* 193 */         (parseVideoColorMode(obj)) || 
/* 194 */         (parsePhotoColorMode(obj)) || 
/* 195 */         (parseElectronicImageStabilizationEnabled(obj)) || 
/* 196 */         (parseVideoTimestamp(obj)) || 
/* 197 */         (parsePhotoTimestamp(obj)) || 
/* 198 */         (parseVideoMuteEnabled(obj)) || 
/* 199 */         (parseLEDMode(obj)) || 
/* 200 */         (parseScreenAutoLock(obj)) || 
/* 201 */         (parseAutoPowerOff(obj)) || 
/* 202 */         (parseVideoRotateMode(obj)) || 
/* 203 */         (parseBuzzerVolume(obj)) || 
/* 204 */         (parseRecordMode(obj)) || 
/* 205 */         (parseCaptureMode(obj)) || 
/* 206 */         (parseTimeLapseVideoInterval(obj)) || 
/* 207 */         (parseTimeLapsePhotoInterval(obj)) || 
/* 208 */         (!parseTimeLapseVideoDuration(obj)));
/*     */     }
/*     */   }
/*     */ 
/*     */   private boolean parseCameraClock(JSONObject obj)
/*     */   {
/* 215 */     String str = getSettingString(obj, "camera_clock");
/* 216 */     if (str == null) {
/* 217 */       return false;
/*     */     }
/*     */     try
/*     */     {
/* 221 */       this.clock = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
/* 222 */       return true;
/*     */     } catch (ParseException ex) {
/* 224 */       ex.printStackTrace();
/* 225 */     }return false;
/*     */   }
/*     */ 
/*     */   private boolean parseVideoStandard(JSONObject obj)
/*     */   {
/* 230 */     String str = getSettingString(obj, "video_standard");
/* 231 */     if (str == null) {
/* 232 */       return false;
/*     */     }
/* 234 */     this.videoStandard = VideoStandard.parse(str);
/* 235 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parseAppStatus(JSONObject obj)
/*     */   {
/* 240 */     String str = getSettingString(obj, "app_status");
/* 241 */     if (str == null) {
/* 242 */       return false;
/*     */     }
/* 244 */     this.status = CameraStatus.parse(str);
/* 245 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parseVideoResolution(JSONObject obj)
/*     */   {
/* 250 */     String str = getSettingString(obj, "video_resolution");
/* 251 */     if (str == null) {
/* 252 */       return false;
/*     */     }
/* 254 */     this.videoResolution = VideoResolution.parse(str);
/* 255 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parseSoftwareVersion(JSONObject obj)
/*     */   {
/* 260 */     String str = getSettingString(obj, "sw_version");
/* 261 */     if (str == null) {
/* 262 */       return false;
/*     */     }
/* 264 */     this.softwareVersion = str;
/* 265 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parseHardwareVersion(JSONObject obj) {
/* 269 */     String str = getSettingString(obj, "hw_version");
/* 270 */     if (str == null) {
/* 271 */       return false;
/*     */     }
/* 273 */     this.hardwareVersion = str;
/* 274 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parseSerialNumber(JSONObject obj) {
/* 278 */     String str = getSettingString(obj, "serial_number");
/* 279 */     if (str == null) {
/* 280 */       return false;
/*     */     }
/* 282 */     this.serialNumber = str;
/* 283 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parseProductName(JSONObject obj) {
/* 287 */     String str = getSettingString(obj, "product_name");
/* 288 */     if (str == null) {
/* 289 */       return false;
/*     */     }
/* 291 */     this.productName = str;
/* 292 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parseSystemMode(JSONObject obj) {
/* 296 */     String str = getSettingString(obj, "system_mode");
/* 297 */     if (str == null) {
/* 298 */       return false;
/*     */     }
/*     */ 
/* 301 */     switch (str) {
/*     */     default:
/* 303 */       this.systemMode = SystemMode.Unknown;
/* 304 */       break;
/*     */     case "capture":
/* 307 */       this.systemMode = SystemMode.Capture;
/* 308 */       break;
/*     */     case "record":
/* 311 */       this.systemMode = SystemMode.Record;
/*     */     }
/*     */ 
/* 314 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parseVideoQuality(JSONObject obj) {
/* 318 */     String str = getSettingString(obj, "video_quality");
/* 319 */     if (str == null) {
/* 320 */       return false;
/*     */     }
/* 322 */     this.videoQuality = Quality.parse(str);
/* 323 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parsePhotoResolution(JSONObject obj)
/*     */   {
/* 328 */     String str = getSettingString(obj, "photo_size");
/* 329 */     if (str == null) {
/* 330 */       return false;
/*     */     }
/* 332 */     this.photoResolution = PhotoResolution.parse(str);
/* 333 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parsePhotoWhiteBalance(JSONObject obj)
/*     */   {
/* 338 */     String str = getSettingString(obj, "iq_photo_wb");
/* 339 */     if (str == null) {
/* 340 */       return false;
/*     */     }
/* 342 */     this.photoWhiteBalance = WhiteBalance.parse(str);
/* 343 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parseVideoWhiteBalance(JSONObject obj)
/*     */   {
/* 348 */     String str = getSettingString(obj, "iq_video_wb");
/* 349 */     if (str == null) {
/* 350 */       return false;
/*     */     }
/* 352 */     this.videoWhiteBalance = WhiteBalance.parse(str);
/* 353 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parsePhotoISO(JSONObject obj)
/*     */   {
/* 358 */     String str = getSettingString(obj, "iq_photo_iso");
/* 359 */     if (str == null) {
/* 360 */       return false;
/*     */     }
/* 362 */     this.photoISO = ISO.parse(str);
/* 363 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parseVideoISO(JSONObject obj)
/*     */   {
/* 368 */     String str = getSettingString(obj, "iq_video_iso");
/* 369 */     if (str == null) {
/* 370 */       return false;
/*     */     }
/* 372 */     this.videoISO = ISO.parse(str);
/* 373 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parsePhotoExposureValue(JSONObject obj)
/*     */   {
/* 378 */     String str = getSettingString(obj, "iq_photo_ev");
/* 379 */     if (str == null) {
/* 380 */       return false;
/*     */     }
/* 382 */     this.photoExposureValue = ExposureValue.parse(str);
/* 383 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parseVideoExposureValue(JSONObject obj)
/*     */   {
/* 388 */     String str = getSettingString(obj, "iq_video_ev");
/* 389 */     if (str == null) {
/* 390 */       return false;
/*     */     }
/* 392 */     this.videoExposureValue = ExposureValue.parse(str);
/* 393 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parsePhotoShutterTime(JSONObject obj)
/*     */   {
/* 398 */     String str = getSettingString(obj, "iq_photo_shutter");
/* 399 */     if (str == null) {
/* 400 */       return false;
/*     */     }
/* 402 */     this.photoShutterTime = ShutterTime.parse(str);
/* 403 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parseVideoSharpness(JSONObject obj)
/*     */   {
/* 408 */     String str = getSettingString(obj, "video_sharpness");
/* 409 */     if (str == null) {
/* 410 */       return false;
/*     */     }
/* 412 */     this.videoSharpness = Sharpness.parse(str);
/* 413 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parsePhotoSharpness(JSONObject obj)
/*     */   {
/* 418 */     String str = getSettingString(obj, "photo_sharpness");
/* 419 */     if (str == null) {
/* 420 */       return false;
/*     */     }
/* 422 */     this.photoSharpness = Sharpness.parse(str);
/* 423 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parseVideoFieldOfView(JSONObject obj)
/*     */   {
/* 428 */     String str = getSettingString(obj, "fov");
/* 429 */     if (str == null) {
/* 430 */       return false;
/*     */     }
/* 432 */     this.videoFieldOfView = FieldOfView.parse(str);
/* 433 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parseMeteringMode(JSONObject obj)
/*     */   {
/* 438 */     String str = getSettingString(obj, "meter_mode");
/* 439 */     if (str == null) {
/* 440 */       return false;
/*     */     }
/* 442 */     this.meteringMode = MeteringMode.parse(str);
/* 443 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parseVideoColorMode(JSONObject obj)
/*     */   {
/* 448 */     String str = getSettingString(obj, "video_flat_color");
/* 449 */     if (str == null) {
/* 450 */       return false;
/*     */     }
/* 452 */     this.videoColorMode = ColorMode.parse(str);
/* 453 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parsePhotoColorMode(JSONObject obj)
/*     */   {
/* 458 */     String str = getSettingString(obj, "photo_flat_color");
/* 459 */     if (str == null) {
/* 460 */       return false;
/*     */     }
/* 462 */     this.photoColorMode = ColorMode.parse(str);
/* 463 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parseElectronicImageStabilizationEnabled(JSONObject obj)
/*     */   {
/* 468 */     String str = getSettingString(obj, "iq_eis_enable");
/* 469 */     if (str == null) {
/* 470 */       return false;
/*     */     }
/* 472 */     this.electronicImageStabilizationState = ToggleState.parse(str);
/* 473 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parseVideoMuteEnabled(JSONObject obj)
/*     */   {
/* 478 */     String str = getSettingString(obj, "video_mute_set");
/* 479 */     if (str == null) {
/* 480 */       return false;
/*     */     }
/* 482 */     this.videoMuteState = ToggleState.parse(str);
/* 483 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parseLEDMode(JSONObject obj)
/*     */   {
/* 488 */     String str = getSettingString(obj, "led_mode");
/* 489 */     if (str == null) {
/* 490 */       return false;
/*     */     }
/* 492 */     this.ledMode = LEDMode.parse(str);
/* 493 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parseScreenAutoLock(JSONObject obj)
/*     */   {
/* 498 */     String str = getSettingString(obj, "screen_auto_lock");
/* 499 */     if (str == null) {
/* 500 */       return false;
/*     */     }
/* 502 */     this.screenAutoLock = ScreenAutoLock.parse(str);
/* 503 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parseAutoPowerOff(JSONObject obj)
/*     */   {
/* 508 */     String str = getSettingString(obj, "auto_power_off");
/* 509 */     if (str == null) {
/* 510 */       return false;
/*     */     }
/* 512 */     this.autoPowerOff = AutoPowerOff.parse(str);
/* 513 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parseVideoRotateMode(JSONObject obj)
/*     */   {
/* 518 */     String str = getSettingString(obj, "video_rotate");
/* 519 */     if (str == null) {
/* 520 */       return false;
/*     */     }
/* 522 */     this.videoRotateMode = VideoRotateMode.parse(str);
/* 523 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parseBuzzerVolume(JSONObject obj)
/*     */   {
/* 528 */     String str = getSettingString(obj, "buzzer_volume");
/* 529 */     if (str == null) {
/* 530 */       return false;
/*     */     }
/* 532 */     this.buzzerVolume = BuzzerVolume.parse(str);
/* 533 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parseRecordMode(JSONObject obj)
/*     */   {
/* 538 */     String str = getSettingString(obj, "rec_mode");
/* 539 */     if (str == null) {
/* 540 */       return false;
/*     */     }
/* 542 */     this.recordMode = RecordMode.parse(str);
/* 543 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parseCaptureMode(JSONObject obj)
/*     */   {
/* 548 */     String str = getSettingString(obj, "capture_mode");
/* 549 */     if (str == null) {
/* 550 */       return false;
/*     */     }
/* 552 */     this.captureMode = CaptureMode.parse(str);
/* 553 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parseTimeLapseVideoInterval(JSONObject obj)
/*     */   {
/* 558 */     String str = getSettingString(obj, "timelapse_video");
/* 559 */     if (str == null) {
/* 560 */       return false;
/*     */     }
/* 562 */     this.timeLapseVideoInterval = TimeLapseVideoInterval.parse(str);
/* 563 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parseTimeLapsePhotoInterval(JSONObject obj)
/*     */   {
/* 568 */     String str = getSettingString(obj, "precise_cont_time");
/* 569 */     if (str == null) {
/* 570 */       return false;
/*     */     }
/* 572 */     this.timeLapsePhotoInterval = TimeLapsePhotoInterval.parse(str);
/* 573 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parseTimeLapseVideoDuration(JSONObject obj)
/*     */   {
/* 578 */     String str = getSettingString(obj, "timelapse_video_duration");
/* 579 */     if (str == null) {
/* 580 */       return false;
/*     */     }
/* 582 */     this.timeLapseVideoDuration = TimeLapseVideoDuration.parse(str);
/* 583 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parseVideoTimestamp(JSONObject obj)
/*     */   {
/* 588 */     String str = getSettingString(obj, "video_stamp");
/* 589 */     if (str == null) {
/* 590 */       return false;
/*     */     }
/* 592 */     this.videoTimestamp = Timestamp.parse(str);
/* 593 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean parsePhotoTimestamp(JSONObject obj)
/*     */   {
/* 598 */     String str = getSettingString(obj, "photo_stamp");
/* 599 */     if (str == null) {
/* 600 */       return false;
/*     */     }
/* 602 */     this.photoTimestamp = Timestamp.parse(str);
/* 603 */     return true;
/*     */   }
/*     */ 
/*     */   private String getSettingString(JSONObject obj, String key)
/*     */   {
/* 608 */     if (obj.has(key)) {
/*     */       try {
/* 610 */         return obj.getString(key);
/*     */       } catch (JSONException ex) {
/* 612 */         ex.printStackTrace();
/*     */       }
/*     */     }
/* 615 */     return null;
/*     */   }
/*     */ 
/*     */   static String getString(SystemMode mode) {
/* 619 */     switch (1.$SwitchMap$com$xiaoyi$action$SystemMode[mode.ordinal()]) {
/*     */     default:
/* 621 */       return "";
/*     */     case 1:
/* 624 */       return "capture";
/*     */     case 2:
/*     */     }
/* 627 */     return "record";
/*     */   }
/*     */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.ActionCameraSettings
 * JD-Core Version:    0.6.2
 */