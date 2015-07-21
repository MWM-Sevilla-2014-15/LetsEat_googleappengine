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
    	//Load System Properties
    	PropUtil.init();
    	// Create a router Restlet
        Router router = new Router(getContext());
        // Services
        router.attach("/singup", SingUp.class);
        router.attach("/singin", SingIn.class); 
        router.attach("/getpass", GetPass.class);
        router.attach("/getrestaurants", GetRestaurants.class);
        router.attach("/bookrestaurant", BookRestaurant.class);
        return router;
    }
}
