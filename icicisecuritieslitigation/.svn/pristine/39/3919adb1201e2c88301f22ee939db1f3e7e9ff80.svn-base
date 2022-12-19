package lcmt.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginIntercepters implements HandlerInterceptor {
	
	private @Value("#{config['project_name']?: 'null'}") String projectName;
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		if( !arg0.getRequestURI().equals("/"+projectName+"/userLogin") && !arg0.getRequestURI().equals("/"+projectName+"/authenticateUser") && !arg0.getRequestURI().equals("/"+projectName+"/userLogout") && !arg0.getRequestURI().equals("/"+projectName+"/pageForPeopleSoft") && !arg0.getRequestURI().equals("/"+projectName+"/authenticateUserPeopleSoft") && !arg0.getRequestURI().equals("/"+projectName+"/addHearingStageOnHearing?liti_id="+arg0.getParameter("liti_id")+"&hear_id="+arg0.getParameter("hear_id")) && !arg0.getRequestURI().equals("/"+projectName+"/saveHearingStageOnHearing") ){
			if(arg0.getSession().getAttribute("sess_user_id") == null || arg0.getSession().getAttribute("username") == null){
				//System.out.println("userlogin");
				//System.out.println("hear id is :"+arg0.getParameter("hear_id"));
				//arg1.sendRedirect("./");
				arg1.sendRedirect("/"+projectName+"/userLogin");
				return false;
			}else{
				if(!arg0.getRequestURI().equals("/"+projectName+"/ChangePassword") && !arg0.getRequestURI().equals("/"+projectName+"/ChangeNewPassword") && !arg0.getRequestURI().equals("/"+projectName+"/userLogout") && !arg0.getRequestURI().equals("/"+projectName+"/resetUserPassword") && !arg0.getRequestURI().equals("/"+projectName+"/getOriginalPassword") && !arg0.getRequestURI().equals("/"+projectName+"/userLogout")){
					if(arg0.getSession().getAttribute("firstLogin") == null){
						arg1.sendRedirect("/"+projectName+"/userLogin");
						return false;
					}else{
						if(arg0.getSession().getAttribute("firstLogin").equals("1")){
							arg1.sendRedirect("/"+projectName+"/ChangePassword");
							return false;
						}else{
							/*if(arg0.getRequestURI().equals("/"+projectName+"/legalUpdates")){
							if(arg0.getSession().getAttribute("sess_user_id").equals("2")){
								arg0.getSession().setAttribute("AdminRights", "1");
								return true;
							}else{
								arg0.getSession().setAttribute("AdminRights", "0");
								arg1.sendRedirect("/"+projectName+"/dashboard");
								return false;
							}
						}else{
							return true;
						}*/
						return true;
					}	
				}
			}else{
				return true;
			}
		}
	}
	else{
		return true;
	}
}

}


