package com.cos.blog.test;
import org.springframework.web.bind.annotation.*;
@RestController
public class HttpController {

    @GetMapping("/http/lombok")
    public String lombokTest(){
        Member m=new Member(1,"ssal","1234","email");
        System.out.println("getter: "+m.getId());
        m.setId(5000);
        System.out.println("setter: "+m.getId());
        return "lombok 완성";
    }
    @GetMapping("/http/get")
    public String getTest(Member m){

        return "get 요청 "+m.getId()+m.getUsername()+m.getPassword();
    }
    @PostMapping("/http/post")
    public String postTest(@RequestBody Member m){
        return "포스트 요청"+m.getId()+m.getUsername()+m.getPassword()+m.getEmail();
    }
    @PutMapping("/http/put")
    public String putTest(@RequestBody Member m){
        return "put 요청"+m.getId()+m.getUsername()+m.getPassword();
    }
    @DeleteMapping("/http/delete")
    public String deleteTest(){
        return "삭제 요청";
    }
}
