package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyController {
    private UserRepository userRepository;
    public DummyController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/dummy/user")
    public List<User> findAll(){
       List<User> users= userRepository.findAll();
       return users;
    }
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){
        User user=userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 아뒤 없네 id: "+id);
            }
        });
        return user;
    }
    @GetMapping("/dummy/user/page")
    public List<User> pageList(@PageableDefault(size = 2,sort = "id",direction = Sort.Direction.DESC)Pageable pageable){
        Page<User> pagingUser=userRepository.findAll(pageable);
        List<User> users=pagingUser.getContent();
        return users;
    }
    @Transactional
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id,@RequestBody User requestUser){
        User user=userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정에 실패했네요");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());
        userRepository.save(user);
        return user;
    }

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            return "삭제 실패. 해당 아뒤는 디비에 없다";
        }
        return "삭제성공 아뒤: "+id;
    }
    @PostMapping("/dummy/join")
    public String join(User user){
        System.out.println("username: "+user.getUsername());
        System.out.println("password: " + user.getPassword());
        System.out.println("email: "+user.getEmail());
        user.setRole(RoleType.User);
        userRepository.save(user);
        return "회원가입 완료";
    }
}
