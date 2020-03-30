package service.item.impl;

import common.Result;
import mapper.ItemMessageMapper;
import org.springframework.stereotype.Service;
import service.item.vo.ItemMessage;
import service.item.IItemMessageService;
import util.DateUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Lxh
 * @date 2020/3/24 11:25
 */
@Service
public class ItemMessageServiceImpl implements IItemMessageService {
    @Resource
    private ItemMessageMapper itemMessageMapper;

    @Override
    public List<ItemMessage> findAll() {
        return itemMessageMapper.findAll();
    }

    @Override
    public List<ItemMessage> getCategoryName() {
        return itemMessageMapper.getCategoryName();
    }

    @Override
    public Result addMessage(ItemMessage itemMessage) {

        return null;
    }


}
