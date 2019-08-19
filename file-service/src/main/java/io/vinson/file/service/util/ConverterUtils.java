package io.vinson.file.service.util;

import com.sun.star.document.UpdateDocMode;
import io.vinson.file.converter.OfficeDocumentConverter;
import io.vinson.file.converter.office.DefaultOfficeManagerConfiguration;
import io.vinson.file.converter.office.OfficeManager;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class ConverterUtils {

    String officeHome;

    OfficeManager officeManager;

    public void initOfficeManager() {
        DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();
        configuration.setOfficeHome(officeHome);
        configuration.setPortNumber(8100);
        officeManager = configuration.buildOfficeManager();
        officeManager.start();
        // 设置任务执行超时为5分钟
        // configuration.setTaskExecutionTimeout(1000 * 60 * 5L);//
        // 设置任务队列超时为24小时
        // configuration.setTaskQueueTimeout(1000 * 60 * 60 * 24L);//
    }

    public OfficeDocumentConverter getDocumentConverter() {
        // TODO: class
        OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager, null);
        converter.setDefaultLoadProperties(getLoadProperties());
        return converter;
    }

    private Map<String,?> getLoadProperties() {
        Map<String,Object> loadProperties = new HashMap<>(10);
        loadProperties.put("Hidden", true);
        loadProperties.put("ReadOnly", true);
        loadProperties.put("UpdateDocMode", UpdateDocMode.QUIET_UPDATE);
        loadProperties.put("CharacterSet", Charset.forName("UTF-8").name());
        return loadProperties;
    }

    public void destroyOfficeManager(){
        if (null != officeManager && officeManager.isRunning()) {
            officeManager.stop();
        }
    }

}
