package com.ang.springboot_es;


import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = SpringbootEsApplication.class)
public class TestSafe {

//    @Autowired
//    StandardPBEStringEncryptor jasyptStringEncryptor;


    @Autowired
    PooledPBEStringEncryptor stringEncryptor;

    @Test
    public void getPass() {
//        String url = encryptor.encrypt("jdbc:mysql://localhost:3306/demo?characterEncoding=utf-8&useSSL=false&serverTimezone=Hongkong");
//        String name = encryptor.encrypt("root");
//        String password = encryptor.encrypt("123");
//        System.out.println(url+"----------------");
//        System.out.println(name+"----------------");
//        System.out.println(password+"----------------");

        //afoius123
//        System.out.println("==============");
//        String p123 = se.encrypt("123");
//        String qiniuSk=se.encrypt("7o-R94zZHGDtNOy-FLpVvsKnzNWyWbwd0fh-ZVVk");
//        String mailPassword=se.encrypt("5b16044498696b52");
//        System.out.println("p:"+p123);
//        System.out.println("qiniuSk:"+qiniuSk);
//        System.out.println("mailPassword:"+mailPassword);


        String mailpassword = stringEncryptor.encrypt("5b16044498696b52");
        String qiuniusk = stringEncryptor.encrypt("7o-R94zZHGDtNOy-FLpVvsKnzNWyWbwd0fh-ZVVk");
        System.out.println(mailpassword);
        System.out.println(qiuniusk);
    }


//    @Bean(name = "jasyptStringEncryptor")
//    public StandardPBEStringEncryptor standardPBEStringEncryptor() {
//        return new StandardPBEStringEncryptor();
//    }


    public static void main(String[] args) {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("12345");
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.salt.NoOpIVGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);


        String p123 = encryptor.encrypt("123");
//        String qiniuSk=encryptor.encrypt("7o-R94zZHGDtNOy-FLpVvsKnzNWyWbwd0fh-ZVVk");
//        String mailPassword=encryptor.encrypt("5b16044498696b52");

        System.out.println("password:" + p123);
//        System.out.println("qiniuSk:" + qiniuSk);
//        System.out.println("mailPassword:" + mailPassword);

    }
}