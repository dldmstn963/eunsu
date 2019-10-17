<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="employee.model.vo.Employee,classs.model.vo.Classs, java.util.ArrayList"%>
<%
	Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");
	ArrayList<Classs> list = (ArrayList<Classs>) request.getAttribute("list");
	int currentPage = ((Integer) request.getAttribute("currentPage")).intValue();
	int beginPage = ((Integer) request.getAttribute("beginPage")).intValue();
	int endPage = ((Integer) request.getAttribute("endPage")).intValue();
	int maxPage = ((Integer) request.getAttribute("maxPage")).intValue();
	String searchno = ((String)request.getAttribute("searchno").toString());
	String searchname = ((String)request.getAttribute("searchname").toString());
	String searchtype = ((String)request.getAttribute("searchtype").toString());
	String searchdepart = ((String)request.getAttribute("searchdepart").toString());
%>
<!DOCTYPE html>
<html>
<title>과목 수정 삭제 페이지</title>
<script type="text/javascript"
	src="/eunsu/resources/js/jquery-3.4.1.min.js"></script>

<script type="text/javascript">
$(function() {
	var sort = 0;
$("#classNoSortD").click(function() {
	sort = 1;
	$.ajax({
		url : "/eunsu/classssearch",
		data : {sort : sort},
		success : function(data) {
			location.href = "http://127.0.0.1:9595/eunsu/classssearch?page=<%=currentPage%>&searchno=<%= searchno%>&searchname=<%=searchname %>&searchtype=<%=searchtype %>&searchdepart=<%= searchdepart%>";
		}
	})//ajax
	return false;
});//click

$("#classNoSortA").click(function() {
	sort = 0;
	$.ajax({
		url : "/eunsu/classssearch",
		data : {sort : sort},
		success : function(data) {
			location.href ="http://127.0.0.1:9595/eunsu/classssearch?page=<%=currentPage%>&searchno=<%= searchno%>&searchname=<%=searchname %>&searchtype=<%=searchtype %>&searchdepart=<%= searchdepart%>";
		}
	})//ajax
	return false;
});//click

$("#classNameSortD").click(function(){
	sort = 2;
	$.ajax({
		url:"/eunsu/classssearch",
		data : {sort : sort},
		success : function(data) {
			location.href = "http://127.0.0.1:9595/eunsu/classssearch?page=<%=currentPage%>&searchno=<%= searchno%>&searchname=<%=searchname %>&searchtype=<%=searchtype %>&searchdepart=<%= searchdepart%>";
		}
	})
	return false;
});//click

$("#classNameSortA").click(function(){
	sort = 3;
	$.ajax({
		url:"/eunsu/classssearch",
		data : {sort : sort},
		success : function(data) {
			location.href = "http://127.0.0.1:9595/eunsu/classssearch?page=<%=currentPage%>&searchno=<%= searchno%>&searchname=<%=searchname %>&searchtype=<%=searchtype %>&searchdepart=<%= searchdepart%>";
		}
	})
	return false;
});//click

$("#classTypeSortD").click(function(){
	sort = 4;
	$.ajax({
		url:"/eunsu/classssearch",
		data : {sort : sort},
		success : function(data) {
			location.href = "http://127.0.0.1:9595/eunsu/classssearch?page=<%=currentPage%>&searchno=<%= searchno%>&searchname=<%=searchname %>&searchtype=<%=searchtype %>&searchdepart=<%= searchdepart%>";
		}
	})
	return false;
});//click

$("#classTypeSortA").click(function(){
	sort = 5;
	$.ajax({
		url:"/eunsu/classssearch",
		data : {sort : sort},
		success : function(data) {
			location.href = "http://127.0.0.1:9595/eunsu/classssearch?page=<%=currentPage%>&searchno=<%= searchno%>&searchname=<%=searchname %>&searchtype=<%=searchtype %>&searchdepart=<%= searchdepart%>";
		}
	})
	return false;
});//click

$("#classDepartSortD").click(function(){
	sort = 6;
	$.ajax({
		url:"/eunsu/classssearch",
		data : {sort : sort},
		success : function(data) {
			location.href = "http://127.0.0.1:9595/eunsu/classssearch?page=<%=currentPage%>&searchno=<%= searchno%>&searchname=<%=searchname %>&searchtype=<%=searchtype %>&searchdepart=<%= searchdepart%>";
		}
	})
	return false;
});//click

$("#classDepartSortA").click(function(){
	sort = 7;
	$.ajax({
		url:"/eunsu/classssearch",
		data : {sort : sort},
		success : function(data) {
			location.href = "http://127.0.0.1:9595/eunsu/classssearch?page=<%=currentPage%>&searchno=<%= searchno%>&searchname=<%=searchname %>&searchtype=<%=searchtype %>&searchdepart=<%= searchdepart%>";
		}
	})
	return false;
});//click
$("#classPreSortD").click(function(){
	sort = 8;
	$.ajax({
		url:"/eunsu/classssearch",
		data : {sort : sort},
		success : function(data) {
			location.href = "http://127.0.0.1:9595/eunsu/classssearch?page=<%=currentPage%>&searchno=<%= searchno%>&searchname=<%=searchname %>&searchtype=<%=searchtype %>&searchdepart=<%= searchdepart%>";
		}
	})
	return false;
});//click
$("#classPreSortA").click(function(){
	sort = 9;
	$.ajax({
		url:"/eunsu/classssearch",
		data : {sort : sort},
		success : function(data) {
			location.href = "http://127.0.0.1:9595/eunsu/classssearch?page=<%=currentPage%>&searchno=<%= searchno%>&searchname=<%=searchname %>&searchtype=<%=searchtype %>&searchdepart=<%= searchdepart%>";
		}
	})
	return false;
});//click
});//document
	
	function dellist(){
		var result = confirm('정말 삭제하시겠습니까?');
		if(result){
		var lists = [];
		  $("#checkbox:checked").each(function(i){   //jQuery로 for문 돌면서 check 된값 배열에 담는다
		   lists.push($(this).val());
		  });
		  console.log(lists);
		 var list = lists.join(","); 
		 console.log(list);
		$.ajax({
			url:"/eunsu/classsdelete",
			type : "post",
			data : {
				lists : list
			},
			success : function(data){
				location.href = "http://127.0.0.1:9595/eunsu/classslist?page="+<%=currentPage%>;
				$("#alertbox").html(data);
			}
		})
		return false;
	}}
