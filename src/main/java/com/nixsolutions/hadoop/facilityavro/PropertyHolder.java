package com.nixsolutions.hadoop.facilityavro;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

abstract class PropertyHolder {
    static Configuration config;
    static FileSystem fs;
    static Path propertyFile = new Path("hdfs://sandbox.hortonworks.com:8020/app/config/sandbox.properties");
    static FSDataInputStream fsIn = null;
    static FSDataOutputStream fsOut = null;

    static {
        config = new Configuration();
        config.addResource(new Path("/HADOOP_HOME/conf/core-site.xml"));
        config.set("fs.default.name", "hdfs://sandbox.hortonworks.com:8020");
        try {
            fs = FileSystem.get(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
