package com.note.management.security.util;

import org.springframework.security.core.context.SecurityContextHolder;

import com.note.management.security.service.entity.SecurityUser;
import com.note.model.user.User;


/**
 * 当前登陆用户
 */
public class SecurityUtil {

    /**
     * 获取登陆用户
     */
//    public static User currentLogin() {
//        try {
//            SecurityUser userDetails = (SecurityUser) SecurityContextHolder.getContext().getAuthentication()
//                    .getPrincipal();
//            return userDetails.getUser();
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    public static void setSecurityUser(User user) {
//        try {
//            SecurityUser userDetails = (SecurityUser) SecurityContextHolder.getContext().getAuthentication()
//                    .getPrincipal();
//            userDetails.setUser(user);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    
	public static User currentLogin() {
        try {
            SecurityUser userDetails = (SecurityUser) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            return userDetails.getUser();
        } catch (Exception e) {
            return null;
        }
        
    }

    public static void setSecurityUser(User user) {
        try {
            SecurityUser userDetails = (SecurityUser) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            userDetails.setUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 

}
