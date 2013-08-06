package com.jspservletcookbook;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.*;
import javax.servlet.http.*;

public class IPFilter implements Filter {
    
    private FilterConfig config;
    public final static String IP_RANGE = "192.168";
    
    public IPFilter() {}
    
    public void  init(FilterConfig filterConfig)  throws ServletException {
    
      this.config = filterConfig;
      
    }
    
    public void  doFilter(ServletRequest request, ServletResponse response, 
      FilterChain chain) throws IOException, ServletException {
        
        String ip = request.getRemoteAddr();

        HttpServletResponse httpResp = null;
        
        if (response instanceof HttpServletResponse)
            httpResp = (HttpServletResponse) response;
            
        StringTokenizer toke = new StringTokenizer(ip,".");
        int dots = 0;
        String byte1 = "";
        String byte2 = "";
        String client = "";
        
        while (toke.hasMoreTokens()){
        
            ++dots;
            
            //if we've reached the second dot, break and check out the indx value    
            if (dots == 1){
            
                byte1 = toke.nextToken();
                
            } else {
            
                byte2 = toke.nextToken();
                break;
            }
        }//while
        
        //Piece together half of the client IP address so it can be compared with
		//the forbidden range represented by IPFilter.IP_RANGE
        client = byte1+"."+byte2; 
        
        if (IP_RANGE.equals(client)){
        
            httpResp.sendError(HttpServletResponse.SC_FORBIDDEN,
			    "That means goodbye forever!" );
            
        } else {
        
            chain.doFilter(request,response);
        }
        
    }// doFilter
    
    public void destroy(){
        /*called before the Filter instance is removed 
        from service by the web container*/
    }
    
}
