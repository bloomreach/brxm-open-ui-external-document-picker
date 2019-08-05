package com.bloomreach.cms.openui.security.valve;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.jcr.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.hippoecm.hst.core.container.AbstractBaseOrderableValve;
import org.hippoecm.hst.core.container.ContainerException;
import org.hippoecm.hst.core.container.ValveContext;

public class SimpleOpenUiSecurityValve extends AbstractBaseOrderableValve {

    private static final String OPEN_UI_REGISTRY = "/hippo:configuration/hippo:frontend/cms/ui-extensions/";


    private static void sendError(final HttpServletResponse servletResponse, final int errorCode) throws ContainerException {
        try {
            servletResponse.sendError(errorCode);
        } catch (IOException e) {
            throw new ContainerException(String.format("Unable to send unauthorized (%s) response to client", errorCode), e);
        }
    }

    @Override
    public void invoke(final ValveContext context) throws ContainerException {
        HttpServletRequest servletRequest = context.getServletRequest();
        String clientId = servletRequest.getParameter("clientId");
        if (StringUtils.isNotEmpty(clientId)) {
            try {
                String application = servletRequest.getPathInfo().split("\\/")[1];
                Session session = context.getRequestContext().getSession();
                if (session.itemExists(OPEN_UI_REGISTRY + application)) {
                    String appClientId = session.getNode(OPEN_UI_REGISTRY + application).getProperty("frontend:clientid").getString();
                    if (clientId.equals(appClientId)) {
                        context.invokeNext();
                    } else {
                        sendError(context.getServletResponse(), HttpServletResponse.SC_UNAUTHORIZED);
                    }
                } else {
                    sendError(context.getServletResponse(), HttpServletResponse.SC_UNAUTHORIZED);
                }
            } catch (Exception e) {
                sendError(context.getServletResponse(), HttpServletResponse.SC_UNAUTHORIZED);
            }
        } else {
            sendError(context.getServletResponse(), HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
