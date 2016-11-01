/*     */ package com.xiaoyi.action;
/*     */ 
/*     */ public enum VideoResolution
/*     */ {
/*   6 */   Unknown, 
/*     */ 
/*   8 */   v_3840x2160_30p_16x9, 
/*     */ 
/*  10 */   v_3840x2160_30p_16x9_super, 
/*     */ 
/*  12 */   v_2560x1920_30p_4x3, 
/*     */ 
/*  14 */   v_1920x1440_60p_4x3, 
/*     */ 
/*  16 */   v_1920x1440_30p_4x3, 
/*     */ 
/*  18 */   v_1920x1080_120p_16x9, 
/*     */ 
/*  20 */   v_1920x1080_120p_16x9_super, 
/*     */ 
/*  22 */   v_1920x1080_60p_16x9, 
/*     */ 
/*  24 */   v_1920x1080_60p_16x9_super, 
/*     */ 
/*  26 */   v_1920x1080_30p_16x9, 
/*     */ 
/*  28 */   v_1920x1080_30p_16x9_super, 
/*     */ 
/*  30 */   v_1280x960_120p_4x3, 
/*     */ 
/*  32 */   v_1280x960_60P_4x3, 
/*     */ 
/*  34 */   v_1280x720_240p_16x9, 
/*     */ 
/*  36 */   v_1280x720_120p_16x9_super, 
/*     */ 
/*  38 */   v_1280x720_60p_16x9_super, 
/*     */ 
/*  40 */   v_840x480_120p_16x9;
/*     */ 
/*     */   static VideoResolution parse(String str) {
/*  43 */     switch (str) {
/*     */     default:
/*  45 */       return Unknown;
/*     */     case "3840x2160 30P 16:9":
/*  48 */       return v_3840x2160_30p_16x9;
/*     */     case "3840x2160 30P 16:9 super":
/*  51 */       return v_3840x2160_30p_16x9_super;
/*     */     case "2560x1920 30P 4:3":
/*  54 */       return v_2560x1920_30p_4x3;
/*     */     case "1920x1440 60P 4:3":
/*  57 */       return v_1920x1440_60p_4x3;
/*     */     case "1920x1440 30P 4:3":
/*  60 */       return v_1920x1440_30p_4x3;
/*     */     case "1920x1080 120P 16:9":
/*  63 */       return v_1920x1080_120p_16x9;
/*     */     case "1920x1080 120P 16:9 super":
/*  66 */       return v_1920x1080_120p_16x9_super;
/*     */     case "1920x1080 60P 16:9":
/*  69 */       return v_1920x1080_60p_16x9;
/*     */     case "1920x1080 60P 16:9 super":
/*  72 */       return v_1920x1080_60p_16x9_super;
/*     */     case "1920x1080 30P 16:9":
/*  75 */       return v_1920x1080_30p_16x9;
/*     */     case "1920x1080 30P 16:9 super":
/*  78 */       return v_1920x1080_30p_16x9_super;
/*     */     case "1280x960 120P 4:3":
/*  81 */       return v_1280x960_120p_4x3;
/*     */     case "1280x960 60P 4:3":
/*  84 */       return v_1280x960_60P_4x3;
/*     */     case "1280x720 240P 16:9":
/*  87 */       return v_1280x720_240p_16x9;
/*     */     case "1280x720 120P 16:9 super":
/*  90 */       return v_1280x720_120p_16x9_super;
/*     */     case "1280x720 60P 16:9 super":
/*  93 */       return v_1280x720_60p_16x9_super;
/*     */     case "840x480 240P 16:9":
/*     */     }
/*  96 */     return v_840x480_120p_16x9;
/*     */   }
/*     */ 
/*     */   static String getString(VideoResolution resolution)
/*     */   {
/* 101 */     if (resolution == v_3840x2160_30p_16x9)
/* 102 */       return "3840x2160 30P 16:9";
/* 103 */     if (resolution == v_3840x2160_30p_16x9_super)
/* 104 */       return "3840x2160 30P 16:9 super";
/* 105 */     if (resolution == v_2560x1920_30p_4x3)
/* 106 */       return "2560x1920 30P 4:3";
/* 107 */     if (resolution == v_1920x1440_60p_4x3)
/* 108 */       return "1920x1440 60P 4:3";
/* 109 */     if (resolution == v_1920x1440_30p_4x3)
/* 110 */       return "1920x1440 30P 4:3";
/* 111 */     if (resolution == v_1920x1080_120p_16x9)
/* 112 */       return "1920x1080 120P 16:9";
/* 113 */     if (resolution == v_1920x1080_120p_16x9_super)
/* 114 */       return "1920x1080 120P 16:9 super";
/* 115 */     if (resolution == v_1920x1080_60p_16x9)
/* 116 */       return "1920x1080 60P 16:9";
/* 117 */     if (resolution == v_1920x1080_60p_16x9_super)
/* 118 */       return "1920x1080 60P 16:9 super";
/* 119 */     if (resolution == v_1920x1080_30p_16x9)
/* 120 */       return "1920x1080 30P 16:9";
/* 121 */     if (resolution == v_1920x1080_30p_16x9_super)
/* 122 */       return "1920x1080 30P 16:9 super";
/* 123 */     if (resolution == v_1280x960_120p_4x3)
/* 124 */       return "1280x960 120P 4:3";
/* 125 */     if (resolution == v_1280x960_60P_4x3)
/* 126 */       return "1280x960 60P 4:3";
/* 127 */     if (resolution == v_1280x720_240p_16x9)
/* 128 */       return "1280x720 240P 16:9";
/* 129 */     if (resolution == v_1280x720_120p_16x9_super)
/* 130 */       return "1280x720 120P 16:9 super";
/* 131 */     if (resolution == v_1280x720_60p_16x9_super)
/* 132 */       return "1280x720 60P 16:9 super";
/* 133 */     if (resolution == v_840x480_120p_16x9) {
/* 134 */       return "840x480 240P 16:9";
/*     */     }
/* 136 */     return "";
/*     */   }
/*     */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.VideoResolution
 * JD-Core Version:    0.6.2
 */