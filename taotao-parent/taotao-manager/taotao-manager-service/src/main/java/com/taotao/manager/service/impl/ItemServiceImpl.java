package com.taotao.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.manager.mapper.TbItemDescMapper;
import com.taotao.manager.mapper.TbItemMapper;
import com.taotao.manager.mapper.TbItemParamItemMapper;
import com.taotao.manager.pojo.TbItemDesc;
import com.taotao.manager.pojo.TbItemParamItem;
import com.taotao.manager.service.ItemService;
import com.taotao.manager.pojo.TbItem;
import com.taotao.manager.pojo.TbItemExample;
import com.taotao.manager.pojo.TbItemExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Override
    public TbItem getItemById(long ItemId) {
//        itemMapper.selectByPrimaryKey(ItemId);
        TbItemExample example = new TbItemExample();
        //添加查询条件
        Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(ItemId);
        List<TbItem> list = itemMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            TbItem item = list.get(0);
            return item;
        }
        return null;
    }

    @Override
    public EUDataGridResult getItemList(int page, int rows) {
        //查询商品列表
        TbItemExample example = new TbItemExample();
        //分页处理
        PageHelper.startPage(page, rows);
        List<TbItem> list = itemMapper.selectByExample(example);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public TaotaoResult creatItem(TbItem item, String desc, String itemParam) throws Exception {
        Long itemId = IDUtils.genItemId();
        item.setId(itemId);
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());

        itemMapper.insert(item);

        //添加商品描述信息
        TaotaoResult result = insertItemDesc(itemId, desc);

        //添加规格参数
        result = insertItemParamItem(itemId, itemParam);

        if (result.getStatus() != 200) {
            throw new Exception();
        }

        return TaotaoResult.ok();
    }

    private TaotaoResult insertItemDesc(Long itemId ,String desc) {
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        itemDescMapper.insert(itemDesc);

        return TaotaoResult.ok();
    }

    private TaotaoResult insertItemParamItem(Long itemId, String itemParam) {
        //创建一个pojo
        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(itemParam);
        itemParamItem.setCreated(new Date());
        itemParamItem.setUpdated(new Date());
        //向表中插入数据
        itemParamItemMapper.insert(itemParamItem);

        return TaotaoResult.ok();

    }
}
