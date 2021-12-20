package by.epamtc.enrollmentsystem.tag;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class IPInfoTag extends TagSupport {
    private static final Logger LOGGER = LogManager.getLogger(IPInfoTag.class);

    private String prefix;

    public void setPrefix(String prefix){
        this.prefix = prefix;
    }
    public int doStartTag() throws JspException {
        JspWriter out = this.pageContext.getOut();
        try {
            out.write(prefix + pageContext.getRequest().getRemoteAddr());
        } catch (IOException e) {
            LOGGER.log(Level.ERROR,e.getMessage());
            throw new JspException(e);
        }

        return SKIP_BODY;
    }
}
