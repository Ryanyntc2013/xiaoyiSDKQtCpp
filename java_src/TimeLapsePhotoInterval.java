/*     */ package com.xiaoyi.action;
/*     */ 
/*     */ public enum TimeLapsePhotoInterval
/*     */ {
/*   6 */   Unknown, 
/*     */ 
/*   8 */   t_Continue, 
/*     */ 
/*  10 */   t_0_5s, 
/*     */ 
/*  12 */   t_1s, 
/*     */ 
/*  14 */   t_2s, 
/*     */ 
/*  16 */   t_5s, 
/*     */ 
/*  18 */   t_10s, 
/*     */ 
/*  20 */   t_30s, 
/*     */ 
/*  22 */   t_60s, 
/*     */ 
/*  24 */   t_2m, 
/*     */ 
/*  26 */   t_5m, 
/*     */ 
/*  28 */   t_10m, 
/*     */ 
/*  30 */   t_30m, 
/*     */ 
/*  32 */   t_60m;
/*     */ 
/*     */   static TimeLapsePhotoInterval parse(String str) {
/*  35 */     switch (str) {
/*     */     default:
/*  37 */       return Unknown;
/*     */     case "continue":
/*  40 */       return t_Continue;
/*     */     case "0.5 sec":
/*  43 */       return t_0_5s;
/*     */     case "1.0 sec":
/*  46 */       return t_1s;
/*     */     case "2.0 sec":
/*  49 */       return t_2s;
/*     */     case "5.0 sec":
/*  52 */       return t_5s;
/*     */     case "10.0 sec":
/*  55 */       return t_10s;
/*     */     case "30.0 sec":
/*  58 */       return t_30s;
/*     */     case "60.0 sec":
/*  61 */       return t_60s;
/*     */     case "2.0 min":
/*  64 */       return t_2m;
/*     */     case "5.0 min":
/*  67 */       return t_5m;
/*     */     case "10.0 min":
/*  70 */       return t_10m;
/*     */     case "30.0 min":
/*  73 */       return t_30m;
/*     */     case "60.0 min":
/*     */     }
/*  76 */     return t_60m;
/*     */   }
/*     */ 
/*     */   static String getString(TimeLapsePhotoInterval interval)
/*     */   {
/*  81 */     switch (1.$SwitchMap$com$xiaoyi$action$TimeLapsePhotoInterval[interval.ordinal()]) {
/*     */     case 1:
/*  83 */       return "continue";
/*     */     case 2:
/*  86 */       return "0.5 sec";
/*     */     case 3:
/*  89 */       return "1.0 sec";
/*     */     case 4:
/*  92 */       return "2.0 sec";
/*     */     case 5:
/*  95 */       return "5.0 sec";
/*     */     case 6:
/*  98 */       return "10.0 sec";
/*     */     case 7:
/* 101 */       return "30.0 sec";
/*     */     case 8:
/* 104 */       return "60.0 sec";
/*     */     case 9:
/* 107 */       return "2.0 min";
/*     */     case 10:
/* 110 */       return "5.0 min";
/*     */     case 11:
/* 113 */       return "10.0 min";
/*     */     case 12:
/* 116 */       return "30.0 min";
/*     */     case 13:
/* 119 */       return "60.0 min";
/*     */     }
/*     */ 
/* 122 */     return "";
/*     */   }
/*     */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.TimeLapsePhotoInterval
 * JD-Core Version:    0.6.2
 */