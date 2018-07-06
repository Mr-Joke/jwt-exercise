package com.example.demo.service;

import com.example.demo.builder.UserBuilder;
import com.example.demo.domain.User;
import com.example.demo.exception.UserServiceException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.utils.CommonUtils;
import com.example.demo.utils.DBUtils;
import com.example.demo.utils.JwtUtils;
import com.example.demo.utils.MailUtils;
import io.jsonwebtoken.Jwts;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * Company: 3DXT
 * Author: Joker
 * DateTime: 2018/7/6 9:36
 **/
public class UserService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    private static final String ACTIVE_SITE = "http://localhost:8889/user/active?code=";

    /**
     * 用户注册
     * @param username 用户名（表单参数）
     * @param password 密码（表单参数）
     * @param email 邮箱，用于发送激活邮件（表单参数）
     * @throws IOException
     */
    public void register(String username, String password, String email) throws IOException, MessagingException {
        checkParams(username,password,email);
        User user = new UserBuilder()
                .username(username)
                .password(password)
                .email(email)
                .validate(false)
                .enable(true)
                .build();
        SqlSession sqlSession = DBUtils.getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            if(mapper.selectByUsername(username) != null){
                throw new UserServiceException("用户名已存在！");
            }
            mapper.insert(user);
            String jwts = JwtUtils.getString(username);
            String content = "请点击 <a href=\"" + ACTIVE_SITE + jwts + "\">激活</a>，请在两小时内激活，否则无法激活成功！";
            MailUtils.send(email, "xxx网站用户激活", content);
            sqlSession.commit();
        } finally {
            DBUtils.closeSession(sqlSession);
        }
    }

    /**
     * 校验参数
     * @param username
     * @param password
     * @param email
     */
    private void checkParams(String username, String password, String email) {
        if (CommonUtils.isEmptyString(username)){
            throw new UserServiceException("用户名为空！");
        }
        if (CommonUtils.isEmptyString(password)){
            throw new UserServiceException("密码为空！");
        }
        if (CommonUtils.isEmptyString(email)){
            throw new UserServiceException("邮箱为空！");
        }
        if (!email.contains("@")){
            throw new UserServiceException("邮箱格式不正确！");
        }
    }

    /**
     * 用户激活
     * @param code 激活码
     * @throws IOException
     */
    public void active(String code) throws IOException {
        SqlSession sqlSession = DBUtils.getSqlSession();
        try {
            if (CommonUtils.isEmptyString(code)){
                throw new UserServiceException("激活码为空！");
            }
            String subject = Jwts.parser().setSigningKey(JwtUtils.secretKey).parseClaimsJws(code).getBody().getSubject();
            if (CommonUtils.isEmptyString(subject)){
                throw new UserServiceException("激活码不正确！");
            }
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.selectByUsername(subject);
            if (user == null || !user.getEnable()){
                throw new UserServiceException("用户不存在");
            }
            if (user.getValidate()){
                throw new UserServiceException("用户已经激活，无须再次激活");
            }
            mapper.updateValidate(user.getId(),true);
            sqlSession.commit();
        }finally {
            DBUtils.closeSession(sqlSession);
        }
    }

    public User findByUsername(String username) throws IOException {
        if(CommonUtils.isEmptyString(username)){
            throw new UserServiceException("用户名为空！");
        }
        SqlSession sqlSession = DBUtils.getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            return mapper.selectByUsername(username);
        }finally {
            DBUtils.closeSession(sqlSession);
        }
    }
}
