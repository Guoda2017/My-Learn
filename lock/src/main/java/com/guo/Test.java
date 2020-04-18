package com.guo;

import java.util.Hashtable;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description:
 * @author: guofengbo
 * @create: 2020-03-05 09:42
 **/
public class Test {

    public static void main(String[] args) throws Exception {

        //AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();

        String username = "sso";
        String password = "YOUxin869";
        //String server = "ldap://10.60.12.12:389";
        String server = "ldap://10.60.12.12:389";
        try {
            Hashtable<String, String> env = new Hashtable<String, String>();
            //用户名称，cn,ou,dc 分别：用户，组，域
            env.put(Context.SECURITY_PRINCIPAL, username);
            //用户密码 cn 的密码
            env.put(Context.SECURITY_CREDENTIALS, password);
            //url 格式：协议://ip:端口/组,域   ,直接连接到域或者组上面
            env.put(Context.PROVIDER_URL, server);
            //LDAP 工厂
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            //验证的类型     "none", "simple", "strong"
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            LdapContext ldapContext = new InitialLdapContext(env, null);
            System.out.println("ldapContext:" + ldapContext);
            System.out.println("用户" + username + "登录验证成功");
        } catch (NamingException e) {
            System.out.println("用户" + username + "登录验证失败");
            System.out.println("错误信息："+e.getExplanation());
        }

    }
}
