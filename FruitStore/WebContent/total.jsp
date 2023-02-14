<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="kr.edu.mit.SalesVO"%>
<%@page import="kr.edu.mit.FruitStoreDAOImpl"%>
<%@page import="kr.edu.mit.FruitStoreDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매출 확인</title>
<style>
.totalprice{
  -webkit-animation: blink 0.5s ease-in-out infinite alternate;
  
}

@-webkit-keyframes blink{
  0% {opacity: 0;}
  100% {opacity: 1;}
}


</style>
</head>
<body>
<%
	FruitStoreDAO dao = new FruitStoreDAOImpl();
	List<SalesVO> list = dao.listSales();

%>
<table border="1">
	<tr><th>과일코드</th> <th>이름</th> <th>판매수량</th> <th>판매금액</th> <th>판매일자</th>
	</tr>
	
<%
	for(SalesVO vo: list){%>
		<tr><td><%=vo.getFruit_code() %></td> 
			<td><%=vo.getFruit_name() %></td>
			<td><%=vo.getSales_quantity() %></td>
			<td><%=vo.getTotal() %></td>
			<td><%=vo.getSales_date() %></td></tr>
			
	<%}

%>
	
			<td class="totalprice" colspan="5" align="right" style="color: #ff7f50" style=animation: blink-effect 2s step-end infinite;>
			매출의 총합은 : <%=dao.totalPrice() %></td>

	<tr> <td colspan="5" align="right"><button type="button" onclick="gotitle()">메뉴로 돌아가기</button></td>
	</tr>
</table>

<script>
function gotitle(){
	history.back();//뒤로가기
}
</script>

</body>
</html>