/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wordpress.salaboy.api;

import com.wordpress.salaboy.conf.HumanTaskServiceConfiguration;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author salaboy
 */
public class HumanTaskServiceFactory {
    
    public static HumanTaskService newHumanTaskService(HumanTaskServiceConfiguration config) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        Map<String, AuthorizedTaskOperations> clientConfigs = new HashMap<String, AuthorizedTaskOperations>();
        
        for(String key : config.getHumanTaskClientConfigurations().keySet()){
           if(config.getHumanTaskClientConfigurations().get(key).getType().equals("jBPM5") ){
                clientConfigs.put("jBPM5TaskOperation", 
                        (AuthorizedTaskOperations) Class.forName("com.wordpress.salaboy.smarttasks.jbpm5wrapper.JBPM5AuthorizedTaskOperations")
                        .newInstance());
           }
        }
        
        HumanTaskService humanTaskService = new HumanTaskServiceImpl(clientConfigs);
        
        return humanTaskService;
    }

}