<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.ArrayList,chatting.model.vo.Chat"%>

<%
	ArrayList<Chat> list = (ArrayList<Chat>) request.getAttribute("list");
	int currentPage = ((Integer) request.getAttribute("currentPage")).intValue();
	int beginPage = ((Integer) request.getAttribute("beginPage")).intValue();
	int endPage = ((Integer) request.getAttribute("endPage")).intValue();
	int maxPage = ((Integer) request.getAttribute("maxPage")).intValue();
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
		<a href="/eunsu/idsearch?page=1">|◁</a> &nbsp;
		<%
			if ((beginPage - 10) < 1) {
		%>
		<a href="/eunsu/idsearch?page=1">◀◀</a>
		<%
			} else {
		%>
		<a href="/eunsu/idsearch?page=<%=beginPage - 10%>">◀◀</a>
		<%
			}
		%>
		&nbsp;
		<%
			for (int p = beginPage; p <= endPage; p++) {
				if (p == currentPage) {
		%>
		<a href="/eunsu/idsearch?page=<%=p%>"><font color="red"><b>[<%=p%>]
			</b></font></a>
		<%
			} else {
		%>
		<a href="/eunsu/idsearch?page=<%=p%>"><%=p%></a>
		<%
			}
			}
		%>
		&nbsp;
		<%
			if ((endPage + 10) > maxPage) {
		%>
		<a href="/eunsu/idsearch?page=<%=maxPage%>">▶▶</a>
		<%
			} else {
		%>
		<a href="/eunsu/idsearch?page=<%=endPage + 10%>">▶▶</a>
		<%
			}
		%>
		&nbsp; <a href="/eunsu/idsearch?page=<%=maxPage%>">▷|</a>
	</div>
	<br>
	<form align="center" action="/eunsu/chatidsearch">
		분류 : <select name="type">
			<option value="">선택 해주세요</option>
			<option value="직원">직원</option>
			<option value="교수">교수</option>
			<option value="학생">학생</option>
		</select><br>
		<bt> 아이디 검색 : <input type="text" name="id" value="">
		<br>
		이름 검색 : <input type="text" name="name" value="">
		<br>
		<br>
		<input type="submit" value="검색">
	</form>




</body>
</html>