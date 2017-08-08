<%@ page language="java" import="com.weibo.model.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>粉丝</title>
<style type="text/css">
	a{text-decoration:none}
	#left{
		width:20%;
		height:600px;
		float:left;	
	}
	#right{
		width:80%;
		height:800px;
		float:right;
	}
	#publish{
		width:60%;
		height:20%;
		float:left;
	}
	#user{
		width:40%;
		height:20%;
		float:right;
	}
	#inf{
		width:60%;
		height:80%;
		float:left;
	}
	#user_1{
		width:50%;
		height:100%;
		float:right;
	}
	#user_2{
		width:50%;
		height:100%;
		float:left;
	}
</style>
</head>
<%
	User u=(User)request.getAttribute("user");
	int followNum=u.getFollowNum();
	int followerNum=u.getFollowerNum();
	int weiboNum=u.getWeibo();
	String username=u.getUsername();
	List<Weibo> followWeibo=(List<Weibo>)request.getAttribute("followWeibo");
	List<Follower> follower=u.getFollower();
	%>
<body bgcolor="#FFFCEC">
	<div id="left">
		<a href="/weibo/login?username=<%=request.getAttribute("guest")%>&password=<%=request.getAttribute("guest")%>&method=login">返回主页</a>
		<br>
		<%=username %>的粉丝
		<ul>
			<%
				for(int i=0;i<follower.size();++i){
			%>		
				<li><a href="/weibo/guest?username=<%=follower.get(i).getUsername()%>&guest=<%=request.getAttribute("guest")%>&password=<%=request.getAttribute("guest")%>&method=guest"><%=follower.get(i).getUsername()%></a></li>
			<%
				}
			%>
		</ul>
	</div>
	<div id="right">
		<div id="publish">
		</div>
		<div id="user">
			<div id="user_2">
			</div>
			<div id="user_1">
				<br>
				<img alt="pic" src="userImg/<%=username%>.jpg" height="134" width="134px">
				<br><br><br>
				<h3 style="color:#7E3D76;text-align:center;"><a href="/weibo/user?method=user&username=<%=username%>&guest=<%=request.getAttribute("guest")%>&password=<%=request.getAttribute("password")%>"><font size="5"><%=username%></font></a></h3>
				<h5 style="text-align:center;">关注:<a href="/weibo/followPage?username=<%=username%>&password=<%=request.getAttribute("password")%>&guest=<%=request.getAttribute("guest")%>&method=followPage"><%=followNum%></a></h5>
				<h5 style="text-align:center;">粉丝:<a href="/weibo/followerPage?username=<%=username%>&password=<%=request.getAttribute("password")%>&guest=<%=request.getAttribute("guest")%>&method=followerPage"><%=followerNum%></a></h5>
				<h5 style="text-align:center;">微博:<%=weiboNum%></h5>
			</div>
		</div>
		<div id="inf">
		</div>
	</div>
</body>
</html>