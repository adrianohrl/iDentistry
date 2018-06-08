/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.util;

import java.util.Collection;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.log4j.Logger;

/**
 *
 * @author adrianohrl
 */
public class ReportUtil {
    
    private static final Logger LOGGER = Logger.getLogger(ReportUtil.class);
    private static final String SOURCE_DIRECTORY = PropertyUtil.getProperty("general.config.repors.source.directory");
    
    public static void generateReport(String sourceFilename, Collection<?> data, Map parameters, String pathToSave) {
        String filename = SOURCE_DIRECTORY + sourceFilename;
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(data);
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(filename, parameters, beanColDataSource);
            LOGGER.info("Generated " + filename + ".");
            JasperExportManager.exportReportToPdfFile(jasperPrint, pathToSave);
            LOGGER.info("Exported " + filename + " to " + pathToSave + ".");
        } catch (JRException e) {
            LOGGER.error("Error while generating and exporting the " + sourceFilename + " to " + pathToSave);
        }
    }
    
}
