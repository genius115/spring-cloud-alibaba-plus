package com.xiaomai.activiti.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 工作流前端化的controller(新建)
 *
 * @Author: tanghh
 */

@RequestMapping("/activiti")
@RestController
public class ActivitiController {

    private static Logger logger=LoggerFactory.getLogger(ActivitiController.class);
    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ObjectMapper objectMapper;



    @ResponseBody
    public String newMode() {
        return "spring-boot";
    }



    /**
     * 根据Model部署流程
     */
    @PostMapping(value = "deploy/{modelId}")
    public void deploy(@PathVariable("modelId") String modelId) {
        try {
            Model modelData = repositoryService.getModel(modelId);
            ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
            byte[] bpmnBytes = null;

            BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
            bpmnBytes = new BpmnXMLConverter().convertToXML(model);

            String processName = modelData.getName() + ".bpmn20.xml";
            List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().processDefinitionResourceName(processName).list();
            for (ProcessDefinition definition:list){
                //删除原有流程定义,正在使用的流程定义无法删除
                repositoryService.deleteDeployment(definition.getDeploymentId());
            }
            Deployment deployment = repositoryService.createDeployment().name(modelData.getName()).addString(processName, new String(bpmnBytes)).deploy();
            System.out.println("流程部署id----"+deployment.getId());
        } catch (Exception e) {
            logger.error("根据模型部署流程失败：modelId={}", modelId, e);
        }
    }

    /**
     * 新建模型
     * @return
     * @throws UnsupportedEncodingException
     */
    @GetMapping("/create")
    public ModelAndView newModel() throws UnsupportedEncodingException {
//    	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//    	RepositoryService repositoryService = processEngine.getRepositoryService();
//    	ObjectMapper objectMapper = new ObjectMapper();
        //初始化一个空模型
        Model model = repositoryService.newModel();

        //设置一些默认信息，可以用参数接收
        int revision = 1;
        String key = "process";
        String name = "process";
        String description = "新建流程模型";

        //ModelEditorSource
        ObjectNode editorNode = objectMapper.createObjectNode();
        editorNode.put("id", "canvas");
        editorNode.put("resourceId", "canvas");
        ObjectNode stencilSetNode = objectMapper.createObjectNode();
        stencilSetNode.put("namespace","http://b3mn.org/stencilset/bpmn2.0#");
        editorNode.put("stencilset" , stencilSetNode);


        ObjectNode modelNode = objectMapper.createObjectNode();
        modelNode.put(ModelDataJsonConstants.MODEL_NAME, name);
        modelNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
        modelNode.put(ModelDataJsonConstants.MODEL_REVISION, revision);

        model.setName(name);
        model.setKey(key);
        model.setMetaInfo(modelNode.toString());

        repositoryService.saveModel(model);

        String id = model.getId();

        repositoryService.addModelEditorSource(id, editorNode.toString().getBytes("utf-8"));
        return new ModelAndView("redirect:/modeler.html?modelId=" + id);
    }
}
