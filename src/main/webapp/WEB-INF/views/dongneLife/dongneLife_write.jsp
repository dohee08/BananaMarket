<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	div.dongnelife_write {
		width: 70%;
		margin:auto;
		height: 1000px;
		padding-top:150px;
		border:1px solid green;
	}
	section.section2_dongneLife_write,
	section.section3_dongneLife_write,
	section.section4_dongneLife_write{
		width:45%;
		margin-left:25%;
		padding-left:4%;
		margin-bottom:40px;
	}
	section.section1_dongneLife_write div.write_nav {
		border-bottom:1px solid black;
		text-align:center;
		padding:10px 0;
	}
	section.section1_dongneLife_write li {
		display:inline-block;
		padding:0 100px;
		font-size:22px;
		font-weight:800;
	}
	section.section1_dongneLife_write li:nth-child(3) button {
		color:RGB(82,67,21);
		background-color:RGB(254,229,0);
		font-weight:bold;
		border:1px solid RGB(254,229,0);
		padding:7px 17px;
		font-size:17px;
		border-radius:5px;
	}
	section.section1_dongneLife_write li:nth-child(3) button:hover{
		cursor:pointer;
		opacity:0.7;
	}
	section.section1_dongneLife_write li:first-child img {
		width:30px;
		height:30px;
		margin-bottom:-5px;
	}
	section.section2_dongneLife_write div.write_topic {
		padding-top:40px;
	}
	section.section2_dongneLife_write div.write_topic label {
		font-size:19px;
		font-weight:700;
	}
	section.section2_dongneLife_write div.write_topic label:hover {
		cursor:pointer;
	}
	section.section3_dongneLife_write div.write_content textarea {
		width:92%;
		height:600px;
		overflow:auto;
		resize: none;
		font-size:18px;
		padding:10px 10px;
	}
	section.section4_dongneLife_write a {
		margin-right:0 30px;
	}
	section.section4_dongneLife_write img {
		width:30px;
		height:30px;
		margin-bottom:-7px;
	}
</style>
</head>
<body>
	<jsp:include page="../header.jsp"/>
	
	<div class="dongnelife_write">
		<section class="section1_dongneLife_write">
			<div class="write_nav">
				<ul>
					<li><a href="dongneLife.do"><img src="http://localhost:9000/banana/images/dongneLife_backword.png"><button type="button"></button></a></li>
					<li><label>동네생활 글쓰기</label></li>
					<li><a href="dongneLife_write_proc.do"><button>완료</button></a></li>
				</ul>
			</div>
		</section>
		<section class="section2_dongneLife_write">
			<div class="write_topic" id="write_topic">
				<a href="#"><label>게시글의 주제를 선택해주세요.</label></a>
			</div>
		</section>
		<section class="section3_dongneLife_write">
			<div class="write_content">
				<textarea placeholder="서초4동 우리 동네 관련된 질문이나 이야기를 해보세요."></textarea>
			</div>
		</section>
		<section class="section4_dongneLife_write">
			<div>
				<a href="#"><img src="http://localhost:9000/banana/images/dongneLife_inputimg.png">0/10</a>
				<a href="#"><img src="http://localhost:9000/banana/images/dongneLife_locate.png">0/5</a>
			</div>
		</section>
	</div>
	
	<jsp:include page="../footer.jsp"/>

</body>
</html>