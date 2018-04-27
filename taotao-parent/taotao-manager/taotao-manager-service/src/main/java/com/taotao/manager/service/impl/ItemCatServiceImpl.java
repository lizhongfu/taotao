package com.taotao.manager.service.impl;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.manager.mapper.TbItemCatMapper;
import com.taotao.manager.pojo.TbItemCat;
import com.taotao.manager.pojo.TbItemCatExample;
import com.taotao.manager.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper itemCatMapper;

    public List<EUTreeNode> getItemCatList(long parentId) {
        //根据parentId查询分类列表
        TbItemCatExample example = new TbItemCatExample();
        //设置查询条件
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        //分类列表转换成TreeNode的列表
        List<EUTreeNode> resultList = new ArrayList<>();
        for (TbItemCat tbItemCat : list) {
            //创建一个TreeNode对象
            EUTreeNode node = new EUTreeNode();
            node.setId(tbItemCat.getId());
            node.setText(tbItemCat.getName());
            node.setState(tbItemCat.getIsParent() ? "closed" : "open");

            resultList.add(node);
        }

        return resultList;
    }
}
