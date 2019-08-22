package io.vinson.file.service.util;

import io.vinson.file.converter.OfficeDocumentConverter;

import java.io.File;

public class OfficeToPdf {
    /**
     * 获取OpenOffice.org 3的安装目录
     */

    ConverterUtils converterUtils;

    /**
     * 转换文件
     *
     * @param inputFile
     * @param outputFilePath_end
     * @param converter
     */
    public static void converterFile(File inputFile, String outputFilePath_end,
                                     OfficeDocumentConverter converter) {
        File outputFile = new File(outputFilePath_end);
        // 假如目标路径不存在,则新建该路径
        if (!outputFile.getParentFile().exists()) {
            outputFile.getParentFile().mkdirs();
        }
        converter.convert(inputFile, outputFile);
    }

    /**
     * 使Office2003-2007全部格式的文档(.doc|.docx|.xls|.xlsx|.ppt|.pptx) 转化为pdf文件
     *
     * @param inputFilePath
     *            源文件路径，如："e:/test.docx"
     * @param outputFilePath
     *            目标文件路径，如："e:/test_docx.pdf"
     * @return
     */
    public void office2pdf(String inputFilePath, String outputFilePath) {
        if(inputFilePath == null) {
            throw new IllegalArgumentException("input file path must not null");
        }

        OfficeDocumentConverter converter = converterUtils.getDocumentConverter();
        File inputFile = new File(inputFilePath);
        // 判断目标文件路径是否为空
        if (outputFilePath == null) {
            // 转换后的文件路径
            outputFilePath = getOutputFilePath(inputFilePath);
        }
        if (inputFile.exists()) {
            converterFile(inputFile, outputFilePath, converter);
        }
    }

    /**
     * 获取输出文件
     *
     * @param inputFilePath
     * @return
     */
    public static String getOutputFilePath(String inputFilePath) {
        String outputFilePath = inputFilePath.replaceAll("."
                + getPostfix(inputFilePath), ".pdf");
        return outputFilePath;
    }

    /**
     * 根据文件名获取后缀
     */
    public static String getPostfix(String inputFilePath) {
        return inputFilePath.substring(inputFilePath.lastIndexOf(".") + 1);
    }

}
