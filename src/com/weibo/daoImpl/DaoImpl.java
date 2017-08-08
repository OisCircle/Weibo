package com.weibo.daoImpl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.weibo.dao.Dao;
import com.weibo.model.Comment;
import com.weibo.model.Follow;
import com.weibo.model.Follower;
import com.weibo.model.User;
import com.weibo.model.UserLike;
import com.weibo.model.UserWeibo;
import com.weibo.model.Weibo;
@Component("dao")
public class DaoImpl implements Dao{
	ApplicationContext ctx;
	@Override
	public void deleteWeibo(int userId,int weiboId){
		User u=this.getUser(userId);
		Weibo weibo=this.getWeibo(weiboId);
		u.setWeibo(u.getWeibo()-1);
		UserWeibo userWeibo=weibo.getUserWeibo();
		ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		SessionFactory sf=ctx.getBean(SessionFactory.class);
		Session s=sf.openSession();
		s.beginTransaction();
		//
		s.update(u);
		s.delete(userWeibo);
		s.delete(weibo);
		//
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
	@Override
	public void addComment(Comment comment){
		ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		SessionFactory sf=ctx.getBean(SessionFactory.class);
		Session s=sf.openSession();
		s.beginTransaction();
		//
		
		s.save(comment);
		
		//
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
	@Override
	public User getUser(int userId){
		ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		SessionFactory sf=ctx.getBean(SessionFactory.class);
		Session s=sf.openSession();
		s.beginTransaction();
		//
		User u=(User) s.get(User.class, userId);
		//
		s.getTransaction().commit();
		s.close();
		sf.close();
		return u;
	}
	@Override
	public User getUser(String username){
		ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		SessionFactory sf=ctx.getBean(SessionFactory.class);
		Session s=sf.openSession();
		s.beginTransaction();
		//
		Query q=s.createQuery("from User");
		List<User> users=q.list();
		for(int i=0;i<users.size();++i){
			if(users.get(i).getUsername().equals(username)){
				return users.get(i);
			}
		}
		
		//
		s.getTransaction().commit();
		s.close();
		sf.close();
		//�������򷵻�null
		return null;
	}
	@Override
	public Weibo getWeibo(int weiboId){
		ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		SessionFactory sf=ctx.getBean(SessionFactory.class);
		Session s=sf.openSession();
		s.beginTransaction();
		//
		Weibo weibo=(Weibo) s.get(Weibo.class, weiboId);
		//
		s.getTransaction().commit();
		s.close();
		sf.close();
		return weibo;
	}
	@Override
	public List<Weibo> getWeibo(){
		ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		SessionFactory sf=ctx.getBean(SessionFactory.class);
		Session s=sf.openSession();
		s.beginTransaction();
		
		Query q=s.createQuery("from Weibo");
		List<Weibo> weibo=(List<Weibo>)q.list();
		
		s.getTransaction().commit();
		s.close();
		sf.close();
		return weibo;
	}
	
	@Override
	public void addUser(User u) {
		ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		SessionFactory sf=ctx.getBean(SessionFactory.class);
		Session s=sf.openSession();
		s.beginTransaction();
		//�Ժ����hibernateTemplate
		
		s.save(u);
		System.out.println("a user added!");
		//��������
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
	@Override
	public void updateUser(User u) {
		ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		SessionFactory sf=ctx.getBean(SessionFactory.class);
		Session s=sf.openSession();
		s.beginTransaction();
		//�Ժ����hibernateTemplate
		
		s.merge(u);
		System.out.println("a user updated!");
		//��������
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
	@Override
	public void publishWeibo(int userId, String message) {
		ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		SessionFactory sf=ctx.getBean(SessionFactory.class);
		Session s=sf.openSession();
		s.beginTransaction();
		
		Weibo weibo=new Weibo();
		User u=(User) s.get(User.class, userId);
		
		
		//��User��UserWeibo�Ĳ���
		UserWeibo userWeibo=new UserWeibo();
		//����˵˵
		userWeibo.setMessage(message);
		//΢��������1
		u.setWeibo(u.getWeibo()+1);
		//��������
		Date date=new Date();
		String publishedTime=date.toLocaleString();
		userWeibo.setMessagePublishedTime(publishedTime);
		//����
		userWeibo.setWeibo(weibo);
		weibo.setUserWeibo(userWeibo);
		u.getUserWeibo().add(userWeibo);
		
		//��Weibo�Ĳ���
		weibo.setUsername(u.getUsername());
		weibo.setPublishedTime(publishedTime);
		weibo.setMessage(message);
		
		//�������ݿ�
		s.save(u);
		s.save(weibo);
		
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
	
	@Override
	public void updateWeibo(Weibo weibo){
		ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		SessionFactory sf=ctx.getBean(SessionFactory.class);
		Session s=sf.openSession();
		s.beginTransaction();
		
		s.update(weibo);
		
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
	@Override
	public void saveWeibo(Weibo weibo){
		ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		SessionFactory sf=ctx.getBean(SessionFactory.class);
		Session s=sf.openSession();
		s.beginTransaction();
		
		s.save(weibo);
		
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
	@Override
	public void deleteUserLike(UserLike userLike){
		ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		SessionFactory sf=ctx.getBean(SessionFactory.class);
		Session s=sf.openSession();
		s.beginTransaction();
		
		s.delete(userLike);
		
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
}
