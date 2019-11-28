//package com.example.manage.util;
//
//
//import com.vnionpay.card.freemarker.uid.utils.AbstractDockerUtils;
//import com.vnionpay.card.freemarker.uid.utils.AbstractNetUtils;
//import com.vnionpay.card.freemarker.uid.worker.WorkerIdAssigner;
//import com.vnionpay.card.freemarker.uid.worker.WorkerNodeType;
//import com.vnionpay.card.freemarker.utils.DateUtils;
//import com.vnionpay.card.freemarker.utils.Global;
//import com.vnionpay.card.information.dao.WorkerNodeMapper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.annotation.Resource;
//
///**
// * Represents an implementation of {@link WorkerIdAssigner}, the worker id will be discarded after assigned to the
// * UidGenerator
// * @author yutianbao
// */
//public class DisposableWorkerIdAssigner implements WorkerIdAssigner {
//
//    private static final Logger logger = LoggerFactory.getLogger(
//            DisposableWorkerIdAssigner.class);
//
//    @Resource
//    private WorkerNodeMapper workerNodeMapper;
//
//    /**
//     * Assign worker id base on database.
//     *
//     * <p>If there is host name & port in the environment, we considered that the node runs in Docker container<br>
//     * Otherwise, the node runs on an actual machine.
//     * @return assigned worker id
//     */
//    @Override
//    public long assignWorkerId() {
//        WorkerNode workerNode = buildWorkerNode();
//        WorkerNode workNode = workerNodeMapper.selectOne(workerNode);
//        if(workNode == null) {
//            logger.info("新启动服务:" + workerNode.getHostName() + workerNode.getPort() + workerNode.getServiceName());
//            workerNode.setLaunchDate(DateUtils.getBusinsessDate());
//            workerNode.setCreated(DateUtils.getBusinsessDate());
//            workerNode.setVersion(1);
//            workerNodeMapper.insertSelective(workerNode);
//            workNode = workerNode;
//        } else {
//            workNode.setModified(DateUtils.getBusinsessDate());
//            workerNodeMapper.updateByPrimaryKeySelective(workNode);
//        }
//        return workNode.getId();
//    }
//
//    /**
//     * Build worker node entity by IP and PORT
//     */
//    private WorkerNode buildWorkerNode() {
//        WorkerNode workerNode = new WorkerNode();
//        workerNode.setServiceName(Global.getConfig("spring.application.name"));
//        //workerNode.setServiceName("information-service");
//        logger.info(workerNode.toString());
//        if(AbstractDockerUtils.isDocker()) {
//            logger.info(workerNode.toString());
//            workerNode.setType(WorkerNodeType.CONTAINER.name());
//            workerNode.setHostName(AbstractDockerUtils.getDockerHost());
//            workerNode.setPort(AbstractDockerUtils.getDockerPort());
//        } else {
//            logger.info(workerNode.toString());
//            workerNode.setType(WorkerNodeType.ACTUAL.name());
//            workerNode.setHostName(AbstractNetUtils.getLocalAddress());
//            workerNode.setPort(AbstractNetUtils.getPort());
//            //workerNode.setPort("8089");
//        }
//        return workerNode;
//    }
//}
