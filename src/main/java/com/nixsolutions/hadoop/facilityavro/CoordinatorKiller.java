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

/**
 *
 */
public class CoordinatorKiller extends PropertyHolder {
    public void killCoord(String coordinatorName) throws IOException {
        fsin = fs.open(propertyFile);
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        byte[] b = new byte[1];
        while(fsin.read(b)!=-1){
            bo.write(b);
        }
        String properties = new String(bo.toByteArray());
        //load properties for getting host IP
        Properties prop = new Properties();
        prop.load(new StringReader(properties));
        String host = prop.getProperty("host");

        //killing coordinator
        OozieClient oozie = new AuthOozieClient("http://" + host + ":11000/oozie");
        List<CoordinatorJob> joblist = null;
        try {
            joblist = oozie.getCoordJobsInfo("status=RUNNING", 1, 100);
//    		System.setProperty("user.name", "root");
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
