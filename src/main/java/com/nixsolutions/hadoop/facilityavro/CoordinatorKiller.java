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


class CoordinatorKiller extends PropertyHolder {

    void killCoordinator(String coordinatorName) {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        byte[] b = new byte[1];
        try {
            fsIn = fs.open(propertyFile);
            while(fsIn.read(b)!=-1){
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
            List<CoordinatorJob> jobList = oozie.getCoordJobsInfo("status=RUNNING", 1, 100);
            for (int i = 0; i < jobList.size(); i++) {
                String result = jobList.get(i).getAppName();
                if (i > 0 && result.equals(coordinatorName)) {
                    oozie.kill(jobList.get(i).getId());
                }
            }
        } catch (OozieClientException e1) {
            throw new RuntimeException(e1);
        }
    }
}
