package com.shubhada.productservice;



import com.shubhada.productservice.inheritanceexamples.joinedtable.JTMentorRepository;
import com.shubhada.productservice.inheritanceexamples.joinedtable.JTUserRepository;
import com.shubhada.productservice.inheritanceexamples.joinedtable.Mentor;
import com.shubhada.productservice.inheritanceexamples.joinedtable.User;
import com.shubhada.productservice.inheritanceexamples.mappedsuperclass.MSMentorRepository;
import com.shubhada.productservice.inheritanceexamples.singleclass.SCMentorRepository;
import com.shubhada.productservice.inheritanceexamples.singleclass.SCUserRepository;
import com.shubhada.productservice.inheritanceexamples.tableperclass.TPCMentorRepository;
import com.shubhada.productservice.inheritanceexamples.tableperclass.TPCUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class ProductserviceApplicationTests {
   /* @Autowired
    private JTUserRepository userRepository;
    @Autowired
    private JTMentorRepository mentorRepository;

    @Autowired
    private MSMentorRepository msMentorRepository;

    @Autowired
    private SCUserRepository scUserRepository;
    @Autowired
    private SCMentorRepository scMentorRepository;

    @Autowired
    private TPCUserRepository tpcUserRepository;
    @Autowired
    private TPCMentorRepository tpcMentorRepository;

    @Test
    void contextLoads() {
    }*/
   /* @Test
    void testDiffInheritances(){
        User user=new User();
        user.setEmail("shubhadamali05@gmail.com");
        user.setPassword("shubh@123");
        userRepository.save(user);

        Mentor mentor=new Mentor();
        mentor.setEmail("shubh@gmail.com");
        mentor.setPassword("shubh1234");
        mentor.setNoOfSessions(50);
        mentor.setNoOfMentees(4);
        mentorRepository.save(mentor);
    }
    @Test
    void testMappedTable(){

        com.shubhada.productservice.inheritanceexamples.mappedsuperclass.Mentor mentor=
                new com.shubhada.productservice.inheritanceexamples.mappedsuperclass.Mentor();
        mentor.setEmail("neeraj@gmail.com");
        mentor.setPassword("neeraj1234");
        mentor.setNoOfMentees(45);
        mentor.setNoOfSessions(3);
        msMentorRepository.save(mentor);
    }
    @Test
    void testSingleClass(){
      *//*  com.shubhada.productservice.inheritanceexamples.singleclass.User user=
                new com.shubhada.productservice.inheritanceexamples.singleclass.User();
        user.setEmail("pratik09@gmail.com");
        user.setPassword("prat1233");
        scUserRepository.save(user);*//*

        com.shubhada.productservice.inheritanceexamples.singleclass.Mentor mentor=
                new com.shubhada.productservice.inheritanceexamples.singleclass.Mentor();
        mentor.setEmail("pratik09@gmail.com");
        mentor.setPassword("prat1234");
        mentor.setNoOfMentees(40);
        mentor.setNoOfSessions(5);
        scMentorRepository.save(mentor);
    }

    @Test
    void testTablePerClass(){
        com.shubhada.productservice.inheritanceexamples.tableperclass.User user=
                new com.shubhada.productservice.inheritanceexamples.tableperclass.User();
        user.setEmail("shubhra@gmail.com");
        user.setPassword("shubhra1234");
        tpcUserRepository.save(user);

        com.shubhada.productservice.inheritanceexamples.tableperclass.Mentor mentor=
                new com.shubhada.productservice.inheritanceexamples.tableperclass.Mentor();
        mentor.setEmail("gauri@gmail.com");
        mentor.setPassword("gauri1234");
        mentor.setNoOfSessions(7);
        mentor.setNoOfMentees(55);
        tpcMentorRepository.save(mentor);
    }*/
}
