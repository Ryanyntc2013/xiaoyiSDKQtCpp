/*     */ package com.xiaoyi.action;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.channels.SelectionKey;
/*     */ import java.nio.channels.SocketChannel;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class AsyncSocket
/*     */ {
/*  18 */   private final int kReadTrunkSize = 1024;
/*     */   private AsyncSocketListener mListener;
/*     */   private DispatchQueue mDispatchQueue;
/*     */   private SocketChannel mChannel;
/*     */   private ArrayList<ByteBuffer> mBuffers;
/*  34 */   private State mState = State.Disconnected;
/*     */ 
/*     */   public AsyncSocket(AsyncSocketListener listener, DispatchQueue dispatchQueue) {
/*  37 */     this.mListener = listener;
/*  38 */     this.mDispatchQueue = dispatchQueue;
/*     */   }
/*     */ 
/*     */   public synchronized void connect(InetSocketAddress address) throws IOException {
/*  42 */     if ((this.mState == State.Connecting) || (this.mState == State.Connected)) {
/*  43 */       return;
/*     */     }
/*     */ 
/*  46 */     this.mChannel = SocketChannel.open();
/*  47 */     this.mChannel.configureBlocking(false);
/*  48 */     AsyncSocketWorkThread.getInstance().register(this.mChannel, 8, this);
/*  49 */     this.mChannel.connect(address);
/*  50 */     this.mState = State.Connecting;
/*     */   }
/*     */ 
/*     */   public synchronized void disconnect(final Error error) throws IOException {
/*  54 */     if (this.mState == State.Disconnected) {
/*  55 */       return;
/*     */     }
/*     */ 
/*  58 */     this.mChannel.close();
/*  59 */     this.mChannel = null;
/*  60 */     if (this.mBuffers != null) {
/*  61 */       this.mBuffers.clear();
/*  62 */       this.mBuffers = null;
/*     */     }
/*  64 */     this.mState = State.Disconnected;
/*  65 */     final AsyncSocket obj = this;
/*  66 */     this.mDispatchQueue.dispatch(new Runnable()
/*     */     {
/*     */       public void run() {
/*  69 */         AsyncSocket.this.mListener.onClosed(obj, error);
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   public synchronized void send(ByteBuffer buffer) throws IOException {
/*  75 */     if (this.mState != State.Connected) {
/*  76 */       return;
/*     */     }
/*     */ 
/*  79 */     if (this.mBuffers == null) {
/*  80 */       this.mBuffers = new ArrayList();
/*     */     }
/*     */ 
/*  83 */     this.mBuffers.add(buffer);
/*  84 */     AsyncSocketWorkThread.getInstance().register(this.mChannel, 5, this);
/*     */   }
/*     */ 
/*     */   public synchronized void onConnectable(SelectionKey key)
/*     */     throws IOException
/*     */   {
/*  90 */     if (this.mState != State.Connecting) {
/*  91 */       return;
/*     */     }
/*     */     try
/*     */     {
/*  95 */       if (this.mChannel.finishConnect()) {
/*  96 */         this.mState = State.Connected;
/*  97 */         key.interestOps(key.interestOps() | 0x1);
/*  98 */         final AsyncSocket obj = this;
/*  99 */         this.mDispatchQueue.dispatch(new Runnable()
/*     */         {
/*     */           public void run() {
/* 102 */             AsyncSocket.this.mListener.onConnected(obj);
/*     */           }
/*     */         });
/*     */       }
/*     */       else {
/* 107 */         key.interestOps(key.interestOps() | 0x8);
/*     */       }
/*     */     } catch (IOException ex) {
/* 110 */       disconnect(new Error(ErrorCode.IOException, ex.toString()));
/*     */     }
/*     */   }
/*     */ 
/*     */   public synchronized void onWritable(SelectionKey key) throws IOException
/*     */   {
/* 116 */     if (this.mState != State.Connected) {
/* 117 */       return;
/*     */     }
/*     */ 
/* 120 */     final ByteBuffer buffer = (ByteBuffer)this.mBuffers.get(0);
/*     */     try {
/* 122 */       Log.debugOnlyVerbose(key, "send data: " + new String(buffer.array()));
/* 123 */       this.mChannel.write(buffer);
/* 124 */       if (buffer.hasRemaining())
/*     */       {
/* 126 */         key.interestOps(key.interestOps() | 0x4);
/*     */       }
/*     */       else {
/* 129 */         this.mBuffers.remove(0);
/* 130 */         final AsyncSocket obj = this;
/* 131 */         this.mDispatchQueue.dispatch(new Runnable()
/*     */         {
/*     */           public void run() {
/* 134 */             AsyncSocket.this.mListener.onDataSent(obj, buffer);
/*     */           } } );
/*     */       }
/*     */     }
/*     */     catch (IOException ex) {
/* 139 */       ex.printStackTrace();
/* 140 */       Log.error(this, "write throws exception: " + ex);
/* 141 */       disconnect(new Error(ErrorCode.IOException, ex.toString()));
/*     */     }
/*     */   }
/*     */ 
/*     */   public synchronized void onReadable(SelectionKey key) throws IOException
/*     */   {
/* 147 */     if (this.mState != State.Connected) {
/* 148 */       return;
/*     */     }
/*     */ 
/* 151 */     final ByteBuffer buffer = ByteBuffer.allocate(1024);
/*     */     try {
/* 153 */       if (this.mChannel.read(buffer) == -1) {
/* 154 */         Log.info(this, "connection has been closed by remote side");
/* 155 */         disconnect(null);
/*     */       } else {
/* 157 */         if (buffer.position() > 0) {
/* 158 */           final AsyncSocket obj = this;
/* 159 */           this.mDispatchQueue.dispatch(new Runnable()
/*     */           {
/*     */             public void run() {
/* 162 */               AsyncSocket.this.mListener.onDataReceived(obj, buffer);
/*     */             }
/*     */ 
/*     */           });
/*     */         }
/*     */ 
/* 168 */         key.interestOps(key.interestOps() | 0x1);
/*     */       }
/*     */     } catch (IOException ex) {
/* 171 */       ex.printStackTrace();
/* 172 */       Log.error(this, "read throws exception: " + ex);
/* 173 */       disconnect(new Error(ErrorCode.IOException, ex.toString()));
/*     */     }
/*     */   }
/*     */ 
/*     */   private static enum State
/*     */   {
/*  23 */     Disconnected, 
/*     */ 
/*  25 */     Connecting, 
/*     */ 
/*  27 */     Connected;
/*     */   }
/*     */ }

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.AsyncSocket
 * JD-Core Version:    0.6.2
 */