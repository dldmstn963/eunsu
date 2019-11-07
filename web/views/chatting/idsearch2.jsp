<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.ArrayList,chatting.model.vo.Chat"%>

<%
	ArrayList<Chat> list = (ArrayList<Chat>) request.getAttribute("list");
	int currentPage = ((Integer) request.getAttribute("currentPage")).intValue();
	int beginPage = ((Integer) request.getAttribute("beginPage")).intValue();
	int endPage = ((Integer) request.getAttribute("endPage")).intValue();
	int maxPage = ((Integer) request.getAttribute("maxPage")).intValue();
	String type = ((String)request.getAttribute("type").toString());
	String id = ((String)request.getAttribute("id").toString());
	String name = ((String)request.getAttribute("name").toString());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수은 대학교</title>
<script type="text/javascript"
	src="/eunsu/resources/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<table align="center" border="1" cellspacing="0" cellpadding="3"
		id="myTable2">
		<tr>
			<th>아이디</th>
			<th>이 름</th>
		</tr>
		<%
			for (Chat c : list) {
		%>
		<tr>
		<script type="text/javascript">
			$(function(){
				$('#<%=c.getReceiver()%>').on('click', function() {
						window.opener.testCheck('<%=c.getReceiver()%>');
						self.close();
					});
				});
			</script>
			<td id="<%=c.getReceiver()%>"><%=c.getReceiver()%></td>
			<td><%=c.getReceiverName()%></td>
		</tr>
		<%
			}
		%>
	</table>
	<div id="pagebox" align="center">
		<a href="/eunsu/chatidsearch?page=1&type=<%=type%>&id=<%=id%>&name=<%=name%>">|◁</a> &nbsp;
		<%
			if ((beginPage - 10) < 1) {
		%>
		<a href="/eunsu/chatidsearch?page=1&type=<%=type%>&id=<%=id%>&name=<%=name%>">◀◀</a>
		<%
			} else {
		%>
		<a href="/eunsu/chatidsearch?page=<%=beginPage - 10%>&type=<%=type%>&id=<%=id%>&name=<%=name%>">◀◀</a>
		<%
			}
		%>
		&nbsp;
		<%
			for (int p = beginPage; p <= endPage; p++) {
				if (p == currentPage) {
		%>
		<a href="/eunsu/chatidsearch?page=<%=p%>&type=<%=type%>&id=<%=id%>&name=<%=name%>"><font color="red"><b>[<%=p%>]
			</b></font></a>
		<%
			} else {
		%>
		<a href="/eunsu/chatidsearch?page=<%=p%>&type=<%=type%>&id=<%=id%>&name=<%=name%>"><%=p%></a>
		<%
			}
			}
		%>
		&nbsp;
		<%
			if ((endPage + 10) > maxPage) {
		%>
		<a href="/eunsu/chatidsearch?page=<%=maxPage%>&type=<%=type%>&id=<%=id%>&name=<%=name%>">▶▶</a>
		<%
			} else {
		%>
		<a href="/eunsu/chatidsearch?page=<%=endPage + 10%>&type=<%=type%>&id=<%=id%>&name=<%=name%>">▶▶</a>
		<%
			}
		%>
		&nbsp; <a href="/eunsu/chatidsearch?page=<%=maxPage%>&type=<%=type%>&id=<%=id%>&name=<%=name%>">▷|</a>
	</div>
<br>
	<form align="center" action="/eunsu/chatidsearch">
	분류 : <select name="type">
	<option value="">선택 해주세요</option>
	<option value="직원">직원</option>
	<option value="교수">교수</option>
	<option value="학생">학생</option>
	</select><br><bt>
	아이디 검색 : <input type="text" name="id"><br>
	이름 검색 : <input type="text" name="name"><br><br>
	<input type="submit" value="검색">
	</form>




</body>
</html>