<%@page language="java" contentType="application/x-msdownload" pageEncoding="utf-8"%>
<%@page import="java.awt.Window"%>
<%@page import="java.net.*"%> 
<%@page import="java.io.*"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>download.jsp</title>
  </head>
  
  <body>
<%
      //关于文件下载时采用文件流输出的方式处理：
      //加上response.reset()，并且所有的％>后面不要换行，包括最后一个；

      response.reset();//可以加也可以不加
      response.setContentType("application/x-download");
      String filedownload = (String)request.getAttribute("filePath");
      String filedisplay = (String)request.getAttribute("fileName");
      //filedisplay = URLEncoder.encode(filedisplay,"UTF-8");
      response.addHeader("Content-Disposition","attachment;filename=\"" + new String(filedisplay.getBytes("gb2312"),"iso8859-1")+"\"");

      OutputStream outp = null;
      FileInputStream in = null;
      try
      {
          outp = response.getOutputStream();
          in = new FileInputStream(filedownload);

          byte[] b = new byte[1024];
          int i = 0;

          while((i = in.read(b)) > 0)
          {
              outp.write(b, 0, i);
          }
          outp.flush();
      }
      catch(Exception e)
      {
          System.out.println("Error!");
          e.printStackTrace();
      }
      finally
      {
          if(in != null)
          {
              in.close();
              in = null;
          }
          if(outp != null)
          {
              outp.close();
              outp = null;
          }
      }
%>
  </body>
</html>
