package by.epamtc.enrollmentsystem.tag;
import by.epamtc.enrollmentsystem.controller.mapping.SessionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class EnrollmentTag extends TagSupport {
    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
        String ip = request.getRemoteAddr() ;
        JspWriter out = this.pageContext.getOut();
        try {
            out.write(ip);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return super.doStartTag();
//        return SKIP_BODY;
    }

    public int doEndTag() {
        return EVAL_PAGE;
    }
}
