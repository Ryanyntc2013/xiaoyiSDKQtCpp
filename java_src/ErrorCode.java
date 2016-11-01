/*     */ package com.xiaoyi.action;
/*     */ 
/*     */ public class ErrorCode
/*     */ {
/*   6 */   public static int Success = 0;
/*     */ 
/*   8 */   public static int Cancelled = -2147483647;
/*     */ 
/*  10 */   public static int InvalidState = -2147483646;
/*     */ 
/*  12 */   public static int IOException = -2147483645;
/*     */ 
/*  14 */   public static int InvalidParameter = -2147483644;
/*     */ 
/*  16 */   public static int NotSupported = -2147483643;
/*     */ 
/*  18 */   public static int InvalidResponse = -2147483642;
/*     */ 
/*  20 */   public static int CommandFailed = -2147483641;
/*     */ 
/*  22 */   public static int InvalidFormat = -2147483640;
/*     */ 
/*  24 */   public static int PreConditionFailed = -2147483639;
/*     */ 
/*  26 */   public static int Timeout = -2147483638;
/*     */ 
/*  30 */   public static int CameraIsBusy = -2147483637;
/*     */ 
/*  33 */   public static int ReachMaxClients = -2147483636;
/*     */ 
/*  35 */   public static int HDMIInserted = -2147483635;
/*     */ 
/*  37 */   public static int NoSpace = -2147483634;
/*     */ 
/*  39 */   public static int CardProtected = -2147483633;
/*     */ 
/*  41 */   public static int NoMemory = -2147483632;
/*     */ 
/*  44 */   public static int PhotoInVideoIsNotAllowed = -2147483631;
/*     */ 
/*  46 */   public static int SDCardIsNotExisted = -2147483630;
/*     */ 
/*  48 */   public static int SDCardIsNotFormated = -2147483629;
/*     */ 
/*  50 */   public static int BatteryIsLow = -2147483628;
/*     */ 
/*  52 */   public static int SDCardCapacityIsLow = -2147483627;
/*     */ 
/*  54 */   public static int BluetoothIsBinding = -2147483626;
/*     */ 
/*  56 */   public static int NoSpaceForLoopRecording = -2147483625;
/*     */ 
/*     */   static int ConvertFirmwareError(int error) {
/*  59 */     switch (error) {
/*     */     case -5:
/*  61 */       return ReachMaxClients;
/*     */     case -16:
/*  64 */       return HDMIInserted;
/*     */     case -17:
/*  67 */       return NoSpace;
/*     */     case -18:
/*  70 */       return CardProtected;
/*     */     case -19:
/*  73 */       return NoMemory;
/*     */     case -20:
/*  76 */       return PhotoInVideoIsNotAllowed;
/*     */     case -21:
/*  79 */       return CameraIsBusy;
/*     */     case -27:
/*  82 */       return SDCardIsNotExisted;
/*     */     case -28:
/*  85 */       return SDCardIsNotFormated;
/*     */     case -29:
/*  88 */       return BatteryIsLow;
/*     */     case -31:
/*  91 */       return SDCardCapacityIsLow;
/*     */     case -32:
/*  94 */       return BluetoothIsBinding;
/*     */     case -37:
/*  97 */       return NoSpaceForLoopRecording;
/*     */     case -36:
/*     */     case -35:
/*     */     case -34:
/*     */     case -33:
/*     */     case -30:
/*     */     case -26:
/*     */     case -25:
/*     */     case -24:
/*     */     case -23:
/*     */     case -22:
/*     */     case -15:
/*     */     case -14:
/*     */     case -13:
/*     */     case -12:
/*     */     case -11:
/*     */     case -10:
/*     */     case -9:
/*     */     case -8:
/*     */     case -7:
/* 100 */     case -6: } return error;
/*     */   }
/*     */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.ErrorCode
 * JD-Core Version:    0.6.2
 */