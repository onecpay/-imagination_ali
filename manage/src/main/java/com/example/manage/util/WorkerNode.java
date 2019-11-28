//package com.example.manage.util;
//
//
//
//import com.common.entity.BaseEntity;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.Date;
//
//@Table(name = "sys_worker_node")
//public class WorkerNode extends BaseEntity implements Serializable {
//
//    /**
//     * auto increment id
//     */
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", updatable = false)
//    private Long id;
//
//    /**
//     * host name
//     */
//    @Column(name = "host_name")
//    private String hostName;
//
//    /**
//     * port
//     */
//    private String port;
//
//    /**
//     * node type: ACTUAL or CONTAINER
//     */
//    private String type;
//
//    /**
//     * launch date
//     */
//    @Column(name = "launch_date")
//    private Date launchDate;
//
//    /**
//     * modified time
//     */
//    private Date modified;
//
//    /**
//     * created time
//     */
//    private Date created;
//
//    /**
//     * 服务名称
//     */
//    @Column(name = "service_name")
//    private String serviceName;
//
//    @Version
//    private Integer version;
//
//    private static final long serialVersionUID = 1L;
//
//    /**
//     * 获取auto increment id
//     * @return id - auto increment id
//     */
//    public Long getId() {
//        return id;
//    }
//
//    /**
//     * 设置auto increment id
//     * @param id auto increment id
//     */
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    /**
//     * 获取host name
//     * @return host_name - host name
//     */
//    public String getHostName() {
//        return hostName;
//    }
//
//    /**
//     * 设置host name
//     * @param hostName host name
//     */
//    public void setHostName(String hostName) {
//        this.hostName = hostName == null ? null : hostName.trim();
//    }
//
//    /**
//     * 获取port
//     * @return port - port
//     */
//    public String getPort() {
//        return port;
//    }
//
//    /**
//     * 设置port
//     * @param port port
//     */
//    public void setPort(String port) {
//        this.port = port == null ? null : port.trim();
//    }
//
//    /**
//     * 获取node type: ACTUAL or CONTAINER
//     * @return type - node type: ACTUAL or CONTAINER
//     */
//    public String getType() {
//        return type;
//    }
//
//    /**
//     * 设置node type: ACTUAL or CONTAINER
//     * @param type node type: ACTUAL or CONTAINER
//     */
//    public void setType(String type) {
//        this.type = type == null ? null : type.trim();
//    }
//
//    /**
//     * 获取launch date
//     * @return launch_date - launch date
//     */
//    public Date getLaunchDate() {
//        return launchDate;
//    }
//
//    /**
//     * 设置launch date
//     * @param launchDate launch date
//     */
//    public void setLaunchDate(Date launchDate) {
//        this.launchDate = launchDate;
//    }
//
//    /**
//     * 获取modified time
//     * @return modified - modified time
//     */
//    public Date getModified() {
//        return modified;
//    }
//
//    /**
//     * 设置modified time
//     * @param modified modified time
//     */
//    public void setModified(Date modified) {
//        this.modified = modified;
//    }
//
//    /**
//     * 获取created time
//     * @return created - created time
//     */
//    public Date getCreated() {
//        return created;
//    }
//
//    /**
//     * 设置created time
//     * @param created created time
//     */
//    public void setCreated(Date created) {
//        this.created = created;
//    }
//
//    /**
//     * 获取服务名称
//     * @return service_name - 服务名称
//     */
//    public String getServiceName() {
//        return serviceName;
//    }
//
//    /**
//     * 设置服务名称
//     * @param serviceName 服务名称
//     */
//    public void setServiceName(String serviceName) {
//        this.serviceName = serviceName == null ? null : serviceName.trim();
//    }
//
//    /**
//     * @return version
//     */
//    public Integer getVersion() {
//        return version;
//    }
//
//    /**
//     * @param version
//     */
//    public void setVersion(Integer version) {
//        this.version = version;
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(getClass().getSimpleName());
//        sb.append(" [");
//        sb.append("Hash = ").append(hashCode());
//        sb.append(", id=").append(id);
//        sb.append(", hostName=").append(hostName);
//        sb.append(", port=").append(port);
//        sb.append(", type=").append(type);
//        sb.append(", launchDate=").append(launchDate);
//        sb.append(", modified=").append(modified);
//        sb.append(", created=").append(created);
//        sb.append(", serviceName=").append(serviceName);
//        sb.append(", version=").append(version);
//        sb.append("]");
//        return sb.toString();
//    }
//}