package com.example.demo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/public")
    public String pub(){
        return "This is public url";
    }

    @GetMapping
//    @PreAuthorize("hasRole('user')")
    public String hello() {
        return "Private endpoint accessible to users with 'User' role";
    }

    
    @GetMapping("/key")
//    @PreAuthorize("hasRole('admin')")
    public String privateEndpoint() {
    	return "Private endpoint accessible to users with 'Admin' role";
    }
}
