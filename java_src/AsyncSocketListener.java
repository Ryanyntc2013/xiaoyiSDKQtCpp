package com.xiaoyi.action;

import java.nio.ByteBuffer;

abstract interface AsyncSocketListener
{
  public abstract void onConnected(AsyncSocket paramAsyncSocket);

  public abstract void onClosed(AsyncSocket paramAsyncSocket, Error paramError);

  public abstract void onDataSent(AsyncSocket paramAsyncSocket, ByteBuffer paramByteBuffer);

  public abstract void onDataReceived(AsyncSocket paramAsyncSocket, ByteBuffer paramByteBuffer);
}

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.AsyncSocketListener
 * JD-Core Version:    0.6.2
 */