<%@ page language="java" import="java.util.*,com.weibo.model.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%
	User u=(User)request.getAttribute("user");
	int guestId=(Integer)request.getAttribute("guestId");
	int userId=(Integer)u.getId();
	int age=u.getAge();
	int followNum=u.getFollowNum();
	int followerNum=u.getFollowerNum();
	int weiboNum=u.getWeibo();
	String gender=u.getGender();
	String username=u.getUsername();
	%>
<title><%= username %></title>
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
<body bgcolor="#FFFCEC">
	<div id="left">
		<a href="/weibo/login?username=<%=request.getAttribute("guest")%>&password=<%=request.getAttribute("password")%>&method=login">返回主页</a>
		<ul>
			<li><h4>年龄:<%= age%></h4></li>
			<li><h4>性别:<%= gender %></h4></li>
		</ul>
		<%
		if(!request.getAttribute("guest").equals(username)){
			if((Boolean)request.getAttribute("isFollowed")==false){
		%>		
				<form action="/weibo/follow">
					<input type="hidden" name="username" value=<%=username %>>
					<input type="hidden" name="guest" value=<%=request.getAttribute("guest") %>>
					<input type="hidden" name="password" value= <%=request.getAttribute("password") %>>
					<input type="hidden" name="method" value="follow">
					<input type="submit" value="关注">
				</form>
		<%		
			}
		}	
		%>
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
				<h3 style="color:#7E3D76;text-align:center;"><font size="5" ><%=username%></font></h3>
				<h5 style="text-align:center;">关注:<a href="/weibo/followPage?username=<%=username%>&password=<%=request.getAttribute("password")%>&guest=<%=request.getAttribute("guest")%>&method=followPage"><%=followNum%></a></h5>
				<h5 style="text-align:center;">粉丝:<a href="/weibo/followerPage?username=<%=username%>&password=<%=request.getAttribute("password")%>&guest=<%=request.getAttribute("guest")%>&method=followerPage"><%=followerNum%></a></h5>
				<h5 style="text-align:center;">微博:<%=weiboNum%></h5>
			</div>
		</div>
		<div id="inf">
			<%
				List<UserWeibo> userWeibo=(List<UserWeibo>)u.getUserWeibo();
			%>
			<%
				for(int i=userWeibo.size()-1;i>=0;--i){
				boolean like=false;
				//拿出每条微博的like的所有Id
				List<UserLike> userLike=userWeibo.get(i).getWeibo().getUserLike();
				
			%>					
				<fieldset>
				<legend>
				<img alt="pic" src="userImg/<%=userWeibo.get(i).getWeibo().getUsername() %>.jpg" height="55px" width="55px">
				<a href="/weibo/guest?username=<%= userWeibo.get(i).getWeibo().getUsername()%>&password=${password}&method=guest&guest=<%= request.getAttribute("guest")%>"><font size="5"><%= userWeibo.get(i).getWeibo().getUsername() %></font></a>
				&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
				&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
				&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
				🕖
				<font size="1"><%= userWeibo.get(i).getWeibo().getPublishedTime() %></font>
				<%= "❤"+userLike.size() %>
				</legend>
				<%= "<br>"+userWeibo.get(i).getWeibo().getMessage()+"<br><br>"%>
				
				<%//判断是否点赞
					for(int j=0;j<userLike.size();++j){
						if(userLike.get(j).getUserLikeId()==userId){
							like=true;
							break;
						}
					}
					if(like){
				%>
							<form action="/weibo/dislike">
							<input type="hidden" name="method" value="dislike">
							<input type="hidden" name="weiboId" value=<%=userWeibo.get(i).getWeibo().getId() %>>
							<input type="hidden" name="userId" value=<%=userId %>>
							<input type="hidden" name="username" value= <%=userWeibo.get(i).getWeibo().getUsername() %>>
							<input type="hidden" name="password" value=<%=request.getAttribute("password") %>>
							<input type="hidden" name="guest" value=<%=request.getAttribute("guest") %>>
							<input type="hidden" name="page" value="otherUser">
							<input type="submit" value="👎" style="float:right;background-color:#FFFCEC">
							</form>
				<%
					}
					else{
				%>	
							<form action="/weibo/like" >
							<input type="hidden" name="method" value="like">
							<input type="hidden" name="weiboId" value=<%=userWeibo.get(i).getWeibo().getId() %>>
							<input type="hidden" name="userId" value=<%=userId %>>
							<input type="hidden" name="username" value= <%=userWeibo.get(i).getWeibo().getUsername() %>>
							<input type="hidden" name="password" value=<%=request.getAttribute("password") %>>
							<input type="hidden" name="guest" value=<%=request.getAttribute("guest") %>>
							<input type="hidden" name="page" value="otherUser">
							<input type="submit" value="👍" style="float:right;background-color:#FFFCEC">
							</form>
				
			<%		
					}
			%>
				<form action="/weibo/comment"style="float:right;background-color:#FFFCEC">
					<textarea rows="1" cols="17" name="comment"></textarea>
					<input type="hidden" name="method" value="comment">
					<input type="hidden" name="weiboId" value=<%=userWeibo.get(i).getId() %>>
					<input type="hidden" name="userId" value=<%=userId %>>
					<input type="hidden" name="username" value= <%=userWeibo.get(i).getWeibo().getUsername() %>>
					<input type="hidden" name="password" value=<%=request.getAttribute("password") %>>
					<input type="hidden" name="guest" value=<%=request.getAttribute("guest") %>>
					<input type="hidden" name="page" value="otherUser">
					<input type="submit" value="💬"style="background-color:#FFFCEC;">
				</form>
				<br>
					<%
						for(int k=0;k<userWeibo.get(i).getWeibo().getComment().size();++k){
					%>		
						<fieldset style="width:95%">
						<legend>
							<img alt="pic" src="userImg/<%=userWeibo.get(i).getWeibo().getComment().get(k).getCommentUser() %>.jpg" height="40px" width="40px">
							&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
							&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
							&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
							&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
							<font size="2" >🕘</font>
							<font size="1"><%= userWeibo.get(i).getWeibo().getComment().get(k).getCommentTime() %></font>
						</legend>
						<font size="2.5"><%=userWeibo.get(i).getWeibo().getComment().get(k).getComment() %></font>
						</fieldset>	
						<br>						
					<%		
						}
					%>
				</fieldset>
				<br><br>
			<%		
				}
			%>
	</div>
</body>
</html>