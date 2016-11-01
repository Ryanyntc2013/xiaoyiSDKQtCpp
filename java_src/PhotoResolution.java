/*    */ package com.xiaoyi.action;
/*    */ 
/*    */ public enum PhotoResolution
/*    */ {
/*  6 */   Unknown, 
/*    */ 
/*  8 */   p_12MP_4000x3000_4x3_w, 
/*    */ 
/* 10 */   p_7MP_3008x2256_4x3_w, 
/*    */ 
/* 12 */   p_7MP_3008x2256_4x3_m, 
/*    */ 
/* 14 */   p_5MP_2560x1920_4x3_m, 
/*    */ 
/* 16 */   p_8MP_3840x2160_16x9_w;
/*    */ 
/*    */   static PhotoResolution parse(String str) {
/* 19 */     switch (str) {
/*    */     default:
/* 21 */       return Unknown;
/*    */     case "12MP (4000x3000 4:3) fov:w":
/* 24 */       return p_12MP_4000x3000_4x3_w;
/*    */     case "7MP (3008x2256 4:3) fov:w":
/* 27 */       return p_7MP_3008x2256_4x3_w;
/*    */     case "7MP (3008x2256 4:3) fov:m":
/* 30 */       return p_7MP_3008x2256_4x3_m;
/*    */     case "5MP (2560x1920 4:3) fov:m":
/* 33 */       return p_5MP_2560x1920_4x3_m;
/*    */     case "8MP (3840x2160 16:9) fov:w":
/*    */     }
/* 36 */     return p_8MP_3840x2160_16x9_w;
/*    */   }
/*    */ 
/*    */   static String getString(PhotoResolution photoResolution)
/*    */   {
/* 41 */     if (photoResolution == p_12MP_4000x3000_4x3_w)
/* 42 */       return "12MP (4000x3000 4:3) fov:w";
/* 43 */     if (photoResolution == p_7MP_3008x2256_4x3_w)
/* 44 */       return "7MP (3008x2256 4:3) fov:w";
/* 45 */     if (photoResolution == p_7MP_3008x2256_4x3_m)
/* 46 */       return "7MP (3008x2256 4:3) fov:m";
/* 47 */     if (photoResolution == p_5MP_2560x1920_4x3_m)
/* 48 */       return "5MP (2560x1920 4:3) fov:m";
/* 49 */     if (photoResolution == p_8MP_3840x2160_16x9_w) {
/* 50 */       return "8MP (3840x2160 16:9) fov:w";
/*    */     }
/* 52 */     return "";
/*    */   }
/*    */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.PhotoResolution
 * JD-Core Version:    0.6.2
 */