/*    */ package com.xiaoyi.action;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public class File
/*    */ {
/*    */   public String name;
/*    */   public long size;
/*    */   public Date time;
/*    */ 
/*    */   File(String _name, long _size, Date _time)
/*    */   {
/* 15 */     this.name = _name;
/* 16 */     this.size = _size;
/* 17 */     this.time = _time;
/*    */   }
/*    */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.File
 * JD-Core Version:    0.6.2
 */