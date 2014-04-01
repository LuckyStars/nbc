<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	Map req = request.getParameterMap();

	for(Object k:req.keySet()){
		out.write(k.toString());
		out.write("====");
		out.write(req.get(k).toString());
		out.write("\n\n");
	}
%>