package com.neu.myportal.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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
	
	private @Autowired ServletContext servletContext;
	
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
	
	@RequestMapping(value="/loginUser.htm", method=RequestMethod.GET)
    public String loginScript() { 
		System.out.println("Inside the controller");
   
        return "patientPage"; 
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
	public String runPythonScript(HttpSession session, HttpServletRequest request) {
		try {
			
			System.out.println("Inside the method");
			String cmd[] = new String[2];
			
			
			String pythonScript = servletContext.getRealPath("/WEB-INF/Sample.py");
			System.out.println(pythonScript);
			
			//cmd[0] = "C:\\Users\\Reshmi\\Anaconda2\\python.exe";
			//cmd[1] = pythonScript;
			//cmd[1] = file.toString();
		
			
			Runtime rt = Runtime.getRuntime();
			
			String commd = "python "+pythonScript;
			System.out.println(commd);
			Process pr = Runtime.getRuntime().exec(commd);
			//Process pr = rt.exec(cmd);
			System.out.println(pr.getInputStream());
			
			BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			System.out.println(bfr.read());
			String line = "";
			List<String> lines = new ArrayList<String>();	
			while((line=bfr.readLine())!=null)
			{
				//System.out.println("Inside while");
				lines.add(line);
				System.out.println(line);
				//System.out.println("session:"+ );
			}
			request.setAttribute("consoleOutput", lines);
			User user = (User) session.getAttribute("userObj");
			//System.out.println("logged as"+user.getUserName());
			//System.out.println("End of try block");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "simulationPage";
	}
		@RequestMapping(value="/submit2.htm", method=RequestMethod.GET)
		public String runUseCase2(HttpSession session,HttpServletRequest request) {
			try {
				System.out.println("Inside the method");
				String cmd[] = new String[2];
				
				
				String pythonScript = servletContext.getRealPath("/WEB-INF/usecase2.py");
				System.out.println(pythonScript);
				
				cmd[0] = "C:\\Users\\Reshmi\\Anaconda2\\python.exe";
				cmd[1] = pythonScript;
				//cmd[1] = file.toString();
			
				
				Runtime rt = Runtime.getRuntime();
				
				String commd = "python "+pythonScript;
				System.out.println(commd);
				//Process pr = Runtime.getRuntime().exec(commd);
				Process pr = rt.exec(cmd);
				System.out.println(pr.getInputStream());
				
				BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));
				System.out.println(bfr.read());
				String line = "";
				List<String> lines = new ArrayList<String>();	
				while((line=bfr.readLine())!=null)
				{
					//System.out.println("Inside while");
					lines.add(line);
					System.out.println(line);
					//System.out.println("session:"+ );
				}
				request.setAttribute("consoleOutput", lines);
				User user = (User) session.getAttribute("userObj");
				//System.out.println("logged as"+user.getUserName());
				//System.out.println("End of try block");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "simulationPage";
	}
		
		@RequestMapping(value="/submit3.htm", method=RequestMethod.GET)
		public String runUseCase3(HttpSession session,HttpServletRequest request) {
			try {
				
				System.out.println("Inside the method");
				String cmd[] = new String[2];
				
				
				String pythonScript = servletContext.getRealPath("/WEB-INF/usecase3.py");
				System.out.println(pythonScript);
				
				cmd[0] = "C:\\Users\\Reshmi\\Anaconda2\\python.exe";
				cmd[1] = pythonScript;
				//cmd[1] = file.toString();
			
				
				Runtime rt = Runtime.getRuntime();
				
				String commd = "python "+pythonScript;
				System.out.println(commd);
				//Process pr = Runtime.getRuntime().exec(commd);
				Process pr = rt.exec(cmd);
				System.out.println(pr.getInputStream());
				
				BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));
				System.out.println(bfr.read());
				String line = "";
				List<String> lines = new ArrayList<String>();	
				while((line=bfr.readLine())!=null)
				{
					//System.out.println("Inside while");
					lines.add(line);
					System.out.println(line);
					//System.out.println("session:"+ );
				}
				request.setAttribute("consoleOutput", lines);
				User user = (User) session.getAttribute("userObj");
				//System.out.println("logged as"+user.getUserName());
				//System.out.println("End of try block");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "simulationPage";
	}
		@RequestMapping(value="/submit4.htm", method=RequestMethod.GET)
		public String runUseCase4(HttpSession session,HttpServletRequest request) {
			try {
				
				System.out.println("Inside the method");
				String cmd[] = new String[2];
				
				
				String pythonScript = servletContext.getRealPath("/WEB-INF/usecase4.py");
				System.out.println(pythonScript);
				
				cmd[0] = "C:\\Users\\Reshmi\\Anaconda2\\python.exe";
				cmd[1] = pythonScript;
				//cmd[1] = file.toString();
			
				
				Runtime rt = Runtime.getRuntime();
				
				String commd = "python "+pythonScript;
				System.out.println(commd);
				//Process pr = Runtime.getRuntime().exec(commd);
				Process pr = rt.exec(cmd);
				System.out.println(pr.getInputStream());
				
				BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));
				System.out.println(bfr.read());
				String line = "";
				List<String> lines = new ArrayList<String>();	
				while((line=bfr.readLine())!=null)
				{
					//System.out.println("Inside while");
					lines.add(line);
					System.out.println(line);
					//System.out.println("session:"+ );
				}
				request.setAttribute("consoleOutput", lines);
				User user = (User) session.getAttribute("userObj");
				//System.out.println("logged as"+user.getUserName());
				//System.out.println("End of try block");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "simulationPage";
	}
		
		@RequestMapping(value="/submit5.htm", method=RequestMethod.GET)
		public String runUseCase5(HttpSession session,HttpServletRequest request) {
			try {
				
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "simulationPage";
		}
}

