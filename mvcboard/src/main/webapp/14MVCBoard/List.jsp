<%@page import="java.util.List"%>
<%@page import="kr.seoul.mvcboard.MVCBoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록</title>
</head>
<body>
목록
<hr>
<%= request.getAttribute("count") %>
<hr>
<%
List<MVCBoardDTO> board = (List<MVCBoardDTO>)request.getAttribute("board");
if (board.size() == 0) {
%>  게시물 없음
<%	
} else {
	for(MVCBoardDTO row: board) {
%><%= row.getName() %><br>
<%		
	}
%>
<%	
}
%>
${ requestScope["map"]["pagingImg"] }
</body>
</html>