package com.silicon.rest;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;
import com.silicom.utils.PropUtil;

public class RestletConfig extends Application{
	/**
     * Creates a root Restlet that will receive all incoming calls.
     */
    @Override
    public Restlet createInboundRoot() {
        // Create a router Restlet 
        Router router = new Router(getContext());
        PropUtil.init();
        // Services
        router.attach("/singup", SingUp.class);
        router.attach("/singin", SingIn.class); 
        router.attach("/getpass", GetPass.class); 
        return router;
    }
}
