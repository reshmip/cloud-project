package com.neu.myportal.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.myportal.dao.UserDAO;
import com.neu.myportal.pojo.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {

		return "home";
	}
	
	@RequestMapping(value="/home.htm", method=RequestMethod.GET)
    public String initializeForm() { 
   
        return "home"; 
    }
	
	@RequestMapping(value="/loginUser.htm", method=RequestMethod.POST)
	public String submitLoginJSForm(HttpServletRequest request,@ModelAttribute("user") User user,BindingResult result,Model model){
		
		if(result.hasErrors()){
			return "home";
		}
		try{
			System.out.println("login controller");
		String userName = user.getUserName();
		String pwd = user.getPassword();
		
		UserDAO userDao = new UserDAO();
		boolean res = userDao.authenticateUser(userName, pwd);
		User u = userDao.getUserByUserName(userName,pwd);
		if(res == true){
			HttpSession session = request.getSession(true);
			session.setAttribute("userName", u.getUserName());
			//System.out.println("name:"+u.getFirstName()+"email:"+u.getEmailId()+"role:"+u.getRole());
			session.setAttribute("userObj",u);
			model.addAttribute("username",u.getUserName());
			if(u.getUserName().equalsIgnoreCase("admin")&&u.getPassword().equalsIgnoreCase("password")){
				System.out.println("hi");
				userDao.loginSession(userName, pwd);
				//u.setLogged("true");
				return "simulationPage";
			}
			else
				return "patientPage";
		}else{
			model.addAttribute("error",true);
			
		}
		return "home";
		}
		
		catch(Exception e){
			e.printStackTrace();
			model.addAttribute("error",true);
			return "home";
		}
		
	}
	
	@RequestMapping(value="/submit.htm", method=RequestMethod.GET)
	public void runPythonScript(HttpSession session) {
		try {
			
			System.out.println("Inside the method");
		//	String pythonScript = "./Sample.py";
			String pythonScript = "C:\\Users\\Reshmi\\Documents\\Projects\\PatientPortal\\Sample.py";
			String cmd[] = new String[2];
			
			cmd[0] = "C:\\Users\\Reshmi\\Anaconda2\\python.exe";
			cmd[1] = pythonScript;
		
			
			Runtime rt = Runtime.getRuntime();
		
			Process pr = rt.exec(cmd);
			
			BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line = "";
			while((line=bfr.readLine())!=null)
			{
				//System.out.println("Inside while");
				System.out.println(line);
				//System.out.println("session:"+ );
			}
			User user = (User) session.getAttribute("userObj");
			System.out.println("logged as"+user.getUserName());
			//System.out.println("End of try block");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
}

