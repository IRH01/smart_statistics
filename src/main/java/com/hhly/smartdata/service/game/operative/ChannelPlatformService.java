package com.hhly.smartdata.service.game.operative;

import com.hhly.smartdata.model.authentication.Node;
import com.hhly.smartdata.model.game.operative.ChannelPlatform;

import java.util.List;

public interface ChannelPlatformService{

    /**
     * 分页，条件查询
     *
     * @param condition
     * @param page
     * @return
     */
    public List<ChannelPlatform> find(ChannelPlatform condition, int pageNumber,
                                      int pageSize);

    /**
     * 根据ChannelId查询
     *
     * @param channelId
     * @return
     */
    public ChannelPlatform findByChannelId(String channelId);

    /**
     * 插入
     *
     * @param value
     * @return
     */
    public boolean add(ChannelPlatform value);

    /**
     * 根据id删除
     *
     * @param tlbId
     * @return
     */
    public boolean deleteById(String channelId);

    /**
     * 根据channelId获取channelName
     *
     * @param channelId
     * @return
     */
    public String findChannel(String channelId);

    /**
     * 获取多选的树节点
     *
     * @return
     */
    public List<Node> getTreeNode(Integer userId);

    /**
     * 根据channelId和platformId查询
     *
     * @param channelId
     * @param platformId
     * @return
     */
    public List<ChannelPlatform> find(String channelId, String platformId);

}
