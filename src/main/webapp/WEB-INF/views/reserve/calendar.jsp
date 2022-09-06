<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<!-- RWD -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- MS -->
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8,IE=EmulateIE9"/> 
<title>JSP bean ���</title>
<!--bootstrap-->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<!--jquery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!--propper jquery -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<!--latest javascript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<!--fontawesome icon-->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" 
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
<!--google icon -->
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<style type="text/css">

@font-face {
    font-family: 'GmarketSansBold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansBold.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

#caledar>tbody>tr>td:checked {
    background-color: #ff3f3e;
    color: #fff;
}

#calendar {
    margin-left:auto; 
    margin-right:auto;
    border-spacing: 0.8vw;
  	border-collapse: separate;
}

#calendar>tbody>tr>td {
	font-size : 1.5rem;
	  width: 30px;
	  font-family: 'GmarketSansBold'
    
};


</style>


<script type="text/javascript">
var absolutetoday = new Date();
var today = new Date();
var ymd = null
function buildCalendar() {
	var row = null
	var cnt = 0;
	var calendarTable = document.getElementById("calendar");
	var calendarTableTitle = document.getElementById("calendarTitle");
	calendarTableTitle.innerHTML = today.getFullYear()+"��"+(today.getMonth()+1)+"��";
	var firstDate = new Date(today.getFullYear(), today.getMonth(),1);
	var lastDate = new Date(today.getFullYear(), today.getMonth()+1,0);

	var day = ('0' + today.getDate()).slice(-2);
	console.log(day);
	
	
	while(calendarTable.rows.length > 2){
		calendarTable.deleteRow(calendarTable.rows.length-1);
	}
	
	row = calendarTable.insertRow(); // �� �߰�
	// ���� ù�� ��ĭ ����
	for(i=0; i<firstDate.getDay(); i++) { // getDay() ��7���� ���ڷ� ���� (��:0,��:1 ...)
		cell = row.insertCell(); // �� �߰� 
		cnt += 1;
	}
	
	// �޷¿� ���� ä���
	for(i=1; i<= lastDate.getDate();i++){ // getDate() ����Ÿ���� �� ���� ���ڷ� ���� 31���̸� 31����
		cell = row.insertCell(); // �� �߰� 
		cnt += 1; 
		
		// ���� �þ id 1��.. id2 =2 id3=3 ���þ
		cell.setAttribute("id",i);   // �Ӽ� �߰� id�� i��
		cell.innerHTML = i; // id���� i�� �ο�
		cell.align = "center";  // �߾�����
	
		
		// Ư��ȿ�� ������ �� ���
		cell.onclick = function() {
			
	//		alert(this.getAttribute("id"));
			
			
			
			var s = document.getElementsByName("choice");
		
	
			for(var i=0; i<s.length; i++) {
				s[i].removeAttribute('style');
			}
			
			this.style.backgroundColor='#F9D142';
		    this.style.padding='1px';
		    this.style.borderRadius='5px';
		    this.style.color='#292826';
			
			this.setAttribute('name', 'choice')
			
			
			clickedYear = today.getFullYear(); // ���ó⵵
			clickedMonth = today.getMonth()+1; // ���ÿ�
			clickedDate = this.getAttribute("id");
			console.log("clickedYear",clickedYear);
			console.log("clickedMonth",clickedMonth);
			console.log("clickedDate",clickedDate);
			
			clickedDate = clickedDate >= 10 ? clickedDate : "0" + clickedDate;
			clickedMonth = clickedMonth >= 10 ? clickedMonth : "0" + clickedMonth;
			clickedYMD = clickedYear + "_" + clickedMonth + "_" + clickedDate;
		
			console.log("clickedDate",clickedDate);
			console.log("clickedMonth",clickedMonth);
			console.log("clickedYMD",clickedYMD);
			
			
		
			ymd = clickedYMD;
			
			
			$.ajax({
				url : "themeNameList",
				type : "get",
				data : {"ymd":ymd},
				contentType : "application/json; charset=utf-8;",
				success: function(data){
					$("#indexListAjax").html(data);
					
				},
				error:function(data){
					alert("�����");
				}
				
			})
			
		}
		
		// cnt�� 7�� �Ǹ� �� = �����������̵Ǹ�
		if(cnt % 7 == 0 ){ 
			row = calendar.insertRow(); // ���� �߰��Ͽ� ���� ���� ���� 
		}
		
		// �Ͽ��� ����
		if (cnt % 7 == 1) {
			cell.innerHTML = "<font color=#F79DC2>" + i + "</font>";
		}  
	 	// ����� �Ķ� ȿ��
		if (cnt % 7 == 0){
			cell.innerHTML = "<font color=skyblue>" + i + "</font>";
			row = calendar.insertRow();
		}
		
	}
	
	// �޷��� �������� ��ĭ ������ ä���
	if(cnt % 7 != 0) { //cnt�� 7�� �ƴѰ��
		for(i = 0; i<7- (cnt%7); i++) { 
			cell = row.insertCell(); // ���� �߰��Ͽ� �������� ����
		}
	}	
	
}
function nextCalendar() {
	today = new Date(today.getFullYear(), today.getMonth()+1, today.getDate());

	buildCalendar();
}
function prevCalendar() {
	

		today = new Date(today.getFullYear(), today.getMonth()-1, today.getDate());

		buildCalendar();

}




</script>
</head>

<body onload="buildCalendar()">
<table id="calendar">
	<tr>
		<td><label onclick="prevCalendar()"> �� </label></td>
		<td colspan="5" id="calendarTitle" style="text-align:center">yyyy�� m��</td>
		<td><label onclick="nextCalendar()"> �� </label></td>
	</tr>
	<tr>
		<td><font color ="#F79DC2">��</font></td>
		<td>��</td>
		<td>ȭ</td>
		<td>��</td>
		<td>��</td>
		<td>��</td>
		<td><font color ="skyblue">��</font></td>
	</tr>
		
		
</table>



</body>
</html>