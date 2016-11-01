/*    */ package com.xiaoyi.action;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.nio.channels.CancelledKeyException;
/*    */ import java.nio.channels.ClosedChannelException;
/*    */ import java.nio.channels.SelectionKey;
/*    */ import java.nio.channels.Selector;
/*    */ import java.nio.channels.SocketChannel;
/*    */ import java.util.Set;
/*    */ 
/*    */ class AsyncSocketWorkThread extends Thread
/*    */ {
/*    */   private static AsyncSocketWorkThread sInstance;
/* 32 */   private Selector mSelector = Selector.open();
/*    */ 
/*    */   private AsyncSocketWorkThread()
/*    */     throws IOException
/*    */   {
/*    */   }
/*    */ 
/*    */   public static AsyncSocketWorkThread getInstance()
/*    */     throws IOException
/*    */   {
/* 21 */     if (sInstance == null) {
/* 22 */       synchronized (AsyncSocketWorkThread.class) {
/* 23 */         if (sInstance == null) {
/* 24 */           sInstance = new AsyncSocketWorkThread();
/*    */         }
/*    */       }
/*    */     }
/* 28 */     return sInstance;
/*    */   }
/*    */ 
/*    */   public void register(SocketChannel channel, int ops, AsyncSocket obj)
/*    */     throws ClosedChannelException
/*    */   {
/* 35 */     synchronized (AsyncSocketWorkThread.class) {
/* 36 */       this.mSelector.wakeup();
/* 37 */       channel.register(this.mSelector, ops, obj);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void run()
/*    */   {
/* 44 */     Log.info(this, "AsyncSocketWorkThread is running");
/*    */     try {
/* 46 */       while (!Thread.interrupted())
/*    */       {
/* 48 */         this.mSelector.select();
/*    */ 
/* 50 */         synchronized (AsyncSocketWorkThread.class)
/*    */         {
/*    */         }
/*    */ 
/*    */         try
/*    */         {
/* 56 */           Set keys = this.mSelector.selectedKeys();
/* 57 */           for (SelectionKey key : keys) {
/*    */             try {
/* 59 */               int readyOps = key.readyOps();
/* 60 */               key.interestOps(key.interestOps() & (readyOps ^ 0xFFFFFFFF));
/*    */ 
/* 62 */               if ((readyOps & 0x8) != 0) {
/* 63 */                 ((AsyncSocket)key.attachment()).onConnectable(key);
/*    */               } else {
/* 65 */                 if ((readyOps & 0x4) != 0) {
/* 66 */                   ((AsyncSocket)key.attachment()).onWritable(key);
/*    */                 }
/*    */ 
/* 69 */                 if ((readyOps & 0x1) != 0)
/* 70 */                   ((AsyncSocket)key.attachment()).onReadable(key);
/*    */               }
/*    */             }
/*    */             catch (CancelledKeyException localCancelledKeyException) {
/*    */             }
/*    */             catch (Exception ex) {
/* 76 */               ex.printStackTrace();
/* 77 */               Log.error(this, "AsyncSocketWorkThread process selection key: " + key + " throws exception: " + ex);
/*    */             }
/*    */           }
/* 80 */           keys.clear();
/*    */         } catch (Exception ex) {
/* 82 */           ex.printStackTrace();
/* 83 */           Log.error(this, "AsycSocketWorkThread process selection keys throws exception: " + ex);
/*    */         }
/*    */       }
/*    */     } catch (Exception ex) {
/* 87 */       ex.printStackTrace();
/* 88 */       Log.error(this, "AsyncSocketThread throws exception: " + ex);
/*    */     }
/* 90 */     Log.info(this, "AsyncSocketWorkThread is exited");
/*    */   }
/*    */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.AsyncSocketWorkThread
 * JD-Core Version:    0.6.2
 */