</script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/eunsu/resources/css/w3.css">
<link rel="stylesheet"
	href="/eunsu/resources/css/w3-theme-blue-grey.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<body class="w3-theme-l5">
	<div id="classTable">

		<!-- Navbar -->
		<div class="w3-top">
			<div class="w3-bar w3-theme-d2 w3-left-align w3-large">
				<a
					class="w3-bar-item w3-button w3-hide-medium w3-hide-large w3-right w3-padding-large w3-hover-white w3-large w3-theme-d2"
					href="javascript:void(0);" onclick="openNav()"><i
					class="fa fa-bars"></i></a> <a href="/eunsu/views/main.jsp"
					class="w3-bar-item w3-button w3-padding-large w3-theme-d4"><i
					class="fa fa-home w3-margin-right"></i>수은 대학교</a> <a href="#"
					class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white"
					title="News"><i class="fa fa-globe"></i></a> <a href="#"
					class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white"
					title="Account Settings"><i class="fa fa-user"></i></a> <a href="#"
					class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white"
					title="Messages"><i class="fa fa-envelope"></i></a> <a href="#"
					class="w3-bar-item w3-button w3-hide-small w3-right w3-padding-large w3-hover-white"
					title="My Account"> </a>
			</div>
		</div>

		<!-- Navbar on small screens -->
		<div id="navDemo"
			class="w3-bar-block w3-theme-d2 w3-hide w3-hide-large w3-hide-medium w3-large">
			<a href="/eunsu/views/studentcrud/studentinsert.jsp"
				class="w3-bar-item w3-button w3-padding-large">학생 추가</a> <a
				href="/eunsu/views/studentcrud/studentinsert.jsp"
				class="w3-bar-item w3-button w3-padding-large">학생 추가</a> <a href="#"
				class="w3-bar-item w3-button w3-padding-large">ㅇㅇ 3</a> <a href="#"
				class="w3-bar-item w3-button w3-padding-large">My Profile</a>
		</div>

		<!-- Page Container -->
		<div class="w3-container w3-content"
			style="max-width: 1400px; margin-top: 80px">
			<!-- The Grid -->
			<div class="w3-row">
				<!-- Left Column -->
				<div class="w3-col m3">
					<!-- Profile -->
					<div class="w3-card w3-round w3-white">
						<div class="w3-container">
							<h4 class="w3-center">
								내 정보 <i class="fa fa-pencil"></i>
							</h4>
							<p class="w3-center">
								<img src="<%=loginEmployee.getEmployeeimage()%>"
									class="w3-circle" style="height: 106px; width: 106px"
									alt="Avatar">
							</p>
							<hr>
							<p>
								<i class="fa fa-pencil fa-fw w3-margin-right w3-text-theme"></i>
								<%=loginEmployee.getEmployeeName()%>
							</p>
							<p>
								<i class="fa fa-pencil fa-fw w3-margin-right w3-text-theme"></i>
								<%=loginEmployee.getEmpDepartment()%>
							</p>
							<br> <a href="/eunsu/logout" style="text-decoration: none;">로그아웃</a>
						</div>
					</div>
					<br>

					<!-- Accordion -->
					<div class="w3-card w3-round">
						<div class="w3-white">
							<button onclick="myFunction('Demo1')"
								class="w3-button w3-block w3-theme-l1 w3-left-align">
								<i class="fa fa-circle-o-notch fa-fw w3-margin-right"></i> 과목 관리
							</button>
							<div id="Demo1" class="w3-hide w3-container">
								<p>
									<a href="#">과목 관리</a>
								</p>
							</div>
							<button onclick="myFunction('Demo2')"
								class="w3-button w3-block w3-theme-l1 w3-left-align">
								<i class="fa fa-calendar-check-o fa-fw w3-margin-right"></i> 학생
								관리
							</button>
							<div id="Demo2" class="w3-hide w3-container">
								<p>
									<a href="/eunsu/views/studentcrud/studentinsert.jsp">학생 추가</a>
								</p>
								<p>
									<a href="/eunsu/views/studentcrud/studentinsert.jsp">학생 수정</a>
								</p>
							</div>
							<button onclick="myFunction('Demo3')"
								class="w3-button w3-block w3-theme-l1 w3-left-align">
								<i class="fa fa-users fa-fw w3-margin-right"></i>학교 관리
							</button>
							<div id="Demo3" class="w3-hide w3-container">
								<br>
								<div class="w3-half">
									<p>교수 관리</p>
								</div>
								<div class="w3-half">
									<p>직원 관리</p>
								</div>
								<div class="w3-half">
									<p>증명서 관리</p>
								</div>
								<div class="w3-half">
									<p>휴학 증명서</p>
								</div>
								<div class="w3-half">
									<p>
										졸업 예정 <br>증명서
									</p>
								</div>
								<div class="w3-half">
									<p>수료 증명서</p>
								</div>
							</div>
						</div>
					</div>
					<br>


					<!-- End Left Column -->
				</div>
				<!-- End Grid -->
				<h1 align="center">과목 수정</h1>
				<div style="display:none;" id="alertbox"></div>
				<form action="/eunsu/classssearch" align="center">
				과목 번호 : <input type="text" style="width: 90px;" name="searchno" value="">
				&nbsp;&nbsp;과목 명 : <input type="text" style="width: 170px;"name="searchname" value=""><br><br>
				
				과목 분류 : <select name="searchtype">
				<option value="">선택 해주세요</option>
				<option value="공통과목">공통과목</option>
				<option value="논문지도">논문지도</option>
				<option value="전공선택">전공선택</option>
				<option value="전공필수">전공필수</option>
				</select>
				
				&nbsp;&nbsp;학과 번호 : <input type="text" style="width: 35px;" name="searchdepart" value="">
				
				<br>
				<input type="submit" value="검색">
				</form>
				<br>
				<form action="/eunsu/classsupdate">
					<table align="center" border="1" cellspacing="0" cellpadding="3"
						id="myTable2">
						<tr>
							<th>체크 박스</th>
							<th>과목 번호<button id="classNoSortD">↓</button><button id="classNoSortA">↑</button></th>
							<th>과목 이름<button id="classNameSortD">↓</button><button id="classNameSortA">↑</button></th>
							<th>과목 분류<button id="classTypeSortD">↓</button><button id="classTypeSortA">↑</button></th>
							<th>학과 번호<button id="classDepartSortD">↓</button><button id="classDepartSortA">↑</button></th>
							<th>선행 과목<button id="classPreSortD">↓</button><button id="classPreSortA">↑</button></th>
						</tr>
						<%
							for (Classs c : list) {
						%>
						<tr>
							<td align="center"><input type="checkbox" id="checkbox" name="checkbox" value="<%=c.getClassNo()%>"></td>
							<td style="display:none;"><input type="text" name="classNo"value="<%=c.getClassNo()%>"></td>
							<td><%=c.getClassNo()%></td>
							<td><input type="text" style="width: 170px;" name="className"
								value="<%=c.getClassName()%>"></td>
							<td><input type="text" style="width: 70px;" name="classType"
								value="<%=c.getClassType()%>"></td>
							<td><input type="text" style="width: 35px;" name="departmentNo"
								value="<%=c.getDepartmentNo()%>"></td>
							<%
								if (c.getPreatendingClassNo() == null) {
							%>
							<td><input type="text" value="없음" style="width: 90px;" name="preatendingClassNo"></td>
							<%
								} else {
							%>
							<td><input type="text" style="width: 90px;" name="preatendingClassNo"
								value="<%=c.getPreatendingClassNo()%>"></td>
							<%
								}
							%>
						</tr>
						<%
							}
						%>
					</table>
					<br>
					<center>
						<input type="submit" value="수정" > &nbsp; <input
							type="reset" value="초기화"> &nbsp; 
							<input type="button" value="삭제" onclick="return dellist();">
					</center>
				</form>
				<br>
				<div id="pagebox" align="center">
					<a href="/eunsu/classssearch?page=1&searchno=<%= searchno%>&searchname=<%=searchname %>&searchtype=<%=searchtype %>&searchdepart=<%= searchdepart%>">|◁</a> &nbsp;
					<%
						if ((beginPage - 10) < 1) {
					%>
					<a href="/eunsu/classssearch?page=1&searchno=<%= searchno%>&searchname=<%=searchname %>&searchtype=<%=searchtype %>&searchdepart=<%= searchdepart%>">◀◀</a>
					<%
						} else {
					%>
					<a href="/eunsu/classssearch?page=<%=beginPage - 10%>&searchno=<%= searchno%>&searchname=<%=searchname %>&searchtype=<%=searchtype %>&searchdepart=<%= searchdepart%>">◀◀</a>
					<%
						}
					%>
					&nbsp;
					<%
						for (int p = beginPage; p <= endPage; p++) {
							if (p == currentPage) {
					%>
					<a href="/eunsu/classssearch?page=<%=p%>&searchno=<%= searchno%>&searchname=<%=searchname %>&searchtype=<%=searchtype %>&searchdepart=<%= searchdepart%>"><font color="red"><b>[<%=p%>]
						</b></font></a>
					<%
						} else {
					%>
					<%--/classssearch?page=2&searchno=&searchname=&searchtype=공통과목&searchdepart= --%>
					<a href="/eunsu/classssearch?page=<%=p%>&searchno=<%= searchno%>&searchname=<%=searchname %>&searchtype=<%=searchtype %>&searchdepart=<%= searchdepart%>"><%=p%></a>
					<%
						}
						}
					%>
					&nbsp;
					<%
						if ((endPage + 10) > maxPage) {
					%>
					<a href="/eunsu/classssearch?page=<%=maxPage%>&searchno=<%= searchno%>&searchname=<%=searchname %>&searchtype=<%=searchtype %>&searchdepart=<%= searchdepart%>">▶▶</a>
					<%
						} else {
					%>
					<a href="/eunsu/classssearch?page=<%=endPage + 10%>&searchno=<%= searchno%>&searchname=<%=searchname %>&searchtype=<%=searchtype %>&searchdepart=<%= searchdepart%>">▶▶</a>
					<%
						}
					%>
					&nbsp; <a href="/eunsu/classssearch?page=<%=maxPage%>&searchno=<%= searchno%>&searchname=<%=searchname %>&searchtype=<%=searchtype %>&searchdepart=<%= searchdepart%>">▷|</a>&nbsp;&nbsp;&nbsp;
				</div>
			</div>

			<!-- End Page Container -->
		</div>
		<br>

		<!-- Footer -->
		<footer class="w3-container w3-theme-d3 w3-padding-16">
			<h5>서울특별시 강남구 테헤란로14길 6</h5>
			<h6>수은대학교 | Tel. 02-880-5114 | Fax. 02-885-5272 Copyright 2019
				Su Eun University All Rights Reserved.</h6>
		</footer>

		<script>
			// Accordion
			function myFunction(id) {
				var x = document.getElementById(id);
				if (x.className.indexOf("w3-show") == -1) {
					x.className += " w3-show";
					x.previousElementSibling.className += " w3-theme-d1";
				} else {
					x.className = x.className.replace("w3-show", "");
					x.previousElementSibling.className = x.previousElementSibling.className
							.replace(" w3-theme-d1", "");
				}
			}

			// Used to toggle the menu on smaller screens when clicking on the menu button
			function openNav() {
				var x = document.getElementById("navDemo");
				if (x.className.indexOf("w3-show") == -1) {
					x.className += " w3-show";
				} else {
					x.className = x.className.replace(" w3-show", "");
				}
			}
		</script>
	</div>

</body>
</html>
