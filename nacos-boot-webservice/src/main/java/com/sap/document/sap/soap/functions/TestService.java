package com.sap.document.sap.soap.functions;

import com.sap.document.sap.soap.functions.mc_style.ZFII00JGJSWSResponse;
import com.sap.document.sap.soap.functions.mc_style.ZFII00JGJSWS_Service;

/**
 * @author wangfeng
 * @date 2020/12/8
 */
public class TestService {
    public static void main(String[] args) {
        ZFII00JGJSWS_Service service = new ZFII00JGJSWS_Service();
        String  zfii00JGJSWSResponse = service.getZFII00JGJSWS().zfii00JGJSWS("","");
    }
}
