package com.nixsolutions.hadoop.facilityavro;

import org.apache.oozie.client.AuthOozieClient;
import org.apache.oozie.client.CoordinatorJob;
import org.apache.oozie.client.OozieClient;
import org.apache.oozie.client.OozieClientException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Properties;


public class CoordinatorKiller extends PropertyHolder {

    public void killCoordinator(String coordinatorName) {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        byte[] b = new byte[1];
        try {
            fsin = fs.open(propertyFile);
            while(fsin.read(b)!=-1){
                bo.write(b);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String properties = new String(bo.toByteArray());
        //load properties for getting host IP
        Properties prop = new Properties();
        try {
            prop.load(new StringReader(properties));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String host = prop.getProperty("host");
        OozieClient oozie = new AuthOozieClient("http://" + host + ":11000/oozie");
        try {
            List<CoordinatorJob> joblist = oozie.getCoordJobsInfo("status=RUNNING", 1, 100);
            for (int i = 0; i < joblist.size(); i++) {
                String result = joblist.get(i).getAppName();
                if (i > 0 && result.equals(coordinatorName)) {
                    oozie.kill(joblist.get(i).getId());
                }
            }
        } catch (OozieClientException e1) {
            throw new RuntimeException(e1);
        }
    }
}
