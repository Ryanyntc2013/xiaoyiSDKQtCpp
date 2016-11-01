package com.xiaoyi.action;

import java.net.HttpURLConnection;

public class DownloadTask
{
  public String fileName;
  public String destFilePath;
  public long totalBytes;
  public long downloadedBytes;
  long lastDownloadBytesOnTimeout;
  HttpURLConnection downloadHttpConnection;
}

/* Location:           E:\YIOpenAPI-master\YIOpenAPI-master\sdk\java\libs\libyiaction.jar
 * Qualified Name:     com.xiaoyi.action.DownloadTask
 * JD-Core Version:    0.6.2
 */