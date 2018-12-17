package cn.hse.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.hse.service.ForwardingService;

/**
 * 整改节点待办-转发
 * @author 
 *
 */
@RestController
@RequestMapping("forwarding")
public class ForwardingController {
	private static final Logger logger=LogManager.getLogger(ForwardingController.class);
	
	@Autowired
	private ForwardingService forwardingService;
}
