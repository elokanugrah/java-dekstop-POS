/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

/**
 *
 * @author Elok Anugrah
 */
public class userSession {
    private static String username;
    private static String password;
    
    public static String getUsername() {
        return username;
    }
 
    public static void setUsername(String username) {
        userSession.username = username;
    }
 
    public static String getPassword() {
        return password;
    }
 
    public static void setPassword(String password) {
        userSession.password = password;
    }
}
