package by.epamtc.enrollmentsystem.tag;
import by.epamtc.enrollmentsystem.controller.mapping.SessionMapping;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class EnrollmentTag extends TagSupport {
    public int doStartTag() throws JspException {
        try {
            String name = (String) pageContext.getAttribute(SessionMapping.USER_LOGIN);
            pageContext.getOut().print("Hello " + name);
        } catch (IOException ex) {
            throw new JspTagException(ex.getMessage());
        }
        return SKIP_BODY;
    }
    public int doEndTag() {
        return EVAL_PAGE;
    }
}
