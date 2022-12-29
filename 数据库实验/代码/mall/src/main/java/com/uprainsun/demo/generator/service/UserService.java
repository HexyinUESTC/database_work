package com.uprainsun.demo.generator.service;

import com.uprainsun.demo.generator.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author uprainsun
 * @since 2021-03-14
 */
public interface UserService extends IService<User> {
    Map<String, Object> register(User user);
    Map<String, Object> login(String name, String password, HttpSession httpSession);
    Map<String, Object> upload(MultipartFile file, String name);
    Map<String, Object> download(String name);
//    Boolean downloadImage(HttpServletResponse response, String name);
    Map<String, Object> edit(User user);
    User getUserById(int userId);
}
