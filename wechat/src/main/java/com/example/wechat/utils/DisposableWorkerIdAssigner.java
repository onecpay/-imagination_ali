package com.example.wechat.utils;

import com.common.uid.worker.WorkerIdAssigner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents an implementation of {@link WorkerIdAssigner}, the worker id will be discarded after assigned to the
 * UidGenerator
 *
 * @author yutianbao
 */
public class DisposableWorkerIdAssigner implements WorkerIdAssigner {

    private static final Logger logger = LoggerFactory.getLogger(
            DisposableWorkerIdAssigner.class);

//    @Resource
//    private WorkerNodeMapper workerNodeMapper;

    /**
     * Assign worker id base on database.
     *
     * <p>If there is host name & port in the environment, we considered that the node runs in Docker container<br>
     * Otherwise, the node runs on an actual machine.
     *
     * @return assigned worker id
     */
    @Override
    public long assignWorkerId() {
//        Global.getConfig("spring.application.name");
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
        return 0;
    }

    /**
     * Build worker node entity by IP and PORT
     */
//    private WorkerNode buildWorkerNode() {
//        WorkerNode workerNode = new WorkerNode();
//        workerNode.setServiceName(Global.getConfig("spring.application.name"));
//        logger.info(workerNode.toString());
//        if (AbstractDockerUtils.isDocker()) {
//            logger.info(workerNode.toString());
//            workerNode.setType(WorkerNodeType.CONTAINER.name());
//            workerNode.setHostName(AbstractDockerUtils.getDockerHost());
//            workerNode.setPort(AbstractDockerUtils.getDockerPort());
//        } else {
//            logger.info(workerNode.toString());
//            workerNode.setType(WorkerNodeType.ACTUAL.name());
//            workerNode.setHostName(AbstractNetUtils.getLocalAddress());
//            workerNode.setPort(AbstractNetUtils.getPort());
//        }
//        return workerNode;
//    }
}
